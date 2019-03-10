package shakki;

import shakki.nappulat.*;

public class Lauta {

	private char vuoro;

	private boolean peliKaynnissa;

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

		this.peliKaynnissa = true;
		this.vuoro = 'v';
	}

	public void lataaPeli() {
		// TODO keksi miten peli ladataan SQLite tietokannasta
		this.peliKaynnissa = true;
	}

	public void tallennaPeli() {
		// TODO keksi miten peli tallennetaan SQLite tietokantaan
	}

	public void siirraNappula(String syote) throws LaitonSiirtoPoikkeus {
		String[] syotetaulu = syote.split(" ");
		String alku = syotetaulu[0];
		String loppu = syotetaulu[1];

		int alkujono = kaannaEnsimmainenKoordinaatti(alku);
		int loppujono = kaannaEnsimmainenKoordinaatti(loppu);
		int alkurivi = kaannaToinenKoordinaatti(alku);
		int loppurivi = kaannaToinenKoordinaatti(loppu);

		if (lauta[alkurivi][alkujono] == null)
			throw new LaitonSiirtoPoikkeus("Valitsit tyhjän ruudun!");

		Pelinappula siirrettava = lauta[alkurivi][alkujono];

		if (siirrettava.getVari() != vuoro)
			throw new LaitonSiirtoPoikkeus("Väärä nappula, nyt on " + vuoro + ":n vuoro");

		if (lauta[loppurivi][loppujono] != null) {
			if ((lauta[loppurivi][loppujono]).getVari() == vuoro) {
				throw new LaitonSiirtoPoikkeus("Et voi syödä omaa nappulaa!");
			}
		}

		if (!(siirrettava instanceof Ratsu)) { // TODO korjaa yhden ruudun siirto tarkistuksille 1 - 4
			if (alkujono == loppujono) {
				if (alkurivi > loppurivi) {
					for (int a = loppurivi; a < alkurivi; a++) {
						if (Math.abs(alkurivi - loppurivi) != 1) {
							if (lauta[a][alkujono] != null) {
								throw new LaitonSiirtoPoikkeus("Reitillä on toinen nappula (1)");
							}
						}
					}
				} else if (alkurivi < loppurivi) { 
					for (int a = loppurivi; a > alkurivi; a--) {
						if (Math.abs(alkurivi - loppurivi) != 1) {
							if (lauta[a][alkujono] != null) {
								throw new LaitonSiirtoPoikkeus("Reitillä on toinen nappula (2) ((Alas))");
							}
						}
					}
				}
			} else if (alkurivi == loppurivi) {
				if (alkujono > loppujono) {
					for (int a = loppujono; a < alkujono; a++) {
						if (Math.abs(alkujono - loppujono) != 1) {
							if (lauta[alkurivi][a] != null) {
								throw new LaitonSiirtoPoikkeus("Reitillä on toinen nappula (3)");
							}
						}
					}
				} else if (alkujono < loppujono) {
					for (int a = loppujono; a > alkujono; a--) {
						if (Math.abs(alkujono - loppujono) != 1) {
							if (lauta[alkurivi][a] != null) {
								throw new LaitonSiirtoPoikkeus("Reitillä on toinen nappula (4)");
							}
						}
					}
				}
			} else if (Math.abs(alkurivi - loppurivi) == Math.abs(alkujono - loppujono)) {
				if (alkurivi > loppurivi && alkujono > loppujono) {
					for (int a = 0; a < loppurivi; a++) {
						if (lauta[loppurivi - a][loppujono - a] != null) { // TODO korjaa ArrayIndexOutOfBound, antaa -1 kun siirrytään A sarakkeelle
							throw new LaitonSiirtoPoikkeus("Reitillä on toinen nappula (5)");
						}
					}
				} else if (alkurivi > loppurivi && alkujono < loppujono) {
					for (int a = alkurivi; a > loppurivi; a--) {
						int b = a;
						if (lauta[a][b] != null) {
							throw new LaitonSiirtoPoikkeus("Reitillä on toinen nappula (6) ((Oikea-ylös))");
						}
					}
				} else if (alkurivi < loppurivi && alkujono < loppujono) {
					for (int a = 0; a < loppurivi; a++) {
						if (lauta[alkurivi + a][alkujono + a] != null) {
							throw new LaitonSiirtoPoikkeus("Reitillä on toinen nappula (7) ((Oikea-alas))");
						}

					}
				} else if (alkurivi < loppurivi && alkujono > loppujono) {
					for (int a = alkurivi + 1; a < loppurivi; a++) {
						int b = a;
						if (lauta[a][b] != null) {
							throw new LaitonSiirtoPoikkeus("Reitillä on toinen nappula (8) ((Vasen-alas))");
						}
					}
				}

			}
		}

		// Tarkistukset loppuvat, siirtää nappulan

		if (siirrettava.voiSiirtaa(alku, loppu)) {
			if (lauta[loppurivi][loppujono] instanceof Kuningas) {
				peliKaynnissa = false;
			}
			lauta[alkurivi][alkujono] = null;
			lauta[loppurivi][loppujono] = siirrettava;
		} else {
			throw new LaitonSiirtoPoikkeus("Nappulaa ei voi siirtaa tähän ruutuun");
		}

		tulostaLauta();

		if (vuoro == 'v') {
			vuoro = 'm';
		} else if (vuoro == 'm') {
			vuoro = 'v';
		}
	}

	public void tulostaLauta() {
		System.out.println("   A   B   C   D   E   F   G   H");
		int i = 1;
		for (Pelinappula[] rivi : lauta) {
			System.out.print(i + " ");
			for (Pelinappula nappula : rivi) {
				if (nappula == null) {
					System.out.print("[  ]");
				} else {
					System.out.print(nappula);
				}
			}
			// System.out.print(" " + i);
			i++;
			System.out.println();
		}
		// System.out.print(" A B C D E F G H");
		System.out.println();
	}

	public int kaannaEnsimmainenKoordinaatti(String koord) throws LaitonSiirtoPoikkeus {
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
			throw new LaitonSiirtoPoikkeus("Huono syote");
		}

		return rivi;
	}

	public int kaannaToinenKoordinaatti(String koord) throws LaitonSiirtoPoikkeus {
		int jono = (Integer.parseInt(koord.substring(1))) - 1;
		if (jono < 0 || jono > 7) {
			throw new LaitonSiirtoPoikkeus("Huono syote");
		} else {
			return jono;
		}
	}

	public boolean onkoKaynnissa() {
		return peliKaynnissa;
	}

	public boolean tarkistaVari(char v, Pelinappula nappula) throws LaitonSiirtoPoikkeus {
		if (nappula.getVari() == v) {
			return true;
		} else {
			throw new LaitonSiirtoPoikkeus("Vaara vari");
		}
	}

	public void tarkistaSyote(String syote) throws LaitonSiirtoPoikkeus {
		if (syote.length() != 5 || syote.isEmpty() || syote.charAt(2) != ' ') {
			throw new LaitonSiirtoPoikkeus("Huono syote, anna muotoa Koordinaatti, väli, Koordinaatti");
		}
	}

}
