package com.spring.second.utilsMethods;

public class UtilsMethods {
    public static Double convertToDouble(String strNumber) {
        if (strNumber == null) {
            return 0D;
        }
        String number = strNumber.replaceAll(",", ".");
        if (isNumeric(number))   return Double.parseDouble(number);
        return 0D;
    }

    public static boolean isNumeric(String strNumber) {
        if (strNumber == null) {
            return false;
        } String number = strNumber.replaceAll(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }

    public static Double averageTwoNumbers(String strNumber1, String strNumber2) {
        Double average = (convertToDouble(strNumber1) + convertToDouble(strNumber2)) / 2;
        return average;
    }
}
