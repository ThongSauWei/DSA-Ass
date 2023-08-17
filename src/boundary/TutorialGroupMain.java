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
    
    private ListInterface<Programme> programmeList = programmeControl.readFromFile();
    private ListInterface<TutorialGroup> tutorialGroupList = tutorialGroupControl.readFromFile();
    private ListInterface<Student> studentList = studentControl.readFromFile();
    
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
                    removeStudent();
                    break;
                case 3:
                    changeTutorialGroup();
                    break;
                case 4:
                    findStudent();
                    break;
                case 5:
                    listStudents();
                    break;
                case 6:
                    filterTutorialGroups();
                    break;
                case 7:
                    generateReports();
                case 0:
                    break;
                default:
                    System.out.println("Invalid Option! Please Enter Again.");
            }
        } while (choice != 0);
    }
    
    private Programme chooseProgramme() {
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
        
        return programmeList.get(choice);
    }
    
    private TutorialGroup chooseTutorialGroup() {       
        Programme programme = chooseProgramme();
        ListInterface<TutorialGroup> programmeTtlGroups = new LinkedList<>();
        
        for (TutorialGroup ttlGroup : tutorialGroupList) {
            if (ttlGroup.getProgrammeCode().equals(programme)) {
                programmeTtlGroups.add(ttlGroup);
            }
        }
        
        int groupNo = InputHandling.getInt("Please Enter the Tutorial Group Number (max = " + programmeTtlGroups.getSize() + " ) : ");
        
        while (groupNo < 1 || groupNo > programmeTtlGroups.getSize()) {
            System.out.println("Invalid Option! Please Enter Again.");
            groupNo = InputHandling.getInt("Please Enter the Tutorial Group Number (max = " + programmeTtlGroups.getSize() + " ) : ");
        }
        
        return programmeTtlGroups.get(groupNo);
    }
    
    private int displayMenu() {
        System.out.println("Welcome to Tutorial Group Management");
        Helper.printLine('=', 50);
        System.out.println("1. Add A Student To A Tutorial Group");
        System.out.println("2. Remove A Student From A Tutorial Group");
        System.out.println("3. Change The Tutorial Group For A Student");
        System.out.println("4. Find A Student In A Tutorial Group");
        System.out.println("5. List All Students In A Tutorial Group");
        System.out.println("6. Filter Tutorial Groups Based On Criteria");
        System.out.println("7. Generate Reports");
        System.out.println("0. Go Back to Home Page");
        Helper.printLine('=', 50);
        
        int choice = InputHandling.getInt("Please Choose Your Option : ");
        
        while (choice < 0 || choice > 7) {
            System.out.println("Invalid Option! Please Enter Again.");
            choice = InputHandling.getInt("Please Choose Your Option : ");
        }
        
        return choice;
    }
    
    private void addStudent() {
        System.out.println("You have selected to add a student to a tutorial group...");
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
        TutorialGroup ttlGroup = chooseTutorialGroup();
        
        
    }
    
    private void removeStudent() {
        System.out.println("You have selected to remove a student from a tutorial group...");
        System.out.println();
        System.out.println("1. ID");
        System.out.println("2. Name");
        System.out.println("3. IC");
        
        for (Student student : studentList) {
            
        }
    }
    
    private void changeTutorialGroup() {
        
    }
    
    private void findStudent() {
        
    }
    
    private void listStudents() {
        
    }
    
    private void filterTutorialGroups() {
        
    }
    
    private void generateReports() {
        
    }
}
