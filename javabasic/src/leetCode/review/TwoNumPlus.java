package leetCode.review;

import java.util.StringJoiner;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * 标签：链表
 * 将两个链表看成是相同长度的进行遍历，如果一个链表较短则在前面补 00，比如 987 + 23 = 987 + 023 = 1010
 * 每一位计算的同时需要考虑上一位的进位问题，而当前位计算结束后同样需要更新进位值
 * 如果两个链表全部遍历完毕后，进位值为 11，则在新链表最前方添加节点 11
 * 小技巧：对于链表问题，返回结果为头结点时，通常需要先初始化一个预先指针 pre，
 * 该指针的下一个节点指向真正的头结点head。使用预先指针的目的在于链表初始化时无可用节点值，而且链表构造过程需要指针移动，进而会导致头指针丢失，无法返回结果。
 *

 */
public class TwoNumPlus {
    public static void main(String[] args) {
       ListNode node = new ListNode(2);
       node.next = new ListNode(4);
       node.next.next = new ListNode(3);
        ListNode node1 = new ListNode(5);
        node1.next = new ListNode(6);
        node1.next.next = new ListNode(4);
        System.out.println(addTwoNumbers(node, node1));

    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        int carry = 0;
        while (l1 != null || l2 != null){
            int x = l1 == null ? 0: l1.data;
            int y = l2 == null? 0:l2.data;
            int sum = x + y +carry;
            carry = sum/10;
            sum = sum%10;

            cur.next = new ListNode(sum);
            if(l1 != null){
                l1 = l1.next;
            }
            if(l2 != null){
                l2 = l2.next;
            }

        }
        if(carry == 1){
            cur.next = new ListNode( carry);
        }
        return pre.next;
    }

    public static class ListNode {
        int data;
        ListNode next;
        public ListNode(int data) {
            this.data = data;
        }

        public ListNode(){}

        @Override
        public String toString() {
            StringJoiner stringBuilder = new StringJoiner(",");
            while (this.next != null) {
                stringBuilder.add(String.valueOf(this.data));
            }
            return stringBuilder.toString();
        }
    }
}

