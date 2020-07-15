package com.example.app.refresh;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.app.R;


public class BackgroundOvalView extends View {
    private int start;
    private int end;
    private int centerY;
    private int r;
    private Paint paint;

    public BackgroundOvalView(Context context) {
        super(context);
        init();
    }

    public BackgroundOvalView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BackgroundOvalView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(getResources().getColor(R.color.f8f2dc));
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(getMeasuredWidth() / 2, centerY, r, paint);
    }

    public void clear() {
        r = 0;
        centerY = 0;
        getLayoutParams().height = 0;
        requestLayout();
    }


    public void startDrawBack(final int i, final int height, final int centerY) {
        this.centerY = 0 - centerY;
        ValueAnimator v = ValueAnimator.ofInt(0, height + 150);
        v.setDuration(260);
        v.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            boolean isShowed = false;

            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (int) valueAnimator.getAnimatedValue();
                r = centerY + value;
                if (drawOverCallback != null) {
                    if (i == drawOverCallback.getCount() - 1) {
                        if (value == height + 150) {
                            drawOverCallback.onDrawOver(i);
                        }
                    } else if (value > height * 0.7 && !isShowed) {
                        isShowed = true;
                        drawOverCallback.onDrawOver(i);
                    }
                }
                postInvalidate();
            }
        });

        v.start();
    }

    public DrawOverCallback drawOverCallback;

    public DrawOverCallback getDrawOverCallback() {
        return drawOverCallback;
    }

    public void setDrawOverCallback(DrawOverCallback drawOverCallback) {
        this.drawOverCallback = drawOverCallback;
    }

    public interface DrawOverCallback {
        void onDrawOver(int i);

        int getCount();

        int getHeight();
    }

}
