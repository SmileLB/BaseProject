package com.example.administrator.baseproject.http;

import android.content.Context;

import com.example.uilibrary.utils.ACache;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by LiBing
 * on 2017/11/22 0022
 * describe:
 */

public class RecieveCookieInterceptor implements Interceptor {

    private Context mContext;

    public RecieveCookieInterceptor(Context context) {
        this.mContext = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        // 获取 Cookie
        Response resp = chain.proceed(chain.request());
        List<String> cookies = resp.headers("Set-Cookie");
        String cookieStr = "";
        if (cookies != null && cookies.size() > 0) {
            for (int i = 0; i < cookies.size(); i++) {
                cookieStr += cookies.get(i);
            }
            //保存Cookie
            ACache.get(mContext).put("cookie",cookieStr);
        }
        return resp;
    }
}
