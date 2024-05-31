package com.spring.second.controllers;

import com.spring.second.exceptions.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class MathController {
    private final AtomicLong counter = new AtomicLong();

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

    private Double convertToDouble(String strNumber) {
        if (strNumber == null) {
            return 0D;
        }
        String number = strNumber.replaceAll(",", ".");
        if (isNumeric(number))   return Double.parseDouble(number);
        return 0D;
    }

    private boolean isNumeric(String strNumber) {
        if (strNumber == null) {
            return false;
        } String number = strNumber.replaceAll(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }

    private Double averageTwoNumbers(String strNumber1, String strNumber2) {
        Double average = (convertToDouble(strNumber1) + convertToDouble(strNumber2)) / 2;
        return average;
    }
}
