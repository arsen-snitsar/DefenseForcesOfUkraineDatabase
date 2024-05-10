package org.example.finalprojectalpha.Data;

public class MyLinkedList<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size = 0;

    public void add(T e) {
        Node<T> nodeToAdd = new Node<>(tail, e, null);
        if (head == null)
            head = nodeToAdd;
        else
            tail.next = nodeToAdd;
        tail = nodeToAdd;
        size++;
    }

    public void add(int index, T e) {
        Node<T> nodeToAdd = new Node<>(null, e, getNode(index - 1).next);
        if (index == 0) {
            nodeToAdd.next = head;
            head = nodeToAdd;
        } else {
            Node<T> node = getNode(index - 1);
            nodeToAdd.next = node.next;
            node.next = nodeToAdd;
        }
        size++;
    }

    private Node<T> getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node<T> node = head;
        for (int i = 0; i < index; i++)
            node = node.next;
        return node;
    }

    public T get(int index) {
        return getNode(index).data;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (index == 0) {
            head = head.next;
            if (head != null)
                head.prev = null;
        } else {
            Node<T> node = getNode(index - 1);
            node.next = node.next.next;
            if (node.next == null)
                tail = node;
            else
                node.next.prev = node;
        }
        size--;
    }

    public void remove(T e) {
        Node<T> node = head;
        for (int i = 0; i < size; i++) {
            if (node.data.equals(e)) {
                remove(i);
                break;
            }
            node = node.next;
        }
    }

    public void removeAll(T e) {
        Node<T> node = head;
        for (int i = 0; i < size; i++) {
            if (node.data.equals(e)) {
                remove(i);
                i--;
            }
            node = node.next;
        }
    }

    public void addFirst(T e) {
        add(0, e);
    }

    public int getSize() {
        return size;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> node = head;
        for (int i = 0; i < size; i++) {
            sb.append(node.data);
            if (i < size - 1)
                sb.append(", ");
            node = node.next;
        }
        sb.append("]");
        return sb.toString();
    }

    private static class Node<T> {

        T data;
        Node<T> prev;
        Node<T> next;

        public Node(Node<T> prev, T data, Node<T> next) {
            this.prev = prev;
            this.data = data;
            this.next = next;

            if (this.prev != null)
                this.prev.next = this;
            if (this.next != null)
                this.next.prev = this;
        }
    }
}