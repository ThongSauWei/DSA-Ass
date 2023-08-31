/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import adt.*;
import entity.*;
import utility.ExceptionHandling.FileAction;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
    
    public static AssignmentTeam getAssignmentTeam(String primaryKey) {
        ListInterface<String> dataList = readFile("AssignmentTeam");
        
        for(String data : dataList) {
            String[] attr = data.split("\\|");
            
            if (primaryKey.equals(attr[0])) {
                return new AssignmentTeam(attr[0], getCourse(attr[1]), getTutorialGroup(attr[2]));
            }
        }
        
        return null;
    }
    
    public static Programme getProgramme(String primaryKey) {
        ListInterface<String> dataList = readFile("Programme");
        
        for(String data : dataList) {
            String[] attr = data.split("\\|");
            
            if (primaryKey.equals(attr[0])) {
                return new Programme(attr[0], attr[1], attr[2], attr[3].charAt(0), attr[4], Integer.parseInt(attr[5]));
            }
        }
        
        return null;
    }
    
    public static CourseProgramme getCourseProgramme(String primaryKey) {
        ListInterface<String> dataList = readFile("CourseProgramme");
        
        for(String data : dataList) {
            String[] attr = data.split("\\|");
            
            if (primaryKey.equals(attr[0])) {
                return new CourseProgramme(getProgramme(attr[1]), getCourse(attr[2]));
            }
        }
        
        return null;
    }
    
    public static Course getCourse(String primaryKey) {
        ListInterface<String> dataList = readFile("Course");

        for (String data : dataList) {
            String[] attr = data.split("\\|");

            if (primaryKey.equals(attr[0])) {
                try {
                    return new Course(attr[0], attr[1], attr[2], new SimpleDateFormat("yyyy-MM-dd").parse(attr[3]),
                            new SimpleDateFormat("yyyy-MM-dd").parse(attr[4]));
                } catch (ParseException ex) {
                    ExceptionHandling.dateParseException();
                }
            }

        }
        
        return null;
    }
    
    public static Student getStudent(String primaryKey) {
        ListInterface<String> dataList = readFile("Student");
        
        for(String data : dataList) {
            String[] attr = data.split("\\|");
            
            if (primaryKey.equals(attr[0])) {
                return new Student(attr[0], attr[1], attr[2], attr[3], attr[4], Integer.parseInt(attr[5]),
                        Integer.parseInt(attr[6]), getTutorialGroup(attr[7]));
            }
        }
        
        return null;
    }
    
    public static TutorialGroup getTutorialGroup(String primaryKey) {
        ListInterface<String> dataList = readFile("TutorialGroup");
        
        for(String data : dataList) {
            String[] attr = data.split("\\|");
            
            if (primaryKey.equals(attr[0])) {
                return new TutorialGroup(attr[0], Integer.parseInt(attr[1]), Integer.parseInt(attr[2]), getProgramme(attr[3]));
            }
        }
        
        return null;
    }
    
    public static AssignmentStudent getAssignmentStudent(String primaryKey) {
        ListInterface<String> dataList = readFile("AssignmentStudent");
        
        for(String data : dataList) {
            String[] attr = data.split("\\|");
            
            if (primaryKey.equals(attr[0])) {
                return new AssignmentStudent(getAssignmentTeam(attr[1]), getStudent(attr[2]));
            }
        }
        
        return null;
    }
}
