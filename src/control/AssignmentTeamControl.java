/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import entity.AssignmentTeam;
import adt.ListInterface;
import da.AssignmentTeamDA;
/**
 *
 * @author User
 */
public class AssignmentTeamControl {
    private AssignmentTeamDA assignmentTeamDA = new AssignmentTeamDA();
    public ListInterface<AssignmentTeam> readFromFile() {
        return assignmentTeamDA.readFromFile();
    }
    
    public void writeToFile(ListInterface<AssignmentTeam> assignmentTeamList) {
        assignmentTeamDA.writeToFile(assignmentTeamList);
    }
}
