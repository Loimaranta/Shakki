package shakki.nappulat;

import shakki.LaitonSiirtoPoikkeus;
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
		try {
			return (Math.abs(kaannaEnsimmainenKoordinaatti(alku) - kaannaEnsimmainenKoordinaatti(loppu)) == Math
					.abs(kaannaToinenKoordinaatti(alku) - kaannaToinenKoordinaatti(loppu))
					|| kaannaEnsimmainenKoordinaatti(loppu) == kaannaEnsimmainenKoordinaatti(alku)
					|| kaannaToinenKoordinaatti(alku) == kaannaToinenKoordinaatti(loppu)) // Rajoittaa liikesuunnat
					&& Math.abs(kaannaEnsimmainenKoordinaatti(alku) - kaannaEnsimmainenKoordinaatti(loppu)) == 1
					|| Math.abs(kaannaToinenKoordinaatti(alku) - kaannaToinenKoordinaatti(loppu)) == 1; // Rajoittaa
																										// liikkeen
																										// yhteen
																										// ruutuun
		} catch (LaitonSiirtoPoikkeus l) {
			return false;
		}
	}
}
