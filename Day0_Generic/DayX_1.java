package Day0_Generic;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files

public class DayX_1 {

    static ArrayList<String> input = new ArrayList<String>(); // Create an ArrayList object

    public static class Class{
        int i;

        public Class(int i, int length, int value){
            this.i = i;
        }

        public int getI(){
            return i;
        }
    }

    public static void method() {

    }

    public static void main(String[] args) {
        try {
            Scanner reader = new Scanner(new File("input.txt"));

            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                input.add(data.split(":")[1]);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
}