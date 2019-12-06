package com.example.myapplication.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;
import cn.jzvd.VideoInfo;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoHolder> {

    private Context mContext;
    private List<VideoInfo> videoInfos = new ArrayList<>();

    public VideoAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setVideoInfos(List<VideoInfo> videoInfos) {
        this.videoInfos = videoInfos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VideoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        VideoHolder viewHolder = new VideoHolder(LayoutInflater.from(mContext).inflate(R.layout.item_videoview, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VideoHolder holder, int position) {
        VideoInfo videoInfo = videoInfos.get(position);
        holder.jzvdStd.setUp(videoInfo.getVideoUrl(),
                "position: " + position,
                Jzvd.SCREEN_NORMAL);
        Glide.with(mContext).asBitmap()
                .load(videoInfo.getVideoImgUrl()).listener(new RequestListener<Bitmap>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                new MyAsyncTask(holder.jzvdStd.jzLayout).execute(resource);
                return false;
            }
        }).into(holder.jzvdStd.thumbImageView);

    }

    @Override
    public int getItemCount() {
        return videoInfos.size();
    }

    class VideoHolder extends RecyclerView.ViewHolder {
        JzvdStd jzvdStd;

        public VideoHolder(@NonNull View itemView) {
            super(itemView);
            jzvdStd = itemView.findViewById(R.id.videoplayer);
        }
    }


    class MyAsyncTask extends AsyncTask<Bitmap, Void, Bitmap> {
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

}
