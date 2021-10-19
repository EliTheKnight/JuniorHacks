import org.jsoup.nodes.Document;

public class EbayMain {
    public static void main(String[] args){
        long startTime = System.currentTimeMillis();
       try {
            EbayMethods sorter = new EbayMethods();
            Document document = sorter.getText("https://www.ebay.com/sch/i.html?_dcat=183454&_fsrp=1&LH_Auction=1&rt=nc&_from=R40&LH_TitleDesc=0&Grade=6%7C6%252E5%7C7%7C7%252E5%7C8%7C8%252E5%7C9%7C9%252E5%7C!%7C10&_ipg=200&Graded=Yes%7C!&Language=English%7C!&_nkw=1st+edition+pokemon+psa&_sacat=0&_sop=1&_udhi=1000", "filename.txt");
           // Document document = Jsoup.parse(new File("filename.txt"), "utf-8");
            sorter.SortItems(null, document,"<h3 class=\"s-item__title\">", 26,"</h3></a>", "list.txt");

            sorter.SortItems(null, document, "\" _sp=\"p2351460.m1686.l7400\" class=\"s-item__link\" href=\"", 56, "\"><h3 class", "urls.txt");
           // Document doc = sorter.getText("https://www.ebay.com/itm/224620890243?hash=item344c72c883%3Ag%3AbM8AAOSwpW1hOo3x&amp;LH_Auction=1", "test.txt");
            sorter.ItemInfo("itemprop=\"price\" content=\"", 26, "\">");


        }catch (Exception e){e.printStackTrace();}
        long endTime = System.currentTimeMillis();

        long duration = (endTime - startTime);
        System.out.println(duration);
    }

}