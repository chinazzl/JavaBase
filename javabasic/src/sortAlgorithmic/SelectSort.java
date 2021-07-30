package sortAlgorithmic;

/**
 * 选择排序
 * 选择排序的思想其实和冒泡排序有点类似，都是在一次排序后把最小的元素放到最前面。
 * 但是过程不同，冒泡排序是通过相邻的比较和交换。
 * 而选择排序是通过对整体的选择。
 * 举个栗子，对5,3,8,6,4这个无序序列进行简单选择排序，
 * 首先要选择5以外的最小数来和5交换，也就是选择3和5交换，一次排序后就变成了3,5,8,6,4.对剩下的序列一次进行选择和交换，
 * 最终就会得到一个有序序列。其实选择排序可以看成冒泡排序的优化，
 * 因为其目的相同，只是选择排序只有在确定了最小数的前提下才进行交换，大大减少了交换的次数。选择排序的时间复杂度为O(n^2)
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] is = {2,5,1,7,4,3};
        int[] sort = sort(is);
        for (int i = 0; i < sort.length; i++) {
            System.out.printf(sort[i]+",");
        }
    }

    private static int[] sort(int[] arr){
        int len = arr.length;
        int minIndex ;
        for (int i = 0; i < len -1 ; i++) {
            minIndex = i;
            for (int j = i +1; j < len; j++) {
                if (arr[j] < arr[minIndex]){
                    minIndex = j;
                }
            }

            if(minIndex != i){
                swap(arr,i,minIndex);
            }
        }
        return arr;
    }

    private static void swap(int[] arr, int i, int minIndex) {
        int temp = arr[minIndex];
        arr[minIndex] = arr[i];
        arr[i] = temp;
    }

}
