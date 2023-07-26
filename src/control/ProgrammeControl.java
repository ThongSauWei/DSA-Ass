/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import entity.Programme;
import adt.ListInterface;
import da.ProgrammeDA;
/**
 *
 * @author User
 */
public class ProgrammeControl {
    private ProgrammeDA programmeDA = new ProgrammeDA();
    public ListInterface<Programme> readFromFile() {
        return programmeDA.readFromFile();
    }
    
    public void writeToFile(ListInterface<Programme> programmeList) {
        programmeDA.writeToFile(programmeList);
    }
}
