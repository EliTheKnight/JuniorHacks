import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.ScriptException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.JavaScriptErrorListener;

import java.io.*;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class WebsiteMethods {

    public String AccessWeb(String searchTerm){
        ArrayList<String> cardList = new ArrayList<>();
        String foundCard = "";
        try {
            WebClient webClient = new WebClient(BrowserVersion.CHROME);

            webClient.getOptions().setThrowExceptionOnScriptError(false);
            webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
            webClient.setJavaScriptErrorListener(new JavaScriptErrorListener(){
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

            HtmlPage page = webClient.getPage("https://www.pokemonprice.com/");
            HtmlForm searchForm = page.getForms().get(0);

            searchForm.getInputByName("searchterm").setValueAttribute(searchTerm);

            HtmlButton submitButton = (HtmlButton) page.createElement("button");
            submitButton.setAttribute("type", "submit");
            searchForm.appendChild(submitButton);

            HtmlPage newPage = submitButton.click();

            List<DomElement> list = newPage.getByXPath("//table/tbody/tr/td/a");

            for (DomElement a: list){
                cardList.add(a.toString());
            }

            foundCard = list.get(0).toString();

        }catch (Exception e){e.printStackTrace();}

        return foundCard;
    }

    public void AccessFoundCard(String cardUrl){
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

            formatCardTable("cardTable");

        }catch (Exception e){e.printStackTrace();}
    }

    public String[][] formatCardTable(String filename){
        int size = 100;
        String[][] cardInfo = new String[size][3];
        try {
            Scanner reader = new Scanner(new File("cardTable.txt"));
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
        return cardInfo;
    }

    public void SearchItems(String filename){
        try {
            Scanner reader = new Scanner(new File(filename + ".txt"));
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("psaPrices.txt"), "utf-8"));
            Array[] list = new Array[100];
            while(reader.hasNextLine()){
                String line = reader.nextLine();
                String cardUrl = AccessWeb(line);
                AccessFoundCard(cardUrl);
            }

        }catch (Exception e){e.printStackTrace();}
    }

    public ArrayList<String> sortHTMLList(List<DomElement> list){
        ArrayList<String> output = new ArrayList<>();
        for (DomElement i: list){
            String item = i.toString();
            item = item.substring(item.indexOf("href=\"") + 6, item.indexOf("\">"));
            output.add(item);
        }
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

    public void findSearchWords(String list, ArrayList<String> list1, ArrayList<String> list2){
        try {
            Scanner reader = new Scanner(new File(list + ".txt"));
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("searchTerms.txt"), "utf-8"));
            Writer writer2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("grades.txt"), "utf-8"));
            ArrayList<String> searchTerms = new ArrayList<>();
            ArrayList<Integer> grades = new ArrayList<>();

            while (reader.hasNextLine()){
                String line = reader.nextLine();
                String copy = line;
                ArrayList<String> words = new ArrayList<>();
                String search = "";
                while (line.contains(" ")){
                    int start = line.indexOf(" ");
                    words.add(line.substring(0, start));
                    line = line.substring(start+1);
                }
//                    for (String word: words) {
//                        if (list1.contains(word.toLowerCase()) || list2.contains(word.toLowerCase())){
//                            search = search + " " + word;
//                        }
//                    }
                    for (int i = 0; i<words.size(); i++){
                        String word = words.get(i);
                        if (i<words.size()-1) {
                            word = words.get(i) + " " + words.get(i + 1);
                        }
                        if ((list1.contains(word.toLowerCase()) || list2.contains(word.toLowerCase())) && word.contains(" ")){
                            search = search + " " + word;
                        }
                        else if ((list1.contains(words.get(i).toLowerCase()) || list2.contains(words.get(i).toLowerCase()))) {
                            search = search + " " + words.get(i);
                        }

                        if (words.get(i).equalsIgnoreCase("holo") || words.get(i).equalsIgnoreCase("1st")){
                            search = search + " " + words.get(i);
                        }
                    }
                searchTerms.add(search);
                words.clear();
                int i = 0;
                if (copy.toLowerCase().contains(" psa") && copy.indexOf(" psa") <= copy.length()-1){
                    boolean findNum = true;
                    while (findNum) {
                        int grade = -1;

                        int start = copy.toLowerCase().indexOf(" psa");
                        Character a = copy.charAt(start + i);
                        if (Character.isDigit(a)){
                            grade = (int) a - 48;
                            if (grade == 1) {
                                if (Character.compare(copy.charAt(start + i + 1),'0') == 0)
                                    grade = 10;
                                else
                                    grade = -1;
                            }
                            grades.add(grade);
                            break;
                        }
                        else if (start + i + 1 < copy.length()){
                            i++;
                        }
                        else {
                            grades.add(grade);
                            break; }
                    }

                } else {grades.add(-1);}
            }

            for (String a: searchTerms){
                writer.write(a + "\n");
            }
            writer.close();
            for (Integer b: grades){
                writer2.write(b + "\n");
            }
            writer2.close();

        }catch (Exception e){e.printStackTrace();}
    }

}
