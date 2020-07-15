package com.example.app.refresh;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class RefrshSubject implements Serializable {
    private String enable;
    private String content;
    private static final String TAG = "RefrshSubject";
    private int id;

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public RefrshSubject copy() {
        RefrshSubject subject = new RefrshSubject();
        subject.id = id;
        subject.enable = enable;
        subject.content = content;
        return subject;
    }

    public RefrshSubject() {
        super();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof RefrshSubject) {
            if (this.id == ((RefrshSubject) obj).getId()) {
                return true;
            }
        }
        return false;
    }

    public void parseJson(JSONObject jsonObject) {
        if (jsonObject != null) {
            id = jsonObject.optInt("id", id);
            enable = jsonObject.optString("enable", enable);
            content = jsonObject.optString("content", content);
        }
    }

    public JSONObject buildJson() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", id);
            jsonObject.put("enable", enable);
            jsonObject.put("content", content);
        } catch (JSONException e) {
        }
        return jsonObject;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
