/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import boundary.MainUI;
import control.*;
import utility.Helper;
/**
 *
 * @author User
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
                    new ProgrammeControl();
                    break;
                case 2:
                    new CourseControl();
                    break;
                case 3:
                    new TutorialGroupControl();
                    break;
                case 4:
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
