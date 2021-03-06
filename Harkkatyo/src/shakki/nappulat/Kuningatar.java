package shakki.nappulat;

import shakki.LaitonSiirtoPoikkeus;
import shakki.Pelinappula;

public class Kuningatar extends Pelinappula {

	public Kuningatar(char vari) {
		super(vari);
	}

	@Override
	public String toString() {
		if (getVari() == 'v') {
			return "[Gv]";
		} else {
			return "[Gm]";
		}
	}

	@Override
	public boolean voiSiirtaa(String alku, String loppu) {
		try {
			return (Math.abs(kaannaEnsimmainenKoordinaatti(alku) - kaannaEnsimmainenKoordinaatti(loppu)) == Math
					.abs(kaannaToinenKoordinaatti(alku) - kaannaToinenKoordinaatti(loppu))
					|| kaannaEnsimmainenKoordinaatti(loppu) == kaannaEnsimmainenKoordinaatti(alku)
					|| kaannaToinenKoordinaatti(alku) == kaannaToinenKoordinaatti(loppu));
		} catch (LaitonSiirtoPoikkeus l) {
			return false;
		}
	}

}
