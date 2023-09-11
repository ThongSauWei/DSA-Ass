/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import boundary.MainUI;
import utility.Helper;
/**
 *
 * @author Benjamin
 */
public class MainControl {
    private MainUI mainUI = new MainUI();
    
    public MainControl() {
        int choice;
        
        do {
            Helper.clearScreen();
            mainUI.displayMenu();
            
            do {
                choice = mainUI.getChoice();
            } while (!Helper.choiceValidation(choice, 0, 4));
            
            switch (choice) {
                case 1:
                    new ProgrammeControl().runProgramme();
                    break;
                case 2:
                    new CourseControl().courseMenu();
                    break;
                case 3:
                    new TutorialGroupControl().runMain();
                    break;
                case 4:
                    new AssignmentTeamControl().runMain();
                    break;
                case 0:
                    break;
                default:
                    mainUI.displayInvalidChoiceMessage();
            }
        } while (choice != 0);
    }
    
    public static void main(String[] args) {
        new MainControl();
    }
}
