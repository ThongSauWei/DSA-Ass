/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package da;

import adt.LinkedList;
import adt.ListInterface;
import entity.Programme;
import utility.FileHandling;

/**
 *
 * @author User
 */
public class ProgrammeDA {
    public ListInterface<Programme> readFromFile() {
        ListInterface<Programme> programmeList = new LinkedList<>();
        ListInterface<String> dataList = FileHandling.readFile("Programme");
        for(String data : dataList) {
            String[] attr = data.split("\\|", 1);
            
            programmeList.add(FileHandling.getProgramme(attr[0]));
        }
        
        return programmeList;
    }
}
