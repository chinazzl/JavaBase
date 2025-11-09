package dataStructure.linked;

/**********************************
 * @author zhang zhao lin
 * @date 2025年11月04日 23:14
 * @Description: 链表节点类
 **********************************/
public class Node {

    private int data;
    private Node next;

    public Node(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
