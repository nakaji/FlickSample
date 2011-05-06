package com.nakaji.android.flicksample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.nakaji.android.flicksample.FlickUtil.SetDataLogic;

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

        ((ListView) findViewById(R.id.ListView01)).setOnItemClickListener(onItemClickListener);
        ((ListView) findViewById(R.id.ListView02)).setOnItemClickListener(onItemClickListener);
        ((ListView) findViewById(R.id.ListView03)).setOnItemClickListener(onItemClickListener);

        futil.setDataLogic(new SetDataLogic() {
            @Override
            public void setData() {
                View v = viewFlipper.getCurrentView();
                Log.d("FlickSample", "SetDataLogic.setData : " + v.toString());

            }
        });
    }

    private OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int arg2, long arg3) {
            Log.d("FlickSample", "onItemClick : " + view.toString());
        }
    };

}
