package com.yang.jetpack.http.httptool;

import com.google.gson.Gson;
import com.yang.jetpack.http.data.BaseResponse;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by yxm on 2021/1/20.
 */
public class ResponseConverterFactory extends Converter.Factory {
    private Gson mGson;

    public static ResponseConverterFactory create() {
        return create(new Gson());
    }

    public static ResponseConverterFactory create(Gson gson) {
        return new ResponseConverterFactory(gson);
    }

    public ResponseConverterFactory(Gson mGson) {
        this.mGson = mGson;
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return super.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new GsonResponseConverterFactory(type);
    }

    public class GsonResponseConverterFactory<T> implements Converter<ResponseBody, T> {
        Type type;

        public GsonResponseConverterFactory(Type type) {
            this.type = type;
        }

        @Override
        public T convert(ResponseBody value) throws IOException {
            Object obj;
            String body = value.string();

            if (!body.contains("errorCode")) {
                return mGson.fromJson(body, type);
            }
            BaseResponse baseResponse = mGson.fromJson(body, BaseResponse.class);
            if (baseResponse.getErrorCode() != 0) {
                throw new HttpException(baseResponse.getErrorCode(), baseResponse.getErrorMsg());
            }
            String bodyJson = mGson.toJson(baseResponse.getData());
            obj = mGson.fromJson(bodyJson, type);
            return (T) obj;
        }
    }
}
