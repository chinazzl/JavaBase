package favor.SE.equal;

import java.util.concurrent.atomic.AtomicInteger;

class Mainn {
    public static void main(String[] args) {
        ColorPoint p1 = new ColorPoint(1,2,new Color("red"));
        Point p2 = new Point(1,2);
        ColorPoint p3 = new ColorPoint(1,2,new Color("blue"));
        System.out.println(p1.equals(p2)); //true
        System.out.println(p2.equals(p3)); //fasle 违反了equals的对称性
        System.out.println(p1.equals(p3)); //fasle 违反了equals的传递性
        System.out.println("=========================");
        AtomicInteger atomicInteger = new AtomicInteger();
        System.out.println(atomicInteger.incrementAndGet());
//        Drive d = new Benz();
//        d.run();
    }

}


