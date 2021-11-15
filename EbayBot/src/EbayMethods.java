import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class EbayMethods {

    public void SortItems(File file, Document document, String start, int startLength, String end, String fileName) {
        try {
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName + ".txt"), "utf-8"));
            Document doc = document;
            if (document.equals(null)){doc = Jsoup.parse(file, "utf-8");}
            int i = 0;
            String input = doc.toString();
                while (input.contains(start)) {
                    int begin = input.indexOf(start);
                    int finish = input.indexOf(end, begin);
                    String output = input.substring(begin + startLength, finish);
                    input = (input.substring(0, begin - 20) + input.substring(finish + 20));

                    if (output.contains("New Listing</span>")) {
                        int a = output.indexOf("</span>");
                        output = output.substring(a + 7);
                    }
                    while (output.substring(0, 1).equals(" ")) {
                        output = output.substring(1);
                    }
                    if (i>198){writer.close();}
                    else {writer.write(output + "\n");}
                    i++;
            }
            System.out.println(i);

        } catch (Exception e) {e.printStackTrace();}
    }

    public Document getText(String url, String fileName) {

        try {
            Document doc = Jsoup.connect(url).get();
            Element a = doc.body();
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName + ".txt"), "utf-8"));
            writer.write(a.toString());

//            System.out.println(a.getElementsByAttributeStarting("class=\"s-item__title\">"));
            //class="s-item__title">

            //</h3></a>
            //          <div class
            return doc;
        }catch (Exception e){e.printStackTrace();}
        return null;
    }

    public ArrayList<String> ItemInfo(String start, int startLength, String end, ArrayList<String> urls) {
        ArrayList<String> itemInfo = new ArrayList<>();
        for (int i = 0; i < urls.size(); i++) {
            itemInfo.add(null);
        }
        int interval = urls.size() / 8;
        try {
            Thread thread1 = new Thread(() -> {
                try {
                    for (int i = interval * (1 - 1); i < interval * 1; i++) {
                        String line = urls.get(i);
                        Document doc = Jsoup.connect(line).get();
                        String input = doc.toString();
                        int begin = input.indexOf(start);
                        int finish = input.indexOf(end, begin);
                        String output = input.substring(begin + startLength, finish);
                        itemInfo.set(i, output);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            Thread thread2 = new Thread(() -> {
                try {
                    for (int i = interval * (2 - 1); i < interval * 2; i++) {
                        String line = urls.get(i);
                        Document doc = Jsoup.connect(line).get();
                        String input = doc.toString();
                        int begin = input.indexOf(start);
                        int finish = input.indexOf(end, begin);
                        String output = input.substring(begin + startLength, finish);
                        itemInfo.set(i, output);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            Thread thread3 = new Thread(() -> {
                try {
                    for (int i = interval * (3 - 1); i < interval * 3; i++) {
                        String line = urls.get(i);
                        Document doc = Jsoup.connect(line).get();
                        String input = doc.toString();
                        int begin = input.indexOf(start);
                        int finish = input.indexOf(end, begin);
                        String output = input.substring(begin + startLength, finish);
                        itemInfo.set(i, output);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            Thread thread4 = new Thread(() -> {
                try {
                    for (int i = interval * (4 - 1); i < interval * 4; i++) {
                        String line = urls.get(i);
                        Document doc = Jsoup.connect(line).get();
                        String input = doc.toString();
                        int begin = input.indexOf(start);
                        int finish = input.indexOf(end, begin);
                        String output = input.substring(begin + startLength, finish);
                        itemInfo.set(i, output);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            Thread thread5 = new Thread(() -> {
                try {
                    for (int i = interval * (5 - 1); i < interval * 5; i++) {
                        String line = urls.get(i);
                        Document doc = Jsoup.connect(line).get();
                        String input = doc.toString();
                        int begin = input.indexOf(start);
                        int finish = input.indexOf(end, begin);
                        String output = input.substring(begin + startLength, finish);
                        itemInfo.set(i, output);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            Thread thread6 = new Thread(() -> {
                try {
                    for (int i = interval * (6 - 1); i < interval * 6; i++) {
                        String line = urls.get(i);
                        Document doc = Jsoup.connect(line).get();
                        String input = doc.toString();
                        int begin = input.indexOf(start);
                        int finish = input.indexOf(end, begin);
                        String output = input.substring(begin + startLength, finish);
                        itemInfo.set(i, output);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            Thread thread7 = new Thread(() -> {
                try {
                    for (int i = interval * (7 - 1); i < interval * 7; i++) {
                        String line = urls.get(i);
                        Document doc = Jsoup.connect(line).get();
                        String input = doc.toString();
                        int begin = input.indexOf(start);
                        int finish = input.indexOf(end, begin);
                        String output = input.substring(begin + startLength, finish);
                        itemInfo.set(i, output);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            Thread thread8 = new Thread(() -> {
                try {
                    for (int i = interval * (8 - 1); i < interval * 8; i++) {
                        String line = urls.get(i);
                        Document doc = Jsoup.connect(line).get();
                        String input = doc.toString();
                        int begin = input.indexOf(start);
                        int finish = input.indexOf(end, begin);
                        String output = input.substring(begin + startLength, finish);
                        itemInfo.set(i, output);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            thread1.start();
            thread2.start();
            thread3.start();
            thread4.start();
            thread5.start();
            thread6.start();
            thread7.start();
            thread8.start();

            while (thread1.isAlive()||thread2.isAlive()||thread3.isAlive()||thread4.isAlive()||thread5.isAlive()||thread6.isAlive()||thread7.isAlive()||thread8.isAlive()) {
                Thread.sleep(200);
            }

        } catch (Exception e) {e.printStackTrace();}
        return itemInfo;
    }


}
