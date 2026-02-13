package version6;

// --Import packages (10 points)
// Importing external libraries for UI
import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Properties;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import org.jdatepicker.impl.*;

/**
 * Appointment class. Stores scheduling details.
 */
class Appointment implements Summary {
    private String date;
    private String time;

    // --Objects and methods (30 points)
    // Constructor initializes the specific date and time selected by the user.
    public Appointment(String ADate, String ATime) {
        this.date = ADate;
        this.time = ATime;
    }

    @Override
    public String getSummary() {
        return "Appointment scheduled for " + date + " at " + time;
    }

    public void displayAppointment() {
        System.out.println("\n------ Registration Confirmed ------");
        System.out.println(getSummary());
    }
}

public class Garden {
    // This 'static' string acts as a buffer to store all activity logs until the user exits.
    private static String finalReport = "";

    public static void main(String[] args) {
        // --Variables (10 points)
        Scanner input = new Scanner(System.in); // Scanner object for terminal input
        boolean keepRunning = true;             // Loop control variable

        // --Arrays (20 points)
        // A 1D array to store the string values of available time slots.
        String[] timeSlots = {
            "08:00 AM", "09:00 AM", "10:00 AM", "11:00 AM", "12:00 PM",
            "01:00 PM", "02:00 PM", "03:00 PM", "04:00 PM", "05:00 PM",
            "06:00 PM", "07:00 PM", "08:00 PM"
        };

        // Header Section with garden info
        System.out.println("-------- Welcome to the MVC Community Garden ---------");
        System.out.print("Enter your full name: ");
        String name = input.nextLine(); // Reads a full line of text
        System.out.print("Enter your email address: ");
        String email = input.nextLine();

        // --2 or more controlled statements (While loop) (20 points)
        // This loop keeps the program running until the user chooses to exit (Option 3).
        while (keepRunning) {
            // --Adequate "Options" menu
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Make a Donation");
            System.out.println("2. Schedule an Appointment");
            System.out.println("3. Exit and Save Summary");
            System.out.print("Please select an option: ");

            int choice = input.nextInt(); // Reads the integer choice
            input.nextLine();             // Consumes the leftover newline character

            // -- Switch statements (10 points)
            // Evaluates 'choice' and jumps to the matching case.
            switch (choice) {
                case 1:
                    handleDonationMenu(input, name);
                    break;
                case 2:
                    // Loop through the array to show the user what times are available.
                    for (String slot : timeSlots) { System.out.print("[" + slot + "] "); }
                    System.out.println();
                    launchAppointmentGUI(timeSlots); // Opens the graphical window
                    break;
                case 3:
                    saveSummaryToFile(name, email); // Saves data to disk
                    System.out.println("Goodbye!");
                    keepRunning = false;            // Breaks the while loop
                    System.exit(0);                 // Closes the application
                    break;
                default:
                    System.out.println("Invalid selection.");
            }
        }
    }

    private static void handleDonationMenu(Scanner input, String name) {
        System.out.println("\nSelect Donation Type: [1] Monetary  [2] Materials");
        int type = input.nextInt();
        input.nextLine();

        // --2 or more controlled statements (If-else) (20 points)
        if (type == 1) {
            System.out.print("Enter amount: ");
            double amt = input.nextDouble();
            input.nextLine();

            //--Operators (20 points)
            // Uses logical AND (&&) to ensure amount is within a valid range.
            if (amt > 0 && amt <= 50000) {
                MonetaryDonation md = new MonetaryDonation(name, "02-12-2026", amt);
                md.displayDonationSummary();
                finalReport += md.getSummary() + "\n"; // Adds this line to our final report
            }
        } else if (type == 2) {
            System.out.print("Item name: ");
            String item = input.nextLine();
            System.out.print("Description: ");
            String desc = input.nextLine();
            ResourceDonation rd = new ResourceDonation(name, "02-12-2026", item, desc);
            rd.displayDonationSummary();
            finalReport += rd.getSummary() + "\n";
        }
    }

    private static void launchAppointmentGUI(String[] slots) {
        // SwingUtilities ensures the GUI is created on the correct "Event Dispatch Thread".
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Garden Scheduler"); // Create the window
            frame.setSize(450, 300);
            frame.setLayout(new FlowLayout()); // Aligns components left-to-right

            // Date Picker setup logic
            UtilDateModel model = new UtilDateModel();
            Properties p = new Properties();
            p.put("text.today", "Today");
            JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
            JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());

            // Dropdown menu (JComboBox) filled with our timeSlots array
            JComboBox<String> timeBox = new JComboBox<>(slots);
            JButton btn = new JButton("Confirm Appointment");

            // Event Listener: Runs when the "Confirm" button is clicked
            btn.addActionListener(e -> {
                String dateStr = datePicker.getJFormattedTextField().getText();
                String timeStr = (String) timeBox.getSelectedItem();

                // Basic validation: ensures user didn't leave the date blank
                if (dateStr == null || dateStr.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Error: Please pick a date.");
                    return;
                }

                // Use Calendar to figure out if the picked date is a Sunday or Saturday
                Calendar cal = Calendar.getInstance();
                cal.setTime((java.util.Date) model.getValue());
                int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

                // --Operators: logical OR (||) (20 points)
                boolean isSunday = (dayOfWeek == Calendar.SUNDAY);
                boolean isSaturday = (dayOfWeek == Calendar.SATURDAY);
                // Checks if the user picked a time later than 5:00 PM
                boolean isLateSlot = timeStr.contains("06:00 PM") || timeStr.contains("07:00 PM") || timeStr.contains("08:00 PM");

                if (isSunday) {
                    JOptionPane.showMessageDialog(frame, "ERROR: Garden closed Sundays.");
                } else if (isSaturday && isLateSlot) {
                    JOptionPane.showMessageDialog(frame, "ERROR: Saturday hours end at 5PM.");
                } else {
                    // Success path: create the object and add to report
                    Appointment appt = new Appointment(dateStr, timeStr);
                    finalReport += appt.getSummary() + "\n";
                    appt.displayAppointment();
                    frame.dispose(); // Closes only the GUI window, not the whole program
                }
            });

            // Adding visual elements to the frame
            frame.add(new JLabel("Select Date:")); frame.add(datePicker);
            frame.add(new JLabel("Select Time:")); frame.add(timeBox);
            frame.add(btn);
            frame.setLocationRelativeTo(null); // Centers the window on screen
            frame.setVisible(true);            // Makes it pop up
        });
    }

    /**
     * --Efficiency and performance (5 points)
     * This method writes to a file. It uses a try-with-resources block which
     * automatically closes the file writer and preventing memory leaks.
     */
    private static void saveSummaryToFile(String user, String email) {
        try (PrintWriter out = new PrintWriter(new FileWriter("GardenSummary.txt"))) {
            out.println("GARDEN SUMMARY REPORT");
            out.println("User: " + user);
            out.println("Email: " + email);
            out.println("==========================================");
            // Ternary operator says if report is empty, write a default message
            out.println(finalReport.isEmpty() ? "No activities recorded." : finalReport);
            System.out.println("\nSummary saved to 'GardenSummary.txt'.");
        } catch (IOException e) {
            // Error handling if the file cannot be written
            System.err.println("File Error: " + e.getMessage());
        }
    }
}

/**

# Compile the code (on Mac use : to separate paths)
javac -cp ".:lib/jdatepicker.jar" version6/*.java

# Run the code
java -cp ".:lib/jdatepicker.jar" version6.Garden

 */
