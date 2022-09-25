package javaFromScratch.utils;

public class Calculator {
    public static final Double PI = 3.14159;
    // o final equivale a declaração de constante, isto é, o valor permanecerá o mesmo, dando erro se tentar alterar via constructor

    public static double circumference(double radValue) {
        return 2.0 * PI * radValue;
    }

    public static double volume(double volValue) {
        return (4.0 * PI * volValue * volValue *  volValue) / 3.0;
    }
}
