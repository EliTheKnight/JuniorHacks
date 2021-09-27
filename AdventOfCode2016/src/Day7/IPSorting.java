package Day7;

import java.io.File;
import java.util.Scanner;

public class IPSorting {
    public static void main(String[] args) {
        try{
            Scanner reader = new Scanner(new File("src/Day7/input.txt"));
            int count = 0;
            while (reader.hasNextLine()){
                String line = reader.nextLine();
                int open = line.indexOf("[");
                int close = line.indexOf("]");
                String beginning = line.substring(0,open);
                String middle = line.substring(open+1,close);
                String end = line.substring(close+1);
                System.out.println(middle);
            }
        }catch (Exception e){e.printStackTrace();}
    }
}
