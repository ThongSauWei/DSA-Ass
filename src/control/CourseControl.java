/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import entity.Course;
import adt.ListInterface;
import boundary.CourseManageUI;
import da.CourseDA;
import da.CourseProgrammeDA;
import da.ProgrammeDA;
import entity.CourseProgramme;
import entity.Programme;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import utility.InputHandling;
import utility.StartUp;

/**
 *
 * @author User
 */
public class CourseControl {

    private CourseDA courseDA = new CourseDA();
    private CourseProgrammeDA courseProgrammeDA = new CourseProgrammeDA();
    private ProgrammeDA programmeDA = new ProgrammeDA();

    private ListInterface<Course> courseList;
    private ListInterface<Programme> programmeList;
    private ListInterface<CourseProgramme> courseProgrammeList;

    private CourseManageUI courseManageUI = new CourseManageUI();

    public CourseControl() {
        courseList = courseDA.readFromFile();
        programmeList = programmeDA.readFromFile();
        courseProgrammeList = courseProgrammeDA.readFromFile();
        CourseProgramme.setCourseNo(1001 + courseProgrammeList.getSize());
    }

    public void courseMenu() {
        int choice;
        do {
            choice = courseManageUI.CourseMenu();
            switch (choice) {
                case 1:
                    displayCourse();
                    break;
                case 2:
                    sortCourse();
                    break;
                case 3:
                    addCourse();
                    break;
                case 4:
                    addCourseToProgramme();
                    break;
                case 5:
                    //removeCourse();
                    break;
                case 6:
                    //updateCourse();
                    break;
                case 7:
                    searchCourse();
                    break;
                case 8:
                    //report();
                    break;
                case 0:
                    break;
                default:
                    courseManageUI.invalidChoiceMessage();
            }
        } while (choice != 0);
    }

//-------------Display function----------------------------------------------------------------------------------------------------------------------------------------------------------------
    //display course
    public void displayCourse() {
        try {
            int ttlCourse = courseList.getSize();//get the total
            courseManageUI.displayCourse(courseList, ttlCourse);//passing the total course to the UI
        } catch (IndexOutOfBoundsException ex) {
            courseManageUI.courseNotFound();
        }
    }

//-------------Sort function----------------------------------------------------------------------------------------------------------------------------------------------------------------
    //sort course menu
    public void sortCourse() {
        int choice;
        do {
            choice = courseManageUI.sortCourse(courseList);

            switch (choice) {
                case 1:
                    sortCourseCode();
                    break;
                case 2:
                    sortCourseName();
                    break;
                case 3:
                    sortStartDate();
                    break;
                case 4:
                    sortEndDate();
                    break;
                case 0:
                    break;
                default:
                    courseManageUI.invalidChoiceMessage();
                    break;
            }
        } while (choice != 0);

    }

    //sort Course Code
    public void sortCourseCode() {
        int choice;

        do {
            choice = courseManageUI.sortCourseCode(courseList);
            switch (choice) {
                case 1:
                    courseList.sort(Comparator.comparing(Course::getCourseCode));
                    displayCourse();
                    break;
                case 2:
                    courseList.sort(Comparator.comparing(Course::getCourseCode));
                    courseList.reverse();
                    displayCourse();
                    break;
                case 0:
                    break;
                default:
                    courseManageUI.invalidChoiceMessage();
                    break;
            }
        } while (choice != 0);
    }

    public void sortCourseName() {
        int choice;

        do {
            choice = courseManageUI.sortCourseName(courseList);
            switch (choice) {
                case 1:
                    courseList.sort(Comparator.comparing(Course::getCourseName));
                    displayCourse();
                    break;
                case 2:
                    courseList.sort(Comparator.comparing(Course::getCourseName));
                    courseList.reverse();
                    displayCourse();
                    break;
                case 0:
                    break;
                default:
                    courseManageUI.invalidChoiceMessage();
                    break;
            }
        } while (choice != 0);
    }

    public void sortStartDate() {
        int choice;

        do {
            choice = courseManageUI.sortStartDate(courseList);
            switch (choice) {
                case 1:
                    courseList.sort(Comparator.comparing(Course::getStartDate));
                    displayCourse();
                    break;
                case 2:
                    courseList.sort(Comparator.comparing(Course::getStartDate));
                    courseList.reverse();
                    displayCourse();
                    break;
                case 0:
                    break;
                default:
                    courseManageUI.invalidChoiceMessage();
                    break;
            }
        } while (choice != 0);
    }

    public void sortEndDate() {
        int choice;

        do {
            choice = courseManageUI.sortEndDate(courseList);
            switch (choice) {
                case 1:
                    courseList.sort(Comparator.comparing(Course::getEndDate));
                    displayCourse();
                    break;
                case 2:
                    courseList.sort(Comparator.comparing(Course::getEndDate));
                    courseList.reverse();
                    displayCourse();
                    break;
                case 0:
                    break;
                default:
                    courseManageUI.invalidChoiceMessage();
                    break;
            }
        } while (choice != 0);
    }

//-------------Add function----------------------------------------------------------------------------------------------------------------------------------------------------------------
    //add course
    public void addCourse() {
        String courseCode = courseManageUI.getCourseCode();
        Course course = courseManageUI.addCourse(courseCode);

        courseList.add(course);
        courseDA.writeToFile(courseList);
    }

//-------------Add course to programme function----------------------------------------------------------------------------------------------------------------------------------------------------------------

//-------------Add course to programme function----------------------------------------------------------------------------------------------------------------------------------------------------------------   
    public void addCourseToProgramme() {
        int choice;
        displayCourse();
        choice = courseManageUI.addCouserToProgramme(courseList);
    }

//-------------Remove function----------------------------------------------------------------------------------------------------------------------------------------------------------------
    //remove course
//-------------Update function----------------------------------------------------------------------------------------------------------------------------------------------------------------
    //update course
//-------------Search function----------------------------------------------------------------------------------------------------------------------------------------------------------------
    //search course
//-------------Search function----------------------------------------------------------------------------------------------------------------------------------------------------------------
    public Course searchCourse() {
        String courseCode = courseManageUI.getSearchCourseCode(); // get the course code
        Course foundCourse = null;

        // loop the list print out the the list are match with the search code
        for (Course course : courseList) {
            if (course.getCourseCode().equalsIgnoreCase(courseCode)) {
                foundCourse = course;
                break; // stop when the result are founded
            }
        }
        int ttlCourse = courseList.getSize();//get the total
        courseManageUI.searchCourse(foundCourse, ttlCourse);
        return foundCourse; // if no the course code return null
    }

//-------------Report function----------------------------------------------------------------------------------------------------------------------------------------------------------------
    //report
    public void writeToFile(ListInterface<Course> courseList) {
        courseDA.writeToFile(courseList);
    }

    public static void main(String[] args) {
        CourseControl courseControl = new CourseControl();
        courseControl.courseMenu();
//        StartUp startUp = new StartUp();
//        startUp.init();
    }
}
