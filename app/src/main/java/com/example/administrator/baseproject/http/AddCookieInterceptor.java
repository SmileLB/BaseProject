package com.example.administrator.baseproject.http;

import android.content.Context;
import android.text.TextUtils;

import com.example.uilibrary.utils.ACache;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by LiBing
 * on 2017/11/22 0022
 * describe:
 */

public class AddCookieInterceptor implements Interceptor {

    private Context mContext;

    public AddCookieInterceptor(Context context) {
        this.mContext = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        // 设置 Cookie
        String cookieStr = ACache.get(mContext).getAsString("cookie");
        if (!TextUtils.isEmpty(cookieStr)) {
            return chain.proceed(chain.request().newBuilder().header("Cookie", cookieStr).build());
        }
        return chain.proceed(chain.request());
    }
}
