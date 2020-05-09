package com.jin.tool.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class HttpUtils {
    /**
     * 获取网络图片流
     *
     * @param url 链接
     * @return
     */
    public static InputStream getImageStream(String url) {
        try {
            URL url1 = new URL(url.substring(0, url.lastIndexOf("/"))+"/"
                    + URLEncoder.encode(url.substring(url.lastIndexOf("/")+1, url.length()), "utf-8"));
            HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("GET");
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return connection.getInputStream();
            }
        } catch (IOException e) {
            System.out.println("获取网络图片出现异常，图片路径为：" + url);
            e.printStackTrace();
        }
        return null;
    }
}
