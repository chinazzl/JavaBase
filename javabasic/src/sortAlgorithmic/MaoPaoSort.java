package sortAlgorithmic;

/**
 * 冒泡排序
 * 冒泡排序是最简单的排序之一了，其大体思想就是通过与相邻元素的比较和交换来把小的数交换到最前面。
 * 这个过程类似于水泡向上升一样，因此而得名。
 * 举个栗子，对5,3,8,6,4这个无序序列进行冒泡排序。首先从后向前冒泡，4和6比较，把4交换到前面，
 * 序列变成5,3,8,4,6。同理4和8交换，变成5,3,4,8,6,3和4无需交换。
 * 5和3交换，变成3,5,4,8,6,3.这样一次冒泡就完了，把最小的数3排到最前面了。
 * 对剩下的序列依次冒泡就会得到一个有序序列。冒泡排序的时间复杂度为O(n^2)。
 */
public class MaoPaoSort {
    public static void main(String[] args) {
        int[] i = {5,2,4,3,1};
        int[] ints = maoPaoSort(i);
        for (int j = 0; j < ints.length; j++) {
            System.out.println(ints[j]);
        }
    }
    
    public static int[] maoPaoSort(int[] is){
        int len = is.length;
        for (int i = 0; i < len-1; i++) {
            for (int j = len - 1; j > i; j--) {
                if(is[j-1] < is[j]){
                    swap(is,j,j-1);
                }
            }
        }
        return is;
    }

    /*
        进行数字交换
        @param int[],int i,int j
     */
    private static void swap(int[] is ,int i, int j){
        int temp = is[j];
        is[j] = is[i];
        is[i] = temp;
    }
}
