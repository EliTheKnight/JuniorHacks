package Day3;

import java.io.File;
import java.util.Scanner;

public class TriangleCounter {

    public static void main(String[] args) {
        try {
            Scanner reader = new Scanner(new File("src/Day3/Input.txt"));
            int count = 0;
            while (reader.hasNext()) {
                String one = reader.next();
                String two = reader.next();
                String three = reader.next();
                int first = Integer.parseInt(one.substring(0));
                int second = Integer.parseInt(two.substring(0));
                int third = Integer.parseInt(three.substring(0));

                if (first >= second && first >= third){if (second + third > first) {count ++;}}
                else if (second >= first && second >= third){if (first + third > second) {count ++;}}
                else if (third >= second && third >= first){if (first + second > third) {count ++;}}


            }
            System.out.println(count);
        }catch (Exception e){e.printStackTrace();}
    }
}
