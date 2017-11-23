package com.example.administrator.baseproject.http;

import com.example.administrator.baseproject.request.Requstdate;
import com.example.administrator.baseproject.request.Requstdate2;
import com.example.rxlibrary.baseBaen.BaseRespMsg;
import com.example.rxlibrary.baseBaen.Msg;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by LiBing
 * on 2017/11/16 0016
 * describe:
 */

public interface HttpService {

    @GET("telecode/to_telecodes.php")
    Observable<BaseRespMsg<Msg>> test(@Query("chars") String chars);

    @GET("telecode/to_telecodes.php")
    Observable<BaseRespMsg<Msg>> test();

    @POST("telecode/to_telecodes.php")
    Observable<BaseRespMsg<Msg>> testPost(@QueryMap Map<String,String> map);

    @POST("telecode/to_telecodes.php")
    Observable<BaseRespMsg<Msg>> testPost(@Body Requstdate date);

    @POST("telecode/to_telecodes.php")
    Observable<BaseRespMsg<Msg>> testPost(@Body Requstdate2 date);

    @POST("telecode/to_telecodes.php")
    Observable<BaseRespMsg<Msg>> testPost2();

    @FormUrlEncoded
    @POST("telecode/to_telecodes.php")
    Observable<BaseRespMsg<Msg>> testFormPost(@Field("chars") String chars,@Field("key") String key);


//    @GET("telecode/to_telecodes.php")
//    Observable<BaseRespMsg<Msg>> test(@Query("chars") String chars,@Query("key") String key);

}
