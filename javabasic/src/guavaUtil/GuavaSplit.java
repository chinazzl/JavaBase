package guavaUtil;

import com.google.common.base.Splitter;

import java.util.List;

/**
 * @author Julyan
 * @version V1.0
 * @Date: 2022/12/8 23:25
 * @Description:
 */
public class GuavaSplit {
    public static void main(String[] args) {
        String s = " a,s,d,f ";
        List<String> strings = Splitter.on(",").trimResults().splitToList(s);
        System.out.println(strings);

        String s1 = "the , quick, brown     , fox,      jumps, over,  the, lazy, little dog.";
        System.out.println(Splitter.on(",").omitEmptyStrings().split(s1));

    }
}
