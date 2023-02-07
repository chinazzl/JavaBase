package guavaUtil;

import com.google.common.base.Preconditions;

import java.util.Arrays;
import java.util.List;

/**
 * @author Julyan
 * @version V1.0
 * @Date: 2022/11/22 21:54
 * @Description: Preconditions提供静态方法来检查方法或构造函数，被调用是否给定适当的参数。它检查的先决条件。其方法失败抛出IllegalArgumentException。
 */
public class Precondition {

    public static void main(String[] args) {
        //System.out.println(abs(2.211));
        //System.out.println(checkElement(Arrays.asList(1, 2, 3)));
        //System.out.println(checkNull(2));
        List<Integer> list = Arrays.asList(1, 2, 3);
        System.out.println(checkPositionIndex(4, list.size()));
    }

    /**
     * 校验数组的位置信息不能超过position
     *
     * @param position
     * @param size
     * @return
     */
    public static int checkPositionIndex(int position, int size) {
        return Preconditions.checkPositionIndex(position, size, "位置信息无效");
    }

    /**
     * 判断非空
     *
     * @param value
     * @return
     */
    public static int checkNull(Integer value) {
        return Preconditions.checkNotNull(value, "The value must not be null.");
    }

    public static double abs(double input) {
        Preconditions.checkArgument(input > 0.0, "参数错误：%s.", input);
        //Preconditions.checkArgument(input > 0.0, "参数错误 %d",2);
        return Math.abs(input);
    }

    /**
     * 校验索引是否在集合中,返回此索引
     *
     * @param list
     * @return
     */
    public static int checkElement(List<Integer> list) {
        int i = Preconditions.checkElementIndex(2, list.size());
        return i;
    }
}
