package org.ayo.app.tmpl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import org.ayo.app.common.AyoSwipeBackActivity;

import genius.android.view.R;


/**
 *  一个Activity装载一个Fragment
 *  Fragment管界面
 *
 */
public abstract class AyoJigsawActivity extends AyoSwipeBackActivity {

    protected abstract Fragment getFragment();
    protected abstract View getTopView();
    protected abstract View getBottomView();
    protected abstract void onCreateViewFinished(Bundle savedInstanceState);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ayo_tmpl_ac_fragment_container);

        ViewGroup top = (ViewGroup) findViewById(R.id.top);
        top.addView(getTopView());

        ViewGroup bottom = (ViewGroup) findViewById(R.id.bottom);
        bottom.addView(getBottomView());

        FrameLayout fl_root = (FrameLayout) findViewById(R.id.fl_root);
        getSupportFragmentManager().beginTransaction().replace(fl_root.getId(), getFragment()).commit();

        onCreateViewFinished(savedInstanceState);
    }
}
