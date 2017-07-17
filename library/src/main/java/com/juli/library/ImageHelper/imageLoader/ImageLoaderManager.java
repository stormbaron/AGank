package com.juli.library.ImageHelper.imageLoader;

import android.view.View;

/**
 * Created by stormbaron on 17-7-17.
 */

public class ImageLoaderManager implements ImageLoader {
    private static ImageLoaderManager instance = new ImageLoaderManager();
    private ImageLoader mImageLoader;

    public static ImageLoaderManager getInstance() {
        return instance;
    }

    public ImageLoaderManager() {
        mImageLoader = new GlideImageLoader();
    }


    @Override
    public void showImage(View view, String url, ImageLoaderOption option) {
        mImageLoader.showImage(view, url, option);
    }

    @Override
    public void showImage(View view, int drawerId, ImageLoaderOption option) {
        mImageLoader.showImage(view, drawerId, option);
    }

    public void setImageLoader(ImageLoader imageLoader) {
        mImageLoader = imageLoader;
    }
}
