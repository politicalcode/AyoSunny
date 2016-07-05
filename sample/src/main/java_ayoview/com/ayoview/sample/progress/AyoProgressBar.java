package com.ayoview.sample.progress;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/**
 * Created by Administrator on 2016/7/5.
 */
public class AyoProgressBar extends ProgressBar {
    public AyoProgressBar(Context context) {
        super(context);
        init();
    }

    public AyoProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AyoProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AyoProgressBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){
        this.setIndeterminateDrawable(new ProgressAnimationDrawable());
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }
}
