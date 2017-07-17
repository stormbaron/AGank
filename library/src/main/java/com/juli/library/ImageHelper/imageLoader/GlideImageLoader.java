package com.juli.library.ImageHelper.imageLoader;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by stormbaron on 17-7-17.
 */

public class GlideImageLoader implements ImageLoader {
    @Override
    public void showImage(View view, String url, ImageLoaderOption option) {
        if (view instanceof ImageView) {
            Glide.with(view.getContext()).load(url).into((ImageView) view);
        }
    }

    @Override
    public void showImage(View view, int drawerId, ImageLoaderOption option) {
        if (view instanceof ImageView) {
            Glide.with(view.getContext()).load(drawerId).into((ImageView) view);
        }
    }
}
