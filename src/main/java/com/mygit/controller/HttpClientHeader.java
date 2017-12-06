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
public class HttpClientHeader {
    public static void main(String[] args) throws Exception {
        //创建httpclient实例
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建httpget实例
        HttpGet httpGet=new HttpGet("http://activity.huazhu.com/Content/h-land/index.html?utm_source=H5&utm_medium=Wechat&utm_campaign=HZ0052&utm_term=2016-11-21&source=PC0035");  //系統有限制

        httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");

        httpGet.setHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");

        httpGet.setHeader("Accept-Encoding", "gzip, deflate, br");

        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.9");

        httpGet.setHeader("Connection", "keep-alive");

        httpGet.setHeader("Cookie", "gr_user_id=e4189009-1dc2-44e5-a4a6-c6976e313311; Hm_lvt_e5770a47472445b3f839a58a32b8abe5=1512441772; Hm_lpvt_e5770a47472445b3f839a58a32b8abe5=1512441923; __utma=107792157.1581886975.1512441772.1512441772.1512441772.1; __utmb=107792157.11.9.1512441922783; __utmc=107792157; __utmz=107792157.1512441772.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); ASP.NET_SessionId=x4crwq0eyjtyh2nfl35dkzmb; p_t=afGlA5irDvlwe+QYl8MCwUpZaYz/3HBMkfefVaPsfAr8VWZ0bYKy3w==; .ASPXAUTH=E95E251D13BBC6D55A5BCD51BB8CC035437C821A3F39C446506454F3B7312635093F7235FE17FFF255917BF4B7C86688EFBA0B169EC89BF3AB14C04625C56A006ACD556E5B4087BE5910CF29F0FB544F9B8991CBD7A2493D7C67D7741ACC0D5A34B2A3B80D932AD85056348A5AA31A1A2C718BD5A420592BB874BC259A6D8E7EAA2EEA2F4D14A268FA57F736AE6BC6B0A3352FEA09B821F9F996C83996E14A9F0CF255F743FD8F30253885CB849EA840D4064615FB5C2F28FEB9D79475CA1445BF861AEDE4F1AC76C9721743466C5A5B434C36A809916BC72326364F043B5340569919E0CE27A619CEB5497675A312F6864E046592B3D25A5B5A3C27B5731F55E5160A864FDAEBB7CE5BAC3D8FAC48A13D3C6B37908490BD52E45B7E009703B8260AB282CC975CAA6BE9808D59365A6CD664C1BA2826ADD521CD235F9B6CDBF97D7B97A9AC06C7F93BD81B05ADF4E420603879A94237C32B00C477F94139838DF1207AA70E123DDFFDFF6DA783F109204875CE6B28ED346FA70FE974E6445471; unick=%e6%9d%a8%e5%ad%a6%e5%b3%b0; MemberLevelID=B; _ga=GA1.2.1581886975.1512441772; _HZ_SessionId=bMYeMlURdA2j8TMI9x5Rg1UZWCl1eYJPSvdDfVqyj40=; _HZ_actsessionId=mJOfjromq4EmHqAmoqOFPaf4jURDh1/g+LYML4fxfDI=; Hm_lvt_f14fa482025352f2dded0c2e008dd278=1512442640; Hm_lpvt_f14fa482025352f2dded0c2e008dd278=1512443102; gr_session_id_8f6e3e7f89d647cab9784afa81ea87bd=a01a3580-cfce-4b61-91fb-723690a8af28");

        httpGet.setHeader("Host", "activity.huazhu.com");

        httpGet.setHeader("Cache-Control", "max-age=0");

//        httpGet.setHeader("If-Modified-Since", "Thu, 27 Apr 2017 07:02:43 GMT");
//        httpGet.setHeader("If-None-Match", "\"8a30824424bfd21:0\"");
        httpGet.setHeader("Upgrade-Insecure-Requests", "1");

        httpGet.setHeader("refer", "http://hotels.huazhu.com/?CityID=3401&CheckInDate=2017-12-05&CheckOutDate=2017-12-06");

        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.62 Safari/537.36");
        //执行http get 请求

        CloseableHttpResponse response = null;

        response = httpClient.execute(httpGet);

        HttpEntity entity = response.getEntity();//获取返回实体
        //EntityUtils.toString(entity,"utf-8");//获取网页内容，指定编码
        String html = EntityUtils.toString(entity,"utf-8");
        System.out.println(html);
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
//                HttpClientHello3img.downloadImg(src.attr("abs:src"), UUID.randomUUID().toString()+".png");

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
