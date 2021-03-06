package shakki.nappulat;

import shakki.LaitonSiirtoPoikkeus;
import shakki.Pelinappula;

public class Torni extends Pelinappula {

	public Torni(char vari) {
		super(vari);
	}

	@Override
	public String toString() {
		if (getVari() == 'v') {
			return "[Tv]";
		} else {
			return "[Tm]";
		}

	}

	@Override
	public boolean voiSiirtaa(String alku, String loppu) {
		try {
		return (kaannaEnsimmainenKoordinaatti(loppu) == kaannaEnsimmainenKoordinaatti(alku)
				|| kaannaToinenKoordinaatti(alku) == kaannaToinenKoordinaatti(loppu));
		} catch (LaitonSiirtoPoikkeus l) {
			return false;
		}
	}

}
