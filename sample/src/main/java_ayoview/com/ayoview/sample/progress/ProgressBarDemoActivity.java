package com.ayoview.sample.progress;

import com.ayoview.sample.progress.av.AVMainActivity;
import com.ayoview.sample.progress.circleprogressable.DemoProgressWheel2;
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
                new Leaf("ProgressWheel2", "", DemoProgressWheel2.class),
        };
        return leaves;
    }
}
