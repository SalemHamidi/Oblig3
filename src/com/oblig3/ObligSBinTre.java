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
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else return true;
        }
        return false;
    }

    @Override
    public boolean fjern(T verdi)
    {
        if (verdi == null) return false;

        Node<T> p = rot;
        Node<T> q = null;           // q skal være forelder til p

        while (p != null) {         // leter etter verdi
            int cmp = comp.compare(verdi, p.verdi);             // sammenligner
            if (cmp < 0) {
                q = p;
                p = p.venstre;
            }
            else if (cmp > 0) {
                q = p;
                p = p.høyre;
            }
            else break;             // den søkte verdien ligger i p
        }
        if (p == null) {
            return false;       // fant ikke verdien
        }

        if (p.venstre == null || p.høyre == null) {
            Node<T> b = p.venstre != null ? p.venstre : p.høyre;
            if (b != null) {
                if (p == rot) {
                    rot = b;
                }
                else if (p == q.venstre) {
                    q.venstre = b;
                    b.forelder = q;
                }
                else {
                    q.høyre = b;
                    b.forelder = q;
                }
            } else {
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
        }
        else {
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
            }
            else {
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
            return;
        } else {
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
                    fjern(p.verdi);
                    p = q.forelder;
                }
            }
        }
    }

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
        while(p.venstre != null) {
            p = p.venstre;
        }
        //Dersom nesteInorden sin verdi ikke er null - skriv ut
        for(int i = 0; i < antall; i++) {
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

      for(; p.høyre != null; p = p.høyre) {
          stack.push(p);
      }

      while(true) {
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
      if(tom()) {
          return "[]";
      }
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
/*
//Finner første verdien i inorden på et binært tre
      //Altså det som er nederst til venstre
      while (p.venstre != null) {
          p = p.venstre;
          return p;
      }
      Node<T> q = p.forelder;

      //Dersom p har høyre barn blir dette neste inorden-verdi
      if(p.høyre != null) {
          return p.høyre;
      }
      else {    // må gå oppover i treet
          while (p.forelder != null && p.forelder.høyre == p) {
              p = p.forelder;
          }
          return p;
      }
 */