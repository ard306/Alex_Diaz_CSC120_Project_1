import java.util.Scanner;

/**
 * The Florida Dental program meant to take in, store, and modify dental information of given
 * family members. Can be used to store incisors, bicuspids, and missing teeth
 * for each individual. Once completed, each individual record can be printed,
 * have teeth extracted, or report the families root canal indices through using
 * the equation Ix^2+Bx-M.
 *
 * @author Alex Diaz
 */

public class Florida_Dental {

    public static final int MINIMUM_PEOPLE_IN_FAM = 1;
    public static final int MAXIMUM_PEOPLE_IN_FAM = 6;
    public static final int TEETH_LAYERS = 2;
    public static final int NUMBER_OF_TEETH = 8;

    public static final char INCISOR = 'I';
    public static final char BICUSPID = 'B';
    public static final char MISSING_TOOTH = 'M';

    public static final char PRINT_RECORDS = 'P';
    public static final char EXTRACT_TOOTH = 'E';
    public static final char CALCULATE_ROOTS = 'R';
    public static final char EXIT_PROGRAM = 'X';

    private static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Welcome to the Floridian Tooth Records");
        System.out.println("--------------------------------------");

        int famSize = getFamilySize();

        String[] names = new String[famSize];

        char[][][] teeth = new char[famSize][TEETH_LAYERS][NUMBER_OF_TEETH];

        getFamilyRecords(names, teeth);

        char choice;
        do {

            System.out.println();

            displayMenu();

            choice = Character.toUpperCase(
                    keyboard.next().charAt(0));

            if (choice == PRINT_RECORDS) {
                printRecords(names, teeth);
            }
            else if (choice == EXTRACT_TOOTH) {
                editTooth(names, teeth);
            }
            else if (choice == CALCULATE_ROOTS) {
                calculateRoots(teeth);
            }
            else if (choice == EXIT_PROGRAM) {

                System.out.println();

                System.out.println("Exiting the Floridian Tooth Records :-)");
            }
            else {
                System.out.println(
                        "Invalid menu option, try again: ");
            }

        } while (choice != EXIT_PROGRAM);

        keyboard.close();
    }//End of the main method.

    /**
     *
     * getFamilySize() method is used to get the number of family members that
     * can be no more than 6 and no less than 1. It will then use the size to
     * collect the dental data for each individual in the family.
     *
     * @return the size of the family to be used for data collection.
     */

    public static int getFamilySize(){
        int famSize;

        System.out.print("Please enter number of people in the family: ");
        famSize = keyboard.nextInt();

        while (famSize < MINIMUM_PEOPLE_IN_FAM || famSize > MAXIMUM_PEOPLE_IN_FAM) {
            System.out.print("Invalid number of people, try again: ");
            famSize = keyboard.nextInt();
        }//end of while statement.

        return famSize;
    }//End of the getFamilySize method.

    /**
     *
     * The getFamilyRecords method is used to gather the data for the family dental information.
     * This includes the famNames of the family members and the famTeeth for each family member
     * on both the upper and lower jaws.
     *
     * @param famNames
     * @param famTeeth
     */

    public static void getFamilyRecords(String[] famNames, char[][][] famTeeth) {

        for (int personIndex = 0; personIndex < famNames.length; personIndex++) {

            System.out.print("Enter the name of family member " + (personIndex + 1) + ": ");

            famNames[personIndex] = keyboard.next();

            for (int mouthLayerIndex = 0; mouthLayerIndex < TEETH_LAYERS; mouthLayerIndex++) {

                if (mouthLayerIndex == 0) {
                    System.out.print("Enter the uppers for " + famNames[personIndex] + ": ");
                }
                else {
                    System.out.print(
                            "Enter the lowers for " + famNames[personIndex] + ": ");
                }

                String famRecord;

                do {

                    famRecord = keyboard.next().toUpperCase();

                    if (famRecord.length() > NUMBER_OF_TEETH) {
                        System.out.print("Too many teeth, try again: ");
                    }
                    else if (isRecordValid(famRecord) == 0) {
                        System.out.print("Invalid teeth types, try again: ");
                    }

                } while (famRecord.length() > NUMBER_OF_TEETH || isRecordValid(famRecord) == 0);
                //End of do-while loop.


                for (int tooth = 0; tooth < famRecord.length(); tooth++) {
                    famTeeth[personIndex][mouthLayerIndex][tooth] = famRecord.charAt(tooth);
                }//End of number of famTeeth for loop.

            }//End of mouth layer for loop.

        }//End of family member for loop

    }//End of getFamilyRecords method.

    /**
     *
     * This method is used to determine whether the entered teeth are valid for the famRecord.
     * This is done by checking to see whether each character in the famRecord is either B,I, or M.
     * If the character is not one of the following, the method sends an error and allows the user
     * to retry.
     *
     * @param famRecord: The previously entered characters that represent each tooth of the individual
     *              family members.
     *
     * @return valid: if the characters are valid, the program will continue, and the loop will make
     * the user try again if it is not.
     */

    public static int isRecordValid(String famRecord) {

        int valid = 1;

        for (int validityIndex = 0; validityIndex < famRecord.length(); validityIndex++) {

            char tooth = famRecord.charAt(validityIndex);

            if (tooth == INCISOR) {
                valid = 1;
            }
            else if (tooth == BICUSPID) {
                valid = 1;
            }
            else if (tooth == MISSING_TOOTH) {
                valid = 1;
            }
            else {
                valid = 0;
                break;
            }//End of if/else if/else statements for validity check.
        }//End of for loop for validity check.

        return valid;
    }//End of isRecordValid method.

    /**
     *
     * The displayMenu method is used to display the menu options after all the
     * family record input values have been taken. The options provided to the user are
     * print, extract, roots, and exit. The method uses previously declared constants
     * for each function.
     *
     */

    public static void displayMenu(){

        System.out.print("(" + PRINT_RECORDS + ")" + "rint, ");
        System.out.print("(" + EXTRACT_TOOTH + ")" + "xtract, ");
        System.out.print("(" + CALCULATE_ROOTS + ")" + "oots, ");
        System.out.print("e" + "(" + EXIT_PROGRAM + ")" + "it: ");

    }//End of the displayMenu method.

    /**
     *
     * This method is used to display the family records. This method displays
     * each family member's name with their respective teeth at the position
     * for both the upper and lower jaws.
     *
     * @param famNames This parameter uses the previous array for the names
     *                  of each family member to display the names.
     * @param famTeeth This parameter uses the previous array for the teeth
     *                 of the family records to display the teeth for
     *                 each family member.
     */

    public static void printRecords(String[] famNames, char[][][] famTeeth) {

        for (int personIndex = 0; personIndex < famNames.length; personIndex++) {

            System.out.println(famNames[personIndex]);

            System.out.print("  Uppers: ");

            for (int toothIndex = 0; toothIndex < NUMBER_OF_TEETH; toothIndex++) {

                if (famTeeth[personIndex][0][toothIndex] != '\0') {
                    System.out.print("  " + (toothIndex + 1) + ":" + famTeeth[personIndex][0][toothIndex]);
                }//End of if statement for upper tooth.
            }//End of for loop for upper teeth.

            System.out.println();

            System.out.print("  Lowers: ");

            for (int tooth = 0; tooth < NUMBER_OF_TEETH; tooth++) {

                if (famTeeth[personIndex][1][tooth] != '\0') {
                    System.out.print("  " + (tooth + 1) + ":" + famTeeth[personIndex][1][tooth]);
                }//End of if statement for lower tooth.
            }//End of for loop for lower teeth.

            System.out.println();

        }//End of the print for loop.

    }//End of the printRecords method.

    /**
     *
     * This method is used to extract a tooth of a specific family member
     * at a specific position. This method will update the record so that
     * the extracted tooth will now be marked as missing for the family member
     *
     *
     * @param famNames This parameter is used to find the name of the family member
     *                 in the array and take the correct data corresponding to the
     *                 array for the teeth.
     * @param famTeeth This parameter is used to find the teeth at the designated
     *                 position to then extract it (replace I or B with M at a certatin
     *                 position).
     */

    public static void editTooth(String[] famNames, char[][][] famTeeth) {

        System.out.print("Which family member: ");
        String selectedName = keyboard.next();

        int person = -1;

        for (int index = 0; index < famNames.length; index++) {
            if (famNames[index].equalsIgnoreCase(selectedName)) {
                person = index;
            }//End of if statement for finding the specific name in the array.

        }//End of for loop for finding the specific name in the array.

        while (person == -1) {
            System.out.print("Invalid family member, try again: ");
            selectedName = keyboard.next();

            for (int index = 0; index < famNames.length; index++) {
                if (famNames[index].equalsIgnoreCase(selectedName)) {
                    person = index;
                }//End of if statement for finding a specific name with an error message.

            }//End of for loop for finding a specific name with an error message.

        }//End of while loop for finding a specific name with an error message.

        System.out.print("Which tooth layer (U)pper or (L)ower: ");
        char layer = Character.toUpperCase(keyboard.next().charAt(0));

        while (layer != 'U' && layer != 'L') {
            System.out.print("Invalid layer, try again: ");
            layer = Character.toUpperCase(keyboard.next().charAt(0));
        }//End of second while loop for an input error.

        int jaw = 0;

        if (layer == 'U') {
            jaw = 0;
        }
        else if (layer == 'M'){
            jaw = 1;
        }

        int recordLength = 0;

        for (int toothIndex = 0; toothIndex < NUMBER_OF_TEETH; toothIndex++) {
            if (famTeeth[person][jaw][toothIndex] != '\0') {
                recordLength++;
            }
        }

        System.out.print("Which tooth number: ");
        int toothNumber = keyboard.nextInt();

        while (toothNumber < 1 || toothNumber > recordLength ||
                famTeeth[person][jaw][toothNumber - 1] == MISSING_TOOTH) {

            if (toothNumber < 1 || toothNumber > recordLength) {
                System.out.print("Invalid tooth number, try again: ");
            }
            else {
                System.out.print("Missing tooth, try again: ");
            }

            toothNumber = keyboard.nextInt();
        }

        famTeeth[person][jaw][toothNumber - 1] = MISSING_TOOTH;
    }//End of editTooth method.

    /**
     *
     * This method analyses the root canal indices by calculating the roots of the
     * data using the equation Ix^2 + Bx - M. The method then reports both of the
     * roots of the calculations. This method includes the following cases: two possible roots,
     * one possible root, and no possible roots.
     *
     * @param famTeeth The famTeeth parameter is used since the data required to
     *                 find the roots is present within the three-dimensional array
     *                 famTeethm.
     */

    public static void calculateRoots(char[][][] famTeeth) {

        int incisors = 0;
        int bicuspids = 0;
        int missing = 0;

        for (int personIndex = 0; personIndex < famTeeth.length; personIndex++) {

            for (int jawIndex = 0; jawIndex < TEETH_LAYERS; jawIndex++) {

                for (int toothIndex = 0; toothIndex < NUMBER_OF_TEETH; toothIndex++) {

                    if (famTeeth[personIndex][jawIndex][toothIndex] == INCISOR) {
                        incisors++;
                    }
                    else if (famTeeth[personIndex][jawIndex][toothIndex] == BICUSPID) {
                        bicuspids++;
                    }
                    else if (famTeeth[personIndex][jawIndex][toothIndex] == MISSING_TOOTH) {
                        missing++;
                    }//End of if/if-else statements for locating each toothIndex type.

                }//End of for loop for locating toothIndex types.

            }//End of for loop for viewing the jaws.

        }//End of the for loop for viewing each person in the family.

        double a = incisors;
        double b = bicuspids;
        double c = -missing;

        if (a == 0) {

            if (b != 0) {
                double root = -c / b;
                System.out.printf("One root canal at %8.2f%n", root);
            }//End of if statement for one possible root.

            else {
                System.out.println("The family has no root canals.");
            }//End of if statement for no roots.

        }//End of if statements for if incisors = 0.

        else {

            double discriminant = b * b - 4 * a * c;

            if (discriminant > 0) {

                double root1 =
                        (-b + Math.sqrt(discriminant)) / (2 * a);

                double root2 =
                        (-b - Math.sqrt(discriminant)) / (2 * a);

                System.out.printf("One root canal at %8.2f%n", root1);
                System.out.printf("Another root canal at %6.2f%n", root2);
            }//End of if statement for two possible roots

            else if (discriminant == 0) {

                double root = -b / (2 * a);
                System.out.printf("One root canal at  %8.2f%n", root);
            }//End of if statement for one possible root.

            else {
                System.out.println("The family has no root canals.");
            }//End of if statement for no possible roots
        }//End of else statement for a!=0.

    }//End of calculateRoots method.

}//End of the Florida_Dental_Association Class.
