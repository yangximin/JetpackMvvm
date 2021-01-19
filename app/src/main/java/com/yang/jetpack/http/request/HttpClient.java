package com.yang.jetpack.http.request;

import com.yang.jetpack.http.httptool.RequestResultFunc;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yxm on 2021/1/11.
 */
public class HttpClient {

    public static String HTTP_HOST_URL = "https://www.wanandroid.com";

    private static HttpClient mInstant;

    Retrofit mRetrofit;

    public static HttpClient getInstance() {
        if (null == mInstant) {
            synchronized (HttpClient.class) {
                if (null == mInstant) {
                    mInstant = new HttpClient();
                }
            }
        }
        return mInstant;
    }

    public HttpClient() {
        mRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(HTTP_HOST_URL)
                .client(HTTP_CLIENT)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
    }

    public <T> T createService(Class<T> service) {
        return mRetrofit.create(service);
    }

    public <T> void execute(Observable<T> observable, Observer observer) {
        observable.
                compose(schedulers())
                .compose(transformResponse())
                .subscribe(observer);
    }
    public <T> void executeOriginal(Observable<T> observable, Observer observer) {
        observable.
                compose(schedulers())
//                .compose(transformResponse())
                .subscribe(observer);
    }
    private static OkHttpClient HTTP_CLIENT = new OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build();

    public <T> ObservableTransformer<T, T> schedulers() {
        return new ObservableTransformer<T, T>() {
            @NonNull
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public <T> ObservableTransformer<T, T> transformResponse() {
        return new ObservableTransformer<T, T>() {
            @NonNull
            @Override
            public ObservableSource<T> apply(@NonNull Observable upstream) {
                return upstream.map(new RequestResultFunc<T>());
            }
        };
    }


}
