package com.summer.ram.deepfaceapp.components;

import android.graphics.Color;
import android.view.View;

import com.facebook.litho.ClickEvent;
import com.facebook.litho.Column;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.FromEvent;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.OnEvent;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.widget.Card;
import com.facebook.yoga.YogaAlign;
import com.facebook.yoga.YogaEdge;
import com.facebook.yoga.YogaJustify;

/**
 * Created by rameshwar on 16/12/17.
 */

@LayoutSpec
public class ButtonContainerSpec {

    @OnCreateLayout
    static ComponentLayout onCreateLayout(ComponentContext c) {
        return Column.create(c)
                .alignItems(YogaAlign.CENTER)
                .justifyContent(YogaJustify.CENTER)
                .child(Card.create(c)
                        .cardBackgroundColor(Color.RED)
                        .cornerRadiusDip(2)
                        .elevationDip(2)
                        .content(ButtonText.create(c).text("Upload"))
                        .withLayout()
                        .flexShrink(1)
                        .alignSelf(YogaAlign.CENTER))
                .paddingDip(YogaEdge.TOP, 40)
                .clickHandler(ButtonContainer.onClick(c))
                .build();
    }

    @OnEvent(ClickEvent.class)
    static void onClick(ComponentContext c, @FromEvent View view,
                        @Prop OnButtonClickListener listener) {
        listener.onButtonClick();
    }

    public interface OnButtonClickListener {
        void onButtonClick();
    }

}
