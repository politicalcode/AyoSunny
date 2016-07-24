package com.ayoview.sample.tmpl_listview;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cowthan.sample.R;

import org.ayo.app.tmpl.recycler.ItemBean;
import org.ayo.app.tmpl.recycler.adapter.AyoItemTemplate2;
import org.ayo.app.tmpl.recycler.adapter.AyoViewHolder;
import org.ayo.imageloader.VanGogh;
import org.ayo.notify.Toaster;

/**
 * Created by Administrator on 2016/7/24.
 */
public class TmplDelegate extends AyoItemTemplate2 {

    public TmplDelegate(Activity activity) {
        super(activity);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_tmpl_item_template;
    }

    @Override
    public boolean isForViewType(ItemBean itemBean, int position) {
        return itemBean instanceof TmplBean;
    }

    @Override
    public void onBindViewHolder(ItemBean itemBean, final int position, AyoViewHolder holder) {

        TmplBean bean = (TmplBean) itemBean;

        TextView tv_title = (TextView) holder.findViewById(R.id.tv_title);
        ImageView iv_photo = (ImageView) holder.findViewById(R.id.iv_photo);


        tv_title.setText(bean.title);
        VanGogh.paper(iv_photo).paintSmallImage(bean.cover_url,null, null);

        holder.root().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toaster.toastShort("click-" + position);
            }
        });
    }

}
