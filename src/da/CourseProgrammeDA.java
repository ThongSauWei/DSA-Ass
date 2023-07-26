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
        ListInterface<String> dataList = FileHandling.readFile("CourseProgramme");
        for(String data : dataList) {
            String[] attr = data.split("\\|", 1);
            
            courseProgrammeList.add(FileHandling.getCourseProgramme(attr[0]));
        }
        
        return courseProgrammeList;
    }
    
    public void writeToFile(ListInterface<CourseProgramme> courseProgrammeList) {
        ListInterface<String> dataList = new LinkedList<>();
        for (CourseProgramme courseProgramme : courseProgrammeList) {
            dataList.add(courseProgramme.saveToFile());
        }
        
        FileHandling.writeFile("CourseProgramme", dataList);
    }
}
