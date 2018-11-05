package com.okhttp;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class WeiboClient {

    static OkHttpClient client = new OkHttpClient();

    static String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }


    public static void main(String[] args) {

        try {
            System.out.println(System.currentTimeMillis());
            String run = run("http://open.weibo.com/tools/aj_apitest.php?appkey=3875242581&_t=0&__rnd="+System.currentTimeMillis());
            System.out.println(run);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
