package com.example.retrofitsample.retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    private static Retrofit mRetrofit;

    private static Api mApiService;

    private static OkHttpClient mOkHttpClient;

    public static OkHttpClient getOkHttpClient() {
        if (mOkHttpClient == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
            mOkHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(3000, TimeUnit.SECONDS)
                    .readTimeout(3000, TimeUnit.SECONDS)
                    .addInterceptor(logging)
                    .build();
        }
        return mOkHttpClient;
    }

    public static Retrofit getRetrofit() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl("http://www.baidu.com/")
                    .addConverterFactory(StringConverterFactory.create())
                    .client(getOkHttpClient())
                    .build();

        }
        return mRetrofit;
    }

    public static Api getApiService() {
        if (mApiService == null) {
            mApiService = getRetrofit().create(Api.class);
        }
        return mApiService;
    }
}
