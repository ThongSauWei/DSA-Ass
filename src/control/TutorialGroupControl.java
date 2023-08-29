/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import entity.Programme;
import entity.Student;
import entity.TutorialGroup;

import da.TutorialGroupDA;
import da.StudentDA;
import da.ProgrammeDA;

import boundary.TutorialGroupUI;

import adt.ListInterface;
import adt.IteratorInterface;

import utility.*;

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
    }
    
    public void runMain() {
        int choice;
        
        do {
            choice = tutorialGroupUI.displayMenu();
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
                    tutorialGroupUI.displayInvalidChoiceMessage();
            }
        } while (choice != 0);
        
        closeMain();
    }
    
    public void addStudent() { // completed
        TutorialGroup ttlGroup = chooseTutorialGroup();
        Student newStudent = tutorialGroupUI.addStudentDetail(ttlGroup);
        boolean idRepeated = false;
        
        for(Student student : studentList) {
            if (student.getStudentId().equals(newStudent.getStudentId())) {
                idRepeated = true;
                break;
            }
        }
        
        if (!idRepeated) {
            studentList.add(newStudent);
            studentDA.writeToFile(studentList);
            
            ttlGroup.setNumOfStudent(ttlGroup.getNumOfStudent() + 1);
            tutorialGroupDA.writeToFile(tutorialGroupList);
        }
    }
    
    public void removeStudent() { // completed
        TutorialGroup ttlGroup = chooseTutorialGroup();
        ListInterface<Student> ttlGroupStudents = studentList.filter(student -> student.getTutorialGroupId().equals(ttlGroup));
        
        Student studentRemove = getStudent(ttlGroupStudents);
        
        int count = 1;
        for (Student student : studentList) {
            if (student.equals(studentRemove)) {
                break;
            }
            
            count++;
        }
        
        studentList.remove(count);        
        studentDA.writeToFile(studentList);
        
        ttlGroup.setNumOfStudent(ttlGroup.getNumOfStudent() - 1);
        tutorialGroupDA.writeToFile(tutorialGroupList);
    }
    
    public void changeTutorialGroup() { // completed
        TutorialGroup oldTtlGroup = chooseTutorialGroup();
        ListInterface<Student> oldTtlGroupStudents = studentList.filter(student -> student.getTutorialGroupId().equals(oldTtlGroup));
        
        Student studentChange = getStudent(oldTtlGroupStudents);
        
        TutorialGroup newTtlGroup = chooseTutorialGroup(oldTtlGroup);
        
        if (tutorialGroupUI.confirmForGroupChanging(oldTtlGroup, newTtlGroup)) {
            studentChange.setTutorialGroupId(newTtlGroup);
            newTtlGroup.setNumOfStudent(newTtlGroup.getNumOfStudent() + 1);
            oldTtlGroup.setNumOfStudent(oldTtlGroup.getNumOfStudent() - 1);
            tutorialGroupDA.writeToFile(tutorialGroupList);
        }
    }
    
    public Student findStudent() { // completed
        TutorialGroup ttlGroup = chooseTutorialGroup();
        
        String id = tutorialGroupUI.getIdInput();
        
        for(Student student : studentList) {
            if (student.getStudentId().equals(id)) {
                tutorialGroupUI.displayStudent(student);
                return student;
            }
        }
        
        tutorialGroupUI.displayStudentNotFoundMessage();
        
        return null;
    }
    
    public void listStudents() { // completed
        TutorialGroup ttlGroup = chooseTutorialGroup();
        ListInterface<Student> ttlGroupStudents = studentList.filter(student -> student.getTutorialGroupId().equals(ttlGroup));
        
        tutorialGroupUI.listStudents(ttlGroupStudents);
    }
    
    public void filterTutorialGroups() {
        
    }
    
    public void generateReports() {
        
    }
    
    public Programme chooseProgramme() {
        tutorialGroupUI.listProgrammes(programmeList);
        
        int choice = tutorialGroupUI.getProgrammeChoice(programmeList.getSize());
        
        return programmeList.get(choice);
    }
    
    public TutorialGroup chooseTutorialGroup() {
        return chooseTutorialGroup(null);
    }
    
    public TutorialGroup chooseTutorialGroup(TutorialGroup oldTtlGroup) {       
        Programme programme = chooseProgramme();
        ListInterface<TutorialGroup> programmeTtlGroups = tutorialGroupList.filter(tutorialGroup -> tutorialGroup.getProgrammeCode().equals(programme));
        TutorialGroup ttlGroupChosen = null;

        do {
            int choice = tutorialGroupUI.getTutorialGroupChoice(programmeTtlGroups.getSize());

            ttlGroupChosen = programmeTtlGroups.get(choice);

        } while (compareTtlGroup(oldTtlGroup, ttlGroupChosen));
        
        return ttlGroupChosen;
    }
    
    public boolean compareTtlGroup(TutorialGroup oldTtlGroup, TutorialGroup newTtlGroup) {
        if (oldTtlGroup.equals(newTtlGroup)) {
            tutorialGroupUI.displaySameTtlGroupMessage();
            return false;
        }
        
        return true;
    }
    
    public Student getStudent(ListInterface<Student> ttlGroupStudents) {
        
        Student student = null;
        
        int choice = tutorialGroupUI.getStudentMenu(); 
        
        switch (choice) {
            case 1:
                student = findStudent();
                break;
            case 2:
                listStudents();
                int studentNo = tutorialGroupUI.getStudentChoice(ttlGroupStudents.getSize());
                student = ttlGroupStudents.get(studentNo);
                break;
            case 3: // maybe will be remove
                IteratorInterface<Student> iterator = ttlGroupStudents.getIterator();
                
                int option;
                
                do {                    
                    option = tutorialGroupUI.browseStudentMenu(iterator.getCurrent());
                    
                    if (option == 1 && !iterator.isFirst()) {
                        iterator.previous();
                    } else if (option == 2 && !iterator.isLast()) {
                        iterator.next();
                    } else if (option == 3) {
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
