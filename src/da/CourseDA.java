/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package da;

import adt.LinkedList;
import adt.ListInterface;
import entity.Course;
import utility.FileHandling;

/**
 *
 * @author User
 */
public class CourseDA {
    public ListInterface<Course> readFromFile() {
        ListInterface<Course> courseList = new LinkedList<>();
        ListInterface<String> dataList = FileHandling.readFile("Course");
        for(String data : dataList) {
            String[] attr = data.split("\\|", 1);
            
            courseList.add(FileHandling.getCourse(attr[0]));
        }
        
        return courseList;
    }
}
