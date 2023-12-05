import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files

public class Day1_2 {

    static String[] convertString = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"}; 
    static String[] convertNumber = {"1", "2", "3", "4", "5", "6", "7", "8", "9"}; 
    
    static ArrayList<String> input = new ArrayList<String>(); // Create an ArrayList object
    static ArrayList<ArrayList> cleanedInput = new ArrayList<ArrayList>(); // Create an ArrayList object

    public static class Value{
        int val;
        int index;

        public Value(int val, int index){
            this.val = val;
            this.index = index;
        }

        public int getIndex(){
            return index;
        }

        public int getValue(){
            return val;
        }
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

        for (String string : input) {
            ArrayList<Value> temp = new ArrayList<Value>();
            for (int i = 0; i < convertString.length; i++) {
                if(string.indexOf(convertString[i]) >= 0) {
                    temp.add(new Value(i+1, string.indexOf(convertString[i])));
                }
            }
            for (int i = 0; i < convertNumber.length; i++) {
                if(string.indexOf(convertNumber[i]) >= 0) {
                    temp.add(new Value(i+1, string.indexOf(convertNumber[i])));
                }
            }
            cleanedInput.add(temp);
            for (Value val : temp) {
                System.out.println("Value: " + val.getValue() + " | Index: " + val.getIndex());
            }
        }
        

        // System.out.println(input.get(0));
    }
}