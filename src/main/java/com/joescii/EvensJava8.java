package com.joescii;

import java.util.stream.IntStream;

public class EvensJava8 {
    public static IntStream evensFirst(int n) {
        return IntStream.rangeClosed(1, n)
                .map((i) -> i * 2);
    }
}
