package com.juli.library.ImageHelper.imageLoader;

import android.view.ViewPropertyAnimator;

/**
 * Created by stormbaron on 17-7-17.
 */

public class ImageLoaderOption {
    private int placeHolder = -1;//没有加载成功时显示的图片
    private int errorDrawableId=-1;//加载失败时显示的图片
    private boolean isCrossFade=false;//是否平平滑家变地加载图片
    private boolean isSkipMemoryCache=false;//是否跳过内存缓存


}
