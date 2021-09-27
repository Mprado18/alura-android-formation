package com.example.aluratravel.ui.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

public class ResourceUtil {

    public static final String DRAWABLE = "drawable";

    @SuppressLint("UseCompatLoadingForDrawables")
    public static Drawable getDrawable(Context context, String drawable) {
        Resources resources = context.getResources();
        int idDrawable = resources.getIdentifier(drawable, DRAWABLE, context.getPackageName());

        return resources.getDrawable(idDrawable, null);
    }

}
