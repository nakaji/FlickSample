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
    FlickLogic setDataLogic;

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

            if (dx > dy && dx > 150) {
                if (e1.getX() < e2.getX()) {
                    viewFlipper.setInAnimation(inFromLeft);
                    viewFlipper.setOutAnimation(outToRight);
                    viewFlipper.showPrevious();
                    setDataLogic.leftToRightLogic();
                } else {
                    viewFlipper.setInAnimation(inFromRight);
                    viewFlipper.setOutAnimation(outToLeft);
                    viewFlipper.showNext();
                    setDataLogic.rightToLeftLogic();
                }
                setDataLogic.setDataLogic();
                Log.d("BBT", "OnGestureListener.onFling");
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

    /***
     * コンストラクタ
     * @param context
     * @param flipper
     * @param logic
     */
    public FlickUtil(Context context, ViewFlipper flipper, FlickLogic logic) {
        viewFlipper = flipper;
        gestureDetecotr = new GestureDetector(context, gestureListener);

        inFromLeft = AnimationUtils.loadAnimation(context, R.anim.in_from_left);
        outToRight = AnimationUtils.loadAnimation(context, R.anim.out_to_right);
        inFromRight = AnimationUtils.loadAnimation(context, R.anim.in_from_right);
        outToLeft = AnimationUtils.loadAnimation(context, R.anim.out_to_left);

        setOnTouchListener(context, flipper);
        setDataLogic(logic);
    }

    /***
     * ViewFlipperの子ViewにOnTouchListenerを設定する
     * @param context
     * @param flipper
     */
    private void setOnTouchListener(Context context, ViewFlipper flipper) {
        int child_count = viewFlipper.getChildCount();
        for (int i = 0; i < child_count; i++) {
            viewFlipper.getChildAt(i).setOnTouchListener(touchListener);
        }
    }

    /***
     * setDataLogicを設定する
     * @param logic
     */
    private void setDataLogic(FlickLogic logic) {
        setDataLogic = logic;
    }

    /***
     * フリックに関する処理に関するインタフェース
     *
     * @author nakaji
     *
     */
    public interface FlickLogic {
        public void setDataLogic();

        public void rightToLeftLogic();

        public void leftToRightLogic();
    }
}
