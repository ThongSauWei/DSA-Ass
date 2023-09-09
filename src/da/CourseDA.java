/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package da;

import adt.LinkedList;
import adt.ListInterface;

import entity.Course;

import utility.ExceptionHandling;

import java.io.*;
/**
 *
 * @author User
 */
public class CourseDA {
    public ListInterface<Course> readFromFile() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("Course.bin"))) {
            return (ListInterface<Course>) inputStream.readObject();
        } catch (IOException ex) {
            ExceptionHandling.fileException(ExceptionHandling.FileAction.READ);
            return new LinkedList<>();
        } catch (ClassNotFoundException ex) {
            ExceptionHandling.classNotFoundExceptionMessage();
            return new LinkedList<>();
        }
    }
    
    public void writeToFile(ListInterface<Course> courseList) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("Course.bin"))) {
            outputStream.writeObject(courseList);
        } catch (IOException ex) {
            ExceptionHandling.fileException(ExceptionHandling.FileAction.WRITE);
        }
    }
}
