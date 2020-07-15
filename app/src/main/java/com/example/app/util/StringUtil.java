package com.example.app.util;

import android.content.res.AssetManager;

import com.example.app.App;
import com.example.app.refresh.Link;
import com.example.app.refresh.RefrshSubject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Create by liuxue on 2020/7/13 0013.
 * description:
 */
public class StringUtil<T> {


    public static <T> T fromJson(String json, Class<T> type) {
        Gson gson = new Gson();
        return gson.fromJson(json, type);

    }

    public static <T> List<T> listFromJson(String json, Type type) {
        Gson gson = new Gson();
        return gson.fromJson(json, type);
    }

    public static List<RefrshSubject> getRefreshHints() {
        String json = readJsonFile("refresh_hints.json", "resp");
        return listFromJson(json, new TypeToken<List<RefrshSubject>>() {
        }.getType());
    }

    public static List<Link> getLinks() {
        String json = readJsonFile("link.json", "links");
        return listFromJson(json, new TypeToken<List<Link>>() {
        }.getType());
    }


    public static String readJsonFile(String fileName, String params) {
        //将json数据变成字符串
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //获取assets资源管理器
            AssetManager assetManager = App.Companion.getContext().getAssets();
            //通过管理器打开文件并读取
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
            JSONObject jsonObject = new JSONObject(stringBuilder.toString());
            return jsonObject.optString(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
