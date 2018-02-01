package com.common.rr_lib.utils;

import android.text.TextUtils;

import com.common.rr_lib.download.IDownloadListener;
import com.common.rr_lib.subscriber.AbstractBeanSubscriber;
import com.common.rr_lib.subscriber.AbstractStringSubscriber;
import com.common.rr_lib.utils.CommonUtils;
import com.common.rr_lib.utils.RetrofitUtils;

import org.reactivestreams.Subscriber;

import java.util.Map;

/**
 * Created by muhanxi on 18/2/1.
 *
 */
public class IHttp {


    /**
     * get 请求
     * @param url
     * @param subscriber
     */
    public static void get(String url,Subscriber<String> subscriber) {
        if(RetrofitUtils.apiService == null){
            throw new NullPointerException("you must init RetrofitUtils.Builder");
        }
        if(TextUtils.isEmpty(url)){
            throw new NullPointerException("parameter error");
        }
        CommonUtils.subscribe(RetrofitUtils.apiService.get(url),subscriber);
    }


    /**
     * get 请求  带标记
     * @param tag
     * @param url
     * @param subscriber
     */
    public static void get(String tag,String url,Subscriber<String> subscriber) {
        if(RetrofitUtils.apiService == null){
            throw new NullPointerException("you must init RetrofitUtils.Builder");
        }
        if(TextUtils.isEmpty(url)){
            throw new NullPointerException("parameter error");
        }
        if(TextUtils.isEmpty(tag)){
            throw new NullPointerException("tag not  allow null");
        }
        if(subscriber instanceof AbstractBeanSubscriber){
            AbstractBeanSubscriber beanSubscriber = (AbstractBeanSubscriber)subscriber ;
            beanSubscriber.setTag(tag);
        }
        if(subscriber instanceof AbstractStringSubscriber){
            AbstractStringSubscriber stringSubscriber = (AbstractStringSubscriber)subscriber ;
            stringSubscriber.setTag(tag);
        }
        CommonUtils.subscribe(RetrofitUtils.apiService.get(url),subscriber);
    }


    /**
     * post
     * @param url
     * @param map
     * @param subscriber
     */
    public static void post(String url, Map<String,String> map , Subscriber<String> subscriber) {

        if(RetrofitUtils.apiService == null){
            throw new NullPointerException("you must init RetrofitUtils.Builder");
        }

        if(TextUtils.isEmpty(url) || map == null ){
            throw new NullPointerException("url or parameter is null");
        }

        CommonUtils.subscribe(RetrofitUtils.apiService.post(url,map),subscriber);

    }


    /**
     * post 请求  带tag
     * @param tag
     * @param url
     * @param map
     * @param subscriber
     */
    public static void post(String tag,String url, Map<String,String> map , Subscriber<String> subscriber) {

        if(RetrofitUtils.apiService == null){
            throw new NullPointerException("you must init RetrofitUtils.Builder");
        }

        if(TextUtils.isEmpty(tag)){
            throw new NullPointerException("tag not  allow null");
        }

        if(TextUtils.isEmpty(url) || map == null ){
            throw new NullPointerException("url or parameter is null");
        }

        if(subscriber instanceof AbstractBeanSubscriber){
            AbstractBeanSubscriber beanSubscriber = (AbstractBeanSubscriber)subscriber ;
            beanSubscriber.setTag(tag);
        }
        if(subscriber instanceof AbstractStringSubscriber){
            AbstractStringSubscriber stringSubscriber = (AbstractStringSubscriber)subscriber ;
            stringSubscriber.setTag(tag);
        }

        CommonUtils.subscribe(RetrofitUtils.apiService.post(url,map),subscriber);

    }




    public static void downloadFile(String url, IDownloadListener listener){





    }




}
