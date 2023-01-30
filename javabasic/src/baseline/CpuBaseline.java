package baseline;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

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
            sum += roundDouble(Math.pow((d - round_avg), 2),3);
        }
        double deviatioin = Math.sqrt(sum / (doubles.size() - 1));
        System.out.println(roundDouble(deviatioin,3));
    }

    private double roundDouble(double val,int scale) {
        BigDecimal b = new BigDecimal(String.valueOf(val));
        return b.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
