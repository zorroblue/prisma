package com.summer.ram.deepfaceapp.components;

import android.content.Context;
import android.graphics.Color;

import com.facebook.litho.Column;
import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
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
    static ComponentLayout onCreateLayout(ComponentContext c, @Prop final Context context, @Prop final ButtonContainerSpec.OnButtonClickListener buttonListener, @Prop final ImageContainerSpec.OnImageClickListener imageListener, @Prop String filePath) {
        Component<Text> introText = Text.create(c)
                .textSizeDip(30)
                .textColor(Color.BLUE)
                .text("DeepArt")
                .build();
        Component<ButtonContainer> button = ButtonContainer.create(c)
                                            .listener(buttonListener)
                                            .build();

        Component<ImageContainer> image = ImageContainer.create(c)
                                            .stateFilePath(filePath)
                                            .listener(imageListener)
                                            .build();


        return Column.create(c)
                .alignItems(YogaAlign.CENTER)
                .marginPx(YogaEdge.ALL, 100)
                .paddingDip(YogaEdge.ALL, 20)
                .child(introText)
                .child(image)
                .child(button)
                .backgroundColor(Color.WHITE)
                .build();

    }
}
