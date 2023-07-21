/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package da;

import adt.LinkedList;
import adt.ListInterface;
import entity.CourseProgramme;
import utility.FileHandling;

/**
 *
 * @author User
 */
public class CourseProgrammeDA {
    public ListInterface<CourseProgramme> readFromFile() {
        ListInterface<CourseProgramme> courseProgrammeList = new LinkedList<>();
        ListInterface<String> dataList = FileHandling.readFile("AssignmentTeam");
        for(String data : dataList) {
            String[] attr = data.split("\\|");
            
            courseProgrammeList.add(new CourseProgramme(attr[0], attr[1], attr[2]));
        }
        
        return courseProgrammeList;
    }
}
