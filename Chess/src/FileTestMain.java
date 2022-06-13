import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileTestMain {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File("src/Chess960Perft.txt"));
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src/MassText.txt"), "utf-8"));
            Scanner finalReader = new Scanner(new File("src/MassText.txt"));
            FileProcessor reader = new FileProcessor();
            ArrayList[] positionInfo = reader.readFile();

            ArrayList<Integer> depth1 = new ArrayList<>();
            ArrayList<Integer> depth2 = new ArrayList<>();

            for (int i = 0; i < positionInfo[0].size(); i++) {
                ChessBoard board = new ChessBoard(768, 768 + 22, positionInfo[0].get(i).toString(), writer);
            }
            writer.close();

            while(finalReader.hasNextLine() && finalReader.hasNextLine()){
                int first = Integer.parseInt(finalReader.nextLine());
                int second = Integer.parseInt(finalReader.nextLine());
                depth1.add(first);
                depth2.add(second);
            }

            for (int i = 0; i< depth2.size() && i < positionInfo[2].size(); i++){
                if (depth1.get(i) != positionInfo[1].get(i)){
                    System.out.println(positionInfo[0].get(i).toString() + "    " + positionInfo[1].get(i) + "    " + depth1.get(i));
                }
//                else if (depth2.get(i) != positionInfo[2].get(i)){
//                    System.out.println(positionInfo[0].get(i).toString() + "    " + positionInfo[2].get(i) + "    " + depth2.get(i));
//                }
            }

        }catch (Exception e){e.printStackTrace();}


    }
}
