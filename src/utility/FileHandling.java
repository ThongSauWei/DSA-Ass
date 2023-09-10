/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import adt.*;
import entity.*;
import utility.ExceptionHandling.FileAction;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Benjamin, Erika, Thong, Valerie
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
}
