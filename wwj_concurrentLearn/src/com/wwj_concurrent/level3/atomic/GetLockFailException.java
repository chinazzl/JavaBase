package com.wwj_concurrent.level3.atomic;

/**********************************
 * @author zhang zhao lin
 * @date 2021年09月26日 22:44
 * @Description
 **********************************/
public class GetLockFailException extends RuntimeException{

    private String msg;

    public GetLockFailException(String message) {
        super(message);
        this.msg = message;
    }

    public String getMsg() {
        return msg;
    }
}
