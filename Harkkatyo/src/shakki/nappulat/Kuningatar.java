package shakki.nappulat;

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
		// TODO Auto-generated method stub
		return true;
	}

}
