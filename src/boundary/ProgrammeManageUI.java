/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import adt.*;
import entity.*;
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
        System.out.println("6. Add Tutorial Group to Programme");
        System.out.println("7. Remove Tutorial Group from a Programme");
        System.out.println("8. List All Tutorial Group from a Programme");
        System.out.println("9. Report");
        System.out.println("0. Exit");
        Helper.printLine('-', 30);

        int choice = InputHandling.getInt("Please Choose Your Option : ");

        while (!Helper.choiceValidation(choice, 0, 9)) {
            choice = InputHandling.getInt("Please Choose Your Option : ");
        }

        return choice;
    }

    //list all programme
    public void listAllProgrammes(String formattedOutput) {
        displayMessage("Programme List: \n" + formattedOutput);
    }

    //formatDisplay
    public String formatProgrammeList(ListInterface<Programme> programmeList) {
        if (programmeList == null) {
            return "No programmes found.";
        }

        StringBuilder formattedOutput = new StringBuilder();

//        formattedOutput.append("\nProgramme List :\n");
        formattedOutput.append("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        formattedOutput.append(String.format("%-8s | %-14s | %-50s | %-58s | %-6s | %-8s | %-15s\n", "No.", "Programme Code", "Programme Name", "Programme Details",
                "Level", "Faculty", "Duration(Months)"));
        formattedOutput.append("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");

        int count = 1;
        for (Programme programme : programmeList) {
            formattedOutput.append(String.format("%2d%-6s | %-14s | %-50s | %-58s | %-6s | %-8s | %-15s\n", count, ".",
                    programme.getProgrammeCode(), programme.getProgrammeName(), programme.getProgrammeDetail(),
                    programme.getProgrammeLevel(), programme.getFaculty(), programme.getDuration()));
            count++;
        }

        // Calculate total and add it to the message
        int totalProgrammes = programmeList.getSize();
        formattedOutput.append("\nTotal Programmes -> ").append(totalProgrammes).append("\n");

        return formattedOutput.toString();
    }

    public void displayTtl(ListInterface<TutorialGroup> ttlList) {
        System.out.println("Tutorial Group List :");
        Helper.printLine('-', 60);
        System.out.printf("%-12s | %-8s | %-15s | %-15s\n", "Tutorial ID", "Group No", "Number of Student", "Programme Code");
        Helper.printLine('-', 60);

        for (TutorialGroup ttlgroup : ttlList) {
            System.out.printf("%-12s | %-8s | %-17s | %-15s\n", ttlgroup.getTutorialGroupId(), ttlgroup.getGroupNo(), ttlgroup.getNumOfStudent(), ttlgroup.getProgrammeCode());
        }

        System.out.println("\nTotal Tutorial Group -> " + ttlList.getSize());
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

    //add tutorial group
    public TutorialGroup addTutorialInput(Programme programmeCode) {
        String tutorialGroupId;
        int groupNo;
        int numOfStudent;

        tutorialGroupId = InputHandling.getString("Tutorial ID: ");
        groupNo = InputHandling.getInt("Group No : ");
//        numOfStudent = InputHandling.getInt("Number of Student : ");

        return new TutorialGroup(tutorialGroupId, groupNo, 0, programmeCode);
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

        String newName, newDetail, newFaculty;
        int newDuration;
        char newLevel;

        switch (updateOption) {
            case 1:
                newName = InputHandling.getString("Enter new Programme Name: ");
                updatedProgramme.setProgrammeName(newName);
                break;
            case 2:
                newDetail = InputHandling.getString("Enter new Programme Detail: ");
                updatedProgramme.setProgrammeDetail(newDetail);
                break;
            case 3:
                newLevel = InputHandling.getChar("Enter new Programme Level (D - Diploma, R - Bachelor Degree): ");
                updatedProgramme.setProgrammeLevel(newLevel);
                break;
            case 4:
                newFaculty = InputHandling.getString("Enter new Faculty: ");
                updatedProgramme.setFaculty(newFaculty);
                break;
            case 5:
                newDuration = InputHandling.getInt("Enter new Duration (in months): ");
                updatedProgramme.setDuration(newDuration);
                break;
            case 6:
                newName = InputHandling.getString("Enter new Programme Name: ");
                newDetail = InputHandling.getString("Enter new Programme Detail: ");
                newLevel = InputHandling.getChar("Enter new Programme Level (D - Diploma, R - Bachelor Degree): ");
                newFaculty = InputHandling.getString("Enter new Faculty: ");
                newDuration = InputHandling.getInt("Enter new Duration (in months): ");

                updatedProgramme.setProgrammeName(newName);
                updatedProgramme.setProgrammeDetail(newDetail);
                updatedProgramme.setProgrammeLevel(newLevel);
                updatedProgramme.setFaculty(newFaculty);
                updatedProgramme.setDuration(newDuration);
                break;
            default:
                System.out.println("Invalid choice.");
                return null;
        }
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

        int choice = InputHandling.getInt("Please Choose Your Option : ");

        while (!Helper.choiceValidation(choice, 0, 6)) {
            choice = InputHandling.getInt("Please Choose Your Option : ");
        }

        return choice;
    }

    //sort
    public int sortMenu() {
        System.out.println("\n~ SORT MENU ~");
        Helper.printLine('-', 20);
        System.out.println("1. Ascending Order");
        System.out.println("2. Descending Order");
        Helper.printLine('-', 20);

        int choice = InputHandling.getInt("Please Choose Sorting Option: ");

        while (!Helper.choiceValidation(choice, 1, 2)) {
            choice = InputHandling.getInt("Please Choose Your Option : ");
        }

        return choice;
    }

    public int sortOptions() {
        System.out.println("\nChoose what type of the programme list :");
        Helper.printLine('-', 20);
        System.out.println("1. By Programme Code");
        System.out.println("2. By Faculty");
        System.out.println("3. By Name");
        System.out.println("4. By Programme Level");
        System.out.println("0. EXIT");
        Helper.printLine('-', 20);

        int choice = InputHandling.getInt("Please Choose Your Option : ");

        while (!Helper.choiceValidation(choice, 0, 4)) {
            choice = InputHandling.getInt("Please Choose Your Option : ");
        }

        return choice;
    }

    //delete ttl
    public String getTutorialGroupIdToDelete() {
        return InputHandling.getString("Enter Tutorial Group ID to delete: ");
    }

    //search
    public String getSearchInput(String fieldLabel) {
        return InputHandling.getString("Enter " + fieldLabel + " to search: ");
    }

    //report
    public int report() {
        System.out.println("\nChoose Type Of Report :");
        Helper.printLine('-', 20);
        System.out.println("1. Faculty");
        System.out.println("2. Programme Level");
        System.out.println("0. EXIT");
        Helper.printLine('-', 20);

        int choice = InputHandling.getInt("Please Choose Your Option : ");

        while (!Helper.choiceValidation(choice, 0, 2)) {
            choice = InputHandling.getInt("Please Choose Your Option : ");
        }

        return choice;
    }
    
    //report Faculty header
    public void reportHeader(ListInterface<String> input){
        System.out.println("- Faculty Report -");
        System.out.println("Total Faculty Available - " + input.getSize());
    }
    
    public void reportHeaderLevel(ListInterface<Character> input){
        System.out.println("- Level Report -");
        System.out.println("Total Programme Level Available - " + input.getSize());
    }
    
    //report highest
    public void reportHighest(String highestInput, double highestPercentage){
        System.out.println("The highest percentage of Faculty is " + highestInput + " which have (" + String.format("%.2f", highestPercentage) + "%).\n\n");
    }
    public void reportHighestLevel(char highestInput, double highestPercentage){
        System.out.println("The highest percentage of Programme Level is " + highestInput + " which have (" + String.format("%.2f", highestPercentage) + "%).\n\n");
    }
    
    //report percentage
    public void reportPercentageFaculty(int facultyNumber, String faculty, double percentage){
        System.out.println(facultyNumber + ". " + faculty + " -> (" + String.format("%.2f", percentage) + "%)");
    }
    public void reportPercentageLevel(int levelNumber, char level, double percentage){
        System.out.println(levelNumber + ". " + level + " -> (" + String.format("%.2f", percentage) + "%)");
    }

    //code does not exists
    public void notExists() {
        System.out.println("Error: Programme with the given code does not exist.");
    }

    public String checkExists(Programme programme) {
        return "Error: the Programme Code - " + programme.getProgrammeCode().toUpperCase() + " already exists.\n";
    }

    //not found
    public void notFound() {
        System.out.println("Error: Programme not Found!");
    }

    //ttl not found
    public void ttlNotFound() {
        System.out.println("Error: Tutorial Group not Found!");
    }

    //sort
    public boolean ttlComfirm(Programme programme) {
        boolean yesNo = InputHandling.getConfirmation("Confirm to made changes to the tutorial group of " + programme.getProgrammeCode().toUpperCase() + " ? (Y or N): ");
        return yesNo;
    }

    //continue
    public boolean continueInput() {
        boolean yesNo = InputHandling.getConfirmation("\nDo you want to continue ? (Y or N): ");
        InputHandling.systemPause();
        return yesNo;
    }

    //comfirm
    public boolean comfirmInput() {
        boolean yesNo = InputHandling.getConfirmation("Confirm ? (Y or N): ");
        return yesNo;
    }

    public void success() {
        System.out.println("\nUpdated Successfully!");
    }

    public void unsuccess() {
        System.out.println("\nUpdated Unsuccessfully! Data Remain Same!");
    }

    //sort
    public boolean sortComfirm() {
        boolean yesNo = InputHandling.getConfirmation("\nDo you want to sort the programme list? (Y or N): ");
        return yesNo;
    }

    //invalid message
    public void invalidInput() {
        System.out.println("Invalid input, please enter again...");
    }

    //thank you
    public void thankYou() {
        System.out.println("\nThank You ~ Have a nice day ^_^");
    }

}
