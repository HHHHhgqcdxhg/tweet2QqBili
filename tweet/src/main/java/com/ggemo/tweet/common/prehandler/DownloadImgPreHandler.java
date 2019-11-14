package com.ggemo.tweet.common.prehandler;

import com.ggemo.tweet.common.StatusWrapper;
import com.ggemo.tweet.common.util.Image;
import com.ggemo.tweet.common.util.ImageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import twitter4j.MediaEntity;
import twitter4j.Status;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class DownloadImgPreHandler implements PreHandler {
    private static final String NAME = "DownloadImgPreHandler";
    @Autowired
    private ImageUtil imageUtil;

    @Override
    public StatusWrapper handle(StatusWrapper statusWrapper) {
        Status status = statusWrapper.getStatus();
        List<Image> images = new ArrayList<>();
        for(MediaEntity i :status.getMediaEntities()){
            if("photo".equals(i.getType())){
                Image image = null;
                while(true) {
                    try {
                        log.info("下载图片" + i.getMediaURL());
                        image = imageUtil.downloadImage(i.getMediaURL());
                        break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                images.add(image);
            }
        }
        statusWrapper.setImages(images);
        return statusWrapper;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
