package com.juli.library.ImageHelper.imageLoader;

import android.view.View;

import com.juli.library.ImageHelper.ImageShowActivity;

/**
 * Created by stormbaron on 17-7-17.
 */

public class ImageLoaderManager {

    private static ImageLoaderManager instance = new ImageLoaderManager();
    private ImageLoaderHelper mImageLoader;
    private ImageLoaderOption mImageLoaderOption = new ImageLoaderOption();

    private int drawableId = -1;
    private String url = null;
    private View view = null;


    public ImageLoaderManager() {
        mImageLoader = new GlideImageLoader();
    }
    /**
     * 返回实例
     *
     * @return
     */
    public static ImageLoaderManager getInstance() {
        return instance;
    }

    /**
     * 加载资源路径
     * @param url
     * @return
     */
    public ImageLoaderManager load(String url){
        this.url=url;
        return this;
    }
    public ImageLoaderManager load(int drawableId){
        this.drawableId=drawableId;
        return this;
    }

    /**
     * 进行加载操作
     * @param view
     */
    public void into(View view){
        if(url!=null){
            setMatrix(view, mImageLoaderOption);
            mImageLoader.showImage(view, url, mImageLoaderOption);
            return;
        }
        if(drawableId!=-1){
            setMatrix(view, mImageLoaderOption);
            mImageLoader.showImage(view, drawableId, mImageLoaderOption);
            return;
        }
    }


    public ImageLoaderManager setImageSize(ImageLoaderOption.ImageSize imageSize) {
        mImageLoaderOption.setImageSize(imageSize);
        return this;
    }

    public ImageLoaderManager setIsCrossFade(boolean isCrossFade) {
        mImageLoaderOption.setCrossFade(isCrossFade);
        return this;
    }

    public ImageLoaderManager setIsSkipMemoryCache(boolean isSkipMemoryCache) {
        mImageLoaderOption.setSkipMemoryCache(isSkipMemoryCache);
        return this;
    }

    public ImageLoaderManager setPlaceHolder(int placeHolder) {
        mImageLoaderOption.setPlaceHolder(placeHolder);
        return this;
    }

    public ImageLoaderManager setErrorDrawableId(int drawableId) {
        mImageLoaderOption.setErrorDrawableId(drawableId);
        return this;
    }

    public ImageLoaderManager setBigUrl(String url) {
        mImageLoaderOption.setBigUrl(url);
        return this;
    }


    /**
     * 更具参数实例子判断是否可以点击查看大图
     *
     * @param view
     * @param option
     */
    private void setMatrix(final View view, final ImageLoaderOption option) {
        if (option == null) {
            return;
        }
        if (option.getBigUrl() == null) {
            return;
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageShowActivity.actionStart(view.getContext(), option.getBigUrl());
            }
        });
    }
}
