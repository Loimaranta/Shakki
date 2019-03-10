package shakki;

import java.util.Scanner;

public class Testi {
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		Lauta lauta = new Lauta();

		lauta.uusiPeli();
		lauta.tulostaLauta();

		while (lauta.onkoKaynnissa()) {
			String syote = (String) scan.nextLine();
			try {
				lauta.siirraNappula(syote);
			} catch (LaitonSiirtoPoikkeus e) {
				e.tulostaVirhe();
			}
		}
		scan.close();
		System.out.println("Peli p‰‰ttyi");
	}

}