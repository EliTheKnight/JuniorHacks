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
            webClient.getOptions().setJavaScriptEnabled(false);
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

    public String[][] AccessFoundCard(String cardUrl){
        ArrayList<String> cardTable = new ArrayList<>();
        try {
            WebClient webClient = new WebClient(BrowserVersion.CHROME);

            webClient.getOptions().setThrowExceptionOnScriptError(false);
            webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
            webClient.getOptions().setJavaScriptEnabled(false);
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

            while (table.contains("\n")){
                String line = table.substring(0,table.indexOf("\n"));
                cardTable.add(line);
                table = table.substring(0,table.indexOf("\n"));
            }

        }catch (Exception e){e.printStackTrace();}

        String[][] output = formatCardTable(cardTable);
        return output;

    }

    public String[][] formatCardTable(ArrayList<String> table){
        int size = 100;
        String[][] cardInfo = new String[size][3];
        try {
            for (int item = 0; item < table.size()-50; item++) {
                if (table.get(item).contains("<tr>")) {
                    String a = table.get(item);
                    String b = table.get(item+1);
                    String c = table.get(item+2);
                    String d = table.get(item+3);
                    String e = table.get(item+4);
                    String f = table.get(item+5);
                    String g = table.get(item+6);
                    String h = table.get(item+7);
                    String i = table.get(item+8);
                    String j = table.get(item+9);
                    String k = table.get(item+10);
                    String l = table.get(item+11);
                    String m = table.get(item+12);
                    String n = table.get(item+13);
                    String o = table.get(item+14);
                    String p = table.get(item+15);
                    String q = table.get(item+16);
                    String r = table.get(item+17);
                    String s = table.get(item+18);
                    String t = table.get(item+19);
                    String u = table.get(item+20);
                    String v = table.get(item+21);
                    String w = table.get(item+22);
                    String x = table.get(item+23);
                    String y = table.get(item+24);
                    String z = table.get(item+25);
                    String aa = table.get(item+26);
                    String ab = table.get(item+27);
                    String ac = table.get(item+28);
                    String ad = table.get(item+29);
                    String ae = table.get(item+30);
                    String af = table.get(item+31);

                    c = c.substring(44);
                    h = h.substring(44);
                    l = l.substring(23);
                    cardInfo[item][0] = c;
                    cardInfo[item][1] = h;
                    cardInfo[item][2] = l;
                    // c is date            h is grade              l is price
                }
            }
//            for (String[] row: cardInfo){
//                System.out.println("[" + row[0] + ", " + row[1] + ", " + row[2] + "]");
//            }
        }catch (Exception e){e.printStackTrace();}
        return cardInfo;
    }

    public ArrayList<String[][]> SearchItems(ArrayList<String> searchterms){
        ArrayList<String[][]> list = new ArrayList<>(200);
        try {
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("psaPrices.txt"), "utf-8"));
            int interval = 20;
            Thread thread1 = new Thread(() -> {
                try{
                    for (int i = (interval)*(0); i<interval*1; i++){
                        String search = searchterms.get(i);
                        String cardUrl = AccessWeb(search);
                        String[][] found= AccessFoundCard(cardUrl);
                        list.set(i, found);
                    }

                }catch (Exception e){e.printStackTrace();}
            });
            Thread thread2 = new Thread(() -> {
                try{
                    for (int i = (interval)*(1); i<interval*2; i++){
                        String search = searchterms.get(i);
                        String cardUrl = AccessWeb(search);
                        String[][] found= AccessFoundCard(cardUrl);
                        list.set(i, found);
                    }

                }catch (Exception e){e.printStackTrace();}
            });
            Thread thread3 = new Thread(() -> {
                try{
                    for (int i = (interval)*(2); i<interval*3; i++){
                        String search = searchterms.get(i);
                        String cardUrl = AccessWeb(search);
                        String[][] found= AccessFoundCard(cardUrl);
                        list.set(i, found);
                    }

                }catch (Exception e){e.printStackTrace();}
            });
            Thread thread4 = new Thread(() -> {
                try{
                    for (int i = (interval)*(3); i<interval*4; i++){
                        String search = searchterms.get(i);
                        String cardUrl = AccessWeb(search);
                        String[][] found= AccessFoundCard(cardUrl);
                        list.set(i, found);
                    }

                }catch (Exception e){e.printStackTrace();}
            });
            Thread thread5 = new Thread(() -> {
                try{
                    for (int i = (interval)*(4); i<interval*5; i++){
                        String search = searchterms.get(i);
                        String cardUrl = AccessWeb(search);
                        String[][] found= AccessFoundCard(cardUrl);
                        list.set(i, found);
                    }

                }catch (Exception e){e.printStackTrace();}
            });
            Thread thread6 = new Thread(() -> {
                try{
                    for (int i = (interval)*(5); i<interval*6; i++){
                        String search = searchterms.get(i);
                        String cardUrl = AccessWeb(search);
                        String[][] found= AccessFoundCard(cardUrl);
                        list.set(i, found);
                    }

                }catch (Exception e){e.printStackTrace();}
            });
            Thread thread7 = new Thread(() -> {
                try{
                    for (int i = (interval)*(6); i<interval*7; i++){
                        String search = searchterms.get(i);
                        String cardUrl = AccessWeb(search);
                        String[][] found= AccessFoundCard(cardUrl);
                        list.set(i, found);
                    }

                }catch (Exception e){e.printStackTrace();}
            });
            Thread thread8 = new Thread(() -> {
                try{
                    for (int i = (interval)*(7); i<interval*8; i++){
                        String search = searchterms.get(i);
                        String cardUrl = AccessWeb(search);
                        String[][] found= AccessFoundCard(cardUrl);
                        list.set(i, found);
                    }

                }catch (Exception e){e.printStackTrace();}
            });
            Thread thread9 = new Thread(() -> {
                try{
                    for (int i = (interval)*(8); i<interval*9; i++){
                        String search = searchterms.get(i);
                        String cardUrl = AccessWeb(search);
                        String[][] found= AccessFoundCard(cardUrl);
                        list.set(i, found);
                    }

                }catch (Exception e){e.printStackTrace();}
            });
            Thread thread10 = new Thread(() -> {
                try{
                    for (int i = (interval)*(9); i<interval*10; i++){
                        if (searchterms.get(i).equals(null)){
                            break;
                        }
                        String search = searchterms.get(i);
                        String cardUrl = AccessWeb(search);
                        String[][] found= AccessFoundCard(cardUrl);
                        list.set(i, found);
                    }

                }catch (Exception e){e.printStackTrace();}
            });
            thread1.start();
            thread2.start();
            thread3.start();
            thread4.start();
            thread5.start();
            thread6.start();
            thread7.start();
            thread8.start();
            thread9.start();
            thread10.start();

            while (thread1.isAlive()||thread2.isAlive()||thread3.isAlive()||thread4.isAlive()||thread5.isAlive()||thread6.isAlive()||thread7.isAlive()||thread8.isAlive()||thread9.isAlive()||thread10.isAlive()){
                Thread.sleep(500);
            }
        }catch (Exception e){e.printStackTrace();}
        return list;
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
