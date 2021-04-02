package app.simple.flaner.decoration.ripple;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class DynamicRippleRelativeLayout extends RelativeLayout {
    
    public DynamicRippleRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(Color.TRANSPARENT);
        setBackground(Utils.getRippleDrawable(getContext(), getBackground()));
    }
    
    public DynamicRippleRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackgroundColor(Color.TRANSPARENT);
        setBackground(Utils.getRippleDrawable(getContext(), getBackground()));
    }
}

