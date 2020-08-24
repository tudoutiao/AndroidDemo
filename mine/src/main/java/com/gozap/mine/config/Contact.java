package com.gozap.mine.config;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**
 * Created by admin on 2016/9/18.
 */

public class Contact {
    private String index;
    private String name;
    private String number;
    private Bitmap contactPhoto;

    /**
     * 由于手机通讯录中的手机联系人可能从手机本地和SIM卡中获取
     * 所以需要去重，本类利用hashSet对list去重，需要重写hashCode
     * 方法
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Contact contact = (Contact) obj;
        if (!name.equals(contact.name)) return false;
        return number.equals(contact.number);
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + name.hashCode();
        result = 31 * result + number.hashCode();
        return result;
    }

    public Contact(String index, String name, String number, Bitmap contactPhoto) {
        this.index = index;
        this.name = name;
        this.number = number;
        this.contactPhoto = contactPhoto;
    }

    public String getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public Bitmap getContactPhoto() {
        return contactPhoto;
    }

    public static ArrayList<Contact> phoneInfoList = new ArrayList<>();

}
