import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class DataMethods {

    public ArrayList<String> SortData(ArrayList<String[][]> data, ArrayList<String> cost){
        ArrayList<String> output = new ArrayList<>();
        ArrayList<String> grades = fileToList("grades");
        System.out.println("data Check");
        for (int i = 0; i<data.size() && i<grades.size(); i++){
            int grade = -1;
            if (Integer.parseInt(grades.get(i)) != -1){
            grade = Integer.parseInt(grades.get(i));
            }
            else{continue;}

            ArrayList<Integer> index = new ArrayList<>();
            String[][] array = data.get(i);

            if (array.length<1){continue;}

            for (int z = 0; z < array.length; z++){
                if (array[z][1] == null){break;}
                int gradedValue = Integer.parseInt(array[z][1]);
                if (gradedValue == grade){
                    index.add(z);
                }
            }
            if (index.size() > 3){while(index.size() > 3){index.remove(3);}}

            double totalPrice = 0;
            for (int a = 0; a < index.size(); a++){
                String replace = array[a][2];
                if (array[a][2].contains(",")){replace = replace.substring(0,replace.indexOf(",")) + replace.substring(replace.indexOf(",")+1);}
                totalPrice = totalPrice + Double.parseDouble(replace);
            }
            double averagePrice = totalPrice/index.size();
            if (cost.get(i) != null && Double.parseDouble(cost.get(i))<averagePrice) {
                ArrayList<String> list = fileToList("list");
                ArrayList<String> urls = fileToList("urls");
                output.add("Current Price: " + cost.get(i) + "  ,  " + "Recent Price: " + averagePrice + ";   " + list.get(i) + "   ,   " + urls.get(i));
            }
        }
        System.out.println("end data");
        return output;
    }

    public ArrayList<String> fileToList(String filename){
        ArrayList<String> output = new ArrayList();
        try {
            Scanner reader = new Scanner(new File(filename + ".txt"));
            while (reader.hasNextLine()){
                String line = reader.nextLine().toLowerCase();
                output.add(line);
            }
        }catch (Exception e){e.printStackTrace();}
        return output;
    }

}
