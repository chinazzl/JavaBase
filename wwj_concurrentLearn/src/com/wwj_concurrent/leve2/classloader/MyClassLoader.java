package com.wwj_concurrent.leve2.classloader;

import java.io.ByteArrayOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.leve2.classloader
 * @Description: 自定义类加载器 必须是 classLoader 的直接或者间接子类
 * @Date: 2021/6/16 14:52
 */
public class MyClassLoader extends ClassLoader {

    //定义默认的class存放路径
    private final static Path DEFULT_CLASS_DIR = Paths.get("D:", "classloader1");

    private final Path classDir;

    //使用默认class路径
    public MyClassLoader() {
        super();
        this.classDir = DEFULT_CLASS_DIR;
    }

    //指定class路径的同时，指定父类加载器
    public MyClassLoader(ClassLoader parent, String classDir) {
        super(parent);
        this.classDir = Paths.get(classDir);
    }

    //允许传入指定路径的class路径
    public MyClassLoader(String classDir) {
        super();
        this.classDir = Paths.get(classDir);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //读取class的二进制的数据
        byte[] classBytes = readClassBytes(name);
        if (classBytes == null || classBytes.length > 0) {
            throw new ClassNotFoundException("Can not load the class " + name);
        }
        //调用 defineClass 方法定义class
        return this.defineClass(name,classBytes,0,classBytes.length);
    }

    //将class文件读入内存
    private byte[] readClassBytes(String name) throws ClassNotFoundException {
        String classPath = name.replace(".", "/");
        Path classFullPath = classDir.resolve(Paths.get(classPath + ".class"));
        if (!classFullPath.toFile().exists()) {
            throw new ClassNotFoundException("The class " + "name " + "not found.");
        }
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            Files.copy(classFullPath, bos);
            return bos.toByteArray();
        } catch (Exception e) {
            throw new ClassNotFoundException("load the class " + "name " + "occur error.", e);
        }
    }
}
