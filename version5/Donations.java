package version5;

/**
 * Abstract Base Class: Donation
 * This serves as the parent class for all donation types.
 * It cannot be instantiated on its own.
 */
abstract class Donation {
    protected String donorName; // Accessible by child classes
    protected String date;

    // Constructor to initialize common fields
    public Donation(String donorName, String date) {
        this.donorName = donorName;
        this.date = date;
    }

    // Abstract method: Every child class must implement its own version of this
    public abstract void displayDonationSummary();
}

/**
 * Child Class: MonetaryDonation
 * Specifically handles cash or digital currency contributions.
 */
class MonetaryDonation extends Donation {
    private double amount; // Specific to monetary gifts

    public MonetaryDonation(String donorName, String date, double amount) {
        // Call the parent (Donation) constructor
        super(donorName, date);
        this.amount = amount;
    }

    @Override
    public void displayDonationSummary() {
        System.out.println("\n--- Monetary Donation Logged ---");
        System.out.println("Donor: " + donorName);
        // Using printf to format as currency (2 decimal places)
        System.out.printf("Amount: $%.2f\n", amount);
    }
}

/**
 * Child Class: ResourceDonation
 * Handles physical goods like seeds, soil, fertilizer, gardening tools, or donated labor hours.
 */
class ResourceDonation extends Donation {
    private String materialType; // "Seeds", "Tools"
    private String description;  //"gardening gloves"

    public ResourceDonation(String donorName, String date, String materialType, String description) {
        super(donorName, date);
        this.materialType = materialType;
        this.description = description;
    }

    @Override
    public void displayDonationSummary() {
        System.out.println("\n--- Material Donation Logged ---");
        System.out.println("Donor: " + donorName);
        System.out.println("Item: " + materialType);
        System.out.println("Details: " + description);
    }
}
