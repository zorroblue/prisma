package com.summer.ram.deepfaceapp.components;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.facebook.litho.ClickEvent;
import com.facebook.litho.Column;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.Row;
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
import com.facebook.yoga.YogaEdge;
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
                .widthDip(200)
                .heightDip(200)
                .child(Image.create(c)
                        .drawable(stateFilePath==null ? c.getBaseContext().getResources().getDrawable(R.drawable.ic_add_a_photo_black_24dp) : Drawable.createFromPath(stateFilePath))
                        .scaleType(ImageView.ScaleType.FIT_XY)
                        .build()
                )
                .borderColor(Color.BLACK)
                .borderWidthDip(YogaEdge.ALL, 5)
                .clickHandler(ImageContainer.onClick(c))
                .build();


    }

    @OnEvent(ClickEvent.class)
    static void onClick(ComponentContext c, @FromEvent View view,
                        @Prop OnImageClickListener listener) {
        listener.onImageClick();
    }


    public interface OnImageClickListener {
        void onImageClick();
    }
}