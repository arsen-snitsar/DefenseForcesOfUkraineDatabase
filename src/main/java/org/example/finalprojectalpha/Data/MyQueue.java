package org.example.finalprojectalpha.Data;

public class MyQueue<T> extends MyLinkedList<T> {
    public void enqueue(T e) {
        add(e);
    }

    public T dequeue() {
        T data = get(0);
        remove(0);
        return data;
    }

    public T peek() {
        return get(0);
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }
}