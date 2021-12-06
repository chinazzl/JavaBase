package com.wwj_concurrent.leve2.chapter2.design.futurepattern;

/**
 * @author zhang zhao lin
 * @date 2021年06月23日 22:47
 * @Description
 */
public interface Future<T> {

    T get();

}
