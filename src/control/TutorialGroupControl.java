/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import entity.TutorialGroup;
import adt.ListInterface;
import da.TutorialGroupDA;
/**
 *
 * @author User
 */
public class TutorialGroupControl {
    private TutorialGroupDA tutorialGroupDA = new TutorialGroupDA();
    public ListInterface<TutorialGroup> readFromFile() {
        return tutorialGroupDA.readFromFile();
    }
    
    public void writeToFile(ListInterface<TutorialGroup> tutorialGroupList) {
        tutorialGroupDA.writeToFile(tutorialGroupList);
    }
}
