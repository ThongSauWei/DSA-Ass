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
public class ArrayList<T> implements Serializable {
    
    private T[] arr;
    private int num;
    private static final int DEFAULT_SIZE = 5;
    
    public ArrayList() {
        arr = (T[]) new Object[DEFAULT_SIZE];
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
        if (position < 0 || position >= getSize()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        
        if (isFull()) {
            expand();
        }
        
        for (int i = getSize(); i > position; i--) {
            arr[i] = arr[i - 1];
        }
        
        arr[position] = newEntry;
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
        if (position < 0 || position >= getSize()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        
        return arr[position];
    }
    
    public boolean replace(T newEntry, int position) {
        arr[position] = newEntry;
        
        return true;
    }
    
    // public void sort();
    
    public int getSize() {
        return num;
    }
    
    @Override
    public String toString() {
        String str = "";
        
        for (int i = 0; i < getSize(); i++) {
            str += arr[i];
        }
        
        return str;
    }
    
    // utility methods
    private void expand() {        
        T[] oldArr = arr;
        arr = (T[]) new Object[oldArr.length * 2];
        
        System.arraycopy(oldArr, 0, arr, 0, oldArr.length);
    }
    
    private void fillGap(int position) {
        for (int i = position; i < getSize() - 1; i++) {
            arr[i] = arr[i + 1];
        }
        
        arr[num - 1] = null;
    }
    
    private boolean isFull() {
        return arr.length == num;
    }
    
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(1,1);
        list.add(2,2);
        list.add(3,2);
        
        System.out.println(list);
        
        System.out.println(list.getSize());
        
        System.out.println(list.remove(3));
        System.out.println(list.get(3));
        list.replace(4, 4);
        
        System.out.println(list);
        
        System.out.println(list.getSize());
    }
}
