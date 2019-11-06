//Skrevet av Salem Hamidi - S333946
package com.oblig3;

////////////////// ObligSBinTre /////////////////////////////////

import java.util.*;

public class ObligSBinTre<T> implements Beholder<T> {
    private static final class Node<T> {                // en indre nodeklasse

        private T verdi;                   // nodens verdi
        private Node<T> venstre;           //Venstre barn
        private Node<T> høyre;             //Høyre barn
        private Node<T> forelder;          // forelder

        private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder) {
            this.verdi = verdi;
            venstre = v;
            høyre = h;
            this.forelder = forelder;
        }

        // konstruktør
        private Node(T verdi, Node<T> forelder) {
            this(verdi, null, null, forelder);
        }

        @Override
        public String toString() {
            return "" + verdi;
        }
    } // class Node

    private Node<T> rot;                            // peker til rotnoden
    private int antall;                             // antall noder
    private int endringer;                          // antall endringer

    private final Comparator<? super T> comp;       // komparator

    public ObligSBinTre(Comparator<? super T> c) {    // konstruktør
        rot = null;
        antall = 0;
        comp = c;
    }

    @Override
    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi, "Ulovlig med nullverdier!");

        if (tom()) {
            rot = new Node<>(verdi, null);
            antall++;
            return true;
        }

        Node<T> p = rot;                                // p starter i roten
        Node<T> q = null;
        int cmp = 0;                                    // hjelpevariabel

        while (p != null)                               // fortsetter til p er ute av treet
        {
            q = p;                                      // q er forelder til p
            cmp = comp.compare(verdi, p.verdi);         // bruker komparatoren
            p = cmp < 0 ? p.venstre : p.høyre;          // flytter p
        }

        // p er nå null, dvs. ute av treet, q er den siste vi passerte

        p = new Node<>(verdi, q);                       // oppretter en ny node

        if (q == null) {
            rot = p;                                    // p blir rotnode
        } else if (cmp < 0) {
            q.venstre = p;                              // venstre barn til q
        } else {
            q.høyre = p;                               // høyre barn til q
        }

        antall++;                                       // én verdi mer i treet
        return true;                                    // vellykket innlegging
    }

    @Override
    public boolean inneholder(T verdi) {
        if (verdi == null) {
            return false;
        }

        Node<T> p = rot;

        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) {
                p = p.venstre;
            } else if (cmp > 0) {
                p = p.høyre;
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean fjern(T verdi) {
        if (verdi == null) {
            return false;
        }

        Node<T> p = rot;
        Node<T> q = null;           // q skal være forelder til p

        while (p != null) {         // leter etter verdi
            int cmp = comp.compare(verdi, p.verdi);             // sammenligner
            if (cmp < 0) {
                q = p;
                p = p.venstre;
            } else if (cmp > 0) {
                q = p;
                p = p.høyre;
            } else break;             // den søkte verdien ligger i p
        }
        if (p == null) {
            return false;       // fant ikke verdien
        }

        if (p.venstre == null || p.høyre == null) {
            Node<T> b = p.venstre != null ? p.venstre : p.høyre;
            if (b != null) {
                if (p == rot) {
                    rot = b;
                } else if (p == q.venstre) {
                    q.venstre = b;
                    b.forelder = q;
                } else {
                    q.høyre = b;
                    b.forelder = q;
                }
            } else {
                if (p == rot) {
                    rot = b;
                } else if (p == q.venstre) {
                    q.venstre = b;
                } else {
                    q.høyre = b;
                }
            }
        } else {
            Node<T> s = p;
            Node<T> r = p.høyre;
            while (r.venstre != null) {
                s = r;
                r = r.venstre;
            }

            p.verdi = r.verdi;

            if (s != p) {
                s.venstre = r.høyre;
                if (r.høyre != null) {
                    r.høyre.forelder = s;
                }
            } else {
                s.høyre = r.høyre;
                if (r.høyre != null) {
                    r.høyre.forelder = s;
                }
            }
        }
        antall--;                       // det er nå én node mindre i treet
        return true;
    }

    public int fjernAlle(T verdi) {
        Objects.requireNonNull(verdi, "Ulovlig med nullverdier");

        if (tom()) {
            return 0;
        }

        int antall = 0;

        while (inneholder(verdi)) {
            fjern(verdi);
            antall++;
        }
        return antall;
    }

    @Override
    public int antall() {
        return antall;
    }

    public int antall(T verdi) {
        Node<T> p = rot;
        int antall = 0;

        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) {
                p = p.venstre;
            } else {
                if (cmp == 0) {
                    antall++;
                }
                p = p.høyre;
            }
        }
        return antall;
    }

    @Override
    public boolean tom() {
        return antall == 0;
    }

    @Override
    public void nullstill() {
        if (!tom()) {
            antall = 0;
        }
        Node<T> p = rot;

        //evt while rot  != null?
        while (p.høyre != null && p.venstre != null) {
            if (p.venstre != null) {
                p = p.venstre;
            }
            if (p.høyre != null) {
                p = p.høyre;
            } else if (p.høyre == null && p.venstre == null) {
                p = p.forelder;
            }

        }
        p.venstre = null;
        p.høyre = null;
    }

    //implementasjonen er litt feil, men det jeg ville ha gjort er å bruke en dybde først metode som rekursivt går
    //gjennom hver enestre gren og setter p.høyre && p.venstre = null
    //helt til den kommer til rot noden som dermed kun vil ha ingen barn hvor man fjerner p = rot = null
        /*else {
            Node<T> p = rot, q;
            while (!tom()) {    //Så lengde treet ikke er tomt, fortsett
                if (p.venstre != null) {    //Start på inorden
                    p = p.venstre;
                } else if (p.høyre != null) { //Start på postorden
                    p = p.høyre;
                } else if (antall == 1) {    // sletter siste element
                    fjern(p.verdi);
                } else {    // utfører fjerning
                    q = p;
                    q = p;
                    fjern(p.verdi);
                    p = q.forelder;
                }
            }
        }
        */

    private static <T> Node<T> nesteInorden(Node<T> p) {
        //Vi vet allerde hvilken verdi som er den første
        //dermed blir den andre inorden den til høyre dersom den finnes
        if (p.høyre != null) {
            p = p.høyre;
            while (p.venstre != null) {
                p = p.venstre;
            }
        }
        //Dersom høyre er null, må man gå opp et nivå til foreldren til p
        else {
            while (p != null) {
                //sjekker om foreldre har en høyre
                if (p.forelder != null && p.forelder.høyre != p) {
                    return p.forelder;
                } else {
                    p = p.forelder;
                }
            }
        }
        return p;
    }

    @Override
    public String toString() {
        //Dersom tabellen er tom skriv ut "[]"
        if (tom()) {
            return "[]";
        }

        Node<T> p = rot;              //Lag første node

        StringBuilder s = new StringBuilder();        //Bruker StringBuilder for å lage stringen
        s.append("[");
        while (p.venstre != null) {
            p = p.venstre;
        }
        //Dersom nesteInorden sin verdi ikke er null - skriv ut
        for (int i = 0; i < antall; i++) {
            if (i == antall - 1) {
                s.append(p.verdi);      // glemmer komma på siste plass for ryddighet
            } else {
                s.append(p.verdi + ", "); // legger inn første verdi
            }
            p = nesteInorden(p);
        }
        s.append("]");
        return s.toString();
    }


    public String omvendtString() {
        //Dersom treet er tomt
        if (tom()) {
            return "[]";
        }

        StringJoiner s = new StringJoiner(", ", "[", "]");

        Stack<Node<T>> stack = new Stack<>();          //Lager en stack
        Node<T> p = rot;                               //Starter i roten og går til ventre

        for (; p.høyre != null; p = p.høyre) {
            stack.push(p);
        }

        while (true) {
            s.add(p.verdi.toString());
            if (p.venstre != null) {
                for (p = p.venstre; p.høyre != null; p = p.høyre) {
                    stack.push(p);
                }
            } else if (!stack.isEmpty()) {
                p = stack.pop();
            } else {
                break;
            }
        }
        return s.toString();
    }

    public String høyreGren() {
        if (tom()) {
            return "[]";
        }

        Node<T> p = rot;

        StringJoiner s = new StringJoiner(", ", "[", "]");

        s.add(p.verdi.toString());

        while(p.høyre != null || p.venstre != null){
            if(p.høyre != null){
                p = p.høyre;
                s.add(p.verdi.toString());
            }
            else{
                p = p.venstre;
                s.add(p.verdi.toString());
            }
        }
        return s.toString();
    }


    public String lengstGren() {
        Node<T> p = rot;
        StringBuilder s = new StringBuilder();

        if (tom()) {
            return "[]";
        }
        s.append("[");
        if (antall == 1) {
            s.append(p.verdi);
        }

        s.append("]");
        return toString();
    }

    public String[] grener() {
        Liste<String> liste = new TabellListe<>();
        StringBuilder s = new StringBuilder("[");

        if (!tom()) {
            grener(rot, liste, s);
        }

        String[] grener = new String[liste.antall()];           // oppretter tabell

        int i = 0;

        for (String gren : liste) {
            grener[i++] = gren;                   // fra liste til tabell
        }
        return grener;                          // returnerer tabellen
    }

    private void grener(Node<T> p, Liste<String> liste, StringBuilder s) {
        T verdi = p.verdi;
        int k = verdi.toString().length(); // lengden på verdi

        if (p.høyre == null && p.venstre == null) {  // bladnode

            liste.leggInn(s.append(verdi).append(']').toString());

            // må fjerne det som ble lagt inn sist - dvs. k + 1 tegn
            s.delete(s.length() - k - 1, s.length());
        } else {
            s.append(p.verdi).append(',').append(' ');  // legger inn k + 2 tegn
            if (p.venstre != null) {
                grener(p.venstre, liste, s);
            }
            if (p.høyre != null) {
                grener(p.høyre, liste, s);
            }
            s.delete(s.length() - k - 2, s.length());   // fjerner k + 2 tegn
        }
    }

    public String bladnodeverdier() {

        Node<T> node = rot;
        StringJoiner s = new StringJoiner(", ", "[", "]");

        finnbladnoder(node, s);

        return s.toString();
    }

    //Rekursiv hjelpemetode
    public void finnbladnoder(Node node, StringJoiner s) {
        if (node == null) {
            return;
        }
        //En node er kun en bladnode dersom den ikke har barn
        if (node.høyre == null && node.venstre == null) {
            s.add(node.verdi.toString());
        }

        finnbladnoder(node.venstre, s);
        finnbladnoder(node.høyre, s);
    }

    public String postString(){

        if(tom()){
            return ("[]");
        }

        if(antall == 1){
            return ("[" + rot + "]");
        }

        StringBuilder s = new StringBuilder("[");
        //Starter i første noden
        Node<T> p = rot;

        while (true){
            if(p.venstre != null) {
                p = p.venstre;
            }
            else if(p.høyre != null) {
                p = p.høyre;
            }
            else break;
        }
        s.append(p.verdi);

        while (p != rot){
            if(p == p.forelder.høyre){
                p = p.forelder;
                s.append(", ").append(p.verdi);
            }
            else if(p == p.forelder.venstre){
                if(p.forelder.høyre == null){
                    p = p.forelder;
                    s.append(", ").append(p.verdi);
                }
                else {
                    p = p.forelder.høyre;
                    //Kompendiet 5.1.7 h
                    while (true){
                        if(p.venstre != null) {
                            p = p.venstre;
                        }
                        else if(p.høyre != null) {
                            p = p.høyre;
                        }
                        else break;
                    }
                    s.append(", ").append(p.verdi);
                }
            }
        }
        s.append("]");
        return s.toString();
    }
  
  @Override
  public Iterator<T> iterator() {
    return new BladnodeIterator();
  }
  
    private class BladnodeIterator implements Iterator<T> {
    private Node<T> p = rot, q = null;
    private boolean removeOK = false;
    private int iteratorendringer = endringer;
    
    private BladnodeIterator()  // konstruktør
    {
      throw new UnsupportedOperationException("Ikke kodet ennå!");
    }
    
    @Override
    public boolean hasNext() {
      return p != null;  // Denne skal ikke endres!
    }
    
    @Override
    public T next() {
      throw new UnsupportedOperationException("Ikke kodet ennå!");
    }
    
    @Override
    public void remove() {
      throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

  } // BladnodeIterator

} // ObligSBinTre
