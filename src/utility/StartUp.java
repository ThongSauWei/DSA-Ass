/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.bin to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import da.*;
import entity.*;
import adt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
/**
 *
 * @author Benjamin, Erika, Thong, Valerie
 */
public class StartUp {
    public static void startUp() { // In case the related file is not open yet
        FileHandling.createOrOpenFile("AssignmentTeam.bin");
        FileHandling.createOrOpenFile("Course.bin");
        FileHandling.createOrOpenFile("CourseProgramme.bin");
        FileHandling.createOrOpenFile("Programme.bin");
        FileHandling.createOrOpenFile("Student.bin");
        FileHandling.createOrOpenFile("TutorialGroup.bin");
        FileHandling.createOrOpenFile("AssignmentStudent.bin");
    }
    
    public static void init() { // Used to create data if the file does not have data
        // initialise programme file
        ListInterface<Programme> programmeList = new LinkedList<>();
        Programme programme1 = new Programme("RSD", "Bachelor in Software System Development", "Year Two Sem One Sibeehhhh Busy.", 'R', "FOCS", 3);
        Programme programme2 = new Programme("DFT", "Diploma in Information Technology", "Diploma Is Used For Playing!!!", 'D', "FOCS", 2);
        Programme programme3 = new Programme("FCP", "Foundation in Computing Track A", "Foundation Not To Say One. No Experience.", 'F', "FOCS", 1);
        
        programmeList.add(programme1);
        programmeList.add(programme2);
        programmeList.add(programme3);
        
        new ProgrammeDA().writeToFile(programmeList);
        
        // initialise course file
        ListInterface<Course> courseList = new LinkedList<>();
        Course course1 = null;
        Course course2 = null;
        Course course3 = null;
        try {
            course1 = new Course("BACS2063", "Data Structure and Algorithms", "It's All About ADTs.", new SimpleDateFormat("yyyy-MM-dd").parse("2023-07-02"), new SimpleDateFormat("yyyy-MM-dd").parse("2023-10-10"));
            course2 = new Course("BAIT2203", "Human Computer Interaction", "It's All About Laws.", new SimpleDateFormat("yyyy-MM-dd").parse("2023-07-02"), new SimpleDateFormat("yyyy-MM-dd").parse("2023-10-10"));
            course3 = new Course("AACS3253", "Cloud Computing for Business", "Lapsap Course.", new SimpleDateFormat("yyyy-MM-dd").parse("2023-07-02"), new SimpleDateFormat("yyyy-MM-dd").parse("2023-10-10"));
        } catch (ParseException ex) {
            ExceptionHandling.dateParseException();
        }
        
        courseList.add(course1);
        courseList.add(course2);
        courseList.add(course3);
        
        new CourseDA().writeToFile(courseList);
        
        // initialise course programme file
        ListInterface<CourseProgramme> courseProgrammeList = new LinkedList<>();
        CourseProgramme courseProgramme1 = new CourseProgramme(programme1, course1);
        CourseProgramme courseProgramme2 = new CourseProgramme(programme1, course2);
        CourseProgramme courseProgramme3 = new CourseProgramme(programme1, course3);
        CourseProgramme courseProgramme4 = new CourseProgramme(programme2, course3);
        
        courseProgrammeList.add(courseProgramme1);
        courseProgrammeList.add(courseProgramme2);
        courseProgrammeList.add(courseProgramme3);
        courseProgrammeList.add(courseProgramme4);
        
        new CourseProgrammeDA().writeToFile(courseProgrammeList);
        
        // initialise tutorial group file
        ListInterface<TutorialGroup> tutorialGroupList = new LinkedList<>();
        TutorialGroup tutorialGroup1 = new TutorialGroup("RSD2S1G1", 1, 3, programme1);
        TutorialGroup tutorialGroup2 = new TutorialGroup("RSD2S1G2", 2, 2, programme1);
        TutorialGroup tutorialGroup3 = new TutorialGroup("RSD2S1G3", 3, 2, programme1);
        TutorialGroup tutorialGroup4 = new TutorialGroup("RSD2S1G4", 4, 1, programme1);
        
        tutorialGroupList.add(tutorialGroup1);
        tutorialGroupList.add(tutorialGroup2);
        tutorialGroupList.add(tutorialGroup3);
        tutorialGroupList.add(tutorialGroup4);
        
        new TutorialGroupDA().writeToFile(tutorialGroupList);
        
        // initialise student file
        ListInterface<Student> studentList = new LinkedList<>();
        Student student1 = new Student("23WMR00001", "Ali", "010101-01-0101", "ali@student.tarc.edu.my", "0123456789", 2, 1, tutorialGroup1);
        Student student2 = new Student("23WMR00002", "Bali", "020202-02-0202", "bali@student.tarc.edu.my", "0134567892", 2, 1, tutorialGroup1);
        Student student3 = new Student("23WMR00003", "Cali", "030303-03-0303", "cali@student.tarc.edu.my", "0145678923", 2, 1, tutorialGroup1);
        Student student4 = new Student("23WMR00004", "Dali", "010203-01-0203", "dali@student.tarc.edu.my", "0156789234", 2, 1, tutorialGroup2);
        Student student5 = new Student("23WMR00005", "Eali", "030201-03-0201", "eali@student.tarc.edu.my", "0167892345", 2, 1, tutorialGroup2);
        Student student6 = new Student("23WMR00006", "Fali", "010203-03-0201", "fali@student.tarc.edu.my", "0178923456", 2, 1, tutorialGroup3);
        Student student7 = new Student("23WMR00007", "Gali", "030203-02-0302", "gali@student.tarc.edu.my", "0189234567", 2, 1, tutorialGroup3);
        Student student8 = new Student("23WMR00008", "Hali", "020302-03-0203", "hali@student.tarc.edu.my", "0192345678", 2, 1, tutorialGroup4);
        
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        studentList.add(student4);
        studentList.add(student5);
        studentList.add(student6);
        studentList.add(student7);
        studentList.add(student8);
        
        new StudentDA().writeToFile(studentList);
        
        // initialise tutorial group file
        ListInterface<AssignmentTeam> assignmentTeamList = new LinkedList<>();
        AssignmentTeam assignmentTeam1 = new AssignmentTeam("BACS2063-1001", course1, tutorialGroup1);
        AssignmentTeam assignmentTeam2 = new AssignmentTeam("BACS2063-1002", course1, tutorialGroup1);
        AssignmentTeam assignmentTeam3 = new AssignmentTeam("BACS2063-1003", course1, tutorialGroup2);
        AssignmentTeam assignmentTeam4 = new AssignmentTeam("BACS2063-1004", course1, tutorialGroup3);
        AssignmentTeam assignmentTeam5 = new AssignmentTeam("BACS2063-1005", course1, tutorialGroup4);
        
        assignmentTeamList.add(assignmentTeam1);
        assignmentTeamList.add(assignmentTeam2);
        assignmentTeamList.add(assignmentTeam3);
        assignmentTeamList.add(assignmentTeam4);
        assignmentTeamList.add(assignmentTeam5);
        
        new AssignmentTeamDA().writeToFile(assignmentTeamList);
        
        // initialise tutorial group file
        ListInterface<AssignmentStudent> assignmentStudentList = new LinkedList<>();
        AssignmentStudent assignmentStudent1 = new AssignmentStudent(assignmentTeam1, student1);
        AssignmentStudent assignmentStudent2 = new AssignmentStudent(assignmentTeam1, student2);
        AssignmentStudent assignmentStudent3 = new AssignmentStudent(assignmentTeam2, student3);
        AssignmentStudent assignmentStudent4 = new AssignmentStudent(assignmentTeam3, student4);
        AssignmentStudent assignmentStudent5 = new AssignmentStudent(assignmentTeam3, student5);
        AssignmentStudent assignmentStudent6 = new AssignmentStudent(assignmentTeam4, student6);
        AssignmentStudent assignmentStudent7 = new AssignmentStudent(assignmentTeam4, student7);
        AssignmentStudent assignmentStudent8 = new AssignmentStudent(assignmentTeam5, student8);
        
        assignmentStudentList.add(assignmentStudent1);
        assignmentStudentList.add(assignmentStudent2);
        assignmentStudentList.add(assignmentStudent3);
        assignmentStudentList.add(assignmentStudent4);
        assignmentStudentList.add(assignmentStudent5);
        assignmentStudentList.add(assignmentStudent6);
        assignmentStudentList.add(assignmentStudent7);
        assignmentStudentList.add(assignmentStudent8);
        
        new AssignmentStudentDA().writeToFile(assignmentStudentList);
    }
    
    public static void main(String[] args) {
        StartUp.startUp();
        StartUp.init();
    }
}
