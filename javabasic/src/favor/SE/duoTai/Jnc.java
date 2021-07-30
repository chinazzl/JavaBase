package favor.SE.duoTai;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/3/6 11:32
 * @Modified By：
 * 剑南春 为 Wine 的子类
 */
public class Jnc extends Wine{

    public void fun1(String a) {
        System.out.println(" Jnc's fun1 method");
        fun2();
    }

    @Override
    public void fun2() {
        System.out.println("Jnc's fun2 method");
    }

    /**
     * 分析：在这个程序中子类JNC重载了父类Wine的方法fun1()，重写fun2()，而且重载后的fun1(String a)与 fun1()不是同一个方法，
     * 由于父类中没有该方法，向上转型后会丢失该方法，所以执行JNC的Wine类型引用是不能引用fun1(String a)方法。
     * 而子类JNC重写了fun2() ，那么指向JNC的Wine引用会调用JNC中fun2()方法。
     *
     * 所以对于多态我们可以总结如下：
     *
     *指向子类的父类引用由于向上转型了，它只能访问父类中拥有的方法和属性，而对于子类中存在而父类中不存在的方法，
     * 该引用是不能使用的，尽管是重载该方法。若子类重写了父类中的某些方法，在调用该些方法的时候，
     * 必定是使用子类中定义的这些方法（动态连接、动态调用）。
     *
     */
    private static class Test{
        public static void main(String[] args) {
            Wine a = new Jnc();
            a.fun1();
        }
    }
}
