package com.wechat.jump.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 *
 */
public class Jump extends JFrame{

    int x0,y0,x1,y1;
    boolean flag = false;
    /*
    构造函数
     */
    public Jump(){
        super("hello wechat");
        //设置窗体大小
        this.setSize(270,605);
        //去除窗口
        this.setUndecorated(true);
        //设置窗体可见
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        //窗口透明
        this.setOpacity(0.6f);
        //设置置顶
        this.setAlwaysOnTop(true);
        //点击x退出程序
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //点击鼠标右键获取坐标

        //创建一个小面板
        JLabel jLabel = new JLabel();
        this.add(jLabel);
        //给jLabel添加一个监听
        jLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(e);
                //如果你点击右键
                if (e.getButton() == MouseEvent.BUTTON1){

                    if (!flag){
                        System.out.println("第一次");
                        x0 = e.getX();
                        y0 = e.getY();
                        flag = true;
                    }else{
                        x1 = e.getX();
                        y1 = e.getY();
                        flag = false;
                        double _x = x0-x1;
                        double _y = y0-y1;
                        double sentence = Math.sqrt(Math.pow(_x,2)+ Math.pow(_y,2));
                        System.out.println(sentence);
                        //定义adb 后面毫秒值乘以一个系数
                        String cmd = "adb shell input touchscreen swipe 180 187 180 187 " + Math.round(sentence*5);
                        Runtime run = Runtime.getRuntime();
                        try {
                            Process exec = run.exec(cmd);
                            System.out.println("执行adb shell 语句："+ "adb shell input touchscreen swipe ");
                            exec.waitFor();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                    }

                }

            }
        });

    }

    public static void main(String[] args) {
        new Jump();
    }
}
