package com.gozap.chouti.view.img;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PointF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.gozap.chouti.R;
import com.gozap.chouti.util.GlideUtil;
import com.gozap.chouti.util.ImageUtils;
import com.gozap.chouti.util.Utils;
import com.gozap.chouti.util.manager.ToastManager;

import java.util.ArrayList;

/**
 * 多图列表
 */
public class ImageListView extends HorizontalScrollView {
    private Context context;
    private ArrayList<ImageView> imageViews = new ArrayList<>();

    private float screenWidth;
    private Float childWidth;
    private GlideUtil glideUtil;

    public ImageListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context, attrs);
    }

    private float downX;    //按下时 的X坐标
    private float downY;    //按下时 的Y坐标
    private boolean isUserSwiped;
    //上一次的xy
    private PointF mLastP = new PointF();
    //2016 11 03 add,判断手指起始落点，如果距离属于滑动了，就屏蔽一切点击事件。
    //up-down的坐标，判断是否是滑动，如果是，则屏蔽一切点击事件
    private PointF mFirstP = new PointF();
    private int mScaleTouchSlop;//为了处理单击事件的冲突
    private final int moveDistanceX = 10;
    private final int moveDistanceY = 30;

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isUserSwiped = false;
//                //将按下时的坐标存储
                downX = x;
                downY = y;
                mLastP.set(event.getRawX(), event.getRawY());
                mFirstP.set(event.getRawX(), event.getRawY());
                if (imageViews.size() > 3) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                float gap = mLastP.x - event.getRawX();

                if (imageViews.size() > 3) {//解决 新热榜推荐数据侧滑 冲突
                    getParent().requestDisallowInterceptTouchEvent(true);
                }

                //为了在水平滑动中禁止父类ListView等再竖直滑动
                if (Math.abs(gap) > moveDistanceX || Math.abs(getScrollX()) > moveDistanceX) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }

                float gapY = mLastP.y - event.getRawY();
                if (Math.abs(gapY) > moveDistanceY || Math.abs(getScrollY()) > moveDistanceY) {
                    return true;
                }

                mLastP.set(event.getRawX(), event.getRawY());
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL: {
                if (Math.abs(event.getRawX() - mFirstP.x) > mScaleTouchSlop) {
                    isUserSwiped = true;
                }
            }
            break;
        }
        return super.dispatchTouchEvent(event);
    }

    public ImageListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImageListView(Context context) {
        this(context, null, 0);
    }


    private void initView(Context context, AttributeSet attrs) {
        this.context = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.imageBoxView);
        mScaleTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        screenWidth = display.getWidth();
        if (childWidth == null) {
            childWidth = (screenWidth - screenWidth / 6) / 3;
        }
        glideUtil = new GlideUtil((Activity) context);
        post(new Runnable() {
            @Override
            public void run() {
                if (getLayoutParams().height == 0) {
                    getLayoutParams().height = childWidth.intValue();
                }
            }
        });
        typedArray.recycle();
    }

    public void addImageList(final ArrayList<String> list) {
        removeAllViews();
        Float width = 0F;
        if (list.size() == 2) {
            width = (screenWidth - Utils.d2p(12)) / 2;
        } else if (list.size() == 3) {
            width = (screenWidth - Utils.d2p(12)) / 3;
        } else if (childWidth > 0) {
            width = childWidth;
        } else {
            width = Float.valueOf(Utils.d2p(100));
        }

        LinearLayout.LayoutParams mainLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(mainLayoutParams);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setPadding(Utils.d2p(6), 0, Utils.d2p(5), 0);
        for (int i = 0; i < list.size(); i++) {
            RelativeLayout relativeLayout = new RelativeLayout(context);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width.intValue(), width.intValue());
            layoutParams.topMargin = 6;
            layoutParams.bottomMargin = 6;
            relativeLayout.setLayoutParams(layoutParams);
            relativeLayout.setBackgroundResource(R.drawable.image_holo_light_frame);
            final ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(params);
            relativeLayout.addView(imageView);
            String loadUrl = list.get(i);
            if (list.size() < 4) {
                loadUrl = ImageUtils.getImageUrl(loadUrl, width.intValue());
            } else {
                loadUrl = ImageUtils.getSmallUrl(loadUrl);
            }
            glideUtil.setImageList(loadUrl, imageView);
            final int finalI = i;
            relativeLayout.setOnClickListener(v -> {
                String path = list.get(finalI);
                if (TextUtils.isEmpty(path)) {
                    ToastManager.showMessage(context, context.getResources().getString(R.string.toast_image_list_null));
                    return;
                }
                imageEvent.onClick(path, finalI, imageView);
            });
            linearLayout.addView(relativeLayout);
            imageViews.add(imageView);
        }
        addView(linearLayout);
    }


    private onClickImageEvent imageEvent;

    public void setImageEvent(onClickImageEvent imageEvent) {
        this.imageEvent = imageEvent;
    }

    public interface onClickImageEvent {
        void onClick(String url, int position, ImageView imageView);
    }


}