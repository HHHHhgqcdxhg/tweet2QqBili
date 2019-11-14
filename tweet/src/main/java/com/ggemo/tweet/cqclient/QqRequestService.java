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
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                cqHttpClient.sendGroupMsg(task.getQqGroup(), task.getMessasg());
            } catch (IOException e) {
                e.printStackTrace();
                log.error("", e);
            }
            while (true) {
                try {
                    Thread.sleep(qqSendMsgTimeWait);
                    break;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    log.error("", e);
                }
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
