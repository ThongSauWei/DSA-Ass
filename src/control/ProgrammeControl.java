/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import boundary.ProgrammeManageUI;

import entity.Programme;
import adt.ListInterface;
import da.ProgrammeDA;
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

//    private StartUp startUp;
//    public ProgrammeControl(){
//        programmeList = programmeDA.readFromFile();
//    }
//    
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
            String outputProg = "";
            for (Programme programme : programmeList) {
                outputProg += programme.toString() + "\n";
            }
            programmeUI.listAllProgrammes(outputProg);
        }
    }

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
