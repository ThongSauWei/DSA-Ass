/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt;

/**
 *
 * @author Benjamin
 */
public class LinkedList<T extends Comparable<T>> implements ListInterface<T> {
    private Node firstNode;
    private Node lastNode;
    private int num; // Number of entries (nodes)
    
    public LinkedList() {
        clear();
    }
    
    // Node class implementation
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
    
    @Override
    public boolean add(T newEntry) {
        Node newNode = new Node(newEntry);
        
        if (getSize() == 0) { // If it is an empty list, the first and last node will be the new entry
            firstNode = newNode;
            lastNode = newNode;
        } else { // else the entry is added to tail of the list
            lastNode.nextNode = newNode; // the old last node has a node behind it, which is the new entry
            newNode.previousNode = lastNode; // the new entry's previous node is the old last node
            lastNode = newNode; // the new entry become the last node now
        }
        
        num++;
        
        return true;
    }
    
    @Override
    public boolean add(T newEntry, int position) {
        Node currentNode = getNode(position);
        
        Node newNode = new Node(newEntry);
        
        if (currentNode.previousNode != null) { // if it is not added to the first node of the list
            newNode.previousNode = currentNode.previousNode; // previous node <- new node
            currentNode.previousNode.nextNode = newNode; // previous node -> new node
        } else { // else it is added to the first node
            firstNode = newNode;
        }
        
        newNode.nextNode = currentNode; // new node -> next node
        currentNode.previousNode = newNode; // new node <- next node

        num++;
        return true;
    }
    
    @Override
    public T remove(int position) {       
        Node currentNode = getNode(position);
        
        Node previous = currentNode.previousNode;
        Node next = currentNode.nextNode;
        
        if (currentNode.previousNode != null) { // if it is not the first node, the previous node point to the next node
            currentNode.previousNode.nextNode = next;
        } else { // else the first node is removed and the new first node is pointed
            firstNode = currentNode.nextNode;
        }
        
        if (currentNode.nextNode != null) { // if it is not the last node, the next node point to the previous node
            currentNode.nextNode.previousNode = previous;
        } else { // else the last node is removed and the new last node is pointed
            lastNode = currentNode.previousNode;
        }
        
        num--;
        
        return (T) currentNode.entry;
    }
    
    @Override
    public T get(int position) {
        return getNode(position).entry;
    }
    
    @Override
    public boolean replace(T newEntry, int position) {
        Node currentNode = getNode(position);
        
        // replace the entry
        currentNode.entry = newEntry;
        
        return true;
    }
    
    @Override
    public void sort() {
        quickSort(firstNode, lastNode); // use quick sort for sorting
    }
    
    @Override
    public int getSize() {
        return num;
    }
    
    @Override
    public void clear() {
        firstNode = null;
        lastNode = null;
        num = 0;
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
        
        // traverse from the first node to the position
        for (int i = 1; i < position; i++) {
            currentNode = currentNode.nextNode;
        }
        
        return currentNode;
    }
    
    private void swap(Node i, Node j) {
        // swapping the entries of two nodes
        T tempEntry = i.entry;
        i.entry = j.entry;
        j.entry = tempEntry;
    }
    
    private boolean compareNode(Node first, Node second) {
        if (first == null || second == null) { // if either node is null, means it is the first/last node, does not need to recursive anymore
            return false;
        }
        
        Node currentNode = firstNode;
        
        // traverse through the whole list
        while (currentNode.nextNode != null) {
            if (currentNode == first) { // need to encounter first node first, but first != second
                return currentNode != second;
            } else if (currentNode == second) { // return false if encounter second first (second node is at the front of the list)
                return false;
            }
            
            currentNode = currentNode.nextNode;
        }
        
        return false;
    }
    
    private void quickSort(Node first, Node last) {
        if (compareNode(first, last)) { // first and last != null and first must be in front of last in the list (first < second in the list)
            // get the node that is sorted
            Node sorted = partition(first, last);
            
            // sort both side from the sorted node
            quickSort(first, sorted.previousNode);
            quickSort(sorted.nextNode, last);           
        }
    }
    
    private Node partition(Node first, Node last) {
        T pivot = last.entry; // select the last entry as the pivot
        
        Node i = first; // node that will be swapped
        
        for (Node j = first; j != last; j = j.nextNode) { // traverse through the sub-list to determine whether to swap or not
            if (j.entry.compareTo(pivot) < 0) { // compare both pivot and the node traversing, if the node traversing is "less than" pivot, swap it with the node "i"
                swap(i, j);
                i = i.nextNode; // go to the next node (the node will then be swapped)
            }
        }
        // now on the left of node "i" will have all the node whose entries are "less than" the pivot, now swap the pivot node with the node "i"
        swap(i, last);
        // after swapping we knew that the pivot node is sorted as all the node on its left are "less than" it while all the node on its right are "more than" it
        // return the node that is sorted
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

