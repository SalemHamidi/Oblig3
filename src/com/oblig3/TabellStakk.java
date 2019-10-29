package com.oblig3;

import java.util.*;

public class TabellStakk<T> implements Stakk<T>
{
    private T[] a;                    // en generisk tabell
    private int antall;               // antall verdier på stakken

    @SuppressWarnings("unchecked")    // konvertering: Object[] -> T[]
    public TabellStakk(int lengde)    // valgfri tabellengde
    {
        a = (T[]) new Object[lengde];   // oppretter tabellen
        antall = 0;                     // stakken er tom
    }

    public TabellStakk()
    {
        this(8);                        // lengde lik 8
    }

    @Override
    public int antall()
    {
        return antall;
    }

    @Override
    public boolean tom()
    {
        return antall == 0;
    }

    @Override
    public void leggInn(T verdi)
    {
        if (antall == a.length)
            a = Arrays.copyOf(a,antall == 0 ? 1 : 2*antall);  // dobler

        a[antall++] = verdi;
    }

    @Override
    public T kikk()
    {
        if (antall == 0) throw
                new NoSuchElementException("Stakken er tom!");

        return a[antall-1];
    }

    @Override
    public T taUt()
    {
        if (antall == 0) throw
                new NoSuchElementException("Stakken er tom!");

        antall--;

        T temp = a[antall];
        a[antall] = null;
        return temp;
    }

    @Override
    public void nullstill()
    {
        for (int i = 0; i < antall; i++) a[i] = null;
        antall = 0;
    }

    @Override
    public String toString()   // bruker StringJoiner
    {
        StringJoiner s = new StringJoiner(", ", "[", "]");
        for (int i = antall - 1; i >= 0; i--)
            s.add(a[i] == null ? "null" : a[i].toString());
        return s.toString();
    }

    public String toString2()  // bruker StringBuilder
    {
        if (tom()) return "[]";

        StringBuilder s = new StringBuilder();
        s.append('[');
        s.append(a[antall-1]);

        for (int i = antall - 2; i >= 0; i--)
            s.append(',').append(' ').append(a[i]);

        s.append(']');
        return s.toString();
    }

} // class TabellStakk
