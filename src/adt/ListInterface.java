/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package adt;

/**
 *
 * @author User
 */
public interface ListInterface<T> {
    
    // Create
    public boolean add(T newEntry);
    
    public boolean add(T newEntry, int position);
    
    // Delete
    public T remove(int position);
    
    // Read
    public T get(int position);
    
    public T get(T entry);
    
    // Update
    public boolean replace(T entry, int position);
    
    // Sort
    public void sort();
    
    // Count
    public int getSize();
}
