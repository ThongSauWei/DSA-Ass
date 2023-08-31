/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.*;
import boundary.ProgrammeManageUI;

import entity.*;
import adt.ListInterface;
import da.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Predicate;
import utility.*;

/**
 *
 * @author User
 */
public class ProgrammeControl {

    //da
    private ProgrammeDA programmeDA = new ProgrammeDA();
    private TutorialGroupDA ttlDA = new TutorialGroupDA();

    //list
    private ListInterface<Programme> programmeList;
    private ListInterface<TutorialGroup> ttlList = new LinkedList<>();

    //boundary
    private ProgrammeManageUI programmeUI = new ProgrammeManageUI();

    public void runProgramme() {
        programmeList = programmeDA.readFromFile();
        ttlList = ttlDA.readFromFile();

        int choice;
        do {
            choice = programmeUI.programmeMenu();
            switch (choice) {
                case 0:
                    System.out.println("\nThank You ~ Have a nice day ^_^");
                    break;
                case 1:
                    listProgramme();
                    break;
                case 2:
                    addProgramme();
                    break;
                case 3:
                    updateProgramme();
                    break;
                case 4:
                    deleteProgramme();
                    break;
                case 5:
                    searchProgramme();
                    break;
                case 6:
                    addTutorialGroup();
                    break;
                case 7:
                    removeTutorialGroup();
                    break;
                case 8:
                    listTtl();
                    break;
                case 9:
                    generateReport();
                    break;
                default:
                    programmeUI.invalidInput();
                    break;
            }
        } while (choice != 0);
    }

    //display programme
    public void listProgramme() {
        programmeList = programmeDA.readFromFile();

        if (programmeList.isEmpty()) {
            programmeUI.displayMessage("No programmes found.");
        } else {
            String formattedOutput = programmeUI.formatProgrammeList(programmeList);
            programmeUI.listAllProgrammes(formattedOutput);

            // Ask user if they want to sort the output
            if (InputHandling.getConfirmation("\nDo you want to sort the programme list? (Y or N): ")) {
                int sortOption = programmeUI.sortOptions();
                if (sortOption == 0) {
                    return;
                }
                int sortChoice = programmeUI.sortMenu();

                if (sortChoice == 1) {
                    sortProgrammeListAscending(sortOption);
                } else if (sortChoice == 2) {
                    sortProgrammeListDescending(sortOption);
                } else if (sortChoice != 3) {
                    System.out.println("Invalid choice.");
                }

                formattedOutput = programmeUI.formatProgrammeList(programmeList);
                programmeUI.listAllProgrammes(formattedOutput);
            }

        }
    }

    //sort
    private void sortProgrammeListAscending(int sortOption) {
        LinkedList<Programme> sortedList = new LinkedList<>();

        for (int i = 1; i <= programmeList.getSize(); i++) {
            Programme currentProgramme = programmeList.get(i);
            insertSorted(sortedList, sortOption, currentProgramme, true);
        }

        programmeList = sortedList;
    }

    private void sortProgrammeListDescending(int sortOption) {
        LinkedList<Programme> sortedList = new LinkedList<>();

        for (int i = 1; i <= programmeList.getSize(); i++) {
            Programme currentProgramme = programmeList.get(i);
            insertSorted(sortedList, sortOption, currentProgramme, false);
        }

        programmeList = sortedList;
    }

    private void insertSorted(LinkedList<Programme> list, int option, Programme programme, boolean ascending) {
        int position = 1;

        // Find the position to insert the current programme
        while (position <= list.getSize()) {
            Programme current = list.get(position);

            int comparison = 0;
            if (option == 1) {
                comparison = programme.getProgrammeCode().compareTo(current.getProgrammeCode());
            } else if (option == 2) {
                comparison = programme.getFaculty().compareTo(current.getFaculty());
            } else if (option == 3) {
                comparison = programme.getProgrammeName().compareTo(current.getProgrammeName());
            } else if (option == 4) {
                comparison = programme.getProgrammeLevel().compareTo(current.getProgrammeLevel());
            }

            if ((ascending && comparison < 0) || (!ascending && comparison > 0)) {
                list.add(programme, position);
                return;
            }

            position++;
        }

        // If not inserted yet, add it to the end~
        list.add(programme);
    }

    //add
    public void addProgramme() {
        boolean yesNo;
        do {
            System.out.println("\nEnter Programme Details to Add :");

            Programme newProgrammeCode;
            do {
                newProgrammeCode = programmeUI.checkProgrammeCode();
//                String programmeCode = InputHandling.getString("Programme Code (exp. RSD): ");

                if (programmeExists(newProgrammeCode.getProgrammeCode())) {
                    System.out.println("Error: the Programme Code - " + newProgrammeCode.getProgrammeCode().toUpperCase() + " already exists.");
                }
            } while (programmeExists(newProgrammeCode.getProgrammeCode()));

            Programme newProgramme = programmeUI.addProgrammeInput(newProgrammeCode);

            if (newProgramme != null) {
                if (InputHandling.getConfirmation("Confirm to add this programme? (Y or N): ")) {
                    programmeList.add(newProgramme);
                    programmeDA.writeToFile(programmeList);
                    System.out.println("\nProgramme added successfully!");
                } else {
                    System.out.println("\nProgramme added unsuccessfully. The Programme List remain same !");
                }
                // Display 
                String formattedOutput = programmeUI.formatProgrammeList(programmeList);
                programmeUI.listAllProgrammes(formattedOutput);
            }
            yesNo = programmeUI.continueInput();
        } while (yesNo == true);
    }

    //update
    public void updateProgramme() {
        boolean yesNo;
        do {
            System.out.println("\nEnter Programme Code to Update:");

            Programme existingProgramme;

            Programme programmeCode = programmeUI.checkProgrammeCode();

            if (!programmeExists(programmeCode.getProgrammeCode())) {
//            System.out.println("Error: Programme with the given code does not exist.");
                programmeUI.notExists();
                return; // Exit the function if programme doesn't exist
            }

//        ListInterface<Programme> programme1 = programmeList.filter(programme -> programme.getProgrammeCode().equals(programmeCode.getProgrammeCode().toUpperCase()));
//        Programme prog1 = FileHandling.getProgramme(primaryKey);
            existingProgramme = getProgrammeByCode(programmeCode.getProgrammeCode().toUpperCase());

            // list selected program for formatting
            LinkedList<Programme> selectedProgramList = new LinkedList<>();
            selectedProgramList.add(existingProgramme);

            String formattedOutput = programmeUI.formatProgrammeList(selectedProgramList);
            programmeUI.listAllProgrammes(formattedOutput); //display current - code

            int updateOption = programmeUI.updateProgrammeMenu();
            if (updateOption == 0) {
                return;
            } else {
                updateProgrammeData(existingProgramme, updateOption);
            }

            yesNo = programmeUI.continueInput();
        } while (yesNo == true);

    }

    private void updateProgrammeData(Programme programme, int updateOption) {
        Programme updatedProgramme = programmeUI.updateProgrammeInput(programme, updateOption);

        if (updatedProgramme != null) {
            //list
            LinkedList<Programme> updateList = new LinkedList<>();
            updateList.add(updatedProgramme);

            String formattedOutput = programmeUI.formatProgrammeList(updateList);
            programmeUI.listAllProgrammes(formattedOutput); //display current - code

            if (InputHandling.getConfirmation("Confirm to update this programme? (Y or N): ")) {
                int index = getIndexByProgrammeCode(updatedProgramme.getProgrammeCode());
                if (index != -1) {
                    Programme existingProgramme = programmeList.get(index);
                    updateProgrammeProperties(existingProgramme, updatedProgramme);
                    programmeDA.writeToFile(programmeList);
                    System.out.println("\nProgramme updated successfully!");
                } else {
                    System.out.println("\nError: Programme not found in the list.");
                }
            } else {
                System.out.println("\nProgramme update cancelled.");
            }

            formattedOutput = programmeUI.formatProgrammeList(programmeList);
            programmeUI.listAllProgrammes(formattedOutput);
        }
    }

    private void updateProgrammeProperties(Programme existingProgramme, Programme updatedProgramme) {
        existingProgramme.setProgrammeName(updatedProgramme.getProgrammeName());
        existingProgramme.setProgrammeDetail(updatedProgramme.getProgrammeDetail());
        existingProgramme.setProgrammeLevel(updatedProgramme.getProgrammeLevel());
        existingProgramme.setFaculty(updatedProgramme.getFaculty());
        existingProgramme.setDuration(updatedProgramme.getDuration());
    }

    private int getIndexByProgrammeCode(String programmeCode) {
        for (int i = 1; i <= programmeList.getSize(); i++) {
            Programme existingProgramme = programmeList.get(i);
            if (existingProgramme.getProgrammeCode().equals(programmeCode)) {
                return i;
            }
        }
        return -1; // not found
    }

    //delete
    public void deleteProgramme() {
        boolean yesNo;
        do {
            System.out.println("\nEnter Programme Code to Delete:");
            Programme existingProgramme;

            Programme programmeCode = programmeUI.checkProgrammeCode();

            if (!programmeExists(programmeCode.getProgrammeCode().toUpperCase())) {
//            System.out.println("Error: Programme with the given code does not exist.");
                programmeUI.notExists();
                return; // Exit the function if programme doesn't exist
            }

            existingProgramme = getProgrammeByCode(programmeCode.getProgrammeCode().toUpperCase());

            // list selected program for formatting
            LinkedList<Programme> selectedProgramList = new LinkedList<>();
//        ListInterface<Programme> programme1 = programmeList.filter(programme -> programme.getProgrammeCode().equals(programmeCode.getProgrammeCode().toUpperCase()));
            selectedProgramList.add(existingProgramme);

            String formattedOutput = programmeUI.formatProgrammeList(selectedProgramList);
            programmeUI.listAllProgrammes(formattedOutput); //display current - code

            if (InputHandling.getConfirmation("Confirm to delete this programme? (Y or N): ")) {
                int index = getIndexByProgrammeCode(existingProgramme.getProgrammeCode());
                if (index != -1) {
                    programmeList.remove(index);
                    programmeDA.writeToFile(programmeList);
                    System.out.println("\nProgramme deleted successfully!");
                } else {
                    programmeUI.notFound();
                }
            } else {
                System.out.println("\nProgramme deleted unsuccessfully.");
            }

            formattedOutput = programmeUI.formatProgrammeList(programmeList);
            programmeUI.listAllProgrammes(formattedOutput);

            yesNo = programmeUI.continueInput();
        } while (yesNo == true);
    }

    //find
    public void searchProgramme() {
        ListInterface<Programme> programmeList = programmeDA.readFromFile();
        int searchOption = programmeUI.sortOptions();

        switch (searchOption) {
            case 1:
                searchByCriteria(programmeList, "Programme Code");
                break;
            case 2:
                searchByCriteria(programmeList, "Faculty");
                break;
            case 3:
                searchByCriteria(programmeList, "Programme Name");
                break;
            case 4:
                searchByCriteria(programmeList, "Programme Level");
                break;
            case 0:
                System.out.println("Returning to the main menu.");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private void searchByCriteria(ListInterface<Programme> programmeList, String fieldLabel) {
        String targetValue = InputHandling.getString("Enter " + fieldLabel + " to search: ");
        LinkedList<Programme> results = new LinkedList<>();
        boolean matches = false;

        for (Programme programme : programmeList) {
            String fieldValue = "";

            switch (fieldLabel) {
                case "Programme Code":
                    fieldValue = programme.getProgrammeCode();
                    break;
                case "Faculty":
                    fieldValue = programme.getFaculty();
                    break;
                case "Programme Name":
                    fieldValue = programme.getProgrammeName();
                    break;
                case "Programme Level":
                    fieldValue = programme.getProgrammeLevel() + "";
                    break;
            }

            if (fieldValue.equalsIgnoreCase(targetValue)) {
                results.add(programme);
                matches = true; // At least one match found
            }
        }

        if (!results.isEmpty()) {
            String formattedOutput = programmeUI.formatProgrammeList(results);
            programmeUI.listAllProgrammes(formattedOutput);
        } else if (!matches) {
            programmeUI.notFound();
        }
    }

    //tutorial group
    //add tutorial group to programme
    public void addTutorialGroup() {
        boolean yesNo;
        do {
            System.out.println("\nAdd Tutorial Group to Programme :");

            Programme newProgrammeCode;
            do {
                newProgrammeCode = programmeUI.checkProgrammeCode(); // Get programme code

                if (!programmeExists(newProgrammeCode.getProgrammeCode())) {
                    programmeUI.notExists();
                }
            } while (!programmeExists(newProgrammeCode.getProgrammeCode()));

            Programme existingProgramme = getProgrammeByCode(newProgrammeCode.getProgrammeCode().toUpperCase());
            TutorialGroup newTutorial = programmeUI.addTutorialInput(newProgrammeCode);

            if (newTutorial != null) {
                if (InputHandling.getConfirmation("Confirm to add this tutorial to " + newProgrammeCode.getProgrammeCode().toUpperCase() + " ? (Y or N): ")) {
                    ttlList.add(newTutorial);
                    ttlDA.writeToFile(ttlList);
                    System.out.println("Tutorial Group added successfully!\n");
                    displayTutorialGroups(existingProgramme);
                } else {
                    System.out.println("Tutorial Group addition cancelled. The Tutorial Group List remains the same.\n");
                    programmeUI.displayTtl(ttlList);
                }

            }

            yesNo = programmeUI.continueInput();
        } while (yesNo);
    }

    //delete
    public void removeTutorialGroup() {
        boolean continueRemoval;
        do {
            System.out.println("\nRemove Tutorial Group from Programme:");

            Programme programmeCode = programmeUI.checkProgrammeCode();

            if (!programmeExists(programmeCode.getProgrammeCode().toUpperCase())) {
                programmeUI.notExists();
            } else {
                Programme existingProgramme = getProgrammeByCode(programmeCode.getProgrammeCode().toUpperCase());
                displayTutorialGroups(existingProgramme);

                String tutorialGroupId = programmeUI.getTutorialGroupIdToDelete();

                TutorialGroup tutorialGroupToRemove = getTutorialGroupById(tutorialGroupId, existingProgramme);

                if (tutorialGroupToRemove != null) {
                    if (InputHandling.getConfirmation("Confirm to remove this tutorial group? (Y or N): ")) {
                        int indexToRemove = getIndexByTutorialGroup(tutorialGroupToRemove);
                        if (indexToRemove != -1) {
                            ttlList.remove(indexToRemove);
                            ttlDA.writeToFile(ttlList);
                            System.out.println("\nTutorial Group removed successfully!");
                        } else {
                            System.out.println("\nError: Tutorial Group not found in the list.");
                        }
                    } else {
                        System.out.println("\nTutorial Group removal cancelled.");
                    }
                    programmeUI.displayTtl(ttlList);
                } else {
                    System.out.println("\nTutorial Group not found in the selected programme.");
                }
            }

            continueRemoval = programmeUI.continueInput();
        } while (continueRemoval);
    }

    //display ttl - code
    public void listTtl() {
        if (ttlList.isEmpty()) {
            programmeUI.displayMessage("No Tutorial Group found.");
        } else {
            System.out.println("\nDisplay Tutorial Group from Programme:");

            Programme programmeCode = programmeUI.checkProgrammeCode();

            if (!programmeExists(programmeCode.getProgrammeCode().toUpperCase())) {
                programmeUI.notExists();
            } else {
                Programme existingProgramme = getProgrammeByCode(programmeCode.getProgrammeCode().toUpperCase());
                displayTutorialGroups(existingProgramme);
            }

        }
    }

    //report 
    public void generateReport() {
        int reportOption = programmeUI.report();
        switch (reportOption) {
            case 0:
                return;
            case 1:
                generateFacultyReport(programmeList);
                break;
            case 2:
                generateLevelReport(programmeList);
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    public void generateFacultyReport(ListInterface<Programme> programmeList) {
        Map<String, Integer> facultyCounts = new HashMap<>();
        int totalProgrammes = programmeList.getSize();

        for (Programme programme : programmeList) {
            facultyCounts.put(programme.getFaculty(), facultyCounts.getOrDefault(programme.getFaculty(), 0) + 1);
        }

        String highestFaculty = null;
        double highestPercentage = 0;

        System.out.println("- Faculty Report -");
        System.out.println("Total Faculty Available - " + facultyCounts.size());

        int facultyNumber = 1;
        for (String faculty : facultyCounts.keySet()) {
            int count = facultyCounts.get(faculty);
            double percentage = (count * 100.0) / totalProgrammes;

            if (percentage > highestPercentage) {
                highestPercentage = percentage;
                highestFaculty = faculty;
            }

            System.out.println(facultyNumber + ". " + faculty + " - (" + String.format("%.2f", percentage) + "%)");
            facultyNumber++;
        }

        if (highestFaculty != null) {
            System.out.println("The highest percentage of faculty is " + highestFaculty + " which have (" + String.format("%.2f", highestPercentage) + "%)");
        } else {
            System.out.println("No faculty information available.");
        }

        // Display 
        System.out.println("\nProgramme Lists:");
        facultyNumber = 1;
        for (String faculty : facultyCounts.keySet()) {
            System.out.println(facultyNumber + ". " + faculty);
            ListInterface<Programme> programmesInFaculty = filterProgrammesByFaculty(programmeList, faculty);
            String formattedOutput = programmeUI.formatProgrammeList(programmesInFaculty);
            System.out.println(formattedOutput);
            facultyNumber++;
        }
    }

    private ListInterface<Programme> filterProgrammesByFaculty(ListInterface<Programme> programmeList, String faculty) {
        Predicate<Programme> facultyFilter = programme -> programme.getFaculty().equalsIgnoreCase(faculty);
        return programmeList.filter(facultyFilter);
    }

    public void generateLevelReport(ListInterface<Programme> programmeList) {
        Map<Character, Integer> levelCounts = new HashMap<>();
        int totalProgrammes = programmeList.getSize();

        for (Programme programme : programmeList) {
            levelCounts.put(programme.getProgrammeLevel(), levelCounts.getOrDefault(programme.getProgrammeLevel(), 0) + 1);
        }

        Character highestLevel = null;
        double highestPercentage = 0;

        System.out.println("- Programme Level Report -");
        System.out.println("Total Programme Levels Available - " + levelCounts.size());

        int levelNumber = 1;
        for (Character level : levelCounts.keySet()) {
            int count = levelCounts.get(level);
            double percentage = (count * 100.0) / totalProgrammes;

            if (percentage > highestPercentage) {
                highestPercentage = percentage;
                highestLevel = level;
            }

            System.out.println(levelNumber + ". " + level + " - (" + String.format("%.2f", percentage) + "%)");
            levelNumber++;
        }

        if (highestLevel != null) {
            System.out.println("The highest percentage of programme level is " + highestLevel + " which have (" + String.format("%.2f", highestPercentage) + "%)");
        } else {
            System.out.println("No programme level information available.");
        }

        // Display
        System.out.println("\nProgramme Lists:");
        levelNumber = 1;
        for (Character level : levelCounts.keySet()) {
            System.out.println(levelNumber + ". " + level);
            Predicate<Programme> levelFilter = programme -> programme.getProgrammeLevel() == level;
            ListInterface<Programme> programmesInLevel = programmeList.filter(levelFilter);
            String formattedOutput = programmeUI.formatProgrammeList(programmesInLevel);
            System.out.println(formattedOutput);
            levelNumber++;
        }
    }

    private void displayTutorialGroups(Programme programme) {
        LinkedList<TutorialGroup> tutorialGroupsInProgramme = new LinkedList<>();
        for (TutorialGroup tutorialGroup : ttlList) {
            if (tutorialGroup.getProgrammeCode().equals(programme)) {
                tutorialGroupsInProgramme.add(tutorialGroup);
            }
        }

        if (tutorialGroupsInProgramme.isEmpty()) {
            System.out.println("No tutorial groups found in the selected programme.");
        } else {
            programmeUI.displayTtl(tutorialGroupsInProgramme);
        }
    }

    private int getIndexByTutorialGroup(TutorialGroup tutorialGroup) {
        for (int i = 1; i <= ttlList.getSize(); i++) {
            TutorialGroup existingTutorialGroup = ttlList.get(i);
            if (existingTutorialGroup.equals(tutorialGroup)) {
                return i;
            }
        }
        return -1;
    }

    private TutorialGroup getTutorialGroupById(String tutorialGroupId, Programme programme) {
        for (TutorialGroup tutorialGroup : ttlList) {
            if (tutorialGroup.getTutorialGroupId().equals(tutorialGroupId) && tutorialGroup.getProgrammeCode().equals(programme)) {
                return tutorialGroup;
            }
        }
        return null;
    }

    //get the specific programme
    private Programme getProgrammeByCode(String programmeCode) {
        for (Programme programme : programmeList) {
            if (programme.getProgrammeCode().equals(programmeCode)) {
                return programme;
            }
        }
        return null;
    }

    //check code exists
    private boolean programmeExists(String programmeCode) {
        String upCode = programmeCode.toUpperCase();
//        ListInterface<Programme> programme1 = programmeList.filter(programme -> programme.getProgrammeCode().equals(upCode));
        for (Programme existingProgramme : programmeList) {
            if (existingProgramme.getProgrammeCode().equals(upCode)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        ProgrammeControl progControl = new ProgrammeControl();
        progControl.runProgramme();
    }

//    public ListInterface<Programme> readFromFile() {
//        return programmeDA.readFromFile();
//    }
//
//    public void writeToFile(ListInterface<Programme> programmeList) {
//        programmeDA.writeToFile(programmeList);
//    }
}
