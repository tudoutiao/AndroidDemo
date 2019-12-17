package com.example.myapplication.fragment;

import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.myapplication.MyAsyncTask;
import com.example.myapplication.R;

import cn.jzvd.JZUtils;

public class VideoPlayerFragment extends Fragment implements View.OnClickListener {

    private View mainView;
    private TextureView textureView;
    private SeekBar seekBar;
    private ProgressBar progressBar;
    private ImageView imgThumb;
    private ImageView imgBack;
    private TextView tvTime;
    private Button btnPlayOrPause;
    private MediaPlayer mediaPlayer;
    private SurfaceTexture surfaceTexture;
    public HandlerThread mMediaHandlerThread;
    public Handler mMediaHandler;

    public static VideoPlayerFragment newInstance() {
        VideoPlayerFragment fragment = new VideoPlayerFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (null == mainView) {
            mainView = inflater.inflate(R.layout.fragment_video_player, container, false);
        }
        initView();
        return mainView;
    }

    private void initView() {
        textureView = mainView.findViewById(R.id.texture_view);
        imgThumb = mainView.findViewById(R.id.thumb);
        imgBack = mainView.findViewById(R.id.back);
        progressBar = mainView.findViewById(R.id.progress);
        btnPlayOrPause = mainView.findViewById(R.id.play);
        btnPlayOrPause.setOnClickListener(this);
        tvTime = mainView.findViewById(R.id.time);
        textureView.setSurfaceTextureListener(surfaceTextureListener);
        seekBar = mainView.findViewById(R.id.seekbar);
        seekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        Glide.with(getActivity()).asBitmap()
                .load("http://img3.chouti.com/CHOUTI_191205_ECEDC3FB7EE049C7AD2FDD5E140FE2D1.jpg").listener(new RequestListener<Bitmap>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                new MyAsyncTask(imgBack).execute(resource);
                return false;
            }
        }).into(imgThumb);

    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            progressBar.setVisibility(View.GONE);
            imgThumb.setVisibility(View.GONE);
        }
    };

    private Runnable mTicker = new Runnable() {
        @Override
        public void run() {
            mMediaHandler.postDelayed(mTicker, 200);
            if (null != mediaPlayer && mediaPlayer.isPlaying()) {
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
            }

        }
    };

    private SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (null != mediaPlayer) {
                tvTime.setText(JZUtils.stringForTime(mediaPlayer.getCurrentPosition()));
            }

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            if (null != mediaPlayer && mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            }
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            if (null != mediaPlayer && mediaPlayer.isPlaying()) {
                mediaPlayer.seekTo(seekBar.getProgress());
            } else {
                mediaPlayer.seekTo(seekBar.getProgress());
                mediaPlayer.start();
            }
        }
    };

    private TextureView.SurfaceTextureListener surfaceTextureListener = new TextureView.SurfaceTextureListener() {
        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
            if (null == surfaceTexture) {
                surfaceTexture = surface;
                prepare();
            } else {
                textureView.setSurfaceTexture(surfaceTexture);
            }

        }

        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

        }

        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
            return false;
        }

        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surface) {

        }
    };

    private void prepare() {
        release();
        mMediaHandlerThread = new HandlerThread("Media");
        mMediaHandlerThread.start();
        mMediaHandler = new Handler(mMediaHandlerThread.getLooper());
        mMediaHandler.post(() -> {
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.start();
                        seekBar.setMax(mediaPlayer.getDuration());
                        mMediaHandler.post(mTicker);
                        mHandler.sendEmptyMessage(0);
                    }
                });
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {

                    }
                });
                mediaPlayer.setScreenOnWhilePlaying(true);
                mediaPlayer.setDataSource("http://img3.chouti.com/74173b84-5cbb-4a25-a7a7-76eaa4f3dafc.mp4");
                mediaPlayer.prepareAsync();
                mediaPlayer.setSurface(new Surface(surfaceTexture));

            } catch (Exception e) {

            }
        });

    }


    public void release() {//not perfect change you later
        if (mMediaHandler != null && mMediaHandlerThread != null && mediaPlayer != null) {//不知道有没有妖孽
            HandlerThread tmpHandlerThread = mMediaHandlerThread;
            MediaPlayer tmpMediaPlayer = mediaPlayer;
            surfaceTexture = null;

            mMediaHandler.post(() -> {
                tmpMediaPlayer.setSurface(null);
                tmpMediaPlayer.release();
                tmpHandlerThread.quit();
            });
            mediaPlayer = null;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play: {
                if (null != mediaPlayer && mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                } else if (null != mediaPlayer) {
                    mediaPlayer.start();
                }
                break;
            }
        }
    }


}
