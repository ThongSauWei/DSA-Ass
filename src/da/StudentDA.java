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
            String[] attr = data.split("\\|");
            
            studentList.add(new Student(attr[0], attr[1], attr[2], attr[3], attr[4], Integer.parseInt(attr[5]), Integer.parseInt(attr[6]), attr[7], attr[8]));
        }
        
        return studentList;
    }
}
