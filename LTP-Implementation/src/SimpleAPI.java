/**
 * Created by hzwangtianlu on 2016/3/23.
 * This example shows how to use Java to build http connection and request
 * the ltp-cloud service for perform full-stack Chinese language analysis
 * and get results in specified formats
 */
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class SimpleAPI {
    public static void main(String[] args) throws IOException {
        if (args.length < 1 || !(args[0].equals("xml")
                || args[0].equals("json")
                || args[0].equals("conll"))) {
            System.out.println("Usage: java SimpleAPI [xml/json/conll]");
            return;
        }

        String api_key = "l414t4W09gbBURxWTo4KdFZVnRgdWwKI4GleDs2E";
        String pattern = "pos";
        String format  = args[0];
        String text    = "SCANV网址安全中心(http://scanv.com)是一个综合性的网址安全服务平台。通过网址安全中心，用户可以方便的查询到要访问的网址是否存在恶意行为，同时可以在SCANV中在线举报曝光违法恶意网站";
        text = URLEncoder.encode(text, "utf-8");

        URL url     = new URL("http://ltpapi.voicecloud.cn/analysis/?"
                + "api_key=" + api_key + "&"
                + "text="    + text    + "&"
                + "format="  + format  + "&"
                + "pattern=" + pattern);
        URLConnection conn = url.openConnection();
        conn.connect();

        BufferedReader innet = new BufferedReader(new InputStreamReader(
                conn.getInputStream(),
                "utf-8"));
        String line;
        File xmlFile = new File("LTP-Implementation/files/tempXML");
        xmlFile.createNewFile();
        FileWriter xmlWriter = new FileWriter(xmlFile);
        while ((line = innet.readLine())!= null) {
            System.out.println(line);
            xmlWriter.write(line);
        }
        xmlWriter.flush();
        xmlWriter.close();
        innet.close();
    }
}
