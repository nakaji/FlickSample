package com.nakaji.android.flicksample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ViewFlipper;

public class FlickSampleActivity extends Activity {

    ViewFlipper viewFlipper;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        viewFlipper = (ViewFlipper) findViewById(R.id.ViewFlipper);

        FlickUtil futil = new FlickUtil();
        futil.setOnTouchListener(this, viewFlipper);
    }

}