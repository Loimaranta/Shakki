package shakki;

import java.util.HashMap;

import shakki.nappulat.*;

public class Lauta {
	// private HashMap<Pelinappula, String> nappulat = new
	// HashMap<Pelinappula,String>();

	private Pelinappula[][] lauta = new Pelinappula[8][8];

	private Sotilas s1 = new Sotilas('v');
	private Sotilas s2 = new Sotilas('v');
	private Sotilas s3 = new Sotilas('v');
	private Sotilas s4 = new Sotilas('v');
	private Sotilas s5 = new Sotilas('v');
	private Sotilas s6 = new Sotilas('v');
	private Sotilas s7 = new Sotilas('v');
	private Sotilas s8 = new Sotilas('v');
	private Sotilas s9 = new Sotilas('m');
	private Sotilas s10 = new Sotilas('m');
	private Sotilas s11 = new Sotilas('m');
	private Sotilas s12 = new Sotilas('m');
	private Sotilas s13 = new Sotilas('m');
	private Sotilas s14 = new Sotilas('m');
	private Sotilas s15 = new Sotilas('m');
	private Sotilas s16 = new Sotilas('m');

	private Torni t1 = new Torni('v');
	private Torni t2 = new Torni('v');
	private Torni t3 = new Torni('m');
	private Torni t4 = new Torni('m');

	private Ratsu r1 = new Ratsu('v');
	private Ratsu r2 = new Ratsu('v');
	private Ratsu r3 = new Ratsu('m');
	private Ratsu r4 = new Ratsu('m');

	private Lahetti l1 = new Lahetti('v');
	private Lahetti l2 = new Lahetti('v');
	private Lahetti l3 = new Lahetti('m');
	private Lahetti l4 = new Lahetti('m');

	private Kuningatar g1 = new Kuningatar('v');
	private Kuningatar g2 = new Kuningatar('m');

	private Kuningas k1 = new Kuningas('v');
	private Kuningas k2 = new Kuningas('m');

	public void uusiPeli() {
		// Lisataan nappulat taulukkoon alkuperaisien jarjestyksen mukaan
		lauta[1][0] = s1;
		lauta[1][1] = s2;
		lauta[1][2] = s3;
		lauta[1][3] = s4;
		lauta[1][4] = s5;
		lauta[1][5] = s6;
		lauta[1][6] = s7;
		lauta[1][7] = s8;
		lauta[6][0] = s9;
		lauta[6][1] = s10;
		lauta[6][2] = s11;
		lauta[6][3] = s12;
		lauta[6][4] = s13;
		lauta[6][5] = s14;
		lauta[6][6] = s15;
		lauta[6][7] = s16;

		lauta[0][0] = t1;
		lauta[0][7] = t2;
		lauta[7][0] = t3;
		lauta[7][7] = t4;

		lauta[0][1] = r1;
		lauta[0][6] = r2;
		lauta[7][1] = r3;
		lauta[7][6] = r4;

		lauta[0][2] = l1;
		lauta[0][5] = l2;
		lauta[7][2] = l3;
		lauta[7][5] = l4;

		lauta[0][3] = g1;
		lauta[7][3] = g2;

		lauta[0][4] = k1;
		lauta[7][4] = k2;
	}

	public void lataaPeli() {

	}

	public void siirraNappula(String alku, String loppu) {
		int alkujono = kaannaKoordinaatti(alku);
		int loppujono = kaannaKoordinaatti(loppu);
		int alkurivi = (Integer.parseInt(alku.substring(1))) - 1;
		int loppurivi = (Integer.parseInt(loppu.substring(1))) - 1;
		
		Pelinappula siirrettava = lauta[alkujono][alkurivi];
		lauta[alkujono][alkurivi] = null;
		lauta[loppujono][loppurivi] = siirrettava;
	}

	public void tulostaLauta() {
		for (Pelinappula[] rivi : lauta) {
			for (Pelinappula nappula : rivi) {
				if (nappula == null) {
					System.out.print("[ ]");
				} else {
					System.out.print(nappula);
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	public int kaannaKoordinaatti(String koord) throws IllegalArgumentException {
		char eka = koord.charAt(0);
		int rivi;

		switch (eka) {
		case 'A':
			rivi = 0;
			break;
		case 'B':
			rivi = 1;
			break;
		case 'C':
			rivi = 2;
			break;
		case 'D':
			rivi = 3;
			break;
		case 'E':
			rivi = 4;
			break;
		case 'F':
			rivi = 5;
			break;
		case 'G':
			rivi = 6;
			break;
		case 'H':
			rivi = 7;
			break;
		default:
			throw new IllegalArgumentException("Huono syote");
		}

		return rivi;
	}

}
