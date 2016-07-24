package org.ayo.app.tmpl.recycler.adapter;

import android.app.Activity;
import android.widget.TextView;

import org.ayo.app.tmpl.recycler.ItemBean;

import genius.android.view.R;

/**
 * 后备，放在template列表的最后，防止有一个bean找不到对应的模板，这里会显示bean信息，让你能找到是哪个bean
 */
public class GuardItemTemplate extends AyoItemTemplate2{

    public GuardItemTemplate(Activity activity) {
        super(activity);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ayo_item_guard;
    }

    @Override
    public boolean isForViewType(ItemBean bean, int position) {
        return true;
    }

    @Override
    public void onBindViewHolder(ItemBean itemBean, int position, AyoViewHolder holder) {
        TextView tv_info = holder.id(R.id.tv_info);
        tv_info.setText(itemBean.getClass().getSimpleName() + "未注册样式模板");
    }

}
