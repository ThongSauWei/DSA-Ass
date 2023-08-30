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
import java.util.Iterator;
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
                    System.out.println("\n Thank You ~ Have a nice day ^_^");
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
//                System.out.println("\nError: Programme not found in the list.");
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
//            System.out.println("No programme found!");
            programmeUI.notFound();
        }
    }

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

            Programme existingProgramme = getProgrammeByCode(newProgrammeCode.getProgrammeCode());
            TutorialGroup newTutorial = programmeUI.addTutorialInput(newProgrammeCode);

            if (newTutorial != null) {
                    if (InputHandling.getConfirmation("Confirm to add this tutorial to " + newProgrammeCode.getProgrammeCode().toUpperCase() + " ? (Y or N): ")) {
                        ttlList.add(newTutorial);
                        ttlDA.writeToFile(ttlList);
                        System.out.println("\nTutorial Group added successfully!");
                    } else {
                        System.out.println("\nTutorial Group addition cancelled. The Tutorial Group List remains the same.");
                    }

                    programmeUI.displayTtl(ttlList, newProgrammeCode.getProgrammeCode());
            }

            yesNo = programmeUI.continueInput();
        } while (yesNo);
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
