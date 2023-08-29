/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.*;
import boundary.ProgrammeManageUI;

import entity.Programme;
import adt.ListInterface;
import da.ProgrammeDA;
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

    //programme list
    private ListInterface<Programme> programmeList;

    //boundary
    private ProgrammeManageUI programmeUI = new ProgrammeManageUI();

    public void runProgramme() {
        programmeList = programmeDA.readFromFile();

        int choice;
        do {
            choice = programmeUI.programmeMenu();
            switch (choice) {
                case 0:
                    // ...
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
                default:
                // ...
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
            if (InputHandling.getConfirmation("Do you want to sort the programme list? (Y or N): ")) {
                int sortOption = programmeUI.sortOptions();
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
        System.out.println("\nEnter Programme Details:");

        Programme newProgrammeCode;
        do {
            newProgrammeCode = programmeUI.checkProgrammeCode();
            String programmeCode = InputHandling.getString("Programme Code (exp. RSD): ");

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
    }

    //update
    public void updateProgramme() {
        System.out.println("\nEnter Programme Code to Update:");

        Programme existingProgramme;

        Programme programmeCode = programmeUI.checkProgrammeCode();

        if (!programmeExists(programmeCode.getProgrammeCode())) {
            System.out.println("Error: Programme with the given code does not exist.");
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
        updateProgrammeData(existingProgramme, updateOption);
    }

    private Programme getProgrammeByCode(String programmeCode) {
        for (Programme programme : programmeList) {
            if (programme.getProgrammeCode().equals(programmeCode)) {
                return programme;
            }
        }
        return null;
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
        System.out.println("\nEnter Programme Code to Delete:");

        Programme existingProgramme;

        Programme programmeCode = programmeUI.checkProgrammeCode();

        if (!programmeExists(programmeCode.getProgrammeCode())) {
            System.out.println("Error: Programme with the given code does not exist.");
            return; // Exit the function if programme doesn't exist
        }

        existingProgramme = getProgrammeByCode(programmeCode.getProgrammeCode().toUpperCase());

        // list selected program for formatting
        LinkedList<Programme> selectedProgramList = new LinkedList<>();
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
                System.out.println("\nError: Programme not found in the list.");
            }
        } else {
            System.out.println("\nProgramme deleted unsuccessfully.");
        }

        formattedOutput = programmeUI.formatProgrammeList(programmeList);
        programmeUI.listAllProgrammes(formattedOutput);
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
