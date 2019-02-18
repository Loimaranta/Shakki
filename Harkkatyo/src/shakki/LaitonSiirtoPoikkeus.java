package shakki;

public class LaitonSiirtoPoikkeus extends Exception {
	
	private String virhe;
	
	public LaitonSiirtoPoikkeus(String virhe) {
		this.virhe = virhe;
	}
	
	public void tulostaVirhe() {
		System.out.println(virhe);
	}

}
