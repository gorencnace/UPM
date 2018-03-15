import java.util.Arrays;

// razred ki implementira igralno plosco z vsemi
// potrebnimi operacijami

class Polje {
    // vsak element tabele predstavlja stevec kolicine
    // figur na izhodiscnem polju (# figur = 4)
    int[] zacetnaPolja;
    // igralno polje velikosti 40
    int[] polje;
    // tabela ki predstavlja koncna polja
    // vrstica tabele --> igralec
    // stolpec tabele --> mesto na koncnem polju
    int[][] koncnaPolja;
    
    // konstante, ki predstavljajo indekse zacetnih in
    // zadnjih polj (= polje pred koncnimi polji)
    final int aStart = 0;
    final int aEnd = 38;
    
    final int bStart = 10;
    final int bEnd = 8;
    
    final int cStart = 20;
    final int cEnd = 18;
    
    final int dStart = 30;
    final int dEnd = 28;
    
    // dolzina igralne plosce
    final int max = 40;
    
    // kreiranje igralnega polja
    Polje() {
        makenull();
    }
    // funkcija inicializira atribute igralnega polja
    // na zacetno postavitev - "pripravi igralno plosco"
    void makenull() {
        zacetnaPolja = new int[4];
        for (int i = 0; i < 4; i++) {
            zacetnaPolja[i] = 4;
        }
        polje = new int[40];
        koncnaPolja = new int[4][4];
    }
    // za vsakega igralca posebaj pripravimo, kje zacne
    // in kje konca
    public void premik(int igralec, int kocka) {
        if (igralec == 0) {
            premik(igralec, kocka, aStart, aEnd);
        } else if (igralec == 1) {
            premik(igralec, kocka, bStart, bEnd);
        } else if (igralec == 2) {
            premik(igralec, kocka, cStart, cEnd);
        } else if (igralec == 3) {
            premik(igralec, kocka, dStart, dEnd);
        }
    }
    
    private void premik(int igralec, int kocka, int start, int end) {
        // v primeru da igralec vrze 6,  da na zacetnem
        // polju ni njegove figure, in da imamo na
        // izhodiscnem polju kaksno figuro, jo postavimo
        // na zacetno polje
        if (kocka == 6 && polje[start] != igralec + 1) {
            if (zacetnaPolja[igralec] > 0) {
                // ce damo figuro na zacetno polje najprej
                // zbije morebitno nasprotno figuro, nato se
                // na to polje premakne
                zbij(start);
                polje[start] = igralec + 1;
                zacetnaPolja[igralec]--;
            }
        }
        // v nasprotnem primeru pa premaknemo drugo figuro po pravilih
        else {
            for (int i = 0; i <= max - 2; i = i + 1) {
                int j = i + start;
                // najprej najdemo prvo figuro najblizjo zacetnemu polju
                if (polje[j % max] == igralec + 1) {
                    // preverimo, ce figura se ni pred koncnimi polji
                    if (i <= max - 2 - kocka) {
                        // preverimo, ce je to veljavna poteza (ce s tem
                        // premikom ne bomo zbili lastne figure)
                        if (polje[(j + kocka) % max] != igralec + 1) {
                            // premaknemo figuro na polje in zbijemo morebitno
                            // nasprotno figuro
                            polje[j % max] = 0;
                            zbij((j + kocka) % max);
                            polje[(j + kocka) % max] = igralec + 1;
                            // koncamo potezo
                            return;
                        }
                    }
                    // ce je figura pred koncnimi polji jo probamo
                    // prestaviti na koncno polje, ce je le to mogoce
                    else if ((j % max) < end - kocka + 4){
                        int doKonca = kocka - ((end - (j % max)) % max) - 1;
                        if (koncnaPolja[igralec][doKonca] == 0) {
                            koncnaPolja[igralec][doKonca] = 1;
                            polje[j % max] = 0;
                            // koncamo potezo
                            return;
                        }
                    }
                }
            }
            // ce nismo uspeli premakniti nobene figure na polju
            // poskusimo premakniti se figure na koncnem polju
            for (int i = 0; i < 4; i++) {
                if (koncnaPolja[igralec][i] == 1) {
                    if (kocka <= 3 - i) {
                        if (koncnaPolja[igralec][i + kocka] == 0) {
                            koncnaPolja[igralec][i] = 0;
                            koncnaPolja[igralec][i + kocka] = 1;
                            // koncamo potezo
                            return;
                        }
                    }
                }
                
            }
        }
    }
    
    // funkcija za zbijanje nasprotnih figur
    private void zbij(int i) {
        int id = polje[i] - 1;
        if (id >= 0) {
            zacetnaPolja[id]++;
            polje[i] = 0;
        }
    }
    
    // funkcija za izpis
    public void izpis() {
        for (int i = 0; i < 4; i++) {
            String niz = "";
            // najprej izpisemo za vsakega igralca toliko crk,
            // kolikor figur mu je se ostalo na izhodiscnem polju
            String id;
            if (i == 0)
                id = "A";
            else if (i == 1)
                id = "B";
            else if (i == 2)
                id = "C";
            else
                id = "D";
            
            for (int j = 0; j < zacetnaPolja[i]; j++) {
                niz = niz + id + " ";
            }
            
            // nato izpisemo indekse polj, na katerih ima igralec figure
            for (int j = 0; j < max; j++) {
                if (polje[j] == i + 1) {
                    niz = niz + Integer.toString(j + 1) + " ";
                }
            }
            
            // ce so ta polja koncna polja izpisemo crko igralca in
            // index koncnega polja
            for (int j = 0; j < 4; j++) {
                if (koncnaPolja[i][j] != 0) {
                    niz = niz + id + Integer.toString(j + 1) + " ";
                }
            }
            System.out.println(niz.substring(0, niz.length()-1));
        }
    }
    
}
