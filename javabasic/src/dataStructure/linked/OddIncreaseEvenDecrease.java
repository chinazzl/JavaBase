package dataStructure.linked;

/**********************************
 * @author zhang zhao lin
 * @date 2025年11月04日 23:14
 * @Description: 有⼀个链表，奇数位升序偶数位降序，如何将链表变成升序？
 **********************************/
public class OddIncreaseEvenDecrease {

    public static void main(String[] args) {
        // 测试用例：奇数位升序(1,3,5)，偶数位降序(8,6,4,2)
        Node head = createTestList();

        System.out.println("原始链表:");
        printList(head);

        Node sortedList = sortLinkedList(head);

        System.out.println("排序后的链表:");
        printList(sortedList);
    }

    /**
     * 创建测试链表: 1->8->3->6->5->4->7->2
     * 奇数位: 1,3,5,7 (升序)
     * 偶数位: 8,6,4,2 (降序)
     */
    public static Node createTestList() {
        Node head = new Node(1);
        head.setNext(new Node(8));
        head.getNext().setNext(new Node(3));
        head.getNext().getNext().setNext(new Node(6));
        head.getNext().getNext().getNext().setNext(new Node(5));
        head.getNext().getNext().getNext().getNext().setNext(new Node(4));
        head.getNext().getNext().getNext().getNext().getNext().setNext(new Node(7));
        head.getNext().getNext().getNext().getNext().getNext().getNext().setNext(new Node(2));
        return head;
    }

    /**
     * 主方法：将奇数位升序偶数位降序的链表排序为完全升序
     */
    public static Node sortLinkedList(Node head) {
        if (head == null || head.getNext() == null) {
            return head;
        }

        // 1. 分离奇偶位链表
        Node[] separatedLists = separateOddEvenLists(head);
        Node oddListHead = separatedLists[0];  // 奇数位链表(已经升序)
        Node evenListHead = separatedLists[1]; // 偶数位链表(降序)

        // 2. 反转偶数位链表使其变成升序
        Node reversedEvenList = reverseList(evenListHead);

        // 3. 合并两个有序链表
        return mergeTwoSortedLists(oddListHead, reversedEvenList);
    }

    /**
     * 分离奇数位和偶数位链表
     */
    public static Node[] separateOddEvenLists(Node head) {
        if (head == null) {
            return new Node[]{null, null};
        }

        Node oddListHead = null;
        Node evenListHead = null;
        Node oddListTail = null;
        Node evenListTail = null;

        Node current = head;
        int position = 1;

        while (current != null) {
            Node nextNode = current.getNext();
            current.setNext(null); // 断开当前节点的next指针

            if (position % 2 == 1) {
                // 奇数位
                if (oddListHead == null) {
                    oddListHead = current;
                    oddListTail = current;
                } else {
                    oddListTail.setNext(current);
                    oddListTail = current;
                }
            } else {
                // 偶数位
                if (evenListHead == null) {
                    evenListHead = current;
                    evenListTail = current;
                } else {
                    evenListTail.setNext(current);
                    evenListTail = current;
                }
            }

            current = nextNode;
            position++;
        }

        return new Node[]{oddListHead, evenListHead};
    }

    /**
     * 反转链表
     */
    public static Node reverseList(Node head) {
        if (head == null || head.getNext() == null) {
            return head;
        }

        Node prev = null;
        Node current = head;

        while (current != null) {
            Node nextNode = current.getNext();
            current.setNext(prev);
            prev = current;
            current = nextNode;
        }

        return prev;
    }

    /**
     * 合并两个有序链表
     */
    public static Node mergeTwoSortedLists(Node list1, Node list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        Node dummyHead = new Node(0); // 虚拟头节点
        Node tail = dummyHead;

        while (list1 != null && list2 != null) {
            if (list1.getData() <= list2.getData()) {
                tail.setNext(list1);
                list1 = list1.getNext();
            } else {
                tail.setNext(list2);
                list2 = list2.getNext();
            }
            tail = tail.getNext();
        }

        // 连接剩余部分
        tail.setNext(list1 != null ? list1 : list2);

        return dummyHead.getNext();
    }

    /**
     * 打印链表
     */
    public static void printList(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.getData());
            if (current.getNext() != null) {
                System.out.print(" -> ");
            }
            current = current.getNext();
        }
        System.out.println();
    }
}
