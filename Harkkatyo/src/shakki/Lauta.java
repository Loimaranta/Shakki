package shakki;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import shakki.nappulat.*;

public class Lauta {

	private char vuoro;

	private boolean peliKaynnissa;

	private Pelinappula[][] lauta = new Pelinappula[8][8];

	/**
	 * Aloittaa uuden pelin oletusasetuksilla
	 */
	public void uusiPeli() {
		for (int i = 0; i <= 7; i++) {
			lauta[1][i] = new Sotilas('v');
		}
		for (int i = 0; i <= 7; i++) {
			lauta[6][i] = new Sotilas('m');
		}

		lauta[0][0] = new Torni('v');
		lauta[0][7] = new Torni('v');
		lauta[7][0] = new Torni('m');
		lauta[7][7] = new Torni('m');

		lauta[0][1] = new Ratsu('v');
		lauta[0][6] = new Ratsu('v');
		lauta[7][1] = new Ratsu('m');
		lauta[7][6] = new Ratsu('m');

		lauta[0][2] = new Lahetti('v');
		lauta[0][5] = new Lahetti('v');
		lauta[7][2] = new Lahetti('m');
		lauta[7][5] = new Lahetti('m');

		lauta[0][3] = new Kuningatar('v');
		lauta[7][3] = new Kuningatar('m');

		lauta[0][4] = new Kuningas('v');
		lauta[7][4] = new Kuningas('m');

		this.peliKaynnissa = true;
		this.vuoro = 'v';
	}

	/**
	 * Lataa pelin tekstitiedostosta
	 */
	public void lataaPeli() {
		File file = new File("shakkiTallennus.txt");

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			System.out.println("Tallennustiedostoa ei löytynyt, aloitetaan uusi peli");
			uusiPeli();
		}

		String st;
		try {
			if (br != null) {
				while ((st = br.readLine()) != null) {
					String[] latauslista = st.split(" ");
					if (latauslista[2] == null)
						continue;
					if (latauslista[2].equals("[Sv]"))
						lauta[Integer.parseInt(latauslista[0])][Integer.parseInt(latauslista[1])] = new Sotilas('v');
					else if (latauslista[2].equals("[Sm]"))
						lauta[Integer.parseInt(latauslista[0])][Integer.parseInt(latauslista[1])] = new Sotilas('m');
					else if (latauslista[2].equals("[Tv]"))
						lauta[Integer.parseInt(latauslista[0])][Integer.parseInt(latauslista[1])] = new Torni('v');
					else if (latauslista[2].equals("[Tm]"))
						lauta[Integer.parseInt(latauslista[0])][Integer.parseInt(latauslista[1])] = new Torni('m');
					else if (latauslista[2].equals("[Lv]"))
						lauta[Integer.parseInt(latauslista[0])][Integer.parseInt(latauslista[1])] = new Lahetti('v');
					else if (latauslista[2].equals("[Lm]"))
						lauta[Integer.parseInt(latauslista[0])][Integer.parseInt(latauslista[1])] = new Lahetti('m');
					else if (latauslista[2].equals("[Rv]"))
						lauta[Integer.parseInt(latauslista[0])][Integer.parseInt(latauslista[1])] = new Ratsu('v');
					else if (latauslista[2].equals("[Rm]"))
						lauta[Integer.parseInt(latauslista[0])][Integer.parseInt(latauslista[1])] = new Ratsu('m');
					else if (latauslista[2].equals("[Kv]"))
						lauta[Integer.parseInt(latauslista[0])][Integer.parseInt(latauslista[1])] = new Kuningas('v');
					else if (latauslista[2].equals("[Km]"))
						lauta[Integer.parseInt(latauslista[0])][Integer.parseInt(latauslista[1])] = new Kuningas('m');
					else if (latauslista[2].equals("[Gv]"))
						lauta[Integer.parseInt(latauslista[0])][Integer.parseInt(latauslista[1])] = new Kuningatar('v');
					else if (latauslista[2].equals("[Gm]"))
						lauta[Integer.parseInt(latauslista[0])][Integer.parseInt(latauslista[1])] = new Kuningatar('m');
					else if (latauslista[0].equals("vuoro"))
						vuoro = latauslista[1].charAt(0);
				}
			}
		} catch (IOException e) {
			System.out.println("IOException");
		}
		this.peliKaynnissa = true;
	}

	/**
	 * Tallentaa pelin tekstitiedostoon
	 */
	public void tallennaPeli() {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("shakkiTallennus.txt", "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		for (int i = 0; i <= 7; i++) {
			for (int j = 0; j <= 7; j++) {
				writer.println(i + " " + j + " " + lauta[i][j]);
			}
		}
		writer.println("vuoro " + vuoro + " vari");
		writer.close();
	}

	/**
	 * Käy läpi kaikki rajoitteet nappulan siirrosta. kääntää koordinaatit koodille
	 * luettavaan muotoon, tarkistaa aloituskoordinaatit, tarkistaa onko haluttu
	 * reitti sallittu kyseiselle nappulalle, ja siirtää nappulan mikäli ehdot
	 * totetutuvat
	 * 
	 * @param syote
	 * @throws LaitonSiirtoPoikkeus
	 */
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

		if (!(siirrettava instanceof Ratsu)) {
			if (alkujono == loppujono) {
				if (alkurivi > loppurivi) {
					for (int a = alkurivi; a < loppurivi; a--) {
						if (Math.abs(alkurivi - loppurivi) != 1) {
							if (lauta[a][alkujono] != null) {
								throw new LaitonSiirtoPoikkeus("Reitillä on toinen nappula (1)");
							}
						}
					}
				} else if (alkurivi < loppurivi) {
					for (int a = alkurivi; a > loppurivi; a++) {
						if (Math.abs(alkurivi - loppurivi) != 1) {
							if (lauta[a][alkujono] != null) {
								throw new LaitonSiirtoPoikkeus("Reitillä on toinen nappula (2) ((Alas))");
							}
						}
					}
				}
			} else if (alkurivi == loppurivi) {
				if (alkujono > loppujono) {
					for (int a = alkujono; a < loppujono; a--) {
						if (Math.abs(alkujono - loppujono) != 1) {
							if (lauta[alkurivi][a] != null) {
								throw new LaitonSiirtoPoikkeus("Reitillä on toinen nappula (3)");
							}
						}
					}
				} else if (alkujono < loppujono) {
					for (int a = alkujono; a > loppujono; a++) {
						if (Math.abs(alkujono - loppujono) != 1) {
							if (lauta[alkurivi][a] != null) {
								throw new LaitonSiirtoPoikkeus("Reitillä on toinen nappula (4)");
							}
						}
					}
				}
			} else if (Math.abs(alkurivi - loppurivi) == Math.abs(alkujono - loppujono)) {
				if (alkurivi > loppurivi && alkujono > loppujono) {
					for (int a = 0; a < loppujono; a++) {
						if (Math.abs(alkujono - loppujono) != 1)
							if (lauta[loppurivi - a][loppujono - a] != null) {
								throw new LaitonSiirtoPoikkeus("Reitillä on toinen nappula (5)");
							}
					}
				} else if (alkurivi > loppurivi && alkujono < loppujono) {
					for (int a = alkurivi; a > loppurivi; a--) {
						int b = a;
						if (Math.abs(alkujono - loppujono) != 1)
							if (lauta[a][b] != null) {
								throw new LaitonSiirtoPoikkeus("Reitillä on toinen nappula (6) ((Oikea-ylös))");
							}
					}
				} else if (alkurivi < loppurivi && alkujono < loppujono) {
					for (int a = 0; a > loppurivi; a--) {
						if (Math.abs(alkujono - loppujono) != 1)
							if (lauta[alkurivi + a][alkujono + a] != null) {
								throw new LaitonSiirtoPoikkeus("Reitillä on toinen nappula (7) ((Oikea-alas))");
							}

					}
				} else if (alkurivi < loppurivi && alkujono > loppujono) {
					for (int a = alkurivi + 1; a < loppurivi; a++) {
						int b = a;
						if (Math.abs(alkujono - loppujono) != 1)
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

	/**
	 * tulostaa pelilaudan suoritushetkisen tilanteen
	 */
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

	/**
	 * Kääntää annetun koordinaatin ensimmäisen osan ohjelman käytettävään muotoon
	 * @param koord
	 * @return
	 * @throws LaitonSiirtoPoikkeus
	 */
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

	/**
	 * Kääntää annetun koordinaatin toisen osan ohjelman käytettävään muotoon
	 * @param koord
	 * @return
	 * @throws LaitonSiirtoPoikkeus
	 */
	public int kaannaToinenKoordinaatti(String koord) throws LaitonSiirtoPoikkeus {
		int jono = (Integer.parseInt(koord.substring(1))) - 1;
		if (jono < 0 || jono > 7) {
			throw new LaitonSiirtoPoikkeus("Huono syote");
		} else {
			return jono;
		}
	}

	/**
	 * tarkistaa onko peli yhä käynnissä. Käyetään pelin päällä pitämiseen kunnes kuningas on syöty
	 * @return
	 */
	public boolean onkoKaynnissa() {
		return peliKaynnissa;
	}

	/**
	 * Tarkisaa onko kysytty nappula kysytyn värinen
	 * @param v
	 * @param nappula
	 * @return
	 * @throws LaitonSiirtoPoikkeus
	 */
	public boolean tarkistaVari(char v, Pelinappula nappula) throws LaitonSiirtoPoikkeus {
		if (nappula.getVari() == v) {
			return true;
		} else {
			throw new LaitonSiirtoPoikkeus("Vaara vari");
		}
	}

	/**
	 * tarkistaa onko syöte sallitun muotoinen, käytetään syötettä kysyessä
	 * @param syote
	 * @return
	 */
	public boolean tarkistaSyote(String syote) {
		return (syote.length() != 5 || syote.isEmpty() || syote.charAt(2) != ' ');
	}

	/**
	 * Palauttaa tällä hetkellä vuorossa olevan pelaajan värin
	 * @return
	 */
	public char getVuoro() {
		return vuoro;
	}

}
