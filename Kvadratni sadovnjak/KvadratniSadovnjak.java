import java.util.Scanner;

/*
 * KVADRATNI SADOVNJAK
 * Iscemo tako naravno stevilo n, da bo za dani k element
 * naravnih stevil veljalo n * n = l * k za nek l element
 * naravnih stevil.
 * izpisati moramo l
 */

public class KvadratniSadovnjak {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // preberemo, za koliko k-jev moramo to narediti
        int n = sc.nextInt();
        
        for (int i = 0; i < n; i++) {
            // preberemo k (long ker je k < 2^32)
            long k = sc.nextInt();
            // sprobavamo vse kvadrate do k^2
            long j = 1;
            // ponavljamo iteracijo dokler ni pogoj izpolnjen
            boolean pogoj = true;
            while(pogoj) {
                long kvadrat = j * j;
                if (kvadrat % k == 0) {
                    // izpisemo stevilo in gremo iz iteracije
                    System.out.println(kvadrat / k);
                    pogoj = false;
                }
                // za vsako poovitev iteracije povecamo j
                j++;
            }
        }
    }
}
