package sortAlgorithmic.lianBiaoLinkedList;

/**
 * 简单的链表结构
 */
public class SingleLinkedList {
    //链表节点的个数
    private int size;
    //头节点
    private Node head;

    public SingleLinkedList() {
        size = 0;
        head = null;
    }

    private class Node {
        private Object data; //每个节点的数据
        private Node next;//每个节点指向下一个节点的连接

        public Node(Object data) {
            this.data = data;
        }


    }

    //在链表头添加元素
    public Boolean addHead(Object obj) {
        Node newHead = new Node(obj);
        if (size == 0) {
            head = newHead;
        } else {
            newHead.next = head;
            head = newHead;
        }
        size++;
        return true;
    }

    //在链表头删除元素
    public Object deleteHead() {
        Object obj = head.data;
        head = head.next;
        size--;
        return obj;
    }

    //查找指定元素，找到返回节点Node，找不到返回null
    public Object get(Object obj) {
        Node current = head;
        int tempSize = size;
        while (tempSize > 0) {
            if (obj.equals(current.data)) {
                return current.next.data;
            } else {
                current = current.next;
            }
            tempSize--;
        }
        return null;
    }

    public Node find(Object obj) {
        Node current = head;
        int tempSize = size;
        while (tempSize > 0) {
            if (obj.equals(current.data)) {
                return current;
            } else {
                current = current.next;
            }
            tempSize--;
        }
        return null;
    }


    //删除指定元素
    public Boolean delete(Object value) {
        if (size == 0) {
            return false;
        }
        Node current = head;
        Node previous = head;
        while (current.data != value) {
            if (current.next == null) {
                return false;
            } else {
                previous = current;
                current = current.next;
            }
        }
        //如果删除第一个节点
        if(current == head){
            head = current.next;
            size--;
        }else { //删除的节点不是第一个节点
            previous.next = current.next;
            size--;
        }
        return true;
    }

}
