package Day4;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files

//529989 is too low
//538934 is too high

public class Day4_1 {

    static ArrayList<String> input = new ArrayList<String>(); // Create an ArrayList object
    static ArrayList<ArrayList<Integer>> winningNumbers = new ArrayList<ArrayList<Integer>>();  
    static ArrayList<ArrayList<Integer>> cardNumbers = new ArrayList<ArrayList<Integer>>();  
    static int[] scores;  

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
        scores = new int[input.size()];
        for (int i : scores) {
            scores[i] = 0;
        }

        for (int i  = 0; i < input.size(); i++) {
            String[] numbers = input.get(i).split(" \\| ", 0);

            String[] wStr = numbers[0].replace("  ", " ").trim().split(" ");
            String[] cStr = numbers[1].replace("  ", " ").trim().split(" ");

            ArrayList<Integer> temp = new ArrayList<Integer>();
            for (String num : wStr) {
                temp.add(Integer.valueOf(num));
            }
            winningNumbers.add(temp);
            temp = new ArrayList<Integer>();
            for (String num : cStr) {
                temp.add(Integer.valueOf(num));
            }
            cardNumbers.add(temp);
        }

        for (int y = 0; y < winningNumbers.size(); y++) {
            for (int x = 0; x < winningNumbers.get(y).size(); x++) {
                if (cardNumbers.get(y).contains(winningNumbers.get(y).get(x))) {
                    scores[y] = Math.max(scores[y]*2, 1);
                }
            }
        }

        int sum = 0;
        for (int i : scores) {
            sum += i;
        }
        System.out.println(sum);
    }
}