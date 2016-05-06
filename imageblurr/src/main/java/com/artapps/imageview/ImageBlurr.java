package com.artapps.imageview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.artapps.imageblurr.R;


public class ImageBlurr extends ImageView{

    private static final String LOG_ERROR = "log_error";

    public ImageBlurr(Context context) {
        super(context);

    }

    public ImageBlurr(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBlurrBackground(context, attrs);
    }

    public ImageBlurr(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBlurrBackground(context, attrs);
    }

    public ImageBlurr(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setBlurrBackground(context, attrs);
    }

    @SuppressWarnings("deprecation")
    private void setBlurrBackground(final Context context, final AttributeSet attrs){
        final Bitmap bitmap = ((BitmapDrawable) getBackground()).getBitmap();
        final float bRadius = getBlurrRadius(context, attrs);
        setBackground(new BitmapDrawable(BlurrEffect.blurrImage(context, bitmap, bRadius)));
    }

    private float getBlurrRadius(final Context context, final AttributeSet attrs){
        try {
            @SuppressLint("Recycle")
            final TypedArray arrayOfAttrs = context.obtainStyledAttributes(attrs, R.styleable.ImageBlurr);
            return arrayOfAttrs.getFloat(R.styleable.ImageBlurr_blurrRadius, 0);
        }catch (Exception e){
            Log.d(LOG_ERROR, "Exception: " + e.toString());
        }

        return 0;
    }
}
