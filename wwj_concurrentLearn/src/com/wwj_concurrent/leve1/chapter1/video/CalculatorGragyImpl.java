package com.wwj_concurrent.leve1.chapter1.video;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/11/1 15:50
 * @Modified By：
 */
public class CalculatorGragyImpl implements CalculatorStragy {

    private static final double STRATEGY_RATE = 0.1;

    private static final double BONUS_RATE = 0.15;

    @Override
    public double getCalculators(double caltax, double bonus) {
        return caltax * STRATEGY_RATE + bonus * BONUS_RATE;
    }
}
