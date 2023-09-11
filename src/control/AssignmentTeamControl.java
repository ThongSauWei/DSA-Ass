/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package control;

import adt.LinkedList;
import boundary.AssignmentTeamUI;
import adt.ListInterface;

import entity.Student;
import entity.TutorialGroup;
import entity.Course;
import entity.AssignmentTeam;
import entity.AssignmentStudent;


import da.StudentDA;
import da.TutorialGroupDA;
import da.CourseDA;
import da.AssignmentTeamDA;
import da.AssignmentStudentDA;
import utility.Helper;
import utility.InputHandling;

/**
 *
 * @author User
 */


public class AssignmentTeamControl {
    
    private StudentDA studentDA = new StudentDA();
    private TutorialGroupDA tutorialGDA = new TutorialGroupDA();
    private CourseDA courseDA = new CourseDA();
    private AssignmentTeamDA assignmentDA = new AssignmentTeamDA();
    private AssignmentStudentDA assignmentStudDA = new AssignmentStudentDA();
      

    private ListInterface<Student> studentList;
    private ListInterface<AssignmentTeam> assignmentList;
    private ListInterface<Course> courseList;
    private ListInterface<TutorialGroup> tutorialGList;
    private ListInterface<AssignmentStudent> assignmentStudList;
    
    private AssignmentTeamUI assignmentTeamUI = new AssignmentTeamUI();

    public AssignmentTeamControl() {
        studentList = studentDA.readFromFile();
        assignmentList = assignmentDA.readFromFile();
        courseList = courseDA.readFromFile();
        tutorialGList = tutorialGDA.readFromFile();
        
        runMain();
    }

    public void runMain() {
        int choice;

        do {
            choice = assignmentTeamUI.assignmentMenu();

            switch (choice) {
                case 1:
                    createTeam();
                    break;
                case 2:
                    removeTeam();
                    break;
                case 3:
                    updateTeam();
                    break;
                case 4:
                    addStudToTeam();
                    break;
                case 5:
                    removeStudFromTeam();
                    break;
                case 6:
                    filterTeam();
                    break;
                case 7:
                    listTeam();
                    break;
                case 8:
                    listStudTeam();
                    break;
                case 9:
                    generateReport();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid Option! Please Enter Again.");
            }
        } while (choice != 0);

        closeMain();
    }
    
    public void createTeam() {

        Course choiceCourse = chooseCourse();
        TutorialGroup choiceTtlgroup = chooseTutorialG();
        
        Helper.clearScreen();
        AssignmentTeam newAssignmentTeam = assignmentTeamUI.addAssignmentTeam(choiceCourse, choiceTtlgroup);
        
        boolean idRepeated = false;
        for(AssignmentTeam assignmentTeam : assignmentList){
            if(assignmentTeam.getAssignmentTeamId().equals(newAssignmentTeam.getAssignmentTeamId())){
                idRepeated = true;
                break;
            }  
        }
        
        if(!idRepeated){
            assignmentTeamUI.displayAssignmentTAddSuccessful();
            assignmentTeamUI.displayAssignmentTeam(newAssignmentTeam);
            
            assignmentList.add(newAssignmentTeam);
            assignmentDA.writeToFile(assignmentList);
        }else{
            assignmentTeamUI.systemPause();
        }  
            
    }
        
    
    public void removeTeam() {
        AssignmentTeam choiceAssignmentT = chooseAssignmentTeam();
        
        if(assignmentTeamUI.getConfirmForRemoveAssignment(choiceAssignmentT)){
            assignmentTeamUI.displayAssignmentTRemoveSuccessful();
            
            assignmentList.remove(choiceAssignmentT);
            assignmentDA.writeToFile(assignmentList);
        }else{
            assignmentTeamUI.displayAssignmentTRemoveUnsuccessful();
        }
    }

    public void updateTeam() {
        AssignmentTeam choiceAssignmentT = chooseAssignmentTeam();
        AssignmentTeam updateTeam = assignmentTeamUI.updateAssignmentT(choiceAssignmentT);
        boolean confirm = assignmentTeamUI.getConfirmUpdateStud(updateTeam);
        int num = 1;
        if(confirm && updateTeam != null){
            for(AssignmentTeam updateT : assignmentList){
                if(updateT.getAssignmentTeamId().equals(updateTeam.getAssignmentTeamId())){
                    assignmentList.replace(updateTeam, num);
                    assignmentDA.writeToFile(assignmentList);
                    assignmentTeamUI.displayAssignmentTUpdatedSuccessful();
                    break;
                }
                num++;               
            }
        }else{
            assignmentTeamUI.displayAssignmentTUpdatedUnsuccessful();
        }
    }

    public void addStudToTeam() { // student cannot convern to assignment team
        Student choiceStudent = chooseStudent();
        AssignmentTeam choiceAssignmentT = chooseAssignmentTeam();
        
        boolean studentExist = false;
        for (AssignmentStudent assignmentStudent : assignmentStudList){
            if(assignmentStudent.getStudentId().equals(choiceStudent) && assignmentStudent.getAssignmentTeamId().equals(choiceAssignmentT)){
                studentExist = true;
                break;
            }
        }
        
        /*if(!studentExist){
            AssignmentStudent assignmentStud = new AssignmentStudent(choiceStudent, choiceAssignmentT);
            //tutorial groupde wen ti bu neng work
            
        }*/
    }
    
    public void removeStudFromTeam() {
        AssignmentTeam choiceAssignmentT = chooseAssignmentTeam();
        AssignmentStudent choiceStud = chooseAssStudent();
        
        assignmentList = new AssignmentTeamDA().readFromFile();
        boolean removeList = assignmentList.remove(choiceAssignmentT);
        boolean removeTeam = false;
        if(removeList){
            if(assignmentTeamUI.getConfirmRemoveStud(choiceStud, choiceAssignmentT)){
                assignmentDA.writeToFile(assignmentList);
                assignmentTeamUI.displayAssignmentTRemoveSuccessful();
                for(AssignmentStudent assignmentStud : assignmentStudList){
                    if(assignmentStud.getAssignmentTeamId().equals(choiceAssignmentT) && assignmentStud.getStudentId().equals(choiceStud)){
                        if(assignmentStudList.remove(assignmentStud));
                        removeTeam = true;
                        break;
                    }
                }
            }else
                assignmentTeamUI.displayAssignmentTRemoveUnsuccessful();
            }
        InputHandling.systemPause();
    }
    
    public void filterTeam() {
        int choice;
        assignmentTeamUI.displayCriteriaMenu();
        
        do{
            choice = assignmentTeamUI.getChoice();
        }while (!Helper.choiceValidation(choice, 0, 2));
        
        
        switch(choice){
            case 1:
                Course course = chooseCourse();
                
                listAssignmentTeam(assignmentList.filter(assignmentTeam -> assignmentTeam.getCourseCode().equals(course)));
                break;
            case 2:
                TutorialGroup ttlgroup = chooseTutorialG();
                
                listAssignmentTeam(assignmentList.filter(assignmentTeam -> assignmentTeam.getTutorialGroupId().equals(ttlgroup)));
                break;
            default:
                assignmentTeamUI.displayInvalidChoiceMessage();
        
        }
    }
    
    public void listTeam() { 
        int numOfAssignmentT = assignmentList.getSize();
       
        if(!assignmentList.isEmpty()){
            assignmentTeamUI.displayAssignmentT(assignmentList, numOfAssignmentT);//??
        }else
            assignmentTeamUI.displayNoAssignmentTMes();
    }

    public void generateReport() {
        int choice;
        
        assignmentTeamUI.assignmentMenu();
        
        do{
            choice = assignmentTeamUI.getChoice();
        }while (!Helper.choiceValidation(choice, 0, 3));
        
        switch (choice) {
            case 1: // generate report for all assignment team
                for(AssignmentTeam assTeam : assignmentList){
                    assignmentTeamUI.displayCourse(assTeam.getCourseCode());
                    assignmentTeamUI.displayAssignmentTeam(assTeam);
                    listStudTeam();
                }
                break;
            case 2: // generate report for all the assignment team in a tutorial group
                TutorialGroup ttlGroupList = null;
                
                ListInterface<AssignmentTeam> ttlAssignmentTeam = new LinkedList<>();
                
                do{
                    TutorialGroup ttlChoice = chooseTutorialG();
                    ttlGroupList = ttlChoice;
                    
                    ttlAssignmentTeam = assignmentList.filter(assignmentTeam -> assignmentTeam.getTutorialGroupId().equals(ttlChoice));
                    
                    if(ttlAssignmentTeam.isEmpty()){
                        assignmentTeamUI.displayNoAssignmentTMes();
                    }
                }while(ttlAssignmentTeam.isEmpty());
                
                assignmentTeamUI.displayTutorialG(ttlGroupList);
                
                for(AssignmentTeam assTeam : ttlAssignmentTeam){
                    assignmentTeamUI.displayAssignmentTeam(assTeam);
                    listStudTeam();
                }
                
            /*case 3: // generate report for all the assignment team in a course
                Course courseList = null;
                
                ListInterface<Course> courseAssignmentTeam = new LinkedList<>();
                
                do{
                    Course courseChoice = chooseCourse();
                    courseList = courseChoice;
                    
                    courseAssignmentTeam = assignmentList.filter(assignmentTeam -> assignmentTeam.getCourseCode().equals(courseChoice));
                    //assignment team cannot convern to course
                    if(courseAssignmentTeam.isEmpty()){
                        assignmentTeamUI.displayNoAssignmentTMes();
                    }
                }while(courseAssignmentTeam.isEmpty());
                
                assignmentTeamUI.displayCourse(courseList);
                
                for(AssignmentTeam assTeam : courseAssignmentTeam){
                    assignmentTeamUI.displayAssignmentTeam(assTeam);
                    listStudTeam();
                }*/
            default:
                assignmentTeamUI.displayInvalidChoiceMessage();
                
        }
    }
    
    
    public void listStudTeam() {
        if(!assignmentStudList.isEmpty()){
            assignmentTeamUI.displayAssignmentTStud(assignmentStudList);
        }else
            assignmentTeamUI.displayNoAssignmentTStudMes();
    }
    public void listAssignmentTeam(ListInterface<AssignmentTeam> assignmentList) {
        if (!assignmentList.isEmpty()) {
            assignmentTeamUI.displayAssignmentFilter(assignmentList);
        } else {
            assignmentTeamUI.displayNoAssignmentTMes();
        }
    }
    
    public Course chooseCourse() { 
        new CourseControl().displayCourse(); // list all the programmes ***UI listCourse 
        int choice = assignmentTeamUI.getCourseChoice(); //***UI getCourseChoice
        
        return courseList.get(choice);
    }
    public TutorialGroup chooseTutorialG(){
        new TutorialGroupControl().listTutorialGroups(tutorialGList);
        int choice = assignmentTeamUI.getTutorialGroup(tutorialGList);
     
        return tutorialGList.get(choice);
    }
    public Student chooseStudent(){
        assignmentTeamUI.displayStudent(studentList);
        int choice = assignmentTeamUI.getStudent(studentList);
        return studentList.get(choice);
     
    }
    public AssignmentStudent chooseAssStudent(){
        assignmentTeamUI.displayAssignmentTStud(assignmentStudList);
        int choice = assignmentTeamUI.getAssStudent(assignmentStudList);
        return assignmentStudList.get(choice);
     
    }
    public AssignmentTeam chooseAssignmentTeam(){
        listTeam();
        int choice = assignmentTeamUI.getAssignmentTeamChoice();
        
        return assignmentList.get(choice);
    }
    
    public boolean compareAssignmentTeam(AssignmentTeam oldAssignmentTeam, AssignmentTeam newAssignmentTeam) {
        if (oldAssignmentTeam.equals(newAssignmentTeam)) {
            assignmentTeamUI.displaySameAssignmentTeamMessage(); // if both tutorial group are the same, display message and return false ***UI displaySameAssignmentTeamMessage
            return false;
        }
        
        return true;
    }
    
    public void closeMain() {
        tutorialGDA.writeToFile(tutorialGList);
        studentDA.writeToFile(studentList);
        courseDA.writeToFile(courseList);
        assignmentDA.writeToFile(assignmentList);
    }
    
    public static void main(String args[]){
        AssignmentTeamControl assignmentTeamControl = new AssignmentTeamControl();
        assignmentTeamControl.runMain();
    }
    /*   public class AssignmentTeamControl {
        private AssignmentTeamDA assignmentDA = new AssignmentTeamDA();
        public ListInterface<AssignmentTeam> readFromFile() {
            return assignmentDA.readFromFile();
    }
    
    public void writeToFile(ListInterface<AssignmentTeam> assignmentTeamList) {
        assignmentDA.writeToFile(assignmentTeamList);
    }
}
    */
 
    /*public AssignmentTeam chooseAssignmentTeam(AssignmentTeam oldAssignmentTeam) {
        ListInterface<AssignmentTeam> courseAssignmentTeam = new LinkedList<>();
        
        do {
            Course course = chooseCourse();

            // filter the list so that the tutorial group in the programme only will be listed
            courseAssignmentTeam = assignmentList.filter(assignmentTeam -> assignmentTeam.getCourseCode().equals(course));

            if (courseAssignmentTeam.isEmpty()) {
                assignmentTeamUI.displayNoTtlGroupMessage(); //*** UI displayNoTtlGroupMessage
            }

        } while (courseAssignmentTeam.isEmpty());

        AssignmentTeam assignmentTeamChosen = null;

        do {
            int choice = assignmentTeamUI.getAssignmentTeamChoice(courseAssignmentTeam.getSize()); //*** UI getAssignmentTeamChoice

            assignmentTeamChosen = courseAssignmentTeam.get(choice);

        } while (oldAssignmentTeam != null && !compareAssignmentTeam(oldAssignmentTeam, assignmentTeamChosen)); // if there is old tutorial group, the selected tutorial group cannot be same with the old tutorial group

        return assignmentTeamChosen;
    }
*/
    
    
}



