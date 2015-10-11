package com.myapp.calculator;

/**
 * Android Calculator App
 */

public class Node<T> {
    private Thunk<T> head;
    private Node<T> tail;

    public Node(Thunk<T> head, Node<T> tail) {
        this.head = head;
        this.tail = tail;
    }

    public Thunk<T> getHead() {
        return head;
    }

    public Node<T> getTail() {
        return tail;
    }
}
