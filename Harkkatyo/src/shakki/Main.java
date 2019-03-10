package shakki;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		Lauta lauta = new Lauta();

		System.out
				.println("Valitse uusi peli kirjoittamalla \"uusi\" tai lataa viimeisin peli kirjoittamalla \"lataa\"");
		String avaus = (String) scan.nextLine();

		while (!lauta.onkoKaynnissa()) {
			if (avaus.equals("uusi")) {
				lauta.uusiPeli();
				lauta.tulostaLauta();
			} else if (avaus.equals("lataa")) {
				lauta.lataaPeli();
				lauta.tulostaLauta();
			} else {
				System.out.println(
						"Kirjoita \"uusi\" jos haluat aloittaa uuden pelin, \"lataa\" jos haluat jatkaa viimeksi tallennettua pelia");

			}
		}

		while (lauta.onkoKaynnissa()) {
			String syote = (String) scan.nextLine();
			if (!lauta.tarkistaSyote(syote)) {
				try {
					lauta.siirraNappula(syote);
				} catch (LaitonSiirtoPoikkeus e) {
					e.tulostaVirhe();
				}
			} else if (syote.equals("tallenna")) {
				lauta.tallennaPeli();
				System.out.println("Peli tallennettu");
				break;
			} else if (syote.equals("lopeta")) {
				break;
			} else {
				System.out.println(
						"Syote ei kelpaa, syota siirtosi muodossa Alkukoordinaatti *v‰li* Loppukoordinaatti, esim A2 C4");
			}
		}
		scan.close();
		System.out.println("Peli p‰‰ttyi");
	}

}