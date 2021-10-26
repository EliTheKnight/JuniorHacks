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

    public void AccessWeb(String searchTerm){
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
//                System.out.println(newPage.getUrl());

            List<DomElement> list = newPage.getByXPath("//table/tbody/tr/td/a");
            System.out.println(list);


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
            ArrayList<String> searchTerms = new ArrayList<>();


            while (reader.hasNextLine()){
                String line = reader.nextLine();
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
            }

            for (String a: searchTerms){
                writer.write(a + "\n");
            }
            writer.close();

        }catch (Exception e){e.printStackTrace();}
    }

}
