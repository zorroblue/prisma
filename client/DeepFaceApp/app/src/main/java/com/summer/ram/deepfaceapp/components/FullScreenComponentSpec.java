package com.summer.ram.deepfaceapp.components;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import com.facebook.litho.Column;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.widget.Image;
import com.facebook.yoga.YogaAlign;
import com.facebook.yoga.YogaEdge;
import com.facebook.yoga.YogaJustify;

/**
 * Created by rameshwar on 11/1/18.
 */

@LayoutSpec
public class FullScreenComponentSpec {

    @OnCreateLayout
    static ComponentLayout onCreateLayout(ComponentContext context, @Prop Bitmap imageBitmap) {
        return Column.create(context)
                .justifyContent(YogaJustify.SPACE_AROUND)
                .heightPercent(100)
                .child(Image.create(context)
                        .drawable(new BitmapDrawable(context.getResources(), imageBitmap))
                        .withLayout()
                        .alignSelf(YogaAlign.CENTER)
                        .build())
                .build();
    }
}
