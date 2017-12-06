package com.mygit.httpclient;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yxf on 2017/12/5.
 */
public class SetHeaderDemo {


    public static void main(String[] args) throws Exception {
        HttpPost post = new HttpPost("http://activity.huazhu.com/Content/h-land/index.html?utm_source=H5&utm_medium=Wechat&utm_campaign=HZ0052&utm_term=2016-11-21&source=PC0035");

        // 首先我们初始化请求头
        post.addHeader("Referer", "http://activity.huazhu.com");
        post.addHeader("Host", "activity.huazhu.com");
        post.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");

        post.setHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");

        post.setHeader("Accept-Encoding", "gzip, deflate, br");

        post.setHeader("Accept-Language", "zh-CN,zh;q=0.9");

        post.setHeader("Connection", "keep-alive");

        post.setHeader("Cookie", "gr_user_id=e4189009-1dc2-44e5-a4a6-c6976e313311; Hm_lvt_e5770a47472445b3f839a58a32b8abe5=1512441772; Hm_lpvt_e5770a47472445b3f839a58a32b8abe5=1512441923; __utma=107792157.1581886975.1512441772.1512441772.1512441772.1; __utmc=107792157; __utmz=107792157.1512441772.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); ASP.NET_SessionId=x4crwq0eyjtyh2nfl35dkzmb; p_t=afGlA5irDvlwe+QYl8MCwUpZaYz/3HBMkfefVaPsfAr8VWZ0bYKy3w==; .ASPXAUTH=E95E251D13BBC6D55A5BCD51BB8CC035437C821A3F39C446506454F3B7312635093F7235FE17FFF255917BF4B7C86688EFBA0B169EC89BF3AB14C04625C56A006ACD556E5B4087BE5910CF29F0FB544F9B8991CBD7A2493D7C67D7741ACC0D5A34B2A3B80D932AD85056348A5AA31A1A2C718BD5A420592BB874BC259A6D8E7EAA2EEA2F4D14A268FA57F736AE6BC6B0A3352FEA09B821F9F996C83996E14A9F0CF255F743FD8F30253885CB849EA840D4064615FB5C2F28FEB9D79475CA1445BF861AEDE4F1AC76C9721743466C5A5B434C36A809916BC72326364F043B5340569919E0CE27A619CEB5497675A312F6864E046592B3D25A5B5A3C27B5731F55E5160A864FDAEBB7CE5BAC3D8FAC48A13D3C6B37908490BD52E45B7E009703B8260AB282CC975CAA6BE9808D59365A6CD664C1BA2826ADD521CD235F9B6CDBF97D7B97A9AC06C7F93BD81B05ADF4E420603879A94237C32B00C477F94139838DF1207AA70E123DDFFDFF6DA783F109204875CE6B28ED346FA70FE974E6445471; unick=%e6%9d%a8%e5%ad%a6%e5%b3%b0; MemberLevelID=B; _ga=GA1.2.1581886975.1512441772; gr_session_id_8f6e3e7f89d647cab9784afa81ea87bd=a01a3580-cfce-4b61-91fb-723690a8af28; _HZ_SessionId=bMYeMlURdA2j8TMI9x5Rg1UZWCl1eYJPSvdDfVqyj40=; _HZ_actsessionId=mJOfjromq4EmHqAmoqOFPaf4jURDh1/g+LYML4fxfDI=; Hm_lvt_f14fa482025352f2dded0c2e008dd278=1512442640; Hm_lpvt_f14fa482025352f2dded0c2e008dd278=1512443832");


        // 然后我们填入我们想要传递的表单参数（主要也就是传递我们的用户名和密码）
        // 我们可以先建立一个List，之后通过post.setEntity方法传入即可
        // 写在一起主要是为了大家看起来方便，大家在正式使用的当然是要分开处理，优化代码结构的
        List<NameValuePair> paramsList = new ArrayList<NameValuePair>();
        /*
         * 添加我们要的参数，这些可以通过查看浏览器中的网络看到，如下面我的截图中看到的一样
         * 不论你用的是firebug,httpWatch或者是谷歌自带的查看器也好,都能查看到（后面会推荐辅助工具来查看）
         * 要把表单需要的参数都填齐，顺序不影响
         */
        paramsList.add(new BasicNameValuePair("utm_source", "H5"));
        paramsList.add(new BasicNameValuePair("utm_medium", "Wechat"));
        paramsList.add(new BasicNameValuePair("utm_campaign", "HZ0052"));
        paramsList.add(new BasicNameValuePair("utm_term", "2016-11-21"));
        paramsList.add(new BasicNameValuePair("source", "PC0035"));

        // 你可以申请一个天涯的账号 并在下两行代码中替换为你的用户名和密码
//        paramsList.add(new BasicNameValuePair("vwriter", "222"));// 替换为你的用户名
//        paramsList.add(new BasicNameValuePair("vpassword", "1"));// 你的密码

        // 将这个参数list设置到post中
        post.setEntity(new UrlEncodedFormEntity(paramsList, Consts.UTF_8));
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(post);

        HttpEntity entity = response.getEntity();//获取返回实体
        //EntityUtils.toString(entity,"utf-8");//获取网页内容，指定编码
//        System.out.println("网页内容\n"+ EntityUtils.toString(entity,"utf-8"));
        String html = EntityUtils.toString(entity,"utf-8");
        System.out.println(html);
        response.close();
        httpClient.close();
    }
}
