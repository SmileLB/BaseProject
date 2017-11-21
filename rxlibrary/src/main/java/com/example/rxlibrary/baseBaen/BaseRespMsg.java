
package com.example.rxlibrary.baseBaen;

import java.io.Serializable;


public class BaseRespMsg<T> implements Serializable {

    protected  int error_code;
    protected String reason;
    private T result;

    public T getData() {
        return result;
    }

    public void setData(T data) {
        this.result = data;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
