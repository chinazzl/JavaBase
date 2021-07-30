package designpattern.prototype.deepclone;

import favor.SE.duoTai.D;

import java.io.*;
import java.util.Date;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package designpattern.prototype.deepclone
 * @Description:
 * @Date: 2021/7/8 17:11
 */
public class QiTianDaSheng extends SunWuKong implements Cloneable, Serializable {

    public JinGuBang jinGuBang;

    public QiTianDaSheng() {
        this.birthDate = new Date();
        this.jinGuBang = new JinGuBang();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return this.deepClone();
    }

    private Object deepClone() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray())) {
            try (ObjectInputStream ois = new ObjectInputStream(bis)) {
                QiTianDaSheng copy = (QiTianDaSheng) ois.readObject();
                return copy;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public QiTianDaSheng shallowCopy(QiTianDaSheng target) {
        QiTianDaSheng qiTianDaSheng = new QiTianDaSheng();
        qiTianDaSheng.height = target.height;
        qiTianDaSheng.weight = target.weight;
        qiTianDaSheng.jinGuBang = target.jinGuBang;
        qiTianDaSheng.birthDate = new Date();
        return qiTianDaSheng;
    }
}
