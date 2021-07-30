package SE8.StreamPac.streamTest.numbericstream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/4/11 13:14
 * @Modified By：
 * 学习 Numeric Test
 */
public class TrasactionTest {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        System.out.println("1. " + findBefor2011Trader(transactions));
        System.out.println("2." + findCityFromTrader(transactions));
        System.out.println("3." + findAllTraderFromCambridge(transactions));
        System.out.println(findAllTraderNameOrderByASCII(transactions));
        System.out.println("5." + findTraderFromMilan(transactions));
        System.out.println("6." + findAllValueFromCambridge(transactions));
        System.out.println("7." + findMaxValueOfTransaction(transactions));
        System.out.println("8. " + findMinValueOfTransaction(transactions));

    }

    /**
     * 找出2011年发生的所有交易，并按照交易额排序
     */
    public static List<Integer> findBefor2011Trader(List<Transaction> transactions) {
        List<Integer> list = transactions.stream().filter(transaction -> transaction.getYear() == 2011)
                .map(Transaction::getValue)
                .sorted(Comparator.comparing(t -> t.intValue()))
                .collect(Collectors.toList());
        return list;

    }

    /**
     * 交易员都在那些不同的城市工作过
     */
    public static List<String> findCityFromTrader(List<Transaction> transactions) {
        return transactions.stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * 3. 查找所有来自于剑桥的交易员，并按照姓名排序
     */
    public static List<Trader> findAllTraderFromCambridge(List<Transaction> transactions) {
        return transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(t -> t.getName()))
                .collect(Collectors.toList());
    }

    /**
     * 返回所有交易员的姓名字符串，按字母顺序排序
     */
    public static String findAllTraderNameOrderByASCII(List<Transaction> transactions) {
        return transactions.stream()
                .map(Transaction::getTrader)
                .map(t -> t.getName())
                .sorted()
                .reduce("4.", (name1, name2) -> name1 + name2);
    }

    /**
     * 有没有交易员是在米兰工作的
     */
    public static boolean findTraderFromMilan(List<Transaction> transactions) {
        return transactions.stream()
                .map(transaction -> transaction.getTrader())
                .anyMatch(t -> t.getCity().equals("Milan"));
    }

    /**
     * 打印生活在剑桥的交易员的所有交易额
     */
    public static int findAllValueFromCambridge(List<Transaction> transactions) {
        return transactions.stream()
                .map(transaction -> transaction.getValue())
                .reduce(0, (i1, i2) -> i1 + i2);
    }

    /**
     * 所有交易中最高的交易额是多少
     */
    public static int findMaxValueOfTransaction(List<Transaction> transactions) {
        return transactions.stream()
                .map(transaction -> transaction.getValue())
                .reduce(0, (i1, i2) -> i1 > i2 ? i1 : i2);
    }

    /**
     * 找到交易额最小的交易
     */
    public static int findMinValueOfTransaction(List<Transaction> transactions) {
        // 这种方式比较常规
        /*return transactions.stream()
                .map(transaction -> transaction.getValue())
                .reduce((i1, i2) -> i1 > i2 ? i2 : i1).get();*/
        //使用stream 的min
        return transactions.stream()
                .min(Comparator.comparing(Transaction::getValue)).get().getValue();
    }
}
