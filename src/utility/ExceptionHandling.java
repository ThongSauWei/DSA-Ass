/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class ExceptionHandling {
    public static void numberFormatExceptionMessage() {
        JOptionPane.showMessageDialog(null, "Invalid Input! You should not enter a string in an input field asking for number.", "Error!", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void indexOutOfBoundsExceptionMessage() {
        JOptionPane.showMessageDialog(null, "Invalid Input! Your input is not within the range.", "Error!", JOptionPane.ERROR_MESSAGE);
    }
    
    public enum FileAction {
        CREATE, READ, WRITE;
    }
    
    public static void fileException(FileAction type) {
        String action;
        switch (type) {
            case CREATE:
                action = "Creating";
                break;
            case READ:
                action = "Reading";
                break;
            case WRITE:
                action = "Writing";
                break;
            default:
                action = "Accessing";
        }
        
        JOptionPane.showMessageDialog(null, "Error " + action + " File.", "Error!", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void endOfList() {
        JOptionPane.showMessageDialog(null, "Reached end of the list.", "Error!", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void dateParseException() {
        JOptionPane.showMessageDialog(null, "Error parsing date.", "Error!", JOptionPane.ERROR_MESSAGE);
    }
}
