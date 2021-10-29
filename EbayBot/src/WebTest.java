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
            WebClient webClient = new WebClient(BrowserVersion.CHROME);

            webClient.getOptions().setThrowExceptionOnScriptError(false);
            webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
            webClient.setJavaScriptErrorListener(new JavaScriptErrorListener() {
                @Override
                public void scriptException(HtmlPage htmlPage, ScriptException e) {

                }

                @Override
                public void timeoutError(HtmlPage htmlPage, long l, long l1) {

                }

                @Override
                public void malformedScriptURL(HtmlPage htmlPage, String s, MalformedURLException e) {

                }

                @Override
                public void loadScriptError(HtmlPage htmlPage, URL url, Exception e) {

                }

                @Override
                public void warn(String s, String s1, int i, String s2, int i1) {

                }
            });

            HtmlPage page = webClient.getPage("https://www.pokemonprice.com/CardDetails/b5dc5bb2-b04b-4b97-95c4-0bc2238c0a8e/pikachu-1st-edition");


//            List<DomElement> list = page.getByXPath("//table/tbody/tr");
//            System.out.println(list.get(0));
//            System.out.println(list.size());

            String pageAsXml = page.asXml();
            String table = pageAsXml.substring(pageAsXml.indexOf("<tbody>"), pageAsXml.indexOf("</tbody>"));
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("cardTable.txt"), "utf-8"));
            writer.write(table);
            writer.close();
            Scanner reader = new Scanner(new File( "cardTable.txt"));

            String[][] cardInfo = new String[100][3];
            for (int item = 0; item<100; item++){
                if (reader.nextLine().contains("<tr>")){
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
                    System.out.println(a + "\n" + b+ "\n" + c+ "\n" + d+ "\n" + e+ "\n" + f+ "\n" + g+ "\n" + h+ "\n" + i+ "\n" + j+ "\n" + k+ "\n" + l+ "\n" + m+ "\n" + n+ "\n" + o+ "\n" + p+ "\n" + q+ "\n" + r+ "\n" + s+ "\n" + t+ "\n" + u+ "\n" + v+ "\n" + w+ "\n" + x+ "\n" + y+ "\n" + z+ "\n" + aa+ "\n" + ab+ "\n" + ac+ "\n" + ad+ "\n" + ae+ "\n" + af+ "\n");
                }
            }

        }catch (Exception e){e.printStackTrace();}
    }
}
