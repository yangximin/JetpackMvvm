package com.yang.jetpack.http.httptool;

import com.yang.jetpack.http.data.BaseResponse;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;


/**
 * Created by yxm on 2021/1/11.
 */
public class RequestResultFunc<T> implements Function<BaseResponse<T>, T> {
    @Override
    public T apply(@NonNull BaseResponse<T> tBaseResponse) throws Exception {
        if (tBaseResponse.getErrorCode() != 0) {
            throw new Exception(tBaseResponse.getErrorMsg());
        }
        return tBaseResponse.getData();
    }
}
