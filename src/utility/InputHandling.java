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
    public static int getInt() {
        return getInt("Please Enter A Digit : ");
    }
    
    public static int getInt(String promptMsg) {
        System.out.print(promptMsg);
        
        do {
            try (Scanner scanner = new Scanner(System.in)) {
                int input = scanner.nextInt();
                return input;
            } catch (IllegalStateException ex) {
                System.out.print("Invalid Input! " + promptMsg);
            }                   
        } while (true);
    }
    
    public static double getDouble() {
        return getDouble("Please Enter A Value : ");
    }
    
    public static double getDouble(String promptMsg) {
        System.out.print(promptMsg);
        
        do {
            try (Scanner scanner = new Scanner(System.in)) {
                double input = scanner.nextDouble();
                return input;
            } catch (IllegalStateException ex) {
                System.out.print("Invalid Input!" + promptMsg);
            }                   
        } while (true);
    }
    
    public static char getChar() {
        return getChar("Please Enter A Character : ");
    }
    
    public static char getChar(String promptMsg) {
        System.out.print(promptMsg);
        return new Scanner(System.in).nextLine().charAt(0);
    }
    
    public static String getString() {
        return getString("Please Enter A String : ");
    }
    
    public static String getString(String promptMsg) {
        System.out.print(promptMsg);
        return new Scanner(System.in).nextLine();
    }
    
    public static boolean getConfirmation() {
        return getConfirmation("Are You Sure? (Y or N) : ");
    }
    
    public static boolean getConfirmation(String promptMsg) {
        System.out.print(promptMsg);
        
        Scanner scanner = new Scanner(System.in);
        char ch = Character.toUpperCase(scanner.next().charAt(0));
        
        while (ch != 'Y' && ch != 'N') {
            System.out.print("Invalid Input! Please Enter Only Y or N : ");
            ch = Character.toUpperCase(scanner.next().charAt(0));
        }
        
        return ch == 'Y';
    }
    
    public static void systemPause() {
        System.out.println("Press Enter To Proceed...");
        new Scanner(System.in).nextLine();
    }
}
