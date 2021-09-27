package Day4;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class RealRooms {
    public static void main(String[] args) {
        try {
            Scanner reader = new Scanner(new File("src/Day4/Input.txt"));
            int count = 0;
            while (reader.hasNextLine()){

                boolean yes = true;
                int[] arr = new int[26];
                ArrayList<Character> word = new ArrayList<>();
                String line = reader.nextLine();
                int stop = line.indexOf("[");
                int code = Integer.parseInt(line.substring(stop-3,stop));
                String name = line.substring(0,stop-4);
                String checksum = line.substring(stop+1,line.length()-1);
                int shift = code % 26;
                while (name.contains("-")){
                    int a = name.indexOf("-");
                    name = name.substring(0,a) + name.substring(a+1);
                }

                for (int i = 0; i<name.length();i++){
                    word.add(name.charAt(i));
                }

                for(Character a : word){
                    int c = a-97;
                    arr[c] ++;
                }

                for (int i = 0; i<5;i++){
                    int maxIndex = 0;
                    int maxValue = arr[0];
                    for (int j = 1; j<26;j++){
                        if (arr[j] > maxValue){
                            maxIndex = j;
                            maxValue = arr[j];
                        }
                    }

                    char c = (char) (maxIndex+97);
                    if (c != checksum.charAt(i)){
                        yes = false;
                        break;
                    }

                    arr[maxIndex] = 0;

                }

                if (yes){
                    for (int i = 0; i<name.length();i++){
                    char a = name.charAt(i);
                    int b = (((a-97)+shift)%26)+97;
                    char c = (char)b;
                        System.out.print(c);
                    }
                    System.out.println(code);
                }

            }
            System.out.println(count);
        }catch (Exception e){e.printStackTrace();}
    }
}
