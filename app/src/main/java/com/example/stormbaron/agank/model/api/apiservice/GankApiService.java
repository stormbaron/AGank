package com.example.stormbaron.agank.model.api.apiservice;


import com.example.stormbaron.agank.model.entity.GankNewsEtity;
import com.example.stormbaron.agank.model.entity.ImageEntity;
import com.example.stormbaron.agank.model.entity.ResultMode;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by stormbaron on 17-6-26.
 */

public interface GankApiService {
    @GET("data/Android/{number}/{page}")
    Call<ResultMode<GankNewsEtity>> getList(@Path("number") int page, @Path("page") int number);


    @GET("福利/count/{page}/page/{number}")
    Call<ResultMode<ImageEntity>> getImageList(@Path("page") int page, @Path("number") int number);
}
