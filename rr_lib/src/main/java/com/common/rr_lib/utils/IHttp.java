package com.common.rr_lib.utils;

import android.text.TextUtils;

import com.common.rr_lib.utils.CommonUtils;
import com.common.rr_lib.utils.RetrofitUtils;

import org.reactivestreams.Subscriber;

import java.util.Map;

/**
 * Created by muhanxi on 18/2/1.
 */

public class IHttp {



    public static void get(String url,Subscriber<String> subscriber) {

        if(RetrofitUtils.apiService == null){
            throw new NullPointerException("you must init RetrofitUtils.Builder");
        }

        if(TextUtils.isEmpty(url)){
            throw new NullPointerException("parameter error");
        }

        CommonUtils.subscribe(RetrofitUtils.apiService.get(url),subscriber);
    }


    public static void post(String url, Map<String,String> map , Subscriber<String> subscriber) {

        if(RetrofitUtils.apiService == null){
            throw new NullPointerException("you must init RetrofitUtils.Builder");
        }

        if(TextUtils.isEmpty(url) || map == null ){
            throw new NullPointerException("url or parameter is null");
        }

        CommonUtils.subscribe(RetrofitUtils.apiService.post(url,map),subscriber);

    }


}
