package com.ayoview.sample.progress;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.SeekBar;

import com.cowthan.sample.BaseActivity;
import com.cowthan.sample.R;

import org.ayo.view.progress.CircularProgressBar;

public class DemoCircularProgressBar extends BaseActivity {

    private CircularProgressBar circularProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sp_ac_demo_circular_progress_bar);

        circularProgressBar = (CircularProgressBar) findViewById(R.id.circularProgressbar);
        circularProgressBar.setProgressWithAnimation(65);

        // PROGRESS
        ((SeekBar) findViewById(R.id.seekBarProgress)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                circularProgressBar.setProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // BORDER
        ((SeekBar) findViewById(R.id.seekBarStrokeWidth)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                circularProgressBar.setProgressBarWidth(progress * getResources().getDisplayMetrics().density);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // BACKGROUND BORDER
        ((SeekBar) findViewById(R.id.seekBarBackgroundStrokeWidth)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                circularProgressBar.setBackgroundProgressBarWidth(progress * getResources().getDisplayMetrics().density);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // COLOR
//        ((LobsterShadeSlider) findViewById(R.id.shadeslider)).addOnColorListener(new OnColorListener() {
//            @Override
//            public void onColorChanged(@ColorInt int color) {
//                circularProgressBar.setColor(color);
//                circularProgressBar.setBackgroundColor(adjustAlpha(color, 0.3f));
//            }
//
//            @Override
//            public void onColorSelected(@ColorInt int color) {
//            }
//        });
    }

    /**
     * Transparent the given color by the factor
     * The more the factor closer to zero the more the color gets transparent
     *
     * @param color  The color to transparent
     * @param factor 1.0f to 0.0f
     * @return int - A transplanted color
     */
    private int adjustAlpha(int color, float factor) {
        int alpha = Math.round(Color.alpha(color) * factor);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }

}
