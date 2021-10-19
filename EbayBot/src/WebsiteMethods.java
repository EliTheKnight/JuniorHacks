import java.io.IOException;
import java.net.URL;
import java.util.List;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.RefreshHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.gargoylesoftware.htmlunit.javascript.host.event.KeyboardEvent;

public class WebsiteMethods {

   // public void WebUse(){
   public static void main(String[] args) {

        try {
            // Create and initialize WebClient object
            WebClient webClient = new WebClient(BrowserVersion.CHROME);
            webClient.setRefreshHandler(new RefreshHandler() {
                public void handleRefresh(Page page, URL url, int arg) throws IOException {
                    System.out.println("handleRefresh");
                }

            });

            // visit Yahoo Mail login page and get the Form object
            HtmlPage page = (HtmlPage) webClient.getPage("https://www.pokemonprice.com/");
            HtmlForm form = page.getFormByName("Search_Bar");

            // Enter login and passwd
            form.getInputByName("Search for a Card").setValueAttribute("@@@@@@@");

            // Click "Sign In" button/link
            form.fireEvent(String.valueOf(KeyboardEvent.DOM_VK_RETURN));
//            page = (HtmlPage) form.getInputByValue("Sign In").fireEvent(KeyboardEvent.DOM_VK_RETURN);
            System.out.println(form);
            // Click "Inbox" link
            HtmlAnchor anchor = (HtmlAnchor) page.getHtmlElementById("WelcomeInboxFolderLink");
            page = (HtmlPage) anchor.click();

            // Get the table object containing the mails
            HtmlTable dataTable = (HtmlTable) page.getHtmlElementById("datatable");

            // Go through each row and count the row with class=msgnew
//            int newMessageCount = 0;
//            List rows = (List) dataTable.getHtmlElementsByTagName("tr");
//            for (HtmlTableRow row : rows) {
//                if (row.getAttribute("class").equals("msgnew")) {
//                    newMessageCount++;
//                }
//            }

            // Print the newMessageCount to screen
//            System.out.println("newMessageCount = " + newMessageCount);

            //System.out.println(page.asXml());
        }catch (Exception e){e.printStackTrace();}
    }
}
