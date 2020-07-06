package com.example.app.video;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.view.View;

public class MyAsyncTask extends AsyncTask<Bitmap, Void, Bitmap> {
    private View view;

    public MyAsyncTask(View view) {
        this.view = view;
    }

    @Override
    protected Bitmap doInBackground(Bitmap... params) {
        Bitmap bitmap = null;
        int scaleRatio = 10;
        int blurRadius = 10;
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(params[0],
                params[0].getWidth() / scaleRatio,
                params[0].getHeight() / scaleRatio,
                false);
        bitmap = FastBlur.doBlur(scaledBitmap, blurRadius, true);
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
//            view.setAlpha(.2f);
        view.setBackgroundDrawable(new BitmapDrawable(result));
        super.onPostExecute(result);
    }
}
