package com.gozap.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Create by liuxue on 2020/11/13 0013.
 * description:
 */
@Target(ElementType.TYPE)
public//作用区域  类

@interface ARouter {
    String path = "";
    String group=null;
}
