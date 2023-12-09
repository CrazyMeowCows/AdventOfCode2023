package Day3;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files

public class Day3_2 {

    static ArrayList<String> input = new ArrayList<String>(); // Create an ArrayList object

    public static int getPower(String string) {
        int minRed = 0;
        int minGreen = 0;
        int minBlue = 0;

        string = string.split(":")[1].trim();
        string = string.replace(";", ",");
        String[] data = string.split(",");

        for (String str : data) {
            str = str.trim();
            int numx = Integer.valueOf(str.split(" ")[0]);

            if (str.contains("red")) {
                minRed = Math.max(minRed, numx);
            }
            if (str.contains("green")) {
                minGreen = Math.max(minGreen, numx);
            }
            if (str.contains("blue")) {
                minBlue = Math.max(minBlue, numx);
            }
        }
        return minRed*minGreen*minBlue;
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
            sum += getPower(string);
        }
        System.out.println(sum);
    }
}