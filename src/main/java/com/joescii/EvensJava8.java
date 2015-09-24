package com.joescii;

import java.util.stream.IntStream;

public class EvensJava8 {
    // Notice that n and i are never _reassigned_
    public static IntStream firstNEvens(int n) {
        return IntStream.rangeClosed(1, n)
                .map((i) -> i * 2);
    }
}
