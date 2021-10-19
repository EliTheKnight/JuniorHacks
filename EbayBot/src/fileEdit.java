import java.io.*;
import java.util.Scanner;

public class fileEdit {
    public static void main(String[] args) {

        try {
            Scanner reader = new Scanner(new File("bigListOfCards.txt"));
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("cardList.txt"), "utf-8"));
//            while (reader.hasNextLine()){
//                String line = reader.nextLine();
//                int end = line.indexOf("',");
//                if (line.contains("('")){
//                    line = line.substring(2,end);
//                    while (line.contains("_")){
//                        int a = line.indexOf("_");
//                        line = line.substring(0,a) + " " + line.substring(a+1);
//                    }
//                    writer.write(line + "\n");
//                }
//            }
                String lastLine = "";
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if (line.equalsIgnoreCase(lastLine)){}
                else {writer.write(line + "\n");}
                lastLine = line;
            }
        } catch (Exception e){e.printStackTrace();}
    }
}
