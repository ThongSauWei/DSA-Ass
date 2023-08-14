/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import control.StudentControl;
import entity.Student;
import adt.ListInterface;
import java.util.Scanner;
/**
 *
 * @author User
 */
public class StudentMain {
    private Scanner scanner = new Scanner(System.in);
    private StudentControl studentControl = new StudentControl();
    private ListInterface<Student> studentList = studentControl.readFromFile();
    
    public StudentMain() {
        
    }
    
    private int displayMenu() {
        int choice;
        
        System.out.println("Welcome to Student Management");
        System.out.println("=============================");
        System.out.println("1. Add New Student");
        System.out.println("2. Search Student");
        System.out.println("3. Update Student Information");
        System.out.println("4. Remove Student");
        System.out.println("=============================");
        System.out.print("Please Choose Your Option : ");
               
        return scanner.nextInt();
    }
}
