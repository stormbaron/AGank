package com.example.stormbaron.agank.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stormbaron.agank.R;
import com.example.stormbaron.agank.model.entity.GankNewsEtity;
import com.example.stormbaron.agank.model.entity.ImageEntity;
import com.juli.library.ImageHelper.ImageShowActivity;
import com.juli.library.ImageHelper.imageLoader.ImageLoaderManager;

import java.util.List;

/**
 * Created by stormbaron on 17-7-12.
 */

public class GankListAdapter extends RecyclerView.Adapter<GankListAdapter.ViewHolder> {

    private List<GankNewsEtity> mDatas;
    private Context mContext;
    private ClickListener clickListener;

    public GankListAdapter(Context mContext, List<GankNewsEtity> mDatas, ClickListener clickListener) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.recyclerview_item_gank, null), clickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.position=position;
        holder.desc.setText(mDatas.get(position).getDesc());
        holder.date.setText(mDatas.get(position).getCreatedAt());
       if(mDatas.get(position).getImages()!=null){
           ImageLoaderManager.getInstance().load(mDatas.get(position).getImages().get(0).toString())
                   .setPlaceHolder(R.mipmap.upload)
                   .setIsCrossFade(true)
                   .into(holder.mImg);
       }

    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImg;
        private TextView desc;
        private TextView date;
        private int position;

        public ViewHolder(View itemView, final ClickListener clickListener) {
            super(itemView);
            mImg = (ImageView) itemView.findViewById(R.id.id_gank_list_img);
            desc = (TextView) itemView.findViewById(R.id.id_gank_item_title);
            date = (TextView) itemView.findViewById(R.id.id_gank_item_date);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListener != null) {

                        clickListener.onClick(position);
                    }
                }
            });
        }
    }

    public interface ClickListener {
        void onClick(int position);
    }
}
