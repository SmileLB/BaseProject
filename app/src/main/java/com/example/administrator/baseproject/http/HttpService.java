package com.example.administrator.baseproject.http;

import com.example.rxlibrary.baseBaen.BaseRespMsg;
import com.example.rxlibrary.baseBaen.Msg;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by LiBing
 * on 2017/11/16 0016
 * describe:
 */

public interface HttpService {

    @GET("telecode/to_telecodes.php")
    Observable<BaseRespMsg<Msg>> test(@Query("chars") String chars,@Query("key") String key);
}
