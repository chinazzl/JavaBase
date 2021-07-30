package com.wwj_concurrent.leve1.chapter1.video;

/**
 * @Author: zhang zhao lin
 * @Description:
 * @Date:Create：in 2020/11/1 15:37
 * @Modified By：
 */
public class TaxCalcuator {

    private Double calTax;

    private Double bonus;

    private final CalculatorStragy calculatorStragy;

    public TaxCalcuator(Double calTax, Double bonus, CalculatorStragy calculatorStragy) {
        this.calTax = calTax;
        this.bonus = bonus;
        this.calculatorStragy = calculatorStragy;
    }

    public Double getCalTax() {
        return calTax;
    }

    public void setCalTax(Double calTax) {
        this.calTax = calTax;
    }

    public Double getBonus() {
        return bonus;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }

    protected double calTax() {
        return calculatorStragy.getCalculators(calTax, bonus);
    }

    public double calculate() {
        return this.calTax();
    }
}
