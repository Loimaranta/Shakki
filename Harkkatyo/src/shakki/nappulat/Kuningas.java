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
		// TODO Auto-generated method stub
		return true;
	}

}
