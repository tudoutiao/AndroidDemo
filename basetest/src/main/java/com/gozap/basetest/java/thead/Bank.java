package com.gozap.basetest.java.thead;

import androidx.annotation.Nullable;

/**
 * Create by liuxue on 2020/9/4 0004.
 * description:
 */
class Bank {
    //    private int count = 0;
    private static ThreadLocal<Integer> count = new ThreadLocal<Integer>() {
        @Nullable
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    //存钱
    public void addMoney(int money) {
        count.set(count.get() + money);
        System.out.println(System.currentTimeMillis() + "存进：" + money);
    }

    //取钱
    public void subMoney(int money) {
        if (count.get() - money < 0) {
            System.out.println("余额不足");
            return;
        }
        count.set(count.get() - money);
        System.out.println(+System.currentTimeMillis() + "取出：" + money);
    }

    //查询
    public void lookMoney() {
        System.out.println("账户余额：" + count.get());
    }

}
