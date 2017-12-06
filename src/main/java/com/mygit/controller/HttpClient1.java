package com.mygit.controller;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by yxf on 2017/11/30.
 * httpClient简单请求获取html内容
 */
public class HttpClient1 {

    public static void main(String[] args) throws ClientProtocolException, IOException {
        // 创建httpclient实例
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建httpget实例
        HttpGet httpGet = new HttpGet("https://www.oschina.net/");
        // 执行http get 请求
        CloseableHttpResponse response = null;
        response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();// 获取返回实体
        // EntityUtils.toString(entity,"utf-8");//获取网页内容，指定编码
        System.out.println("网页内容" + EntityUtils.toString(entity, "utf-8"));
        response.close();
        httpClient.close();
    }
}
