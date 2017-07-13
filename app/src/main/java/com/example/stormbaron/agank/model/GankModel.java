package com.example.stormbaron.agank.model;

import android.util.Log;

import com.example.stormbaron.agank.model.api.Api;
import com.example.stormbaron.agank.model.api.apiservice.GankApiService;
import com.example.stormbaron.agank.model.entity.GankNewsEtity;
import com.example.stormbaron.agank.model.entity.ResultMode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by stormbaron on 17-6-26.
 */

public class GankModel {
    public static void getDataList() {
        Log.d("GankModel", "Start GankModel");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.API_DOMAIN)
                .addConverterFactory(GsonConverterFactory.create())//添加 json 转换器
                .build();
        GankApiService apiService = retrofit.create(GankApiService.class);//这里采用的是Java的动态代理模式
        Call<ResultMode<GankNewsEtity>> call =apiService.getList(10, 1);

        call.enqueue(new Callback<ResultMode<GankNewsEtity>>() {

            @Override
            public void onResponse(Call<ResultMode<GankNewsEtity>> call, Response<ResultMode<GankNewsEtity>> response) {
                Log.d("GankModel", response.body().toString());
            }
            @Override
            public void onFailure(Call<ResultMode<GankNewsEtity>> call, Throwable t) {
                Log.d("GankModel", " onFailure"+call.request().url());
            }
        });
    }
}
