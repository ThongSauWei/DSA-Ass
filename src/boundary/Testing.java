/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import adt.LinkedList;
import adt.LinkedList.CustomIterator;
/**
 *
 * @author User
 */
public class Testing {
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
        
        CustomIterator iterator = list.placeIterator(1);
        
        System.out.println(iterator.getCurrent());
        while (iterator.hasNext()) {
            iterator.next();
            System.out.println(iterator.getCurrent());
        }
    }
}
