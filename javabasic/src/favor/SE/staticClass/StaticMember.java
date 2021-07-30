package favor.SE.staticClass;

import java.util.*;

/**
 * 学习静态成员类
 * 学习通配符，泛型
 */
public class StaticMember {
    private String name;
     static class A<E> implements Map.Entry {
//         private E[] element;
         @Override
         public Object getKey() {
             return null;
         }

         @Override
         public Object getValue() {
             return null;
         }

         @Override
         public Object setValue(Object value) {
             return null;
         }

         public Stack H(Iterable<? extends E> iterator){
             Stack stack = new Stack();
             for (E e: iterator) {
                 stack.push(e);
             }
             return stack;
         }


     }

     /*
        普通嵌套类
      */
     class NomalClz{
         private String n_nmae;
         public  void fun() throws ClassNotFoundException {
             nomalClaz();
             System.out.println(Class.forName("StaticMember"));
         }

         public NomalClz(String n_nmae) {
             this.n_nmae = n_nmae;
         }
     }
    public static void main(String[] args) throws NoSuchMethodException{
         
         A a = new A();
         Iterable iterable = new ArrayList();
         ((ArrayList) iterable).add(1);
         ((ArrayList) iterable).add(2);
        System.out.println(a.H(iterable));
    }

    public void nomalClaz(){
        NomalClz n = new NomalClz("heh");
    }
}
