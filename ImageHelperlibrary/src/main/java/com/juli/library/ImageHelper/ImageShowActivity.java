package com.juli.library.ImageHelper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.bumptech.glide.Glide;
import com.juli.library.ImageHelper.imageLoader.ImageLoaderManager;
import com.juli.library.ImageHelper.zoomImage.MatrixImageView;
import com.juli.library.R;

public class ImageShowActivity extends Activity {
    MatrixImageView mImageView;
    String Url = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_image_show);
        mImageView = (MatrixImageView) findViewById(R.id.is_imageview_img);
        Url = getIntent().getStringExtra("Url");
        initView();
    }

    public void initView() {
        if (Url != null) {
            ImageLoaderManager.getInstance().load(Url)
                    .setPlaceHolder(R.drawable.upload)
                    .into(mImageView);
        }
    }

    public static void actionStart(Context mContext, String Url) {
        Intent mIntent = new Intent();
        mIntent.setClass(mContext, ImageShowActivity.class);
        mIntent.putExtra("Url", Url);
        mContext.startActivity(mIntent);
    }
}
