/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package da;

import adt.LinkedList;
import adt.ListInterface;

import entity.Programme;

import utility.ExceptionHandling;

import java.io.*;

/**
 *
 * @author User
 */
public class ProgrammeDA {
    public ListInterface<Programme> readFromFile() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("Programme.bin"))) {
            return (ListInterface<Programme>) inputStream.readObject();
        } catch (IOException ex) {
            ExceptionHandling.fileException(ExceptionHandling.FileAction.READ);
            return new LinkedList<>();
        } catch (ClassNotFoundException ex) {
            ExceptionHandling.classNotFoundExceptionMessage();
            return new LinkedList<>();
        }
    }
    
    public void writeToFile(ListInterface<Programme> programmeList) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("Programme.bin"))) {
            outputStream.writeObject(programmeList);
        } catch (IOException ex) {
            ExceptionHandling.fileException(ExceptionHandling.FileAction.WRITE);
        }
    }
}
