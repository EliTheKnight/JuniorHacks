import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.ScriptException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.gargoylesoftware.htmlunit.javascript.JavaScriptErrorListener;
import com.gargoylesoftware.htmlunit.javascript.host.event.Event;
import com.gargoylesoftware.htmlunit.javascript.host.event.KeyboardEvent;
import com.gargoylesoftware.htmlunit.javascript.host.html.HTMLListElement;
import org.apache.xalan.xsltc.DOM;
import org.jsoup.nodes.Element;

import java.net.MalformedURLException;
import java.net.URL;

public class WebTest {
    public static void main(String[] args) {

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

//                webClient.getOptions().setJavaScriptEnabled(false);

                HtmlPage page = webClient.getPage("https://www.pokemonprice.com/");
                HtmlForm searchForm = page.getForms().get(0);

                searchForm.getInputByName("searchterm").setValueAttribute("Pikachu");

                HtmlButton submitButton = (HtmlButton) page.createElement("button");
                submitButton.setAttribute("type", "submit");
                searchForm.appendChild(submitButton);

                HtmlPage newPage = submitButton.click();
                System.out.println(newPage.asXml());

                //TODO: before I get this next element I have to return the page with my search term


                /*Autocomplete list elements*/
//                DomElement a = page.getElementById("ui-id-1");

//                System.out.println(a);

        }catch (Exception e){e.printStackTrace();}
    }
}
