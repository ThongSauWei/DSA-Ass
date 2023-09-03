/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author User
 */
public class InputHandling {
    private static final Scanner scanner = new Scanner(System.in);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static int getInt() {
        return getInt("Please Enter A Digit : ");
    }
    
    public static int getInt(String promptMsg) {       
        System.out.print(promptMsg);
                  
        do {
            try {
                int input = scanner.nextInt();
                scanner.nextLine();
                return input;
            } catch (InputMismatchException ex) {
                System.out.print("Invalid Input! " + promptMsg);
                scanner.nextLine();
            }
        } while (true);
    }
    
    public static double getDouble() {
        return getDouble("Please Enter A Value : ");
    }
    
    public static double getDouble(String promptMsg) {       
        System.out.print(promptMsg);
        
        do {
            try {
                double input = scanner.nextDouble();
                scanner.nextLine();
                return input;
            } catch (InputMismatchException ex) {
                System.out.print("Invalid Input! " + promptMsg);
                scanner.nextLine();
            }                   
        } while (true);
    }
    
    public static char getChar() {
        return getChar("Please Enter A Character : ");
    }
    
    public static char getChar(String promptMsg) {
        System.out.print(promptMsg);
        
        char ch = scanner.nextLine().charAt(0);
        return ch;
    }
    
    public static String getString() {
        return getString("Please Enter A String : ");
    }
    
    public static String getString(String promptMsg) {
        System.out.print(promptMsg);
        
        String str = scanner.nextLine();
        return str;
    }
    
    public static Date getDate(String promptMsg) {
        System.out.print(promptMsg);
            String input = scanner.nextLine();
            try {
                Date date = dateFormat.parse(input);
                return date;
            } catch (ParseException ex) {
                System.out.println("!Invalid Date Format!");
            }
        return null;
    }
    
    public static boolean getConfirmation() {
        return getConfirmation("Are You Sure? (Y or N) : ");
    }
    
    public static boolean getConfirmation(String promptMsg) {
        System.out.print(promptMsg);

        char ch = Character.toUpperCase(scanner.next().charAt(0)); 
        scanner.nextLine();
        
        while (ch != 'Y' && ch != 'N') {
            System.out.print("Invalid Input! Please Enter Only Y or N : ");
            ch = Character.toUpperCase(scanner.next().charAt(0));
            scanner.nextLine();
        }
        
        System.out.println();
        
        return ch == 'Y';
    }
    
    public static void systemPause() {
        System.out.print("Press Enter To Proceed...");
        scanner.nextLine();
    }
}
