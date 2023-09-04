/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package da;

import adt.LinkedList;
import adt.ListInterface;
import entity.TutorialGroup;
import utility.FileHandling;

/**
 *
 * @author Benjamin
 */
public class TutorialGroupDA {
    public ListInterface<TutorialGroup> readFromFile() {
        ListInterface<TutorialGroup> tutorialGroupList = new LinkedList<>();
        ListInterface<String> dataList = FileHandling.readFile("TutorialGroup");
        for(String data : dataList) {
            String[] attr = data.split("\\|");
            
            tutorialGroupList.add(FileHandling.getTutorialGroup(attr[0]));
        }
        
        return tutorialGroupList;
    }
    
    public void writeToFile(ListInterface<TutorialGroup> tutorialGroupList) {
        ListInterface<String> dataList = new LinkedList<>();
        for (TutorialGroup tutorialGroup : tutorialGroupList) {
            dataList.add(tutorialGroup.saveToFile());
        }
        
        FileHandling.writeFile("TutorialGroup", dataList);
    }
}
