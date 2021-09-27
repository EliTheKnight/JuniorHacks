import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.swing.plaf.TableHeaderUI;
import java.io.*;
import java.util.Scanner;

public class FileSorter {

//    public void getHTML(){
//        try {
//            URL oracle = new URL("https://www.ebay.com/sch/i.html?_dcat=183454&_fsrp=1&_from=R40&Language=English%7C%21&_nkw=1st+edition+pokemon+psa&_sacat=0&LH_TitleDesc=0&Grade=%21%7C9%252E5%7C9%7C8%252E5%7C8%7C7%252E5%7C7%7C6%252E5%7C6&Graded=Yes%7C%21&_udhi=1000&LH_Auction=1&_ipg=200&_sop=1");
//            URLConnection con = oracle.openConnection();
//            InputStream list = con.getInputStream();
//            String HTTPtext = new String(list.readAllBytes(), StandardCharsets.UTF_8);
//
////            System.out.println(text);
//
//            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("filename.txt"), "utf-8"));
//            writer.write(HTTPtext);
//        }catch (Exception e){e.printStackTrace();}
//    }


    public void SortItems(File file, Document document, String start, int startLength, String end, String fileName) {
        try {
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "utf-8"));
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

        } catch (Exception e) {e.printStackTrace();}
    }

    public Document getText(String url, String fileName) throws IOException {

        try {
            Document doc = Jsoup.connect(url).get();
            Element a = doc.body();
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "utf-8"));
            writer.write(a.toString());

//            System.out.println(a.getElementsByAttributeStarting("class=\"s-item__title\">"));
            //class="s-item__title">

            //</h3></a>
            //          <div class
            return doc;
        }catch (Exception e){e.printStackTrace();}
        return null;
    }


    public void ItemInfo(String start, int startLength, String end){
        try {

//             int i = 0;
            Thread thread1 = new Thread( () -> {
                try{
                    Scanner reader = new Scanner(new File("urls.txt"));
                    Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("itemInfo.txt"), "utf-8"));
                    int a = 0;
                    while (reader.hasNextLine()) {
                        String line = reader.nextLine();
                        String no = reader.nextLine();
                        String no2 = reader.nextLine();
                        String no3 = reader.nextLine();
                        String no4 = reader.nextLine();
                        String no5 = reader.nextLine();
                        String no6 = reader.nextLine();
                        String no7 = reader.nextLine();
                        Document doc = Jsoup.connect(line).get();

                        String input = doc.toString();
                        int begin = input.indexOf(start);
                        int finish = input.indexOf(end, begin);
                        String output = input.substring(begin + startLength, finish);

                        if (a>25){writer.close(); break;}
                        else {writer.write(output + "\n");}
                        a++;
                    }
                }catch (Exception e){e.printStackTrace();}
            });


            Thread thread2 = new Thread(() -> {try{
                int a = 0;
                Scanner reader = new Scanner(new File("urls.txt"));
                Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("itemInfo2.txt"), "utf-8"));
                while (reader.hasNextLine()) {
                    String no = reader.nextLine();
                    String line = reader.nextLine();
                    String no2 = reader.nextLine();
                    String no3 = reader.nextLine();
                    String no4 = reader.nextLine();
                    String no5 = reader.nextLine();
                    String no6 = reader.nextLine();
                    String no7 = reader.nextLine();
                    Document doc = Jsoup.connect(line).get();

                    String input = doc.toString();
                    int begin = input.indexOf(start);
                    int finish = input.indexOf(end, begin);
                    String output = input.substring(begin + startLength, finish);

                    if (a>25){writer.close(); break;}
                    else {writer.write(output + "\n");}
                    a++;
                }
            }catch (Exception e){e.printStackTrace();}
            });

            Thread thread3 = new Thread( () -> {
                try{
                    Scanner reader = new Scanner(new File("urls.txt"));
                    Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("itemInfo3.txt"), "utf-8"));
                    int a = 0;
                    while (reader.hasNextLine()) {
                        String no = reader.nextLine();
                        String no2 = reader.nextLine();
                        String line = reader.nextLine();
                        String no3 = reader.nextLine();
                        String no4 = reader.nextLine();
                        String no5 = reader.nextLine();
                        String no6 = reader.nextLine();
                        String no7 = reader.nextLine();
                        Document doc = Jsoup.connect(line).get();

                        String input = doc.toString();
                        int begin = input.indexOf(start);
                        int finish = input.indexOf(end, begin);
                        String output = input.substring(begin + startLength, finish);

                        if (a>25){writer.close(); break;}
                        else {writer.write(output + "\n");}
                        a++;
                    }
                }catch (Exception e){e.printStackTrace();}
            });

            Thread thread4 = new Thread( () -> {
                try{
                    Scanner reader = new Scanner(new File("urls.txt"));
                    Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("itemInfo4.txt"), "utf-8"));
                    int a = 0;
                    while (reader.hasNextLine()) {
                        String no = reader.nextLine();
                        String no2 = reader.nextLine();
                        String no3 = reader.nextLine();
                        String line = reader.nextLine();
//                        String no4 = reader.nextLine();
//                        String no5 = reader.nextLine();
//                        String no6 = reader.nextLine();
//                        String no7 = reader.nextLine();
                        Document doc = Jsoup.connect(line).get();

                        String input = doc.toString();
                        int begin = input.indexOf(start);
                        int finish = input.indexOf(end, begin);
                        String output = input.substring(begin + startLength, finish);

                        if (a>25){writer.close(); break;}
                        else {writer.write(output + "\n");}
                        a++;
                    }
                }catch (Exception e){e.printStackTrace();}
            });

            Thread thread5 = new Thread( () -> {
                try{
                    Scanner reader = new Scanner(new File("urls.txt"));
                    Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("itemInfo5.txt"), "utf-8"));
                    int a = 0;
                    while (reader.hasNextLine()) {
                        String no = reader.nextLine();
                        String no2 = reader.nextLine();
                        String no3 = reader.nextLine();
                        String no4 = reader.nextLine();
                        String line = reader.nextLine();
                        String no5 = reader.nextLine();
                        String no6 = reader.nextLine();
                        String no7 = reader.nextLine();
                        Document doc = Jsoup.connect(line).get();

                        String input = doc.toString();
                        int begin = input.indexOf(start);
                        int finish = input.indexOf(end, begin);
                        String output = input.substring(begin + startLength, finish);

                        if (a>25){writer.close(); break;}
                        else {writer.write(output + "\n");}
                        a++;
                    }
                }catch (Exception e){e.printStackTrace();}
            });

            Thread thread6 = new Thread( () -> {
                try{
                    Scanner reader = new Scanner(new File("urls.txt"));
                    Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("itemInfo6.txt"), "utf-8"));
                    int a = 0;
                    while (reader.hasNextLine()) {
                        String no = reader.nextLine();
                        String no2 = reader.nextLine();
                        String no3 = reader.nextLine();
                        String no4 = reader.nextLine();
                        String no5 = reader.nextLine();
                        String line = reader.nextLine();
                        String no6 = reader.nextLine();
                        String no7 = reader.nextLine();

                        Document doc = Jsoup.connect(line).get();

                        String input = doc.toString();
                        int begin = input.indexOf(start);
                        int finish = input.indexOf(end, begin);
                        String output = input.substring(begin + startLength, finish);

                        if (a>25){writer.close(); break;}
                        else {writer.write(output + "\n");}
                        a++;
                    }
                }catch (Exception e){e.printStackTrace();}
            });

            Thread thread7 = new Thread( () -> {
                try{
                    Scanner reader = new Scanner(new File("urls.txt"));
                    Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("itemInfo7.txt"), "utf-8"));
                    int a = 0;
                    while (reader.hasNextLine()) {
                        String no = reader.nextLine();
                        String no2 = reader.nextLine();
                        String no3 = reader.nextLine();
                        String no4 = reader.nextLine();
                        String no5 = reader.nextLine();
                        String no6 = reader.nextLine();
                        String line = reader.nextLine();
                        String no7 = reader.nextLine();

                        Document doc = Jsoup.connect(line).get();

                        String input = doc.toString();
                        int begin = input.indexOf(start);
                        int finish = input.indexOf(end, begin);
                        String output = input.substring(begin + startLength, finish);

                        if (a>25){writer.close(); break;}
                        else {writer.write(output + "\n");}
                        a++;
                    }
                }catch (Exception e){e.printStackTrace();}
            });

            Thread thread8 = new Thread( () -> {
                try{
                    Scanner reader = new Scanner(new File("urls.txt"));
                    Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("itemInfo8.txt"), "utf-8"));
                    int a = 0;
                    while (reader.hasNextLine()) {
                        String no = reader.nextLine();
                        String no2 = reader.nextLine();
                        String no3 = reader.nextLine();
                        String no4 = reader.nextLine();
                        String no5 = reader.nextLine();
                        String no6 = reader.nextLine();
                        String no7 = reader.nextLine();
                        String line = reader.nextLine();
                        Document doc = Jsoup.connect(line).get();

                        String input = doc.toString();
                        int begin = input.indexOf(start);
                        int finish = input.indexOf(end, begin);
                        String output = input.substring(begin + startLength, finish);

                        if (a>25){writer.close(); break;}
                        else {writer.write(output + "\n");}
                        a++;
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

//            while (reader.hasNextLine()) {
//                String line = reader.nextLine();
//                Document doc = Jsoup.connect(line).get();
//
//                String input = doc.toString();
//                int begin = input.indexOf(start);
//                int finish = input.indexOf(end, begin);
//                String output = input.substring(begin + startLength, finish);
//
//                if (i>49){writer.close(); break;}
//                else {writer.write(output + "\n");}
//                i++;
//            }

        } catch (Exception e) {e.printStackTrace();}
    }


}
