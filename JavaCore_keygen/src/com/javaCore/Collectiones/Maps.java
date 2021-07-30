package com.javaCore.Collectiones;

import java.util.*;

/**
 * @author zhangzhaolin
 * @version V1.0
 * @Title: JavaBase
 * @Package com.javaCore.Collectiones
 * @Description:
 * @Date: 2021/7/2 16:20
 */
public class Maps {

    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1,"Tom");
        map.put(2,"Jerry");
        map.put(3,"scott");
    }

    private static void getKeySet(Map<Integer, String> map) {
        Set<Integer> set = map.keySet();
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            System.out.println("key -> " +next);
        }
        System.out.println("===========================");

    }
}
