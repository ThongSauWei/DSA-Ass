/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package da;

import adt.LinkedList;
import adt.ListInterface;

import entity.AssignmentTeam;

import utility.ExceptionHandling;

import java.io.*;
/**
 *
 * @author User
 */
public class AssignmentTeamDA {
    public ListInterface<AssignmentTeam> readFromFile() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("AssignmentTeam.bin"))) {
            return (ListInterface<AssignmentTeam>) inputStream.readObject();
        } catch (IOException ex) {
            ExceptionHandling.fileException(ExceptionHandling.FileAction.READ);
            return new LinkedList<>();
        } catch (ClassNotFoundException ex) {
            ExceptionHandling.classNotFoundExceptionMessage();
            return new LinkedList<>();
        }
    }
    
    public void writeToFile(ListInterface<AssignmentTeam> assignmentTeamList) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("AssignmentTeam.bin"))) {
            outputStream.writeObject(assignmentTeamList);
        } catch (IOException ex) {
            ExceptionHandling.fileException(ExceptionHandling.FileAction.WRITE);
        }
    }
}
