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
        ListInterface<String> dataList = FileHandling.readFile("Student");
        for(String data : dataList) {
            String[] attr = data.split("\\|");
            
            studentList.add(FileHandling.getStudent(attr[0]));
        }
        
        return studentList;
    }
    
    public void writeToFile(ListInterface<Student> studentList) {
        ListInterface<String> dataList = new LinkedList<>();
        for (Student student : studentList) {
            dataList.add(student.saveToFile());
        }
        
        FileHandling.writeFile("Student", dataList);
    }
}
