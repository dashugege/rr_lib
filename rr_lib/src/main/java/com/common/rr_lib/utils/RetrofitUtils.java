package com.common.rr_lib.utils;

import android.content.Context;
import android.text.TextUtils;

import com.common.rr_lib.APIService;

import org.reactivestreams.Subscriber;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by muhanxi on 18/1/29.
 *
 */
public final class RetrofitUtils {


    private volatile static Retrofit INSTANCE = null;

    private static APIService apiService ;




    private final boolean mDebug ;
    private final Context mContext;
    private final HashMap<String,String> mHeaderMap ;
    private final String mBaseurl;

    private RetrofitUtils(boolean debug,HashMap<String,String> header,Context context,String baseurl){
        this.mDebug = debug;
        this.mContext = context;
        this.mHeaderMap = header;
        this.mBaseurl = baseurl;
        getInstance(mContext);
    }






    private Retrofit getInstance(Context context){
        if(INSTANCE == null){
            synchronized (RetrofitUtils.class){
                if (INSTANCE == null){
                    INSTANCE = new Retrofit.Builder()
                            .baseUrl(mBaseurl)
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .client(new OkHttpUtils(mHeaderMap).getInstance(context))
                            .build();
                }
            }
        }
        return INSTANCE ;
    };




    private static APIService create(){
        if(INSTANCE == null){
            throw  new NullPointerException("you first create RetrofitUtils.Builder");
        }
        if(apiService == null){
            apiService = INSTANCE.create(APIService.class) ;
        }
       return apiService ;
    }







    public static void get(String url,Subscriber<String> subscriber) {
        if(TextUtils.isEmpty(url)){
            throw new NullPointerException("parameter error");
        }

        CommonUtils.subscribe(create().get(url),subscriber);


    }


    public static void post(String url, Map<String,String> map , Subscriber<String> subscriber) {
        if(TextUtils.isEmpty(url) || map == null ){
            throw new NullPointerException("url or parameter is null");
        }

        CommonUtils.subscribe(create().post(url,map),subscriber);

    }







    public static final class Builder {

        private boolean isDebug = false ;
        private HashMap<String,String> headerMap = null ;
        private Context context ;
        private String baseurl;


        public Builder(){
        }

        public Builder debug(boolean debug){
            this.isDebug = debug;
            return this;
        }

        public Builder context(Context context){
            this.context = context;
            return this;
        }

        public Builder addHeader(HashMap<String,String> headerMap){
            this.headerMap = headerMap;
            return this;
        }

        public Builder baseurl(String url){
            this.baseurl = url;
            return this;
        }

        public RetrofitUtils build(){
            if(context == null){
                throw new NullPointerException("you must init context");
            }
            if(TextUtils.isEmpty(baseurl)){
                throw  new NullPointerException("you must set baseurl");
            }

            return new RetrofitUtils(isDebug,headerMap,context,baseurl);
        }



    }


}
