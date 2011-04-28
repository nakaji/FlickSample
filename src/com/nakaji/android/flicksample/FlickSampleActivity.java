package com.nakaji.android.flicksample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import android.widget.ViewFlipper;

public class FlickSampleActivity extends Activity {

    ListView listView01;
    ListView listView02;
    ListView listView03;

    ViewFlipper viewFlipper;

    GestureDetector gestureDetecotr;

    Animation inFromLeft;
    Animation outToRight;
    Animation inFromRight;
    Animation outToLeft;

    // タッチ処理リスナー
    OnTouchListener touchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            Log.d("Chapter06", "onTouch : " + v.toString());
            if (gestureDetecotr.onTouchEvent(event)) {
                return true;
            }
            return false;
        }
    };

    // ジェスチャーリスナー
    OnGestureListener gestureListener = new OnGestureListener() {
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float dx = Math.abs(velocityX);
            float dy = Math.abs(velocityY);
            Log.d("Chapter06", "onFling : (" + dx + "," + dy + ")");
            if (dx > dy && dx > 150) {
                if (e1.getX() < e2.getX()) {
                    viewFlipper.setInAnimation(inFromLeft);
                    viewFlipper.setOutAnimation(outToRight);
                    viewFlipper.showPrevious();
                } else {
                    viewFlipper.setInAnimation(inFromRight);
                    viewFlipper.setOutAnimation(outToLeft);
                    viewFlipper.showNext();
                }
                return true;
            }

            return false;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }
    };

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        listView01 = (ListView) findViewById(R.id.ListView01);
        listView02 = (ListView) findViewById(R.id.ListView02);
        listView03 = (ListView) findViewById(R.id.ListView03);

        setFlick(new View[] { listView01, listView02, listView03 });
    }

    private void setFlick(View[] views) {
        viewFlipper = (ViewFlipper) findViewById(R.id.ViewFlipper);

        gestureDetecotr = new GestureDetector(this, gestureListener);

        inFromLeft = AnimationUtils.loadAnimation(this, R.anim.in_from_left);
        outToRight = AnimationUtils.loadAnimation(this, R.anim.out_to_right);
        inFromRight = AnimationUtils.loadAnimation(this, R.anim.in_from_right);
        outToLeft = AnimationUtils.loadAnimation(this, R.anim.out_to_left);

        for (View v : views) {
            v.setOnTouchListener(touchListener);
        }
    }

    private class FlickUtils {

    }

}