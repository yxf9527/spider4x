package com.mygit.utils;

import com.mygit.httpclient.HttpClientImage;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.LinkedList;
import java.util.UUID;

/**
 * Created by yxf on 2017/12/1.
 */
public class HttpClientUtils {



    /**
     * 传入url
     * 返回网页源代码
     * httpClient  get请求
     * */
    public static String getHtmlContentByUrl(String url) throws Exception{
        //创建httpclient实例
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建httpget实例
        HttpGet httpGet=new HttpGet(url);  //系統有限制
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.62 Safari/537.36");
        //执行http get 请求
        CloseableHttpResponse response = null;

        response = httpClient.execute(httpGet);

        if(response.getStatusLine().getStatusCode()!=200){
            System.out.println("url访问异常！");
            return null;
        }

        HttpEntity entity = response.getEntity();//获取返回实体
        //EntityUtils.toString(entity,"utf-8");//获取网页内容，指定编码
//        System.out.println("网页内容\n"+ EntityUtils.toString(entity,"utf-8"));
        String html = EntityUtils.toString(entity,"utf-8");
        response.close();
        httpClient.close();
        return html;
    }


    /**
     * 传入url参数
     * 打印页面中存在的资源文本
     * 链接 图片 等
     * */
    public static void printResourceFromHtml(String html){
        Document doc = Jsoup.parse(html);
        Elements media = doc.select("[src]"); //获取有src属性值的所有标签
        Elements imports = doc.select("link[href]");
        Elements links = doc.select("a[href]");

        //获取图片链接并打印
        print("\nMedia: (%d)", media.size());
        for (Element src : media) {
            if (src.tagName().equals("img")){//在所有具有src属性的标签中获取img标签
                if(src.attr("abs:src")!=null && !"".equals(src.attr("abs:src"))){ // img 标签 src不为空判断
                    print(" * %s: <%s> (%s)",
                            src.tagName(), src.attr("abs:src"), trim(src.attr("alt"), 20));
                }
            }
            else{  //获取含有src属性的标签  如script标签  <script src="***" ></script>
                print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
            }
        }

        System.out.println();
        print("\nImports: (%d)", imports.size());
        for (Element link : imports) {
            if(link.attr("abs:href")!=null && !"".equals(link.attr("abs:href"))){  // link 标签 href不为空判断
                print(" * %s <%s> (%s)", link.tagName(),link.attr("abs:href"), link.attr("rel"));
            }
        }
        //获取a标签链接并打印
        print("\nLinks: (%d)", links.size());
        for (Element link : links) {
            if(link.attr("abs:href")!=null && !"".equals(link.attr("abs:href"))){  //a标签href不为空判断
                print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
            }
        }
    }

    /**
     * 传入url
     * 返回一个UrlQueue实例  填充img的src地址
     * */
    public static LinkedList<String> putImgUrlIntoQueue(String url) throws Exception{
        LinkedList<String> imgsList = new LinkedList<String>();
        UrlQueue imgQueue = new UrlQueue();
        String html =  getHtmlContentByUrl(url);
        if(html==null || html.length()==0){
            return null;
        }

        Document doc = Jsoup.parse(html);
        Elements media = doc.select("[src]"); //获取有src属性值的所有标签
        Elements imports = doc.select("link[href]");
        Elements links = doc.select("a[href]");

        //获取图片链接并打印
        print("\nMedia: (%d)", media.size());
        for (Element src : media) {
            if (src.tagName().equals("img")){//在所有具有src属性的标签中获取img标签
                if(src.attr("abs:src")!=null && !"".equals(src.attr("abs:src"))){ // img 标签 src不为空判断
                    print(" * %s: <%s> (%s)",
                            src.tagName(), src.attr("abs:src"), trim(src.attr("alt"), 20));
                    imgQueue.addElem(src.attr("abs:src"));
                }
            }
            else{  //获取含有src属性的标签  如script标签  <script src="***" ></script>
                if(src.attr("abs:src")!=null || !"".equals(src.attr("abs:src"))){
                    print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
                }
            }
        }
        return imgQueue.getUrlQueue();
    }

    /**
     * 下载图片方法1
     * url
     * */
    public static void downloadImgFromList(String url) throws Exception{
        LinkedList<String> urlList = putImgUrlIntoQueue(url);
        if(urlList!=null && urlList.size()>0){
            for (String item : urlList ) {
                HttpClientImage.downloadImg(item,UUID.randomUUID().toString()+".png");
            }
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

    //打印元素为list的元素
    public static void printListString(LinkedList<String> list){
        if(list!=null && list.size()>0){
            for (String item:list ) {
                System.out.println(item);
            }
        }
    }

    public static void main(String[] args) throws Exception {
//        System.out.println(getHtmlContentByUrl("https://www.oschina.net/"));
//        printResourceFromHtml(getHtmlContentByUrl("http://tieba.baidu.com/p/2647232671#!/l/p1"));
//        printListString(putImgUrlIntoQueue("https://www.oschina.net/"));
        downloadImgFromList("http://tieba.baidu.com/p/5420705803?red_tag=o0471958342");
    }

}
