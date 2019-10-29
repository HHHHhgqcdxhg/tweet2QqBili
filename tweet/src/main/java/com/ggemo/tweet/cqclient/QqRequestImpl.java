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
import org.apache.http.util.EntityUtils;
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
public class QqRequestImpl implements QqRequest {

    @Autowired
    RedisUtil redisUtil;

    @Value("http://${qqServer.host}:${qqServer.port}/")
    String baseUrl;

    @Value("${qqServer.accessToken}")
    String accessToken;

    private Header tokenHeader;

    private static final ThreadLocal<HttpClient> HTTP_CLIENT_THREAD_LOCAL = new ThreadLocal<>();
    private static final HttpClient HTTP_CLIENT = HttpClients.createDefault();

    @PostConstruct
    public void init() {
        if (!accessToken.isEmpty()) {
            this.tokenHeader = new BasicHeader("Authorization", "Token " + accessToken);
        } else {
            this.tokenHeader = null;
        }
    }

    private static UrlEncodedFormEntity dumpEntityFromMap(Map<String, String> map) {
        List<NameValuePair> kvs = new ArrayList<>(map.size());
        for (String k : map.keySet()) {
            kvs.add(new BasicNameValuePair(k, map.get(k)));
        }
        UrlEncodedFormEntity entity1 = null;
        try {
            entity1 = new UrlEncodedFormEntity(kvs, "utf-8");
        } catch (UnsupportedEncodingException e) {
            log.error(e.toString());
            e.printStackTrace();
        }
        return entity1;
    }

    private HttpEntity request(String url, Map<String, String> map) {
        HTTP_CLIENT_THREAD_LOCAL.set(HTTP_CLIENT);
        HttpClient httpClient = HTTP_CLIENT_THREAD_LOCAL.get();
        try {
            HttpPost r = new HttpPost(url);

            if (tokenHeader != null) {
                r.addHeader(tokenHeader);
            }

            UrlEncodedFormEntity entity = dumpEntityFromMap(map);
            r.setEntity(entity);
            HttpResponse httpResponse = null;
            try {
                httpResponse = httpClient.execute(r);
                HttpEntity e = httpResponse.getEntity();
                return e;
            } catch (IOException e) {
                log.error(e.toString());
                e.printStackTrace();
                return null;
            }
        }finally {
            HTTP_CLIENT_THREAD_LOCAL.remove();
        }
    }

    @Override
    public HttpEntity sendGroupMsg(int groupId, String msg) {
        String url = baseUrl + ApiEnum.SEND_GROUP_MSG.getUrl();
        Map<String, String> map = new HashMap<>();
        map.put("group_id", Integer.toString(groupId));
        map.put("message", msg);
        var res = request(url, map);
        try {
            log.info(String.format("Send to qqGroup: groupId=%d, res=%s", groupId, EntityUtils.toString(res, "utf-8")));
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.toString());
        }
        return res;
    }

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>() {{
            put("大师傅", "123撒的发");
        }};
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
        try {
            System.out.println(EntityUtils.toString(entity1, "utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ;

    }
}
