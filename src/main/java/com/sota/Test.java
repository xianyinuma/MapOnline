package com.sota;
//
//import com.sota.controller.UserController;
//import com.sota.message.Base64String;
//import com.sota.message.EditResponse;
//import com.sota.message.UploadResponse;
//import net.sf.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * Created by wenjin on 2017/5/8.
 */
public class Test {
    public static void main(String[] args) throws IOException {
//

//
//        String param = "username=maji&password=majiji";
        String editJson = "{\"username\": \"maji\",\n" +
                "        \"password\": \"majiji\",\n" +
                "        \"uploadResponse\": {\n" +
                "            \"imageID\": 1,\n" +
                "            \"title\": \"haha\"\n," +
                "            \"description\": \"huhuhu\",\n" +
                "            \"adultOrNot\": 0,\n" +
                "            \"tags\": [\n" +
                "                \"man\",\n" +
                "                \"person\",\n" +
                "                \"dark\",\n" +
                "                \"looking\",\n" +
                "                \"standing\",\n" +
                "                \"front\",\n" +
                "                \"black\",\n" +
                "                \"monitor\",\n" +
                "                \"holding\",\n" +
                "                \"water\",\n" +
                "                \"red\",\n" +
                "                \"room\",\n" +
                "                \"flying\"\n" +
                "            ],\n" +
                "            \"weatherData\": {\n" +
                "                \"tempDay\": 30,\n" +
                "                \"tempNight\": 12,\n" +
                "                \"cityName\": \"鹤峰县\",\n" +
                "                \"conditionDay\": \"晴\",\n" +
                "                \"conditionNight\": \"晴\"\n" +
                "            }\n" +
                "        }\n" +
                "    }";
        String param = "username=jijijiji&password=jijijiji&latitude=32&longitude=100&base64Coding=" +
                "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";// base64Coding
        String test = "username=ji&password=jiji&friendID=4";
//        JSONObject jsonObject = JSONObject.fromObject(editString);
//        EditResponse editResponse = (EditResponse) JSONObject.toBean(jsonObject, EditResponse.class);
//        System.out.println(editResponse.getUploadResponse().getImageID());

        System.out.println(sendPostRequest("http://127.0.0.1:8080/delete-friend", "username=majimaji&password=majimaji&friendID=4"));

        // Base64String.base64ToImage(base64, "src/main/resources/testout/test.jpg");
    }

    //url:ip:8080/login post:username,password
    public static String sendGetRequest(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    public static String sendPostRequest(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "User-Agent,Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.110 Safari/537.36");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static String sendJSONPostRequest(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Content-type", "application/json;charset=UTF-8");
            conn.setRequestProperty("user-agent",
                    "User-Agent,Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.110 Safari/537.36");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }
}
