package com.ayoview.sample.progress.av;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.cowthan.sample.R;

import org.ayo.view.progress.av.AVLoadingIndicatorView;

/**
 * Created by Jack Wang on 2016/5/6.
 */
public class AVLoadingIndicatorDialog extends AlertDialog {

    private TextView mMessageView;
    View view;

    public AVLoadingIndicatorDialog(Context context) {

        super(context);
        view = LayoutInflater.from(getContext()).inflate(R.layout.av_progress_avld,null);
        mMessageView= (TextView) view.findViewById(R.id.message);
        setView(view);
    }

    public void setIndicator(int id, int color){
        AVLoadingIndicatorView av = (AVLoadingIndicatorView) view.findViewById(R.id.av);
        if(id < 0){
            id = AVLoadingIndicatorView.Pacman;
        }
        if(color <= 0){
            color = Color.parseColor("#FFE75764");
        }
        av.setIndicator(id, color);
    }


    @Override
    public void setMessage(CharSequence message) {
        mMessageView.setText(message);
    }
}
