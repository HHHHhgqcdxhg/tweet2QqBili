package com.ggemo.tweet.cqclient;

import com.ggemo.tweet.common.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class QqSender {

    @Autowired
    RedisUtil redisUtil;

    @Value("http://${qqServer.host}:${qqServer.port}/")
    String baseUrl;

    @Value("${qqServer.accessToken}")
    String accessToken;

    private Header tokenHeader;

    private ThreadLocal<HttpClient> httpClientThreadLocal;

    @PostConstruct
    public void init() {
        if(!accessToken.isEmpty()){
            this.tokenHeader = new BasicHeader("Authorization", "Token " + accessToken);
        }else{
            this.tokenHeader = null;
        }
        HttpClient httpClient = HttpClients.createDefault();
        this.httpClientThreadLocal = new ThreadLocal<>();
        this.httpClientThreadLocal.set(httpClient);
    }

    private static UrlEncodedFormEntity dumpEntityFromMap(Map<String, String> map){
        List<NameValuePair> kvs = new ArrayList<>(map.size());
        for (String k : map.keySet()) {
            kvs.add(new BasicNameValuePair(k, map.get(k)));
        }
        UrlEncodedFormEntity entity1 = null;
        try {
            entity1 = new UrlEncodedFormEntity(kvs);
        } catch (UnsupportedEncodingException e) {
            log.error(e.toString());
            e.printStackTrace();
        }
        return entity1;
    }

    private HttpEntity request(String url, Map<String, String> entity) {
        HttpClient httpClient = httpClientThreadLocal.get();

        try {
            HttpPost r = new HttpPost(url);

            if (tokenHeader != null) {
                r.setHeader(tokenHeader);
            }

            UrlEncodedFormEntity entity1 = dumpEntityFromMap(entity);
            r.setEntity(entity1);

            HttpResponse httpResponse = null;
            try {
                httpResponse = httpClient.execute(r);
                return httpResponse.getEntity();
            } catch (IOException e) {
                log.error(e.toString());
                e.printStackTrace();
                return null;
            }

        } finally {
            httpClientThreadLocal.remove();
        }
    }

    public HttpEntity sendGroupMsg(int groupId, String msg) {
        String url = baseUrl + ApiEnum.SEND_GROUP_MSG.getUrl();
        Map<String, String> entity = new HashMap<>();
        entity.put("group_id",Integer.toString(groupId));
        entity.put("message", msg);
        return request(url, entity);
    }
}
