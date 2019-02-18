package shakki;

import javax.swing.JFrame;

import shakki.grafiikka.Poyta;


public class Testi {

	public static void main(String[] args) {

		Poyta kentta = new Poyta();

		kentta.setTitle("Shakki");

		kentta.pack();

		kentta.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		kentta.setVisible(true);
		
	}
	
	

}