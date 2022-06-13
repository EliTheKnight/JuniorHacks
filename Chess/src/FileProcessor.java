import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class FileProcessor {

    public ArrayList[] readFile (){
        ArrayList[] output = new ArrayList[3];
        ArrayList<String> fens = new ArrayList<>();
        ArrayList<Integer> depth1 = new ArrayList<>();
        ArrayList<Integer> depth2 = new ArrayList<>();
        try {
            Scanner reader = new Scanner(new File("src/Chess960Perft.txt"));

            while (reader.hasNextLine()){
                String line = reader.nextLine();
                String fen = line.substring(line.indexOf("\t")+1);
                String depth1String = fen.substring(fen.indexOf("\t")+1);
                String depth2String = depth1String.substring(depth1String.indexOf("\t")+1);
                fen = fen.substring(0, fen.indexOf("\t")) + " ";
                depth1String = depth1String.substring(0, depth1String.indexOf("\t"));
                depth2String = depth2String.substring(0, depth2String.indexOf("\t"));

                Integer depth1Int = Integer.parseInt(depth1String);
                Integer depth2Int = Integer.parseInt(depth2String);

                fens.add(fen);
                depth1.add(depth1Int);
                depth2.add(depth2Int);

            }

        }catch (Exception e){e.printStackTrace();}
        output[0] = fens;
        output[1] = depth1;
        output[2] = depth2;
        return output;

    }

}
