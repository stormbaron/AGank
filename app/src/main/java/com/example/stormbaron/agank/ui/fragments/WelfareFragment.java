package com.example.stormbaron.agank.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.stormbaron.agank.R;
import com.example.stormbaron.agank.model.api.Api;
import com.example.stormbaron.agank.model.api.apiservice.GankApiService;
import com.example.stormbaron.agank.model.entity.ImageEntity;
import com.example.stormbaron.agank.model.entity.ResultMode;
import com.example.stormbaron.agank.ui.activity.MainActivity;
import com.example.stormbaron.agank.ui.adapter.GridAdapterRecyclerView;

import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.support.v7.widget.StaggeredGridLayoutManager.VERTICAL;

public class WelfareFragment extends BaseFragment {
    private RecyclerView mRecyclerView;
    private Retrofit retrofit;
    private Call<ResultMode<ImageEntity>> call;
    private Context mContext = null;

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    List<ImageEntity> mImageEntitys=new LinkedList<>();
    public WelfareFragment() {
        // Required empty public constructor
    }

    public static WelfareFragment newInstance(String param1, String param2) {
        WelfareFragment fragment = new WelfareFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        mContext = getActivity();
        initConfig();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welfare, container, false);
        initView(view);
        return view;
    }


    private void initConfig() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Api.API_Gank_Image_base)
                .addConverterFactory(GsonConverterFactory.create())//添加 json 转换器
                .build();

        GankApiService apiService = retrofit.create(GankApiService.class);
        call = apiService.getImageList(30, 1);
    }

    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.id_main_recycle_view);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, VERTICAL));
        mGridAdapterRecyclerView= new GridAdapterRecyclerView(getActivity(), mImageEntitys);
        mRecyclerView.setAdapter(mGridAdapterRecyclerView);
        initData();
    }
    GridAdapterRecyclerView mGridAdapterRecyclerView;
    private int REQUEST_CODE_CHOOSE = 1100;
    private ResultMode<ImageEntity> result;

    public void initData() {
        Log.e("TAG", call.request().url().toString());
        Observable.create(new Observable.OnSubscribe<ResultMode<ImageEntity>>() {
            @Override
            public void call(Subscriber s) {
                Log.e("TAG", call.request().url().toString());
                try {
                    result = call.execute().body();
                } catch (Exception e) {
                    Log.e("TAG",e.getMessage()+e.toString());
                    e.printStackTrace();
                }
                Log.e("TAG",result.toString()+result.count);
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
                        Log.e("TAG",result.toString()+result.count);
                        mImageEntitys.clear();
                        mImageEntitys.addAll(s.results);
                        mGridAdapterRecyclerView.notifyDataSetChanged();
                    }
                });
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            // mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
