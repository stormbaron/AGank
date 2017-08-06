package com.example.stormbaron.agank.ui.fragments;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.stormbaron.agank.R;
import com.example.stormbaron.agank.prensenter.BasePrensenter;
import com.example.stormbaron.agank.prensenter.GankPrensenter;
import com.example.stormbaron.agank.ui.BaseView;

/**
 * Created by stormbaron on 17-7-16.
 */

public class GankFragment extends BaseFragment implements BaseView {
    private GankPrensenter gankPrensenter;
    private RecyclerView mRecyclerView;

    public GankFragment() {
        gankPrensenter = new GankPrensenter(this);
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gank, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.id_gank_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);

            }
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(4, 4, 4, 4);//设置itemView中内容相对边框左，上，右，下距离
            }
        });
        gankPrensenter.requestData(getActivity(), mRecyclerView);
    }




    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    public void onDestroyView() {
        super.onDestroyView();
    }

    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void showProgress() {
        Toast.makeText(getContext(), "开始下载", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideProgress() {
        Toast.makeText(getContext(), "结束下载", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setData() {

    }
}
