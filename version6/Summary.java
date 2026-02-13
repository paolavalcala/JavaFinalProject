package version6;

/**
 * --Implement interfaces (20 points)
 * This interface ensures that any class representing an activity (Donation or Appointment)
 * provides a way to retrieve a text summary of that activity.
 */
public interface Summary {
    // This is an abstract method and has no body
    // Classes implementing this mus define how the method works
    String getSummary();
}
