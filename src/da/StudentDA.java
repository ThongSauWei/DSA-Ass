/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package da;

import adt.LinkedList;
import adt.ListInterface;

import entity.Student;

import utility.ExceptionHandling;

import java.io.*;
/**
 *
 * @author Benjamin
 */
public class StudentDA {
    public ListInterface<Student> readFromFile() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("Student.bin"))) {
            return (ListInterface<Student>) inputStream.readObject();
        } catch (IOException ex) {
            ExceptionHandling.fileException(ExceptionHandling.FileAction.READ);
            return new LinkedList<>();
        } catch (ClassNotFoundException ex) {
            ExceptionHandling.classNotFoundExceptionMessage();
            return new LinkedList<>();
        }
    }
    
    public void writeToFile(ListInterface<Student> studentList) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("Student.bin"))) {
            outputStream.writeObject(studentList);
        } catch (IOException ex) {
            ExceptionHandling.fileException(ExceptionHandling.FileAction.WRITE);
        }
    }
}
