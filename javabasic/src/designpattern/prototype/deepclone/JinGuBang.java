package designpattern.prototype.deepclone;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package designpattern.prototype.deepclone
 * @Description:
 * @Date: 2021/7/8 17:05
 */
public class JinGuBang implements Serializable {
    private float h = 3000;

    private float w = 20000;

    private Date birthDate = new Date();

    public void big() {
        this.h *= 2;
        this.w *= 2;
    }

    public void small() {
        this.h /= 2;
        this.w /= 2;
    }
}
