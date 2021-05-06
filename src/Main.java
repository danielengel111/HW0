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
     * The compression process is done by replacing a sequence of identical consecutive characters
     * with that same character followed by the length of sequence.
     *
     * It is guaranteed that the string contains only letters (lowercase and uppercase).
     *
     * @param stringToCompress The string to compress
     * @return The compressed version of the string
     */
    public static String compressString(String stringToCompress) {
        String compressedString = "";

        int count = 0;
        for(int i = 0; i < stringToCompress.length(); i++)
        {
            count += 1;
            if(i + 1 == stringToCompress.length() || stringToCompress.charAt(i) != stringToCompress.charAt(i + 1))
            {
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
     * The decompression process is done by duplicating each sequence of characters
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
        String temp_str = "";
        int temp_int = 0;
        boolean is_end_of_sequence = false;

        for(int i = 0; i < length; i++)
        {
            while((Character.isDigit(compressedString.charAt(i)))){
                temp_int *= 10;
                temp_int += Integer.parseInt(String.valueOf(compressedString.charAt(i)));
                if(i == length - 1 || !Character.isDigit(compressedString.charAt(i+1))){
                    is_end_of_sequence = true;
                    break;
                }
                i++;
            }
            if(is_end_of_sequence) {
                while (temp_int > 0) {
                    decompressedString += temp_str;
                    temp_int--;
                }
                temp_str = "";
                is_end_of_sequence = false;
            }else {
                temp_str += compressedString.charAt(i);
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
            System.out.println("The compressed version of " + stringToCompress + " is " + compressedString);
        }

        // Tests for part B2
        int numberOfDecompressedStrings = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < numberOfDecompressedStrings; i++) {
            String compressedString = scanner.nextLine();
            String decompressedString = decompressString(compressedString);
            System.out.println("The decompressed version of " + compressedString + " is " + decompressedString);
        }

        // Tests for both part B1 and B2
        int numberOfCombinedTests = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < numberOfCombinedTests; i++) {
            String stringToCompress = scanner.nextLine();
            String compressedString = compressString(stringToCompress);
            String decompressedString = decompressString(compressedString);
            System.out.println("decompress(compress(" + stringToCompress + ")) == " + stringToCompress + "? " + stringToCompress.equals(decompressedString));
        }
    }
}
