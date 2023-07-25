/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import entity.Student;
import adt.ListInterface;
import da.StudentDA;
/**
 *
 * @author User
 */
public class StudentControl {
    private StudentDA studentDA = new StudentDA();
    public ListInterface<Student> readFromFile() {
        return studentDA.readFromFile();
    }
}
