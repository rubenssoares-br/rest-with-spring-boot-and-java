package com.spring.second.math;



public class SimpleMath {


    public Double multiplication( Double numberOne, Double numberTwo) {

        return  numberOne * numberTwo;
    }


    public Double subtraction(Double numberOne, Double numberTwo) {
        return numberOne - numberTwo;
    }


    public Double addition( Double numberOne, Double numberTwo) {
        return numberOne + numberTwo;
    }

    public Double division(Double numberOne, Double numberTwo) {
        return numberOne / numberTwo;
    }


    public Double average(Double numberOne, Double numberTwo) {
        return ((numberOne + numberTwo) / 2);
    }

    public Double squareRoot(Double numberOne) {

        return Math.sqrt((numberOne));
    }

}
