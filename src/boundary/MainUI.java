/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import utility.Helper;
import utility.InputHandling;
/**
 *
 * @author Benjamin
 */
public class MainUI {
    
    public void displayMenu() {
        System.out.println("Welcome to the University Management System");
        Helper.printLine('-', 43);
        System.out.println("1. Programme Management");
        System.out.println("2. Course Management");
        System.out.println("3. Tutorial Group Management");
        System.out.println("4. Assignment Team Management");
        System.out.println("0. Exit");
        Helper.printLine('-', 43);
    }
    
    public void displayInvalidChoiceMessage() {
        System.out.println("Invalid Choice! Please Enter Again.");
    }
    
    public int getChoice() {
        int choice = InputHandling.getInt("Please Select the Subsystem To Proceed : ");
        System.out.println();
        
        return choice;
    }
}
