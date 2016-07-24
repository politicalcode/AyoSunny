package com.ayoview.sample.progress;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cowthan.sample.BaseActivity;
import com.cowthan.sample.R;

import org.ayo.view.progress.ProgressBarView;


public class DemoProgressBarView extends BaseActivity {


    private ProgressHandler progressHandler = new ProgressHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sp_cpb2_ac_main);

        Button button = (Button) findViewById(R.id.btn);
        final ProgressBarView v1 = (ProgressBarView) findViewById(R.id.view);
        final ProgressBarView v2 = (ProgressBarView) findViewById(R.id.view2);
        final ProgressBarView v3 = (ProgressBarView) findViewById(R.id.view3);
        final ProgressBarView v4 = (ProgressBarView) findViewById(R.id.view4);
        final ProgressBarView v5 = (ProgressBarView) findViewById(R.id.view5);

        //progressBarView.setPercent(false);
        //progressBarView.setStyle(0);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressHandler.sendEmptyMessageDelayed(ProgressHandler.UPDATE, ProgressHandler.TIME);
            }
        });


        progressHandler.setProgress(new ProgressHandler.Progress() {
            @Override
            public void setSchedule(int schedule) {
                v1.setCurrentProgress(schedule);
                v2.setCurrentProgress(schedule);
                v3.setCurrentProgress(schedule);
                v4.setCurrentProgress(schedule);
                v5.setCurrentProgress(schedule);
            }

            @Override
            public void onSuccess() {
            }
        });


    }
}
