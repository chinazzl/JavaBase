package designpattern.prototype.deepclone;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package designpattern.prototype.deepclone
 * @Description:
 * @Date: 2021/7/9 9:45
 */
public class deepCloneMain {
    public static void main(String[] args) throws CloneNotSupportedException {
        final QiTianDaSheng qiTianDaShengSimple = new QiTianDaSheng();
        QiTianDaSheng qiTianDaShengDeep = (QiTianDaSheng) qiTianDaShengSimple.clone();
        System.out.println("深克隆 ==> " + (qiTianDaShengDeep.jinGuBang.hashCode() == qiTianDaShengSimple.jinGuBang.hashCode()));

        QiTianDaSheng qiTianDaSheng = new QiTianDaSheng();
        QiTianDaSheng shallowCopy = qiTianDaShengSimple.shallowCopy(qiTianDaSheng);
        System.out.println("浅克隆 ==> " + (shallowCopy.jinGuBang.hashCode() == qiTianDaSheng.jinGuBang.hashCode()));
    }
}
