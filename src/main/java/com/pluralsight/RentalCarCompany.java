package com.pluralsight;

// ! import Scanner to receive user input

import java.util.Scanner;

public class RentalCarCompany {

    // * This is my public static scanner. This is a scanner that anyone can use (public) and it receives user input.
    public static Scanner sc = new Scanner(System.in);

    // * These are public static variables. They belong to the entire class and are created immediately at program
    // * start.
    // * They hold ANSI escape codes - instructions that tell the console to change the color of the text printed next.
    // * The RESET code reverts to the standard print color scheme - without this, every line printed after an ANSI code
    // * will be that color (til end of time/program)
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String BLUE = "\u001B[34m";

    // * These are public static constants for the numbers I'm using for calculations and the program.
    public static final double DAILY_RATE = 29.99;
    public static final double TOLL_TAG_FEE_PER_DAY = 3.95;
    public static final double GPS_FEE_PER_DAY = 2.95;
    public static final double AAA_FEE_PER_DAY = 3.95;
    public static final double SURCHARGE_RATE = .30;
    public static final int SURCHARGE_AGE_LIMIT = 25;

    public static void main(String[] args) {

        // * Welcome message:

        String welcome;
        welcome = makeColorScheme(BLUE, "Welcome to the Rental Car Calculator!\n");
        System.out.println(welcome);

        // ? - Ask for date they will pick up the car.
        String pickupAnswer = askQuestion(GREEN, """
        What day will you be picking up the rental car? (mm/dd/yyyy):
        """);

        // ? - Ask for how long they will be renting the car.
//        String duration;
//        duration = makeColorScheme(GREEN, "How long will you be renting a car for? (e.g. 7 days) \n");
//        System.out.println(duration);
//        String durationAnswer = sc.nextLine();
//        String durationNString = durationAnswer.replaceAll("[^0-9]", "");
//        int numberOfDays = Integer.parseInt(durationNString);
        int numberOfDays = askNumber(GREEN, "How long will you be renting a car for? (e.g. 7 days)\n");

        // ? - Ask for their age.
//        String ageSurcharge;
//        ageSurcharge = makeColorScheme(RED, """
//        Drivers under the age of 25 receive a 30% surcharge; how old are
//        you? (e.g. 26 years old)
//         """);
//        System.out.println(ageSurcharge);
//        String ageAnswer = sc.nextLine();
//        String ageNString = ageAnswer.replaceAll("[^0-9]", "");
//        int driverAge = Integer.parseInt(ageNString);
        int driverAge = askNumber(RED, """
                Drivers under the age of 25 receive a 30% surcharge; how old are you? (e.g. 26 years old)
                """);

        // ? - Optionals: Ask if they want a toll tag, GPS, or roadside assistance (fee per day)

        // ? Toll Tag Query
//        String electronicTollTag;
//        electronicTollTag = makeColorScheme(RED, """
//     Do you want an electronic toll tag for $3.95 per day of rental? (Yes/No)
//     """);
//        System.out.println(electronicTollTag);
//        String electronicTollTagAnswer = sc.nextLine();
//        boolean wantsTag = electronicTollTagAnswer.equalsIgnoreCase("yes");
        boolean wantsTag = askYesNo(RED, "Do you want an electronic toll tag for $3.95 per day of rental? (Yes/No)\n");

        // ? GPS Query
//        String gps;
//        gps = makeColorScheme(RED, "Do you want a GPS for $2.95 per day of rental? (Yes/No) \n");
//        System.out.println(gps);
//        String gpsAnswer = sc.nextLine();
//        boolean wantsGps = gpsAnswer.equalsIgnoreCase("yes");
        boolean wantsGps = askYesNo(RED, "Do you want a GPS for $2.95 per day of rental? (Yes/No) \n");

        // ? AAA Query
//        String aaa;
//        aaa = makeColorScheme(RED, """
//        Do you want roadside assistance for $3.95 per day of rental? (Yes/No)
//        """);
//        System.out.println(aaa);
//        String aaaAnswer = sc.nextLine();
//        boolean wantsAAA = aaaAnswer.equalsIgnoreCase("yes");
        boolean wantsAAA = askYesNo(RED, "Do you want roadside assistance for $3.95 per day of rental? (Yes/No)\n");

        // TODO - Calculate and dislay to the user:
        // TODO - Basic Car Rental cost
        double basicCarRental = DAILY_RATE * numberOfDays;
        System.out.printf("The basic car rental fee for %d days is $%.2f%n", numberOfDays, basicCarRental);

        // TODO - Options selected cost
        double electronicTollTagFee = 0.00;
        if (wantsTag) {
            electronicTollTagFee = TOLL_TAG_FEE_PER_DAY * numberOfDays;
        }
        double gpsFee = 0.00;
        if (wantsGps) {
            gpsFee = GPS_FEE_PER_DAY * numberOfDays;
        }
        double aaaFee = 0.00;
        if (wantsAAA) {
            aaaFee = AAA_FEE_PER_DAY * numberOfDays;
        }
        double optionalsTotal = electronicTollTagFee + gpsFee + aaaFee;

        System.out.printf("The fee for the optional selections is: $%.2f%n", optionalsTotal);

        // TODO - underage driver surcharge (if applicable)
        double underageSurcharge = 0.00;
        if (driverAge < SURCHARGE_AGE_LIMIT) {
            underageSurcharge = basicCarRental * SURCHARGE_RATE;
            System.out.printf("The underage surcharge (under 25) is %.2f%n", underageSurcharge);
        } else {
            System.out.println("You have no underage surcharge! Yay! ");
        }

        // TODO - total cost / sum
        // Basic car rental is $29.99 per day. 30% surcharge on the basic car rental for drivers under 25. All taxes
        // have been incorporated into the fees shown.
        double total = (basicCarRental + optionalsTotal + underageSurcharge);
        System.out.printf("Your total is $%.2f ", total);

        sc.close();
    } // ! main method closing bracket

    // * This is a public static String. This is a method that anyone can use (public) and returns a String when done.
    // * Parameters color and originalMessage are two boxes to fill in when you call this method. You will input
    // * what color you want, and the message / text you want to be that color next.
    // * The return statement is the action - it takes the color, glues it to the text, and sticks the RESET code on
    // * the end so everything after is a normal color scheme and not whatever color you chose.
    public static String makeColorScheme(String color, String originalMessage) {
        return color + originalMessage + RESET;
    }

    // * I noticed I had the same question format 6 times throughout my code - so I'm adding a static method
    // * for this so each question is one line.
    public static String askQuestion(String color, String prompt) {
        String coloredPrompt;
        coloredPrompt = makeColorScheme(color, prompt);
        System.out.println(coloredPrompt);
        String answer = sc.nextLine();
        return answer;
    }
    // * this is an ask question method but for numbers - doubles and ints. the replaceAll takes anything not through
    // * 0-9 and gets rid of it so we isolate numbers out of a string response.
    public static int askNumber(String color, String prompt) {
        String answer = askQuestion(color, prompt);
        String digitsOnly = answer.replaceAll("[^0-9]", "");
        int number = Integer.parseInt(digitsOnly);
        return number;
    }
    // * this is an ask question method but for the boolean user prompts: toll tag, gps, AAA.
    public static boolean askYesNo(String color, String prompt) {
        String answer = askQuestion(color, prompt);
        boolean saidYes = answer.equalsIgnoreCase("yes");
        return saidYes;
    }
} // ! class closing bracket
