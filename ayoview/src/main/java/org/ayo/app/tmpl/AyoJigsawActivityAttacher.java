package org.ayo.app.tmpl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import org.ayo.app.common.AyoSwipeBackActivityAttacher;

import genius.android.view.R;


/**
 *  一个Activity装载一个Fragment
 *  Fragment管界面
 *
 */
public abstract class AyoJigsawActivityAttacher extends AyoSwipeBackActivityAttacher {

    protected abstract Fragment getFragment();
    protected abstract View getTopView();
    protected abstract View getBottomView();
    protected abstract View getCoverView();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ayo_tmpl_ac_fragment_container);

        View topView = getTopView();
        if(topView != null) {
            ViewGroup top = (ViewGroup) findViewById(R.id.top);
            top.addView(topView);
        }

        View bottomView = getBottomView();
        if(bottomView != null){
            ViewGroup bottom = (ViewGroup) findViewById(R.id.bottom);
            bottom.addView(bottomView);
        }

        View coverView = getCoverView();
        if(coverView != null){
            ViewGroup root = (ViewGroup) findViewById(R.id.root);
            root.addView(coverView);
        }

        FrameLayout fl_root = (FrameLayout) findViewById(R.id.fl_root);
        getSupportFragmentManager().beginTransaction().replace(fl_root.getId(), getFragment()).commit();

    }
}
