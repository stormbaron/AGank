package com.juli.library.ImageHelper.imageLoader;

import android.view.View;

/**
 * Created by stormbaron on 17-7-17.
 * 图片加载接口
 */

public interface ImageLoaderHelper {

    void showImage(View view, String url, ImageLoaderOption option);

    void showImage(View view, int drawerId, ImageLoaderOption option);
}
