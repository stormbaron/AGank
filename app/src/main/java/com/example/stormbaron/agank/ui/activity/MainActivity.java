package com.example.stormbaron.agank.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.example.stormbaron.agank.R;
import com.example.stormbaron.agank.app.PermissionUtil;
import com.example.stormbaron.agank.model.api.Api;
import com.example.stormbaron.agank.model.api.apiservice.GankApiService;
import com.example.stormbaron.agank.model.entity.ImageEntity;
import com.example.stormbaron.agank.model.entity.ResultMode;
import com.example.stormbaron.agank.ui.adapter.GridAdapterRecyclerView;
import com.zhihu.matisse.Matisse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.support.v7.widget.StaggeredGridLayoutManager.VERTICAL;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    Retrofit retrofit;
    Call<ResultMode<ImageEntity>> call;
    private Context mContext = MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PermissionUtil.checkPermission(MainActivity.this);
        retrofit = new Retrofit.Builder()
                .baseUrl(Api.API_Gank_Image_base)
                .addConverterFactory(GsonConverterFactory.create())//添加 json 转换器
                .build();
        GankApiService apiService = retrofit.create(GankApiService.class);
        call = apiService.getImageList(30, 1);
        initView();
        initData();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.id_main_recycle_view);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, VERTICAL));
    }

    private int REQUEST_CODE_CHOOSE = 1100;
    private ResultMode<ImageEntity> result;

    public void initData() {
        Observable.create(new Observable.OnSubscribe<ResultMode<ImageEntity>>() {
            @Override
            public void call(Subscriber s) {
                Log.e("TAG", call.request().url().toString());
                try {
                    result = call.execute().body();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                s.onNext(result);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<ResultMode<ImageEntity>>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(ResultMode<ImageEntity> s) {
                        mRecyclerView.setAdapter(new GridAdapterRecyclerView(mContext, s.results));
                    }
                });
    }


    List<Uri> mSelected;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            mSelected = Matisse.obtainResult(data);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            Toast.makeText(this, "你点击了退回", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
