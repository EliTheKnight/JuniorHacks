import java.io.*;
import java.util.*;

public class PrimeMethods {

    public ArrayList<Integer> getPrimes(int max){

        ArrayList<Integer> list = new ArrayList<>();

        try (Reader reader =  new BufferedReader(new InputStreamReader(new FileInputStream("filename.txt"), "utf-8"))) {
            int yes = reader.read();
            if (yes != -1) {
                list.add(reader.read());
            }
        }catch (Exception e){e.printStackTrace();}

        if (max <= 1){ return list; }

        list.add(2);
        list.add(3);
        for (int i = list.get(list.size()-1); i<=max; i +=2){
            boolean prime = true;
            for (int n : list){
                if (i%n == 0){
                    prime = false;
                    break;
                }
            }
            if (prime)
                list.add(i);

        }
        return list;
    }


    // break    -> quit the current loop
    // continue -> quits the current iteration of the loop but continues the loop
}
