package shakki.nappulat;

import shakki.Pelinappula;

public class Kuningas extends Pelinappula {

	public Kuningas(char vari) {
		super(vari);
	}

	@Override
	public String toString() {
		if (getVari() == 'v') {
			return "[Kv]";
		} else {
			return "[Km]";
		}

	}

	@Override
	public boolean voiSiirtaa(String alku, String loppu) {
		return (Math.abs(kaannaEnsimmainenKoordinaatti(alku) - kaannaEnsimmainenKoordinaatti(loppu)) == Math
				.abs(kaannaToinenKoordinaatti(alku) - kaannaToinenKoordinaatti(loppu))
				|| kaannaEnsimmainenKoordinaatti(loppu) == kaannaEnsimmainenKoordinaatti(alku)
				|| kaannaToinenKoordinaatti(alku) == kaannaToinenKoordinaatti(loppu));
		//TODO rajoita liike yhteen ruutuun
	}
}
