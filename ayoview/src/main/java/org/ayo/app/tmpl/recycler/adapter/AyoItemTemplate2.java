package org.ayo.app.tmpl.recycler.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import org.ayo.app.adapter.AdapterDelegate;
import org.ayo.app.tmpl.recycler.ItemBean;

import java.util.List;

/**
 * 模板，负责处理一个类型的Bean的显示，依赖于
 */
public abstract class AyoItemTemplate2 implements AdapterDelegate<List<ItemBean>> {

    public abstract boolean isForViewType(ItemBean itemBean, int position);
    public abstract void onBindViewHolder(ItemBean itemBean, int position, AyoViewHolder holder);

    protected AyoRecyclerAdapter<ItemBean> mAdapter;

    public void bindAdapter(AyoRecyclerAdapter<ItemBean> ayoAdapter){
        this.mAdapter = ayoAdapter;
    }

    public AyoRecyclerAdapter<ItemBean> adapter(){
        return mAdapter;
    }

    protected Activity mActivity;

    public AyoItemTemplate2(Activity activity) {
        this.mActivity = activity;
    }

    @Override
    public boolean isForViewType(@NonNull List<ItemBean> items, int position) {
        return isForViewType(items.get(position), position);
    }


//    @NonNull
//    @Override
//    public AyoViewHolder onCreateViewHolder(ViewGroup parent) {
//        return new AyoViewHolder(View.inflate(mActivity, getLayoutId(), null));
//    }

    @Override
    public void onBindViewHolder(@NonNull List<ItemBean> items, int position, @NonNull AyoViewHolder holder) {
        onBindViewHolder(items.get(position), position, holder);
    }

    protected abstract int getLayoutId();

    @NonNull
    @Override
    public AyoViewHolder onCreateViewHolder(ViewGroup parent) {
        View v = View.inflate(mActivity, getLayoutId(), null);
        AyoViewHolder h = new AyoViewHolder(v);
        return h;
    }

    //protected int getLayoutId();

}
