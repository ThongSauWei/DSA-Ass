/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;


import adt.ListInterface;
import control.CourseControl;
import control.TutorialGroupControl;
import utility.InputHandling;
import utility.Helper;

import entity.Student;
import entity.TutorialGroup;
import entity.Course;
import entity.AssignmentTeam;
import entity.AssignmentStudent;

/**
 *
 * @author Valerie
 */
public class AssignmentTeamUI {
    

    
    public int assignmentMenu(){
        System.out.println("Welcome to Assignment Team Management Subsystem!");
        Helper.printLine('*', 50);
        System.out.println("1. Create An New Assignment Team");
        System.out.println("2. Remove Assignment Team");
        System.out.println("3. Update Assignment Team Detail");
        System.out.println("4. Add A Student To An Assignment Team");
        System.out.println("5. Filter Assignment Team Based On Criteria");
        System.out.println("6. List All Assignment Team");
        System.out.println("7. List All Students In An Assignment Team");
        System.out.println("0. Back to Home Page");
        Helper.printLine('*', 50);
        
        int choice = InputHandling.getInt("Please Select Your Option > ");
        
        while(!Helper.choiceValidation(choice, 0, 9)){
            choice = InputHandling.getInt("Please Select Your Option > ");
        }
        
        return choice;
    }
    
    public int displayCriteriaMenu(){
        System.out.println("Please select to filter the assignment team...");
        System.out.println();
        System.out.println("Select for Criteria");
        Helper.printLine('*', 20);
        System.out.println("1. Course");
        System.out.println("2. Tutorial Group");
        System.out.println("0. Back to Home Page");
        Helper.printLine('*', 20);
        
        int choice = InputHandling.getInt("Please Select Your Option > ");
        
        while(!Helper.choiceValidation(choice, 0, 2)){
            choice = InputHandling.getInt("Please Select Your Option > ");
        }
        
        return choice;
    }
    
    public void displayReportMenu(){
        System.out.println("Please select a generate report ~");
        System.out.println();
        System.out.println("Generate for ...");
        Helper.printLine('*', 20);
        System.out.println("1. An Assignment Team");
        System.out.println("2. All Assignment Team in A Course");
        System.out.println("3. All Assignment Team in A Tutorial Group");
        System.out.println("0. Back to Home Page");
        Helper.printLine('*', 20);
        
    }
   
    public void displayAssignmentT(ListInterface<AssignmentTeam> assignmentList, int numOfAssignmentT){
        Helper.clearScreen();
        int num = 1;
        System.out.println("Display All Assignment Team");
        System.out.println("");
        Helper.printLine('*', 144);
        System.out.printf("%-7s %-1s %-15s %-1s %-40s %-1s %-10s %-1s %-10s %-1s %-15s %-1s %-14s %-1s\n", "No.", "*", "AssignmentTeam Id", "*", "Assignment Title", "*", "Number Of Member", "*", "Submission Week", "*", "Course", "*", "Tutorial Group", "*");
        Helper.printLine('*', 144);
        for(AssignmentTeam assignmentTeam : assignmentList){
            System.out.printf("%2d%-6s", num, ".");
            System.out.printf("%-1s %-15s %-1s %-40s %-1s %-10d %-1s %-10d %-1s %-15s %-1s %-14s %-1s\n", "*", assignmentTeam.getAssignmentTeamId(), "*", assignmentTeam.getAssignmentTitle(), "*", assignmentTeam.getNumOfMembers(), "*", assignmentTeam.getSubmissionWeek(), "*", assignmentTeam.getCourseCode().getCourseCode(), "*", assignmentTeam.getTutorialGroupId().getTutorialGroupId(), "*");//??
            num++;
        }
        Helper.printLine('*', 144);
    }
    public void displayStudent(ListInterface<Student> studentList){
        
        int num = 1;
        System.out.println("Display All Student");
        System.out.println("");
        Helper.printLine('*', 139);
        System.out.printf("%-8s %-12s %-30s %-16s %-40s %-13s %-14s\n", "No.", "Student ID", "Student Name", "Student IC", "Student Email", "Phone No", "Tutorial Group");
        Helper.printLine('*', 139);
        for(Student student : studentList){
            System.out.printf("%2d%-6s", num, ".");
            System.out.println(student);
            num++;
        }
        Helper.printLine('*', 144);
    }
    public void displayAssignmentTStud(ListInterface<AssignmentStudent> assignmentStudList){
        int num = 1;
        System.out.println("Display All Student In Assignment");
        System.out.println("");
        Helper.printLine('*', 139);
        System.out.printf("%-8s %-14s %-12s %-30s %-16s %-40s %-13s %-14s\n", "No.", "Assignment Team", "Student ID", "Student Name", "Student IC", "Student Email", "Phone No", "Tutorial Group");
        Helper.printLine('*', 139);
        for(AssignmentStudent assignmentStudent : assignmentStudList){
            System.out.printf("%2d%-6s", num, ".");
            System.out.printf("%-14s %-12s %-30s %-16s %-40s %-13s %-14s\n", assignmentStudent.getAssignmentTeamId().getAssignmentTeamId(), assignmentStudent.getStudentId().getStudentId(), assignmentStudent.getStudentId().getStudentName(), assignmentStudent.getStudentId().getStudentIc(), assignmentStudent.getStudentId().getStudentEmail(), assignmentStudent.getStudentId().getPhoneNo(), assignmentStudent.getStudentId().getTutorialGroupId().getTutorialGroupId());
            num++;
        }
        Helper.printLine('*', 144);
    }
    public void displayAssignmentFilter(ListInterface<AssignmentTeam> assignmentList){
        int num = 1;
        
        System.out.println("The AssignmentTeam That In The Course");
        Helper.printLine('*', 30);
        System.out.printf("%-7s %-15s %-40s %-10s %-10s\n", "No.", "AssignmentTeam Id", "Assignment Title", "Number Of Member", "Submission Week");
        Helper.printLine('*', 100);
        for(AssignmentTeam assignmentTeam : assignmentList){
            System.out.printf("%2d%-6s ", num, ".");
            System.out.printf("%-15s %-40s %-10s %-10s\n", assignmentTeam.getAssignmentTeamId(), assignmentTeam.getAssignmentTitle(), assignmentTeam.getNumOfMembers(), assignmentTeam.getSubmissionWeek());
            num++;
        }
        Helper.printLine('*', 100);
        
        System.out.println();
    }    
    
    public void displayCourse(Course course){
        System.out.println("Course Code > " + course.getCourseCode());
        System.out.println("Course Name > " + course.getCourseName());
        System.out.println("Course Detail > " + course.getCourseDetail());
    }
    public void displayTutorialG(TutorialGroup ttlGroup){
        System.out.println("Tutorial Group Id > " + ttlGroup.getTutorialGroupId());
        System.out.println("Total of members in Tutorial Group > " + ttlGroup.getNumOfStudent());
        System.out.println("Programme > " + ttlGroup.getProgrammeCode());
    }
    public void displayStudent(Student student) {
            System.out.println(student);
            displayStopMessage();
    }
    
    public AssignmentTeam addAssignmentTeam(Course choiceCourse, TutorialGroup choiceTtlgroup){
        
        System.out.println("Create a New Assignment Team");
        System.out.println();
        System.out.println("Enter Assignment Team Detail");
        String assignmentTeamId = InputHandling.getString("Assignment Team ID (eg:BAIT2203-1004) > ");
        String assignmentTitle = InputHandling.getString("Assignment Title > ");
        int maxMembers = InputHandling.getInt("Maximum of Assignment Members > ");
        int submissionWeek = InputHandling.getInt("Submission Week (eg:10) > ");
        System.out.println();
        Course choiceCode = new Course(choiceCourse.getCourseCode());
        TutorialGroup choiceTtlId = new TutorialGroup(choiceTtlgroup.getTutorialGroupId());
        return new AssignmentTeam(assignmentTeamId, assignmentTitle, maxMembers, submissionWeek, choiceCode, choiceTtlId);
    }
    
    public AssignmentTeam updateAssignmentT(AssignmentTeam updatedAssignmentT){
        Helper.clearScreen();
        System.out.println("Update Assignment Team");
        Helper.printLine('*', 25);
        
        String assignmentTitle;
        int numOfMember = 0;
        int submissionWeek = 0;
        
        do{
            assignmentTitle = InputHandling.getString("Update Assignment Title > ");
            if(assignmentTitle == null){
                System.out.println("Please enter again!");
            }else{
                do{
                    numOfMember = InputHandling.getInt("Update Number Of Member > ");
                    if(numOfMember == -1){
                        System.out.println("Please enter number!");
                    }else{
                        do{
                           submissionWeek = InputHandling.getInt("Update Submission Week > ");
                           if(submissionWeek == -1){
                               System.out.println("Please enter number!");
                           }else{
                               System.out.println();
                               Helper.printLine('*', 35);
                               System.out.println("Updated Assignment Team Detail");
                               Helper.printLine('*', 35);
                               System.out.println("Assignment Title > " + assignmentTitle);
                               System.out.println("Number Of Member > " + numOfMember);
                               System.out.println("Submission Week > " + submissionWeek);
                           }
                        }while(submissionWeek == -1);
                    }
                }while(numOfMember == -1);
            }
        }while(assignmentTitle == null);
        
        return new AssignmentTeam(updatedAssignmentT.getAssignmentTeamId(), assignmentTitle, numOfMember, submissionWeek, updatedAssignmentT.getCourseCode(), updatedAssignmentT.getTutorialGroupId());
    } 
    
    public void displayNoAssignmentTMes(){
        System.out.println("There is No Assignment Team Now ...");
        System.out.println();
        systemPause();
    }
    public void displayNoAssignmentTStudMes(){
        System.out.println("There is Student In Assignment ...");
        System.out.println();
        systemPause();
    }

    public void displayInvalidChoiceMessage() {
        System.out.println("Invalid Option! Please Enter Again.");
    }

    public void displayStopMessage(){
        InputHandling.systemPause();
        Helper.clearScreen();
    }

    public void displayAssignmentTeam(AssignmentTeam assignmentTeam) {
        System.out.printf("%-15s %-1s %-40s %-1s %-10d %-1s %-10d %-1s %-15s %-1s %-14s %-1s\n", assignmentTeam.getAssignmentTeamId(), "*", assignmentTeam.getAssignmentTitle(), "*", assignmentTeam.getNumOfMembers(), "*", assignmentTeam.getSubmissionWeek(), "*", assignmentTeam.getCourseCode().getCourseCode(), "*", assignmentTeam.getTutorialGroupId().getTutorialGroupId(), "*");//??
        displayStopMessage();
    }

    public void displayAssignmentTAddSuccessful() {
        System.out.println("Assignment Team Added Successfully");
    }
    public void displayAssignmentTAddUnsuccessful(){
        System.out.println("Assignment Team Added Unsuccessfully");
        System.out.println("");
        systemPause();
    }
    public void displayAssignmentTUpdatedSuccessful(){
        System.out.println("Assignment Team Updated Successfully");
    }
    public void displayAssignmentTUpdatedUnsuccessful(){
        System.out.println("Assignment Team Updated Unsuccessfully");
        System.out.println("");
        systemPause();
    }
    public void displaySameAssignmentTeamMessage() {
        System.out.println("You Cannot Choose The Same Tutorial Group! Please Choose Again.");
    }
    public void displayAssignmentTRemoveSuccessful(){
        System.out.println("Assignment Team Removed Successfully");
    }
    public void displayAssignmentTRemoveUnsuccessful(){
        System.out.println("Assignment Team Removed Unsuccessfully");
        System.out.println("");
        systemPause();
    }
    public int getCourseChoice(){
        int choice = InputHandling.getInt("Please Choice a Course > ");
        System.out.println();
        return choice;
    }
    public int getTutorialGroup(ListInterface<TutorialGroup> tutorialGroupsList){
        int choice = InputHandling.getInt("Please Choice a Tutorial Group > ");
        System.out.println();
            
        return choice;
    }
    public int getStudent(ListInterface<Student> studentList){
        int choice = InputHandling.getInt("Please Choice a Student > ");
        System.out.println();
            
        return choice;
    }
    public int getAssStudent(ListInterface<AssignmentStudent> assignmentStudList){
        int choice = InputHandling.getInt("Please Choice a Student > ");
        System.out.println();
            
        return choice;
    }
    public int getAssignmentTeamChoice(){
        int choice = InputHandling.getInt("Please Choice the Assignment Team > ");
        System.out.println();
            
        return choice;
    }
    public int getChoice(){
        int choice = InputHandling.getInt("Please Choice Your Option > ");
        System.out.println();
            
        return choice;
    }
        
    public boolean getConfirmForRemoveAssignment(AssignmentTeam assignmentTeam){
        return InputHandling.getConfirmation("Are u sure to remove " + assignmentTeam.getAssignmentTeamId() + "? (Y or N) > ");
    }
    public boolean getConfirmRemoveStud(AssignmentStudent assignmentStudent, AssignmentTeam assignmentTeam){
        return InputHandling.getConfirmation("Are u confirm to remove " + assignmentStudent.getStudentId() + "from " + assignmentTeam.getAssignmentTeamId() + "? (Y or N) > ");
    }
    public boolean getConfirmUpdateStud(AssignmentTeam assignmentTeam){
        return InputHandling.getConfirmation("Are u confirm to update " + assignmentTeam.getAssignmentTeamId() + "? (Y or N) > ");
    }
        
    public void systemPause(){
        InputHandling.systemPause();
        Helper.clearScreen();
    }
    
        
}
