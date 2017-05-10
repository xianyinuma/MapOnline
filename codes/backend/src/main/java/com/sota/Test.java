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


        String test = "username=ji&password=jiji&friendID=4";
//        JSONObject jsonObject = JSONObject.fromObject(editString);
//        EditResponse editResponse = (EditResponse) JSONObject.toBean(jsonObject, EditResponse.class);
//        System.out.println(editResponse.getUploadResponse().getImageID());
        System.out.println(sendPostRequest("http://118.89.184.85:8080/register","username=ganmajia&password=ganmajia"));
//        System.out.println(sendPostRequest("http://118.89.184.85:8080/upload", "username=901demaji&password=majixiangchiji&longitude=121&latitude=60&base64Coding=" +
//                "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"));
//        System.out.println(editJson);
//        System.out.println(sendJSONPostRequest("http://127.0.0.1:8080/edit", editJson));
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
