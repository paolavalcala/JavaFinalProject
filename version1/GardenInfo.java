/*
Paola Alcala
CIS-18A
Version 1
 */

//program with string input, input the scanner class
import java.util.Scanner;

//Create Driver class that will display the community
//garden information

public class GardenInfo {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // 1. Include hours of operation and options
        System.out.println("-------- Welcome to the MVC Community Garden ---------");
        System.out.println("Hours: 8:00 AM - 8:00 PM Monday - Friday");
        System.out.println("and 8:00 AM - 5:00 PM every Saturday");
        System.out.println("During appointments you will be able to pick fresh produce, fruits, and vegetables at no cost.");
        System.out.println("Appointments Available: \n");

        // 2. Prompt user to register general contact info
        System.out.print("Enter your full name: ");
        String name = input.nextLine();

        System.out.print("Enter your email address: ");
        String email = input.nextLine();

        // 3. Display user information back
        System.out.println("\n------ Registration Confirmed ------");
        System.out.println("Thank you for joining, " + name + "!");
        System.out.println("A confirmation has been sent to: " + email);

        input.close();
    }
}
