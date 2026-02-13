/*
Paola Alcala
CIS-18A
Version 3
 */

// Import Swing components for the GUI
import javax.swing.*;
// Import AWT for layout management
import java.awt.*;
// Import ActionEvent and ActionListener to handle button clicks
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// Import the JDatePicker implementation classes from your external JAR library
import org.jdatepicker.impl.*;
// Import Properties to configure the text inside the date picker
import java.util.Properties;
// Import Scanner to read user input from the command line/terminal
import java.util.Scanner;

// Appointment class to encapsulate appointment data.
class Appointment {
    String date; // Variable to store the date string
    String time; // Variable to store the time slot string

    // Constructor runs when 'new Appointment()' is called to initialize the object
    public Appointment(String ADate, String ATime) {
        this.date = ADate; // Assign the parameter ADate to the class variable date
        this.time = ATime; // Assign the parameter ATime to the class variable time
    }

    // Helper method to print the object's data back to the terminal
    public void displayAppointment() {
        System.out.println("Your appointment is scheduled for: " + date);
        System.out.println("At this specific time block: " + time);
    }
}

// The Driver class
public class Garden2 {
    public static void main(String[] args) {
        // Initialize the Scanner to read from System.in
        Scanner input = new Scanner(System.in);

        // Garden Information
        System.out.println("-------- Welcome to the MVC Community Garden ---------");
        System.out.println("Hours: 8:00 AM - 8:00 PM Monday - Friday");
        System.out.println("and 8:00 AM - 5:00 PM every Saturday\n");

        // Prompt the user and capture their name as a String
        System.out.print("Enter your full name: ");
        String name = input.nextLine();

        // Prompt the user and capture their email
        System.out.print("Enter your email address: ");
        String email = input.nextLine();

        // Prompt for the time slot since we aren't using a GUI time-picker
        System.out.print("Enter preferred time slot (ex.) 10:00 AM): ");
        String userTime = input.nextLine();

        // Inform the user that the next step happens in a separate window
        System.out.println("\n--- Opening Date Picker for Selection ---");

        // GUI DATE PICKER
        // SwingUtilities.invokeLater ensures the GUI is built on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            // Create the main application window (JFrame)
            JFrame frame = new JFrame("Select Harvest Date");
            // Ensure the program stops running when the window is closed
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // Set the initial dimensions of the window (width, height)
            frame.setSize(450, 250);
            // Set layout to FlowLayout (elements appear one after another, left to right)
            frame.setLayout(new FlowLayout());

            // Create a data model for the date picker to manage the date state
            UtilDateModel model = new UtilDateModel();
            // Create a Properties object to define the labels in the date picker popup
            Properties p = new Properties();
            p.put("text.today", "Today"); // Label for the 'Today' button
            p.put("text.month", "Month"); // Label for the month dropdown
            p.put("text.year", "Year");   // Label for the year dropdown

            // Create the panel that shows the calendar grid using the model and properties
            JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
            // Create the actual picker (text field + button) with a date formatter
            JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());

            // Create a button that the user clicks to submit their choice
            JButton confirmButton = new JButton("Confirm Appointment");

            // Attach an "Action Listener" to the button to detect when it's clicked
            confirmButton.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    // Extract the text (the date) currently showing in the date picker's text field
                    String selectedDate = datePicker.getJFormattedTextField().getText();

                    // Validation: check if the user actually clicked a date on the calendar
                    if (selectedDate.isEmpty()) {
                        // Show an error popup if no date was selected
                        JOptionPane.showMessageDialog(frame, "Please select a date first!");
                    } else {
                        // Create a new instance of the Appointment class using the gathered data
                        Appointment myAppt = new Appointment(selectedDate, userTime);

                        // Output the final results back to the console
                        System.out.println("\n------ Registration Confirmed ------");
                        System.out.println("Thank you for signing up, " + name + "!");
                        System.out.println("A confirmation has been sent to: " + email);
                        // Call the method from the Appointment class to print date/time
                        myAppt.displayAppointment();

                        // Close and destroy the GUI window
                        frame.dispose();
                        // Final instruction to the user
                        System.out.println("\nYou may now exit the program.");
                    }
                }
            });

            // Add the text label to the window
            frame.add(new JLabel("Select your harvest date:"));
            // Add the date picker component to the window
            frame.add(datePicker);
            // Add the confirm button to the window
            frame.add(confirmButton);

            // Center the window on the computer screen
            frame.setLocationRelativeTo(null);
            // Make the window visible to the user
            frame.setVisible(true);
        });

        // The Scanner is left open here because the GUI runs in a separate thread asynchronously.
        // input.close();
    }
}


/**

# Compile the code (on Mac use : to separate paths)
javac -cp ".:lib/jdatepicker-1.3.4 (1).jar" version3/Garden2.java

# Run the code
java -cp ".:lib/jdatepicker-1.3.4 (1).jar:version3" Garden2

 */
