import java.util.Scanner;
import java.util.LinkedList;
/*
 * PERMUTACIJE
 * Permutacije lahko zapisemo na mnogo razlicnih nacinov.
 * Ta program pretvarja permutacije predtsavljene s tabelo
 * inverzij (za vec info glej besedilo naloge) v ciklicni
 * zapis
 */
public class Permutacije {
    public static void main(String[] args) {
        // preberemo vhodni podatek o dolzini permutacije
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // permutacijo zapisemo z "besedp"
        int[] permutacija = zBesedo(n, sc);
        // izpisemo permutacijo v ciklicni obliki
        write(permutacija);
    }
    
    // funkcija za pretvorbo iz tabele inverzij v navaden
    // zapis (= z besedo0)
    public static int[] zBesedo(int n, Scanner sc) {
        // kreiranje nove tabele
        int[] p = new int[n];
        // vstavljanje stavil v pravilno zaporedje permutacije
        for (int i = 1; i <= n; i++) {
            // preberemo novo stevilo, ki predstavlja stevilo
            // elementov, ko so vecji od elementa i in se v
            // zapisu permutacije z "besedo" pojavijo levo od
            // elementa i
            int a = sc.nextInt();
            // s pomocjo funkcije predhodnik in while zanke
            // ugotovimo pravi indeks elementa i v tabeli
            int id = predhodniki(p, a);
            while (p[id] != 0) {
                id++;
            }
            // element zapisemo v tabelo
            p[id] = i;
        }
        // vrnemo tabelo
        return p;
    }
    
    // element ima pred seboj n elementov ki so vecji od njega,
    // ta funkcija pa nam pove, koliko manjsih elementov je
    // v zapisu pred njim
    public static int predhodniki(int[] p, int n) {
        for (int i = 0; i <= n && i < p.length; i++) {
            // ce je na mestu i element, ki ni enak 0, potem
            // je to predodnik elementa
            if (p[i] != 0) {
                // povecamo vrstni red elementa
                n++;
            }
        }
        return n;
    }
    
    // izpis permutacije v ciklicni obliki
    public static void write(int[] p) {
        int n = p.length;
        //inicializacija niza za izpis
        String niz = "";
        // za vsako ponovitev notranje zanke stevec n zmanjsamo
        // in na ta nacin "precesemo" vse elemente
        while (n > 0) {
            // kreiramo povezan seznam (ker ne vemo dolzine cikla)
            LinkedList l = new LinkedList();
            // s funkcijo ugotovim, kateri je najmanjsi element,
            // ki ga se nismo obravnavali (v prvi iteraciji bo to 1)
            int id = min(p);
            // dodamo element v seznam
            l.addFirst(id+1);
            // stevilo elementov v tabeli je sedaj za 1 manjse
            n--;
            // ponavljamo notranjo zanko, dokler ne bomo ponovno
            // srecali prvega elementa v ciklu
            while (((int) l.getFirst()) != p[id]) {
                // na zadnje mesto seznama dodamo nasledni element
                l.addLast(p[id]);
                int tmp = id;
                // nastavimo index tabele, da bo kazal na nasledni element
                id = p[id] - 1;
                // oznacimo, da smo ta element ze obravnavali
                p[tmp] = 0;
                n--;
            }
            p[id] = 0;
            // kreiranje niza za izpis
            niz = niz + "(";
            while (l.size() != 0) {
                niz = niz + Integer.toString((int) l.getFirst()) + " ";
                l.removeFirst();
            }
            niz = niz.substring(0, niz.length()-1) + ") ";
        }
        // izpis niza permutacije ciklicne oblike
        System.out.println(niz.substring(0, niz.length() - 1));
    }
    
    // funkcija poisce najmanjsi element v tabeli,
    // ki ga se nismo obravlavali
    public static int min(int[] p) {
        int min = p.length;
        for (int i = 0; i < p.length; i++) {
            // najmanjsi element bo bil tisti, ki je manjsi
            // od min in ki se ne preslika v 0
            if (p[i] != 0 && i < min) {
                min = i;
            }
        }
        return min;
    }
}
