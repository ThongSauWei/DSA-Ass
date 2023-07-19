/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt;

import java.io.Serializable;
/**
 *
 * @author User
 */
public class ArrayList<T extends Comparable<T>> implements Serializable {
    
    private T[] arr;
    private int num;
    private static final int DEFAULT_SIZE = 5;
    
    public ArrayList() {
        arr = (T[]) new Comparable[DEFAULT_SIZE];
        num = 0;
    }
    
    public boolean add(T newEntry) {      
        if (isFull()) {
            expand();
        }
        
        arr[num] = newEntry;
        num++;
        
        return true;
    }
    
    public boolean add(T newEntry, int position) {        
        if (position < 1 || position > getSize()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        
        if (isFull()) {
            expand();
        }
        
        for (int i = getSize(); i > position - 1; i--) {
            arr[i] = arr[i - 1];
        }
        
        arr[position - 1] = newEntry;
        num++;
        
        return true;
    }
    
    public T remove(int position) {
        T removal = get(position);
        
        fillGap(position);
        num--;
        
        return removal;
    }
    
    public T get(int position) {
        if (position < 1 || position > getSize()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        
        return arr[position - 1];
    }
    
    public boolean replace(T newEntry, int position) {
        arr[position - 1] = newEntry;
        
        return true;
    }
    
    public void sort() {
        quickSort(0, getSize() - 1);
    }
    
    public int getSize() {
        return num;
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
        arr = (T[]) new Comparable[oldArr.length * 2];
        
        System.arraycopy(oldArr, 0, arr, 0, oldArr.length);
    }
    
    private void fillGap(int position) {
        for (int i = position - 1; i < getSize(); i++) {
            arr[i] = arr[i + 1];
        }
        
        arr[num - 1] = null;
    }
    
    private void swap(int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    private void quickSort(int first, int last) {
        if (first < last) {
            int sorted = partition(first, last);
            
            quickSort(first, sorted - 1);
            quickSort(sorted + 1, last);
        }
    }
    
    private int partition(int first, int last) {
        T pivot = arr[last];
        
        int i = first;
        for (int j = first; j <= last; j++) {
            if (arr[j].compareTo(pivot) < 0) { // if arr[j] < pivot
                swap(i, j);
                i++;
            }
        }
        swap(i,last);
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
