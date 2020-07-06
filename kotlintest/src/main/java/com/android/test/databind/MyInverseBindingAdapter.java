package com.android.test.databind;

import android.widget.RatingBar;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;

/**
 * Create by liuxue on 2020/4/29 0029.
 * description:
 */
public class MyInverseBindingAdapter {
    @BindingAdapter("android:rt")
    public static void setRt(RatingBar rating, float value) {
        if (rating == null) return;
        if (rating.getRating() == value) return;
        rating.setRating(value);
    }

    @InverseBindingAdapter(attribute = "android:rt", event = "android:ratingAttrChanged")
    public static float getRt(RatingBar view) {
        if (view == null) return 0f;
        return view.getRating();
    }

    @BindingAdapter(value = {"android:onRatingChanged", "android:ratingAttrChanged"}, requireAll
            = false)
    public static void setListener(RatingBar view, final RatingBar.OnRatingBarChangeListener listener,
                                   final InverseBindingListener attrChangedListener){
        if(attrChangedListener == null){
            view.setOnRatingBarChangeListener(listener);
        }else{
            view.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    if(listener != null){
                        listener.onRatingChanged(ratingBar,rating,fromUser);
                    }
                    attrChangedListener.onChange();
                }
            });

        }
    }
}
