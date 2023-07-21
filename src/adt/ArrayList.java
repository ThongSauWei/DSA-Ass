/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt;

import java.io.Serializable;
import utility.ExceptionHandling;
/**
 *
 * @author Benjamin
 */
public class ArrayList<T extends Comparable<T>> implements Serializable, ListInterface<T> {
    
    private T[] arr;
    private int num;
    private static final int DEFAULT_SIZE = 5;
    
    public ArrayList() {
        arr = (T[]) new Comparable[DEFAULT_SIZE];
        num = 0;
    }
    
    // Custom iterator class implementation
    public class CustomIterator {
        private int currentPosition;
        
        public CustomIterator() {
            this(1);
        }
        
        public CustomIterator(int position) {
            currentPosition = position;
        }
        
        public boolean hasNext() {
            return currentPosition < getSize();
        }
        
        public boolean hasPrevious() {
            return currentPosition > 1;
        }
        
        public void next() {
            if (hasNext()) {
                currentPosition++;
            } else {
                ExceptionHandling.endOfList();
            }
        }
        
        public void previous() {
            if (hasPrevious()) {              
                currentPosition--;
            } else {
                ExceptionHandling.endOfList();
            }
        }
        
        public T getCurrent() {
            return arr[currentPosition - 1];
        }
    }
    
    public CustomIterator placeIterator() {
        return new CustomIterator(1);
    }
    
    public CustomIterator placeIterator(int position) {
        return new CustomIterator(position);
    }
    
    @Override
    public boolean add(T newEntry) {      
        if (isFull()) { // if the array is full, expand the array for new entries
            expand();
        }
        
        arr[num] = newEntry;
        num++;
        
        return true;
    }
    
    @Override
    public boolean add(T newEntry, int position) {        
        if (position < 1 || position > getSize()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        
        if (isFull()) { // if the array is full, expand the array for new entries
            expand();
        }
        
        for (int i = getSize(); i > position - 1; i--) { // moving the entries after the position one index to the right (back of the array)
            arr[i] = arr[i - 1];
        }
        
        arr[position - 1] = newEntry;
        num++;
        
        return true;
    }
    
    @Override
    public T remove(int position) {
        T removal = get(position);
        
        fillGap(position);
        num--;
        
        return removal;
    }
    
    @Override
    public T get(int position) {
        if (position < 1 || position > getSize()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        
        return arr[position - 1];
    }
    
    @Override
    public boolean replace(T newEntry, int position) {
        arr[position - 1] = newEntry;
        
        return true;
    }
    
    @Override
    public void sort() {
        quickSort(0, getSize() - 1); // use quick sort for sorting
    }
    
    @Override
    public int getSize() {
        return num;
    }
    
    @Override
    public void clear() {
        for (int i = 0; i < num; i++) {
            arr[i] = null;
        }
        
        num = 0;
    }
    
    @Override
    public boolean isEmpty() {
        return num == 0;
    }
    
    @Override
    public String toString() {
        String str = "";
        
        for (int i = 0; i < getSize(); i++) {
            str += arr[i] + " ";
        }
        
        return str;
    }
    
    // utility methods
    private void expand() {        
        T[] oldArr = arr;
        arr = (T[]) new Comparable[oldArr.length * 2]; // create a new array which has double the size than the old array
        
        System.arraycopy(oldArr, 0, arr, 0, oldArr.length); // copy the entries in the old array to the new array
    }
    
    private void fillGap(int position) {
        for (int i = position - 1; i < getSize(); i++) { // moving the entries after the position one index to the left (front of the array)
            arr[i] = arr[i + 1];
        }
        
        arr[num - 1] = null;
    }
    
    private void swap(int i, int j) {
        // swapping the entries between two indexes
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    private void quickSort(int first, int last) {
        if (first < last) { // the sub-array must be exist / must not be empty array (e.g. from index 1 to index 1)
            // get the node that is sorted
            int sorted = partition(first, last);
            
            // sort both side from the sorted node
            quickSort(first, sorted - 1);
            quickSort(sorted + 1, last);
        }
    }
    
    private int partition(int first, int last) {
        T pivot = arr[last]; // select the last entry as the pivot
        
        int i = first; // index that will be swapped
        
        for (int j = first; j <= last; j++) { // traverse through the sub-array to determine whether to swap or not
            if (arr[j].compareTo(pivot) < 0) { // compare both pivot and the element traversed, if the element traversing is "less than" pivot, swap it with the element "i"
                swap(i, j);
                i++; // go to the next index (the element in the index will then be swapped)
            }
        }
        // now on the left of index "i" will have all the elements that are "less than" the pivot, now swap the pivot index with the index "i"
        swap(i,last);
        // after swapping we knew that the pivot index is sorted as all the elements on its left are "less than" it while all the elements on its right are "more than" it
        // return the index that is sorted
        return i;
    }
    
    private boolean isFull() {
        return arr.length == num;
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        
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
