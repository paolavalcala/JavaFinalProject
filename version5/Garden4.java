/*
Paola Alcala
CIS-18A
Version 5
 */

package version5;

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
// Import Calendar to validate specific days (Saturdays/Sundays)
import java.util.Calendar;

// Appointment class to encapsulate appointment data.
class Appointment {
    String date; // Variable to store the date string
    String time; // Variable to store the time slot string

    // Constructor: Runs when 'new Appointment()' is called to initialize the object
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
public class Garden4 {
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

        // Donations
        System.out.print("\nWould you like to make a donation today? (yes/no): ");
        String choice = input.nextLine();

        if (choice.equalsIgnoreCase("yes")) {
            System.out.println("Select Donation Type: [1] Monetary  [2] Materials");
            int type = input.nextInt();
            input.nextLine(); // Clear the buffer after nextInt()

            if (type == 1) {
                // Handle Monetary Donation
                System.out.print("Enter amount: ");
                double amt = input.nextDouble();
                input.nextLine(); // Clear buffer

                MonetaryDonation md = new MonetaryDonation(name, "02-12-2026", amt);
                md.displayDonationSummary();
            } else {
                // Handle Resource/Material Donation
                System.out.print("What are you donating? (Seeds/Tools/Soil/Labor): ");
                String item = input.nextLine();
                System.out.print("Provide a brief description: ");
                String desc = input.nextLine();

                ResourceDonation rd = new ResourceDonation(name, "02-12-2026", item, desc);
                rd.displayDonationSummary();
            }
        }

        // Array of available time slots for the drop down menu
        String[] timeSlots = {
            "08:00 AM", "09:00 AM", "10:00 AM", "11:00 AM", "12:00 PM",
            "01:00 PM", "02:00 PM", "03:00 PM", "04:00 PM", "05:00 PM",
            "06:00 PM", "07:00 PM", "08:00 PM"
        };

        // Inform the user that the next step happens in a separate window
        System.out.println("\n--- Opening Selection Window ---");

        // GUI DATE & TIME PICKER
        SwingUtilities.invokeLater(() -> {
            // Create the main application window (JFrame)
            JFrame frame = new JFrame("Select Produce Picking Date & Time");
            // Ensure the program stops running when the window is closed
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // Set the initial dimensions of the window (width, height)
            frame.setSize(450, 300);
            // Set layout to FlowLayout
            frame.setLayout(new FlowLayout());

            // Create a data model for the date picker
            UtilDateModel model = new UtilDateModel();
            // Create a Properties object for labels
            Properties p = new Properties();
            p.put("text.today", "Today");
            p.put("text.month", "Month");
            p.put("text.year", "Year");

            // Create the panel and picker
            JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
            JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());

            // Create a JComboBox (drop down box) using the timeSlots array
            JComboBox<String> timeBox = new JComboBox<>(timeSlots);

            // Create a button that the user clicks to submit
            JButton confirmButton = new JButton("Create Appointment");

            // Attach an "Action Listener" to the button
            confirmButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Extract the text currently showing in the date picker
                    String selectedDate = datePicker.getJFormattedTextField().getText();
                    // Get the selected item from the drop down menu
                    String selectedTime = (String) timeBox.getSelectedItem();

                    // Validation: check if a date was selected
                    if (selectedDate.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Please select a date first!");
                        return;
                    }

                    // Error Handling for Business Hours

                    // Get the Date object and convert it to a Calendar object
                    java.util.Date dateValue = (java.util.Date) model.getValue();
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(dateValue);

                    int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

                    // Logic to check if time is after 5 PM
                    boolean isAfterFive = selectedTime.contains("PM") &&
                                        (selectedTime.startsWith("06") ||
                                            selectedTime.startsWith("07") ||
                                            selectedTime.startsWith("08"));

                    // Rule 1: No appointments on Sundays
                    if (dayOfWeek == Calendar.SUNDAY) {
                        JOptionPane.showMessageDialog(frame, "The garden is closed on Sundays!");
                    }
                    // Rule 2: Saturday closing at 5:00 PM
                    else if (dayOfWeek == Calendar.SATURDAY && isAfterFive) {
                        JOptionPane.showMessageDialog(frame, "On Saturdays, the garden closes at 5:00 PM.");
                    }
                    else {
                        // Create a new instance of the Appointment class
                        Appointment myAppt = new Appointment(selectedDate, selectedTime);

                        // Output the final results back to the console
                        System.out.println("\n------ Registration Confirmed ------");
                        System.out.println("Thank you for signing up, " + name + "!");
                        System.out.println("A confirmation has been sent to: " + email);
                        myAppt.displayAppointment();

                        // Close and destroy the GUI window
                        frame.dispose();
                        System.out.println("\nYou may now exit the program.");
                    }
                }
            });

            // Add components to the window
            frame.add(new JLabel("Select your produce picking date:"));
            frame.add(datePicker);
            frame.add(new JLabel("Select preferred time:"));
            frame.add(timeBox);
            frame.add(confirmButton);

            // Center and show window
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}


/**

# Compile the code (on Mac use : to separate paths)
javac -cp ".:lib/jdatepicker.jar" version5/*.java

# Run the code
java -cp ".:lib/jdatepicker.jar" version5.Garden4

 */
