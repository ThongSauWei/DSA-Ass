/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import utility.InputHandling;
import utility.Helper;

import control.ProgrammeControl;
import control.StudentControl;
import control.TutorialGroupControl;

import entity.Programme;
import entity.Student;
import entity.TutorialGroup;

import adt.LinkedList;
import adt.ListInterface;
/**
 *
 * @author User
 */
public class TutorialGroupMain {
    private ProgrammeControl programmeControl = new ProgrammeControl();
    private TutorialGroupControl tutorialGroupControl = new TutorialGroupControl();
    private StudentControl studentControl = new StudentControl();
    private ListInterface<TutorialGroup> tutorialGroupList = tutorialGroupControl.readFromFile();
    private ListInterface<Programme> programmeList = programmeControl.readFromFile();
    
    public TutorialGroupMain() {
        
        int choice;
        
        do {
            choice = displayMenu();
            Helper.clearScreen();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    searchStudent();
                    break;
                case 3:
                    updateStudent();
                    break;
                case 4:
                    removeStudent();
                    break;
                case 0:
                    break;
            }
        } while (choice != 0);
    }
    
    private TutorialGroup chooseTutorialGroup() {
        int count = 1;
        System.out.println("Choose the Programme");
        Helper.printLine('=', 20);
        for (Programme programme : programmeList) {
            System.out.println(count + ". " + programme.getProgrammeCode());
            count++;
        }
        Helper.printLine('=', 20);
        
        int choice = InputHandling.getInt("Please Choose Your Option : ");
        
        while (choice < 1 || choice > programmeList.getSize()) {
            System.out.println("Invalid Option! Please Enter Again.");
            choice = InputHandling.getInt("Please Choose Your Option : ");
        }
        
        Programme programme = programmeList.get(choice);
        ListInterface<TutorialGroup> programmeTtlGroups = new LinkedList<>();
        
        for (TutorialGroup ttlGroup : tutorialGroupList) {
            if (ttlGroup.getProgrammeCode().equals(programme)) {
                programmeTtlGroups.add(ttlGroup);
            }
        }
        
        int groupNo = InputHandling.getInt("Please Enter the Tutorial Group (max = " + programmeTtlGroups.getSize() + " ) : ");
        
        while (groupNo < 1 || groupNo > programmeTtlGroups.getSize()) {
            System.out.println("Invalid Option! Please Enter Again.");
            groupNo = InputHandling.getInt("Please Enter the Tutorial Group (max = " + programmeTtlGroups.getSize() + " ) : ");
        }
        
        return programmeTtlGroups.get(groupNo);
    }
    
    private int displayMenu() {
        System.out.println("Welcome to Tutorial Group Management");
        Helper.printLine('=', 36);
        System.out.println("1. Add New Student");
        System.out.println("2. Search Student");
        System.out.println("3. Update Student Information");
        System.out.println("4. Remove Student");
        System.out.println("0. Go Back to Home Page");
        Helper.printLine('=', 36);
        
        int choice = InputHandling.getInt("Please Choose Your Option : ");
        
        while (choice < 0 || choice > 4) {
            System.out.println("Invalid Option! Please Enter Again.");
            choice = InputHandling.getInt("Please Choose Your Option : ");
        }
        
        return choice;
    }
    
    private void addStudent() {
        System.out.println("You have selected to add new student...");
        System.out.println();
        System.out.println("Enter the Detail of the new student : ");
        String id = InputHandling.getString("ID : ");
        String name = InputHandling.getString("Name : ");
        String ic = InputHandling.getString("IC : ");
        String email = InputHandling.getString("Email : ");
        String phoneNo = InputHandling.getString("Phone Number : ");
        int year = InputHandling.getInt("Year of Study : ");
        int sem = InputHandling.getInt("Semester : ");
        System.out.println();
        
    }
    
    private void searchStudent() {
        System.out.println("You have selected to search for student...");
        System.out.println();
        System.out.println("1. ID");
        System.out.println("2. Name");
        System.out.println("3. IC");
        
        for (Student student : studentList) {
            
        }
    }
    
    private void updateStudent() {
        
    }
    
    private void removeStudent() {
        
    }
}
