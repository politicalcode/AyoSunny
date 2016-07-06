package com.ayoview.sample.progress.av;

import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.cowthan.sample.BaseActivity;
import com.cowthan.sample.R;

import org.ayo.lang.OnWalk;
import org.ayo.view.progress.av.AVLoadingIndicatorView;


public class AVMainActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.av_activity_loading);

        walkOnViewTree(this.<ViewGroup>findViewById(R.id.roooooot), new OnWalk<View>() {
            @Override
            public boolean process(int index, View view, int total) {
                if(view instanceof AVLoadingIndicatorView){
                    view.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View view) {
                            AVLoadingIndicatorView av = (AVLoadingIndicatorView) view;
                            //Toaster.toastShort(av.getIndicatorName());

                            final AVLoadingIndicatorDialog dialog=new AVLoadingIndicatorDialog(getActivity());
                            dialog.setMessage(av.getIndicatorName() + ": Loading...");
                            dialog.setIndicator(av.getIndicatorId(), 0);
                            dialog.show();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    dialog.cancel();
                                }
                            },4000);

                        }
                    });
                }
                return false;
            }
        });
    }

    private void walkOnViewTree(ViewGroup parent, OnWalk<View> onWalk){
        if(parent != null && parent.getChildCount() > 0){
            for(int i = 0; i < parent.getChildCount(); i++){
                View son = parent.getChildAt(i);
                if(son instanceof ViewGroup) {
                    walkOnViewTree((ViewGroup) son, onWalk);
                }else{
                    onWalk.process(i, son, parent.getChildCount());
                }
            }
        }
    }


    @Override
    public Boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.av_menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public Boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.action_settings){
            final AVLoadingIndicatorDialog dialog=new AVLoadingIndicatorDialog(getActivity());
            dialog.setMessage("Loading");
            dialog.show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    dialog.cancel();
                }
            },2000);
        }
        return super.onOptionsItemSelected(item);
    }
}
