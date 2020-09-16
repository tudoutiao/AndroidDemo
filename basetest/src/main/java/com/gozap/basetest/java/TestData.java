package com.gozap.basetest.java;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * Create by liuxue on 2020/8/28 0028.
 * description:
 */
class TestData {
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String[] args) {
        List<String> list=new ArrayList<>();
        Set<String> set = new HashSet<>();
        Map<String,String > map = new HashMap<>();
        for (String i:map.keySet()){

        }
        Set<String > linkedHashSet=new LinkedHashSet<>();
        set.add("aa");
        set.add("bb");
        set.forEach(it -> {
            System.out.println(it);
        });

        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    //==============================hashtable=====================================
    static void testHashTable() {
        // Create a hash map
        Hashtable balance = new Hashtable();
        Enumeration names;
        String str;
        double bal;

        balance.put("Zara", new Double(3434.34));
        balance.put("Mahnaz", new Double(123.22));
        balance.put("Ayan", new Double(1378.00));
        balance.put("Daisy", new Double(99.22));
        balance.put("Qadir", new Double(-19.08));

        // Show all balances in hash table.
        names = balance.keys();
        while (names.hasMoreElements()) {
            str = (String) names.nextElement();
            System.out.println(str + ": " +
                    balance.get(str));
        }
        System.out.println();
        // Deposit 1,000 into Zara's account
        bal = ((Double) balance.get("Zara")).doubleValue();
        balance.put("Zara", new Double(bal + 1000));
        System.out.println("Zara's new balance: " +
                balance.get("Zara"));
    }

    //==============================stack=======================================
    static void test(int x) {
        Stack<Integer> s1 = new Stack<Integer>();
        Stack<Integer> s2 = new Stack<Integer>();
        s1.push(x);
        s2.push(x);
        int p1 = s1.peek();
        int p2 = s2.peek();
        System.out.println(p1 == p2);
        System.out.println(s1.peek() == s2.peek());
    }


    static void testStack() {
        Stack<String> stringStack = new Stack<>();
        System.out.println(stringStack);
        showPush(stringStack, "a");
        showPush(stringStack, "b");
        showPush(stringStack, "c");
        showPush(stringStack, "d");
        showPush(stringStack, "e");

        showPop(stringStack);
        showPop(stringStack);
        showPop(stringStack);
        showPop(stringStack);
        showPop(stringStack);
        try {
            showPop(stringStack);
        } catch (Exception e) {
            System.out.println("empty stack");
        }
    }


    static void showPush(Stack<String> st, String s) {
        System.out.print("push->");
        st.push(s);
        System.out.println(st);
    }

    static void showPop(Stack<String> st) {
        System.out.print("pop->");
        System.out.println(st);
    }

}


