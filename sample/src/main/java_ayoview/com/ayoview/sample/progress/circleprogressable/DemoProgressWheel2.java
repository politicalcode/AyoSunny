package com.ayoview.sample.progress.circleprogressable;

import android.os.Bundle;

import com.cowthan.sample.BaseActivity;
import com.cowthan.sample.R;

import org.ayo.lang.Lang;
import org.ayo.view.progress.ProgressWheel2;

/**
 */
public class DemoProgressWheel2 extends BaseActivity {

	private ProgressWheel2 pb0, pb1;
    private boolean wasSpinning = false;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_wheel_activity);
        pb0 = findViewById(R.id.pb0);
        pb1 = findViewById(R.id.pb1);

        Lang.run(new Runnable() {

            int progress = 0;

            @Override
            public void run() {
                progress += 18;
                pb0.setProgress(progress);
                pb1.setProgress(progress);
            }
        }, 20, 300);
    }

    @Override
    public void onPause() {
        super.onPause();
        if(wasSpinning = pb0.isSpinning()) pb0.stopSpinning();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(wasSpinning) {
            pb0.startSpinning();
        }
        wasSpinning = false;
    }

}
