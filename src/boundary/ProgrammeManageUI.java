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
 * @author Thong Sau Wei
 */
public class ProgrammeManageUI {

    public static final String ANSI_RESET = "\u001B[0m"; //reset
    public static final String ANSI_RED = "\u001B[31m"; //red
    public static final String ANSI_YELLOW = "\u001B[33m"; //yellow
    public static final String ANSI_GREEN = "\u001B[32m"; //green
    public static final String ANSI_BLUE = "\u001B[34m"; //blue

    //programme menu
    public int programmeMenu() {
        System.out.println("\n~ PROGRAMME MENU ~");
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

        int choice = InputHandling.getInt("\u001B[36mPlease Choose Your Option : " + ANSI_RESET);

        while (!Helper.choiceValidation(choice, 0, 9)) {
            choice = InputHandling.getInt("\u001B[36m\nPlease Choose Your Option : " + ANSI_RESET);
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

        formattedOutput.append("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        formattedOutput.append(String.format("%-8s | %-14s | %-50s | %-58s | %-6s | %-8s | %-15s\n", "No.", "Programme Code", "Programme Name", "Programme Details",
                "Level", "Faculty", "Duration(Year)"));
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
    public String getProgrammeCode() {
        return InputHandling.getString("Programme Code (exp. RSD): ");
    }

    //add
    public String getProgrammeName() {
        return InputHandling.getString("Programme Name: ");
    }

    public String getProgrammeDetail() {
        return InputHandling.getString("Programme Detail: ");
    }

    public String getProgrammeLevel() {
        return InputHandling.getString("Programme Level (exp. D - Diploma, R - Bachelor Degree): ");
    }

    public String getFaculty() {
        return InputHandling.getString("Faculty (exp. FOCS): ");
    }

    public int getDuration() {
        return InputHandling.getInt("Duration (in year): ");
    }
    
    public void invalidDuration() {
        System.out.println(ANSI_RED + "Please enter a valid duration (year) such as 1 year or more." + ANSI_RESET);
    }

    //add tutorial group
    public TutorialGroup addTutorialInput(Programme programmeCode) {
        String tutorialGroupId;
        int groupNo;
        int numOfStudent;

        tutorialGroupId = InputHandling.getString("Tutorial ID: ");
        groupNo = InputHandling.getInt("Group No : ");

        return new TutorialGroup(tutorialGroupId, groupNo, 0, programmeCode);
    }

    //update
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

        int choice = InputHandling.getInt("\u001B[36mPlease Choose Your Option : " + ANSI_RESET);

        while (!Helper.choiceValidation(choice, 0, 6)) {
            choice = InputHandling.getInt("\u001B[36mPlease Choose Your Option : " + ANSI_RESET);
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

        int choice = InputHandling.getInt("\u001B[36mPlease Choose Sorting Option: " + ANSI_RESET);

        while (!Helper.choiceValidation(choice, 1, 2)) {
            choice = InputHandling.getInt("\u001B[36mPlease Choose Your Option : " + ANSI_RESET);
        }

        return choice;
    }

    public int sortOptions() {
        System.out.println("Choose what type of the programme list :");
        Helper.printLine('-', 20);
        System.out.println("1. By Programme Code");
        System.out.println("2. By Faculty");
        System.out.println("3. By Name");
        System.out.println("4. By Programme Level");
        System.out.println("0. EXIT");
        Helper.printLine('-', 20);

        int choice = InputHandling.getInt("\u001B[36mPlease Choose Your Option : " + ANSI_RESET);

        while (!Helper.choiceValidation(choice, 0, 4)) {
            choice = InputHandling.getInt("\u001B[36mPlease Choose Your Option : " + ANSI_RESET);
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

        int choice = InputHandling.getInt("\u001B[36mPlease Choose Your Option : " + ANSI_RESET);

        while (!Helper.choiceValidation(choice, 0, 2)) {
            choice = InputHandling.getInt("\u001B[36mPlease Choose Your Option : " + ANSI_RESET);
        }

        return choice;
    }

    //report Faculty header
    public void reportHeader(ListInterface<String> input) {
        System.out.println(ANSI_YELLOW + "\n- Faculty Report -\n" + ANSI_RESET);
        System.out.println("Total Faculty Available - " + input.getSize());
    }

    public void reportHeaderLevel(ListInterface<Character> input) {
        System.out.println(ANSI_YELLOW + "\n- Level Report -\n" + ANSI_RESET);
        System.out.println("Total Programme Level Available - " + input.getSize());
    }

    //report highest
    public void reportHighest(String highestInput, double highestPercentage) {
        System.out.println("The highest percentage of Faculty is \u001B[36m" + highestInput + ANSI_RESET + " which have (" + String.format("%.2f", highestPercentage) + "%).\n\n");
    }

    public void reportHighestLevel(char highestInput, double highestPercentage) {
        System.out.println("The highest percentage of Programme Level is \u001B[36m" + highestInput + ANSI_RESET + " which have (" + String.format("%.2f", highestPercentage) + "%).\n\n");
    }

    //report percentage
    public void reportPercentageFaculty(int facultyNumber, String faculty, double percentage) {
        System.out.println(facultyNumber + ". " + faculty + " -> (" + String.format("%.2f", percentage) + "%)");
    }

    public void reportPercentageLevel(int levelNumber, char level, double percentage) {
        System.out.println(levelNumber + ". " + level + " -> (" + String.format("%.2f", percentage) + "%)");
    }

    //code does not exists
    public void notExists() {
        System.out.println(ANSI_RED + "Error: Programme with the given code does not exist." + ANSI_RESET);
    }

    public String checkExists(Programme programme) {
        return ANSI_RED + "Error: the Programme Code - " + programme.getProgrammeCode().toUpperCase() + " already exists.\n" + ANSI_RESET;
    }

    //not found
    public void notFound() {
        System.out.println(ANSI_RED + "Error: Programme not Found!" + ANSI_RESET);
    }

    //ttl not found
    public void ttlNotFound() {
        System.out.println(ANSI_RED + "Error: Tutorial Group not Found!" + ANSI_RESET);
    }

    //sort
    public boolean ttlComfirm(Programme programme) {
        boolean yesNo = InputHandling.getConfirmation("\u001B[36m Confirm to made changes to the tutorial group of " + programme.getProgrammeCode().toUpperCase() + " ? (Y or N): " + ANSI_RESET);
        return yesNo;
    }

    //continue
    public boolean continueInput() {
        boolean yesNo = InputHandling.getConfirmation("\u001B[36m \nDo you want to continue ? (Y or N): " + ANSI_RESET);
        InputHandling.systemPause();
        return yesNo;
    }

    //comfirm
    public boolean comfirmInput() {
        boolean yesNo = InputHandling.getConfirmation("\u001B[36m Confirm ? (Y or N): " + ANSI_RESET);
        return yesNo;
    }

    public void success() {
        System.out.println(ANSI_GREEN + "\nUpdated Data Successfully!" + ANSI_RESET);
    }

    public void unsuccess() {
        System.out.println(ANSI_YELLOW + "\nUpdated Data Unsuccessfully! Data Remain Same!" + ANSI_RESET);
    }

    //sort
    public boolean sortComfirm() {
        boolean yesNo = InputHandling.getConfirmation("\u001B[36m\nDo you want to sort the programme list? (Y or N): " + ANSI_RESET);
        return yesNo;
    }

    //invalid message
    public void invalidInput() {
        System.out.println(ANSI_RED + "Invalid input, please enter again..." + ANSI_RESET);
    }

    //thank you
    public void thankYou() {
        System.out.println("\u001B[36m	\n\nThank You ~ Have a nice day ^_^" + ANSI_RESET);
    }

}
