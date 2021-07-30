package com.wwj_concurrent.leve1.chapter1.video;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/11/1 15:50
 * @Modified By：
 */
public class CalculatorStratgyMain {
    public static void main(String[] args) {
//         TaxCalcuator taxCalcuator = new TaxCalcuator(10000d,2000d){
//            @Override
//            public double calTax() {
//                return getCalTax() * 0.1 + getBonus() * 0.15;
//            }
//        };
//        taxCalcuator.calculate();

        //使用策略模式
        TaxCalcuator taxCalcuator = new TaxCalcuator(10000d, 2000d, (s, b) -> s * 0.1 + b * 0.15);
        System.out.println(taxCalcuator.calculate());

    }
}
