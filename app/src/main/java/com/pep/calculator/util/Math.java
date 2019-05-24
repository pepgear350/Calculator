package com.pep.calculator.util;

public class Math {


    private  String operator;
    private  boolean isSecondNegative;
    private  boolean isFrisrtNegative;



    public  String [] readText(String text) {

        String trimText = text.trim();
        isFrisrtNegative = false;
        isSecondNegative = false;
        boolean isFound = false;
        operator = null;


        if (trimText.startsWith("-")) {
            isFrisrtNegative = true;
            trimText = trimText.replaceFirst("-", "");

        }

        String[] split = null;


        if (trimText.contains("*")) {
            isFound = true;
            operator = "*";
            split = trimText.split("[*]", 2);

        }

        if (trimText.contains("/") && !isFound) {
            isFound = true;
            operator = "/";
            split = trimText.split("[/]", 2);

        }


        if (trimText.contains("+") && !isFound) {
            isFound = true;
            operator = "+";
            split = trimText.split("[+]", 2);

        }

        if (trimText.contains("-") && !isFound) {
            operator = "-";
            split = trimText.split("[-]", 2);

        }


        if (split == null)
            return null;

        String first = split[0];
        String second = split[1];

        if (first.isEmpty() || second.isEmpty()) {

            return null;

        }


        second = second.trim();
        if (second.startsWith("-")) {
            isSecondNegative = true;
            second = second.replaceFirst("-", "");

        }



        Boolean isSpaces = hasSpaces(first, second);


        if (isSpaces) {
            first = first.replace(" ", "");
            second = second.replace(" ", "");

        } else {

            return null;


        }

        String firstValue = getParenthesis(first);
        String secondValue = getParenthesis(second);

        if (firstValue.isEmpty() || secondValue.isEmpty()) {

            return null;
        }

        return new String[]{firstValue, secondValue};
    }


    public  Boolean hasSpaces(String first, String second) {

        String[] mFirst = first.split("[-]");
        String[] mSecond = second.split("[-]");

        for (String s : mFirst) {
            if (s.startsWith(" ")) {
                return false;
            }
        }

        for (String s : mSecond) {
            if (s.startsWith(" ")) {
                return false;
            }
        }
        return true;
    }

    public  double mathOperation(String [] value) {


        double result = 0;
        double firstParse = Double.valueOf(value[0]);
        double secondParse = Double.valueOf(value[1]);
        if (isFrisrtNegative) {
            firstParse = firstParse * -1;
        }

        if (isSecondNegative) {
            secondParse = secondParse * -1;
        }





        switch (operator) {
            case "*":
                result = firstParse * secondParse;
                break;

            case "/":
                result = firstParse / secondParse;
                break;

            case "+":
                result = firstParse + secondParse;
                break;

            case "-":
                result = firstParse - secondParse;
                break;
        }


        return result;
    }


    private  String getParenthesis(String string) {

        int first = string.indexOf("(");
        int second;

        if (first != -1) {
            second = string.indexOf(")");
            if (second != -1) {
                return string.substring(first + 1, second);
            }
            string = string.replace("(", "");
        }
        return string;
    }





}
