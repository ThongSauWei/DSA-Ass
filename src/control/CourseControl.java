/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import entity.Course;
import adt.ListInterface;
import da.CourseDA;
/**
 *
 * @author User
 */
public class CourseControl {
    private CourseDA courseDA = new CourseDA();
    public ListInterface<Course> readFromFile() {
        return courseDA.readFromFile();
    }
    
    public void writeToFile(ListInterface<Course> courseList) {
        courseDA.writeToFile(courseList);
    }
}
