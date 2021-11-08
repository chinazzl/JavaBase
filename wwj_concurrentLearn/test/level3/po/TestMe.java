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

}
