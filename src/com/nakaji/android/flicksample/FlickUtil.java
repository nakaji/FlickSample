package com.nakaji.android.flicksample;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

public class FlickUtil {
    ViewFlipper viewFlipper;

    GestureDetector gestureDetecotr;

    Animation inFromLeft;
    Animation outToRight;
    Animation inFromRight;
    Animation outToLeft;

    SetDataLogic setDataLogic;

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
                setDataLogic.setData();
                return true;
            }

            return false;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }
    };

    // タッチ処理リスナー
    OnTouchListener touchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (gestureDetecotr.onTouchEvent(event)) {
                return true;
            }
            return false;
        }
    };

    public void setOnTouchListener(Context context, ViewFlipper flipper) {

        viewFlipper = flipper;
        gestureDetecotr = new GestureDetector(context, gestureListener);

        inFromLeft = AnimationUtils.loadAnimation(context, R.anim.in_from_left);
        outToRight = AnimationUtils.loadAnimation(context, R.anim.out_to_right);
        inFromRight = AnimationUtils.loadAnimation(context, R.anim.in_from_right);
        outToLeft = AnimationUtils.loadAnimation(context, R.anim.out_to_left);

        // FiewFlipperの子に対して、OnTouchListenerを割り当てる
        int child_count = viewFlipper.getChildCount();
        for (int i = 0; i < child_count; i++) {
            viewFlipper.getChildAt(i).setOnTouchListener(touchListener);
        }
    }

    public void setDataLogic(SetDataLogic logic) {
        setDataLogic = logic;
    }

    public interface SetDataLogic {
        public void setData();
    }
}
