package Day6;

import java.io.File;
import java.util.Scanner;

public class ColumnSort {
    public static void main(String[] args) {
        try {
            for (int i = 0; i < 8; i++) {
                Scanner reader = new Scanner(new File("src/Day6/Input.txt"));
                int[] arr = new int[26];

                while(reader.hasNextLine()){
                    String a = reader.nextLine();
                    char b = a.charAt(i);
                    int c = b-97;
                    arr[c] ++;
                }


                int maxIndex = 0;
                int maxValue = arr[0];
                for (int j = 1; j<26;j++){
                    if (arr[j] > maxValue){
                        maxIndex = j;
                        maxValue = arr[j];
                    }
                }
                char f = (char) (maxIndex+97);
                System.out.print(f);
            }
        }catch (Exception e) {e.printStackTrace();}
    }
}
