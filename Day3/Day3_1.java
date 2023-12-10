package Day3;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files

//529989 is too low
//538934 is too high

public class Day3_1 {

    static ArrayList<String> input = new ArrayList<String>(); // Create an ArrayList object
    static ArrayList<ArrayList<Number>> numbers = new ArrayList<ArrayList<Number>>();  

    public static class Number{
        int index;
        int length;
        int value;

        public Number(int index, int length, int value){
            this.index = index;
            this.length = length;
            this.value = value;
        }

        public int getIndex(){
            return index;
        }

        public int getLength(){
            return length;
        }

        public int getValue(){
            return value;
        }
    }

    public static void extractNumbers(String dataLine) {
        ArrayList<Number> extracted = new ArrayList<Number>();
        String[] data = dataLine.replace("@", "x").split("x");

        for (int i = 0; i < data.length; i++) {
            if (!data[i].isEmpty()){
                int offset = 0;
                for (Number num : extracted) {
                    offset += num.getLength();
                }
                extracted.add(new Number(i+offset, data[i].length(), Integer.valueOf(data[i])));
            }
        }
        numbers.add(extracted);
    }

    public static boolean isNumberValid(int index, int line, int length) {
        for (int y = line-1; y <= line+1; y++) {
            for (int x = index-1; x <= index+length; x++) {
                try { 
                    if (input.get(y).charAt(x) == '@') {
                        return true;
                    }
                } catch (IndexOutOfBoundsException e) {}
            }
        }
        return false;
    }

    public static void main(String[] args) {
        try {
            File myObj = new File("input.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                input.add(data);
                extractNumbers(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        int sum = 0;
        for (ArrayList<Number> line : numbers) {
            for (Number num : line) {
                // System.out.print("Index: " + num.getIndex() + " | Length: " + num.getLength() + " Value: " + num.getValue() + " ");
                if(isNumberValid(num.getIndex(), numbers.indexOf(line), num.getLength())) {
                    sum += num.getValue();
                }
            }
            // System.out.println("");
        }
        System.out.println(sum);
    }
}