package com.example.app.refresh;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.view.MotionEventCompat;
import androidx.core.view.NestedScrollingChild;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.NestedScrollingParent;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.core.view.ViewCompat;

import com.example.app.R;
import com.example.app.util.StringUtil;
import com.example.app.util.Utils;

import java.util.ArrayList;
import java.util.Random;

public class CTSwipeRefreshLayout extends FrameLayout implements NestedScrollingChild, NestedScrollingParent, WaveView.OnLongLoad {

    private View mTarget;
    public LinearLayout mRefreshView;
    private int mTouchSlop;
    private NestedScrollingParentHelper mNestedScrollingParentHelper;
    private NestedScrollingChildHelper mNestedScrollingChildHelper;
    private ArrayList<RefrshSubject> list = new ArrayList<>();
    private int p;
    private ImageView imPull;
    private TextView tvTip;
    private int refreshH;
    private String showText;
    private boolean isLongRfreshEnable = false;
    //头部layout
    protected FrameLayout mHeadLayout;
    private WaveView waveView;
    private OnRefreshListener onRefreshListener; //短拉接口
    private OnLongRefreshListener onLongRefreshListener; //长拉接口
    private boolean isLong = false;
    private boolean enableTouch = true;//是否允许继续下拉
    private LinearLayout textLayout;
    private boolean isBacking;
    float mInitialMotionY;
    boolean mIsBeingDragged;
    float mBeingTranslationY;
    float mInitialDownY;
    private int mActivePointerId = INVALID_POINTER;
    private static final int INVALID_POINTER = -1;
    private static final float DRAG_RATE = .5f;
    private boolean mNestedScrollInProgress;
    float translationY;

    public CTSwipeRefreshLayout(Context context) {
        super(context);
        init(context);
    }

    public CTSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CTSwipeRefreshLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public void setLongRefreshEnable(boolean isLongRfreshEnable) {
        this.isLongRfreshEnable = isLongRfreshEnable;
    }

    //是否支持长拉操作。
    public boolean isLongRefreshEnable() {
        return isLongRfreshEnable;
    }

    private String getText() {
        list = (ArrayList<RefrshSubject>) StringUtil.getRefreshHints();
        if (list != null && list.size() > 0) {
            int i = new Random().nextInt(list.size());
            RefrshSubject refrshSubject = list.get(i);
            if ("true".equals(refrshSubject.getEnable())) {
                showText = refrshSubject.getContent();
                return showText;
            } else {
                return getText();
            }
        } else {
            list = (ArrayList<RefrshSubject>) StringUtil.getRefreshHints();
            showText = "暂时没啥说的";
            return showText;
        }
    }

    private void init(Context context) {
        p = new Utils().d2p(10);
        refreshH = (int) (context.getResources().getDimension(R.dimen.dimen_63));
        mNestedScrollingParentHelper = new NestedScrollingParentHelper(this);
        mNestedScrollingChildHelper = new NestedScrollingChildHelper(this);
        setNestedScrollingEnabled(true);
        setBackgroundColor(getResources().getColor(R.color.ffffff));
        mRefreshView = new LinearLayout(getContext());
        FrameLayout headViewLayout = new FrameLayout(getContext());
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        layoutParams.gravity = Gravity.TOP;
        headViewLayout.setLayoutParams(layoutParams);
        mHeadLayout = headViewLayout;
        waveView = new WaveView(getContext());
        mHeadLayout.addView(waveView);
        mRefreshView.addView(mHeadLayout);
        waveView.setLongLoad(this);
        mRefreshView.setBackgroundColor(getResources().getColor(R.color.ffffff));
        mRefreshView.setGravity(Gravity.CENTER);
        mRefreshView.setOrientation(LinearLayout.VERTICAL);
        mRefreshView.setPadding(0, 0, 0, 0);
        imPull = new ImageView(getContext());
        imPull.setImageResource(R.mipmap.ic_progress_pull_list);
//        mRefreshView.addView(imPull);
        textLayout = new LinearLayout(getContext());
        textLayout.setOrientation(LinearLayout.VERTICAL);
        textLayout.setPadding(p, p, p, p);
        textLayout.setGravity(Gravity.CENTER);
        textLayout.addView(imPull);

        tvTip = new TextView(getContext());
        tvTip.setGravity(Gravity.CENTER);
        tvTip.setPadding(0, 10, 0, 0);
        tvTip.setTextSize(10);
        tvTip.setTextColor(getResources().getColor(R.color.afafaf));
        textLayout.addView(tvTip);
        mRefreshView.addView(textLayout);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        addView(mRefreshView, params);
        mTouchSlop = new Utils().d2p(3);
        getText();

    }

    public void onPulling(float fraction) {
        if (isLongRefreshEnable()) {
            float headW = new Utils().d2p(60);
            float fraction2 = fraction / headW;
            int height = (int) (headW * Math.max(0, fraction2 - 1));
            waveView.setWaveHeight(height - 15);
            mHeadLayout.getLayoutParams().height = height;
            mHeadLayout.requestLayout();
            waveView.invalidate();
        }
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int width = getMeasuredWidth();
        final int height = getMeasuredHeight();
        if (getChildCount() == 0) {
            return;
        }
        if (mTarget == null) {
            ensureTarget();
        }
        if (mTarget == null) {
            return;
        }
        try {
            final View child = mTarget;
            final int childLeft = getPaddingLeft();
            final int childTop = getPaddingTop();
            final int childWidth = width - getPaddingLeft() - getPaddingRight();
            final int childHeight = height - getPaddingTop() - getPaddingBottom();
            child.layout(childLeft, childTop, childLeft + childWidth, childTop + childHeight);
            mRefreshView.layout(0, -mRefreshView.getMeasuredHeight(), mRefreshView.getMeasuredWidth(), 0);
            refreshH = mRefreshView.getMeasuredHeight() - 30;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLongLoad(boolean isLong) {
        this.isLong = isLong;
        if (isLong) {
            textLayout.setVisibility(INVISIBLE);
            enableTouch = false;
        } else {
            textLayout.setVisibility(VISIBLE);
            enableTouch = true;
        }
    }

    public interface OnRefreshListener {
        void onRefresh();
    }

    public interface OnLongRefreshListener {
        void onAnimatorSetOver();

        void onLongRefresh();
    }

    public OnLongRefreshListener getOnLongRefreshListener() {
        return onLongRefreshListener;
    }

    public void setOnLongRefreshListener(OnLongRefreshListener onLongRefreshListener) {
        this.onLongRefreshListener = onLongRefreshListener;
    }

    public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
        this.onRefreshListener = onRefreshListener;
    }

    private boolean isRefreshing;

    public boolean isRefreshing() {
        return isRefreshing;
    }

    public void setRefreshComplete() {
        updateRefreshView(STATE_DONE);
    }

    public synchronized void startRefresh() {
        if (refreshState != STATE_DONE) {
            return;
        }
        initRefreshView();
        postDelayed(new Runnable() {
            @Override
            public void run() {
                updateRefreshView(STATE_REFRESH_ING);
            }
        }, 100);
    }

    public void startLongRefresh() {
        if (refreshState != STATE_DONE)
            return;
        isLong = true;
        isLongRfreshEnable = true;
        initRefreshView();
        final int length = new Utils().d2p(140);
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, length);
        valueAnimator.setDuration(400);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int h = (int) animation.getAnimatedValue();
                translationY = h;
                if (null != mTarget)
                    mTarget.setTranslationY(translationY);
                mRefreshView.setTranslationY(translationY);
                onPulling(translationY);
                if (translationY == length) {
                    refreshState = STATE_LONG_REFRESH_ING;
                    updateRefreshView(refreshState);
                }
            }
        });
        valueAnimator.start();
    }


    private int refreshState;
    private static final int STATE_DONE = 0;
    private static final int STATE_PULL_TO_REFRESH = 1;
    private static final int STATE_RELEASE_TO_REFRESH = 2;
    private static final int STATE_RELEASE_TO_LONG_REFRESH = 5;
    private static final int STATE_REFRESH_ING = 3;
    private static final int STATE_LONG_REFRESH_ING = 4;

    private synchronized void updateRefreshView(int state) {
        switch (state) {
            case STATE_DONE:
                if (refreshState != STATE_DONE) {
                    isBacking = true;
                    waveView.setBacking(isBacking);
                    if (textLayout.getHeight() > 0) {
                        height = textLayout.getHeight();
                    }
                    if (refreshState == STATE_LONG_REFRESH_ING) {
                        final int waveHeight = waveView.getHeight();

                        maxHeight = height;
                        if (waveHeight > maxHeight) {
                            maxHeight = waveHeight;
                        }
                        ValueAnimator valueAnimator2 = ValueAnimator.ofInt(0, maxHeight);
                        valueAnimator2.setDuration(400);
                        valueAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            boolean isRefresh = false;

                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                int h = (int) animation.getAnimatedValue();
                                int he = height - h * 3;
                                if (he >= 0) {
                                    textLayout.getLayoutParams().height = he;
                                }
                                textLayout.requestLayout();
                                ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) waveView.getLayoutParams();
                                lp.topMargin = h;
                                waveView.setLayoutParams(lp);
                                if (h > maxHeight * 0.2 && !isRefresh) {
                                    isRefresh = true;
                                    if (null != getOnLongRefreshListener())
                                        getOnLongRefreshListener().onAnimatorSetOver();
                                }

                                if (h == maxHeight) {
                                    isBacking = false;
                                    waveView.setBacking(isBacking);
                                    ValueAnimator animator = ValueAnimator.ofFloat(mRefreshView.getTranslationY(), 0);
                                    animator.setDuration(300);
                                    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                        @Override
                                        public void onAnimationUpdate(ValueAnimator animation) {
                                            float v = (Float) animation.getAnimatedValue();
                                            if (null == mRefreshView || null == mTarget)
                                                return;
                                            mRefreshView.setTranslationY(v);
                                            mTarget.setTranslationY(v);

                                        }
                                    });
                                    animator.addListener(new BaseAnimatorListener() {
                                        @Override
                                        public void onAnimationEnd(Animator arg0) {
                                            stopRefreshAnimator();
                                            waveView.endLoadingAnimator();
                                            isBacking = false;
                                            waveView.setBacking(isBacking);

                                            super.onAnimationEnd(arg0);
                                        }
                                    });
                                    animator.start();
                                }
                            }
                        });
                        valueAnimator2.start();
                    } else {
                        ValueAnimator animator = ValueAnimator.ofFloat(mRefreshView.getTranslationY(), 0);
                        animator.setDuration(300);
                        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                float v = (Float) animation.getAnimatedValue();
                                if (null == mRefreshView || null == mTarget)
                                    return;
                                mRefreshView.setTranslationY(v);
                                mTarget.setTranslationY(v);
                            }
                        });
                        animator.addListener(new BaseAnimatorListener() {
                            @Override
                            public void onAnimationEnd(Animator arg0) {
                                stopRefreshAnimator();
                                waveView.endLoadingAnimator();
                                isBacking = false;
                                waveView.setBacking(isBacking);
                                super.onAnimationEnd(arg0);
                            }
                        });
                        animator.start();
                    }
                    getText();
                } else {
                    if (null == mRefreshView || null == mTarget)
                        return;
                    mRefreshView.setTranslationY(0);
                    mTarget.setTranslationY(0);
                    imPull.setRotation(0);
                    waveView.endLoadingAnimator();
                    isBacking = false;
                    waveView.setBacking(isBacking);
                }
                break;
            case STATE_LONG_REFRESH_ING:
            case STATE_REFRESH_ING:
                tvTip.setText(showText);
                if (state == STATE_REFRESH_ING) {
                    ValueAnimator valueAnimator = ValueAnimator.ofFloat(mRefreshView.getTranslationY(), textLayout.getHeight());
                    valueAnimator.setDuration(300);
                    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            float v = (Float) animation.getAnimatedValue();
                            if (null == mRefreshView || null == mTarget)
                                return;
                            mRefreshView.setTranslationY(v);
                            mTarget.setTranslationY(v);
                        }
                    });
                    valueAnimator.start();
                }
                startRefreshAnimator();
                break;
//            case STATE_PULL_TO_REFRESH:
//                LogThis("STATE_PULL_TO_REFRESH");
//                if (refreshState == STATE_RELEASE_TO_REFRESH) {
//                    ValueAnimator animator = ValueAnimator.ofFloat(imPull.getRotation(), 0);
//                    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                        @Override
//                        public void onAnimationUpdate(ValueAnimator animation) {
//                            float v = (Float) animation.getAnimatedValue();
//                            imPull.setRotation(v);
//                        }
//                    });
//                    animator.start();
//                } else {
//                    imPull.setRotation(0);
//                }
//                imPull.setRotation(0);
//                refreshTime();
//                tvTip.setText(showText);
//                break;
//            case STATE_RELEASE_TO_REFRESH:
//                LogThis("STATE_RELEASE_TO_REFRESH");
//                if (refreshState == STATE_PULL_TO_REFRESH) {
//                    ValueAnimator animator = ValueAnimator.ofFloat(imPull.getRotation(), 180);
//                    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                        @Override
//                        public void onAnimationUpdate(ValueAnimator animation) {
//                            float v = (Float) animation.getAnimatedValue();
//                            imPull.setRotation(v);
//                        }
//                    });
//                    animator.start();
//                } else {
//                    imPull.setRotation(180);
//                }
//                tvTip.setText(showText);
//                break;

        }
        refreshState = state;
    }

    private ValueAnimator refreshingAnimator;
    private int height;

    private int maxHeight;

    private void startRefreshAnimator() {
        if (refreshState != STATE_REFRESH_ING && refreshState != STATE_LONG_REFRESH_ING && refreshState != STATE_RELEASE_TO_LONG_REFRESH) {
            imPull.setImageResource(R.mipmap.ic_progress_pull_list);
            refreshingAnimator = ValueAnimator.ofFloat(imPull.getRotation(), imPull.getRotation() + 360);
            refreshingAnimator.setInterpolator(new LinearInterpolator());
            refreshingAnimator.setDuration(400);
            refreshingAnimator.setRepeatMode(ValueAnimator.RESTART);
            refreshingAnimator.setRepeatCount(ValueAnimator.INFINITE);
            refreshingAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(final ValueAnimator animation) {
                    float v = (Float) animation.getAnimatedValue();
                    imPull.setRotation(v);
                }
            });
            refreshingAnimator.start();
        } else if (refreshState == STATE_RELEASE_TO_LONG_REFRESH) {
            waveView.startLoadingAnimator();
        }
        if (refreshState == STATE_RELEASE_TO_LONG_REFRESH || refreshState == STATE_LONG_REFRESH_ING) {
            if (onLongRefreshListener != null) {
                onLongRefreshListener.onLongRefresh();
            }
        } else {
            if (onRefreshListener != null) {
                onRefreshListener.onRefresh();
            }
        }
        isRefreshing = true;
    }

    private void stopRefreshAnimator() {
        if (refreshingAnimator != null && (refreshingAnimator.isRunning() || refreshingAnimator.isStarted())) {
            refreshingAnimator.cancel();
            refreshingAnimator.end();
            imPull.setImageResource(R.mipmap.ic_progress_pull_list);
        }
        isRefreshing = false;
    }


    private float getMotionEventY(MotionEvent ev, int activePointerId) {
        final int index = MotionEventCompat.findPointerIndex(ev, activePointerId);
        if (index < 0) {
            return -1;
        }
        return MotionEventCompat.getY(ev, index);
    }


    //设置是否开始下拉刷新
    private void setMIsBeingDragged(boolean mIsBeingDragged) {
        this.mIsBeingDragged = mIsBeingDragged;
    }

    //获取是否开始了下拉刷新。
    private boolean isBeingDragged() {
        return mIsBeingDragged;
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        ensureTarget();
        final int action = MotionEventCompat.getActionMasked(ev);
        if (!isEnabled() || canChildScrollUp() || mNestedScrollInProgress) {
            return false;
        }
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (refreshState == STATE_REFRESH_ING || refreshState == STATE_LONG_REFRESH_ING) {
                    return false;
                }
                if (refreshState != STATE_RELEASE_TO_LONG_REFRESH && refreshState != STATE_REFRESH_ING) {
                    textLayout.setVisibility(VISIBLE);
                    LinearLayout.LayoutParams layoutParams =
                            new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    textLayout.setLayoutParams(layoutParams);
                    textLayout.requestLayout();
                    ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) waveView.getLayoutParams();
                    lp.topMargin = 0;
                    waveView.setLayoutParams(lp);
                }
                mActivePointerId = MotionEventCompat.getPointerId(ev, 0);
                setMIsBeingDragged(false);
                enableTouch = true;
                final float initialDownY = getMotionEventY(ev, mActivePointerId);
                if (initialDownY == -1) {
                    return false;
                }
                if (refreshState != STATE_REFRESH_ING) {
                    updateRefreshView(STATE_DONE);
                }
                mInitialDownY = initialDownY;
                mBeingTranslationY = mTarget.getTranslationY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (refreshState == STATE_REFRESH_ING || refreshState == STATE_LONG_REFRESH_ING) {
                    return false;
                }
                if (mActivePointerId == INVALID_POINTER) {
                    return false;
                }
                final float y = getMotionEventY(ev, mActivePointerId);
                if (y == -1) {
                    return false;
                }
                float yDiff = y - mInitialDownY;
                yDiff = yDiff + mBeingTranslationY;
                if (yDiff > mTouchSlop && !isBeingDragged()) {
                    mInitialMotionY = mInitialDownY + mTouchSlop;
                    setMIsBeingDragged(true);
                }
                break;
            case MotionEventCompat.ACTION_POINTER_UP:
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                setMIsBeingDragged(false);
                mActivePointerId = INVALID_POINTER;
                break;
        }
        return isBeingDragged();
    }


    @Override
    public boolean onTouchEvent(final MotionEvent ev) {

        final int action = MotionEventCompat.getActionMasked(ev);
        int pointerIndex = -1;
        if (!isEnabled() || canChildScrollUp() || mNestedScrollInProgress) {
            return false;
        }
        if (ev.getAction() == 261 || ev.getAction() == 517 || ev.getAction() == 262) {
            return false;
        }
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mActivePointerId = MotionEventCompat.getPointerId(ev, 0);
                setMIsBeingDragged(false);
                break;
            case MotionEvent.ACTION_MOVE: {
                pointerIndex = MotionEventCompat.findPointerIndex(ev, mActivePointerId);
                if (pointerIndex < 0) {
                    return false;
                }
                final float y = MotionEventCompat.getY(ev, pointerIndex);

                final float overscrollTop = (y - mInitialMotionY) * DRAG_RATE;

                if (isBeingDragged()) {
                    if (Math.abs(overscrollTop) > 0 && mTarget != null && mRefreshView != null) {
                        translationY = overscrollTop + mBeingTranslationY;
                        //下拉到一定高度，停止下滑
                        if (!enableTouch && translationY > (waveView.getStartY() + new Utils().d2p(76))) {
                            return true;
                        }
                        if (translationY < 0) {
                            translationY = 0;
                        }

//                        if (translationY > 440) {
//                            return translationY == 440;
//                        }
                        mTarget.setTranslationY(translationY);
                        mRefreshView.setTranslationY(translationY);

                        onPulling(translationY);
                        if (translationY > 0 && translationY < refreshH) {
                            if (refreshState != STATE_PULL_TO_REFRESH && refreshState != STATE_REFRESH_ING) {
                                updateRefreshView(STATE_PULL_TO_REFRESH);
                            }
                        } else if (translationY >= refreshH) {
//                            translationY > (waveView.getStartY() + Utils.d2p(90))
//                            CTLog.e("refreshState", "onTouchEvent :" + translationY + "___" + (waveView.getStartY()+ Utils.d2p(70))+"___"+isLong);
                            if (null != getOnLongRefreshListener() && (translationY > (waveView.getStartY() + new Utils().d2p(60)) || isLong)) {
                                if (!isLong)
                                    isLong = true;
                                if (refreshState != STATE_RELEASE_TO_LONG_REFRESH && refreshState != STATE_REFRESH_ING) {
//                                    CTLog.e("refreshState", "onTouchEvent :" + refreshState + "--long voteSuess");
                                    updateRefreshView(STATE_RELEASE_TO_LONG_REFRESH);
                                }
                            } else {
                                if (refreshState != STATE_RELEASE_TO_REFRESH && refreshState != STATE_REFRESH_ING) {
//                                    CTLog.e("refreshState", "onTouchEvent :" + refreshState + "--voteSuess");
                                    updateRefreshView(STATE_RELEASE_TO_REFRESH);
                                }
                            }
                        } else {
                            final MotionEvent newEV = MotionEvent.obtain(ev);
                            final MotionEvent newEV2 = MotionEvent.obtain(ev);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    newEV.setAction(MotionEvent.ACTION_CANCEL);
                                    dispatchTouchEvent(newEV);
                                    newEV2.setAction(MotionEvent.ACTION_DOWN);
                                    dispatchTouchEvent(newEV2);
                                }
                            }, 10);
                            return false;
                        }
                    } else {
                        return false;
                    }
                }
                break;
            }
            case MotionEventCompat.ACTION_POINTER_DOWN: {
                pointerIndex = MotionEventCompat.getActionIndex(ev);
                if (pointerIndex < 0) {
                    return false;
                }
                mActivePointerId = MotionEventCompat.getPointerId(ev, pointerIndex);
                break;
            }
            case MotionEventCompat.ACTION_POINTER_UP:
                break;
            case MotionEvent.ACTION_UP: {
                pointerIndex = MotionEventCompat.findPointerIndex(ev, mActivePointerId);
                if (pointerIndex < 0) {
                    return false;
                }
                final float y = MotionEventCompat.getY(ev, pointerIndex);
                final float overscrollTop = (y - mInitialMotionY) * DRAG_RATE;
                translationY = overscrollTop + mBeingTranslationY;
                setMIsBeingDragged(false);

                if (translationY > 0 && translationY < refreshH && refreshState != STATE_REFRESH_ING) {
                    updateRefreshView(STATE_DONE);
                } else if (translationY > refreshH) {
//                    CTLog.e("refreshState", "onTouchEvent   isLong :" + isLong);
                    if (isLong) {
                        updateRefreshView(STATE_LONG_REFRESH_ING);
                    } else {
                        updateRefreshView(STATE_REFRESH_ING);
                    }
                }
                mActivePointerId = INVALID_POINTER;
                return false;
            }
            case MotionEvent.ACTION_CANCEL:
                return false;
        }

        return true;
    }

    public boolean canChildScrollUp() {
        if (android.os.Build.VERSION.SDK_INT < 14) {
            if (mTarget instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) mTarget;
                return absListView.getChildCount() > 0
                        && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0)
                        .getTop() < absListView.getPaddingTop());
            } else {
                return ViewCompat.canScrollVertically(mTarget, -1) || mTarget.getScrollY() > 0;
            }
        } else {
            return ViewCompat.canScrollVertically(mTarget, -1);
        }
    }

    private void ensureTarget() {
        // Don't bother getting the parent height if the parent hasn't been laid
        // out yet.
        if (mTarget == null) {
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                if (!child.equals(mRefreshView)) {
                    if (child.getVisibility() == VISIBLE) {
                        mTarget = child;
                        break;
                    }
                }
            }
        }
    }


    private void initRefreshView() {
        textLayout.setVisibility(VISIBLE);
        LinearLayout.LayoutParams layoutParams =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        textLayout.setLayoutParams(layoutParams);
        textLayout.requestLayout();
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) waveView.getLayoutParams();
        lp.topMargin = 0;
        waveView.setLayoutParams(lp);
        waveView.invalidate();
    }

    @Override
    public void setNestedScrollingEnabled(boolean enabled) {
        mNestedScrollingChildHelper.setNestedScrollingEnabled(enabled);
    }

    @Override
    public boolean isNestedScrollingEnabled() {
        return mNestedScrollingChildHelper.isNestedScrollingEnabled();
    }

    @Override
    public boolean startNestedScroll(int axes) {
        return mNestedScrollingChildHelper.startNestedScroll(axes);
    }

    @Override
    public void stopNestedScroll() {
        mNestedScrollingChildHelper.stopNestedScroll();
    }


    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return false;
    }

    private float mTotalUnconsumed;

    @Override
    public void onNestedScrollAccepted(View child, View target, int axes) {
        mNestedScrollingParentHelper.onNestedScrollAccepted(child, target, axes);
        startNestedScroll(axes & ViewCompat.SCROLL_AXIS_VERTICAL);
        mTotalUnconsumed = 0;
        mNestedScrollInProgress = true;
    }

    @Override
    public void onStopNestedScroll(View target) {
        mNestedScrollingParentHelper.onStopNestedScroll(target);
        mNestedScrollInProgress = false;
        // Finish the spinner for nested scrolling if we ever consumed any
        // unconsumed nested scroll
        if (mTotalUnconsumed > 0) {
//            finishSpinner(mTotalUnconsumed);
            mTotalUnconsumed = 0;
        }
        // Dispatch up our nested parent
        stopNestedScroll();
    }

    private final int[] mParentScrollConsumed = new int[2];
    private final int[] mParentOffsetInWindow = new int[2];

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed,
                mParentOffsetInWindow);

        // This is a bit of a hack. Nested scrolling works from the bottom up, and as we are
        // sometimes between two nested scrolling views, we need a way to be able to know when any
        // nested scrolling parent has stopped handling events. We do that by using the
        // 'offset in window 'functionality to see if we have been moved from the event.
        // This is a decent indication of whether we should take over the event stream or not.
        final int dy = dyUnconsumed + mParentOffsetInWindow[1];
        if (dy < 0) {
            mTotalUnconsumed += Math.abs(dy);
//            moveSpinner(mTotalUnconsumed);
        }
    }

    private boolean mUsingCustomStart;

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        // If we are in the middle of consuming, a scroll, then we want to move the spinner back up
        // before allowing the list to scroll
        if (dy > 0 && mTotalUnconsumed > 0) {
            if (dy > mTotalUnconsumed) {
                consumed[1] = dy - (int) mTotalUnconsumed;
                mTotalUnconsumed = 0;
            } else {
                mTotalUnconsumed -= dy;
                consumed[1] = dy;

            }
//            moveSpinner(mTotalUnconsumed);
        }

        // If a client layout is using a custom start position for the circle
        // view, they mean to hide it again before scrolling the child view
        // If we get back to mTotalUnconsumed == 0 and there is more to go, hide
        // the circle so it isn't exposed if its blocking content is moved
        if (mUsingCustomStart && dy > 0 && mTotalUnconsumed == 0
                && Math.abs(dy - consumed[1]) > 0) {
//            mCircleView.setVisibility(View.GONE);
        }

        // Now let our nested parent consume the leftovers
        final int[] parentConsumed = mParentScrollConsumed;
        if (dispatchNestedPreScroll(dx - consumed[0], dy - consumed[1], parentConsumed, null)) {
            consumed[0] += parentConsumed[0];
            consumed[1] += parentConsumed[1];
        }
    }

    @Override
    public int getNestedScrollAxes() {
        return 0;
    }


    @Override
    public boolean hasNestedScrollingParent() {
        return mNestedScrollingChildHelper.hasNestedScrollingParent();
    }

    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed,
                                        int dyUnconsumed, int[] offsetInWindow) {
        return mNestedScrollingChildHelper.dispatchNestedScroll(dxConsumed, dyConsumed,
                dxUnconsumed, dyUnconsumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
        return mNestedScrollingChildHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX,
                                    float velocityY) {
        return dispatchNestedPreFling(velocityX, velocityY);
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY,
                                 boolean consumed) {
        return dispatchNestedFling(velocityX, velocityY, consumed);
    }

    @Override
    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        return mNestedScrollingChildHelper.dispatchNestedFling(velocityX, velocityY, consumed);
    }

    @Override
    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        return mNestedScrollingChildHelper.dispatchNestedPreFling(velocityX, velocityY);
    }
}
