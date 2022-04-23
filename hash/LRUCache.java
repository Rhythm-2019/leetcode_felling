package hash;

import java.util.*;

public class LRUCache {

    private Map<Integer, Node> lruCache;
    private Node head;
    private Node tail;
    private int capacity;
    private int size;

    public LRUCache(int capacity) {
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        this.capacity = capacity;
        this.head.next = this.tail;
        this.tail.pre = this.head;
        this.lruCache = new HashMap<>();
    }
    
    public int get(int key) {
        Node node = this.lruCache.get(key);
        if (node != null) {
            this.removeNode(node);
            this.addLast(node);
            return node.val;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if (this.get(key) != -1) {
            this.lruCache.get(key).val = value;
            return;
        }
        if (this.isFull()) {
            this.removeHead();
            this.size--;
        }
        Node node = new Node(key, value);
        this.addLast(node);
        this.lruCache.put(key, node);
        this.size++;

    }

    private void removeHead() {
        if (this.isEmpty()) {
            return;
        }
        this.lruCache.remove(this.head.next.key);
        this.removeNode(this.head.next);
    }

    // 核心方法
    private void addLast(Node node) {
        Node preNode = this.tail.pre;
        preNode.next = node;
        node.pre = preNode;
        node.next = this.tail;
        this.tail.pre = node;
    }

    // 核心方法
    private void removeNode(Node node) {
        Node preNode = node.pre;
        Node next = node.next;
        
        node.next = null;
        node.pre = null;
        preNode.next = next;
        next.pre = preNode;
    }

    private boolean isFull() {
        return this.size == this.capacity;
    }
    private boolean isEmpty() {
        return this.size == 0;
    }

    class Node {
        int key;   // 删除 head 的时候要用到
        int val;   
        Node next;
        Node pre;

        Node(int key, int val) {
            this.val = val;
            this.key = key;
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */