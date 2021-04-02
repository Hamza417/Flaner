package app.simple.flaner.decoration.corners;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import app.simple.flaner.utils.ViewUtils;

public class DynamicCornerLinearLayout extends LinearLayout {

    public DynamicCornerLinearLayout(Context context) {
        super(context);
        init();
    }

    public DynamicCornerLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public DynamicCornerLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attributeSet) {
        LayoutBackground.setBackground(getContext(), this, attributeSet);
        ViewUtils.INSTANCE.addShadow(this);
    }

    private void init() {
        setBackgroundTintList(ColorStateList.valueOf(Color.TRANSPARENT));
        LayoutBackground.setBackground(getContext(), this, null);
        ViewUtils.INSTANCE.addShadow(this);
    }
}
