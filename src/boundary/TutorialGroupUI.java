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
import entity.Course;

import adt.ListInterface;
/**
 *
 * @author Benjamin
 */
public class TutorialGroupUI {
    // Display Menu
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
    
    public int displayGetStudentMenu() {
        System.out.println("You have to select a student from the tutorial group...");
        System.out.println();
        System.out.println("Choose the method for selecting");
        Helper.printLine('=', 36);
        System.out.println("1. Searching");
        System.out.println("2. Browsing (List)");
        System.out.println("3. Browsing (One)");
        Helper.printLine('=', 36);
        
        return InputHandling.choiceValidation("Option : ", 1, 3);
    }
    
    public int displayBrowseStudentMenu(Student student) {
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
    
    public int displayReportMenu() {
        System.out.println("You have selected to generate report");
        System.out.println();
        System.out.println("Generate Report For...");
        Helper.printLine('=', 22);
        System.out.println("1. One Tutorial Group");
        System.out.println("2. All Tutorial Groups In A Programme");
        System.out.println("3. All Tutorial Groups");
        Helper.printLine('=', 22);
        
        return InputHandling.choiceValidation("Option : ", 1, 3);
    }
    
    // Display Message/Entity
    public void displayTutorialGroup(TutorialGroup tutorialGroup) {
        System.out.println(tutorialGroup);
    }
    
    public void displayStudent(Student student) {
        System.out.println(student);
    }
    
    public void displayProgramme(Programme programme) {
        System.out.println("Programme Code : " + programme.getProgrammeCode());
        System.out.println("Programme Name : " + programme.getProgrammeName());
        System.out.println("Faculty : " + programme.getFaculty());
    }
    
    public void displayStudentNotFoundMessage() {
        System.out.println("Student Not Found!");
    }
    
    public void displayInvalidChoiceMessage() {
        System.out.println("Invalid Option! Please Enter Again.");
        InputHandling.systemPause();
    }
    
    public void displaySameTtlGroupMessage() {
        System.out.println("You Cannot Choose The Same Tutorial Group! Please Choose Again.");
        InputHandling.systemPause();
    }
    
    // Display List
    public void listTutorialGroups(ListInterface<TutorialGroup> tutorialGroupList) {
        listTutorialGroups(tutorialGroupList, 1);
    }
    
    public void listTutorialGroups(ListInterface<TutorialGroup> tutorialGroupList, int count) {
        System.out.println("The Tutorial Groups In The Programme");
        Helper.printLine('=', 36);
        for (TutorialGroup ttlGroup : tutorialGroupList) {
            System.out.print(count + ". " + ttlGroup);
            count++;
        }
        Helper.printLine('=', 36);
    }
    
    public void listProgrammes(ListInterface<Programme> programmeList) {
        int count = 1;
        System.out.println("The Programmes Available");
        Helper.printLine('=', 24);
        for (Programme programme : programmeList) {
            System.out.print(count + ". " + programme);
            count++;
        }
        Helper.printLine('=', 24);
    }
    
    public void listStudents(ListInterface<Student> studentList) {
        int count = 1;
        System.out.println("The Students In The Tutorial Group");
        Helper.printLine('=', 34);
        for (Student student : studentList) {
            System.out.print(count + ". " + student);
            count++;
        }
        Helper.printLine('=', 34);
    }
    
    public void listCourses(ListInterface<Course> courseList) {
        int count = 1;
        System.out.println("The Courses Available");
        Helper.printLine('=', 21);
        for (Course course : courseList) {
            System.out.print(count + ". " + course);
            count++;
        }
        Helper.printLine('=', 21);
    }
    
    public void generateReports() {
        
    }
    
    // Get Input
    public String getIdInput() {
        return InputHandling.getString("ID : ");
    }
    
    public int getNumOfStudents() {
        return InputHandling.getInt("Only Shows Tutorial Groups With Number Of Students More Than Or Equals To : ");
    }
    
    public int getStudentChoice(int size) {
        return InputHandling.choiceValidation("Please Choose The Student", 1, size);
    }
    
    public int getProgrammeChoice(int size) {
        return InputHandling.choiceValidation("Please Choose Your Option : ", 1, size);
    }
    
    public int getTutorialGroupChoice(int size) {       
        return InputHandling.choiceValidation("Please Enter The Tutorial Group Number (max = " + size + " ) : ", 1, size);
    }
    
    public int getCourseChoice(int size) {
        return InputHandling.choiceValidation("Please Choose Your Option : ", 1, size);
    }
    
    public Student getStudentDetail(TutorialGroup ttlGroup) {
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
    
    public boolean getConfirmationForGroupChanging(TutorialGroup oldTtlGroup, TutorialGroup newTtlGroup) {
        return InputHandling.getConfirmation("Are You Sure You Want To Change The Tutorial Group Of The Student From " + oldTtlGroup.getTutorialGroupId()
                + " To " + newTtlGroup.getTutorialGroupId() + "? (Y or N) : ");
    }
}
