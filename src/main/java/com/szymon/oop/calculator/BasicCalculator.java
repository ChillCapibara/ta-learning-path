package com.szymon.oop.calculator;

public class BasicCalculator implements Calculator{

    @Override
    public void add() {
        int result = getNumber("first") + getNumber("second");
        while(continueOperation()){
            result += getNumber();
        }
        announceResult(result);
    }

    @Override
    public void subtract() {
        int result = getNumber("first") - getNumber("second");
        while(continueOperation()){
            result -= getNumber();
        }
        announceResult(result);
    }

    @Override
    public void multiply() {

    }

    @Override
    public void divide() {

    }

    @Override
    public void square() {

    }

    @Override
    public void power() {

    }
}
