package guavaUtil;

import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Range;
import com.google.common.primitives.Ints;

/**
 * @author Julyan
 * @version V1.0
 * @Date: 2022/11/23 21:44
 * @Description: Range 表示一个间隔或一个序列。它被用于获取一组数字/串在一个特定范围之内。
 */
public class GuavaRange {

    public static void main(String[] args) {
        testRange();
    }

    private static void testRange() {
        // create a range [a,b] = {x | a <= x <= b}
        Range<Integer> range = Range.closed(0, 9);
        System.out.print("[0.9]: ");
        printRange(range);
        System.out.println(" ");
        System.out.println("5 is present: " + range.contains(5));
        System.out.println("(1,2,3) is present: " + range.containsAll(Ints.asList(1, 2, 3)));
        System.out.println("Lower Bound: " + range.lowerEndpoint());
        System.out.println("High Bound: " + range.upperEndpoint());
        // create a range [a,b] = {x | a < x < b}
        Range<Integer> open = Range.open(0, 9);
        System.out.print("(0,9): ");
        printRange(open);
        System.out.println(" ");
        // create a range [a,b] = {x | a < x <= b}
        Range<Integer> openClosed = Range.openClosed(0, 9);
        System.out.print("(0,9]: ");
        printRange(openClosed);
        System.out.println(" ");
        // create an open ended rang (9,infinity)
        Range<Integer> greaterThan = Range.greaterThan(9);
        System.out.println("(9,infinity): ");
        System.out.println("Lower Bound: " + greaterThan.lowerEndpoint());
        System.out.println("High Bound: " + greaterThan.hasUpperBound());

        // check a subrange [3,5] in [0,9]
        Range<Integer> closed = Range.closed(3, 5);
        System.out.println("subrange [3,5] in [0,9]: " + range.isConnected(closed));

        // intersection
        Range<Integer> range1 = Range.closed(10, 15);
        // printRange(range.intersection(range1));
        System.out.println(" ");
        // span
        printRange(range.span(range1));
    }

    private static void printRange(Range<Integer> range) {
        System.out.print("[");
        for (Integer grade : ContiguousSet.create(range, DiscreteDomain.integers())) {
            System.out.print(grade + " ");
        }
        System.out.print("]");

    }
}
