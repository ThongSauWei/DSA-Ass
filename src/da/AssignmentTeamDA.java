/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package da;

import adt.LinkedList;
import adt.ListInterface;
import entity.AssignmentTeam;
import utility.FileHandling;
/**
 *
 * @author User
 */
public class AssignmentTeamDA {
    public ListInterface<AssignmentTeam> readFromFile() {
        ListInterface<AssignmentTeam> assignmentTeamList = new LinkedList<>();
        ListInterface<String> dataList = FileHandling.readFile("AssignmentTeam");
        for(String data : dataList) {
            String[] attr = data.split("\\|", 1);
            
            assignmentTeamList.add(FileHandling.getAssignmentTeam(attr[0]));
        }
        
        return assignmentTeamList;
    }
    
    public void writeToFile(ListInterface<AssignmentTeam> assignmentTeamList) {
        ListInterface<String> dataList = new LinkedList<>();
        for (AssignmentTeam assignmentTeam : assignmentTeamList) {
            dataList.add(assignmentTeam.saveToFile());
        }
        
        FileHandling.writeFile("AssignmentTeam", dataList);
    }
}
