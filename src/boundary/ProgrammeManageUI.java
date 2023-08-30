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

    //programme menu
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

    //list all programme
    public void listAllProgrammes(String formattedOutput) {
        displayMessage("\nProgramme List:\n" + formattedOutput);
    }

    //formatDisplay - any format also can der
    public String formatProgrammeList(ListInterface<Programme> programmeList) {
        if (programmeList == null) {
            return "No programmes found.";
        }

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

        // Calculate total and add it to the message
        int totalProgrammes = programmeList.getSize();
        formattedOutput.append("\nTotal Programmes -> ").append(totalProgrammes);

        return formattedOutput.toString();
    }

    //for display the message out
    public void displayMessage(String message) {
        System.out.println(message);
    }

    //check programme code
    public Programme checkProgrammeCode() {
        String programmeCode;
        do {

            programmeCode = InputHandling.getString("Programme Code (exp. RSD): ");
            if (!programmeCode.matches("[A-Za-z]{3}")) {
                invalidInput();
            }
        } while (!programmeCode.matches("[A-Za-z]{3}"));
        return new Programme(programmeCode);
    }

    //add
    public Programme addProgrammeInput(Programme programmeCode) {
        String programmeName;
        String programmeDetail;
        char programmeLevel;
        String faculty;
        int duration;

        programmeName = InputHandling.getString("Programme Name: ");

        do {
            programmeDetail = InputHandling.getString("Programme Detail: ");
            if (programmeDetail.trim().isEmpty()) {
                invalidInput();
            }
        } while (programmeDetail.trim().isEmpty());

        do {
            programmeLevel = InputHandling.getChar("Programme Level (exp. D - Diploma, R - Bachelor Degree): ");
            if (!Character.isLetter(programmeLevel) || Character.isWhitespace(programmeLevel)) {
                invalidInput();
            }
        } while (!Character.isLetter(programmeLevel) || Character.isWhitespace(programmeLevel));

        do {
            faculty = InputHandling.getString("Faculty (exp.FOCS): ");
            if (!faculty.matches("[A-Za-z]{4}")) {
                invalidInput();
            }
        } while (!faculty.matches("[A-Za-z]{4}"));

        duration = InputHandling.getInt("Duration (in months): ");

        return new Programme(programmeCode.getProgrammeCode().toUpperCase(), programmeName, programmeDetail, Character.toUpperCase(programmeLevel), faculty.toUpperCase(), duration);
    }

    //invalid message
    private void invalidInput() {
        System.out.println("Invalid input, please enter again...");
    }

    //update
    public Programme updateProgrammeInput(Programme existingProgramme, int updateOption) {
        Programme updatedProgramme = new Programme(); // Create a new instance for updating

        // Copy existing data to the new instance
        updatedProgramme.setProgrammeCode(existingProgramme.getProgrammeCode());
        updatedProgramme.setProgrammeName(existingProgramme.getProgrammeName());
        updatedProgramme.setProgrammeDetail(existingProgramme.getProgrammeDetail());
        updatedProgramme.setProgrammeLevel(existingProgramme.getProgrammeLevel());
        updatedProgramme.setFaculty(existingProgramme.getFaculty());
        updatedProgramme.setDuration(existingProgramme.getDuration());

        switch (updateOption) {
            case 1:
                String newName = InputHandling.getString("Enter new Programme Name: ");
                updatedProgramme.setProgrammeName(newName);
                break;
            case 2:
                String newDetail = InputHandling.getString("Enter new Programme Detail: ");
                updatedProgramme.setProgrammeDetail(newDetail);
                break;
            case 3:
                char newLevel = InputHandling.getChar("Enter new Programme Level (D - Diploma, R - Bachelor Degree): ");
                updatedProgramme.setProgrammeLevel(newLevel);
                break;
            case 4:
                String newFaculty = InputHandling.getString("Enter new Faculty: ");
                updatedProgramme.setFaculty(newFaculty);
                break;
            case 5:
                int newDuration = InputHandling.getInt("Enter new Duration (in months): ");
                updatedProgramme.setDuration(newDuration);
                break;
            default:
                System.out.println("Invalid choice.");
                return null;
        }

//        System.out.println("\nUpdated Programme Details:");
//        System.out.println(updatedProgramme);
        return updatedProgramme;
    }

    public int updateProgrammeMenu() {
        System.out.println("\nUpdate Programme Menu:");
        Helper.printLine('-', 30);
        System.out.println("1. Update Programme Name");
        System.out.println("2. Update Programme Detail");
        System.out.println("3. Update Programme Level");
        System.out.println("4. Update Faculty");
        System.out.println("5. Update Duration");
        System.out.println("6. Update All Details");
        System.out.println("0. EXIT");
        Helper.printLine('-', 30);

        return InputHandling.choiceValidation("Please choose an option: ", 0, 6);
    }

    //sort
    public int sortMenu() {
        System.out.println("\n~ SORT MENU ~");
        Helper.printLine('-', 20);
        System.out.println("1. Ascending Order");
        System.out.println("2. Descending Order");
        Helper.printLine('-', 20);

        return InputHandling.choiceValidation("Please Choose Sorting Option: ", 1, 2);
    }

    public int sortOptions() {
        System.out.println("\nChoose how to sort the programme list:");
        Helper.printLine('-', 20);
        System.out.println("1. By Programme Code");
        System.out.println("2. By Faculty");
        System.out.println("3. By Name");
        System.out.println("4. By Programme Level");
        System.out.println("0. EXIT");
        Helper.printLine('-', 20);
        return InputHandling.choiceValidation("Please Choose Option: ", 0, 4);
    }

}
