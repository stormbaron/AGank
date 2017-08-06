package com.juli.library.ImageHelper;


import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

public class ImageUtils {

    // 给图片添加水印  
    @SuppressLint("SimpleDateFormat")
    public static Bitmap createBitmap(Bitmap src, String str) {
        int w = src.getWidth();
        int h = src.getHeight();
        String mstrTitle = str;
        Bitmap bmpTemp = Bitmap.createBitmap(w, h, Config.ARGB_8888);
        Canvas canvas = new Canvas(bmpTemp);
        Paint paint = new Paint();
        String familyName = "宋体";
        Typeface font = Typeface.create(familyName, Typeface.BOLD);
        paint.setColor(Color.RED);
        paint.setTypeface(font);
        paint.setTextSize(h / 17);
        
        canvas.drawBitmap(src, 0, 0, paint);
        canvas.drawText(mstrTitle, w / 4, h - (h / 16), paint);

        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();
        return bmpTemp;
    }

}
