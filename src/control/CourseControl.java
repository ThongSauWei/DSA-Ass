/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.LinkedList;
import entity.Course;
import adt.ListInterface;
import boundary.CourseManageUI;
import da.CourseDA;
import da.CourseProgrammeDA;
import da.ProgrammeDA;
import entity.CourseProgramme;
import entity.Programme;
import java.util.Comparator;
import utility.InputHandling;

/**
 *
 * @author Erika Fung Chyau Kang
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
                    removeCourse();
                    break;
                case 6:
                    updateCourse();
                    break;
                case 7:
                    searchCourse();
                    break;
                case 8:
                    filterCourse();
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
        int ttlCourse = courseList.getSize();//get the total
        if (!courseList.isEmpty()) {
            courseManageUI.displayCourse(courseList, ttlCourse);//passing the total course to the UI
        } else {
            courseManageUI.displayCourseNotFound(courseList, ttlCourse);
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
        boolean courseExists = false;
        for (Course courseCheck : courseList) {
            if (courseCheck.getCourseCode().equals(courseCode)) {
                courseExists = true;
                break; // Found the course, exit the loop
            }
        }
        
        if (!courseExists) {
            Course course = courseManageUI.addCourse(courseCode);
            courseList.add(course);
            courseDA.writeToFile(courseList);
        } else {
            courseManageUI.duplicateCourse();
        }

    }

//-------------Programme list function----------------------------------------------------------------------------------------------------------------------------------------------------------------
    public Programme chooseProgramme() {
        new ProgrammeControl().listProgramme(programmeList);
        int choice = courseManageUI.getProgrammeChoice(programmeList);

        return programmeList.get(choice);//get the user choice
    }

//-------------Course list function----------------------------------------------------------------------------------------------------------------------------------------------------------------
    public Course chooseCourse() {
        displayCourse();
        int choice = courseManageUI.getCourseChoice(courseList);

        return courseList.get(choice);
    }

//-------------Filter Course choose function----------------------------------------------------------------------------------------------------------------------------------------------------------------
    public ListInterface<Course> filterChooseCourse(Programme programme) {//have a programme
        //if return list then it can return many course list
        ListInterface<Course> filterCourse = new LinkedList();//new empty linkedList to store the filter course

        for (CourseProgramme courseProgramme : courseProgrammeList) {
            if (courseProgramme.getProgrammeCode().equals(programme)) {
                filterCourse.add(courseProgramme.getCourseCode());
            }
        }

        return filterCourse;
    }

    public Course filterChooseDisplay(ListInterface<Course> courseFilterList) {

        int ttlCourse = courseFilterList.getSize();//get the total
        if (!courseFilterList.isEmpty()) {
            courseManageUI.displayCourse(courseFilterList, ttlCourse);//passing the total course to the UI
            int choice = courseManageUI.getCourseChoice(courseFilterList);
            return courseFilterList.get(choice);
        } else {
            courseManageUI.displayCourseNotFound(courseFilterList, ttlCourse);
            return null;
        }

    }
//-------------Add course to programme function----------------------------------------------------------------------------------------------------------------------------------------------------------------

    public void addCourseToProgramme() {
        Programme programme = chooseProgramme();
        Course course = chooseCourse();

        boolean courseExist = false;
        for (CourseProgramme courseProgramme : courseProgrammeList) {
            if (courseProgramme.getProgrammeCode().equals(programme) && courseProgramme.getCourseCode().equals(course)) {
                courseExist = true;
                break;//when found it then stop
            }
        }

        if (!courseExist) {
            CourseProgramme courseProgramme = new CourseProgramme(programme, course);
            courseProgrammeList.add(courseProgramme);
            courseProgrammeDA.writeToFile(courseProgrammeList);
            courseManageUI.courseAddSuccess(course.getCourseCode(), programme.getProgrammeCode());
        } else {
            courseManageUI.courseExist();
        }
        InputHandling.systemPause();
    }

//-------------Remove function----------------------------------------------------------------------------------------------------------------------------------------------------------------
    //remove course
    public void removeCourse() {
        int choice;
        do {
            choice = courseManageUI.removeCourse(courseList);

            switch (choice) {
                case 1:
                    removeCourseList();
                    break;
                case 2:
                    removeCourseInProgramme();
                    break;
                case 0:
                    break;
                default:
                    courseManageUI.invalidChoiceMessage();
                    break;
            }
        } while (choice != 0);

    }

    //remove course list
    public void removeCourseList() {
        Course course = chooseCourse();
        Programme programme = new Programme();

        courseList = new CourseDA().readFromFile();
        boolean removeList = courseList.remove(course);
        boolean remove = false;
        if (removeList) {
            if (courseManageUI.confirmRemove()) {
                new CourseDA().writeToFile(courseList);
                courseManageUI.courseRemoveSuccess(course.getCourseCode());
                for (CourseProgramme courseProgramme : courseProgrammeList) {
                    if (courseProgramme.getProgrammeCode().equals(programme) && courseProgramme.getCourseCode().equals(course)) {
                        if (courseManageUI.confirmRemove()) {
                            courseProgrammeList.remove(courseProgramme);
                            remove = true;
                            break;//when found it then stop
                        }
                    }
                }
            } else {
                courseManageUI.courseRemoveUnsuccess(course.getCourseCode());
            }
            InputHandling.systemPause();
        }
    }

    //remove course in programme
    public void removeCourseInProgramme() {
        Programme programme = chooseProgramme();
        ListInterface<Course> filterCourse = filterChooseCourse(programme);// call interface
        Course course = filterChooseDisplay(filterCourse);

        if (course == null) {// when the record are empty it will return null if null the function will exit
            return;
        }

        courseProgrammeList = new CourseProgrammeDA().readFromFile();
        boolean remove = false;

        for (CourseProgramme courseProgramme : courseProgrammeList) {
            if (courseProgramme.getProgrammeCode().equals(programme) && courseProgramme.getCourseCode().equals(course)) {
                if (courseManageUI.confirmRemove()) {
                    courseProgrammeList.remove(courseProgramme);
                    remove = true;
                    break;//when found it then stop
                }
            }
        }

        if (remove) {
            courseProgrammeDA.writeToFile(courseProgrammeList);
            courseManageUI.CPRemoveSuccess(course.getCourseCode(), programme.getProgrammeCode());
        } else {
            courseManageUI.CPRemoveUnsuccess(course.getCourseCode(), programme.getProgrammeCode());
        }
        InputHandling.systemPause();
    }
//-------------Update function----------------------------------------------------------------------------------------------------------------------------------------------------------------
    //update course

    public void updateCourse() {
        Course course = chooseCourse();
        Course updatedCourse = courseManageUI.updateCourse(course);
        boolean confirm = courseManageUI.confirmUpdate();
        int count = 1;
        if (confirm && updatedCourse != null) {
            for (Course upCourse : courseList) {
                if (upCourse.getCourseCode().equals(updatedCourse.getCourseCode())) {//String have to compare back with the String
                    courseList.replace(updatedCourse, count);
                    courseDA.writeToFile(courseList);
                    courseManageUI.updateSuccess();
                    break;
                }
                count++;
            }
        } else {
            courseManageUI.unsuccess();
        }
    }

//-------------Search function----------------------------------------------------------------------------------------------------------------------------------------------------------------
    //search course
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

//-------------Filter function----------------------------------------------------------------------------------------------------------------------------------------------------------------
    public void filterCourse() {

        Programme programme = chooseProgramme();
        ListInterface<CourseProgramme> courseProgrammeList = new LinkedList<>();

        // filter the course programme list so that only courses available in the programme is listed
        courseProgrammeList = new da.CourseProgrammeDA().readFromFile().filter(courseProgramme -> courseProgramme.getProgrammeCode().equals(programme));

        ListInterface<Course> courseList = new LinkedList<>();

        for (CourseProgramme courseProgramme : courseProgrammeList) { // the courses are stored into the course list
            courseList.add(courseProgramme.getCourseCode());
        }

        courseManageUI.displayProgramme(programme); // display the programme

        if (courseList.isEmpty()) {
            courseManageUI.displayCourseNotFound(courseList, courseList.getSize());
        } else {
            courseManageUI.displayCourse(courseList, courseList.getSize()); // display all the courses taken
        }
    }

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
