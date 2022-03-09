package me.abspring.service;

import me.abspring.dao.AnOtherTbDao;
import me.abspring.entity.AnOtherTb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public int insertTestTb(AnOtherTb otherTb) {
        int result = 0;
        try {
            for (int i = 0; i < 8; i++) {
                result = anOtherTbDao.insertTestTb(otherTb);
                if (i == 3) {
                    result = 1 / 0;
                }
            }
        } catch (Exception e) {

        }

        return result;
    }
}
