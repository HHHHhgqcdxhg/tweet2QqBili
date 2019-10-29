package com.ggemo.tweet.cqclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Component
public class QqRequestService {
    @Autowired
    QqRequest qqRequest;

    @Value("${qq.sendMsgTimeWait}")
    int qqSendMsgTimeWait;

    @Autowired
    QqMq qqMq;

    public void run() {
        while (true) {
            QqMq.Task task = qqMq.take();
            qqRequest.sendGroupMsg(task.getQqGroup(), task.getMessasg());
            while (true) {
                try {
                    Thread.sleep(qqSendMsgTimeWait);
                    break;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    log.error(e.toString());
                }
            }
        }
    }

    public void start() {
        ExecutorService pool = Executors.newSingleThreadExecutor((r)->{
            Thread t = new Thread(r);
            t.setName("qqRequestServiceThread");
            return t;
        });
        pool.execute(this::run);
    }
}
