package com.firnice.test.httpclient;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * @author: liyl
 * @date: 2016/11/24 上午11:11
 * @since 1.0.0
 */
public class HttpClientTest1 {

    public static void main(String[] args) {
        String[] codes = new String[] {"210004", "540006", "003624"};
        String url = "http://fund.eastmoney.com/f10/F10DataApi.aspx";
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("type", "lsjz");
            params.put("code", codes[0]);
            params.put("page", 1);
            params.put("per", 200);
            params.put("sdate", "");
            params.put("edate", "");
            String result = doGet(url, params);
            //System.out.println(result);
            String content = result.substring(result.indexOf("\"") + 1, result.lastIndexOf("\""));
            //System.out.println(content);
            Document parse = Jsoup.parse(content);
            table1(parse);

            //System.out.println(list);
            Collections.reverse(list);
            fun();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static double max = 0;

    public static void fun() {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(j).getNav() - list.get(i).getNav() > max) {
                    max = list.get(j).getNav() - list.get(i).getNav();
                    System.out.println("max:" + max + " | " + getDistanceTimes(list.get(j).getDate(),list.get(i).getDate())+ " | " +list.get(j) + " - " + list.get(i));
                }
            }
        }
    }

    public static long getDistanceTimes(Date one, Date two) {

        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        long time1 = one.getTime();
        long time2 = two.getTime();
        long diff;
        if (time1 < time2) {
            diff = time2 - time1;
        } else {
            diff = time1 - time2;
        }
        day = diff / (24 * 60 * 60 * 1000);
        hour = (diff / (60 * 60 * 1000) - day * 24);
        min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
        sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);

        long[] times = {day, hour, min, sec};
        return day;
    }

    static Map map = Maps.newHashMap();

    static List<Record> list = Lists.newArrayList();

    private static int table1(Document doc) {
        Elements trs = doc.select("table").select("tr");
        int i;
        for (i = 1; i < trs.size(); i++) {
            Elements tds = trs.get(i).select("td");
            Record record = null;
            try {
                record = new Record(tds.get(0).text(), Double.valueOf(tds.get(1).text()), Double.valueOf(tds.get(2).text()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            map.put(tds.get(0).text(), record);
            list.add(record);
            //for (int j=0; j<tds.size(); j++){
            //    String txt = tds.get(j).text();
            //    System.out.print(txt+" ");
            //}
            //System.out.println("");
        }
        return i;
    }

    public static String doGet(String url, Map<String, Object> params) {
        String apiUrl = url;
        StringBuffer param = new StringBuffer();
        int i = 0;
        for (String key : params.keySet()) {
            if (i == 0) {
                param.append("?");
            } else {
                param.append("&");
            }
            param.append(key).append("=").append(params.get(key));
            i++;
        }
        apiUrl += param;
        String result = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpPost = new HttpGet(apiUrl);
            HttpResponse response = httpclient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            //System.out.println("执行状态码 : " + statusCode);

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instream = entity.getContent();
                result = IOUtils.toString(instream, "UTF-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static class Record {
        private String time;
        private double nav;
        private double accnav;

        private Date date;

        public Record(String time, double nav, double accnav) throws ParseException {
            this.time = time;
            this.nav = nav;
            this.accnav = accnav;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            this.date = sdf.parse(time);
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public double getNav() {
            return nav;
        }

        public void setNav(double nav) {
            this.nav = nav;
        }

        public double getAccnav() {
            return accnav;
        }

        public void setAccnav(double accnav) {
            this.accnav = accnav;
        }

        @Override
        public String toString() {
            return "Record{" +
                    "time='" + time + '\'' +
                    ", nav=" + nav +
                    ", accnav=" + accnav +
                    '}';
        }
    }
}



