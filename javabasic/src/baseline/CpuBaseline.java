package baseline;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**********************************
 * @author zhang zhao lin
 * @date 2023年01月30日 22:38
 * @Description:
 * 使用统计方法进行基线建立的步骤如下：
 * 1. 收集性能数据：首先需要收集系统或应用程序的性能数据，例如 CPU 使用率、内存利用率、磁盘 I/O 等等。
 * 2. 数据清洗：对收集到的数据进行清洗和预处理，以确保数据的准确性和一致性。这可能包括删除无效的数据、填补缺失的数据和处理异常值。
 * 3. 确定数据分布：对清洗过后的数据进行统计分析，例如计算平均值、标准差、最大值、最小值等等，以了解数据的分布情况。
 * 4. 确定基线值：根据分布情况，确定一个性能水平作为基线，可以是平均值、中位数或某个特定百分位数。例如，可以将平均值加上标准差的一个倍数作为基线值。
 * 5. 确定阈值：根据确定的基线值，确定一个阈值范围，通常是基线值加上或减去一个标准差的倍数，以判断当前性能数据是否异常。
 * 6. 实时分析：将当前的性能数据与基线进行比较，以检测是否存在异常。如果当前性能数据超出预期的范围，则可能会触发警报，以提醒管理员进行响应。
 * 7. 更新基线：随着时间的推移，系统的性能可能会发生变化，因此需要定期更新基线，以确保算法的准确性和有效性。
 *
 * 总的来说，统计方法建立基线的过程是基于历史性能数据进行分析，并根据数据的分布情况确定一个基础性能水平作为基线，然后通过对当前性能数据进行分析，判断是否达到了预期水平。
 **********************************/
public class CpuBaseline {

    /**
     * 计算标准差
     */
    @Test
    public void test() {
        // List<Double> doubles = Arrays.asList(5.37, 5.65, 5.9, 6.26, 6.57, 6.62, 6.66, 6.97, 6.98, 7.13, 7.2, 7.24, 7.72, 8.36, 8.53,
        //         8.93, 9.01, 9.02, 9.03, 9.18, 9.25, 9.31, 9.35);
        List<Double> doubles = Arrays.asList(9.18, 9.64, 8.53, 9.25, 9.01, 9.35, 9.48, 6.62, 6.26, 5.37, 6.97, 9.02, 7.13, 6.57, 9.03, 9.65,
                9.50, 9.91, 7.24, 5.65, 7.72, 9.73, 6.98, 9.94);
        Double reduce = doubles.stream().reduce(0.0, Double::sum);
        double avg = reduce / doubles.size();
        BigDecimal b = new BigDecimal(String.valueOf(avg));
        double round_avg = b.setScale(3, RoundingMode.HALF_UP).doubleValue();
        double sum = 0.0;
        for (Double d : doubles) {
            sum += roundDouble(Math.pow((d - round_avg), 2), 3);
        }
        double deviatioin = Math.sqrt(sum / (doubles.size() - 1));
        System.out.println(roundDouble(deviatioin, 3));
    }

    /**
     * 保留指定位数的小数
     *
     * @param val
     * @param scale
     * @return
     */
    private double roundDouble(double val, int scale) {
        BigDecimal b = new BigDecimal(String.valueOf(val));
        return b.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 计算基线值
     * 1. 获取一个ip一个月内每天相同时间的性能数据
     * 2. 性能数据按照区间分布进行数据分区
     * 3. 分区数据比对，去除噪声，获得样本数据
     * 4. 根据设置的置信度，获取准滑动窗口采纳的数据
     * 5. 根据滑动窗口算法进行汇总数据并计算每个汇总区间的标准差
     * 6. 取出标准差最小的区间集合作为当前时间的基线标准。
     */
    @Test
    public void putNumberIntoRanged() {
        // 采集一个ip 一个月内的当前时间同一时刻的cpu使用率的性能数据。（例子）
        // Double[] doubles = new Double[]{9.18, 9.64, 8.53, 9.25, 9.01, 9.35, 9.48, 6.62, 6.26, 5.37, 6.97, 9.02, 7.13, 6.57, 9.03,
        //         9.65, 9.50, 9.91, 7.24, 5.65, 7.72, 9.73, 6.98, 9.94, 8.36, 6.66, 8.93, 5.90, 9.31, 7.20};
        Double[] doubles = CPUsage.t;
        List<Double> c1 = Arrays.asList(doubles);
        List<Double> cpuDatas = new ArrayList<>(c1);
        // 获取这段时间性能数据的最大值
        Double maxCPU = cpuDatas.stream().max(Double::compareTo).get();
        // 根据样本最大值计算五个区间范围
        long v = Math.round((maxCPU / 5)) == 0 ? 1 : Math.round((maxCPU / 5));
        // 将时间段的性能数据分别分布在不同的区间[0,0+v], [0+v+0.01, 0+v+0.01+v] ...
        // Multimap<String, Double> rangeMap = ArrayListMultimap.create();
        // for (Double cpu : cpuDatas) {
        //    int start = 0, rangeName = 1;
        //    double rangeStart = 0.0;
        //    while (rangeStart < Math.ceil(maxCPU)) {
        //        if (cpu >= rangeStart + 0.01 && cpu <= (int) (rangeStart + v)) {
        //            rangeMap.put(String.valueOf(rangeName), cpu);
        //            break;
        //        }
        //        rangeStart = rangeStart + v;
        //        rangeName++;
        //    }
        //}
        List<Double> sorted = cpuDatas.stream().sorted(Double::compareTo).collect(Collectors.toList());
        List<Double[]> container = new ArrayList<Double[]>();
        List<Double[]> ranger = ranger(container, sorted, v, 1, v);
        // ===============================
        System.out.println("进行区间划分（排序前）");
        for (Double[] ds : ranger) {
            for (Double d : ds) {
                System.out.print(d + ",");
            }
            System.out.println();
        }
        // =================================================================
        // 获取样本数据，取分布不同区间数据量最多的前三个区间数据作为样本区间
        // LinkedList<Collection<Double>> collect = rangeMap.asMap().entrySet().stream().map(m -> m.getValue())
        //        .sorted((m1, m2) -> Integer.compare(m2.size(), m1.size()))
        //        .collect(Collectors.toCollection(LinkedList::new));
       /* LinkedList<Double[]> collect = ranger.stream()
                .sorted(Comparator.comparingInt(m -> m.length))
                .collect(Collectors.toCollection(LinkedList::new));
        System.out.println("区间排序");
        // ===============================
        System.out.println("进行区间排序后划分（排序后）");
        for (Double[] ds : collect) {
            for (Double d : ds) {
                System.out.print(d + ",");
            }
            System.out.println();
        }*/
        // =================================================================

        List<Double[]> useList = new ArrayList<Double[]>();
        /*
         * 计算中位数
         * 找出这组数据：23、29、20、32、23、21、33、25 的中位数。
         * 解：
         * 首先将该组数据进行排列（这里按从小到大的顺序），得到：
         * 20、21、23、23、25、29、32、33
         * 因为该组数据一共由8个数据组成，即n为偶数，故按中位数的计算方法，得到中位数，即第四个数和第五个数的平均数。
         * 例2
         * 找出这组数据：10、20、 20、 20、 30的中位数。
         * 解：
         * 首先将该组数据进行排列（这里按从小到大的顺序），得到：
         * 10、 20、 20、 20、 30
         * 因为该组数据一共由5个数据组成，即n为奇数，故按中位数的计算方法，得到中位数为20，即第3个数。
         */
        double middleNum = 0.0;
       /* if (collect.size() % 2 == 0) {
            middleNum = Math.ceil((collect.get((collect.size()-1)/2).length + collect.get((collect.size() / 2)).length) / 2.0);
        } else {
            if (collect.size() == 1) {
                middleNum = 1;
            } else {
                middleNum = Math.ceil((collect.get((collect.size() / 2 )).length) / 2.0);
            }
        }*/
        middleNum = ranger.stream().collect(Collectors.averagingDouble(d -> d.length));
        System.out.println("平均数数为："+middleNum);
        // 过滤小于平均数的集合。
        for (Double[] ds : ranger) {
            if (ds.length < middleNum) {
                useList.add(ds);
            }
        }
        // =================================================================
        System.out.println("需要删除小于中位数的样本数：");
        for (Double[] ds : useList) {
            for (Double d : ds) {
                System.out.print(d + ",");
            }
            System.out.println();
        }
        // =================================================================
        cpuDatas.removeAll(useList.stream().flatMap(Stream::of).collect(Collectors.toList()));
        /*Iterator<Double[]> iterator = collect.iterator();
        List<Double[]> useList = new ArrayList<Double[]>();
        while (iterator.hasNext()) {
            Double[] next = iterator.next();
            if (next.length == 1) {
                break;
            }
            iterator.remove();
            useList.add(next);
        }
        List<Double> collect1 = collect.stream().flatMap(Stream::of).collect(Collectors.toList());
        // 移除不需要的样本数据，在这里只需要后三个的区间的数据作为样本数据
        cpuDatas.removeAll(collect1);
        if (useList.size() != 1 && useList.size() > (int) Math.floor(ranger.size() >> 1)) {
            List<Double> collect2 = useList.stream()
                    .sorted((m1, m2) -> Integer.compare(m2.length, m1.length))
                    .skip((int) Math.floor(ranger.size() >> 1))
                    .flatMap(Stream::of).collect(Collectors.toList());
            // 移除不需要的样本数据，在这里只需要后三个的区间的数据作为样本数据
            cpuDatas.removeAll(collect2);
        }*/
        System.out.println(cpuDatas);
        // 滑动窗口，分别计算1~24  2~25 3~ 26 以此类推的标准差
        // 开始计算标准差
        // Double reduce = window.stream().reduce(0.0, Double::sum);
        // 设置置信度为0.8
        double confidence = 0.8;
        // 获取滑动窗口的数据采纳个数
        double sampleSize = cpuDatas.size() * confidence;
        // 滑动窗口的计算后的数据集合
        List<Double[]> windowsData = slidingWindows(cpuDatas.toArray(new Double[0]), (int) sampleSize);
        List<Double> deviations = new ArrayList<Double>();
        for (Double[] window : windowsData) {
            // 计算平均数
            // double avg = (Arrays.stream(window).reduce(Double::sum).orElse(0.0)) / sampleSize;
            Double avg = Arrays.stream(window).collect(Collectors.averagingDouble(Double::doubleValue));
            // 保留三位小数
            BigDecimal b = new BigDecimal(String.valueOf(avg));
            double round_avg = b.setScale(4, RoundingMode.HALF_UP).doubleValue();
            double sum = 0.0;
            for (Double d : window) {
                sum += roundDouble(Math.pow((d - round_avg), 2), 3);
            }
            // 获取每一个区间的标准差
            double deviation = Math.sqrt(sum / (window.length - 1));
            deviations.add(deviation);
        }
        // 取出最小的标准差作为当前时间的基线标准。
        Integer baseline_Index = deviations.stream().min(Double::compareTo).map(d -> deviations.indexOf(d)).get();
        Double baseline_Down = Arrays.stream(windowsData.get(baseline_Index)).min(Double::compareTo).get();
        Double baseline_Up = Arrays.stream(windowsData.get(baseline_Index)).max(Double::compareTo).get();

        System.out.println("下基线是：" + baseline_Down + "，上基线是：" + baseline_Up);
    }

    private List<Double[]> slidingWindows(Double[] nums, int k) {
        int right = 0;
        // double[] res = new double[nums.length - k + 1];
        List<Double[]> resList = new ArrayList<Double[]>();
        int index = 0;
        LinkedList<Double> list = new LinkedList<>();
        // 开始构造窗口
        while (right < nums.length) {
            // 不断添加
            list.addLast(nums[right]);
            right++;
            // 构造完成，这时候需要根据条件做一些操作
            if (right >= k) {
                // res[index++]= list.peekFirst();
                resList.add(list.toArray(new Double[0]));
                // 如果发现第一个已经在窗口外面则移除
                if (Objects.equals(list.peekFirst(), nums[right - k])) {
                    list.removeFirst();
                }
            }
        }
        return resList;
    }

    /**
     * 区间取数法
     */
    @Test
    public void testRangeArray() {
        // Double[] doubles = new Double[]{9.18, 9.64, 8.53, 9.25, 9.01, 9.35, 9.48, 6.62, 6.26, 5.37, 6.97, 9.02, 7.13, 6.57, 9.03,
        //         9.65, 9.50, 9.91, 7.24, 5.65, 7.72, 9.73, 6.98, 9.94, 8.36, 6.66, 8.93, 5.90, 9.31, 7.20, 0.12, 2.32};
        Double[] doubles = CPUsage.cpu_real;
        double maxCPU = Stream.of(doubles).max(Double::compareTo).get();
        // 根据样本最大值计算五个区间范围 2
        long v = Math.round((maxCPU / 5)) == 0 ? 1 : Math.round((maxCPU / 5));
        List<Double> sortCpus = Stream.of(doubles).sorted(Double::compareTo).collect(Collectors.toList());
        // sortCpus.forEach(System.out::println);
        System.out.println("================================================================");
        List<Double[]> container = new ArrayList<Double[]>();
        List<Double[]> ranger = ranger(container, sortCpus, v, 1, v);
        for (Double[] ds : ranger) {
            for (Double d : ds) {
                System.out.print(d + ",");
            }
            System.out.println();
        }
    }

    private List<Double[]> ranger(List<Double[]> container, List<Double> ds, long k, long start, long close) {
        Double[] d = new Double[ds.size()];
        Iterator<Double> iterator = ds.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Double next = iterator.next();
            d[i++] = next;
            iterator.remove();
            if (next >=  k) {
                //ranger(container, ds, 2 * k);
            }
        }
        Double[] doubles = Stream.of(d).filter(Objects::nonNull).toArray(Double[]::new);
        if (doubles.length > 0) {
            container.add(doubles);
        }
        return container;
    }

}
