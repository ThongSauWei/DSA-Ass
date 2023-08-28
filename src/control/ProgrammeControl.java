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
        int choice;
//        startUp.startUp();
        do {
            choice = programmeUI.programmeMenu();
            switch (choice) {
                case 0:
//                    listProgramme();
                    break;
                case 1:
                    listProgramme();
                    break;
                case 2:
                    addProgramme();
//                    productUI.listAllProducts(getAllProducts());
                    break;
                default:
//                    MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice != 0);
    }

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
//            int option = 1;
            int comparison = 0;
            if(option == 1){
                comparison = programme.getProgrammeCode().compareTo(current.getProgrammeCode());
            }else if(option == 2){
                comparison = programme.getFaculty().compareTo(current.getFaculty());
            }else if(option == 3){
                comparison = programme.getProgrammeName().compareTo(current.getProgrammeName());
            }
            

//            int comparison = programme.getProgrammeCode().compareTo(current.getProgrammeCode());

            if ((ascending && comparison < 0) || (!ascending && comparison > 0)) {
                list.add(programme, position);
                return;
            }

            position++;
        }

        // If not inserted yet, add it to the end
        list.add(programme);
    }

    //add
    public void addProgramme() {
        Programme newProgramme = programmeUI.getProgrammeInput();
        programmeList.add(newProgramme);
        programmeDA.writeToFile(programmeList); // Update the file after adding
        System.out.println("Programme added successfully!");
    }

    public static void main(String[] args) {
        ProgrammeControl progControl = new ProgrammeControl();
        progControl.runProgramme();
    }

//    public void displayProgramme() {
//        listProgramme();
//    }
//    public ListInterface<Programme> readFromFile() {
//        return programmeDA.readFromFile();
//    }
//
//    public void writeToFile(ListInterface<Programme> programmeList) {
//        programmeDA.writeToFile(programmeList);
//    }
}
