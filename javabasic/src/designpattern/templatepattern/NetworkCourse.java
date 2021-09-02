package designpattern.templatepattern;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package designpattern.templatepattern
 * @Description:
 * @Date: 2021/8/6 14:27
 */
public abstract class NetworkCourse {
    protected final void createCourse() {
        //发布预习资料
        this.postPreResource();
        //制作课件ppt
        this.createPPT();
        //在线直播
        this.liveVideo();
        //提交课堂笔记
        this.postNote();
        //提交源码
        this.postSource();
        //布置作业，有些课程是没有作业的
        if (this.needHomework()) {
            checkHomeWork();
        }
    }

    abstract void checkHomeWork();

    protected boolean needHomework() {
        return false;
    }

    final void postSource() {
        System.out.println("提交源代码");
    }

    final void postNote() {
        System.out.println("提交课件和笔记");
    }

    final void liveVideo() {
        System.out.println("直播授课");
    }

    final void createPPT() {
        System.out.println("创建备课PPT");
    }

    final void postPreResource() {
        System.out.println("分发预习资料");
    }
}
