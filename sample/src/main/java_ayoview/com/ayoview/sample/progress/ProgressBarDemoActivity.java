package com.ayoview.sample.progress;

import com.ayoview.sample.progress.av.AVMainActivity;
import com.cowthan.sample.BaseDemoMenuActivity;
import com.cowthan.sample.menu.Leaf;

/**
 * Created by Administrator on 2016/7/5.
 */
public class ProgressBarDemoActivity extends BaseDemoMenuActivity {
    @Override
    protected Leaf[] getMenus() {
        Leaf[] leaves = {
                new Leaf("Progress原生", "", V_ProgressBar.class),
                new Leaf("AVLoadingIndicatorView", "", AVMainActivity.class),
        };
        return leaves;
    }
}
