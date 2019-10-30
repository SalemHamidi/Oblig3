package com.oblig3;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Integer[] a = {4,7,2,9,4,10,8,7,4,6};
        ObligSBinTre<Integer> tre = new ObligSBinTre<>(Comparator.naturalOrder());
        for(int verdi : a) {
            tre.leggInn(verdi);
        }
        System.out.println(tre);
        System.out.println(tre.antall());
        System.out.println(tre.antall(5));
        System.out.println(tre.antall(4));
        System.out.println(tre.antall(7));
        System.out.println(tre.antall(10));

    }
}
