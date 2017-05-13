package com.shisheng.util;

/**
 * Created by Magic on 2017/4/18.
 */
public class MyBoolean {
    private boolean success;

    private String Message;

    public MyBoolean(boolean success, String message) {
        this.success = success;
        Message = message;
    }

    public MyBoolean(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
