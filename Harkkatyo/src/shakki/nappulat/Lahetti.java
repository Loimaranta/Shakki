package shakki.nappulat;

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
		// TODO Auto-generated method stub
		return true;
	}

}
