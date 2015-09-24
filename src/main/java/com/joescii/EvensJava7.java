package com.joescii;

import java.util.*;

public class EvensJava7 {
    public static List<Integer> firstNEvens(int n) {
        List<Integer> squares = new ArrayList<Integer>();
        for(int i=1; i<=n; i++)
            squares.add(i*2);
        return squares;
    }
}
