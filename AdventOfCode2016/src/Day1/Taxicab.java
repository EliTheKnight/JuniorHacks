package Day1;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Taxicab {
        public static void main(String[] args){
            int facing = 0;
            int x = 0;
            int y = 0;
            ArrayList<Point> locations = new ArrayList<>();
            try {
                Scanner reader = new Scanner(new File("src/Day1/Input.txt"));
                while(reader.hasNext()) {
                    String step = reader.next();
                    String dir = step.substring(0,1);
                    int dist = Integer.parseInt(step.substring(1));

                    if (dir.equals("R")){facing++;}
                    if (dir.equals("L")){facing+=3;}

                    for (int i = 0; i<dist;i++) {
                        if (facing % 4 == 0) {
                            y ++;
                        }
                        if (facing % 4 == 1) {
                            x ++;
                        }
                        if (facing % 4 == 2) {
                            y --;
                        }
                        if (facing % 4 == 3) {
                            x --;
                        }

                        Point a = new Point(x, y);
                        for (Point b : locations) {
                            if (a.equals(b)) {
                                System.out.println(a);
                            }
                        }
                        locations.add(a);
                    }

                }

                System.out.println(Math.abs(x) + Math.abs(y));

            }catch (Exception e){e.printStackTrace();}


        }
}
