package org.ayo.app.tmpl.recycler.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import org.ayo.app.adapter.AdapterDelegatesManager;
import org.ayo.app.tmpl.recycler.ItemBean;

import java.util.ArrayList;
import java.util.List;


/**
 * 这是一个尝试，尝试写一个真正通用的adapter，连继承都不用，唯一的区别就是添加不同的AyoItemTemplate
 *
 * AyoSoloAdapter和ItemTemplate2配合使用，这两个才是暴露给用户的
 */
public class AyoSoloAdapter extends AyoRecyclerAdapter<ItemBean> {

    private AdapterDelegatesManager<List<ItemBean>> delegatesManager;

    public AyoSoloAdapter(Activity activity, List<AyoItemTemplate2> templates) {

        super(activity, null);

        // Delegates
        delegatesManager = new AdapterDelegatesManager<>();
        if(templates != null){
            for(AyoItemTemplate2 template: templates){
                template.bindAdapter(this);
                delegatesManager.addDelegate(template);
            }
        }
        delegatesManager.addDelegate(new GuardItemTemplate(activity));
    }

//    public void notifyDataSetChanged2(List<? extends ItemBean> list){
//        if(list == null || list.size() == 0){
//            super.notifyDataSetChanged(null);
//        }else{
//            super.notifyDataSetChanged(upgrade(list));
//        }
//
//    }

    @Override
    public int getItemViewType(int position) {
        return delegatesManager.getItemViewType(mList, position);
    }

    @Override
    public AyoViewHolder newHolder(ViewGroup parent, int viewType, View contentView) {
        return null;
    }

    @Override
    public AyoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return delegatesManager.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void bindHolder(AyoViewHolder h, ItemBean bean, int position) {
        delegatesManager.onBindViewHolder(mList, position, h);
    }

    @Override
    protected int getLayoutIdByType(int type) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public void remove(ItemBean t){
        mList.remove(t);
    }

    public void remove(int index){
        mList.remove(index);
    }


    private static <T> List<T> upgrade(List<? extends T> list){
        List<T> res = new ArrayList<>();
        for(T t: list){
            res.add(t);
        }
        return res;
    }
}

