package shakki.nappulat;

import shakki.Pelinappula;

public class Sotilas extends Pelinappula {

	private boolean liikutettu = false;

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
		if (!(liikutettu) && Math.abs(kaannaToinenKoordinaatti(alku) - kaannaToinenKoordinaatti(loppu)) > 2) {
			return false;
		} else if (Math.abs(kaannaToinenKoordinaatti(alku) - kaannaToinenKoordinaatti(loppu)) > 1) {
			return false;
		}
		//TODO lisää vielä syömistä koskeva diagonaalinen liike

		return true;
	}

}
