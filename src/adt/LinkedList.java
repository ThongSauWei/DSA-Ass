/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt;

/**
 *
 * @author User
 */
public class LinkedList<T> {
    private Node firstNode;
    private Node lastNode;
    private int num;
    
    private class Node {
        private T entry;
        private Node nextNode;
        private Node previousNode;
        
        private Node(T entry) {
            this.entry = entry;
            this.nextNode = null;
            this.previousNode = null;
        }
        
        private Node(T entry, Node previousNode) {
            this.entry = entry;
            this.nextNode = null;
            this.previousNode = previousNode;
        }
        
        private Node(T entry, Node nextNode, Node previousNode) {
            this.entry = entry;
            this.nextNode = nextNode;
            this.previousNode = previousNode;
        }
    }
    
    public boolean add(T newEntry) {
        Node newNode = new Node(newEntry);
        
        if (num == 0) {
            firstNode = newNode;
            lastNode = newNode;
        } else {            
            lastNode.nextNode = newNode;
            newNode.previousNode = lastNode;
            lastNode = newNode;
        }
        
        num++;
        
        return true;
    }
    
    public boolean add(T newEntry, int position) {
        if (position < 0 || position >= num) {
            throw new IndexOutOfBoundsException();
        }
        
        Node newNode = new Node(newEntry);
        Node currentNode = firstNode;
        
        for (int i = 1; i < position; i++) {
            currentNode = currentNode.nextNode;
        }
        
        if (currentNode.previousNode != null) {
            newNode.previousNode = currentNode.previousNode;
            currentNode.previousNode.nextNode = newNode;
        } else {
            firstNode = newNode;
        }
        
        newNode.nextNode = currentNode;
        currentNode.previousNode = newNode;

        
        return true;
    }
    
    @Override
    public String toString() {
        Node currentNode = firstNode;
        String str = firstNode.entry + "";
        while (currentNode.nextNode != null) {
            currentNode = currentNode.nextNode;
            str += currentNode.entry;
        }
        
        return str;
    }
    
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(1,1);
        list.add(2,2);
        list.add(3,2);
        
        System.out.println(list);
        System.out.println(list.lastNode.previousNode.previousNode.entry);
    }
}

