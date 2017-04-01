package com.wondersgroup.healthxuhui.http;

import com.wondersgroup.healthxuhui.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yang on 16/7/5.
 */
public class AppClient {

    private static AppClient mInstance;

    private Retrofit mRetrofit;

    private OkHttpClient okHttpClient;


    private AppClient() {

    }

    public static AppClient getInstance()
    {
        if (mInstance == null)
        {
            synchronized (AppClient.class)
            {
                if (mInstance == null)
                {
                    mInstance = new AppClient();
                }
            }
        }
        return mInstance;
    }

    public Retrofit retrofit(){
        if (mRetrofit == null){
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(ApiStores.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(getOkHttpClient())
                    .build();
        }
        return mRetrofit;
    }

    public OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            if (BuildConfig.DEBUG) {//打印请求体
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                builder.addInterceptor(interceptor);
            }
            okHttpClient = builder.build();
        }
        return okHttpClient;
    }
}
