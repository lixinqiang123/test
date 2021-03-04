package com.fh.api.test;

import com.alibaba.fastjson.JSON;
import com.fh.api.entity.po.MongoLog;
import com.fh.api.entity.po.User;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestHttpClient {

    /**
     *
     * post
     * 传参 普通参数 加对象

     */
    public static  void dopostList(){

        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();


        //创建post请求参数
        URI uri=null;

        try {

            // 将参数放入键值对类NameValuePair中,再放入集合中
            List<NameValuePair> params = new ArrayList<>();

            params.add(new BasicNameValuePair("flag", "4"));
            params.add(new BasicNameValuePair("message", "这是什么鬼"));


            // 设置uri信息,并将参数集合放入uri;
            // 注:这里也支持一个键值对一个键值对地往里面放setParameter(String key, String value)
            uri = new URIBuilder().setScheme("http").setHost("localhost").setPort(9000)
                    .setPath("/api/user/testadd").setParameters(params).build();
        }catch (Exception e){
            e.printStackTrace();
        }


        HttpPost httpPost = new HttpPost(uri);
        // HttpPost httpPost = new
        // HttpPost("http://localhost:12345/doPostControllerThree1");

        //创建对象参数
        User user = new User();
        user.setName("潘晓婷");
        user.setCreateDate(new Date());
        user.setWeight(33.9);

        // 将user对象转换为json字符串，并放入entity中
        StringEntity entity = new StringEntity(JSON.toJSONString(user), "UTF-8");

        // post请求是将参数放在请求体里面传过去的;这里将entity放入post请求体中
        httpPost.setEntity(entity);

        httpPost.setHeader("Content-Type", "application/json;charset=utf8");

        // 响应模型
        CloseableHttpResponse response = null;

        try {


            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();

            System.out.println("响应状态为:" + response.getStatusLine());

            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    /**
     * post传参 普通参数
     *
     */
    public static void dopostParams(){


        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        //参数
        StringBuffer params=new StringBuffer();
        try{

            // 字符数据最好encoding以下;这样一来，某些特殊字符才能传过去(如:某人的名字就是“&”,不encoding的话,传不过去)
            params.append("page=1" + URLEncoder.encode("&", "utf-8"));
            params.append("&");
            params.append("size=1");

        }catch (Exception e){
            e.printStackTrace();
        }

        //创建post请求
        HttpPost httpPost=new HttpPost("http://localhost:9000/api/user/queryUser"+"?"+params);

        // 设置ContentType(注:如果只是传普通参数的话,ContentType不一定非要用application/json)
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");

        // 响应模型
        CloseableHttpResponse response = null;

        try{

            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);

            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();

            System.out.println("响应状态为:" + response.getStatusLine());
            if(responseEntity!=null){

                System.out.println("响应内容长度为"+responseEntity.getContentLength());
                System.out.println("响应内容"+EntityUtils.toString(responseEntity));
            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }



    /**
     * get无参
     */
    public static void doGetTest(){

        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        //创建get请求
        HttpGet httpGet=new HttpGet("http://localhost:8085/api/log/queryLogAll");

        //响应模型
        CloseableHttpResponse response=null;

        try {

            //由客户端执行发送get请求
            response = httpClient.execute(httpGet);

            //从响应模型中获取实体
            HttpEntity entity = response.getEntity();

            System.out.println("响应状态为"+response.getStatusLine());

            if(entity!=null){

                System.out.println("响应长度为"+entity.getContentLength());
                System.out.println("响应内容"+ EntityUtils.toString(entity));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * get有参
     */
    public static  void dogetParams(){


        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        //参数
        StringBuffer params=new StringBuffer();

        try {

            params.append("name=lxq"+ URLEncoder.encode("&","utf-8"));
            params.append("&");
            params.append("age=99");

        }catch (UnsupportedClassVersionError e1){
            e1.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        //创建get请求
        HttpGet httpGet=new HttpGet("http://localhost:8085/api/log/testAdd"+"?"+params);

        //响应模型
        CloseableHttpResponse response=null;

        try {

            //由客户端执行发送get请求
            response = httpClient.execute(httpGet);

            //从响应模型中获取实体
            HttpEntity entity = response.getEntity();

            System.out.println("响应状态为"+response.getStatusLine());

            if(entity!=null){

                System.out.println("响应长度为"+entity.getContentLength());
                System.out.println("响应内容"+ EntityUtils.toString(entity)+"");
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }


    /**
     * post对象参数
     */
    public static  void doPostTest(){


        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        //创建post请求
        HttpPost httpPost=new HttpPost("http://localhost:8085/api/log/addMongoLog");

        //实例化对象
        MongoLog mongoLog=new MongoLog();
        mongoLog.setName("你们好");
        mongoLog.setLogDate(new Date());
        mongoLog.setAuthor("ddd");

        //把对象转为json
        String string = JSON.toJSONString(mongoLog);

        //获取实体类
        StringEntity stringEntity=new StringEntity(string,"UTF-8");

        //post请求是放到请求体中的
        httpPost.setEntity(stringEntity);


        httpPost.setHeader("Content-Type", "application/json;charset=utf8");

        //响应模型
        CloseableHttpResponse response=null;

        try{

            //有客户端执行post// 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);

            //从响应模型中获取实体
            HttpEntity entity = response.getEntity();

            System.out.println("响应状态为"+response.getStatusLine());


            if(entity!=null){
                System.out.println("响应长度为"+entity.getContentLength()+"");
                System.out.println("响应内容"+EntityUtils.toString(entity)+"");
            }


        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
