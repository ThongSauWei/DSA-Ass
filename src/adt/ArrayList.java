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
        if (position < 0 || position >= num) {
            throw new ArrayIndexOutOfBoundsException();
        }
        
        if (isFull()) {
            expand();
        }
        
        for (int i = num; i > position; i--) {
            arr[i] = arr[i - 1];
        }
        
        arr[position] = newEntry;
        num++;
        
        return true;
    }
    
    @Override
    public String toString() {
        String str = "";
        
        for (int i = 0; i < num; i++) {
            str += arr[i];
        }
        
        return str;
    }
    
    private void expand() {        
        T[] oldArr = arr;
        arr = (T[]) new Object[oldArr.length * 2];
        
        System.arraycopy(oldArr, 0, arr, 0, oldArr.length);
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
    }
}
