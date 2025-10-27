package leetCode.datastructure.linked;

/**
 * @author: zhaolin
 * @Date: 2025/9/27
 * @Description: 链表学习
 **/
public class LinedListUsage {

    public static void main(String[] args) {
        LinkedNode linkedNode = initLinkedList();
        // 链表的反转——迭代法
//        LinkedNode pre = null;
//        LinkedNode cur = linkedNode;
//        while (cur != null) {
//            LinkedNode next = cur.next;
//            cur.next = pre;
//            pre = cur;
//            cur = next;
//        }
//        System.out.println(pre);
        LinkedNode dfs = dfs(linkedNode);
        System.out.println(dfs);
    }

    private static LinkedNode dfs(LinkedNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        LinkedNode res = dfs(head.next);
        head.next.next = head;
        head.next = null;
        return res;
    }

    private static LinkedNode initLinkedList() {
        LinkedNode head = new LinkedNode(1);
        head.next = new LinkedNode(2);
        head.next.next = new LinkedNode(3);
        head.next.next.next = new LinkedNode(4);
        return head;
    }
}
