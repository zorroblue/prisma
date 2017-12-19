package com.summer.ram.deepfaceapp.components;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

import com.facebook.litho.ClickEvent;
import com.facebook.litho.Column;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.StateValue;
import com.facebook.litho.annotations.FromEvent;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateInitialState;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.OnEvent;
import com.facebook.litho.annotations.OnUpdateState;
import com.facebook.litho.annotations.Param;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.State;
import com.facebook.litho.widget.Card;
import com.facebook.litho.widget.Image;
import com.facebook.yoga.YogaAlign;
import com.facebook.yoga.YogaJustify;
import com.summer.ram.deepfaceapp.R;

/**
 * Created by rameshwar on 17/12/17.
 */
@LayoutSpec
public class ImageContainerSpec {


    @OnCreateLayout
    static ComponentLayout onCreateLayout(ComponentContext c, @Prop String stateFilePath) {
        Log.d("IMAGE GETS", stateFilePath == null? "NULL" : stateFilePath);
        return Column.create(c)
                .alignItems(YogaAlign.CENTER)
                .justifyContent(YogaJustify.CENTER)
                .child(Image.create(c)
                        .drawable(stateFilePath==null ? c.getBaseContext().getResources().getDrawable(R.drawable.image_blank) : Drawable.createFromPath(stateFilePath))
                        .build()
                )
                .build();
    }
}