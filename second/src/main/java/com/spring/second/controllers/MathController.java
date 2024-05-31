package com.spring.second.controllers;


import com.spring.second.exceptions.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.spring.second.utilsMethods.UtilsMethods;

import java.util.concurrent.atomic.AtomicLong;

import static com.spring.second.utilsMethods.UtilsMethods.*;


@RestController
public class MathController {
    public final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = "/multiplication/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double multiplication(@PathVariable(value = "numberOne") String numberOne,
                                 @PathVariable(value = "numberTwo") String numberTwo) throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
         throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        return convertToDouble(numberOne) * convertToDouble(numberTwo);
    }

    @RequestMapping(value = "/subtraction/{number1}/{number2}", method = RequestMethod.GET)
    public Double subtraction(@PathVariable(value = "number1") String numberOne,
                              @PathVariable(value = "number2") String numberTwo) throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        return convertToDouble(numberOne) - convertToDouble(numberTwo);
    }

    @RequestMapping(value = "/addition/{number1}/{number2}", method = RequestMethod.GET)
    public Double addition(@PathVariable(value = "number1") String numberOne,
                              @PathVariable(value = "number2") String numberTwo) throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        return convertToDouble(numberOne) + convertToDouble(numberTwo);
    }

    @RequestMapping(value = "/division/{number1}/{number2}", method = RequestMethod.GET)
    public Double division(@PathVariable(value = "number1") String numberOne,
                           @PathVariable(value = "number2") String numberTwo) throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        return convertToDouble(numberOne) / convertToDouble(numberTwo);
    }

    @RequestMapping(value = "/average/{number1}/{number2}", method = RequestMethod.GET)
    public Double average(@PathVariable(value = "number1") String numberOne,
                           @PathVariable(value = "number2") String numberTwo) throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        return averageTwoNumbers(numberOne, numberTwo);
    }

    @RequestMapping(value = "/squareRoot/{number1}", method = RequestMethod.GET)
    public Double squareRoot(@PathVariable(value = "number1") String numberOne) throws Exception {
        if (!isNumeric(numberOne)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        return Math.sqrt(convertToDouble(numberOne));
    }
}
