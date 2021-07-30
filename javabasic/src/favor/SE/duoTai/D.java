package favor.SE.duoTai;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/3/6 14:58
 * @Modified By：
 */
public class D extends B {

    private static class Test {
        public static void main(String[] args) {
            A a1 = new A();
            A a2 = new B();
            B b = new B();
            C c = new C();
            D d = new D();
/**
 * 其实在继承链中对象方法的调用存在一个优先级：this.show(O)、super.show(O)、this.show((super)O)、super.show((super)O)。
 * 1--A parameter A
 * 2--A parameter A
 * 3--A parameter D
 * 4--B parameter a
 * 5--B parameter a
 * 6--A parameter D
 * 7--B parameter b
 * 8--B parameter b
 *
 * Process finished with exit code 0
 */
//            System.out.println("1--" + a1.show(b)); //"B parameter a"
//            System.out.println("2--" + a1.show(c)); //
//            System.out.println("3--" + a1.show(d));
            System.out.println("4--" + a2.show(b));
//            System.out.println("5--" + a2.show(c));
//            System.out.println("6--" + a2.show(d));
//            System.out.println("7--" + b.show(b));
//            System.out.println("8--" + b.show(c));
        }
    }
}

