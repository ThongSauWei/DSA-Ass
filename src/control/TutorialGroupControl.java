/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import entity.Programme;
import entity.Student;
import entity.TutorialGroup;
import entity.Course;
import entity.CourseProgramme;

import da.TutorialGroupDA;
import da.StudentDA;
import da.ProgrammeDA;

import boundary.TutorialGroupUI;

import adt.*;

import utility.Helper;

/**
 *
 * @author Benjamin
 */
public class TutorialGroupControl {  
    private TutorialGroupDA tutorialGroupDA = new TutorialGroupDA();
    private StudentDA studentDA = new StudentDA();    
    private ProgrammeDA programmeDA = new ProgrammeDA();

    private ListInterface<TutorialGroup> tutorialGroupList;
    private ListInterface<Student> studentList;
    private ListInterface<Programme> programmeList;

    private TutorialGroupUI tutorialGroupUI = new TutorialGroupUI();
    
    public TutorialGroupControl() {
        tutorialGroupList = tutorialGroupDA.readFromFile();
        studentList = studentDA.readFromFile();
        programmeList = programmeDA.readFromFile();
        
        runMain();
    }
    
    public void runMain() {
        int choice;
        
        do {
            tutorialGroupUI.displayMenu();
            
            do {
                choice = tutorialGroupUI.getChoice();
            } while (!Helper.choiceValidation(choice, 0, 7));

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
                    break;
                case 0:
                    break;
                default:
                    tutorialGroupUI.displayInvalidChoiceMessage();
            }
        } while (choice != 0);
        
        closeMain();
    }
    
    public void addStudent() { // completed
        TutorialGroup ttlGroup = chooseTutorialGroup(); // Programme programme = getProgramme() and Course course = getCourse()
        // courseProgrammeList.add(new CourseProgramme(programme, course);
        Student newStudent = tutorialGroupUI.getStudentDetail(ttlGroup);
        boolean idRepeated = false;
        
        for(Student student : studentList) {
            if (student.getStudentId().equals(newStudent.getStudentId())) { // check if the ID is duplicated
                idRepeated = true;
                break;
            }
        }
        
        if (!idRepeated) {
            tutorialGroupUI.displayAddSuccessMessage();
            tutorialGroupUI.displayStudent(newStudent);
            
            // add new student and save to student file
            studentList.add(newStudent);
            studentDA.writeToFile(studentList);
            
            // update the tutorial group data and save to tutorial group file
            ttlGroup.setNumOfStudent(ttlGroup.getNumOfStudent() + 1);
            tutorialGroupDA.writeToFile(tutorialGroupList);
        } else {
            tutorialGroupUI.displayAddFailedMessage();
        }
    }
    
    public void removeStudent() { // completed
        // filter the list so that the students in the tutorial group only will be listed
        Student studentRemove = getStudent();
        TutorialGroup ttlGroup = studentRemove.getTutorialGroupId();
        
        if (tutorialGroupUI.getConfirmationForRemoving(studentRemove)) { // ask for confirmation of removing
            tutorialGroupUI.displayRemoveSuccessMessage();
            
            // remove the student and save to student file
            studentList.remove(studentRemove);
            studentDA.writeToFile(studentList);

            // update the tutorial group data and save to tutorial group file
            ttlGroup.setNumOfStudent(ttlGroup.getNumOfStudent() - 1);
            tutorialGroupDA.writeToFile(tutorialGroupList);
        } else {
            tutorialGroupUI.displayRemoveCancelMessage();
        }
        
    }
    
    public void changeTutorialGroup() { // completed        
        // filter the list so that the students in the tutorial group only will be listed
        Student studentChange = getStudent();
        TutorialGroup oldTtlGroup = studentChange.getTutorialGroupId();
        
        // choose the new tutorial group (cannot same with the old tutorial group)
        tutorialGroupUI.displayChooseNewTtlGroup();
        TutorialGroup newTtlGroup = chooseTutorialGroup(oldTtlGroup);
        
        if (tutorialGroupUI.getConfirmationForGroupChanging(oldTtlGroup, newTtlGroup)) { // ask for confirmation of changing group
            tutorialGroupUI.displayChangeSuccessMessage();
            
            // update student data and save to student file
            studentChange.setTutorialGroupId(newTtlGroup);
            studentDA.writeToFile(studentList);
            
            // update the tutorial group data and save to tutorial group file
            newTtlGroup.setNumOfStudent(newTtlGroup.getNumOfStudent() + 1);
            oldTtlGroup.setNumOfStudent(oldTtlGroup.getNumOfStudent() - 1);
            tutorialGroupDA.writeToFile(tutorialGroupList);
        } else {
            tutorialGroupUI.displayChangeCancelMessage();
        }
    }
    
    public Student findStudent() { // completed & tested
        String id = tutorialGroupUI.getIdInput();
        
        return findStudent(id);
    }
    
    public Student findStudent(String id) {
        for(Student student : studentList) {
            if (student.getStudentId().equals(id)) { // search if the ID is matched
                tutorialGroupUI.displayStudent(student);
                return student;
            }
        }
        
        tutorialGroupUI.displayStudentNotFoundMessage(); // if not found, display message
        
        return null;
    }
    
    public void listStudents() { // completed & tested
        listStudents(true);
    }
    
    public void listStudents(boolean sysPause) {
        TutorialGroup tutorialGroup = chooseTutorialGroup();
        
        listStudents(tutorialGroup, sysPause);
    }
    
    public void listStudents(TutorialGroup tutorialGroup) {
        // filter the list so that the students in the tutorial group only will be listed
        listStudents(tutorialGroup, true);
    }
    
    public void listStudents(TutorialGroup tutorialGroup, boolean sysPause) {
        ListInterface<Student> filteredList = studentList.filter(student -> student.getTutorialGroupId().equals(tutorialGroup));
        
        if (!filteredList.isEmpty()) {
            
            tutorialGroupUI.listStudents(filteredList);
            
            if (sysPause) {
                tutorialGroupUI.displaySystemPauseMessage();
            }
        } else {
            tutorialGroupUI.displayNoStudentMessage();
        }
    }
    
    public void filterTutorialGroups() { // completed & tested
        int choice;
        
        tutorialGroupUI.displayCriteriaMenu();
        
        do {
            choice = tutorialGroupUI.getChoice();
        } while (!Helper.choiceValidation(choice, 1, 3));
        
        switch (choice) {
            case 1: // filter based on programme
                Programme programme = chooseProgramme();
                
                // filter the list so that the tutorial groups in the programme only will be listed               
                listTutorialGroups(tutorialGroupList.filter(tutorialGroup -> tutorialGroup.getProgrammeCode().equals(programme)));
                break;
            case 2: // maybe will be remove (filter based on courses taken)
                ListInterface<Course> allCourseList = new da.CourseDA().readFromFile(); // get the course list
                
                tutorialGroupUI.listCourses(allCourseList); // list out the course list for user to choose
                new CourseControl().displayCourse();
                
                int courseNo;
                
                do {
                    courseNo = tutorialGroupUI.getCourseChoice();
                } while (!Helper.choiceValidation(courseNo, 1, allCourseList.getSize()));
                
                Course course = allCourseList.get(courseNo);
                
                // filter the course programme list so that only programmes which has the course is listed
                ListInterface<CourseProgramme> programmes = new da.CourseProgrammeDA().readFromFile().filter(courseProgramme -> courseProgramme.getCourseCode().equals(course));

                for (CourseProgramme courseProgramme : programmes) { // for each programme, list out all the tutorial groups
                    tutorialGroupUI.displayProgramme(courseProgramme.getProgrammeCode());
                    listTutorialGroups(tutorialGroupList.filter(tutorialGroup -> tutorialGroup.getProgrammeCode().equals(courseProgramme.getProgrammeCode())));
                }
                break;
            case 3: // filter based on the number of students
                int num = tutorialGroupUI.getNumOfStudents();                
                
                // filter the list so that the tutorial groups with number of students higher than or equal to the num only will be listed
                listTutorialGroups(tutorialGroupList.filter(tutorialGroup -> tutorialGroup.getNumOfStudent() >= num));
                break;
            default:
                tutorialGroupUI.displayInvalidChoiceMessage();
        }
    }
    
    public void generateReports() { // completed & tested
        int choice;
        
        tutorialGroupUI.displayReportMenu();
        
        do {
            choice = tutorialGroupUI.getChoice();
        } while (!Helper.choiceValidation(choice, 1, 3));
        
        switch (choice) {
            case 1: // generate report for one tutorial group
                TutorialGroup tutorialGroup = chooseTutorialGroup();
                Programme programme = tutorialGroup.getProgrammeCode();
                
                ListInterface<CourseProgramme> courseProgrammeList = new LinkedList<>();
                
                // filter the course programme list so that only courses available in the programme is listed
                courseProgrammeList = new da.CourseProgrammeDA().readFromFile().filter(courseProgramme -> courseProgramme.getProgrammeCode().equals(programme));
                
                ListInterface<Course> courseList = new LinkedList<>();
                
                for (CourseProgramme courseProgramme : courseProgrammeList) { // the courses are stored into the course list
                    courseList.add(courseProgramme.getCourseCode());
                }
                
                tutorialGroupUI.displayProgramme(programme); // display the programme
                
                if (courseList.isEmpty()){
                    tutorialGroupUI.displayNoCourseMessage();
                } else {
                    tutorialGroupUI.listCourses(courseList); // display all the courses taken
                    new CourseControl().displayCourse();
                }
                
                tutorialGroupUI.displayTutorialGroup(tutorialGroup); // display the tutorial group
                listStudents(tutorialGroup); // display all the students
                break;
            case 2: // generate report for all the tutorial groups in a programme
                Programme programmeDisplay = null;
                // filter the list so that the tutorial group in the programme only will be listed
                ListInterface<TutorialGroup> programmeTtlGroups = new LinkedList<>();
                
                do {                    
                    Programme programmeChosen = chooseProgramme();
                    programmeDisplay = programmeChosen;
                    
                    programmeTtlGroups = tutorialGroupList.filter(ttlGroup -> ttlGroup.getProgrammeCode().equals(programmeChosen));
                    
                    if (programmeTtlGroups.isEmpty()) {
                        tutorialGroupUI.displayNoTtlGroupMessage();
                    }
                } while (programmeTtlGroups.isEmpty());
             
                tutorialGroupUI.displayProgramme(programmeDisplay);
                for (TutorialGroup group : programmeTtlGroups) { // for each tutorial group, list all the students inside
                    tutorialGroupUI.displayTutorialGroup(group);
                    listStudents(group);
                }
                break;
            case 3: // generate report for all the tutorial groups
                for (TutorialGroup group : tutorialGroupList) { // for each tutorial group, list its programme and all the students inside
                    tutorialGroupUI.displayProgramme(group.getProgrammeCode());
                    tutorialGroupUI.displayTutorialGroup(group);
                    listStudents(group);
                }
                break;
            default:
                tutorialGroupUI.displayInvalidChoiceMessage();
        }
    }
    
    public void listTutorialGroups(ListInterface<TutorialGroup> tutorialGroupList) {
        if (!tutorialGroupList.isEmpty()) {
            tutorialGroupUI.listTutorialGroups(tutorialGroupList);
            tutorialGroupUI.displaySystemPauseMessage();
        } else {
            tutorialGroupUI.displayNoTtlGroupMessage();
        }
    }
    
    public Programme chooseProgramme() {
        new ProgrammeControl().listProgramme(programmeList); // list all the programmes
        
        int choice;
        
        do {
            choice = tutorialGroupUI.getProgrammeChoice();
        } while (!Helper.choiceValidation(choice, 1, programmeList.getSize()));
        
        return programmeList.get(choice);
    }
    
    public TutorialGroup chooseTutorialGroup() {
        return chooseTutorialGroup(null); // no old tutorial group except for the case of changing tutorial group for a student
    }
    
    public TutorialGroup chooseTutorialGroup(TutorialGroup oldTtlGroup) {
        ListInterface<TutorialGroup> programmeTtlGroups = new LinkedList<>();
        
        do {
            Programme programme = chooseProgramme();

            // filter the list so that the tutorial group in the programme only will be listed
            programmeTtlGroups = tutorialGroupList.filter(tutorialGroup -> tutorialGroup.getProgrammeCode().equals(programme));

            if (programmeTtlGroups.isEmpty()) {
                tutorialGroupUI.displayNoTtlGroupMessage();
            }

        } while (programmeTtlGroups.isEmpty());

        TutorialGroup ttlGroupChosen = null;

        do {
            int choice = tutorialGroupUI.getTutorialGroupChoice(programmeTtlGroups.getSize());

            ttlGroupChosen = programmeTtlGroups.get(choice);

        } while (oldTtlGroup != null && !compareTtlGroup(oldTtlGroup, ttlGroupChosen)); // if there is old tutorial group, the selected tutorial group cannot be same with the old tutorial group

        return ttlGroupChosen;
    }
    
    public boolean compareTtlGroup(TutorialGroup oldTtlGroup, TutorialGroup newTtlGroup) {
        if (oldTtlGroup.equals(newTtlGroup)) {
            tutorialGroupUI.displaySameTtlGroupMessage(); // if both tutorial group are the same, display message and return false
            return false;
        }
        
        return true;
    }
    
    public Student getStudent() {       
        int choice;
        
        tutorialGroupUI.displayGetStudentMenu(); 
        
        do {
            choice = tutorialGroupUI.getChoice();
        } while (!Helper.choiceValidation(choice, 1, 3));
        
        Student student = null;
        TutorialGroup oldTtlGroup;
        ListInterface<Student> ttlGroupStudents;
        
        switch (choice) {
            case 1:
                student = findStudent();
                break;
            case 2:
                tutorialGroupUI.displayChooseOldTtlGroup();
                oldTtlGroup = chooseTutorialGroup(); // choose the old tutorial group
                ttlGroupStudents = studentList.filter(stud -> stud.getTutorialGroupId().equals(oldTtlGroup));
                listStudents(oldTtlGroup, false);
                
                int studentNo;
                
                do {
                    studentNo = tutorialGroupUI.getStudentChoice();
                } while (!Helper.choiceValidation(studentNo, 1, ttlGroupStudents.getSize()));
                
                student = ttlGroupStudents.get(studentNo);
                break;
            case 3: // maybe will be remove
                tutorialGroupUI.displayChooseOldTtlGroup();
                oldTtlGroup = chooseTutorialGroup(); // choose the old tutorial group
                ttlGroupStudents = studentList.filter(stud -> stud.getTutorialGroupId().equals(oldTtlGroup));
                
                IteratorInterface<Student> iterator = ttlGroupStudents.getIterator(); // place iterator
                
                int option;
                
                do {                    
                    tutorialGroupUI.displayBrowseStudentMenu(iterator.getCurrent()); // display current student
                    
                    do {
                        option = tutorialGroupUI.getChoice();
                    } while (!Helper.choiceValidation(choice, 0, 3));
                    
                    if (option == 1 && !iterator.isFirst()) { // go to previous student if there is one
                        iterator.previous();
                    } else if (option == 2 && !iterator.isLast()) { // go to next student if there is one
                        iterator.next();
                    } else if (option == 3) { // student selected
                        student = iterator.getCurrent();
                    } else {
                        tutorialGroupUI.displayInvalidChoiceMessage();
                    }                    
                } while (option != 0 && option != 3);
                break;
            default:
                tutorialGroupUI.displayInvalidChoiceMessage();
        }
        
        return student;
    }
    
    public void closeMain() {
        tutorialGroupDA.writeToFile(tutorialGroupList);
        studentDA.writeToFile(studentList);
        programmeDA.writeToFile(programmeList);
    }
    
    public static void main(String[] args) {
        TutorialGroupControl tutorialGroupControl = new TutorialGroupControl();
        tutorialGroupControl.runMain();
    }
}
