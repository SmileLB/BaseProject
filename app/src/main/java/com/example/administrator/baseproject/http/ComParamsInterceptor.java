package com.example.administrator.baseproject.http;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * Created by LiBing
 * on 2017/11/22 0022
 * describe:
 */

public class ComParamsInterceptor implements Interceptor {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private Gson mGson;
    private Context mContext;

    public ComParamsInterceptor(Context context, Gson gson) {
        this.mContext = context;
        this.mGson = gson;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        try {
            String method = request.method();
            HashMap<String, Object> commomParamsMap = new HashMap<>();
//            commomParamsMap.put("chars", "聚合数据");
            commomParamsMap.put("key", "a8cf8dac8c50d46000b41d2e08727862");

            if (method.equals("GET")) {
                HttpUrl httpUrl = request.url();
                Set<String> paramNames = httpUrl.queryParameterNames();
                if (paramNames != null) {
                    for (String key : paramNames) {
                        commomParamsMap.put(key, httpUrl.queryParameter(key));
                    }
                }
                String url = httpUrl.toString();
                int index = url.indexOf("?");
                if (index > 0) {
                    url = url.substring(0, index);
                }
                url = url + "?";
                for (Map.Entry<String, Object> entry : commomParamsMap.entrySet()) {
                    url = url + entry.getKey() + "=" + entry.getValue() + "&";
                }
                url = url.substring(0, url.length() - 1);
                request = request.newBuilder().url(url).build();

            } else if (method.equals("POST")) {
                RequestBody body = request.body();
                HashMap<String, Object> rootMap = new HashMap<>();
                if (body instanceof FormBody) {
                    // form 表单
//                    for (int i = 0; i < ((FormBody) body).size(); i++) {
//                        rootMap.put(((FormBody) body).encodedName(i), ((FormBody) body).encodedValue(i));
//                    }
                } else {
                    Buffer buffer = new Buffer();
                    body.writeTo(buffer);
                    String oldJsonParams = buffer.readUtf8();
                    if (!TextUtils.isEmpty(oldJsonParams)) {
                        rootMap = mGson.fromJson(oldJsonParams, HashMap.class); // 原始参数
                    }
                    for (Map.Entry<String, Object> entry : commomParamsMap.entrySet()) {
                        rootMap.put(entry.getKey(), entry.getValue());
                    }
                    // 添加新的参数
                    HttpUrl.Builder authorizedUrlBuilder = request.url()
                            .newBuilder()
                            .scheme(request.url().scheme())
                            .host(request.url().host());

                    for (Map.Entry<String, Object> entry : rootMap.entrySet()) {
                        authorizedUrlBuilder.addQueryParameter(entry.getKey(), (String) entry.getValue());
                    }
                    // 新的请求
                    request = request.newBuilder()
                            .method(request.method(), request.body())
                            .url(authorizedUrlBuilder.build())
                            .build();
                }
            }

        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return chain.proceed(request);
    }
}
