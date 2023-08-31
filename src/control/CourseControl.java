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
import java.util.Date;
import utility.InputHandling;

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
                    //displayCourse();
                    break;
                case 2:
                    //filterCourse();
                    break;
                case 3:
                    addCourse();
                    break;
                case 4:
                    //removeCourse();
                    break;
                case 5:
                    //updateCourse();
                    break;
                case 6:
                    //searchCourse();
                    break;
                case 7:
                    //report();
                    break;
                case 0:
                    break;
                default:
                    courseManageUI.invalidChoiceMessage();
            }
        } while (choice != 0);
    }
    
    /*
    public void addCourse() {
        String courseCode = courseManageUI.getCourseCode();
        Course course = courseManageUI.addCourse(courseCode);

        courseList.add(course);
        courseDA.writeToFile(courseList);
    } 
    */

    public void addCourse() {
        String courseCode = courseManageUI.getCourseCode();
        String courseName = courseManageUI.getCourseName();
        String courseDetail = courseManageUI.getCourseDetail();
        Date startDate = getDate(courseManageUI.getStartDate());
        Date endDate = getDate(courseManageUI.getEndDate());
        
        Course course = new Course(courseCode, courseName, courseDetail, startDate, endDate);

        courseList.add(course);
        courseDA.writeToFile(courseList);
    }
    
    // Ben make de, dont like can delete, no need give him face one
    public Date getDate(String promptMsg) {
        do {
            String input = InputHandling.getString(promptMsg);
            try {
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(input);
                return date;
            } catch (ParseException ex) {
                courseManageUI.displayInvalidDateMessage();
            }
        } while (true);
    }

    public void writeToFile(ListInterface<Course> courseList) {
        courseDA.writeToFile(courseList);
    }

    public static void main(String[] args) {
        CourseControl courseControl = new CourseControl();
        courseControl.courseMenu();
    }
}
