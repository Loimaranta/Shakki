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
		// TODO Auto-generated method stub
		return true;
	}

}
