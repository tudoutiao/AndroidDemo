package com.android.test.databind;

import android.widget.RatingBar;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.InverseBindingMethod;
import androidx.databinding.InverseBindingMethods;

/**
 * Create by liuxue on 2020/4/28 0028.
 * description:
 */

@InverseBindingMethods(value = {
        @InverseBindingMethod(
                type = RatingBar.class,
                attribute = "android:rating"
        )
})

public class MyInverseBindingMethods {

    @BindingAdapter("android:rating")
    public static void setRating(RatingBar view, float value) {
        if (view == null) {
            return;
        }

        float rating = view.getRating();

        if (rating == value) {
            return;
        }

        //model->view
        view.setRating(value);
    }

    @BindingAdapter(value = {"android:onRatingChanged", "android:ratingAttrChanged"}, requireAll = false)
    public static void setListener(RatingBar view,
                                   final RatingBar.OnRatingBarChangeListener listener,
                                   final InverseBindingListener attrListener) {
        if (attrListener == null) {
            view.setOnRatingBarChangeListener(listener);
        } else {

            //view->model
            view.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                    if (listener != null) {
                        listener.onRatingChanged(ratingBar, rating, fromUser);
                    }
                    attrListener.onChange();
                }
            });
        }
    }
}
