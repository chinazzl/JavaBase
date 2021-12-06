package com.wwj_concurrent.level3.atomic;

import java.util.concurrent.atomic.AtomicBoolean;

/**********************************
 * @author zhang zhao lin
 * @date 2021年09月27日 22:17
 * @Description
 **********************************/
public class AtomicBooelanTest {

    public static void main(String[] args) {
//        getAtomicBooWithoutArg();
        getAtomicBooelWithinArg();
    }

    private static void getAtomicBooWithoutArg() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        System.out.println(atomicBoolean.get());
    }

    private static void getAtomicBooelWithinArg() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        boolean result = atomicBoolean.compareAndSet(true, false);
        System.out.println(atomicBoolean.get());
        System.out.println("===============");
        System.out.println(result);
    }
}
