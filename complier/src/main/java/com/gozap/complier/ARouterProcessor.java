package com.gozap.complier;

import com.google.auto.service.AutoService;
import com.gozap.annotation.ARouter;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

/**
 * Create by liuxue on 2020/11/13 0013.
 * description:
 */
@AutoService(Process.class)
//开启服务 监听编译器干活


class ARouterProcessor extends AbstractProcessor {
    Element elementTool;

    //使用了注解 在编译器调用
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnv) {
        //编译期的时候，扫描整个项目，你到底使用了ARout注解了吗
        if (set.isEmpty()) {
            //
            return false;
        }
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(ARouter.class);
        //遍历所有使用注解的地方 获取信息
        for (Element element : elements) {
            elementTool.
        }


        return true;
    }


}
