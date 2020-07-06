package com.example.app.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.app.R;

public class TabItem extends FrameLayout {
    private Context mContext;
    private ImageView icon;
    private TextView textView;
    private int mDefaultDrawable;
    private int mCheckedDrawable;
    private String title;
    private boolean isCheck;

    public TabItem(Context context) {
        super(context);
        init(context, null);
    }

    public TabItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }


    public TabItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        this.mContext = context;
        final TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.TabItem);
        if (a.hasValue(R.styleable.TabItem_title)) {
            title = a.getString(R.styleable.TabItem_title);
        }
        isCheck = a.getBoolean(R.styleable.TabItem_isCheck, false);
        mDefaultDrawable = a.getResourceId(R.styleable.TabItem_defaultDrawable, R.mipmap.ic_launcher);
        mCheckedDrawable = a.getResourceId(R.styleable.TabItem_checkDrawable, R.mipmap.ic_launcher);
        LayoutInflater.from(context).inflate(R.layout.tab_item_layout, this);
        icon = findViewById(R.id.icon);
        textView = findViewById(R.id.title);
        textView.setText(title);
        setChecked(isCheck);
        a.recycle();
    }

    public void setChecked(boolean checked) {
        if (checked) {
            icon.setImageResource(mCheckedDrawable);
            textView.setTextColor(getResources().getColor(R.color.tab_check_color));
        } else {
            icon.setImageResource(mDefaultDrawable);
            textView.setTextColor(getResources().getColor(R.color.tab_default_color));
        }
    }

}
