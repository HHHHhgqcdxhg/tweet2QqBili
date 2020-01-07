package com.ggemo.tweet.cqclient;

import com.ggemo.cqhttpclient.CqHttpClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Component
public class QqRequestService {

    @Value("${qq.sendMsgTimeWait}")
    int qqSendMsgTimeWait;

    @Autowired
    QqMq qqMq;

    @Value("${qqServer.baseurl}")
    String baseUrl;

    private CqHttpClient cqHttpClient;

    @PostConstruct
    public void init() {
        this.cqHttpClient = new CqHttpClient(baseUrl);
        this.start();
    }

    private void run() {
        for (; ; ) {
            QqMq.Task task = null;
            try {
                task = qqMq.take();
            } catch (InterruptedException | RuntimeException e) {
                log.error(e.toString());
                continue;
            }
            try {
                log.info("发起cqhttp发消息的请求: " + task);
                cqHttpClient.sendGroupMsg(task.getQqGroup(), task.getMessasg());
            } catch (IOException | RuntimeException e) {
                e.printStackTrace();
                log.error("", e);
            }
            try {
                Thread.sleep(qqSendMsgTimeWait);
            } catch (InterruptedException | RuntimeException e) {
                e.printStackTrace();
                log.error("", e);
            }
        }
    }

    public void start() {
        ExecutorService pool = Executors.newSingleThreadExecutor((r) -> {
            Thread t = new Thread(r);
            t.setName("qqRequestServiceThread");
            return t;
        });
        pool.execute(this::run);
    }
}
