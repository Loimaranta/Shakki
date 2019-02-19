package shakki;

import java.util.Scanner;

//import javax.swing.JFrame;

//import shakki.grafiikka.Poyta;

public class Testi {

	public static void main(String[] args) {

		/*
		 * Poyta kentta = new Poyta();
		 * 
		 * kentta.setTitle("Shakki");
		 * 
		 * kentta.pack();
		 * 
		 * kentta.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 * 
		 * kentta.setVisible(true);
		 */
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

	}

}