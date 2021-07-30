package src.utilstest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.my.booter.Runner;
import org.my.booter.utils.imageUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Runner.class)
public class UtilsTest {

    @Test
    public void copyImgTest(){
        imageUtils imageUtils = new imageUtils();
        String s = imageUtils.uploadUtil("C:\\Users\\lenovo\\Desktop\\MOMP.JPG");
        System.out.println(s);
    }
}
