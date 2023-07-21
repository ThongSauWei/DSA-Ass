/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package da;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import adt.LinkedList;
import adt.ListInterface;
import entity.Programme;
import utility.ExceptionHandling;
import utility.FileHandling;

/**
 *
 * @author User
 */
public class ProgrammeDA {
    public ListInterface<Programme> readFromFile() {
        ListInterface<Programme> programmeList = new LinkedList<>();
        ListInterface<String> dataList = FileHandling.readFile("AssignmentTeam");
        for(String data : dataList) {
            String[] attr = data.split("\\|");

            try {
                programmeList.add(new Programme(attr[0], attr[1], attr[2], new SimpleDateFormat("yyyy-MM-dd").parse(attr[3]),
                        new SimpleDateFormat("yyyy-MM-dd").parse(attr[4]), Integer.parseInt(attr[5])));
            } catch (ParseException ex) {
                ExceptionHandling.dateParseException();
            }
        }
        
        return programmeList;
    }
}
