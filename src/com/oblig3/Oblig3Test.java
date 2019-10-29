package com.oblig3;


////// Testprogram for Oblig 3 - 2019 ////////////////////////

/*
Testprogrammet har en main-metode som tester metodene i de 10 oppgavene.
Som utgangspunkt er metodekallene kommentert vekk. Her må en passe på
å bruke nøyaktig samme navn på metodene som det er bedt om i oppgaveteksten.

Når en mener at en oppgave/metode er løst, bør en først teste med egne tester.
Når en så tror at koden er feilfri, kan testprogrammet brukes ved at
kommentartegnet foran metodekallet tas vekk. Testprogrammet må gi 0 feil før
Oblig 3 sendes inn!
*/

///// Oppdatert 8. oktober 2019 ///////////////

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Oblig3Test {

    // OPPGAVE 1 ////////////////////////////////////////////////
    @org.junit.jupiter.api.Test
    void oppgave1() {
        int antallFeil = 0;
        com.oblig3.ObligSBinTre<Integer> tre =
                new ObligSBinTre<>(Comparator.naturalOrder());

        try {
            if (tre.antall() != 0) {
                antallFeil++;
                System.out.println("Oppgave 1a: Feil antall i et tomt tre!");
            }
        } catch (Exception e) {
            antallFeil++;
            System.out.println
                    ("Oppgave 1b: Skal ikke kaste unntak for et tomt tre");
        }

        tre.leggInn(1);
        tre.leggInn(2);
        tre.leggInn(3);

        if (tre.antall() != 3) {
            antallFeil++;
            System.out.println
                    ("Oppgave 1c: Antall blir ikke oppdatert!");
        }

        assertEquals(antallFeil, 0);

    }  // slutt på Oppgave 1


    // OPPGAVE 2 ////////////////////////////////////////////////
    @org.junit.jupiter.api.Test
    void oppgave2() {
        com.oblig3.ObligSBinTre<Integer> tre =
                new ObligSBinTre<>(Comparator.naturalOrder());

        int antallFeil = 0;

        tre.leggInn(1);

        try {
            if (tre.antall(1) != 1) {
                antallFeil++;
                System.out.println("Oppgave 2a: Feil antall(T)-metoden!");
            }
        } catch (Exception e) {
            antallFeil++;
            System.out.println
                    ("Oppgave 2b: Skal ikke kaste unntak her!");
        }

        // Nytt tre
        tre = new ObligSBinTre<>(Comparator.naturalOrder());
        int[] a = {1, 7, 1, 6, 1, 5, 1, 4, 1, 1, 1, 3};
        for (int verdi : a) tre.leggInn(verdi);

        if (tre.antall(7) != 1 || tre.antall(6) != 1
                || tre.antall(5) != 1 || tre.antall(4) != 1
                || tre.antall(3) != 1 || tre.antall(2) != 0
                || tre.antall(1) != 7 || tre.antall(0) != 0) {
            antallFeil++;
            System.out.println("Oppgave 2c: Feil antall(T)-metoden!");
        }


        assertEquals(antallFeil, 0);
    }  // slutt på Oppgave 2


    // OPPGAVE 3 ////////////////////////////////////////////////
    @org.junit.jupiter.api.Test
    void oppgave3() {
        int antallFeil = 0;
        com.oblig3.ObligSBinTre<Integer> tre =
                new ObligSBinTre<>(Comparator.naturalOrder());

        String s;

        try {
            s = tre.toString();
            if (!s.equals("[]")) {
                antallFeil++;
                System.out.println("Oppgave 3a: Feil i toString() for et tomt tre!!");
            }

        } catch (Exception e) {
            antallFeil++;
            System.out.println
                    ("Oppgave 3b: Skal ikke kaste unntak for et tomt tre!");
        }
/*
        // legger inn 10
        tre.leggInn(10);

        s = tre.toString();
        if (!s.equals("[10]")) {
            antallFeil++;
            System.out.println("Oppgave 3c: Feil i toString() for et tre med kun en verdi!");
        }

        // legger inn flere verdier
        int[] a = {6, 14, 1, 8, 12, 3, 7, 9, 11, 13, 2, 5, 4};
        for (int verdi : a) tre.leggInn(verdi);

        try {
            s = tre.toString();
            if (!s.equals("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14]")) {
                antallFeil++;
                System.out.println("Oppgave 3d: Feil i toString()! Men feilen kan");
                System.out.println("ligge i leggInn() eller i nesteInorden().");
            }
        } catch (Exception e) {
            antallFeil++;
            System.out.println
                    ("Oppgave 3e: Her kastes et unntak! Det skal ikke skje!");
        }

        // Et nytt tre
        tre = new ObligSBinTre<>(Comparator.naturalOrder());

        // legger inn 10 flere ganger
        for (int i = 0; i < 4; i++) tre.leggInn(10);

        s = tre.toString();
        if (!s.equals("[10, 10, 10, 10]")) {
            antallFeil++;
            System.out.println("Oppgave 3f: Feil i toString()! Men feilen kan");
            System.out.println("ligge i leggInn() eller i nesteInorden().");
        }

        // Et nytt tre
        tre = new ObligSBinTre<>(Comparator.naturalOrder());

        int[] b = {5, 4, 3, 2, 1};
        for (int k : b) tre.leggInn(k);

        s = tre.toString();
        if (!s.equals("[1, 2, 3, 4, 5]")) {
            antallFeil++;
            System.out.println("Oppgave 3g: Feil i toString()! Men feilen kan");
            System.out.println("ligge i leggInn() eller i nesteInorden().");
        }
        assertEquals(antallFeil, 0);

 */
    }  // slutt på Oppgave 3


    // OPPGAVE 4 ////////////////////////////////////////////////
    @org.junit.jupiter.api.Test
    void oppgave4() {
        int antallFeil = 0;
/*
        com.oblig3.ObligSBinTre<Integer> tre =
                new ObligSBinTre<>(Comparator.naturalOrder());

        String s;

        try {
            s = tre.omvendtString();
            if (!s.equals("[]")) {
                antallFeil++;
                System.out.println("Oppgave 4a: Feil i omvendtString() for et tomt tre!!");
            }
        } catch (Exception e) {
            antallFeil++;
            System.out.println
                    ("Oppgave 4b: Skal ikke kaste unntak for et tomt tre!");
        }

        int[][] a = {{1, 2, 3, 4, 5}, {5, 4, 3, 2, 1}, {4, 2, 5, 1, 3},
                {2, 1, 4, 3, 5}, {1, 4, 2, 5, 3}, {4, 1, 5, 3, 2}};


        for (int[] b : a) {
            tre = new ObligSBinTre<>(Comparator.naturalOrder());

            for (int k : b) tre.leggInn(k);
            s = tre.omvendtString();

            if (!s.equals("[5, 4, 3, 2, 1]")) {
                antallFeil++;
                System.out.println("Oppgave 4c: Feil i omvendtString()! Treet");
                System.out.println("ble bygget opp med verdiene " + Arrays.toString(b));
                break;
            }
        }
        assertEquals(antallFeil, 0);
        */
    }  // slutt på Oppgave 4


    // OPPGAVE 5 ////////////////////////////////////////////////
    @org.junit.jupiter.api.Test
    void oppgave5() {
        int antallFeil = 0;

        com.oblig3.ObligSBinTre<Integer> tre =
                new ObligSBinTre<>(Comparator.naturalOrder());

        String s;

        tre.leggInn(6);
        tre.fjern(6);

        s = tre.toString();
        if (!s.equals("[]")) {
            antallFeil++;
            System.out.println("Oppgave 5a: Feil i fjern(T)!");
        }
/*
        int[] a = {6, 3, 9, 1, 5, 7, 10, 2, 4, 8, 11, 6, 8};
        for (int verdi : a) tre.leggInn(verdi);

        boolean fjernet = tre.fjern(12);
        s = tre.toString();

        if (!s.equals("[1, 2, 3, 4, 5, 6, 6, 7, 8, 8, 9, 10, 11]")) {
            antallFeil++;
            System.out.println("Oppgave 5b: Feil i fjern(T)! Tallet 12 er ikke i treet!");
        }

        if (fjernet == true) {
            antallFeil++;
            System.out.println("Oppgave 5c: Feil i fjern(T)! Skal returnere false når");
            System.out.println("verdien ikke er i treet.");
        }

        if (tre.antall() != 13) {
            antallFeil++;
            System.out.println("Oppgave 5d: Feil i fjern(T)! Variabelen antall skal");
            System.out.println("ikke endres for en mislykket fjerning.");
        }

        fjernet = tre.fjern(2);
        s = tre.toString();

        if (!s.equals("[1, 3, 4, 5, 6, 6, 7, 8, 8, 9, 10, 11]")) {
            antallFeil++;
            System.out.println("Oppgave 5e: Feil i fjern(T)!");
        }

        if (fjernet == false) {
            antallFeil++;
            System.out.println("Oppgave 5f: Feil i fjern(T)! Skal returnere true");
            System.out.println("for en vellykket fjerning.");
        }

        if (tre.antall() != 12) {
            antallFeil++;
            System.out.println("Oppgave 5g: Feil i fjern(T)! Variabelen antall skal");
            System.out.println("reduseres med 1 for en vellykket fjerning.");
        }

        tre.fjern(4);
        s = tre.toString();

        if (!s.equals("[1, 3, 5, 6, 6, 7, 8, 8, 9, 10, 11]")) {
            antallFeil++;
            System.out.println("Oppgave 5h: Feil i fjern(T)!");
        }

        tre.fjern(6);
        s = tre.toString();

        if (!s.equals("[1, 3, 5, 6, 7, 8, 8, 9, 10, 11]")) {
            antallFeil++;
            System.out.println("Oppgave 5i: Feil i fjern(T)!");
        }

        tre.fjern(8);
        s = tre.toString();

        if (!s.equals("[1, 3, 5, 6, 7, 8, 9, 10, 11]")) {
            antallFeil++;
            System.out.println("Oppgave 5j: Feil i fjern(T)!");
        }

        tre.fjern(10);
        tre.fjern(11);
        tre.fjern(8);
        tre.fjern(7);
        s = tre.toString();

        if (!s.equals("[1, 3, 5, 6, 9]")) {
            antallFeil++;
            System.out.println("Oppgave 5k: Feil i fjern(T)!");
        }

        tre.fjern(1);
        tre.fjern(3);
        tre.fjern(5);
        tre.fjern(9);

        s = tre.toString();

        if (!s.equals("[6]")) {
            antallFeil++;
            System.out.println("Oppgave 5l: Feil i fjern(T)!");
        }

        tre.nullstill();

        if (tre.antall() != 0) {
            antallFeil++;
            System.out.println("Oppgave 5m: Feil i nullstill() - antall er feil!");
        }

        s = tre.toString();

        if (!s.equals("[]")) {
            antallFeil++;
            System.out.println("Oppgave 5n: Feil i nullstill()!");
        }

        try {
            tre.nullstill();
        } catch (Exception e) {
            antallFeil++;
            System.out.println
                    ("Oppgave 5o: Skal ikke kaste unntak når et tomt tre nullstilles!");
        }

        try {
            if (tre.fjernAlle(0) != 0) {
                antallFeil++;
                System.out.println("Oppgave 5p: Feil i fjernAlle(T) for tomt tre!");
            }
        } catch (Exception e) {
            antallFeil++;
            System.out.println
                    ("Oppgave 5q: Kaster unntak i fjernAlle(T) for tomt tre!");
        }

        tre.leggInn(0);

        try {
            if (tre.fjernAlle(0) != 1) {
                antallFeil++;
                System.out.println
                        ("Oppgave 5r: Feil i fjernAlle(T) for tre med en verdi!");
            }
        } catch (Exception e) {
            antallFeil++;
            System.out.println
                    ("Oppgave 5s: Kaster unntak i fjernAlle(T) for tre med en verdi!");
        }

        int[] b = {1, 4, 1, 3, 1, 2, 1, 1};
        for (int verdi : b) tre.leggInn(verdi);

        if (tre.fjernAlle(1) != 5) {
            antallFeil++;
            System.out.println("Oppgave 5t: Feil i fjernAlle(T)!");
        }

        s = tre.toString();
        if (!s.equals("[2, 3, 4]")) {
            antallFeil++;
            System.out.println("Oppgave 5u: Feil i fjernAlle(T)!");
        }

        tre = new ObligSBinTre<>(Comparator.naturalOrder());

        Random r = new Random();
        for (int i = 0; i < 500_000; i++) tre.leggInn(r.nextInt(1_000_000));

        long tid = System.currentTimeMillis();
        tre.nullstill();
        tid = System.currentTimeMillis() - tid;

        if (tid < 10) {
            antallFeil++;
            System.out.println("Oppgave 5v: Har du kodet nullstill() ved kun");
            System.out.println("nullstille hode og antall? Alle nodeverdier og");
            System.out.println("pekere i treet skal nulles!");
        }
        assertEquals(antallFeil, 0);

 */
    }  // slutt på Oppgave 5


    // OPPGAVE 6 ////////////////////////////////////////////////
    @org.junit.jupiter.api.Test
    void oppgave6() {
        int antallFeil = 0;

        com.oblig3.ObligSBinTre<Integer> tre =
                new ObligSBinTre<>(Comparator.naturalOrder());

        String s = null;

        try {
            s = tre.høyreGren();
            if (!s.equals("[]")) {
                antallFeil++;
                System.out.println("Oppgave 6a: Det skal bli [] for et tomt tre!");
            }
        } catch (Exception e) {
            antallFeil++;
            System.out.println
                    ("Oppgave 6b: Det skal ikke kastes unntak for et tomt tre!");
        }

        tre.leggInn(3);
        s = tre.høyreGren();

        if (!s.equals("[3]")) {
            antallFeil++;
            System.out.print("Oppgave 6c: Feil - du har " + s + ", det skal");
            System.out.println(" være [3].");
        }

        int[] a = {1, 8, 2, 4, 7, 5, 6, 6};
        for (int verdi : a) tre.leggInn(verdi);

        s = tre.høyreGren();
        if (!s.equals("[3, 8, 4, 7, 5, 6, 6]")) {
            antallFeil++;
            System.out.print("Oppgave 6d: Feil - du har " + s + ", det skal");
            System.out.println(" være [3, 8, 4, 7, 5, 6, 6].");
        }

        // Et nytt tre
        tre = new ObligSBinTre<>(Comparator.naturalOrder());

        tre.leggInn(4);
        tre.leggInn(3);
        tre.leggInn(2);
        tre.leggInn(1);

        s = tre.høyreGren();
        if (!s.equals("[4, 3, 2, 1]")) {
            antallFeil++;
            System.out.print("Oppgave 6e: Feil - du har " + s + ", det skal");
            System.out.println(" være [4, 3, 2, 1].");
        }

        s = tre.lengstGren();

        if (!s.equals("[4, 3, 2, 1]")) {
            antallFeil++;
            System.out.print("Oppgave 6f: Feil - du har " + s + ", det skal");
            System.out.println(" være [4, 3, 2, 1].");
        }

        // Et nytt tre
        com.oblig3.ObligSBinTre<String> tre2 =
                new ObligSBinTre<>(Comparator.naturalOrder());

        try {
            s = tre2.lengstGren();
            if (!s.equals("[]")) {
                antallFeil++;
                System.out.print("Oppgave 6g: Feil - du har " + s + ", det skal");
                System.out.println(" være [].");
            }
        } catch (Exception e) {
            antallFeil++;
            System.out.println
                    ("Oppgave 6h: Det skal ikke kastes unntak for et tomt tre!");
        }

        tre2.leggInn("F");
        s = tre2.lengstGren();

        if (!s.equals("[F]")) {
            antallFeil++;
            System.out.print("Oppgave 6i: Feil - du har " + s + ", det skal");
            System.out.println(" være [F].");
        }

        tre2.leggInn("B");
        tre2.leggInn("AAAAAAAAAAAAAAAAAAAA");
        String[] verdi = "HDGOCEKPJMIL".split("");
        for (String t : verdi) tre2.leggInn(t);

        s = tre2.lengstGren();

        if (!s.equals("[F, H, O, K, J, I]")) {
            antallFeil++;
            System.out.print("Oppgave 6j: Feil - du har " + s + ", det skal");
            System.out.println(" være [F, H, O, K, J, I].");
        }
        assertEquals(antallFeil, 0);
    }  // slutt på Oppgave 6


    // OPPGAVE 7 ////////////////////////////////////////////////
    @org.junit.jupiter.api.Test
    void oppgave7() {
        int antallFeil = 0;

        com.oblig3.ObligSBinTre<Integer> tre =
                new ObligSBinTre<>(Comparator.naturalOrder());

        String[] s = tre.grener();

        try {
            if (s.length != 0) {
                antallFeil++;
                System.out.println
                        ("Oppgave 7a: Feil i grener() for tomt tre!");
            }
        } catch (Exception e) {
            antallFeil++;
            System.out.println
                    ("Oppgave 7b: Metoden grener() skal ikke kaste unntak for et tomt tre!");
        }

        tre.leggInn(10);
        s = tre.grener();
        String t = Arrays.toString(s);

        if (!t.equals("[[10]]")) {
            antallFeil++;
            System.out.println
                    ("Oppgave 7c: Feil i grener() for et tre med en verdi!");
        }

        tre.leggInn(6);
        tre.leggInn(9);
        tre.leggInn(7);
        tre.leggInn(8);
        t = Arrays.toString(tre.grener());

        if (!t.equals("[[10, 6, 9, 7, 8]]")) {
            antallFeil++;
            System.out.println
                    ("Oppgave 7d: Feil grener() for tre med en gren!");
        }

        tre.nullstill();

        int[] a = {4, 1, 6, 3, 5, 8, 2, 7, 9};
        for (int verdi : a) tre.leggInn(verdi);
        s = tre.grener();

        if (!s[0].equals("[4, 1, 3, 2]") || !s[1].equals("[4, 6, 5]")
                || !s[2].equals("[4, 6, 8, 7]") || !s[3].equals("[4, 6, 8, 9]")) {
            antallFeil++;
            System.out.println
                    ("Oppgave 7e: Feil i metoden grener()!");
        }
        assertEquals(antallFeil, 0);
    }  // slutt på Oppgave 7


    // OPPGAVE 8 ////////////////////////////////////////////////
    @org.junit.jupiter.api.Test
    void oppgave8() {
        int antallFeil = 0;

        com.oblig3.ObligSBinTre<Integer> tre =
                new ObligSBinTre<>(Comparator.naturalOrder());

        String s = tre.bladnodeverdier();

        try {
            if (!s.equals("[]")) {
                antallFeil++;
                System.out.println
                        ("Oppgave 8aa: Skal returnere [] for tomt tre!");
            }
        } catch (Exception e) {
            antallFeil++;
            System.out.println
                    ("Oppgave 8ab: Skal ikke kaste unntak for et tomt tre!");
        }

        tre.leggInn(10);
        s = tre.bladnodeverdier();

        if (!s.equals("[10]")) {
            antallFeil++;
            System.out.println
                    ("Oppgave 8ac: Feil - du har " + s + ", det skal være [10].");
        }

        tre.leggInn(11);
        s = tre.bladnodeverdier();

        if (!s.equals("[11]")) {
            antallFeil++;
            System.out.println
                    ("Oppgave 8ad: Feil - du har " + s + ", det skal være [11].");
        }

        int[] a = {7, 5, 8, 15, 6, 9, 13, 16, 12, 14};
        for (int verdi : a) tre.leggInn(verdi);
        s = tre.bladnodeverdier();

        if (!s.equals("[6, 9, 12, 14, 16]")) {
            antallFeil++;
            System.out.println
                    ("Oppgave 8ae: Feil - du har " + s + ", det skal være [6, 9, 12, 14, 16].");
        }

        tre.nullstill();

        for (int i = 1; i <= 5; i++) tre.leggInn(i);
        s = tre.bladnodeverdier();

        if (!s.equals("[5]")) {
            antallFeil++;
            System.out.println
                    ("Oppgave 8af: Feil - du har " + s + ", det skal være [5].");
        }

        tre.nullstill();

        for (int i = 5; i >= 1; i--) tre.leggInn(i);
        s = tre.bladnodeverdier();

        if (!s.equals("[1]")) {
            antallFeil++;
            System.out.println
                    ("Oppgave 8ag: Feil - du har " + s + ", det skal være [1].");
        }

        tre.nullstill();

        String poststring = tre.postString();
        if (poststring.compareTo("[]") != 0) {
            antallFeil++;
            System.out.println
                    ("Oppgave 8ba: Feil - du har " + poststring + ", det skal være [].");
        }

        tre.leggInn(10);
        poststring = tre.postString();
        if (poststring.compareTo("[10]") != 0) {
            antallFeil++;
            System.out.println
                    ("Oppgave 8bb: Feil - du har " + poststring + ", det skal være [10].");
        }

        tre.leggInn(15);
        poststring = tre.postString();
        if (poststring.compareTo("[15, 10]") != 0) {
            antallFeil++;
            System.out.println
                    ("Oppgave 8bc: Feil - du har " + poststring + ", det skal være [15, 10].");
        }

        int[] verdier = {5, 3, 8, 7, 6, 11, 16, 14, 17, 12, 13};
        for (int k : verdier) tre.leggInn(k);
        poststring = tre.postString();
        String svar = "[3, 6, 7, 8, 5, 13, 12, 14, 11, 17, 16, 15, 10]";
        if (poststring.compareTo(svar) != 0) {
            antallFeil++;
            System.out.println
                    ("Oppgave 8bd: Feil - du har " + poststring + ",");
            System.out.println
                    ("             det skal være " + svar);
        }
        assertEquals(antallFeil, 0);
    }  // slutt på Oppgave 8


    // OPPGAVE 9 ////////////////////////////////////////////////
    @org.junit.jupiter.api.Test
    void oppgave9() {
        int antallFeil = 0;

        com.oblig3.ObligSBinTre<Integer> tre =
                new ObligSBinTre<>(Comparator.naturalOrder());

        Iterator<Integer> i = tre.iterator();

        try {
            i.next();
            antallFeil++;
            System.out.println("Oppgave 9a: Skal kaste unntak når treet er tomt!");
        } catch (Exception e) {
            if (!(e instanceof NoSuchElementException)) {
                antallFeil++;
                System.out.println
                        ("Oppgave 9b: Skal kaste NoSuchElementException her!");
            }
        }

        tre.leggInn(10);
        i = tre.iterator();

        if (i.next().compareTo(10) != 0) {
            antallFeil++;
            System.out.println("Oppgave 9c: Her skal next() returnere 10!");
        }

        try {
            i.next();
            antallFeil++;
            System.out.println
                    ("Oppgave 9d: Skal kaste unntak når det ikke er flere igjen!");
        } catch (Exception e) {
            if (!(e instanceof NoSuchElementException)) {
                antallFeil++;
                System.out.println
                        ("Oppgave 9e: Skal kaste NoSuchElementException her!");
            }
        }

        tre.nullstill();

        int[] a = {5, 2, 8, 1, 4, 6, 9, 3, 7};
        for (int k : a) tre.leggInn(k);

        List<Integer> liste = new ArrayList<>();
        tre.forEach(verdi -> liste.add(verdi));
        String s = liste.toString();

        if (!s.equals("[1, 3, 7, 9]")) {
            antallFeil++;
            System.out.println
                    ("Oppgave 9f: Feil - du har " + s + ", det skal være [1, 3, 7, 9].");
            System.out.println
                    ("            Din iterator skal kun gå gjennom bladnodene!");

        }

        tre.nullstill();
        tre.leggInn(1);
        tre.leggInn(2);
        tre.leggInn(3);
        tre.leggInn(4);

        int verdi = tre.iterator().next();
        if (verdi != 4) {
            antallFeil++;
            System.out.println
                    ("Oppgave 9g: Treet har kun 4 som bladnodeverdi!");
        }

        tre.nullstill();
        tre.leggInn(1);
        i = tre.iterator();
        tre.leggInn(2);
        try {
            i.next();

            antallFeil++;

            System.out.println
                    ("Oppgave 9h: Har du oppdatert endringer i leggInn?");
        } catch (Exception e) {
            if (!(e instanceof ConcurrentModificationException)) {
                antallFeil++;
                System.out.println
                        ("Oppgave 9i: Her skal du kaste en ConcurrentModificationException!");
            }
        }

        tre.nullstill();
        tre.leggInn(1);
        tre.leggInn(2);
        i = tre.iterator();
        tre.fjern(2);
        try {
            i.next();

            antallFeil++;

            System.out.println
                    ("Oppgave 9j: Har du oppdatert endringer i fjern(verdi)?");
        } catch (Exception e) {
            if (!(e instanceof ConcurrentModificationException)) {
                antallFeil++;
                System.out.println
                        ("Oppgave 9k: Her skal du kaste en ConcurrentModificationException!");
            }
        }

        tre.nullstill();
        tre.leggInn(1);
        tre.leggInn(2);
        i = tre.iterator();
        tre.nullstill();
        try {
            i.next();

            antallFeil++;

            System.out.println
                    ("Oppgave 9l: Har du oppdatert endringer i nullstill()?");
        } catch (Exception e) {
            if (!(e instanceof ConcurrentModificationException)) {
                antallFeil++;
                System.out.println
                        ("Oppgave 9m: Her skal du kaste en ConcurrentModificationException!");
            }
        }


        assertEquals(antallFeil, 0);
    }  // slutt på Oppgave 9


    // OPPGAVE 10 ////////////////////////////////////////////////
    @org.junit.jupiter.api.Test
    void oppgave10() {
        int antallFeil = 0;

        com.oblig3.ObligSBinTre<Integer> tre =
                new ObligSBinTre<>(Comparator.naturalOrder());

        Iterator<Integer> i = tre.iterator();

        try {
            i.remove();
            antallFeil++;
            System.out.println("Oppgave 10a: Skal kaste unntak når treet er tomt!");
        } catch (Exception e) {
            if (!(e instanceof IllegalStateException)) {
                antallFeil++;
                System.out.println
                        ("Oppgave 10b: Skal kaste IllegalStateException her!");
            }
        }

        tre.leggInn(2);
        tre.leggInn(1);
        tre.leggInn(3);
        i = tre.iterator();
        i.next();
        i.remove();

        try {
            i.remove();
            antallFeil++;
            System.out.println("Oppgave 10c: Ikke tillatt med remove() her!");
        } catch (Exception e) {
            if (!(e instanceof IllegalStateException)) {
                antallFeil++;
                System.out.println
                        ("Oppgave 10d: Skal kaste IllegalStateException her!");
            }
        }

        tre.nullstill();
        tre.leggInn(1);
        tre.leggInn(2);
        i = tre.iterator();
        Iterator<Integer> j = tre.iterator();
        i.next();
        i.remove();
        try {
            j.next();

            antallFeil++;

            System.out.println
                    ("Oppgave 10e: Har du oppdatert endringer i iteratorens remove()?");
        } catch (Exception e) {
            if (!(e instanceof ConcurrentModificationException)) {
                antallFeil++;
                System.out.println
                        ("Oppgave 10f: Her skal du kaste en ConcurrentModificationException!");
            }
        }

        tre.nullstill();

        tre.leggInn(2);
        tre.leggInn(1);
        tre.leggInn(3);
        i = tre.iterator();
        i.next();
        i.remove();
        try {
            i.next();
        } catch (Exception e) {
            antallFeil++;
            System.out.println
                    ("Oppgave 10g: Har du oppdatert iteratorendringer i remove()?");
        }

        tre.nullstill();

        int[] a = {7, 1, 11, 3, 8, 12, 2, 5, 10, 4, 6, 9};
        for (int verdi : a) tre.leggInn(verdi);

        try {
            tre.fjernHvis(x -> x % 2 == 0);  // fjerner bladnoder med partall

            if (!tre.toString().equals("[1, 3, 5, 7, 8, 9, 10, 11]")) {
                antallFeil++;
                System.out.println("Oppgave 10h: Feil i metoden remove()!");
            }

            if (tre.antall() != 8) {
                antallFeil++;
                System.out.println
                        ("Oppgave 10i: Feil - må ha antall-- i remove()!");
            }
        } catch (Exception e) {
            antallFeil++;
            System.out.println
                    ("Oppgave 10j: Skal ikke kaste unntak her!");
        }

        tre.fjernHvis(x -> x % 2 != 0);  // fjerner bladnoder med oddetall

        if (!tre.toString().equals("[1, 3, 7, 8, 10, 11]")) {
            antallFeil++;
            System.out.println("Oppgave 10k: Feil i metoden remove()!");
        }

        tre.fjernHvis(x -> true);

        if (!tre.toString().equals("[1, 7, 8, 11]")) {
            antallFeil++;
            System.out.println("Oppgave 10l: Feil i metoden remove()!");
        }

        tre.fjernHvis(x -> true);

        if (!tre.toString().equals("[7, 11]")) {
            antallFeil++;
            System.out.println("Oppgave 10m: Feil i metoden remove()!");
        }

        tre.fjernHvis(x -> true);

        if (!tre.toString().equals("[7]")) {
            antallFeil++;
            System.out.println("Oppgave 10n: Feil i metoden remove()!");
        }

        tre.fjernHvis(x -> true);

        if (!tre.toString().equals("[]")) {
            antallFeil++;
            System.out.println("Oppgave 10o: Feil i metoden remove()!");
        }
        assertEquals(antallFeil, 0);
    }  // slutt på Oppgave 10

} // Oblig3Test
