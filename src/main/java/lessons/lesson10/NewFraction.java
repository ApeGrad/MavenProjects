package lessons.lesson10;

import java.util.Arrays;
import java.util.Scanner;

public class NewFraction {

    int intPart;
    short decimalPart;

    static Scanner scan = new Scanner(System.in);

    static int[] floatArr = new int[2];

    public NewFraction(int intPart, short decimalPart) {
        this.intPart = intPart;
        this.decimalPart = decimalPart;
    }

    public static void main(String[] args) throws Exception {
        start();
    }

    public static String firstPart(String userString) {
        while (true) {
            userString = userString.substring(0, userString.lastIndexOf("p"));
            break;
        }
        return userString;
    }

    public static String firstPartWithMinus(String userString) {
        while (true) {
            userString = userString.substring(0, userString.lastIndexOf("m"));
            break;
        }
        return userString;
    }

    public static String secondPart(String userString) {
        while (true) {
            userString = userString.substring(userString.lastIndexOf(" ") + 1);
            break;
        }
        return userString;
    }

    public static int partBefore(String firstPart) {
        String[] first = firstPart.split(",");
        return Integer.parseInt(first[0]);
    }

    public static int partAfter(String secondPart) throws Exception {
        int returnShortValue = Integer.parseInt(secondPart.substring(secondPart.lastIndexOf(",") + 1, secondPart.lastIndexOf(" ")));
        if (returnShortValue > Short.MAX_VALUE) {
            throw new Exception("Десятичное число превышает возможное максимальное значение");
        }
        return returnShortValue;
    }

    public static int partAfterSecondNumber(String secondPart) {
        int afterComa = Integer.parseInt(secondPart.substring(secondPart.lastIndexOf(",") + 1, secondPart.lastIndexOf("")));
        if (afterComa > Short.MAX_VALUE) {
            try {
                throw new Exception("Десятичное число превышает возможное максимальное значение");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return afterComa;
    }

    public static void start() throws Exception {
        System.out.println("Введите две десятичные дроби через запятую.\nМежду ними напишите 'plus' или 'minus', \nчтобы определить какую операцию выполнить.");
        System.out.println("Пример: 32,534 plus 985,5453");
        String floatType = scan.nextLine();
        if (floatType.contains("plus")) {
            int firstPart = partBefore(firstPart(floatType));
            short secondPart = (short) partAfter(firstPart(floatType));
            int firstPartOfSecondNum = partBefore(secondPart(floatType));
            short secondPartOfSecondNum = (short) partAfterSecondNumber(secondPart(floatType));
            int length = (int) (Math.log10(firstPart) + 1);
            NewFraction fractionNum1 = new NewFraction(firstPart, secondPart);
            NewFraction fractionNum2 = new NewFraction(firstPartOfSecondNum, secondPartOfSecondNum);
            System.out.println(Arrays.toString(additionNew(fractionNum1, fractionNum2)));
        } else if (floatType.contains("minus")) {
            int firstPart = partBefore(firstPartWithMinus(floatType));
            short secondPart = (short) partAfter(firstPartWithMinus(floatType));
            int firstPartOfSecondNum = partBefore(secondPart(floatType));
            short secondPartOfSecondNum = (short) partAfterSecondNumber(secondPart(floatType));
            NewFraction fractionNum1 = new NewFraction(firstPart, secondPart);
            NewFraction fractionNum2 = new NewFraction(firstPartOfSecondNum, secondPartOfSecondNum);
            System.out.println(Arrays.toString(subtractionNew(fractionNum1, fractionNum2)));
        }
    }

    public static int numLength(int x) {
        if (x > -10 && x < 10) {
            return 1;
        } else {
            return numLength(x / 10) + 1;
        }
    }

    static int[] additionNew(NewFraction num1, NewFraction num2) {

        int numLength = numLength(num1.decimalPart);
        int numLength2 = numLength(num2.decimalPart);
        int decimal1 = num1.decimalPart;
        int decimal2 = num2.decimalPart;
        if (numLength == 1) {
            decimal1 *= 10000;
        }
        if (numLength == 2) {
            decimal1 *= 1000;
        }
        if (numLength == 3) {
            decimal1 *= 100;
        }
        if (numLength == 4) {
            decimal1 *= 10;
        }
        if (numLength2 == 1) {
            decimal2 *= 10000;
        }
        if (numLength2 == 2) {
            decimal2 *= 1000;
        }
        if (numLength2 == 3) {
            decimal2 *= 100;
        }
        if (numLength2 == 4) {
            decimal2 *= 10;
        }
        if (num1.intPart < 0 && decimal1 > 0) {
            decimal1 *= -1;
        }
        if (num2.intPart < 0 && decimal2 > 0) {
            decimal2 *= -1;
        }
        int newNum1 = num1.intPart * 100000 + decimal1;
        int newNum2 = num2.intPart * 100000 + decimal2;
        int subOfDecimal = newNum1 + newNum2;
        int first = subOfDecimal / 100000;
        int decimalOnePart = subOfDecimal / 10000 % 10;
        int decimalOnePart2 = subOfDecimal / 1000 % 10;
        int decimalOnePart3 = subOfDecimal / 100 % 10;
        int decimalOnePart4 = subOfDecimal / 10 % 10;
        int decimalOnePart5 = subOfDecimal % 10;
        decimalOnePart *= 10000;
        decimalOnePart2 *= 1000;
        decimalOnePart3 *= 100;
        decimalOnePart4 *= 10;
        int sumOfDecimal = decimalOnePart + decimalOnePart2 + decimalOnePart3 + decimalOnePart4 + decimalOnePart5;
        if (sumOfDecimal < 0) {
            sumOfDecimal *= -1;
        }
        if (sumOfDecimal % 10 == 0) {
            sumOfDecimal /= 10;
        }
        if (sumOfDecimal % 10 == 0) {
            sumOfDecimal /= 10;
        }
        floatArr[0] = first;
        floatArr[1] = sumOfDecimal;
        return floatArr;
    }

    static int[] subtractionNew(NewFraction num1, NewFraction num2) {

        int numLength = numLength(num1.decimalPart);
        int numLength2 = numLength(num2.decimalPart);
        int decimal1 = num1.decimalPart;
        int decimal2 = num2.decimalPart;
        if (num1.intPart < 0 && num2.intPart < 0) {
            decimal1 *= -1;
            decimal2 *= -1;
        }
        if (num1.intPart < 0 && num2.intPart > 0) {
            num1.decimalPart *= -1;
            num2.decimalPart *= -1;
            num2.intPart *= -1;
            return additionNew(num1, num2);
        }
//        if (num2.intPart < 0) {
//            decimal2 *= -1;
//
//        }

        if (numLength == 1) {
            decimal1 *= 10000;

        }

        if (numLength == 2) {
            decimal1 *= 1000;

        }
        if (numLength == 3) {
            decimal1 *= 100;

        }
        if (numLength == 4) {
            decimal1 *= 10;

        }
        if (numLength2 == 1) {
            decimal2 *= 10000;

        }

        if (numLength2 == 2) {
            decimal2 *= 1000;

        }
        if (numLength2 == 3) {
            decimal2 *= 100;

        }
        if (numLength2 == 4) {
            decimal2 *= 10;

        }
        int newNum1 = num1.intPart * 100000 + decimal1;
        int newNum2 = num2.intPart * 100000 + decimal2;
        int subOfDecimal = newNum1 - newNum2;
        int first = subOfDecimal / 100000;
        int decimalOnePart = subOfDecimal / 10000 % 10;
        int decimalOnePart2 = subOfDecimal / 1000 % 10;
        int decimalOnePart3 = subOfDecimal / 100 % 10;
        int decimalOnePart4 = subOfDecimal / 10 % 10;
        int decimalOnePart5 = subOfDecimal % 10;
        decimalOnePart *= 10000;
        decimalOnePart2 *= 1000;
        decimalOnePart3 *= 100;
        decimalOnePart4 *= 10;
        int sumOfDecimal = decimalOnePart + decimalOnePart2 + decimalOnePart3 + decimalOnePart4 + decimalOnePart5;
        if (sumOfDecimal < 0) {
            sumOfDecimal *= -1;

        }
        if (sumOfDecimal % 10 == 0) {
            sumOfDecimal /= 10;

        }
        if (sumOfDecimal % 10 == 0) {
            sumOfDecimal /= 10;

        }
        if (sumOfDecimal % 10 == 0) {
            sumOfDecimal /= 10;

        }
        floatArr[0] = first;
        floatArr[1] = sumOfDecimal;
        return floatArr;
    }
}
