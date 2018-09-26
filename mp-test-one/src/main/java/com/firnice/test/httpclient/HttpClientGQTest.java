package com.firnice.test.httpclient;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author: liyl
 * @date: 2016/11/24 上午11:11
 * @since 1.0.0
 */
public class HttpClientGQTest {

    static List<String> urls = Lists.newArrayList("gqt.mk1i14.cn", "gqt2.mk1i14.cn", "www.douyin.com", "pik");

    public static void main(String[] args) {
        try {

            ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);
            scheduledThreadPool.scheduleAtFixedRate(new Runnable() {

                @Override
                public void run() {
                    try {
                        System.out.println("begin");
                        for (String url : urls) {

                            test1(url);
                        }

                        System.out.println("end");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }, 0, 1, TimeUnit.MINUTES);
            //httpsTest();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void test1(String url) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpget = new HttpGet("http://api.kindling.top/wechat/check?url=" + url);
            ResponseHandler<String> responseHandler = response -> {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            };
            String responseBody = httpclient.execute(httpget, responseHandler);
            System.out.println("----------------------------------------");
            System.out.println(responseBody);
            JSONObject jsonObject = JSONObject.parseObject(responseBody);
            String status = jsonObject.getString("status");
//            Integer a1 = data.getInteger("1");
//            Integer a2 = data.getInteger("2");
//            Integer a3 = data.getInteger("3");
//            Integer a4 = data.getInteger("4");
//            Integer a5 = data.getInteger("5");
//            System.out.println(a1);
//            System.out.println(a2);
//            System.out.println(a3);
//            System.out.println(a4);
//            System.out.println(a5);
            SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
            jsonObject.put("date", time.format(new Date()));
            jsonObject.put("url", url);

//
            try {
                File file = new File("/Users/yiruike/Downloads/gq.txt");     //文件路径（路径+文件名）
                if (!file.exists()) {   //文件不存在则创建文件，先创建目录
                    File dir = new File(file.getParent());
                    dir.mkdirs();
                    file.createNewFile();
                }
                FileOutputStream outStream = new FileOutputStream(file, true);    //文件输出流用于将数据写入文件
                outStream.write(jsonObject.toJSONString().getBytes());
                outStream.write('\n');
                outStream.close();  //关闭文件输出流
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpclient.close();
        }

    }


}
