/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package da;

import adt.LinkedList;
import adt.ListInterface;

import entity.TutorialGroup;

import utility.ExceptionHandling;

import java.io.*;
/**
 *
 * @author Benjamin
 */
public class TutorialGroupDA {
    public ListInterface<TutorialGroup> readFromFile() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("TutorialGroup.bin"))) {
            return (ListInterface<TutorialGroup>) inputStream.readObject();
        } catch (IOException ex) {
            ExceptionHandling.fileException(ExceptionHandling.FileAction.READ);
            return new LinkedList<>();
        } catch (ClassNotFoundException ex) {
            ExceptionHandling.classNotFoundExceptionMessage();
            return new LinkedList<>();
        }
    }
    
    public void writeToFile(ListInterface<TutorialGroup> tutorialGroupList) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("TutorialGroup.bin"))) {
            outputStream.writeObject(tutorialGroupList);
        } catch (IOException ex) {
            ExceptionHandling.fileException(ExceptionHandling.FileAction.WRITE);
        }
    }
}
