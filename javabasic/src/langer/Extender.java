package langer;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/6/4 23:19
 * @Modified By：
 */
public class Extender {
    class emplyee {
        String name;
        public void printName() {
            System.out.println("parent method");
        }
        public void setName(String name){
            this.name =  name;
        }
    }

    class Teacher extends emplyee {

        public void priintT(){
            System.out.println("child name" + name);
        }
    }

    public static void main(String[] args) {
        Extender extender = new Extender();
        Extender.Teacher t = extender.new Teacher();
        t.setName("zs");

        t.priintT();
    }
}
