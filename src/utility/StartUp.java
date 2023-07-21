/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

/**
 *
 * @author User
 */
public class StartUp {
    public static void startUp() {
        FileHandling.createOrOpenFile("AssignmentTeam.txt");
        FileHandling.createOrOpenFile("Course.txt");
        FileHandling.createOrOpenFile("CourseProgramme.txt");
        FileHandling.createOrOpenFile("Programme.txt");
        FileHandling.createOrOpenFile("Student.txt");
        FileHandling.createOrOpenFile("TutorialGroup.txt");
    }
}
