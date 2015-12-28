package com.iceblock.myuntil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Http 协议工具类
 *
 * @author YanLiang
 * @date 2015-12-2.
 */
public class HttpReponseTimeUtil {

    /**
     * 防止实例化
     */
    private HttpReponseTimeUtil() {
    }

    /**
     * 获取 get 请求的响应时间，试用于页面访问、加载时间的计算，
     * 不包括页面 JS 运行、CSS 渲染的时间，只是页面内容下载到本
     * 地的时间。
     *
     * @param url   访问地址
     * @param param 请求参数
     * @return 响应时间(ms). -1：访问异常，无法访问；
     */
    public static Long getHttpReponsTime(String url, String param) {
        Long reponseTime;
        reponseTime = 0L;
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            Long startTime = System.currentTimeMillis();
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            // System.out.println(result);
            Long endTime = System.currentTimeMillis();
            reponseTime = endTime - startTime;
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
            reponseTime = -1L;
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
        return reponseTime;
    }

    public static void main(String[] args) {
        String url = "https://github.com/";
        System.out.println("响应时间：" + HttpReponseTimeUtil.getHttpReponsTime(url, null));
    }
}
