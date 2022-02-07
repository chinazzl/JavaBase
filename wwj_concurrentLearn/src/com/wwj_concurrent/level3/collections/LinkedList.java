package com.wwj_concurrent.level3.collections;

/**********************************
 * @author zhang zhao lin
 * @date 2022年02月03日 18:10
 * @Description: 自定义简单的LinkedList
 **********************************/
public class LinkedList<E> {

    // 记录头几点
    private Node<E> first;
    private final Node<E> NULL = (Node<E>) null;
    private static final String PLAIN_NULL = "null";
    private int size;

    public LinkedList() {
        this.first = NULL;
    }

    public static <E> LinkedList<E> of(E... elements) {
        LinkedList<E> list = new LinkedList<>();
        if (elements.length != 0) {
            for (E element : elements) {
                list.addFirst(element);
            }
        }
        return list;
    }

    public boolean contains(E element) {
        Node<E> current = first;
        while (current != null) {
            if (current.value == element) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public E removeFirst() {
        if (size() == 0) {
            throw new LinkedListException("list is empty");
        }
        Node<E> node = first;
        first = node.next;
        size--;
        return node.value;
    }

    static class LinkedListException extends RuntimeException {

        public LinkedListException(String message) {
            super(message);
        }
    }

    public LinkedList<E> addFirst(E element) {
        Node<E> newNode = new Node<>(element);
        newNode.next = first;
        size++;
        return this;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (size() == 0);
    }


    private class Node<E> {
        private E value;
        Node<E> next;

        public Node(E element) {
            this.value = element;
        }

        public String toString() {
            if (value != null) {
                return value.toString();
            } else {
                return PLAIN_NULL;
            }
        }
    }
}

