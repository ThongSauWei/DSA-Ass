/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import adt.ListInterface;
import entity.Course;
import entity.Programme;
import java.text.SimpleDateFormat;
import java.util.Date;
import utility.Helper;
import utility.InputHandling;

/**
 *
 * @author Erika Fung Chyau Kang
 */
public class CourseManageUI {

    public int CourseMenu() {
        System.out.println();
        Helper.printLine('-', 30);
        System.out.println("-\u001B[36m Course Menu\u001B[0m -");
        Helper.printLine('-', 30);
        System.out.println("1. Display Course List");
        System.out.println("2. Sort Course List");
        System.out.println("3. Add A New Course");
        System.out.println("4. Add A Course To Programme");
        System.out.println("5. Delete Course");
        System.out.println("6. Update Course");
        System.out.println("7. Search Course");
        System.out.println("8. Filter Course By Progamme");
        System.out.println("0. Exit");
        Helper.printLine('-', 30);

        int choice = InputHandling.getInt("Please Choose Your Option : ");

        while (!Helper.choiceValidation(choice, 0, 8)) {
            choice = InputHandling.getInt("\nPlease Choose Your Option Again : ");
        }

        return choice;
    }

    //-------------Display course list function----------------------------------------------------------------------------------------------------------------------------------------------------------------
    public void displayCourse(ListInterface<Course> courseList, int ttlCourse) {
        int num = 1;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("");
        Helper.printLine('-', 160);
        System.out.println("-\u001B[36m Display Cousre List\u001B[0m -");
        Helper.printLine('-', 160);
        System.out.printf("%-5s %-15s %-38s %-65s %-20s %-20s%n", "No", "Course Code", "Course Name", "Course Details", "Start Date", "End Date");
        System.out.printf("%-5s %-15s %-38s %-65s %-20s %-20s%n", "--", "-----------", "-----------", "--------------", "----------", "--------");

        for (Course course : courseList) {
            System.out.printf("%-5s %-15s %-38s %-65s %-20s %-20s%n", num, course.getCourseCode(), course.getCourseName(), course.getCourseDetail(), dateFormat.format(course.getStartDate()), dateFormat.format(course.getEndDate()));
            num++;
        }
        Helper.printLine('-', 160);
        System.out.println("Total Course: \u001B[32m" + ttlCourse + "\u001B[0m");
        System.out.println("----------------");
        System.out.println("");
    }

    public void displayCourseNotFound(ListInterface<Course> courseList, int ttlCourse) {
        Helper.clearScreen();
        Helper.clearScreen();
        Helper.printLine('-', 160);
        System.out.println("-\u001B[36m Display Cousre List\u001B[0m -");
        Helper.printLine('-', 160);
        System.out.printf("%-5s %-15s %-38s %-65s %-20s %-20s%n", "No", "Course Code", "Course Name", "Course Details", "Start Date", "End Date");
        System.out.printf("%-5s %-15s %-38s %-65s %-20s %-20s%n", "--", "-----------", "-----------", "--------------", "----------", "--------");
        courseNotFound();

        Helper.printLine('-', 160);
        System.out.println("Total Course: \u001B[32m" + ttlCourse + "\u001B[0m");
        System.out.println("----------------");
        InputHandling.systemPause();
    }

    //-------------Sort course list function----------------------------------------------------------------------------------------------------------------------------------------------------------------
    public int sortCourse(ListInterface<Course> courseList) {
        Helper.clearScreen();
        int choice;

        Helper.printLine('-', 30);
        System.out.println("-\u001B[36m Sort Cousre List\u001B[0m -");
        Helper.printLine('-', 30);
        System.out.println("1. Sort by Course Code");
        System.out.println("2. Sort by Course Name");
        System.out.println("3. Sort by Start Date");
        System.out.println("4. Sort by End Date");
        System.out.println("0. Exit");

        choice = InputHandling.getInt("Please Choose Your Option : ");
        while (!Helper.choiceValidation(choice, 0, 4)) {
            choice = InputHandling.getInt("\nPlease Choose Your Option Again : ");
        }

        return choice;
    }

    //Sort by Code
    public int sortCourseCode(ListInterface<Course> courseList) {
        int choice;

        Helper.printLine('-', 30);
        System.out.println("-\u001B[36m Sort By Course Code\u001B[0m -");
        Helper.printLine('-', 30);
        System.out.println("1. Sort Course Code");
        System.out.println("2. Reverse Course Code");
        System.out.println("0. Exit");
        choice = InputHandling.getInt("Please Choose Your Option : ");

        while (!Helper.choiceValidation(choice, 0, 2)) {
            choice = InputHandling.getInt("\nPlease Choose Your Option Again : ");
        }

        return choice;
    }

    // sort by Name
    public int sortCourseName(ListInterface<Course> courseList) {
        int choice;

        Helper.printLine('-', 30);
        System.out.println("-\u001B[36m Sort By Course Name\u001B[0m -");
        Helper.printLine('-', 30);
        System.out.println("1. Sort Course Name");
        System.out.println("2. Reverse Course Name");
        System.out.println("0. Exit");
        choice = InputHandling.getInt("Please Choose Your Option : ");

        while (!Helper.choiceValidation(choice, 0, 2)) {
            choice = InputHandling.getInt("\nPlease Choose Your Option Again : ");
        }

        return choice;
    }

    public int sortStartDate(ListInterface<Course> courseList) {
        int choice;

        Helper.printLine('-', 30);
        System.out.println("-\u001B[36m Sort By Start Date\u001B[0m -");
        Helper.printLine('-', 30);
        System.out.println("1. Sort Start Date");
        System.out.println("2. Reverse Start Date");
        System.out.println("0. Exit");
        choice = InputHandling.getInt("Please Choose Your Option : ");

        while (!Helper.choiceValidation(choice, 0, 2)) {
            choice = InputHandling.getInt("\nPlease Choose Your Option Again : ");
        }

        return choice;
    }

    public int sortEndDate(ListInterface<Course> courseList) {
        int choice;

        Helper.printLine('-', 30);
        System.out.println("-\u001B[36m Sort By End Date\u001B[0m -");
        Helper.printLine('-', 30);
        System.out.println("1. Sort End Date");
        System.out.println("2. Reverse End Date");
        System.out.println("0. Exit");
        choice = InputHandling.getInt("Please Choose Your Option : ");

        while (!Helper.choiceValidation(choice, 0, 2)) {
            choice = InputHandling.getInt("\nPlease Choose Your Option Again : ");
        }

        return choice;
    }

    //-------------Add A New Course Function----------------------------------------------------------------------------------------------------------------------------------------------------------------
    //get course code
    public String getCourseCode() {//BACS2063 add design
        String courseCode;
        boolean codeCheck;
        Helper.clearScreen();
        Helper.printLine('-', 30);
        System.out.println("-\u001B[36m Add A New Course\u001B[0m -");
        Helper.printLine('-', 30);
        do {
            courseCode = InputHandling.getString("Course Code (exp:AACS2034): ");
            if (!isValidCourseCode(courseCode)) {
                invalidCourseCode();
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
        Date startDate = null;
        Date endDate = null;

        courseName = InputHandling.getString("Course Name: ");
        courseDetail = InputHandling.getString("Course Details: ");
        do {//do while for start date
            startDate = InputHandling.getDate("Start Date (yyyy-MM-dd): ");
            if (startDate == null) {
                System.out.println("Please Enter Start Date again!");
            } else {
                do {//do while for end date
                    endDate = InputHandling.getDate("End Date (yyyy-MM-dd): ");
                    if (endDate == null) {
                        System.out.println("Please Enter End Date again!");
                    } else {//both successful then will come out the message
                        Helper.printLine('-', 30);
                        addSuccess();
                    }
                } while (endDate == null);
            }
        } while (startDate == null);
        InputHandling.systemPause();
        return new Course(courseCode.toUpperCase(), courseName, courseDetail, startDate, endDate);
    }

    //-------------Add course to programme function----------------------------------------------------------------------------------------------------------------------------------------------------------------
    public int getProgrammeChoice(ListInterface<Programme> programmeList) {
        int choice = InputHandling.getInt("Please Choose The Number Of Programme: ");

        while (!Helper.choiceValidation(choice, 1, programmeList.getSize())) {
            choice = InputHandling.getInt("\nPlease Choose Your Option Again : ");
        }

        return choice;
    }

    public int getCourseChoice(ListInterface<Course> courseList) {
        int choice = InputHandling.getInt("Please Enter The Number Of Course: ");

        while (!Helper.choiceValidation(choice, 1, courseList.getSize())) {
            choice = InputHandling.getInt("\nPlease Choose Your Option Again : ");
        }

        return choice;
    }

    //-------------Remove course function----------------------------------------------------------------------------------------------------------------------------------------------------------------
    public int removeCourse(ListInterface<Course> courseList) {
        Helper.clearScreen();
        int choice;

        Helper.printLine('-', 30);
        System.out.println("-\u001B[36m Remove Course\u001B[0m -");
        Helper.printLine('-', 30);
        System.out.println("1. Remove From Course List");
        System.out.println("2. Remove From Programe");
        System.out.println("0. Exit");

        choice = InputHandling.getInt("Please Choose Your Option : ");
        while (!Helper.choiceValidation(choice, 0, 2)) {
            choice = InputHandling.getInt("\nPlease Choose Your Option Again : ");
        }

        return choice;
    }

    public boolean confirmRemove() {
        return InputHandling.getConfirmation("Confirm to Delete? (Y=Yes/N=No): ");
    }

    //-------------Search course function----------------------------------------------------------------------------------------------------------------------------------------------------------------
    public String getSearchCourseCode() {//BACS2063 (get the code user input)
        String courseCode;

        Helper.clearScreen();
        Helper.printLine('-', 30);
        System.out.println("-\u001B[36m Search Course\u001B[0m -");
        Helper.printLine('-', 30);

        do {
            courseCode = InputHandling.getString("Course Code (exp:AACS2034): ");
            if (!isValidCourseCode(courseCode)) {
                invalidCourseCode();
            }
        } while (!isValidCourseCode(courseCode));

        return courseCode;
    }

    public void searchCourse(Course foundCourse, int ttlCourse) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Helper.clearScreen();
        Helper.printLine('-', 160);
        System.out.println("-\u001B[36m Search Course List\u001B[0m -");
        Helper.printLine('-', 160);
        if (foundCourse != null) {//check the course whether exist or not
            System.out.printf("%-15s %-35s %-70s %-20s %-20s%n", "Course Code", "Course Name", "Course Details", "Start Date", "End Date");
            System.out.printf("%-15s %-35s %-70s %-20s %-20s%n", "-----------", "-----------", "--------------", "----------", "--------");
            System.out.printf("%-15s %-35s %-70s %-20s %-20s%n", foundCourse.getCourseCode(), foundCourse.getCourseName(), foundCourse.getCourseDetail(), dateFormat.format(foundCourse.getStartDate()), dateFormat.format(foundCourse.getEndDate()));
            Helper.printLine('-', 160);
            Helper.printLine('-', 22);
        } else {
            Helper.printLine('-', 16);
            courseNotFound();
            Helper.printLine('-', 16);
        }

        InputHandling.systemPause();
    }

    //-------------Update course function----------------------------------------------------------------------------------------------------------------------------------------------------------------
    public Course updateCourse(Course updatedCourse) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;

        Helper.printLine('-', 17);
        System.out.println("- \u001B[36mUpdate Course\u001B[0m -");
        Helper.printLine('-', 17);
        String courseName = InputHandling.getString("Updated Course Name: ");
        String courseDetail = InputHandling.getString("Updated Course Details: ");
        do {//do while for start date
            startDate = InputHandling.getDate("Updated Start Date (yyyy-MM-dd): ");
            if (startDate == null) {
                System.out.println("Please Enter Start Date again!");
            } else {
                do {//do while for end date
                    endDate = InputHandling.getDate("Updated End Date (yyyy-MM-dd): ");
                    if (endDate == null) {
                        System.out.println("Please Enter End Date again!");
                    } else {//both successful then will come out the message
                        System.out.println();
                        Helper.printLine('-', 30);
                        System.out.println("- \u001B[36mUpdated Course Information\u001B[0m -");
                        Helper.printLine('-', 30);
                        System.out.println("Course Name: \u001B[32m" + courseName + "\u001B[0m"); // Set to green and reset
                        System.out.println("Course Details: \u001B[32m" + courseDetail + "\u001B[0m"); // Set to green and reset
                        System.out.println("Start Date: \u001B[32m" + dateFormat.format(startDate) + "\u001B[0m"); // Set to green and reset
                        System.out.println("End Date: \u001B[32m" + dateFormat.format(endDate) + "\u001B[0m"); // Set to green and reset
                    }
                } while (endDate == null);
            }
        } while (startDate == null);

        // Display the updated course information for confirmation
        return new Course(updatedCourse.getCourseCode(), courseName, courseDetail, startDate, endDate);
    }

    public boolean confirmUpdate() {
        return InputHandling.getConfirmation("Confirm to Update? (Y=Yes/N=No): ");
    }

//    Black: \u001B[30m
//    Red: \u001B[31m
//    Green: \u001B[32m
//    Yellow: \u001B[33m
//    Blue: \u001B[34m
//    Magenta: \u001B[35m
//    Cyan: \u001B[36m
//    White: \u001B[37m
    //-------------Filter course function----------------------------------------------------------------------------------------------------------------------------------------------------------------
    public void filterCourse() {
        Helper.printLine('-', 30);
        System.out.println("-\u001B[36m Filter Course By Program\u001B[0m -");
        Helper.printLine('-', 30);
    }

    public void displayProgramme(Programme programme) {
        Helper.printLine('-', 50);
        System.out.println("Programme Code : " + programme.getProgrammeCode());
        System.out.println("Programme Name : " + programme.getProgrammeName());
        System.out.println("Faculty : " + programme.getFaculty());
        Helper.printLine('-', 50);
    }

    //-------------Display successful the message----------------------------------------------------------------------------------------------------------------------------------------------------------------
    public void updateSuccess() {
        System.out.println("\u001B[32mUpdate successful\u001B[0m!");
    }

    public void addSuccess() {
        System.out.println("\u001B[33mCourse \u001B[32madded \u001B[32msuccessful\u001B[0m!");
    }

    public void courseAddSuccess(String courseCode, String programmeCode) {
        System.out.println("\nCourse " + courseCode + " \u001B[32mhas added successfully\u001B[0m to " + programmeCode + ".\n");
    }

    public void CPRemoveSuccess(String courseCode, String programmeCode) {
        System.out.println("\nCourse " + courseCode + " \u001B[32mhas been removed successfully\u001B[0m from " + programmeCode + ".\n");
    }

    public void courseRemoveSuccess(String courseCode) {
        System.out.println("\nCourse " + courseCode + " \u001B[32mremoved successfully\u001B[0m.\n");
    }

    //--------------Display invalid message----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public void invalidChoiceMessage() {
        System.out.println("\u001B[31mInvalid \u001B[34mInput. Please \u001B[0menter again.");
    }

    public void invalidCourseCode() {
        System.out.println("\u001B[31mInvalid\u001B[0m course code. Please enter an \u001B[33m4\u001B[0m-character code with \u001B[33m4\u001B[0m digits.");

    }

    public void notFound() {
        System.out.println("\u001B[34mRecord \u001B[31mNot Found\u001B[0m!");
    }

    public void unsuccess() {
        System.out.println("\u001B[34mUpdated \u001B[31mUnsuccessful\u001B[0m!");
    }

    public void CPRemoveUnsuccess(String courseCode, String programmeCode) {
        System.out.println("\nCourse " + courseCode + " \u001B[31mremove unsuccessfully\u001B[0m from " + programmeCode + ".\n");
    }

    public void courseRemoveUnsuccess(String courseCode) {
        System.out.println("\nCourse " + courseCode + " \u001B[31mremove unsuccessfully\u001B[0m.\n");
    }

    public void courseNotFound() {
        System.out.println("Course \u001B[31mNot Found\u001B[0m!");
    }

    public void courseExist() {
        System.out.println("\nThe Course Is Already \u001B[31mIn The Program\u001B[0m!\n");
    }
    
    public void duplicateCourse() {
        System.out.println("\nThe Course Code Is Already \u001B[31mExist\u001B[0m!\n");
    }

    public void generateReport(String addedCourses, String removedCourses, String updatedCourses) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
