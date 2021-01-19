package com.yang.jetpack.base.model;

import com.yang.jetpack.http.request.HttpClient;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * Created by yxm on 2021/1/12.
 */
public class BaseModel {

    protected void execute(Observable<?> observable, Observer<?> observer) {
        HttpClient.getInstance().execute(observable, observer);
    }
    protected void executeOriginal(Observable<?> observable, Observer<?> observer) {
        HttpClient.getInstance().executeOriginal(observable, observer);
    }
}
