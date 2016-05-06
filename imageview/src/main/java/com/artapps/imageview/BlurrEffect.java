package com.artapps.imageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.renderscript.Allocation;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

public class BlurrEffect {

    public static Bitmap blurrImage(final Context context, final Bitmap image, final float blurrRadius) {

        final int width = Math.round(image.getWidth() * 0.3f);
        final int height = Math.round(image.getHeight() * 0.3f);

        final Bitmap inputBitmap = Bitmap.createScaledBitmap(image, width, height, false);
        final Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap);

        final RenderScript rs = RenderScript.create(context);
        final Allocation tmpIn = Allocation.createFromBitmap(rs, inputBitmap);
        final Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);
        final ScriptIntrinsicBlur theIntrinsic = ScriptIntrinsicBlur.create(rs, tmpIn.getElement());

        theIntrinsic.setRadius(blurrRadius);
        theIntrinsic.setInput(tmpIn);
        theIntrinsic.forEach(tmpOut);
        tmpOut.copyTo(outputBitmap);

        return outputBitmap;
    }
}
