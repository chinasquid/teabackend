package com.teabackend.teabackend.comment.Bean;

import lombok.Data;

/**
 * @author shuyang
 * @Description:
 * @create 2020-05-11 14:22
 */
@Data
public class Result {
    private int status;
    private String msg = "";
    private Object body =null;

    public void success(){
        this.setStatus(1);
    }

    public void success(String msg){
        this.setStatus(1);
        this.setMsg(msg);
    }

    public void success(String msg,Object data){
        this.setStatus(1);
        this.setMsg(msg);
        this.setBody(data);
    }

    public void fail(){
        this.setStatus(0);
    }

    public void fail(String msg){
        this.setStatus(0);
        this.setMsg(msg);
    }
}
