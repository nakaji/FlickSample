package com.nakaji.android.flicksample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.nakaji.android.flicksample.FlickUtil.FlickLogic;

public class FlickSampleActivity extends Activity {

    ViewFlipper viewFlipper;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ((ListView) findViewById(R.id.ListView01)).setOnItemClickListener(onItemClickListener);
        ((ListView) findViewById(R.id.ListView02)).setOnItemClickListener(onItemClickListener);
        ((ListView) findViewById(R.id.ListView03)).setOnItemClickListener(onItemClickListener);

        ((ListView) findViewById(R.id.ListView01)).setOnItemLongClickListener(onItemLongClickListener);
        ((ListView) findViewById(R.id.ListView02)).setOnItemLongClickListener(onItemLongClickListener);
        ((ListView) findViewById(R.id.ListView03)).setOnItemLongClickListener(onItemLongClickListener);

        viewFlipper = (ViewFlipper) findViewById(R.id.ViewFlipper);

        new FlickUtil(this, viewFlipper, new FlickLogic() {
            @Override
            public void setDataLogic() {
                View v = viewFlipper.getCurrentView();
                Log.d("FlickSample", "SetDataLogic.setDataLogic : " + v.toString());
            }

            @Override
            public void rightToLeftLogic() {
                View v = viewFlipper.getCurrentView();
                Log.d("FlickSample", "SetDataLogic.rightToLeftLogic : " + v.toString());
            }

            @Override
            public void leftToRightLogic() {
                View v = viewFlipper.getCurrentView();
                Log.d("FlickSample", "SetDataLogic.leftToRightLogic : " + v.toString());
            }
        });
    }

    private OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int arg2, long arg3) {
            Log.d("FlickSample", "onItemClick : " + view.toString());
        }
    };

    private OnItemLongClickListener onItemLongClickListener = new OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            Log.d("FlickSample", "onItemLongClick : " + arg1.toString());
            return false;
        }
    };

}
