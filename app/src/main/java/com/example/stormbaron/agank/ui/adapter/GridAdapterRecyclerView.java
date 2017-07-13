package com.example.stormbaron.agank.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.example.stormbaron.agank.R;
import com.example.stormbaron.agank.model.entity.ImageEntity;

import java.util.List;

/**
 * Created by stormbaron on 17-7-12.
 */

public class GridAdapterRecyclerView extends RecyclerView.Adapter<GridAdapterRecyclerView.ViewHolder> {

    private List<ImageEntity> mDatas;
    Context mContext;

    public GridAdapterRecyclerView(Context mContext, List<ImageEntity> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.recyclerview_item_main_grid, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(mContext).load(mDatas.get(position).getUrl()).placeholder(R.mipmap.ic_launcher).error(android.R.drawable.ic_delete).into(holder.mImg);
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImg;

        public ViewHolder(View itemView) {
            super(itemView);
            mImg = (ImageView) itemView.findViewById(R.id.id_main_recycle_item_img);
        }
    }
}
