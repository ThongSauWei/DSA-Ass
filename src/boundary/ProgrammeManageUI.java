/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import adt.*;
import entity.*;
import java.util.Scanner;
import utility.*;

/**
 *
 * @author Acer
 */
public class ProgrammeManageUI {

    public int programmeMenu() {
        System.out.println("\n~PROGRAMME MENU~");
        Helper.printLine('-', 30);
        System.out.println("1. Display Programme List"); //sort
        System.out.println("2. Add Programme");
        System.out.println("3. Update Programme");
        System.out.println("4. Delete Programme");
        System.out.println("5. Search Programme");  //filter
        System.out.println("6. Report");
        System.out.println("0. Exit");
        Helper.printLine('-', 30);

        return InputHandling.choiceValidation("Please Choose Your Option : ", 0, 6);
    }

    public void listAllProgrammes(String formattedOutput) {
        displayMessage("\nProgramme List:\n" + formattedOutput);
    }

    //formatDisplay - any format also can der
    public String formatProgrammeList(ListInterface<Programme> programmeList) {
        StringBuilder formattedOutput = new StringBuilder();

        formattedOutput.append("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        formattedOutput.append(String.format("%-14s | %-50s | %-58s | %-6s | %-8s | %-15s\n", "Programme Code", "Programme Name", "Programme Details",
                "Level", "Faculty", "Duration(Months)"));
        formattedOutput.append("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");

        for (Programme programme : programmeList) {
            formattedOutput.append(String.format("%-14s | %-50s | %-58s | %-6s | %-8s | %-15s\n",
                    programme.getProgrammeCode(), programme.getProgrammeName(), programme.getProgrammeDetail(),
                    programme.getProgrammeLevel(), programme.getFaculty(), programme.getDuration()));
        }

        return formattedOutput.toString();
    }

    //for display the message out
    public void displayMessage(String message) {
        System.out.println(message);
    }

    //add
    public Programme getProgrammeInput() {
        System.out.println("\nEnter Programme Details:");
        String programmeCode = InputHandling.getString("Programme Code: ");
        String programmeName = InputHandling.getString("Programme Name: ");
        String programmeDetail = InputHandling.getString("Programme Detail: ");
        char programmeLevel = InputHandling.getChar("Programme Level: ");
        String faculty = InputHandling.getString("Faculty: ");
        int duration = InputHandling.getInt("Duration (in months): ");

        return new Programme(programmeCode, programmeName, programmeDetail, programmeLevel, faculty, duration);
    }

    public int sortMenu() {
        System.out.println("\n~SORT MENU ~");
        Helper.printLine('-', 20);
        System.out.println("1. Ascending Order");
        System.out.println("2. Descending Order");
        System.out.println("0. EXIT");
        Helper.printLine('-', 20);

        return InputHandling.choiceValidation("Please Choose Sorting Option: ", 0, 2);
    }
    
    public int sortOptions() {
        System.out.println("\nChoose how to sort the programme list:");
        Helper.printLine('-', 20);
        System.out.println("1. By Programme Code");
        System.out.println("2. By Faculty");
        System.out.println("3. By Name");
        System.out.println("0. EXIT");
        Helper.printLine('-', 20);
        return InputHandling.choiceValidation("Please Choose Option: ", 0, 3);
    }

}
