package com.example.myapplication.fragment;


import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import cn.jzvd.Jzvd;
import cn.jzvd.VideoInfo;

/**
 * A simple {@link Fragment} subclass.
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

    private List<VideoInfo> videoInfos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (null == mainView) {
            mainView = inflater.inflate(R.layout.fragment_main, container, false);
        }
        recyclerView = mainView.findViewById(R.id.recycler_view);
        videoAdapter = new VideoAdapter(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
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
                if (recyclerView.getScrollState() != RecyclerView.SCROLL_STATE_IDLE) {
                    if (-1 == recyclerView.getChildAdapterPosition(view))
                        return;
                    Jzvd videoPlayer = Jzvd.CURRENT_JZVD;
                }
            }
        });


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

}
