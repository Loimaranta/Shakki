package shakki.nappulat;

import shakki.Pelinappula;

public class Ratsu extends Pelinappula {

	public Ratsu(char vari) {
		super(vari);
	}

	@Override
	public String toString() {
		if (getVari() == 'v') {
			return "[Rv]";
		} else {
			return "[Rm]";
		}

	}

	@Override
	public boolean voiSiirtaa(String alku, String loppu) {
		return Math.abs(kaannaEnsimmainenKoordinaatti(alku) - kaannaEnsimmainenKoordinaatti(loppu)) == 2
				&& Math.abs(kaannaToinenKoordinaatti(alku) - kaannaToinenKoordinaatti(loppu)) == 1
				|| Math.abs(kaannaEnsimmainenKoordinaatti(alku) - kaannaEnsimmainenKoordinaatti(loppu)) == 1
						&& Math.abs(kaannaToinenKoordinaatti(alku) - kaannaToinenKoordinaatti(loppu)) == 2;
	}

}
