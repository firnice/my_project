package com.airbnb.lottie;

import android.graphics.Bitmap;
import android.graphics.Paint;

public class LottieDraw {

    private final Paint paint = new LPaint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);


    public static void main(String[] args) {
        Bitmap bitmap = getBitmap();
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
//        float density = Utils.dpScale();
        float density = -1;

        paint.setAlpha(parentAlpha);
        if (colorFilterAnimation != null) {
            paint.setColorFilter(colorFilterAnimation.getValue());
        }
        canvas.save();
        canvas.concat(parentMatrix);
        src.set(0, 0, bitmap.getWidth(), bitmap.getHeight());
        dst.set(0, 0, (int) (bitmap.getWidth() * density), (int) (bitmap.getHeight() * density));
        canvas.drawBitmap(bitmap, src, dst , paint);
        canvas.restore();
    }


    public static Bitmap getBitmap(){
        return null;
    }
}
