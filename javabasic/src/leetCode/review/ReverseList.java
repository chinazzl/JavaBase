package leetCode.review;

/**********************************
 * @author zhang zhao lin
 * @date 2024年02月07日 14:06
 * @Description: 反转链表
 **********************************/
public class ReverseList {
    public static void main(String[] args) {
        ListNode node = new ListNode(3);
        node = new ListNode(2,node);
        node = new ListNode(1,node);
        ListNode listNode = reverseList(node);
        while (listNode != null) {
            System.out.printf("%s ->",listNode.data);
            listNode = listNode.next;
        }
    }

    /**
     * 反转列表，使用双指针迭代法。
     * 1 -> 2 -> 3 -> *
     * * <- 1 <-2 <- 3
     * @param head
     * @return
     */
    private static ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    /**
     * 使用递归遍历链表，当越过尾节点后终止递归，回溯时修改各个节点的next的引用指向
     * @param head
     * @return
     */
    private static ListNode recurList(ListNode head) {
        ListNode pre = null;
        ListNode cur  = head;
        if (cur.next != null) {
            pre = cur;
            return pre;
        }
        return null;
    }

    private static class ListNode {
        int data;
        ListNode next;

        public ListNode(int data) {
            this.data = data;
        }

        public ListNode(int data, ListNode next) {
            this.data = data;
            this.next = next;
        }
    }
}
