import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.logging.Level;

public class Lottie6 {

    public static void main(String[] args) throws IOException {


        // 屏蔽HtmlUnit等系统 log
        LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
        java.util.logging.Logger.getLogger("org.apache.http.client").setLevel(Level.OFF);

        String url = "https://localhost:8033/wx/ft/index.html?w=720&h=1280&json=https://wechat-mini-program-beta.oss-cn-beijing.aliyuncs.com/model/1/std.json&path=https://wechat-mini-program-beta.oss-cn-beijing.aliyuncs.com/wx/ft/u/30/";
        System.out.println("Loading page now-----------------------------------------------: " + url);

        // HtmlUnit 模拟浏览器
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setJavaScriptEnabled(true);              // 启用JS解释器，默认为true
        webClient.getOptions().setCssEnabled(false);                    // 禁用css支持
        webClient.getOptions().setThrowExceptionOnScriptError(false);   // js运行错误时，是否抛出异常
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setTimeout(10 * 1000);                   // 设置连接超时时间
        HtmlPage page = webClient.getPage(url);
        webClient.waitForBackgroundJavaScript(3 * 1000);               // 等待js后台执行30秒

        String pageAsXml = page.asXml();

        // Jsoup解析处理
        Document doc = Jsoup.parse(pageAsXml, url);
        Elements pngs = doc.select("img[src$=.png]");                   // 获取所有图片元素集
        // 此处省略其他操作
        System.out.println(doc.toString());

    }
}
