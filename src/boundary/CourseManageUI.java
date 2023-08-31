/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import entity.Course;
import java.util.Date;
import utility.Helper;
import utility.InputHandling;

/**
 *
 * @author erika
 */
public class CourseManageUI {
    // ANSI color codes

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public int CourseMenu() {
        System.out.println("\n- PROGRAMME MENU -");
        Helper.printLine('-', 25);
        System.out.println("1. Display Course List");
        System.out.println("2. Filter Course List");
        System.out.println("3. Add Course");
        System.out.println("4. Delete Course");
        System.out.println("5. Update Course");
        System.out.println("6. Search Course");
        System.out.println("7. Generate Report");
        System.out.println("0. Exit");
        Helper.printLine('-', 25);

        int choice = InputHandling.getInt("Please Choose Your Option : ");

        while (!Helper.choiceValidation(choice, 0, 7)) {
            choice = InputHandling.getInt("Please Choose Your Option : ");
        }

        return choice;
    }

    //get course code
    public String getCourseCode() {//BACS2063
        String courseCode;
        do {
            courseCode = InputHandling.getString("Course Code (exp:AACS2034): ");
            if (!isValidCourseCode(courseCode)) {
                System.out.println("Invalid course code. Please enter a 4-character code with 4 digits.");
            }
        } while (!isValidCourseCode(courseCode));

        return courseCode;
    }

    public boolean isValidCourseCode(String courseCode) {
        return courseCode.matches("[A-Z]{4}\\d{4}");// \\d{4}is a digit
    }

    //add new course
    public Course addCourse(String courseCode) {
        String courseName;
        String courseDetail;
        Date startDate;
        Date endDate;
        
        courseName = InputHandling.getString("Course Name: ");
        courseDetail = InputHandling.getString("Course Details: ");
        startDate = InputHandling.getDate("Start Date (yyy-MM-dd): ");
        endDate = InputHandling.getDate("End Date (yyyy-MM-dd): ");
        
        return new Course(courseCode.toUpperCase(), courseName, courseDetail, startDate, endDate);
    }

// Display successful the message
    public void success() {
        System.out.println(ANSI_BLUE + "Upadate" + ANSI_RESET + " " + ANSI_GREEN + "successful" + ANSI_RESET + "!");
    }

    public void addSuccess() {
        System.out.println(ANSI_BLUE + "Course" + ANSI_RESET + " " + ANSI_RESET + "added" + ANSI_GREEN + "successful" + ANSI_RESET + "!");
    }

    // Display invalid message
    public void invalidChoiceMessage() {
        System.out.println(ANSI_RED + "Invalid" + ANSI_RESET + " Input. Please " + ANSI_BLUE + "enter" + ANSI_RESET + " again.");
    }

    public void notFound() {
        System.out.println(ANSI_BLUE + "Record" + ANSI_RESET + " " + ANSI_RED + "Not Found" + ANSI_RESET + "!");
    }

    public void unsuccess() {
        System.out.println(ANSI_BLUE + "Upadated" + ANSI_RESET + " " + ANSI_RED + "Unsuccessful" + ANSI_RESET + "!");
    }

    public void courseNotFound() {
        System.out.println(ANSI_BLUE + "Course" + ANSI_RESET + " " + ANSI_RED + "Not Found" + ANSI_RESET + "!");
    }
}
