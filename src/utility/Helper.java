/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

/**
 *
 * @author Benjamin, Erika, Thong, Valerie
 */
public class Helper {
  
    public static void clearScreen() {
        for (int i = 1; i <= 50; i++) {
            System.out.println();
        }
    }
    
    public static void printLine(char ch, int num) {
        for (int i = 1; i <= num; i++) {
            System.out.print(ch);
        }
        
        System.out.println();
    }
    
    public static boolean choiceValidation(int choice, int min, int max) {
        
        if (choice < min || choice > max) {
            System.out.println("Invalid Option! Please Enter Again.");
            return false;
        }
        
        return true;
    }
}
