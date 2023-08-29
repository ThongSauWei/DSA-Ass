/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import utility.InputHandling;
import utility.Helper;

import entity.Programme;
import entity.Student;
import entity.TutorialGroup;

import adt.ListInterface;
/**
 *
 * @author Benjamin
 */
public class TutorialGroupUI {
    public int displayMenu() {
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
        
        return InputHandling.choiceValidation("Please Choose Your Option : ", 0, 7);
    }
    
    public Student addStudentDetail(TutorialGroup ttlGroup) {
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
        
        return new Student(id, name, ic, email, phoneNo, year, sem, ttlGroup);  
    }
    
    public int getStudentMenu() {
        System.out.println("You have to select a student from the tutorial group...");
        System.out.println();
        System.out.println("Choose the method for selecting");
        Helper.printLine('=', 36);
        System.out.println("1. Searching");
        System.out.println("2. Browsing (List)");
        System.out.println("3. Browsing (One)");
        Helper.printLine('=', 36);
        
        return InputHandling.choiceValidation("Please Choose Your Option : ", 1, 3);
    }
    
    public int browseStudentMenu(Student student) {
        System.out.println(student);
        Helper.printLine('=', 30);
        System.out.println("1. Previous Student");
        System.out.println("2. Next Student");
        System.out.println("3. Remove Student");
        System.out.println("0. Exit");
        Helper.printLine('=', 30);
        
        return InputHandling.choiceValidation("Option : ", 0, 3);
    }
    
    public int displayCriteriaMenu() {
        System.out.println("You have selected to filter the tutorial groups...");
        System.out.println();
        System.out.println("Choose The Criteria");
        Helper.printLine('=', 19);
        System.out.println("1. Programme");
        System.out.println("2. Course Taken");
        System.out.println("3. Number Of Students");
        Helper.printLine('=', 19);
        
        return InputHandling.choiceValidation("Option : ", 1, 3);
    }
    
    public void displayStudent(Student student) {
        System.out.println(student);
    }
    
    public void displayStudentNotFoundMessage() {
        System.out.println("Student Not Found!");
    }
    
    public void filterTutorialGroups() {
        
    }
    
    public void generateReports() {
        
    }
    
    // others
    public void listProgrammes(ListInterface<Programme> programmeList) {
        int count = 1;
        System.out.println("Choose the Programme");
        Helper.printLine('=', 20);
        for (Programme programme : programmeList) {
            System.out.print(count + ". " + programme);
            count++;
        }
        Helper.printLine('=', 20);
    }
    
    public String getIdInput() {
        return InputHandling.getString("ID : ");
    }
    
    public void listStudents(ListInterface<Student> studentList) {
        int count = 1;
        System.out.println("Choose the Student");
        Helper.printLine('=', 18);
        for (Student student : studentList) {
            System.out.print(count + ". " + student);
            count++;
        }
        Helper.printLine('=', 18);
    }
    
    public void displayInvalidChoiceMessage() {
        System.out.println("Invalid Option! Please Enter Again.");
        InputHandling.systemPause();
    }
    
    public void displaySameTtlGroupMessage() {
        System.out.println("You Cannot Choose The Same Tutorial Group! Please Choose Again.");
        InputHandling.systemPause();
    }
    
    public int getStudentChoice(int size) {
        return InputHandling.choiceValidation("Please Choose The Student", 1, size);
    }
    
    public int getProgrammeChoice(int size) {
        return InputHandling.choiceValidation("Please Choose Your Option : ", 1, size);
    }
    
    public int getTutorialGroupChoice(int size) {       
        return InputHandling.choiceValidation("Please Enter the Tutorial Group Number (max = " + size + " ) : ", 1, size);
    }
    
    public boolean confirmForGroupChanging(TutorialGroup oldTtlGroup, TutorialGroup newTtlGroup) {
        return InputHandling.getConfirmation("Are You Sure You Want To Change The Tutorial Group Of The Student From " + oldTtlGroup.getTutorialGroupId()
                + " To " + newTtlGroup.getTutorialGroupId() + "? (Y or N) : ");
    }
}
