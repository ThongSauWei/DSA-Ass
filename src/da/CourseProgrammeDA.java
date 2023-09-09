/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package da;

import adt.LinkedList;
import adt.ListInterface;

import entity.CourseProgramme;

import utility.ExceptionHandling;

import java.io.*;
/**
 *
 * @author User
 */
public class CourseProgrammeDA {
    public ListInterface<CourseProgramme> readFromFile() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("CourseProgramme.bin"))) {
            return (ListInterface<CourseProgramme>) inputStream.readObject();
        } catch (IOException ex) {
            ExceptionHandling.fileException(ExceptionHandling.FileAction.READ);
            return new LinkedList<>();
        } catch (ClassNotFoundException ex) {
            ExceptionHandling.classNotFoundExceptionMessage();
            return new LinkedList<>();
        }
    }
    
    public void writeToFile(ListInterface<CourseProgramme> courseProgrammeList) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("CourseProgramme.bin"))) {
            outputStream.writeObject(courseProgrammeList);
        } catch (IOException ex) {
            ExceptionHandling.fileException(ExceptionHandling.FileAction.WRITE);
        }
    }
}
