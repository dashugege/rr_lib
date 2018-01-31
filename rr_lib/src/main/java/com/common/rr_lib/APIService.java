package com.common.rr_lib;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by muhanxi on 18/1/29.
 */

public interface APIService {


    @GET
    Flowable<String> get(@Url String url);

    @GET
    Flowable<String> get(@Url String url,@QueryMap Map<String,String> map);

    @FormUrlEncoded
    @POST
    Flowable<String> post(@Url String url,@FieldMap Map<String,String> map);

}
