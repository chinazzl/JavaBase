package sortAlgorithmic;


/**
 * 快速排序
 *
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {5,2,3,1,6};
        int[] sorts = sort(arr);
        for (int i = 0; i < sorts.length; i++) {
            System.out.print(sorts[i]);
        }
    }
    public static int[] sort(int[] arr){
        if(arr == null || arr.length ==0){
            throw new NullPointerException();
        }
        //第一个为基准数
        quickSort(arr,0,arr.length-1);
        return arr;
    }

    private static void quickSort(int[] arr, int left, int right) {
        if(left >= right){
            return;
        }
        int pivotPos = partition(arr,left,right);
        quickSort(arr,left,pivotPos-1);
        quickSort(arr,pivotPos+1,right);
    }

    //一次划分
    private static int partition(int[] arr, int left, int right) {
        int pivotKey = arr[left];
        int povootPointer = left;

        while (left < right){ //int[] arr = {5,2,3,1,6};
            while (left < right && arr[right] > pivotKey){
                right--;
            }
            while (left < right && arr[left] <= pivotKey){
                left++;
            }
            //把最大的交换到右边，把最小的交换到左边
            swap(arr,left,right);
        }
        swap(arr,povootPointer,left);
        return left;
    }

    private static void swap(int[] arr, int pivotKey, int left) {
        int temp = arr[left];
        arr[left] = arr[pivotKey];
        arr[pivotKey] = temp;
    }
}
