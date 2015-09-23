package com.joescii;

import java.util.*;

public class EvensJava7 {
    public static List<Integer> first25() {
        List<Integer> squares = new ArrayList<Integer>();
        for(int i=1; i<=25; i++)
            squares.add(i*2);
        return squares;
    }
}
