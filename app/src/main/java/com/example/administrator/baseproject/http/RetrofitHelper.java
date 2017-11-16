package com.example.administrator.baseproject.http;

import android.app.Application;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by LiBing
 * on 2017/11/16 0016
 * describe:Retrofit帮助类
 */

public class RetrofitHelper {

    //设置缓存目录
    private static File cacheFile;
    private static long maxSize = 8 * 1024 * 1024; //缓存文件大小
    private final static long TIMEOUT = 500;  //超时时间
    private Cache mCache;
    private final static String baseUrl = "";
    private OkHttpClient mClient;
    private Retrofit mRetrofit;
    private final HttpService mHttpService;
    private static String TAG;
    private static RetrofitHelper retrofitHelper;

    private RetrofitHelper() {
        TAG = this.getClass().getSimpleName();
        mHttpService = getRetrofit().create(HttpService.class);
    }

    //单例 保证对象唯一
    public static RetrofitHelper getInstance(Application application) {
        if (retrofitHelper == null) {
            synchronized (RetrofitHelper.class) {
                if (retrofitHelper == null) {
                    cacheFile = new File(application.getCacheDir().getAbsolutePath(), "cache");
                    if (!cacheFile.exists()) {
                        cacheFile.mkdir();
                    }
                    retrofitHelper = new RetrofitHelper();
                }
            }
        }
        return retrofitHelper;
    }

    //创建缓存文件和目录
    private Cache getCache() {
        if (mCache == null)
            mCache = new Cache(cacheFile, maxSize);
        return mCache;
    }

    //获取OkHttpClient
    private OkHttpClient getOkHttpClient() {
        if (mClient == null) {
            mClient = new OkHttpClient.Builder()
                    .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                    //.addInterceptor(headInterceptor)
                    .addInterceptor(loggingInterceptor)
                    .cache(getCache())      //设置缓存
                    .build();
        }
        return mClient;
    }

    //获取Retrofit对象
    public Retrofit getRetrofit() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())        //添加Gson支持
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())  //添加RxJava支持
                    .client(getOkHttpClient())                                 //关联okhttp
                    .build();
        }
        return mRetrofit;
    }

    //获取服务对象
    public static synchronized HttpService getService(Application application) {
        return RetrofitHelper.getInstance(application).mHttpService;
    }

    //日志拦截器
    private final static Interceptor headInterceptor = new Interceptor() {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request mRequest = chain.request();
            //在这里你可以做一些想做的事,比如token失效时,重新获取token
            //或者添加header等等
            return chain.proceed(mRequest);
        }
    };

    //日志拦截器
    private final static HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
        @Override
        public void log(String message) {
            Log.e(TAG + " : log", message);
        }
    }).setLevel(HttpLoggingInterceptor.Level.BODY);
}
