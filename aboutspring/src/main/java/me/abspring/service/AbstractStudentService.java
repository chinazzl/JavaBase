package me.abspring.service;

import org.springframework.beans.factory.annotation.Autowired;

/**********************************
 * @author zhang zhao lin
 * @date 2022年04月06日 12:04
 * @Description:
 **********************************/
public abstract class AbstractStudentService {

    @Autowired
    AnOtherTbService anOtherTbService;

    public void test() {
        System.out.println(anOtherTbService);
    }
}
