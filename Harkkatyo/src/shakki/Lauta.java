package shakki;

import java.util.ArrayList;
import java.util.HashMap;

public class Lauta {
	private HashMap<Pelinappula, String> nappulat = new HashMap<Pelinappula, String>();
	
	public void uusiPeli() {
		
	}
	
	public void lataaPeli() {
		
	}
	
	public void tulostaLauta() {
		ArrayList<ArrayList<Integer>> ruudukko = new ArrayList<ArrayList<Integer>>();
		for (ArrayList<Integer> rivi : ruudukko) {
			for(int ruutu : rivi) {
				System.out.println();
			}
		}
	}
	
}
