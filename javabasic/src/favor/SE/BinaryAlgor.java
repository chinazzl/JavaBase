package favor.SE;

/**
 * 二分法摘抄自 Arrays.binarySearch
 */
public class BinaryAlgor {

    public static void main(String[] args) {
        long[] arr = {1,2,3,4};
        int i = binarySearch0(arr, 0, 3, 123);
        System.out.println(i);
    }

    private static int binarySearch0(long[] a, int fromIndex, int toIndex,
                                     long key) {
        int low = fromIndex;
        int high = toIndex - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            long midVal = a[mid];

            if (midVal < key)
                low = mid + 1;
            else if (midVal > key)
                high = mid - 1;
            else
                return mid; // key found
        }
        return -(low + 1);  // key not found.
    }
}
