package com.mygit.controller;

import com.mygit.utils.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by yxf on 2017/11/30.
 */
public class HttpClientHello2 {
    public static void main(String[] args) throws Exception {
        //创建httpclient实例
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建httpget实例
        HttpGet httpGet=new HttpGet("https://www.oschina.net/");  //系統有限制
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.62 Safari/537.36");
        //执行http get 请求
        CloseableHttpResponse response = null;

        response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();//获取返回实体
        //EntityUtils.toString(entity,"utf-8");//获取网页内容，指定编码
//        System.out.println("网页内容\n"+ EntityUtils.toString(entity,"utf-8"));


        String html = EntityUtils.toString(entity,"utf-8");
        getResource(html);
//        FileUtils.string2File("D:\\a.html",html);


        //查看响应类型
//        System.out.println(entity.getContentType().getValue());
//        System.out.println(response.getStatusLine().getStatusCode());
        //HTTP/1.1 200 OK    200
        response.close();
        httpClient.close();
    }

    public static void getResource(String html) throws Exception{
        Document doc = Jsoup.parse(html);
        Elements media = doc.select("[src]");
        Elements imports = doc.select("link[href]");
        Elements links = doc.select("a[href]");

        print("\nMedia: (%d)", media.size());
        for (Element src : media) {
            if (src.tagName().equals("img")){
                print(" * %s: <%s> %sx%s (%s)",
                        src.tagName(), src.attr("abs:src"), src.attr("width"), src.attr("height"),
                        trim(src.attr("alt"), 20));
                System.out.println("----------");
                HttpClientHello3img.downloadImg(src.attr("abs:src"), UUID.randomUUID().toString()+".png");

            }
            else{
                print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
//                HttpClientHello3img.downloadImg(src.attr("abs:src"), UUID.randomUUID().toString()+".img");
            }

        }
        System.out.println();
        print("\nImports: (%d)", imports.size());
        for (Element link : imports) {
            print(" * %s <%s> (%s)", link.tagName(),link.attr("abs:href"), link.attr("rel"));

        }

        print("\nLinks: (%d)", links.size());
        for (Element link : links) {
            print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
        }
    }

    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }

    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }



}
