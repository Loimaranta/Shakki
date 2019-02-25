package shakki.nappulat;

import shakki.Pelinappula;

public class Sotilas extends Pelinappula {

	private boolean siirtojaljella = true;

	public Sotilas(char vari) {
		super(vari);
	}

	@Override
	public String toString() {
		if (getVari() == 'v') {
			return "[Sv]";
		} else {
			return "[Sm]";
		}

	}

	@Override
	public boolean voiSiirtaa(String alku, String loppu) {
		if (kaannaEnsimmainenKoordinaatti(alku) != kaannaEnsimmainenKoordinaatti(loppu)) {
			return false;
		}
		if (kaannaToinenKoordinaatti(alku) > kaannaToinenKoordinaatti(loppu) && getVari() == 'v') {
			return false;
		} else if (kaannaToinenKoordinaatti(alku) < kaannaToinenKoordinaatti(loppu) && getVari() == 'm') {
			return false;
		}
		if (Math.abs(kaannaToinenKoordinaatti(alku) - kaannaToinenKoordinaatti(loppu)) > 2) {
			return false;
		} 
		if (!(siirtojaljella) && Math.abs(kaannaToinenKoordinaatti(alku) - kaannaToinenKoordinaatti(loppu)) > 1) {
			return false;
		}
		//TODO lisää vielä syömistä koskeva diagonaalinen liike
		siirtojaljella = false;
		return true;
	}

}
