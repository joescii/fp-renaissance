package com.joescii;

import java.util.*;

public class EvensJava7 {
    public static List<Integer> firstNEvens(int n) {
        List<Integer> evens = new ArrayList<Integer>();
        for(int i=1; i<=n; i++)
            evens.add(i*2);
        return evens;
    }
}
