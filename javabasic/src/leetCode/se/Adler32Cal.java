package leetCode.se;

import java.util.zip.Adler32;

/**
 * @author: zhaolin
 * @Date: 2025/10/17
 * @Description:
 **/
public class Adler32Cal {

    public static void main(String[] args) {
        Adler32 adler32 = new Adler32();
        User u1 = new User("zs","22");
        adler32.update(u1.toString().getBytes());
        System.out.println(adler32.getValue());
        adler32.reset();
        User u2 = new User("zs","22");
        adler32.update(u2.toString().getBytes());
        System.out.println(adler32.getValue());
        int ceil = (int) Math.ceil((double) 500 / 30);
        System.out.println(ceil);

    }

    private static class User {
        private String name;
        private String age;
        public User(String name, String age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age='" + age + '\'' +
                    '}';
        }
    }

}
