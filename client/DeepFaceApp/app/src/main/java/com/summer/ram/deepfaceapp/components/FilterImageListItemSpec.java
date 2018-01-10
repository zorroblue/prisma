package com.summer.ram.deepfaceapp.components;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.facebook.litho.Column;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.widget.Image;
import com.facebook.litho.widget.Text;
import com.facebook.yoga.YogaEdge;

/**
 * Created by rameshwar on 2/1/18.
 */

@LayoutSpec
public class FilterImageListItemSpec {

    @OnCreateLayout
    static ComponentLayout onCreateLayout(ComponentContext c, @Prop Drawable drawable) {
        return Column.create(c)
                .paddingDip(YogaEdge.ALL, 5)
                .heightDip(150)
                .child(Image.create(c)
                            .drawable(drawable)
                            .scaleType(ImageView.ScaleType.FIT_CENTER)
                )
                .child(Text.create(c)
                            .text("Here")
                            )
                .build();
    }


}
