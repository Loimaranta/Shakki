package shakki;

import javax.swing.JFrame;

import shakki.grafiikka.Poyta;


public class Testi {

	public static void main(String[] args) {

		/*
		Poyta kentta = new Poyta();

		kentta.setTitle("Shakki");

		kentta.pack();

		kentta.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		kentta.setVisible(true);
		*/
		
		Lauta lauta = new Lauta();
		
		lauta.uusiPeli();
		
		lauta.tulostaLauta();
		
		lauta.siirraNappula("A1", "C3");
		
		lauta.tulostaLauta();
		
	}
	
	

}