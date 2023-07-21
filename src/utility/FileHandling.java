/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import adt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import utility.ExceptionHandling.FileAction;

/**
 *
 * @author User
 */
public class FileHandling {
    public static void createOrOpenFile(String filePath) {
        File file = new File(filePath);
        
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                ExceptionHandling.fileException(FileAction.CREATE);
            }
        }
    }
    
    public static ListInterface<String> readFile(String fileName) {
        String filePath = fileName + ".txt";
        ListInterface<String> dataList = new LinkedList<>();
        
        createOrOpenFile(filePath);
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String data;
            while ((data = reader.readLine()) != null) {
                dataList.add(data);
            }
        } catch (IOException ex) {
            ExceptionHandling.fileException(FileAction.READ);
        }
        
        return dataList;
    }
    
    public static void writeFile(String fileName, ListInterface<String> dataList) {
        String filePath = fileName + ".txt";

        createOrOpenFile(filePath);
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String data : dataList) {
                writer.write(data);
                writer.newLine();
            }
        } catch (IOException ex) {
            ExceptionHandling.fileException(FileAction.WRITE);
        }
    }
    
    public static void main(String[] args) {
        ListInterface<String> dataList = readFile("Course");
        if (dataList.isEmpty()) {
            ListInterface<String> writeFile = new LinkedList<>();
            writeFile.add("1 ........");
            writeFile.add("2 ........");
            writeFile.add("3 ........");
            writeFile.add("4 ........");
            writeFile("Course", writeFile);
        } else {
            for (String data : dataList) {
                System.out.println(data);
            }
        }
    }
}
