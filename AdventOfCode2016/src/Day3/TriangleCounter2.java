package Day3;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class TriangleCounter2 {

    public static void main(String[] args) {
        try {
            Scanner reader = new Scanner(new File("src/Day3/Input.txt"));
            int count = 0;
            ArrayList<Integer> list = new ArrayList<>();
            while (reader.hasNext()) {
                list.add(Integer.parseInt(reader.next()));
            }
            for (int i = 0; i < list.size()-6; i++){
                if (i%3 == 0  && i > 1){
                    i+=6;
                }
                int first = list.get(i);
                int second = list.get(i+3);
                int third = list.get(i+6);

                if (first >= second && first >= third){if (second + third > first) {count ++;}}
                else if (second >= first && second >= third){if (first + third > second) {count ++;}}
                else if (third >= second && third >= first){if (first + second > third) {count ++;}}

            }
            System.out.println(count);
        }catch (Exception e){e.printStackTrace();}
    }
}

