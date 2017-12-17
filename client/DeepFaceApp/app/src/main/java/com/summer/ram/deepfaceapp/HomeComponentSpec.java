package com.summer.ram.deepfaceapp;

import android.content.Context;
import android.graphics.Color;
import android.widget.Toast;

import com.facebook.litho.Column;
import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.widget.Image;
import com.facebook.litho.widget.Text;
import com.facebook.yoga.YogaAlign;
import com.facebook.yoga.YogaEdge;


/**
 * Created by rameshwar on 16/12/17
 * Layout spec for the homepage
 */

@LayoutSpec
public class HomeComponentSpec {
    @OnCreateLayout
    static ComponentLayout onCreateLayout(ComponentContext c, @Prop final Context context, @Prop final ButtonContainerSpec.OnButtonClickListener buttonListener) {
        Component<Text> introText = Text.create(c)
                .textSizeDip(16)
                .textColor(Color.WHITE)
                .text("UPLOAD IMAGE")
                .build();
        Component<ButtonContainer> button = ButtonContainer.create(c)
                                            .listener(buttonListener)
                                            .build();



        return Column.create(c)
                .alignItems(YogaAlign.CENTER)
                .marginPx(YogaEdge.ALL, 20)
                .paddingDip(YogaEdge.ALL, 20)
                .child(introText)
                .child(button)
                .backgroundColor(Color.BLACK)
                .build();

    }
}
