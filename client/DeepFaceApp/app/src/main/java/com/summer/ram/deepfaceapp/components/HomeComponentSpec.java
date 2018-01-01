package com.summer.ram.deepfaceapp.components;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.OrientationHelper;

import com.facebook.litho.Column;
import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentInfo;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.widget.LinearLayoutInfo;
import com.facebook.litho.widget.Recycler;
import com.facebook.litho.widget.RecyclerBinder;
import com.facebook.litho.widget.Text;
import com.facebook.yoga.YogaAlign;
import com.facebook.yoga.YogaEdge;
import com.summer.ram.deepfaceapp.R;

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
                .text("Deep Art")
                .typeface(Typeface.createFromAsset(context.getAssets(), "Pacifico.ttf"))
                .shouldIncludeFontPadding(true)
                .build();
        Component<ButtonContainer> button = ButtonContainer.create(c)
                .listener(buttonListener)
                .build();

        Component<ImageContainer> image = ImageContainer.create(c)
                .stateFilePath(filePath)
                .listener(imageListener)
                .build();

        Component<Recycler> recyclerComponent = getRecyclerComponent(c);

        return Column.create(c)
                .alignItems(YogaAlign.CENTER)
                .marginPx(YogaEdge.ALL, 100)
                .paddingDip(YogaEdge.ALL, 60)
                .child(introText)
                .child(image)
                .child(button)
                .child(recyclerComponent)
                .backgroundColor(Color.WHITE)
                .build();

    }

    private static Component<Recycler> getRecyclerComponent(ComponentContext c) {
        RecyclerBinder binder = new RecyclerBinder(c, new LinearLayoutInfo(c.getBaseContext(), OrientationHelper.HORIZONTAL, false));
        int x[] = {R.drawable.the_scream, R.drawable.rain_princess};
        for (int i = 0; i < 2; i++) {
            binder.insertItemAt(i, ComponentInfo.create().component(
                    FilterImageListItem.create(c)
                            .drawable(c.getBaseContext().getResources().getDrawable(x[i]))
                            .build()
            ).build());
        }
        return Recycler.create(c)
                .binder(binder)
                .build();
    }
}
