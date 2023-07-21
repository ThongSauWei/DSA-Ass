/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package adt;

/**
 *
 * @author Benjamin
 */
public interface ListInterface<T extends Comparable<T>> {
    
    // Create
    public boolean add(T newEntry);
    
    public boolean add(T newEntry, int position);
    
    // Delete
    public T remove(int position);
    
    // Read
    public T get(int position);
    
    // Update
    public boolean replace(T entry, int position);
    
    // Sort
    public void sort();
    
    // Count
    public int getSize();
    
    // Clear
    public void clear();
    
    public boolean isEmpty();
}
