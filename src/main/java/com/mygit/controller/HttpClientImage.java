package com.mygit.controller;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by yxf on 2017/11/30.
 */
public class HttpClientImage {

    public static void main(String[] args) throws ClientProtocolException, IOException {
        //创建httpclient实例
        CloseableHttpClient httpClient= HttpClients.createDefault();
        //创建httpget实例
        HttpGet httpGet=new HttpGet("https://static.oschina.net/uploads/space/2017/1125/102748_KeBM_2340783.png");  //系統有限制
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.110 Safari/537.36");

        //执行http get 请求
        CloseableHttpResponse response=null;
        response=httpClient.execute(httpGet);
        HttpEntity entity=response.getEntity();//获取返回实体
        //EntityUtils.toString(entity,"utf-8");//获取网页内容，指定编码
        //System.out.println("網頁內容"+EntityUtils.toString(entity,"gb2312"));
        //查看响应类型
        if(entity!=null)
        {
            System.out.println(entity.getContentType().getValue());
            InputStream input=entity.getContent();
            FileUtils.copyInputStreamToFile(input, new File("D://111.png"));
        }
        System.out.println(response.getStatusLine().getStatusCode());
        //HTTP/1.1 200 OK    200
        response.close();
        httpClient.close();
    }

    public static void downloadImg(String url,String filename) throws ClientProtocolException, IOException{
        if (url==null || "".equals(url))
            return;

        //创建httpclient实例
        CloseableHttpClient httpClient= HttpClients.createDefault();
        //创建httpget实例
        HttpGet httpGet=new HttpGet(url);  //系統有限制
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.110 Safari/537.36");

        //执行http get 请求
        CloseableHttpResponse response=null;
        response=httpClient.execute(httpGet);
        HttpEntity entity=response.getEntity();//获取返回实体
        //EntityUtils.toString(entity,"utf-8");//获取网页内容，指定编码
        //System.out.println("網頁內容"+EntityUtils.toString(entity,"gb2312"));
        //查看响应类型
        if(entity!=null)
        {
            System.out.println(entity.getContentType().getValue());
            InputStream input=entity.getContent();
            FileUtils.copyInputStreamToFile(input, new File("D://imgs//"+filename));
        }
        System.out.println(response.getStatusLine().getStatusCode());
        //HTTP/1.1 200 OK    200
        response.close();
        httpClient.close();

    }



}
