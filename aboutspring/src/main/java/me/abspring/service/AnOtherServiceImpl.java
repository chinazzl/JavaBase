package me.abspring.service;

import me.abspring.dao.AnOtherTbDao;
import me.abspring.entity.AnOtherTb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Julyan
 * @version V1.0
 * @Description:
 * @Date: 2022/3/8 9:55
 */
@Service("anOtherTbService")
public class AnOtherServiceImpl implements AnOtherTbService {

    @Autowired
    private AnOtherTbDao anOtherTbDao;

    @Override
    public int insertTestTb(AnOtherTb otherTb) {
        return anOtherTbDao.insertTestTb(otherTb);
    }
}
