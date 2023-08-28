/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package da;

import adt.LinkedList;
import adt.ListInterface;
import entity.AssignmentStudent;
import utility.FileHandling;
/**
 *
 * @author User
 */
public class AssignmentStudentDA {
    public ListInterface<AssignmentStudent> readFromFile() {
        ListInterface<AssignmentStudent> assignmentStudentList = new LinkedList<>();
        ListInterface<String> dataList = FileHandling.readFile("AssignmentStudent");
        for(String data : dataList) {
            String[] attr = data.split("\\|");
            
            assignmentStudentList.add(FileHandling.getAssignmentStudent(attr[0]));
        }
        
        return assignmentStudentList;
    }
    
    public void writeToFile(ListInterface<AssignmentStudent> assignmentStudentList) {
        ListInterface<String> dataList = new LinkedList<>();
        for (AssignmentStudent assignmentStudent : assignmentStudentList) {
            dataList.add(assignmentStudent.saveToFile());
        }
        
        FileHandling.writeFile("AssignmentStudent", dataList);
    }
}
