package com.enjoy.routers;

import com.android.build.api.transform.DirectoryInput;
import com.android.build.api.transform.JarInput;
import com.android.build.api.transform.QualifiedContent;
import com.android.build.api.transform.Transform;
import com.android.build.api.transform.TransformException;
import com.android.build.api.transform.TransformInput;
import com.android.build.api.transform.TransformInvocation;
import com.android.build.gradle.internal.pipeline.TransformManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Create by liuxue on 2020/11/24 0024.
 * description:
 */
class RegisterTransform extends Transform {

    static List<String> registerList = new ArrayList<>();

    @Override
    public String getName() {
        return "com.enjoy.router";
    }

    /**
     * 配置这个Transform要处理得类型
     *
     * @return
     */
    @Override
    public Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS;
    }

    /**
     * 范围
     * 全部：依赖+jar+app模块所有得class
     *
     * @return
     */
    @Override
    public Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT;
    }

    @Override
    public boolean isIncremental() {
        return false;
    }

    @Override
    public void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        super.transform(transformInvocation);
        Collection<TransformInput> inputs = transformInvocation.getInputs();
        for (TransformInput input : inputs) {
            //获取class
            Collection<DirectoryInput> directoryInputs = input.getDirectoryInputs();//编译出得目录文件
            //获取jar包（jar包里就是class）
            Collection<JarInput> jarInputs = input.getJarInputs();//依赖得代码

            //从所有获取到的class记录 注册类得类名 以及获取到的Router.class
            processDirectory(directoryInputs);
        }
    }

    private void processDirectory(Collection<DirectoryInput> directoryInputs) {
        for(DirectoryInput input:directoryInputs){
            File file=input.getFile();

        }
    }
}
