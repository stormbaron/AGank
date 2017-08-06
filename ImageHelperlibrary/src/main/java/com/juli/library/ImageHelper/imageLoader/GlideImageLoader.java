package com.juli.library.ImageHelper.imageLoader;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;

/**
 * Created by stormbaron on 17-7-17.
 */

public class GlideImageLoader implements ImageLoaderHelper {
    @Override
    public void showImage(View view, String url, ImageLoaderOption option) {
        if (view instanceof ImageView) {

            DrawableTypeRequest mDtr = Glide.with(view.getContext()).load(url);
            setOptions(mDtr, option).into((ImageView) view);

        }
    }

    @Override
    public void showImage(View view, int drawerId, ImageLoaderOption option) {
        if (view instanceof ImageView) {
            Glide.with(view.getContext()).load(drawerId).into((ImageView) view);
        }
    }

    private DrawableTypeRequest setOptions(DrawableTypeRequest dtr, ImageLoaderOption option) {
        if (option == null) {
            return dtr;
        }
        if (option.getPlaceHolder() != -1) {
            dtr.placeholder(option.getPlaceHolder());
        }
        if (option.getErrorDrawableId() != -1) {
            dtr.error(option.getErrorDrawableId());
        }
        if (option.isSkipMemoryCache()) {
            dtr.skipMemoryCache(true);
        }
        if (option.isCrossFade()) {
            dtr.crossFade();
        }
        return dtr;

    }
}
