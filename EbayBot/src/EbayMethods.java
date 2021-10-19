import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.*;
import java.util.Scanner;

public class EbayMethods {

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
                    System.out.println(i);
            }

        } catch (Exception e) {e.printStackTrace();}
    }

    public Document getText(String url, String fileName) {

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
            Thread thread1 = new Thread(() -> {
                try {
                    Scanner reader = new Scanner(new File("urls.txt"));
                    Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("itemInfo1.txt"), "utf-8"));
                    int a = 0;
                    while (reader.hasNextLine()) {
                        String line = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String no = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String no2 = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String no3 = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String no4 = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String no5 = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String no6 = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String no7 = reader.nextLine();
                        Document doc = Jsoup.connect(line).get();

                        String input = doc.toString();
                        int begin = input.indexOf(start);
                        int finish = input.indexOf(end, begin);
                        String output = input.substring(begin + startLength, finish);

                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        } else {
                            writer.write(output + "\n");
                        }
                        a++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            Thread thread2 = new Thread(() -> {
                try {
                    int a = 0;
                    Scanner reader = new Scanner(new File("urls.txt"));
                    Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("itemInfo2.txt"), "utf-8"));
                    while (reader.hasNextLine()) {
                        String no = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String line = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String no2 = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String no3 = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String no4 = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String no5 = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String no6 = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String no7 = reader.nextLine();
                        Document doc = Jsoup.connect(line).get();

                        String input = doc.toString();
                        int begin = input.indexOf(start);
                        int finish = input.indexOf(end, begin);
                        String output = input.substring(begin + startLength, finish);

                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        } else {
                            writer.write(output + "\n");
                        }
                        a++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            Thread thread3 = new Thread(() -> {
                try {
                    Scanner reader = new Scanner(new File("urls.txt"));
                    Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("itemInfo3.txt"), "utf-8"));
                    int a = 0;
                    while (reader.hasNextLine()) {
                        String no = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String no2 = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String line = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String no3 = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String no4 = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String no5 = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String no6 = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String no7 = reader.nextLine();
                        Document doc = Jsoup.connect(line).get();

                        String input = doc.toString();
                        int begin = input.indexOf(start);
                        int finish = input.indexOf(end, begin);
                        String output = input.substring(begin + startLength, finish);

                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        } else {
                            writer.write(output + "\n");
                        }
                        a++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            Thread thread4 = new Thread(() -> {
                try {
                    Scanner reader = new Scanner(new File("urls.txt"));
                    Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("itemInfo4.txt"), "utf-8"));
                    int a = 0;
                    while (reader.hasNextLine()) {
                        String no = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String no2 = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String no3 = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String line = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String no4 = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String no5 = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String no6 = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String no7 = reader.nextLine();
                        Document doc = Jsoup.connect(line).get();

                        String input = doc.toString();
                        int begin = input.indexOf(start);
                        int finish = input.indexOf(end, begin);
                        String output = input.substring(begin + startLength, finish);

                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        } else {
                            writer.write(output + "\n");
                        }
                        a++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            Thread thread5 = new Thread(() -> {
                try {
                    Scanner reader = new Scanner(new File("urls.txt"));
                    Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("itemInfo5.txt"), "utf-8"));
                    int a = 0;
                    while (reader.hasNextLine()) {
                        String no = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String no2 = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String no3 = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String no4 = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String line = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String no5 = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String no6 = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String no7 = reader.nextLine();
                        Document doc = Jsoup.connect(line).get();

                        String input = doc.toString();
                        int begin = input.indexOf(start);
                        int finish = input.indexOf(end, begin);
                        String output = input.substring(begin + startLength, finish);

                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        } else {
                            writer.write(output + "\n");
                        }
                        a++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            Thread thread6 = new Thread(() -> {
                try {
                    Scanner reader = new Scanner(new File("urls.txt"));
                    Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("itemInfo6.txt"), "utf-8"));
                    int a = 0;
                    while (reader.hasNextLine()) {
                        String no = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String no2 = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String no3 = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String no4 = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String no5 = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String line = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String no6 = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String no7 = reader.nextLine();

                        Document doc = Jsoup.connect(line).get();
                        String input = doc.toString();
                        int begin = input.indexOf(start);
                        int finish = input.indexOf(end, begin);
                        String output = input.substring(begin + startLength, finish);

                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        } else {
                            writer.write(output + "\n");
                        }
                        a++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            Thread thread7 = new Thread(() -> {
                try {
                    Scanner reader = new Scanner(new File("urls.txt"));
                    Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("itemInfo7.txt"), "utf-8"));
                    int a = 0;
                    while (reader.hasNextLine()) {
                        String no = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String no2 = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String no3 = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String no4 = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String no5 = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String no6 = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String line = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String no7 = reader.nextLine();

                        Document doc = Jsoup.connect(line).get();

                        String input = doc.toString();
                        int begin = input.indexOf(start);
                        int finish = input.indexOf(end, begin);
                        String output = input.substring(begin + startLength, finish);

                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        } else {
                            writer.write(output + "\n");
                        }
                        a++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            Thread thread8 = new Thread(() -> {
                try {
                    Scanner reader = new Scanner(new File("urls.txt"));
                    Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("itemInfo8.txt"), "utf-8"));
                    int a = 0;
                    while (reader.hasNextLine()) {
                        String no = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String no2 = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String no3 = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String no4 = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String no5 = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String no6 = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String no7 = reader.nextLine();
                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        }
                        String line = reader.nextLine();
                        Document doc = Jsoup.connect(line).get();

                        String input = doc.toString();
                        int begin = input.indexOf(start);
                        int finish = input.indexOf(end, begin);
                        String output = input.substring(begin + startLength, finish);

                        if (reader.hasNextLine() == false) {
                            writer.close();
                            break;
                        } else {
                            writer.write(output + "\n");
                        }
                        a++;
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

            while (thread1.isAlive() && thread2.isAlive() && thread3.isAlive() && thread4.isAlive() && thread5.isAlive() && thread6.isAlive() && thread7.isAlive() && thread8.isAlive()) {
                Thread.sleep(1000);
            }

            Compile("cost");
        }catch (Exception e){e.printStackTrace();}
    }

    public void Compile(String file){

        try {
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file + ".txt"), "utf-8"));
            Scanner r1 = new Scanner(new File("itemInfo1.txt"));
            Scanner r2 = new Scanner(new File("itemInfo2.txt"));
            Scanner r3 = new Scanner(new File("itemInfo3.txt"));
            Scanner r4 = new Scanner(new File("itemInfo4.txt"));
            Scanner r5 = new Scanner(new File("itemInfo5.txt"));
            Scanner r6 = new Scanner(new File("itemInfo6.txt"));
            Scanner r7 = new Scanner(new File("itemInfo7.txt"));
            Scanner r8 = new Scanner(new File("itemInfo8.txt"));

            while (r1.hasNextLine() && r2.hasNextLine() && r3.hasNextLine() && r4.hasNextLine() && r5.hasNextLine() && r6.hasNextLine() && r7.hasNextLine() && r8.hasNextLine()){
                String one = r1.nextLine();
                String two = r2.nextLine();
                String three = r3.nextLine();
                String four = r4.nextLine();
                String five = r5.nextLine();
                String six = r6.nextLine();
                String seven = r7.nextLine();
                String eight = r8.nextLine();
                String output = one + "\n" + two + "\n" + three + "\n" + four + "\n" + five + "\n" + six + "\n" + seven + "\n" + eight + "\n";
                writer.write(output);
            }
            writer.close();

        }catch (Exception e){e.printStackTrace();}

    }

}
