# JavaFinalProject
CIS-18A Course Project - Option 2: Community Garden Program

### Version 1
I will start with creating a driver program that will output the specific information about The Community Garden. Then I will use the scanner class to get user string input to collect the user's contact information.

### Version 2
In this next version I will be creating an object class to hold the information for the appointment options. Set appointment during operating hours and have a 1 year span.

### Version 3
I will attempt to incorporate the 'JDatePicker: Java Swing Date Picker' feature with a button to save the date picked by user.

### Version 4
I caught 2 potential bugs in my version 3. The probability of user inputting a date and time on non business hours. Need to include error handling as well as creating a drop down box that provides an array of appropriate times to choose from.

### Version 5
In this version I will be creating a package based structure to add monetary and material donations. I will be creating a class 'Donation' and use inheritance to specify the different types of donations.

### Version 6 (Final Version)
I am implementing the final feature that will output the order summary and appointment in a text file. In this version I also went through and made sure that I have met all the rubric requirements. In Donation.java I implemented the use of interfaces and a switch statement.

## To run the final version file
### Compile the code (on Mac use : to separate paths)
javac -cp ".:lib/jdatepicker.jar" version6/*.java

### Run the code
java -cp ".:lib/jdatepicker.jar" version6.Garden
