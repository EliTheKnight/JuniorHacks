import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.ScriptException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.gargoylesoftware.htmlunit.javascript.JavaScriptErrorListener;
import org.w3c.dom.Element;
import org.w3c.dom.html.HTMLTableElement;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WebTest {
    public static void main(String[] args) {

        try {
            Scanner reader = new Scanner(new File("cardTable.txt"));
            int size = 100;
            String[][] cardInfo = new String[size][3];
            for (int item = 0; item < size; item++) {
                if (reader.nextLine().contains("<tr>")) {
                    String a = reader.nextLine();
                    String b = reader.nextLine();
                    String c = reader.nextLine();
                    String d = reader.nextLine();
                    String e = reader.nextLine();
                    String f = reader.nextLine();
                    String g = reader.nextLine();
                    String h = reader.nextLine();
                    String i = reader.nextLine();
                    String j = reader.nextLine();
                    String k = reader.nextLine();
                    String l = reader.nextLine();
                    String m = reader.nextLine();
                    String n = reader.nextLine();
                    String o = reader.nextLine();
                    String p = reader.nextLine();
                    String q = reader.nextLine();
                    String r = reader.nextLine();
                    String s = reader.nextLine();
                    String t = reader.nextLine();
                    String u = reader.nextLine();
                    String v = reader.nextLine();
                    String w = reader.nextLine();
                    String x = reader.nextLine();
                    String y = reader.nextLine();
                    String z = reader.nextLine();
                    String aa = reader.nextLine();
                    String ab = reader.nextLine();
                    String ac = reader.nextLine();
                    String ad = reader.nextLine();
                    String ae = reader.nextLine();
                    String af = reader.nextLine();

                    c = c.substring(44);
                    h = h.substring(44);
                    l = l.substring(23);
                    cardInfo[item][0] = c;
                    cardInfo[item][1] = h;
                    cardInfo[item][2] = l;
                    // c is date            h is grade              l is price
                }
            }
            for (String[] row: cardInfo){
                    System.out.println("[" + row[0] + ", " + row[1] + ", " + row[2] + "]");
            }
        }catch (Exception e){e.printStackTrace();}
    }
}
