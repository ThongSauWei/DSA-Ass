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

        int choice = InputHandling.getInt("Please Choose Your Option : ");
        System.out.println();

        while (!Helper.choiceValidation(choice, 0, 7)) {
            choice = InputHandling.getInt("Please Choose Your Option : ");
            System.out.println();
        }

        return choice;
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

        int choice = InputHandling.getInt("Please Choose Your Option : ");
        System.out.println();

        while (!Helper.choiceValidation(choice, 1, 3)) {
            choice = InputHandling.getInt("Please Choose Your Option : ");
            System.out.println();
        }

        return choice;
    }

    public int displayBrowseStudentMenu(Student student) {
        System.out.println(student);
        Helper.printLine('=', 30);
        System.out.println("1. Previous Student");
        System.out.println("2. Next Student");
        System.out.println("3. Select Student");
        System.out.println("0. Exit");
        Helper.printLine('=', 30);

        int choice = InputHandling.getInt("Please Choose Your Option : ");
        System.out.println();

        while (!Helper.choiceValidation(choice, 0, 3)) {
            choice = InputHandling.getInt("Please Choose Your Option : ");
            System.out.println();
        }

        return choice;
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

        int choice = InputHandling.getInt("Please Choose Your Option : ");
        System.out.println();

        while (!Helper.choiceValidation(choice, 1, 3)) {
            choice = InputHandling.getInt("Please Choose Your Option : ");
            System.out.println();
        }

        return choice;
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

        int choice = InputHandling.getInt("Please Choose Your Option : ");
        System.out.println();

        while (!Helper.choiceValidation(choice, 1, 3)) {
            choice = InputHandling.getInt("Please Choose Your Option : ");
            System.out.println();
        }

        return choice;
    }

    // Display Message/Entity
    public void displayTutorialGroup(TutorialGroup tutorialGroup) {
        System.out.println();
        System.out.println("Tutorial Group : " + tutorialGroup.getTutorialGroupId());
    }

    public void displayStudent(Student student) {
        System.out.println(student);
        displaySystemPauseMessage();
    }

    public void displayProgramme(Programme programme) {
        System.out.println("Programme Code : " + programme.getProgrammeCode());
        System.out.println("Programme Name : " + programme.getProgrammeName());
        System.out.println("Faculty : " + programme.getFaculty());
    }
    
    public void displayChooseOldTtlGroup() {
        System.out.println("Please Choose The Old Tutorial Group");
    }
    
    public void displayChooseNewTtlGroup() {
        System.out.println("Please Choose The New Tutorial Group");
    }
    
    public void displayAddSuccessMessage() {
        System.out.println("Student Added Successfully");
    }
    
    public void displayAddFailedMessage() {
        System.out.println("Duplicated ID Is Found. Failed To Add Student");
        System.out.println();
        displaySystemPauseMessage();
    }
    
    public void displayRemoveSuccessMessage() {
        System.out.println("Student Removed Successfully");
        System.out.println();
        displaySystemPauseMessage();
    }
    
    public void displayRemoveCancelMessage() {
        System.out.println("Removing The Student Is Cancelled");
        System.out.println();
        displaySystemPauseMessage();
    }
    
    public void displayChangeSuccessMessage() {
        System.out.println("Student Successfully Change His/Her Tutorial Group");
        System.out.println();
        displaySystemPauseMessage();
    }
    
    public void displayChangeCancelMessage() {
        System.out.println("Changing Tutorial Group For Student Is Cancelled");
        System.out.println();
        displaySystemPauseMessage();
    }

    public void displayStudentNotFoundMessage() {
        System.out.println("Student Not Found!");
        System.out.println();
        InputHandling.systemPause();
    }

    public void displayInvalidChoiceMessage() {
        System.out.println("Invalid Option! Please Enter Again.");
    }

    public void displaySameTtlGroupMessage() {
        System.out.println("You Cannot Choose The Same Tutorial Group! Please Choose Again.");
    }
    
    public void displayNoTtlGroupMessage() {
        System.out.println("There Is No Tutorial Group In This Programme. Please Choose Again...");
        System.out.println();
        InputHandling.systemPause();
        System.out.println();
        System.out.println();
    }
    
    public void displayNoStudentMessage() {
        System.out.println("There is No Student In This Tutorial Group...");
        System.out.println();
        displaySystemPauseMessage();
    }
    
    public void displayNoCourseMessage() {
        System.out.println("There is No Course In This Tutorial Group...");
    }

    public void displaySystemPauseMessage() {
        InputHandling.systemPause();
        Helper.clearScreen();
    }

    // Display List
    public void listTutorialGroups(ListInterface<TutorialGroup> tutorialGroupList) {
        int count = 1;

        System.out.println("The Tutorial Groups In The Programme");
        Helper.printLine('=', 36);
        System.out.printf("%-8s %-25s %-15s\n", "No.", "Tutorial Group ID", "Num Of Students");
        Helper.printLine('-', 50);
        for (TutorialGroup ttlGroup : tutorialGroupList) {
            System.out.printf("%2d%-6s ", count, ".");
            System.out.println(ttlGroup);
            count++;
        }
        Helper.printLine('-', 50);
        
        System.out.println();
    }

    public void listStudents(ListInterface<Student> studentList) {
        listStudents(studentList, true);
    }

    public void listStudents(ListInterface<Student> studentList, boolean sysPause) {
        int count = 1;
        System.out.println("The Students In The Tutorial Group");
        Helper.printLine('=', 34);
        System.out.printf("%-8s %-12s %-30s %-16s %-40s %-13s %-14s\n", "No.", "Student ID", "Student Name", "Student IC", "Student Email", "Phone No", "Tutorial Group");
        Helper.printLine('-', 139);
        for (Student student : studentList) {
            System.out.printf("%2d%-6s ", count, ".");
            System.out.println(student);
            count++;
        }
        Helper.printLine('-', 139);

        if (sysPause) {
            displaySystemPauseMessage();
        }
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

    // Get Input
    public String getIdInput() {
        return InputHandling.getString("ID : ");
    }

    public int getNumOfStudents() {
        return InputHandling.getInt("Only Shows Tutorial Groups With Number Of Students More Than Or Equals To : ");
    }

    public int getStudentChoice(int size) {
        int choice = InputHandling.getInt("Please Choose Your Option : ");
        System.out.println();

        while (!Helper.choiceValidation(choice, 1, size)) {
            choice = InputHandling.getInt("Please Choose Your Option : ");
            System.out.println();
        }

        return choice;
    }

    public int getProgrammeChoice(int size) {
        int choice = InputHandling.getInt("Please Choose Your Option : ");
        System.out.println();

        while (!Helper.choiceValidation(choice, 1, size)) {
            choice = InputHandling.getInt("Please Choose Your Option : ");
            System.out.println();
        }

        return choice;
    }

    public int getTutorialGroupChoice(int size) {
        int choice = InputHandling.getInt("Please Enter The Tutorial Group Number (max = " + size + ") : ");
        System.out.println();

        while (!Helper.choiceValidation(choice, 1, size)) {
            choice = InputHandling.getInt("Please Enter The Tutorial Group Number (max = " + size + ") : ");
            System.out.println();
        }

        return choice;
    }

    public int getCourseChoice(int size) {
        int choice = InputHandling.getInt("Please Choose Your Option : ");
        System.out.println();

        while (!Helper.choiceValidation(choice, 1, size)) {
            choice = InputHandling.getInt("Please Choose Your Option : ");
            System.out.println();
        }

        return choice;
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
    
    public boolean getConfirmationForRemoving(Student student) {
        return InputHandling.getConfirmation("Are You Sure You Want To Remove " + student.getStudentName() + " (" + student.getStudentId()
                + ") From His/Her Tutorial Group? (Y or N) : ");
    }

    public boolean getConfirmationForGroupChanging(TutorialGroup oldTtlGroup, TutorialGroup newTtlGroup) {
        return InputHandling.getConfirmation("Are You Sure You Want To Change The Tutorial Group Of The Student From " + oldTtlGroup.getTutorialGroupId()
                + " To " + newTtlGroup.getTutorialGroupId() + "? (Y or N) : ");
    }
}
