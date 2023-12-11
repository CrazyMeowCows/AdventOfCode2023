package Day5;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files

public class Day5_1 {
    static long[] seeds = {2845474954L,19257985L};
    static ArrayList<ArrayList<AlmanacMap>> almanacMaps = new ArrayList<ArrayList<AlmanacMap>>();

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
            Scanner reader = new Scanner(new File("input.txt"));

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

        long min = Long.MAX_VALUE;
        for (int x = 0; x < seeds.length; x += 2) {
            for (long i = seeds[x]; i < (seeds[x]+seeds[x+1]); i++) {
                long seed = i;
                for (ArrayList<AlmanacMap> map : almanacMaps) {
                    seed = convertSeed(seed, map);
                }
                min = Math.min(min, seed);
            }
        }
        System.out.println(min);
    }
}