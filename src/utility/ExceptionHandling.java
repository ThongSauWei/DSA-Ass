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
    public static void NumberFormatExceptionMessage() {
        JOptionPane.showMessageDialog(null, "Invalid Input! You should not enter a string in an input field asking for number.", "Error!", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void IndexOutOfBoundsExceptionMessage() {
        JOptionPane.showMessageDialog(null, "Invalid Input! Your input is not within the range.", "Error!", JOptionPane.ERROR_MESSAGE);
    }
}
