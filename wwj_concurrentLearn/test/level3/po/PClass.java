package level3.po;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package level3.po
 * @Description:
 * @Date: 2021/11/8 10:33
 */
public class PClass {

    public String name ;
    public Son son;

    public PClass() {
        name = "123";
    }

    public Son getSon() {
        return son;
    }
    public void setSon(Son son) {
        this.son = son;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
