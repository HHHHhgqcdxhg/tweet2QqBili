package com.ggemo.tweet.pojo.vo;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class BaiduTransRes {

    @Data
    public class SrcDst{
        String src;
        String dst;
    }
    public String from;
    public String to;
    public List<SrcDst> trans_result;

    public String getDst(){
        return StringUtils.join(trans_result.stream().map((s)->s.dst).collect(Collectors.toList()), "\n");
    }
}
