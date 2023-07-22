/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package da;

import adt.LinkedList;
import adt.ListInterface;
import entity.Student;
import utility.FileHandling;

/**
 *
 * @author User
 */
public class StudentDA {
    public ListInterface<Student> readFromFile() {
        ListInterface<Student> studentList = new LinkedList<>();
        ListInterface<String> dataList = FileHandling.readFile("AssignmentTeam");
        for(String data : dataList) {
            String[] attr = data.split("\\|", 1);
            
            studentList.add(FileHandling.getStudent(attr[0]));
        }
        
        return studentList;
    }
}
