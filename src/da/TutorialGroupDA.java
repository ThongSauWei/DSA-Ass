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
 * @author User
 */
public class TutorialGroupDA {
    public ListInterface<TutorialGroup> readFromFile() {
        ListInterface<TutorialGroup> tutorialGroupList = new LinkedList<>();
        ListInterface<String> dataList = FileHandling.readFile("TutorialGroup");
        for(String data : dataList) {
            String[] attr = data.split("\\|", 1);
            
            tutorialGroupList.add(FileHandling.getTutorialGroup(attr[0]));
        }
        
        return tutorialGroupList;
    }
}
