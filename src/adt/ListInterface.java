/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package adt;

import java.util.Comparator;
import java.util.function.Predicate;

/**
 *
 * @author Benjamin
 */
public interface ListInterface<T extends Comparable<T>> extends Iterable<T> {
    
    // Create
    public boolean add(T newEntry);
    
    public boolean add(T newEntry, int position);
    
    // Delete
    public T remove(int position);
    
    // Read
    public T get(int position);
    
    // Update
    public boolean replace(T entry, int position);
    
    public void sort();
    // Sort
    public void sort(Comparator<T> attribute);
    
    // Reverse
    public void reverse();
    
    // Count
    public int getSize();
    
    // Clear
    public void clear();
    
    public boolean isEmpty();
    
    // filter the list based on the criteria given
    public ListInterface<T> filter(Predicate<T> criteria);
    
    public IteratorInterface<T> getIterator();
    
    public IteratorInterface<T> getIterator(int position);
}
