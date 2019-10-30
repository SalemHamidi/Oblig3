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

    // konstruktør
    private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder) {
        this.verdi = verdi;
        venstre = v; høyre = h;
        this.forelder = forelder;
    }

    // konstruktør
    private Node(T verdi, Node<T> forelder){
        this(verdi, null, null, forelder);
    }

    @Override
    public String toString(){
        return "" + verdi;
    }
  } // class Node

  private Node<T> rot;                            // peker til rotnoden
  private int antall;                             // antall noder
  private int endringer;                          // antall endringer

  private final Comparator<? super T> comp;       // komparator

  public ObligSBinTre(Comparator<? super T> c){    // konstruktør
      rot = null;
      antall = 0;
      comp = c;
  }

  @Override
  public boolean leggInn(T verdi) {
      Objects.requireNonNull(verdi, "Ulovlig med nullverdier!");

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
      }
      else if (cmp < 0) {
          q.venstre = p;                              // venstre barn til q
      }
      else {
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
          if (cmp < 0) p = p.venstre;
          else if (cmp > 0) p = p.høyre;
          else return true;
      }
      return false;
  }
  
  @Override
  public boolean fjern(T verdi) {
      if(verdi == null) {
          return false;  // treet har ingen nullverdier
      }

      Node<T> p = rot;
      Node<T> q = null;   // q skal være forelder til p

      while(p != null) {           // leter etter verdi

      int temp = comp.compare(verdi, p.verdi);      // sammenligner

      if(temp < 0) {
          q = p;
          p = p.venstre;
      }      // går til venstre

      else if (temp > 0) {
          q = p;
          p = p.høyre;
      }   // går til høyre
      else break;    // den søkte verdien ligger i p
      }

      if (p == null) {
        return false;   // finner ikke verdi
      }

      if(p.venstre == null || p.høyre == null){  // Tilfelle 1) og 2)
          Node<T> b = p.venstre != null ? p.venstre : p.høyre;  // b for barn
          if (p == rot) {
            rot = b;
          }
          else if (p == q.venstre) {
            q.venstre = b;
          }
          else {
            q.høyre = b;
          }
      }
      else { // Tilfelle 3)
      Node<T> s = p;
      Node<T> r = p.høyre;   // finner neste i inorden

      while (r.venstre != null) {
        s = r;    // s er forelder til r
        r = r.venstre;
      }

      p.verdi = r.verdi;   // kopierer verdien i r til p

      if (s != p) {
        s.venstre = r.høyre;
      }
      else{
        s.høyre = r.høyre;
      }
    }
    antall--;   // det er nå én node mindre i treet
    return true;
  }
  
  public int fjernAlle(T verdi) {
    if(tom()) {
      return 0;
    }

    int verdiAntall = 0;
    while (fjern(verdi)) {
      verdiAntall++;
    }
    return verdiAntall;
  }

  @Override
  public int antall() {
    return antall;
  }

  public int antall(T verdi) {
      Node<T> p = rot;
      int antall = 0;

      while (p != null)
      {
          int cmp = comp.compare(verdi,p.verdi);
          if (cmp < 0) {
              p = p.venstre;
          }
          else {
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
      nullstill();  // nullstiller
    }
    rot = null;
    antall = 0;      // treet er nå tomt
  }
  
  private static <T> Node<T> nesteInorden(Node<T> p) {
      //Finner første verdien i inorden på et binært tre
      //Altså det som er nederst til venstre
      while (p.venstre != null) {
          p = p.venstre;
          return p;
      }
      //Dersom p har høyre barn blir dette neste inorden-verdi
      if(p.høyre != null) {  //p har høyre barn
          return p.høyre;
      }
      else { // må gå oppover i treet
          while (p.forelder != null && p.forelder.høyre == p) {
              p = p.forelder;
          }
          return p.forelder;
      }
  }

  @Override
  public String toString() {
    //Dersom tabellen er tom skriv ut "[]"
    if(tom()) {
      return "[]";
    }

    StringBuilder s = new StringBuilder();
    s.append('[');
    Node<T> p = rot; //Lag første node

    //Sett første node som p.venstre - Første inorden verdien
    while(p.venstre != null){
      p = p.venstre;
    }
    s.append(p.verdi);

    //Dersom nesteInorden sin verdi ikke er null - skriv ut
    while(nesteInorden(p) != null) {
      s.append(", ");
      p = nesteInorden(p);
      s.append(p.verdi);
    }
      s.append(']');
      return s.toString();
    }

  public String omvendtString() {
    throw new UnsupportedOperationException("Ikke kodet ennå!");
  }
  
  public String høyreGren() {
    throw new UnsupportedOperationException("Ikke kodet ennå!");
  }
  
  public String lengstGren() {
    throw new UnsupportedOperationException("Ikke kodet ennå!");
  }
  
  public String[] grener() {
    throw new UnsupportedOperationException("Ikke kodet ennå!");
  }
  
  public String bladnodeverdier() {
    throw new UnsupportedOperationException("Ikke kodet ennå!");
  }
  
  public String postString() {
    throw new UnsupportedOperationException("Ikke kodet ennå!");
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
