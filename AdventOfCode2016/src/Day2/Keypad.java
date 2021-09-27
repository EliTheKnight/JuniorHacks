package Day2;

import java.io.File;
import java.util.Scanner;

public class Keypad {
    public static void main(String[] args) {

        int[][] pad = {{1,2,3},{4,5,6},{7,8,9}};

        int row = 1;
        int col = 1;
        try {
            Scanner reader = new Scanner(new File("src/Day2/Input.txt"));
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                for (int i = 0; i<line.length();i++) {
                    String move = line.substring(i, i + 1);
                    if (move.equals("U") && row>0){row--;}
                    if (move.equals("D") && row<2){row++;}
                    if (move.equals("R") && col<2){col++;}
                    if (move.equals("L") && col>0){col--;}
                }
                System.out.println(pad[row][col]);
            }
        }catch (Exception e){e.printStackTrace();}
    }
}
