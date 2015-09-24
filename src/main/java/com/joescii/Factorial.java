package com.joescii;

public class Factorial {
    public static int factorial(int n) {
        int acc = 1;
        for(int i=1; i<=n; i++)
            acc *= i;
        return acc;
    }
}
