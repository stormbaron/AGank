package com.example.stormbaron.agank.prensenter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.stormbaron.agank.app.APPConfig;
import com.example.stormbaron.agank.model.api.Api;
import com.example.stormbaron.agank.model.api.apiservice.GankApiService;
import com.example.stormbaron.agank.model.entity.GankNewsEtity;
import com.example.stormbaron.agank.model.entity.ResultMode;
import com.example.stormbaron.agank.ui.BaseView;
import com.example.stormbaron.agank.ui.activity.WebViewActivity;
import com.example.stormbaron.agank.ui.adapter.GankListAdapter;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by stormbaron on 17-8-4.
 */

public class GankPrensenter implements BasePrensenter {
    private BaseView baseView;
    private Retrofit retrofit;
    private Call<ResultMode<GankNewsEtity>> call;
    private ResultMode<GankNewsEtity> mResponse;
    private List<GankNewsEtity> mGanks = new LinkedList<>();
    private GankListAdapter mGankListAdapter;
    private GankApiService apiService;

    public GankPrensenter(BaseView baseView) {
        this.baseView = baseView;
        retrofit = new Retrofit.Builder()
                .baseUrl(Api.API_DOMAIN)
                .addConverterFactory(GsonConverterFactory.create())//添加 json 转换器
                .build();
        apiService = retrofit.create(GankApiService.class);
    }


    public void requestData(final Context mContext, RecyclerView recyclerView) {
        if (mGankListAdapter == null) {
            mGankListAdapter = new GankListAdapter(mContext, mGanks, new GankListAdapter.ClickListener() {
                @Override
                public void onClick(int position) {
                    WebViewActivity.actionStart(mContext, mGanks.get(position).getUrl());
                   /* Intent mIntent = new Intent();
                    Uri uri = Uri.parse(mGanks.get(position).getUrl());
                    mIntent.setAction(Intent.ACTION_VIEW);
                    mIntent.setData(uri);
                    mContext.startActivity(mIntent);*/

                }
            });
        }
        recyclerView.setAdapter(mGankListAdapter);
        call = apiService.getList(50, 1);
        Observable.create(new Observable.OnSubscribe<List<GankNewsEtity>>() {
            @Override
            public void call(Subscriber<? super List<GankNewsEtity>> subscriber) {
                try {
                    mResponse = call.execute().body();
                    mGanks.addAll(mResponse.results);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                subscriber.onNext(mGanks);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<List<GankNewsEtity>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<GankNewsEtity> gankNewsEtities) {
                        mGankListAdapter.notifyDataSetChanged();
                    }
                });


    }
}
