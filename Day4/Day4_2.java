package Day4;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files

public class Day4_2 {

    static ArrayList<String> input = new ArrayList<String>(); // Create an ArrayList object
    static ArrayList<ArrayList<Integer>> winningNumbers = new ArrayList<ArrayList<Integer>>();  
    static ArrayList<ArrayList<Integer>> cardNumbers = new ArrayList<ArrayList<Integer>>();  
    static int[] scores;

    static ArrayList<ArrayList<Integer>> cards = new ArrayList<ArrayList<Integer>>();  

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
                    scores[y]++;
                }
            }
        }

        ArrayList<Integer> level0 = new ArrayList<Integer>();
        for (int i = 0; i < scores.length; i++) {
            level0.add(i);
        }
        cards.add(level0);

        int level = 1;
        while (cards.get(level-1).size() > 0) {
            ArrayList<Integer> levelx = new ArrayList<Integer>();
            for (int card : cards.get(level-1)) {
                for (int x = 1; x <= scores[card]; x++) {
                    levelx.add(x+card);
                }
            }
            cards.add(levelx);
            level++;
        }

        int sum = 0;
        for (int x = 0; x < level; x++) {
            for (int i : cards.get(x)) {
                // System.out.println(i+1);
                sum++;
            }
        }
        System.out.print(sum);
    }
}