package com.ggemo.tweet.common.util;

import org.apache.commons.httpclient.HttpURL;
import org.apache.commons.httpclient.URIException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

@Component
public class ImageUtil {
    @Value("${image.saveDir}")
    private String saveDir;

    @Value("image.publicBaseUrl")
    private String publicBaseUrl;

    private File dirPath;

    CloseableHttpClient httpClient;

    @PostConstruct
    public void init() {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(100);
        this.httpClient = HttpClients.custom().setConnectionManager(cm).build();
        this.dirPath = new File(saveDir);
    }

    public Image downloadImage(String rawUrl) throws IOException {
        HttpGet httpget = new HttpGet(rawUrl);
        HttpResponse resp = httpClient.execute(httpget);

        HttpEntity entity = resp.getEntity();

        InputStream in = entity.getContent();

        String[] fileNameTmp = rawUrl.split("/");
        String fileName = fileNameTmp[fileNameTmp.length - 1];

        String localPath = savePicToDisk(in, this.dirPath, fileName);
        String publicUrl = this.publicBaseUrl.concat(fileName);
        return new Image(rawUrl, localPath, publicUrl);
    }

    /**
     * 将图片写到 硬盘指定目录下
     *
     * @param in
     * @param dir
     * @param fileName
     */
    private String savePicToDisk(InputStream in, File dir, String fileName) throws IOException {
        try (in) {
            if (!dir.exists()) {
                dir.mkdirs();
            }

            //文件真实路径
            String realPath = saveDir.concat(fileName);
            File file = new File(realPath);
            if (!file.exists()) {
                file.createNewFile();
            }

            FileOutputStream fos = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = in.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            fos.flush();
            fos.close();
            return realPath;
        }
    }

}
