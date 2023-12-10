package Day3;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files

public class Day3_2 {

    static ArrayList<String> input = new ArrayList<String>(); // Create an ArrayList object
    static ArrayList<ArrayList<Number>> numbers = new ArrayList<ArrayList<Number>>();  
    static ArrayList<Ratio> ratios = new ArrayList<Ratio>();  
    static int[][] gears;
    static int sum = 0;

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

    public static class Ratio{
        int gearX;
        int gearY;
        int value;

        public Ratio(int gearX, int gearY, int value){
            this.gearX = gearX;
            this.gearY = gearY;
            this.value = value;
        }

        public int getX(){
            return gearX;
        }

        public int getY(){
            return gearY;
        }

        public int getValue(){
            return value;
        }
    }

    public static void extractNumbers(String dataLine) {
        ArrayList<Number> extracted = new ArrayList<Number>();
        String[] data = dataLine.replace("*", "x").split("x");

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

    public static void findGears(int index, int line, int length, int value) {
        for (int y = line-1; y <= line+1; y++) {
            for (int x = index-1; x <= index+length; x++) {
                try { 
                    if (input.get(y).charAt(x) == '*') {
                        gears[y][x]++;
                        if (gears[y][x] <= 1) {
                            ratios.add(new Ratio(x, y, value));
                        } else {
                            for (Ratio ratio : ratios) {
                                if (ratio.getX() == x && ratio.getY() == y) {
                                    sum += ratio.getValue()*value;
                                }
                            }
                        }
                    }
                } catch (IndexOutOfBoundsException e) {}
            }
        }
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
        gears = new int[input.size()][input.size()];

        for (ArrayList<Number> line : numbers) {
            for (Number num : line) {
                findGears(num.getIndex(), numbers.indexOf(line), num.getLength(), num.value);
            }
        }

        // for (int y = 0; y < gears.length; y++) {
        //     for (int x = 0; x < gears[y].length; x++) {
        //         System.out.print(gears[y][x]);
        //     }
        //     System.out.println("");
        // }

        System.out.println(sum);
    }
}