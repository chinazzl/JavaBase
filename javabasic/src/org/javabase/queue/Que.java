package org.javabase.queue;

import java.util.*;

/**
 * Java Querue队列
 */
public class Que {
    public static void main(String[] args) {
//        Queue<String> queue = new LinkedList<>();
//        queue.add("1");
//        queue.add("2");
//        Iterator<String> iterator = queue.iterator();
//        while (iterator.hasNext()){
//            String next = iterator.next();
//            System.out.println(next);
//        }
        UUID uuid = UUID.randomUUID();
//        UUID u = fromStringWhitoutHyphens(uuid.toString().replaceAll("-","").trim());
//        System.out.println(u.toString());
        getUUID();
    }

    public static void getUUID(){
        //随机生成一位整数
        int random = (int) (Math.random()*9+1);
        String valueOf = String.valueOf(random);
        //生成uuid的hashCode值
        int hashCode = UUID.randomUUID().toString().hashCode();
        //可能为负数
        if(hashCode<0){
            hashCode = -hashCode;
        }
        String value = valueOf + String.format("%015d", hashCode);
        System.out.println(value);
    }
}


