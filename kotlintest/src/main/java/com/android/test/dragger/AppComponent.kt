package com.android.test.dragger

import dagger.Component

/**
 * Create by liuxue on 2020/4/29 0029.
 * description:
 */

@Component(modules = [MainModule::class, MainModule2::class])
interface MainComponent {
    //第三步  写一个方法 绑定Activity /Fragment
    fun inject(activity: DaggerActivity?)
}
