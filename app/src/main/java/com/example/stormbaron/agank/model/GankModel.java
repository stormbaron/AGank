package com.example.stormbaron.agank.model;

import android.util.Log;

import com.example.stormbaron.agank.model.api.Api;
import com.example.stormbaron.agank.model.api.apiservice.GankApiService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by stormbaron on 17-6-26.
 */

public class GankModel {

    public static void getDataList(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.API_DOMAIN)
               //.addConverterFactory(GsonConverterFactory.create())//添加 json 转换器
                .build();
        GankApiService apiService = retrofit.create(GankApiService.class);//这里采用的是Java的动态代理模式
        Call<ResponseBody> call=apiService.getList(10,1);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("GankModel",response.toString());
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });


    }
}
