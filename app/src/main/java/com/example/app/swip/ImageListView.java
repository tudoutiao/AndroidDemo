package com.example.app.swip;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PointF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.app.R;
import com.example.app.util.Utils;

import java.util.ArrayList;

/**
 * 多图列表
 */
public class ImageListView extends HorizontalScrollView {
    private Context context;
    private ArrayList<ImageView> imageViews = new ArrayList<>();

    private float screenWidth;
    private Float childWidth;

    public ImageListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context, attrs);
    }

    private float downX;    //按下时 的X坐标
    private float downY;    //按下时 的Y坐标
    //上一次的xy
    private PointF mLastP = new PointF();
    //2016 11 03 add,判断手指起始落点，如果距离属于滑动了，就屏蔽一切点击事件。
    //up-down的坐标，判断是否是滑动，如果是，则屏蔽一切点击事件
    private PointF mFirstP = new PointF();
    private int mScaleTouchSlop;//为了处理单击事件的冲突
    private final int moveDistanceX = 10;
    private final int moveDistanceY = 30;
    private int swipMoveDistanceX;

    private boolean isShowMenu = false;

    public void setShowMenu(boolean showMenu) {
        isShowMenu = showMenu;
    }


    float mFirstRawX;

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("---swip", "imageList___dispatchTouchEvent down");
                mFirstRawX = event.getRawX();
//                //将按下时的坐标存储
                downX = event.getX();
                downY = event.getY();
                mLastP.set(event.getRawX(), event.getRawY());
                mFirstP.set(event.getRawX(), event.getRawY());
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("---swip", "imageList___dispatchTouchEvent move : " + getScrollX() + "--" + event.getRawX() + "--" + mFirstRawX + "--" + swipMoveDistanceX);

                mLastP.set(event.getRawX(), event.getRawY());

                float gap = mFirstRawX - event.getRawX();
                if (Math.abs(getScrollX()) < swipMoveDistanceX) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                } else {
                    if (isShowMenu) {
                        getParent().requestDisallowInterceptTouchEvent(false);
                    } else {
                        if (gap > 0) {//右滑
                            getParent().requestDisallowInterceptTouchEvent(false);
                        } else if (gap < 0) {//左滑
                            getParent().requestDisallowInterceptTouchEvent(true);
                        }
                    }
                }
                mFirstRawX = event.getRawX();

                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL: {
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
            }
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
        post(() -> {
            if (getLayoutParams().height == 0) {
                getLayoutParams().height = childWidth.intValue();
            }
        });
        typedArray.recycle();
    }

    public void addImageList(final ArrayList<String> list) {
        removeAllViews();
        Float width = 0F;
        if (list.size() == 2) {
            width = (screenWidth - new Utils().d2p(context, 12)) / 2;
        } else if (list.size() == 3) {
            width = (screenWidth - new Utils().d2p(context, 12)) / 3;
        } else if (childWidth > 0) {
            width = childWidth;
        } else {
            width = Float.valueOf(new Utils().d2p(context, 100));
        }

        LinearLayout.LayoutParams mainLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(mainLayoutParams);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setPadding(new Utils().d2p(context, 6), 0, new Utils().d2p(context, 5), 0);
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
            new Utils().setImageList(loadUrl, imageView, context);
            final int finalI = i;
            relativeLayout.setOnClickListener(v -> {
                String path = list.get(finalI);
                if (TextUtils.isEmpty(path)) {
                    Toast.makeText(context, "图片异常无法显示", Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(context, "点击图片放大显示", Toast.LENGTH_SHORT).show();
            });
            linearLayout.addView(relativeLayout);
            imageViews.add(imageView);
        }
        addView(linearLayout);

        int view_width = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int height = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        linearLayout.measure(view_width, height);
        linearLayout.getMeasuredWidth(); // 获取宽度
        swipMoveDistanceX = (linearLayout.getMeasuredWidth() - new Utils().getScreenWidthPx(context));
    }


}