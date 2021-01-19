package com.yang.jetpack.http.data;

import com.yang.jetpack.bean.BaseBean;

/**
 * Created by yxm on 2021/1/11.
 */
public class BaseResponse<T> extends BaseBean {

    /**
     * "data": ...,
     * "errorCode": 0,
     * "errorMsg": ""
     */
    private T data;

    private int errorCode;

    private String errorMsg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
