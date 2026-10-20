package com.pluralsight;

// ! import Scanner to receive user input
import java.util.Scanner;

public class RentalCarCalc {

    public static void main(String[] args) {

        // * Declared Variables:

        String welcome;
        welcome = makeColorScheme(BLUE, "Welcome to the Rental Car Calculator!\n");
        System.out.println(welcome);

        // ? - Ask for date they will pick up the car.
        String pickup;
        pickup = makeColorScheme(GREEN, """
        What day will you be picking up the rental car? (mm/dd/yyyy) \n
        """);
        System.out.println(pickup);
        String pickupAnswer = sc.nextLine();


        // ? - Ask for how long they will be renting the car.
        String duration;
        duration = makeColorScheme(GREEN, "How long will you be renting a car for? (e.g. 7 days) \n");
        System.out.println(duration);
        String durationAnswer = sc.nextLine();
        String durationNString = durationAnswer.replaceAll("[^0-9]", "");
        int numberOfDays = Integer.parseInt(durationNString);

        // ? - Ask for their age.
        String ageSurcharge;
        ageSurcharge = makeColorScheme(RED, """
        Drivers under the age of 25 receive a 30% surcharge; how old are
        you? (e.g. 26 years old)
         """);
        System.out.println(ageSurcharge);
        String ageAnswer = sc.nextLine();
        String ageNString = ageAnswer.replaceAll("[^0-9]", "");
        int driverage = Integer.parseInt(ageNString);

        // ? - Optionals: Ask if they want a toll tag, GPS, or roadside assistance (fee per day)

        // ? Toll Tag Query
        String electronicTollTag;
        electronicTollTag = makeColorScheme(RED, """
     Do you want an electronic toll tag for $3.95 per day of rental? (Yes/No)
     """);
        System.out.println(electronicTollTag);
        String electronicTollTagAnswer = sc.nextLine();
        boolean wantsTag = electronicTollTagAnswer.equalsIgnoreCase("yes");

        // ? GPS Query
        String gps;
        gps = makeColorScheme(RED, "Do you want a GPS for $2.95 per day of rental? (Yes/No) \n");
        System.out.println(gps);
        String gpsAnswer = sc.nextLine();
        boolean wantsGps = gpsAnswer.equalsIgnoreCase("yes");

        // ? AAA Query
        String aaa;
        aaa = makeColorScheme(RED, """
        Do you want roadside assistance for $3.95 per day of rental? (Yes/No)
        """);
        System.out.println(aaa);
        String aaaAnswer = sc.nextLine();
        boolean wantsAAA = aaaAnswer.equalsIgnoreCase("yes");

        // TODO - Calculate and dislay to the user:
        // TODO - Basic Car Rental cost
        double basicCarRental = 29.99 * numberOfDays;
        System.out.printf("The basic car rental fee for %d days is $%.2f%n", numberOfDays, basicCarRental);

        // TODO - Options selected cost
        double electronicTollTagFee = 0.00;
        if (wantsTag) {
            electronicTollTagFee = 3.95 * numberOfDays;
        }
        double gpsFee = 0.00;
        if (wantsGps) {
            gpsFee = 2.95 * numberOfDays;
        }
        double aaaFee = 0.00;
        if (wantsAAA) {
            aaaFee = 3.95 * numberOfDays;
        }
        double optionalsTotal = electronicTollTagFee + gpsFee + aaaFee;

        System.out.printf("The fee for the optional selections is: $%.2f%n", optionalsTotal);

        // TODO - underage driver surcharge (if applicable)
        double underageSurcharge = 0.00;
        if (driverage < 25) {
            underageSurcharge = basicCarRental * .30;
            System.out.printf("The underage surcharge (under 25) is %.2f%n", underageSurcharge);
        } else {
            System.out.println("You have no underage surcharge! Yay! ");
        }

        // TODO - total cost / sum
        // Basic car rental is $29.99 per day. 30% surcharge on the basic car rental for drivers under 25. All taxes
        // have been incorporated into the fees shown.
        double total = (basicCarRental + optionalsTotal + underageSurcharge);
        System.out.printf("Your total is $%.2f ", total);

    } // ! main method closing bracket

// ! other methods below this comment

    // * These are public static variables. They belong to the entire class and are created immediately at program
    // * start.
    // * They hold ANSI escape codes - instructions that tell the console to change the color of the text printed next.
    // * The RESET code reverts to the standard print color scheme - without this, every line printed after an ANSI code
    // * will be that color (til end of time/program)
    public static String RESET = "\u001B[0m";
    public static String RED = "\u001B[31m";
    public static String GREEN = "\u001B[32m";
    public static String BLUE = "\u001B[34m";

    // * This is a public static String. This is a method that anyone can use (public) and returns a String when done.
    // * Parameters color and originalMessage are two boxes to fill in when you call this method. You will input
    // * what color you want, and the message / text you want to be that color next.
    // * The return statement is the action - it takes the color, glues it to the text, and sticks the RESET code on
    // * the end so everything after is a normal color scheme and not whatever color you chose.
    public static String makeColorScheme(String color, String originalMessage) {
        return color + originalMessage + RESET;

    }

    // * This is my public static scanner. This is a scanner that anyone can use (public) and it receives user input.
    public static Scanner sc = new Scanner(System.in);

} // ! class closing bracket
