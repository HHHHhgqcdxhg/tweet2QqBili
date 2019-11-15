package com.ggemo.tweet.cqclient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
@Component
public class QqMq {

    @Getter
    @AllArgsConstructor
    @ToString
    public class Task {
        private int qqGroup;
        private String messasg;
    }

    private BlockingQueue<Task> queue;

    @PostConstruct
    public void init(){
        queue = new LinkedBlockingQueue<>();
    }

    public void put(Task task) {
        while (true) {
            try {
                queue.put(task);
                return;
            } catch (InterruptedException e) {
                log.error("", e);
                e.printStackTrace();
            }
        }
    }

    public Task take() throws InterruptedException {
        return queue.take();
        }
}