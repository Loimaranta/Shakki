package shakki.nappulat;

import shakki.Pelinappula;

public class Sotilas extends Pelinappula {

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
		return true;
	}

}
