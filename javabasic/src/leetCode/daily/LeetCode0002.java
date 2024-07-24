package leetCode.daily;

/**********************************
 * @author zhang zhao lin
 * @date 2023年08月23日 15:50
 * @Description: 两数相加
 **********************************/
public class LeetCode0002 {

    public static void main(String[] args) {
        Integer a = -16;
        Integer i = a >>> 2;
        System.out.println(i);
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {

        }
        ListNode(int val) {
            this.val = val;
        }

        ListNode (int val,ListNode next) {
            this.next = next;
            this.val = val;
        }
    }

}
