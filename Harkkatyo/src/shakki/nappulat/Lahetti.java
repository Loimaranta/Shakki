package shakki.nappulat;

import shakki.LaitonSiirtoPoikkeus;
import shakki.Pelinappula;

public class Lahetti extends Pelinappula {

	public Lahetti(char vari) {
		super(vari);
	}

	@Override
	public String toString() {
		if (getVari() == 'v') {
			return "[Lv]";
		} else {
			return "[Lm]";
		}

	}

	@Override
	public boolean voiSiirtaa(String alku, String loppu) {
		try {
			return (Math.abs(kaannaEnsimmainenKoordinaatti(alku) - kaannaEnsimmainenKoordinaatti(loppu)) == Math
					.abs(kaannaToinenKoordinaatti(alku) - kaannaToinenKoordinaatti(loppu)));
		} catch (LaitonSiirtoPoikkeus l) {
			return false;
		}
	}

}
