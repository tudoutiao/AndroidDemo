package com.example.app.video;


import android.content.res.AssetManager;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;
import cn.jzvd.VideoInfo;

/**
 * 视频列表滑动自动播放
 */
public class MainFragment extends Fragment {


    public MainFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        MainFragment mainFragment = new MainFragment();
        return mainFragment;
    }

    private View mainView;
    private RecyclerView recyclerView;
    private VideoAdapter videoAdapter;
    private LinearLayoutManager layoutManager;

    private List<VideoInfo> videoInfos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (null == mainView) {
            mainView = inflater.inflate(R.layout.fragment_main, container, false);
        }
        recyclerView = mainView.findViewById(R.id.recycler_view);
        videoAdapter = new VideoAdapter(getActivity());
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(videoAdapter);
        initData();
        initView();
        return mainView;
    }

    private void initView() {
        recyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(@NonNull View view) {

            }

            @Override
            public void onChildViewDetachedFromWindow(@NonNull View view) {

            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                Jzvd currentJzvd = Jzvd.CURRENT_JZVD;
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:
                        if (currentJzvd == null || currentJzvd.state == Jzvd.STATE_NORMAL) {
                            videoAdapter.setScrolling(false);
                            return;
                        }
                        videoAdapter.setScrolling(true);
                        autoPlayVideoView(recyclerView);
                        break;
                    default: {
                        videoAdapter.setScrolling(true);
                    }
                    break;
                }
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                getRecycleVisibleItems();
            }
        });

    }

    private int lastItemPosition = 0, firstItemPosition = 0, visibleCount = 0;
    private static boolean isOnchange = false;
    private long systemTime;

    synchronized void getRecycleVisibleItems() {
        if (null == recyclerView || null == videoInfos || videoInfos.size() == 0) {
            return;
        }

        //获取最后一个可见view的位置
        lastItemPosition = layoutManager.findLastVisibleItemPosition();
        //获取第一个可见view的位置
        firstItemPosition = layoutManager.findFirstVisibleItemPosition();
        //可以见item数量
        visibleCount = lastItemPosition - firstItemPosition + 1;

        long tempTime = System.currentTimeMillis();
        if (isOnchange || tempTime - systemTime > 500) {
            systemTime = tempTime;
            isOnchange = false;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    autoPlayVideoView(recyclerView);
                }
            }, 200);
        }

    }


    synchronized void autoPlayVideoView(RecyclerView view) {
        Jzvd currentJzvd = Jzvd.CURRENT_JZVD;
        VideoInfo itemInfo = null;
        if (currentJzvd != null && null != currentJzvd.jzDataSource) {
            if (currentJzvd.state == Jzvd.STATE_PLAYING || currentJzvd.state == Jzvd.STATE_PREPARING) {//当前存在mp4播放
                itemInfo = currentJzvd.jzDataSource.videoInfo;
            }
        }

        if (null != currentJzvd && currentJzvd.screen == Jzvd.SCREEN_FULLSCREEN) {
            return;
        }


        JzvdStd jzvdStd = null;
        float maxPercent = 0;
        for (int i = 0; i < visibleCount; i++) {
            if (view != null && view.getChildAt(i) != null && view.getChildAt(i).findViewById(R.id.videoplayer) != null) {
                JzvdStd videoPlayerStandard = (JzvdStd) view.getChildAt(i).findViewById(R.id.videoplayer);

                if (null == videoPlayerStandard.jzDataSource || null == videoPlayerStandard.jzDataSource.videoInfo)
                    continue;

                Rect rect = new Rect();
                videoPlayerStandard.getLocalVisibleRect(rect);
                int videoheight = videoPlayerStandard.getHeight();
                if (rect.top == 0 && rect.bottom == videoheight) {
                    jzvdStd = videoPlayerStandard;
                    // 借助跳出循环，达到只处理可见区域内的第一个播放器
                    break;
                }
            }
        }

        if (null != itemInfo && null != jzvdStd && jzvdStd.jzDataSource.videoInfo.getId() == itemInfo.getId()) {
            return;
        }

        if (null != jzvdStd && null != jzvdStd.jzDataSource && null != jzvdStd.jzDataSource.videoInfo) {
            if (jzvdStd.state == Jzvd.STATE_NORMAL ||
                    jzvdStd.state == Jzvd.STATE_ERROR) {
                Jzvd.releaseAllVideos();
                jzvdStd.startButton.performClick();
            }
        }
    }


    private void initData() {
        //将json数据变成字符串
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //获取assets资源管理器
            AssetManager assetManager = getActivity().getAssets();
            //通过管理器打开文件并读取
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open("videoInfo.json")));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
            JSONObject jsonObject = new JSONObject(stringBuilder.toString());
            videoInfos = new Gson().fromJson(jsonObject.optString("list"), new TypeToken<List<VideoInfo>>() {
            }.getType());

            videoAdapter.setVideoInfos(videoInfos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }
}
