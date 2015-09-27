package com.joescii;

import java.util.LinkedList;
import java.util.List;

public class PrimeJava {
    public static boolean isPrime(int n) {
        boolean isPrime = true;
        for(int i=2; i<n; i++) {
            if(n % i == 0) {
                isPrime = false;
                break;
            }
        }
        return isPrime;
    }

    public static List<Integer> first(int n) {
        List<Integer> primes = new LinkedList<Integer>();
        for(int i=2; primes.size() < n; i++) {
            if(isPrime(i)) primes.add(i);
        }
        return primes;
    }
}
