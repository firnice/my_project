package com.firnice.test.httpclient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

/**
 * @author: liyl
 * @date: 2016/11/24 上午11:11
 * @since 1.0.0
 */
public class HttpClientTest_huajiao2 {

    public static class Data {
        private Integer more;

        private JSONObject data;

        private JSONArray feeds;

        private Integer offset;

        public Integer getMore() {
            return more;
        }

        public void setMore(Integer more) {
            this.more = more;
        }

        public JSONObject getData() {
            return data;
        }

        public void setData(JSONObject data) {
            this.data = data;
        }

        public JSONArray getFeeds() {
            return feeds;
        }

        public void setFeeds(JSONArray feeds) {
            this.feeds = feeds;
        }

        public Integer getOffset() {
            return offset;
        }

        public void setOffset(Integer offset) {
            this.offset = offset;
        }

        public Data(Integer more, JSONObject data, JSONArray feeds, Integer offset) {
            this.more = more;
            this.data = data;
            this.feeds = feeds;
            this.offset = offset;
        }
    }

    public static Data get(Integer offset) throws IOException {
        String s = test1(String.format(url, offset));
        JSONObject jsonObject = JSON.parseObject(s);
        JSONObject data = jsonObject.getJSONObject("data");
        Integer more = data.getInteger("more");
        JSONArray feeds = data.getJSONArray("feeds");
        offset = data.getInteger("offset");
        return new Data(more, data, feeds, offset);
    }

    static String url
        = "http://webh.huajiao.com/live/listcategory?_callback=jQuery110208633890259027215_1508401298799&cateid=1000"
        + "&offset=%s&nums=2000&_=1508401298810";

    static String split_str = "@@@###&&&";

    public static void main(String[] args) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Data data = get(0);
                    JSONArray feeds = new JSONArray();
                    while (true) {
                        feeds.addAll(data.getFeeds());
                        if(data.getMore() == 1){
                            data = get(data.getOffset());
                        }else {
                            break;
                        }
                    }
                    String line = "";
                    Date date = new Date();
                    DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String format = format2.format(date);
                    for (int i = 0; i < feeds.size(); i++) {
                        JSONObject feedData = feeds.getJSONObject(i);
                        JSONObject feed = feedData.getJSONObject("feed");
                        JSONObject author = feedData.getJSONObject("author");
                        JSONArray labels = feed.getJSONArray("labels");
                        String level = author.getString("level");
                        String authorlevel = author.getString("authorlevel");
                        String nickname = author.getString("nickname");
                        String.format(line, i + 1, level, authorlevel, nickname, format);
                        line += (i + 1 + split_str);
                        line += (level + split_str);
                        line += (authorlevel + split_str);
                        line += (nickname + split_str);
                        line += (format + split_str);
                        line += (labels + "");
                        line += "\r\n";

                    }

                    File file = new File("/Users/firnice/Downloads/huajiao_" + format + ".txt");
                    if (!file.exists()) {
                        try {
                            file.createNewFile();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }

                    FileOutputStream out = new FileOutputStream(file, true);
                    out.write(line.getBytes());
                    //out.write("\r\n".getBytes());// 写入一个换行

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        ScheduledExecutorService service = Executors
            .newSingleThreadScheduledExecutor();
        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
        service.scheduleAtFixedRate(runnable, 0, 5, TimeUnit.MINUTES);

    }

    public static String test1(String url) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            System.out.println(url);
            HttpGet httpget = new HttpGet(url);
            //System.out.println("Executing request " + httpget.getRequestLine());
            // Create a custom response handler
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                @Override
                public String handleResponse(
                    final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }

            };
            String responseBody = httpclient.execute(httpget, responseHandler);
            System.out.println("----------------------------------------");
            System.out.println(responseBody);
            return responseBody;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpclient.close();
        }
        return "";
    }

    public static void httpsTest() {
        // Trust own CA and all self-signed certs
        SSLContext sslcontext = null;
        try {
            //sslcontext = SSLContexts.custom()
            //        .loadTrustMaterial(new File("my.keystore"), "nopassword".toCharArray(),
            //                new TrustSelfSignedStrategy())
            //        .build();

            sslcontext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                @Override
                public boolean isTrusted(java.security.cert.X509Certificate[] chain, String authType)
                    throws CertificateException {
                    return true;
                }

            }).build();

            // Allow TLSv1 protocol only
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,
                new String[] {"TLSv1"},
                null,
                SSLConnectionSocketFactory.getDefaultHostnameVerifier());
            CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .build();
            try {

                HttpGet httpget = new HttpGet("https://httpbin.org/");

                System.out.println("Executing request " + httpget.getRequestLine());

                CloseableHttpResponse response = httpclient.execute(httpget);
                try {
                    HttpEntity entity = response.getEntity();

                    System.out.println("----------------------------------------");
                    //System.out.println(response.getStatusLine());
                    EntityUtils.consume(entity);
                } finally {
                    response.close();
                }
            } finally {
                httpclient.close();
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
