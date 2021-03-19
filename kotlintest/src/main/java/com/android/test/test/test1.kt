package com.android.test.test

import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock

/**
 * Create by liuxue on 2020/11/26 0026.
 * description:
 */
internal class test1 {
    fun <T> lock(lock: Lock, body: () -> T): T {
        lock.lock()
        try {
            return body()
        } finally {
            lock.unlock()
        }
    }

    fun main() {
        lock(ReentrantLock()) {
            val a = 2 + 3
            val str = "aaa"
        }

        var a=0;
        a.let {  }
    }
}