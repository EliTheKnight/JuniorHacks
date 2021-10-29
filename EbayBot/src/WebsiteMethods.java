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

            HtmlPage page = webClient.getPage("https://www.pokemonprice.com" + cardUrl);

            DomElement listSize = page.getElementById("prices_length");
            List<DomElement> list = page.getByXPath("//table/tbody");

        }catch (Exception e){e.printStackTrace();}
    }

    public void SearchItems(String filename){
        try {
            Scanner reader = new Scanner(new File(filename + ".txt"));
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("psaPrices.txt"), "utf-8"));
            ArrayList<ArrayList<String>> listByGrade = new ArrayList<>();
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
                            if (grade == 1)
                                grade = 10;
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
