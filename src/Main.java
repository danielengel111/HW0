import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    /**
     * Prints a message according to a given grade.
     *
     * It is guaranteed that the grade is within the range [0, 100].
     *
     * @param grade The grade
     */
    public static void gradeMessage(int grade) {
        switch(grade / 10)
        {
            case (10):
                System.out.println("Excellent");
                break;
            case (9):
                System.out.println("Great");
                break;
            case (8):
                System.out.println("Very Good");
                break;
            case (7):
                System.out.println("Good");
                break;
            default:
                System.out.println("OK");
        }
    }

    /**
     * Compresses a given string.
     *
     * The compression process is done by replacing
     * a sequence of identical consecutive characters
     * with that same character followed by the length of sequence.
     *
     * It is guaranteed that the string contains only letters
     * (lowercase and uppercase).
     *
     * @param stringToCompress The string to compress
     * @return The compressed version of the string
     */
    public static String compressString(String stringToCompress) {
        String compressedString = ""; /*string that will contain the
        compressed version
        */

        int count = 0;
        for(int i = 0; i < stringToCompress.length(); i++)
        {
            count += 1; //count the number of times a letter appears in a row
            if(i + 1 == stringToCompress.length() || stringToCompress.charAt(i)
                    != stringToCompress.charAt(i + 1))
            { /*once we have reached the end of the identical sequence,
                we update the compressed version of the string, zero the
                counter, and move on to the next letter.
                */
                compressedString += stringToCompress.charAt(i);
                compressedString += count;
                count = 0;
                continue;
            }
        }
        return compressedString;
    }

    /**
     * Decompresses a given string.
     *
     * The decompression process is done by duplicating
     * each sequence of characters
     * according to the number which appears after the sequence.
     *
     * It is guaranteed that the string is a legal compressed string.
     *
     * @param compressedString The string to decompress
     * @return The decompressed string
     */
    public static String decompressString(String compressedString) {
        String decompressedString = "";

        int length = compressedString.length();
        String currentSequence = "";
        int Repetitions = 0;
        boolean isEndOfSequence = false; /* the flag will be true when we
        are trying to get how many repetitions the current sequence has
        but the next char is not a number, or if we are at the last char
        */

        for(int i = 0; i < length; i++) //for every letter
        {
            while((Character.isDigit(compressedString.charAt(i)))){
                //we are expecting only digits for now
                Repetitions *= 10;
                Repetitions += Integer.parseInt(String.valueOf(
                        compressedString.charAt(i)));
                if(i == length - 1 || !Character.isDigit(
                        compressedString.charAt(i+1))){
                    //if so, flag turns true
                    isEndOfSequence = true;
                    break;
                }
                i++;
            }
            if(isEndOfSequence) {//if so, add the current sequence
                //to the decompressedString
                while (Repetitions > 0) {
                    decompressedString += currentSequence;
                    Repetitions--;
                }
                currentSequence = ""; // initialize the current sequence
                isEndOfSequence = false;
            }else {
                currentSequence += compressedString.charAt(i);
                // we are not at the end so add the letter to the current
                // sequence
            }
        }
        return decompressedString;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String filePath = args[0];
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);

        // Tests for part A
        int numberOfGrades = scanner.nextInt();
        for (int i = 0; i < numberOfGrades; i++) {
            int grade = scanner.nextInt();
            gradeMessage(grade);
        }

        // Tests for part B1
        int numberOfStringsToCompress = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < numberOfStringsToCompress; i++) {
            String stringToCompress = scanner.nextLine();
            String compressedString = compressString(stringToCompress);
            System.out.println("The compressed version of " +
                    stringToCompress + " is " + compressedString);
        }

        // Tests for part B2
        int numberOfDecompressedStrings = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < numberOfDecompressedStrings; i++) {
            String compressedString = scanner.nextLine();
            String decompressedString = decompressString(compressedString);
            System.out.println("The decompressed version of " +
                    compressedString + " is " + decompressedString);
        }

        // Tests for both part B1 and B2
        int numberOfCombinedTests = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < numberOfCombinedTests; i++) {
            String stringToCompress = scanner.nextLine();
            String compressedString = compressString(stringToCompress);
            String decompressedString = decompressString(compressedString);
            System.out.println("decompress(compress(" + stringToCompress
                    + ")) == " + stringToCompress + "? " +
                    stringToCompress.equals(decompressedString));
        }
    }
}
