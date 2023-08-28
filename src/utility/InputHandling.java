/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import java.util.Scanner;
/**
 *
 * @author User
 */
public class InputHandling {
    private static final Scanner scanner = new Scanner(System.in);
    
    public static int getInt() {
        return getInt("Please Enter A Digit : ");
    }
    
    public static int getInt(String promptMsg) {       
        System.out.print(promptMsg);
        
        do {
            try {
                if (scanner.hasNextInt()) {
                    int input = scanner.nextInt();
                    scanner.nextLine();
                    return input;
                }
            } catch (IllegalStateException ex) {
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
            } catch (IllegalStateException ex) {
                System.out.print("Invalid Input!" + promptMsg);
                scanner.nextLine();
            }                   
        } while (true);
    }
    
    public static char getChar() {
        return getChar("Please Enter A Character : ");
    }
    
    public static char getChar(String promptMsg) {
        System.out.print(promptMsg);
        return scanner.nextLine().charAt(0);
    }
    
    public static String getString() {
        return getString("Please Enter A String : ");
    }
    
    public static String getString(String promptMsg) {
        System.out.print(promptMsg);
        return scanner.nextLine();
    }
    
    public static boolean getConfirmation() {
        return getConfirmation("Are You Sure? (Y or N) : ");
    }
    
    public static boolean getConfirmation(String promptMsg) {
        System.out.print(promptMsg);

        char ch = Character.toUpperCase(scanner.next().charAt(0));
        
        while (ch != 'Y' && ch != 'N') {
            System.out.print("Invalid Input! Please Enter Only Y or N : ");
            ch = Character.toUpperCase(scanner.next().charAt(0));
        }
        
        return ch == 'Y';
    }
    
    public static int choiceValidation(String promptMsg, int min, int max) {
        int choice = getInt(promptMsg);
        
        while (choice < min || choice > max) {
            System.out.println("Invalid Option! Please Enter Again.");
            choice = getInt(promptMsg);
        }
        
        return choice;
    }
    
    public static void systemPause() {
        System.out.println("Press Enter To Proceed...");
        scanner.nextLine();
    }
}
