/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt;

/**
 *
 * @author User
 */
public class LinkedList<T extends Comparable<T>> {
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
        
        if (getSize() == 0) {
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
        Node currentNode = getNode(position);
        
        Node newNode = new Node(newEntry);
        
        if (currentNode.previousNode != null) {
            newNode.previousNode = currentNode.previousNode;
            currentNode.previousNode.nextNode = newNode;
        } else {
            firstNode = newNode;
        }
        
        newNode.nextNode = currentNode;
        currentNode.previousNode = newNode;

        num++;
        return true;
    }
    
    public T remove(int position) {       
        Node currentNode = getNode(position);
        
        // changing the previous and next node
        Node previous = currentNode.previousNode;
        Node next = currentNode.nextNode;
        
        if (currentNode.previousNode != null) {
            currentNode.previousNode.nextNode = next;
        } else {
            firstNode = currentNode.nextNode;
        }
        
        if (currentNode.nextNode != null) {
            currentNode.nextNode.previousNode = previous;
        } else {
            lastNode = currentNode.previousNode;
        }
        
        num--;
        
        return (T) currentNode.entry;
    }
    
    public T get(int position) {
        return getNode(position).entry;
    }
    
    public boolean replace(T newEntry, int position) {
        Node currentNode = getNode(position);
        
        Node newNode = new Node(newEntry);
        
        if (currentNode.previousNode != null) {
            currentNode.previousNode.nextNode = newNode;
        } else {
            firstNode = newNode;
        }
        
        if (currentNode.nextNode != null) {
            currentNode.nextNode.previousNode = newNode;
        } else {
            lastNode = newNode;
        }
        
        newNode.nextNode = currentNode.nextNode;
        newNode.previousNode = currentNode.previousNode;
        
        return true;
    }
    
    public void sort() {
        quickSort(firstNode, lastNode);
    }
    
    public int getSize() {
        return num;
    }
    
    @Override
    public String toString() {
        Node currentNode = firstNode;
        String str = firstNode.entry + " ";
        while (currentNode.nextNode != null) {
            currentNode = currentNode.nextNode;
            str += currentNode.entry + " ";
        }
        
        return str;
    }
    
    // utility method
    private Node getNode(int position) {
        if (position < 1 || position > getSize()) {
            throw new IndexOutOfBoundsException();
        }
        
        Node currentNode = firstNode;
        
        for (int i = 1; i < position; i++) {
            currentNode = currentNode.nextNode;
        }
        
        return currentNode;
    }
    
    private void swap(Node i, Node j) {
        T tempEntry = i.entry;
        i.entry = j.entry;
        j.entry = tempEntry;
    }
    
    private boolean compareNode(Node first, Node second) {
        if (first == null || second == null) {
            return false;
        }
        
        Node currentNode = firstNode;
        
        while (currentNode.nextNode != null) {
            if (currentNode == first) {
                return currentNode != second;
            } else if (currentNode == second) {
                return false;
            }
            
            currentNode = currentNode.nextNode;
        }
        
        return false;
    }
    
    private void quickSort(Node first, Node last) {
        if (compareNode(first, last)) { 
            Node sorted = partition(first, last);
            
            quickSort(first, sorted.previousNode);
            quickSort(sorted.nextNode, last);           
        }
    }
    
    private Node partition(Node first, Node last) {
        T pivot = last.entry;
        
        Node i = first;
        for (Node j = first; j != last; j = j.nextNode) {
            if (j.entry.compareTo(pivot) < 0) {
                swap(i, j);
                i = i.nextNode;
            }
        }
        swap(i, last);
        return i;
    }
    
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(-7);
        list.add(24);
        list.add(6,1);
        list.add(9,3);
        list.add(13,2);

        System.out.println(list);
        
        System.out.println(list.getSize());
        
        System.out.println(list.remove(3));
        System.out.println(list.get(3));
        list.replace(4, 4);
        
        System.out.println(list);
        
        System.out.println(list.getSize());
        
        list.sort();
        
        System.out.println(list);
    }
}

