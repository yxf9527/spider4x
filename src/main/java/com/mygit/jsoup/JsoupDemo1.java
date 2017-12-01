package com.mygit.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

/**
 * Created by yxf on 2017/11/30.
 */
public class JsoupDemo1 {

    public static void main(String[] args) throws IOException {
        String html = "<html><head><title>First parse</title></head>"
                + "<body><p id=\"pid\" >Parsed HTML into a doc.</p></body></html>";
        Document document = Jsoup.parse(html);

        String html2 = "<div><p>Lorem ipsum.</p>";
        Document document1 = Jsoup.parseBodyFragment(html2);
        Element body = document1.body();

        //通过url获取urlhtml内容
        Document doc = Jsoup.connect("http://www.baidu.com").get();
        String title = doc.title();
//        System.out.println(title);

        //Connection 接口还提供一个方法链来解决特殊请求
        Document document2 = Jsoup.connect("https://www.baidu.com")
                .data("query", "Java")
                .userAgent("Mozilla")
                .cookie("auth", "token")
                .timeout(3000)
                .post();
//        System.out.println(document2.title());

        File file = new File("C:\\Users\\yxf\\Desktop\\test.html");
        Document document3 = Jsoup.parse(file,"UTF-8","");
        Element element = document3.getElementById("incompatible_tip");
        Elements divs = element.getElementsByTag("div");
        for (Element link : divs) {
            String linkHref = link.attr("class");
            String linkText = link.text();
            System.out.println(linkHref);
            System.out.println(linkText);
            System.out.println("----------------------------------");
        }

    }
}
