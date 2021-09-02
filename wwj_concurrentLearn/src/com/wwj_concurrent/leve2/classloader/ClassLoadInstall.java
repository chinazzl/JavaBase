package com.wwj_concurrent.leve2.classloader;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.wwj_concurrent.leve2.classloader.DrivingLoading
 * @Description: class 初始化顺序
 * 1. 加载：加载二进制文件，class文件
 * 2. 连接
 *  <p>a. 验证</p>}
 *  <p>b. 给具体的属性赋予默认值</p>
 *  <p>c. 把类中的符号引用转换为直接引用。</p>
 * 3. 初始化: 为类的静态变量赋予正确的初始值（代码编写阶段给定的值）</p>
 * @Date: 2021/6/9 17:01
 */
public class ClassLoadInstall {

    //b
    private static ClassLoadInstall instance = new ClassLoadInstall();

    private static int x = 0;

    private static int y;

    //a
//    private static ClassLoadInstall instance = new ClassLoadInstall();

    public ClassLoadInstall() {
        x++;
        y++;
    }

    public static ClassLoadInstall getInstance() {
        return instance;
    }

    /**
     * a:
     *  1. 连接阶段 x = 0,y = 0;
     *  2. instance 初始化
     *  3. 初始化阶段 x = 1, y =1;
     * b:
     *  1. 连接阶段 instance = null, x = 0, y = 0;
     *  2.  直接引用 instance = new ClassLoadInstall(), x = 1, y = 1;
     *  3. 初始化阶段 赋给正确的值 x = 0, y =1;
     *
     * @param args
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoadInstall instance = ClassLoadInstall.getInstance();
        System.out.println(instance.x);
        System.out.println(instance.y);
    }
}
