import org.jsoup.nodes.Document;


public class URLConnectionReader {
    public static void main(String[] args){
        long startTime = System.currentTimeMillis();
       try {
            FileSorter sorter = new FileSorter();
            Document document = sorter.getText("https://www.ebay.com/sch/i.html?_dcat=183454&_fsrp=1&_from=R40&Language=English%7C%21&_nkw=1st+edition+pokemon+psa&_sacat=0&LH_TitleDesc=0&Grade=%21%7C9%252E5%7C9%7C8%252E5%7C8%7C7%252E5%7C7%7C6%252E5%7C6&Graded=Yes%7C%21&_udhi=1000&LH_Auction=1&_sop=1&_ipg=200", "filename.txt");

            sorter.SortItems(null, document,"<h3 class=\"s-item__title\">", 26,"</h3></a>", "list.txt");

            sorter.SortItems(null, document, "\" _sp=\"p2351460.m1686.l7400\" class=\"s-item__link\" href=\"", 56, "\"><h3 class", "urls.txt");

            sorter.ItemInfo("price\" content=\"", 16, "\">");

        }catch (Exception e){e.printStackTrace();}
        long endTime = System.currentTimeMillis();

        long duration = (endTime - startTime);
        System.out.println(duration);
    }

}