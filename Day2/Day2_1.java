package Day2;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files

public class Day2_1 {

    static ArrayList<String> input = new ArrayList<String>(); // Create an ArrayList object

    static final int maxRed = 12;
    static final int maxGreen = 13;
    static final int maxBlue = 14;

    public static boolean isPossible(String string) {
        string = string.split(":")[1].trim();
        string = string.replace(";", ",");
        String[] data = string.split(",");

        for (String str : data) {
            str = str.trim();
            if (str.contains("red") && Integer.valueOf(str.split(" ")[0]) > maxRed) {return false;}
            if (str.contains("green") && Integer.valueOf(str.split(" ")[0]) > maxGreen) {return false;}
            if (str.contains("blue") && Integer.valueOf(str.split(" ")[0]) > maxBlue) {return false;}
        }
        return true;
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
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        int sum = 0;
        for (String string : input) {
            if (isPossible(string)) {
                sum += input.indexOf(string)+1;
            }
        }
        System.out.println(sum);
    }
}