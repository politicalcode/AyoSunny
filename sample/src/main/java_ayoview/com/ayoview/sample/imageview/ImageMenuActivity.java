package com.ayoview.sample.imageview;

import com.cowthan.sample.BaseDemoMenuActivity;
import com.cowthan.sample.menu.Leaf;

/**
 * Created by Administrator on 2016/7/4.
 */
public class ImageMenuActivity extends BaseDemoMenuActivity{
    @Override
    protected Leaf[] getMenus() {
        Leaf[] leaves = {
                new Leaf("ImageView初学实例", "", V_ImageView.class),
                new Leaf("ScaleType演示", "", V_ImageView_ScaleType.class),
        };
        return leaves;
    }
}
