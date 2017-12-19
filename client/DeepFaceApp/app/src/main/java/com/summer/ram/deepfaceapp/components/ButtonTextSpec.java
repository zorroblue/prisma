package com.summer.ram.deepfaceapp.components;

import android.text.Layout;

import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.widget.Text;
import com.facebook.yoga.YogaAlign;

import static com.facebook.yoga.YogaEdge.ALL;

/**
 * Created by rameshwar on 16/12/17.
 */

@LayoutSpec
public class ButtonTextSpec {

    @OnCreateLayout
    static ComponentLayout onCreateLayout(ComponentContext c, @Prop String text) {
        return Text.create(c)
                .text(text)
                .textSizeDip(18)
                .textColorRes(android.R.color.white)
                .textSizeSp(18)
                .textAlignment(Layout.Alignment.ALIGN_CENTER)
                .withLayout()
                .alignSelf(YogaAlign.STRETCH)
                .paddingDip(ALL, 8)
                .build();
    }
}