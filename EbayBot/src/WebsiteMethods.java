import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.ScriptException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.JavaScriptErrorListener;

import java.io.*;
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
            webClient.getOptions().setCssEnabled(false);
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
            webClient.close();
            for (DomElement a: list){
                cardList.add(a.toString());
            }

            if (list.size()>0) {
                foundCard = list.get(0).toString();
            }

            if (foundCard.toLowerCase().contains("holo") && (searchTerm.toLowerCase().contains("holo") == false)){
                if (list.size()>1){
                    foundCard = list.get(1).toString();
                }
            }

        }catch (Exception e){e.printStackTrace();}
        if (foundCard.length()>1) {
            foundCard = foundCard.substring(foundCard.indexOf("/Ca"), foundCard.indexOf("\">]"));
        }

        return foundCard;
    }

    public String[][] AccessFoundCard(String cardUrl){
        ArrayList<String> cardTable = new ArrayList<>();
        try {
            WebClient webClient = new WebClient(BrowserVersion.CHROME);

            webClient.getOptions().setThrowExceptionOnScriptError(false);
            webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
            webClient.getOptions().setJavaScriptEnabled(false);
            webClient.getOptions().setCssEnabled(false);
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

            HtmlPage page = webClient.getPage("https://www.pokemonprice.com" + cardUrl);

            String pageAsXml = page.asXml();
            String table = "";
            if (pageAsXml.contains("<tbody>")) {
                table = pageAsXml.substring(pageAsXml.indexOf("<tbody>"), pageAsXml.indexOf("</tbody>"));
            }
            webClient.close();
            while (table.contains("\n")){
                String line = table.substring(0,table.indexOf("\n"));
                cardTable.add(line);
                table = table.substring(table.indexOf("\n")+1);
            }

        }catch (Exception e){e.printStackTrace();}

        String[][] output = formatCardTable(cardTable);
        return output;

    }

    public String[][] formatCardTable(ArrayList<String> table){
        if (table.size()<=0){
            return null;
        }
        int size = 100;
        String[][] cardInfo = new String[size][3];
        try {
            for (int item = 0; item < table.size()-15 && item<100; item++) {
                if (table.get(item).contains("<tr>")) {
                    String c = table.get(item+3);
                    String h = table.get(item+8);
                    String l = table.get(item+12);

                    c = c.substring(44);
                    h = h.substring(44);
                    l = l.substring(23);
                    cardInfo[item][0] = c;
                    cardInfo[item][1] = h;
                    cardInfo[item][2] = l;
                    // c is date           h is grade             l is price
                }
                else{
                    table.remove(item);
                    item--;
                }
            }

        }catch (Exception e){e.printStackTrace();}

        return cardInfo;
    }

    public ArrayList<String[][]> SearchItems(ArrayList<String> searchterms){
        if (searchterms.size() < 2){return null;}
        ArrayList<String[][]> list = new ArrayList<>();
        for (int i = 0; i<200; i++){
            list.add(null);
        }
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
                    for (int i = (interval)*(9); i<interval*10-1; i++){
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
            System.out.println("end write");
            String[][] placeholder = new String[0][0];
            for(int i = 0; i< list.size(); i++){
                try {if (!list.get(i).equals(null)){continue;}}
                catch (Exception e){list.set(i, placeholder);}
            }

            for (String[][] array2D: list){
                writer.write("[");
                for (String[] array: array2D){
                    String last = array[2];
                    if (last==null){}
                    else {
                        last = last.substring(0, last.length() - 1);
                    }
                    writer.write("(" + array[0] +  ", " + array[1] + ", " + last + ")");
                }
                writer.write("]\n");
            }

            writer.close();
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

                        if ((words.get(i).equalsIgnoreCase("holo") && (i == 0 || words.get(i-1).equalsIgnoreCase("non") == false) )|| words.get(i).equalsIgnoreCase("1st")){
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
                                if (copy.length() > start+i+1 && copy.charAt(start + i + 1) == '0')
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