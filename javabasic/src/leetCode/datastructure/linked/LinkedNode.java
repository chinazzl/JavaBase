package leetCode.datastructure.linked;

/**
 * @author: zhaolin
 * @Date: 2025/9/27
 * @Description:
 **/
public class LinkedNode {

    public int val;
    public LinkedNode next;

    public LinkedNode(int val) {
        this.val = val;
    }

    public LinkedNode(int val, LinkedNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return "LinkedNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
