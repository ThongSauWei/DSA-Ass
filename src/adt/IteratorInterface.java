/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package adt;

/**
 *
 * @author User
 */
public interface IteratorInterface<T> {
    
    public boolean hasNext();
    
    public boolean hasPrevious();
    
    public T next();
    
    public T previous();
    
    public T getCurrent();
}
