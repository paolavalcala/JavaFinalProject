package version6;

/**
 * --Incorporate at least 2 classes (10 points)
 * This is an 'abstract' class
 */
abstract class Donations implements Summary {

    // --Control access to class members (10 points)
    // 'protected' variables are visible to this class and its subclasses (Inheritance).
    protected String donorName; // Stores who gave the donation
    protected String date;      // Stores when the donation happened

    // Constructor runs when a new donation is created to set initial values
    public Donations(String donorName, String date) {
        this.donorName = donorName;
        this.date = date;
    }

    // Abstract method. Every subclass must provide its own way to print a summary.
    public abstract void displayDonationSummary();
}

/**
 * --Inheritance (10 points)
 * MonetaryDonation "extends" Donations, inheriting donorName and date.
 */
class MonetaryDonation extends Donations {
    // --Appropriate data type (10 points)
    // 'double' is used here to allow for decimal values (cents) in currency.
    private double amount;

    public MonetaryDonation(String donorName, String date, double amount) {
        // 'super' calls the constructor of the parent class (Donations)
        super(donorName, date);
        this.amount = amount; // Sets the specific amount for this instance
    }

    // Overriding the interface method to return a formatted currency string
    @Override
    public String getSummary() {
        return "Monetary Donation by " + donorName + ": $" + String.format("%.2f", amount);
    }

    // Implementation of the abstract method to print to the console
    @Override
    public void displayDonationSummary() {
        System.out.println("\n--- Monetary Donation Logged ---");
        System.out.println(getSummary());
    }
}

/**
 * ResourceDonation represents physical good donations (seeds, soil, tools).
 */
class ResourceDonation extends Donations {
    private String materialType; // example "Seeds"
    private String description;  // example, "3 bags of soil"

    public ResourceDonation(String donorName, String date, String materialType, String description) {
        super(donorName, date);
        this.materialType = materialType;
        this.description = description;
    }

    @Override
    public String getSummary() {
        return "Material Donation (" + materialType + ") by " + donorName + ": " + description;
    }

    @Override
    public void displayDonationSummary() {
        System.out.println("\n--- Material Donation Logged ---");
        System.out.println(getSummary());
    }
}
