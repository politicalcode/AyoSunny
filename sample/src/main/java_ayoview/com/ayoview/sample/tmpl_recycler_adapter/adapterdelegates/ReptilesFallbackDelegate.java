package com.ayoview.sample.tmpl_recycler_adapter.adapterdelegates;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cowthan.sample.R;

import org.ayo.app.adapter.AbsFallbackAdapterDelegate;

/**
 * @author Hannes Dorfmann
 */
public class ReptilesFallbackDelegate extends AbsFallbackAdapterDelegate {

  private LayoutInflater inflater;

  public ReptilesFallbackDelegate(Activity activity) {
    inflater = activity.getLayoutInflater();
  }

  @NonNull @Override public AyoViewHolder onCreateViewHolder(ViewGroup parent) {
    View view = inflater.inflate(R.layout.adapter_delegate_item_unknown_reptile, parent, false);
    return new AyoViewHolder(view);
  }

  @Override public void onBindViewHolder(@NonNull Object items, int position,
      @NonNull AyoViewHolder holder) {

  }

  class ReptileFallbackViewHolder extends RecyclerView.ViewHolder {
    public ReptileFallbackViewHolder(View itemView) {
      super(itemView);
    }
  }
}
