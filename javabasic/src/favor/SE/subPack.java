package favor.SE;

import favor.testPackage.Pack;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * 在testPackage的包下，如果抽象类中的抽象方法tt()设置的是default则，在其他包下
 * 继承它后不能实现它的抽象方法，至少改成
 * @see protected
 */
public class subPack extends Pack {

    public static void main(String[] args) {
        Map map = new HashMap();
        map.put("a","a");
        map.put("b","b");
//        Map.Entry entry = new Map.Entry();
//        entry.
        map.forEach(new BiConsumer() {
            @Override
            public void accept(Object o, Object o2) {

            }
        });


    }

    @Override
    protected void tt() {

    }
}
