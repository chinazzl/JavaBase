package baseline;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**********************************
 * @author zhang zhao lin
 * @date 2023年01月30日 22:38
 * @Description:
 **********************************/
public class CpuBaseline {

    public static void main(String[] args) {
        Double[] doubles = new Double[]{9.18, 9.64, 8.53, 9.25, 9.01, 9.35, 9.48, 6.62, 6.26, 5.37, 6.97, 9.02, 7.13, 6.57, 9.03,
                9.65, 9.50, 9.91, 7.24, 5.65, 7.72, 9.73, 6.98, 9.94, 8.36, 6.66, 8.93, 5.90, 9.31, 7.20};
        Arrays.sort(doubles);
        StringJoiner stringJoiner = new StringJoiner(",", "[", "]");
        for (Double d : doubles) {
            stringJoiner.add(d.toString());
        }
        System.out.println(stringJoiner);
    }

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
     */
    @Test
    public void putNumberIntoRanged() {
        // 采集一个ip 一个月内的当前时间同一时刻的cpu使用率的性能数据。
        Double[] doubles = new Double[]{9.18, 9.64, 8.53, 9.25, 9.01, 9.35, 9.48, 6.62, 6.26, 5.37, 6.97, 9.02, 7.13, 6.57, 9.03,
                9.65, 9.50, 9.91, 7.24, 5.65, 7.72, 9.73, 6.98, 9.94, 8.36, 6.66, 8.93, 5.90, 9.31, 7.20, 0.12, 2.32};
        List<Double> c1 = Arrays.asList(doubles);
        List<Double> cpuDatas = new ArrayList<>(c1);
        // 获取这段时间性能数据的最大值
        Double maxCPU = cpuDatas.stream().max(Double::compareTo).get();
        // 根据样本最大值计算五个区间范围
        long v = Math.round((maxCPU / 5));
        // 将时间段的性能数据分别分布在不同的区间[0,0+v], [0+v+0.01, 0+v+0.01+v] ...
        Multimap<String, Double> rangeMap = ArrayListMultimap.create();
        for (Double cpu : cpuDatas) {
            int start = 0, rangeName = 1;
            double rangeStart = 0.0;
            while (rangeStart < Math.ceil(maxCPU)) {
                if (cpu >= rangeStart && cpu <= (int) (rangeStart + v)) {
                    rangeMap.put(String.valueOf(rangeName), cpu);
                    break;
                }
                rangeStart = rangeStart + v + 0.01;
                rangeName++;
            }
        }
        System.out.println(rangeMap);
        // 获取样本数据，取分布不同区间数据量最多的前三个区间数据作为样本区间
        List<Double> collect = rangeMap.asMap().entrySet().stream().map(m -> m.getValue())
                .sorted(Comparator.comparingInt(Collection::size))
                .limit(2).flatMap(c -> c.stream()).collect(Collectors.toList());
        // 移除不需要的样本数据，在这里只需要后三个的区间的数据作为样本数据
        cpuDatas.removeAll(collect);
        // 滑动窗口，分别计算1~24  2~25 3~ 26 以此类推的标准差
        List<Double> window = cpuDatas.stream().limit(24).collect(Collectors.toList());
        // 开始计算标准差 TODO 需要滑动窗口计算
        Double reduce = window.stream().reduce(0.0, Double::sum);
        // 计算平均数
        double avg = reduce / window.size();
        // 保留三位小数
        BigDecimal b = new BigDecimal(String.valueOf(avg));
        double round_avg = b.setScale(3, RoundingMode.HALF_UP).doubleValue();
        double sum = 0.0;
        for (Double d : window) {
            sum += roundDouble(Math.pow((d - round_avg), 2), 3);
        }
        double deviatioin = Math.sqrt(sum / (window.size() - 1));
        System.out.println(roundDouble(deviatioin, 3));
    }

    /**
     * =================================================================
     * 测试
     */
    @Test
    public void testNum() {
        System.out.println(Math.round(9.12));
        System.out.println(String.format("%.2d", 9.12));
        System.out.println(String.format("%.2d", 9.52));
    }
}
