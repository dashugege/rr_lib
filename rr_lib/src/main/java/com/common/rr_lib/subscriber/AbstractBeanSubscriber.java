package com.common.rr_lib.subscriber;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by muhanxi on 18/1/29.
 * 返回值是json 直接解析成bean对象
 */
public abstract class AbstractBeanSubscriber<T> implements Subscriber<String> {

    private static final String DEFAULT = "default" ;

    /**
     * 成功回调
     * @param tag  请求标记
     * @param t    请求结果
     */
    protected abstract void onResponse(String tag , T t);

    /**
     * 请求失败回调
     * @param code 失败编码
     */
    protected abstract void onErrorResponse(int code);

    private String mTag = null;
    private Subscription mSubscription ;
    private Class mBeanClass ;


    public AbstractBeanSubscriber(){
        this(DEFAULT);
    }

    public AbstractBeanSubscriber(String tag){
        try {
            this.mTag = tag;
            Type genType = getClass().getGenericSuperclass();
            Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
            mBeanClass  = (Class) params[0];
        } catch (Exception e) {
            e.printStackTrace();
        }


    }



    @Override
    public void onSubscribe(Subscription s) {
        if(s != null){
            s.request(Integer.MAX_VALUE);
            mSubscription = s ;
        }
    }

    @Override
    public void onNext(String result) {

        try {
            System.out.println("receive data = " + result);
            T t = (T) JSONObject.parseObject(result,mBeanClass);
            onResponse(mTag,t);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            onErrorResponse(1);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            cancel();
        }
    }

    @Override
    public void onError(Throwable t) {
        System.out.println("onError t = " + t.getMessage());
        onErrorResponse(1);
        cancel();
    }

    @Override
    public void onComplete() {


    }


    private void cancel(){
        if(mSubscription != null){
            mSubscription.cancel();
        }
        mSubscription = null ;
    }



}
