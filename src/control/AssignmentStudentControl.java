/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import entity.AssignmentStudent;
import adt.ListInterface;
import da.AssignmentStudentDA;
/**
 *
 * @author User
 */
public class AssignmentStudentControl {
    private AssignmentStudentDA assignmentStudentDA = new AssignmentStudentDA();
    public ListInterface<AssignmentStudent> readFromFile() {
        return assignmentStudentDA.readFromFile();
    }
    
    public void writeToFile(ListInterface<AssignmentStudent> assignmentStudentList) {
        assignmentStudentDA.writeToFile(assignmentStudentList);
    }
}
