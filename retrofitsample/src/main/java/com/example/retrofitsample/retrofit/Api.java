package com.example.retrofitsample.retrofit;

import com.example.retrofitsample.bean.BaseBean;
import com.example.retrofitsample.bean.Info;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("/")
    Call<String> getJsonData();
}
