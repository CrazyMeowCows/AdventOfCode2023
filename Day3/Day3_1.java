package Day3;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files

public class Day3_1 {

    static ArrayList<String> input = new ArrayList<String>(); // Create an ArrayList object

    public static void isPossible(String string) {
        
    }

    public static void main(String[] args) {
        try {
            File myObj = new File("testinput.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                input.add(data);
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        // int sum = 0;
        // for (String string : input) {
        //     sum += input.indexOf(string)+1;
        // }
        // System.out.println(sum);
    }
}