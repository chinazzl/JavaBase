package level3.po;

import org.junit.Test;

import java.lang.reflect.Field;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package level3.po
 * @Description:
 * @Date: 2021/10/18 17:12
 */
public class TestMe {

    @Test
    public void testField() throws NoSuchFieldException, IllegalAccessException {
        Field field = PClass.class.getDeclaredField("name");
//        field.setAccessible(true);
        String o = (String) field.get(new PClass());
        System.out.println(o);
    }

    @Test
    public void testClass() {
        Son son = new Son();
        son.setName("son");
        PClass p = new PClass();
        p.setName("p1");
        p.setSon(son);
        System.out.println("================================");

        Son son1 = p.getSon();
        son1.setName("fff");
        System.out.println(p.getName());

        System.out.println(p.getSon().getName());

    }

}
