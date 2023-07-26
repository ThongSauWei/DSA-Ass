/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import entity.CourseProgramme;
import adt.ListInterface;
import da.CourseProgrammeDA;
/**
 *
 * @author User
 */
public class CourseProgrammeControl {
    private CourseProgrammeDA courseProgrammeDA = new CourseProgrammeDA();
    public ListInterface<CourseProgramme> readFromFile() {
        return courseProgrammeDA.readFromFile();
    }
    
    public void writeToFile(ListInterface<CourseProgramme> courseProgrammeList) {
        courseProgrammeDA.writeToFile(courseProgrammeList);
    }
}
