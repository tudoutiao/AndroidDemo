package com.example.app.refresh;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.app.R;

public class WaveView extends View {
    private int waveHeight;
    private Paint paint;

    public WaveView(Context context) {
        this(context, null, 0);
        init();
    }

    public WaveView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(getResources().getColor(R.color.feac2c));
        paint.setAntiAlias(true);
        // 获取位图
    }

    ValueAnimator v2;

    int distanceY = 228;
    int startY = 210;
    int b;
    int centerY = 0;
    int centerX = 0;
    int r, lastR;
    float newR;
    float newCenter;
    private int yPoint;
    ValueAnimator v;
    private boolean isStarted = false;

    private boolean isBacking = false;

    public boolean isBacking() {
        return isBacking;
    }


    public void setBacking(boolean backing) {
        isBacking = backing;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        b = waveHeight + 1;
        centerX = getMeasuredWidth() / 2;
        try {
            r = (b * b + centerX * centerX) / 2 / b;
        } catch (ArithmeticException e) {
        }

        centerY = b - r;

        if (b > startY) { //大于画圆高度 ，画小圆
            if (!isStarted) {
                lastR = r;
                startAnimator();
                LongLoad();
            }
            canvas.drawCircle(centerX, newCenter + startY, newR, paint);
        } else {
            shortLoad();
            isStarted = false;
            if (isBacking) {
                r = 30;
            }
            canvas.drawCircle(centerX, centerY, r, paint);
//            Log.e("xxx", "   drawCircle:centerX:" + centerX + "  centerY:" + centerY + "  r:" + r);
        }

    }

    public void startDownAnimator() {
        ValueAnimator v3;
        v3 = ValueAnimator.ofInt(getMeasuredHeight(), 0);
        v3.setDuration(580);
        v3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                yPoint = (int) valueAnimator.getAnimatedValue();
                newCenter = getMeasuredHeight() / 2;
                newR = 30;
                postInvalidate();
            }
        });
        v3.start();
    }

    public void startLoadingAnimator() {
        final int endValue = distanceY - startY - 30;
        v2 = ValueAnimator.ofInt(endValue, endValue + 20);
        v2.setRepeatMode(ValueAnimator.REVERSE);
        v2.setRepeatCount(ValueAnimator.INFINITE);
        v2.setDuration(400);
        v2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                yPoint = (int) valueAnimator.getAnimatedValue();
                newCenter = endValue - yPoint;
                newR = 30;
                postInvalidate();
            }
        });
        v2.start();
    }


    public void endLoadingAnimator() {
        if (v2 != null) {
            v2.cancel();
            v2 = null;
        }
    }

    public void startAnimator() {
        final int endValue = distanceY - startY + 15;
        v = ValueAnimator.ofInt(0, endValue);
        v.setDuration(580);
        v.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                isStarted = true;
                yPoint = (int) valueAnimator.getAnimatedValue();
                float rate = ((float) yPoint) / (distanceY - startY);
                if (rate < 1) {
                    newR = lastR * (1 - rate);
                } else {
                    newR = 30;
                    v.cancel();
                }
                newCenter = yPoint - newR;
                postInvalidate();

            }
        });
        v.start();
    }

    public int getWaveHeight() {
        return waveHeight;
    }

    public void setWaveHeight(int waveHeight) {
        this.waveHeight = waveHeight;

    }

    private OnLongLoad longLoad;

    public void LongLoad() {
        if (longLoad != null) {
            longLoad.onLongLoad(true);
        }
    }

    public void shortLoad() {
        if (longLoad != null) {
            longLoad.onLongLoad(false);
        }
    }

    public void setLongLoad(OnLongLoad longLoad) {
        this.longLoad = longLoad;
    }

    public interface OnLongLoad {
        void onLongLoad(boolean isLong);
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }
}
