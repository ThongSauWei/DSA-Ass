/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import entity.*;
import java.util.Scanner;
import utility.*;

/**
 *
 * @author Acer
 */
public class ProgrammeManageUI {

    public int programmeMenu() {
        System.out.println("\nPROGRAMME MENU");
        Helper.printLine('-', 30);
        System.out.println("1. Display Programme List"); //sort
        System.out.println("2. Add Programme");
        System.out.println("3. Update Programme");
        System.out.println("4. Delete Programme");
        System.out.println("5. Search Programme");  //filter
        System.out.println("6. Report");
        System.out.println("0. Exit");
        Helper.printLine('-', 30);

        return Helper.choiceValidation("Please Choose Your Option : ", 0, 6);
    }

//    public void listAllProgrammes(String outputProg) {
//        System.out.println("\nProgramme List:\n" + outputProg);
//    }
    public void listAllProgrammes(String outputProg) {
        displayMessage("\nProgramme List:\n" + outputProg);
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

//    public Programme getProgrammeInput() {
//        System.out.println("\nEnter Programme Details:");
//        String programmeCode = InputHandling.getString("Programme Code: ");
//        String programmeName = InputHandling.getString("Programme Name: ");
//        String programmeDetail = InputHandling.getString("Programme Detail: ");
//        char programmeLevel = InputHandling.getChar("Programme Level: ");
//        String faculty = InputHandling.getString("Faculty: ");
//        int duration = InputHandling.getInt("Duration (in months): ");
//
//        return new Programme(programmeCode, programmeName, programmeDetail, programmeLevel, faculty, duration);
//    }
    public Programme getProgrammeInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Programme Code: ");
        String programmeCode = scanner.nextLine();

        System.out.print("Enter Programme Name: ");
        String programmeName = scanner.nextLine();

        System.out.print("Enter Programme Detail: ");
        String programmeDetail = scanner.nextLine();

        System.out.print("Enter Programme Level: ");
        char programmeLevel = scanner.nextLine().charAt(0);

        System.out.print("Enter Programme Faculty: ");
        String programmeFaculty = scanner.nextLine();

        System.out.print("Enter Programme Duration: ");
        int programmeDuration = Integer.parseInt(scanner.nextLine()); // Read the whole line and parse as integer

        return new Programme(programmeCode, programmeName, programmeDetail, programmeLevel, programmeFaculty, programmeDuration);
    }
}
