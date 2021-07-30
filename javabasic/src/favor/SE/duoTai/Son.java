package favor.SE.duoTai;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/3/6 14:45
 * @Modified By：
 */
public class Son extends Father{
    int i = 20;

    private static class Test{
        public static void main(String[] args) {
            /**
             *  当引用类型的真实身份是父类本身的类型时，强制类型转换就会产生错误。例如：
             *
             *        Father father = new Father();
             *
             *        Son son = (Son) father;
             *
             *        这个系统会抛出ClassCastException异常信息。
             */
            Father f = new Son();
//            System.out.println(f.i);
            Son s = (Son)f;
            System.out.println(s.i);
        }
    }
}
