package com.ayoview.anim;

import com.ayoview.anim.tween.TweenActivity;
import com.ayoview.anim.tween.TweenActivity2;
import com.cowthan.sample.BaseDemoMenuActivity;
import com.cowthan.sample.menu.Leaf;

/**
 */
public class DemoAnimMenu extends BaseDemoMenuActivity{
    @Override
    protected Leaf[] getMenus() {
        return new Leaf[]{
                new Leaf("tween：演示", "", TweenActivity.class),
                new Leaf("tween：生成器", "", TweenActivity2.class),
        };
    }

    @Override
    public void onClicked(String btnText) {
    }
}
