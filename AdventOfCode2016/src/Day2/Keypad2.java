package Day2;

import java.io.File;
import java.util.Scanner;

public class Keypad2 {
    public static void main(String[] args) {

        int[][] pad = {{0,0,1,0,0},{0,2,3,4,0},{5,6,7,8,9},{0,10,11,12,0},{0,0,13,0,0}};

        int row = 2;
        int col = 0;
        try {
            Scanner reader = new Scanner(new File("src/Day2/Input.txt"));
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                for (int i = 0; i<line.length();i++) {
                    String move = line.substring(i, i + 1);
                    if (move.equals("U") && row>0 && pad[row-1][col] != 0){row--;}
                    if (move.equals("D") && row<4 && pad[row+1][col] != 0){row++;}
                    if (move.equals("R") && col<4 && pad[row][col+1] != 0){col++;}
                    if (move.equals("L") && col>0 && pad[row][col-1] != 0){col--;}
                }
                System.out.println(pad[row][col]);
            }
        }catch (Exception e){e.printStackTrace();}
    }
}
