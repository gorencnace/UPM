import java.lang.Math;
import java.util.Scanner;

/*
 * SIMULACIJA IGRE CLOVEK NE JEZI SE
*/

public class Ludo {
    public static void main(String[] args) {
        // Prebere, koliko metov kocke naj opravijo igralci
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // kreira igralno polje
        Polje igra = new Polje();
        // inicializacija stevca vrstenga reda igralcev
        int igralec = 0;
        
        int kocka;
        
        for (int i = 0; i < n; i++) {
            // metanje kocke s pomocjo funkcije random
            kocka = (int) ((Math.random() * 6) + 1);
            // ce so na zacetnem polju vse figure ima igralce
            // mozost treh zaporednih metov, dokler ne vrze 6
            if (igra.zacetnaPolja[igralec] == 4) {
                int j = 0;
                while (j < 2 && kocka != 6) {
                    kocka = (int) ((Math.random() * 6) + 1);
                    j++;
                }
                igra.premik(igralec, kocka);
            }
            else {
                igra.premik(igralec, kocka);
            }
            // ce igralec vrze 6 ima moznost dodatnega meta,
            // v nasprotnem primeru pa je na vrsti naslednji
            if (kocka != 6) {
                igralec = (igralec + 1) % 4;
            }
        }
        // izpis koncnega stanja igralnega polja
        igra.izpis();
    }
    
}
