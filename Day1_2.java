import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files

// > 5210 < 5309

public class Day1_2 {

    static String[] convertFromString = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"}; 
    
    static ArrayList<String> input = new ArrayList<String>(); // Create an ArrayList object
    static ArrayList<Integer> firstNumber = new ArrayList<Integer>(); // Create an ArrayList object
    static ArrayList<Integer> lastNumber = new ArrayList<Integer>(); // Create an ArrayList object

    public static int getFirstNumber(String string) {
        for (int i = 0; i < string.length(); i++) {
            if (Character.isDigit(string.charAt(i))) {
                return Character.getNumericValue(string.charAt(i));
            }
            for (int x = 0; x < convertFromString.length; x++) {
                if (string.substring(0, i+1).contains(convertFromString[x])) {
                    return (x+1);
                }
            }
        }
        return -1;
    }

    public static int getLastNumber(String string) {
        for (int i = string.length()-1; i >= 0; i--) {
            if (Character.isDigit(string.charAt(i))) {
                return Character.getNumericValue(string.charAt(i));
            }
            for (int x = 0; x < convertFromString.length; x++) {
                if (string.substring(i).contains(convertFromString[x])) {
                    return (x+1);
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        try {
            File myObj = new File("input.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                input.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {}

        int sum = 0;
        for (String string : input) {
            System.out.println(getLastNumber(string));
            sum += Integer.valueOf("" + getFirstNumber(string) + getLastNumber(string));
        }
        System.out.println(sum);

        // System.out.println("abcde".substring(0, 5));
    }
}