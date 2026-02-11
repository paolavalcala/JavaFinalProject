/*
Paola Alcala
CIS-18A
Version 2
 */

//program with string input, input the scanner class
import java.util.Scanner;

//Appointment class to encapsulate appointment data.
class Appointment {
    String date;
    String time;

    //Constructor to initialize the appointment object
    public Appointment(String ADate, String ATime) {
        date = ADate;
        time = ATime;
    }

    //helper method to display the appointment data after
    public void displayAppointment(){
        System.out.println("Your appointment is scheduled for: " + date);
        System.out.println("At this specific time block: " + time);
    }
}

//Create Driver class that will display the community
//garden information

public class Garden {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Include hours of operation and options
        System.out.println("-------- Welcome to the MVC Community Garden ---------");
        System.out.println("Hours: 8:00 AM - 8:00 PM Monday - Friday");
        System.out.println("and 8:00 AM - 5:00 PM every Saturday");
        System.out.println("During appointments you will be able to pick fresh produce, fruits, and vegetables at no cost.");
        System.out.println("Appointments Available: \n");

        // Prompt user to register general contact info
        System.out.print("Enter your full name: ");
        String name = input.nextLine();

        System.out.print("Enter your email address: ");
        String email = input.nextLine();

        //Ask for appointment details
        System.out.print("Enter preferred date (MM/DD/YYYY): ");
        String userDate = input.nextLine();

        System.out.print("Enter preferred time slot: ");
        String userTime = input.nextLine();

        //create the appointment object
        Appointment myAppt = new Appointment(userDate, userTime);

        // Display user information back
        System.out.println("\n------ Registration Confirmed ------");
        System.out.println("Thank you for joining, " + name + "!");
        System.out.println("A confirmation has been sent to: " + email);

        // Use the object to print the appointment details
        myAppt.displayAppointment();

        input.close();
    }
}
