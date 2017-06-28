package com.example.stormbaron.agank.model.api.apiservice;


import com.example.stormbaron.agank.model.entity.BaseEntity;
import com.example.stormbaron.agank.model.entity.GankNewsEtity;
import com.example.stormbaron.agank.model.entity.Result;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by stormbaron on 17-6-26.
 */

public interface GankApiService {
    @GET("data/Android/{page}/{number}")
    Call<Result<GankNewsEtity>> getList(@Path("page")int page, @Path("number") int number);
}
