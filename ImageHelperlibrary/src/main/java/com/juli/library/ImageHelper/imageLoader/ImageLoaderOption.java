package com.juli.library.ImageHelper.imageLoader;


/**
 * Created by stormbaron on 17-7-17.
 */

public class ImageLoaderOption {
    private String bigUrl = null;//查看大图的地址
    private int errorDrawableId = -1;//加载失败时显示的图片
    private boolean isCrossFade = false;//是否平平滑家变地加载图片
    private boolean isSkipMemoryCache = false;//是否跳过内存缓存
    private ImageSize mImageSize = null;
    private int placeHolder = -1;//没有加载成功时显示的图片

    public int getPlaceHolder() {
        return placeHolder;
    }

    public void setPlaceHolder(int placeHolder) {
        this.placeHolder = placeHolder;
    }

    public int getErrorDrawableId() {
        return errorDrawableId;
    }

    public void setErrorDrawableId(int errorDrawableId) {
        this.errorDrawableId = errorDrawableId;
    }

    public boolean isCrossFade() {
        return isCrossFade;
    }

    public void setCrossFade(boolean crossFade) {
        isCrossFade = crossFade;
    }

    public boolean isSkipMemoryCache() {
        return isSkipMemoryCache;
    }

    public void setSkipMemoryCache(boolean skipMemoryCache) {
        isSkipMemoryCache = skipMemoryCache;
    }

    public ImageSize getmImageSize() {
        return mImageSize;
    }


    public String getBigUrl() {
        return bigUrl;
    }

    public void setBigUrl(String bigUrl) {
        this.bigUrl = bigUrl;
    }


    public ImageLoaderOption() {
    }


    public ImageLoaderOption(int placeHolder, int errorDrawableId, boolean isCrossFade, boolean isSkipMemoryCache) {
        this.placeHolder = placeHolder;
        this.errorDrawableId = errorDrawableId;
        this.isCrossFade = isCrossFade;
        this.isSkipMemoryCache = isSkipMemoryCache;
    }

    public ImageLoaderOption(int placeHolder, int errorDrawableId, boolean isCrossFade, boolean isSkipMemoryCache, String bigUrl) {
        this.placeHolder = placeHolder;
        this.errorDrawableId = errorDrawableId;
        this.isCrossFade = isCrossFade;
        this.isSkipMemoryCache = isSkipMemoryCache;
        this.bigUrl = bigUrl;
    }


    class ImageSize {
        float width = -1;
        float height = -1;
    }

    public void setImageSize(ImageSize imageSize) {
        mImageSize = imageSize;
    }
}
