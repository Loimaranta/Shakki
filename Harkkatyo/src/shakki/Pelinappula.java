package shakki;

public abstract class Pelinappula {

	private char vari;

	public Pelinappula(char vari) {
		this.vari = vari;
	}

	/**
	 * Tarkistaa, voiko nappulatyyppia siirtaa kutsutulla tavalla. Laudan siirra()
	 * metodi kutsuu siirrettaessa.
	 * 
	 * @param alku
	 * @param loppu
	 * @return
	 */
	public abstract boolean voiSiirtaa(String alku, String loppu);

	public char getVari() {
		return vari;
	}

	/**
	 * pakottaa nappulat kirjoittamaan oman toString() metodin
	 */
	public abstract String toString();

	public int kaannaEnsimmainenKoordinaatti(String koord) throws IllegalArgumentException {
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

	public int kaannaToinenKoordinaatti(String koord) throws IllegalArgumentException {
		int jono = (Integer.parseInt(koord.substring(1))) - 1;
		if (jono < 0 || jono > 7) {
			throw new IllegalArgumentException("Huono syote");
		} else {
			return jono;
		}
	}
}
