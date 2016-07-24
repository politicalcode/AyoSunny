package org.ayo.app.tmpl.recycler.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 最原始的Adapter，只是基于AyoViewHolder对原生adapter的封装
 */
public abstract class AyoRecyclerAdapter<T> extends RecyclerView.Adapter<AyoViewHolder> {

    protected List<T> mList;
    protected Activity mActivity;

    public AyoRecyclerAdapter(Activity a, List<T> list){
        this.mList = list;
        this.mActivity = a;
    }

    public AyoViewHolder newHolder(ViewGroup parent, int viewType, View contentView){
        return new AyoViewHolder(contentView);
    }
    public abstract void bindHolder(AyoViewHolder h, T bean, int position);
    protected abstract int getLayoutIdByType(int type);
//    {
//
//        HashMap<Integer, Integer> maps = new HashMap<Integer, Integer>();
//        maps.put(0, R.layout.item_article_one_small_image);
//        maps.put(1, R.layout.item_article_ad);
//        maps.put(2, R.layout.item_article_tripple_image);
//        maps.put(3, R.layout.item_article_one_big_image);
//        maps.put(4, R.layout.item_article_vedio);
//
//        return maps.get(new Integer(type));
//
//    }

    @Override
    public AyoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int id = getLayoutIdByType(viewType);
        if(id > 0){
            View v = View.inflate(mActivity, id, null);
            return newHolder(parent, viewType, v);
        }else{
            throw new RuntimeException("item的布局id不合法--" + id);
        }
    }

    @Override
    public void onBindViewHolder(AyoViewHolder holder, int position) {
        T t = mList.get(position);
        bindHolder(holder, t, position);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public void notifyDataSetChanged(List<T> list){
        this.mList = list;
        this.notifyDataSetChanged();
    }

    public void remove(T t){
        mList.remove(t);
    }

    public void remove(int index){
        mList.remove(index);
    }
}
