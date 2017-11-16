package com.example.administrator.baseproject.http;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by LiBing
 * on 2017/11/16 0016
 * describe:
 */

public interface HttpService {

    @POST("")
    Observable<Response<String>> checkHardWare(@FieldMap Map<String, String> map);

    @GET("app/{id}")
    Observable<Response<String>> getAppDetail(@Path("id") int id);
}
