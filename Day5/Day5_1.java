package Day5;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files

public class Day5_1 {
    static long[] seeds = {487758422L,524336848L,2531594804L,27107767L,1343486056L,124327551L,1117929819L,93097070L,3305050822L,442320425L,2324984130L,87604424L,4216329536L,45038934L,1482842780L,224610898L,115202033L,371332058L,2845474954L,19257985L};
    static ArrayList<ArrayList<AlmanacMap>> almanacMaps = new ArrayList<ArrayList<AlmanacMap>>();
    static ArrayList<Long> results = new ArrayList<Long>();

    public static class AlmanacMap{
        long destinationStart;
        long sourceStart;
        long range;

        public AlmanacMap(long destinationStart, long sourceStart, long range){
            this.destinationStart = destinationStart;
            this.sourceStart = sourceStart;
            this.range = range;
        }

        public long getDestinationStart(){
            return destinationStart;
        }
        public long getSourceStart(){
            return sourceStart;
        }
        public long getRange(){
            return range;
        }
    }

    public static long convertSeed(long seed, ArrayList<AlmanacMap> maps) {
        for (AlmanacMap map : maps) {
            if (seed >= map.getSourceStart() && seed <= map.getSourceStart()+map.getRange()) {
                return map.getDestinationStart() + (seed - map.getSourceStart());
            }
        }
        return seed;
    }

    public static void main(String[] args) {
        int index = -1;
        try {
            Scanner reader = new Scanner(new File("testinput.txt"));

            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                if (data.contains("map:")) {
                    index++;
                    almanacMaps.add(new ArrayList<AlmanacMap>());
                } else if (!data.isEmpty()) {
                    String[] str = data.split(" ");
                    almanacMaps.get(index).add(new AlmanacMap(Long.valueOf(str[0]), Long.valueOf(str[1]), Long.valueOf(str[2])));
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        for (int x = 0; x < seeds.length; x += 2) {
            for (long i = seeds[x]; i < (seeds[x]+seeds[x+1]); i++) {
                long seed = i;
                for (ArrayList<AlmanacMap> map : almanacMaps) {
                    seed = convertSeed(seed, map);
                }
                results.add(seed);
            }
        }

        long min = results.get(0);
        for (long seed : results) {
            min = Math.min(min, seed);
        }
        System.out.println(min);
    }
}