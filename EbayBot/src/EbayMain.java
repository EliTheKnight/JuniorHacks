import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.File;
import java.util.ArrayList;

public class EbayMain {
    public static void main(String[] args){
        long startTime = System.currentTimeMillis();
       try {
           EbayMethods ebay = new EbayMethods();
           WebsiteMethods access = new WebsiteMethods();

                   Document document = ebay.getText("https://www.ebay.com/sch/i.html?_dcat=183454&_fsrp=1&LH_Auction=1&rt=nc&_from=R40&LH_TitleDesc=0&Grade=6%7C6%252E5%7C7%7C7%252E5%7C8%7C8%252E5%7C9%7C9%252E5%7C!%7C10&_ipg=200&Graded=Yes%7C!&Language=English%7C!&_nkw=1st+edition+pokemon+psa&_sacat=0&_sop=1&_udhi=1000", "filename");
//                   Document document = Jsoup.parse(new File("filename.txt"), "utf-8");
                   ebay.SortItems(null, document,"<h3 class=\"s-item__title\">", 26,"</h3></a>", "list");

                   ebay.SortItems(null, document, "\" _sp=\"p2351460.m1686.l7400\" class=\"s-item__link\" href=\"", 56, "\"><h3 class", "urls");

                   ebay.ItemInfo("itemprop=\"price\" content=\"", 26, "\">");

                   ebay.Compile("cost");
           System.out.println("ebay Check");
                   ArrayList<String> cardList = access.fileToList("cardList");
                   ArrayList<String> setList = access.fileToList("Sets");
           System.out.println("check 1");
                   access.findSearchWords("list", cardList, setList);
           System.out.println("check 2");
                   ArrayList<String> searchTerms = access.fileToList("searchTerms");
                   ArrayList<String> grades = access.fileToList("grades");
           System.out.println("check 3");
                   access.SearchItems(searchTerms);
           System.out.println("final check");


        }catch (Exception e){e.printStackTrace();}
        long endTime = System.currentTimeMillis();

        long duration = (endTime - startTime);
        System.out.println(duration);
    }

}