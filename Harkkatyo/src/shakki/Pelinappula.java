package shakki;

public abstract class Pelinappula {

	private char vari;
	
	public Pelinappula(char vari) {
		this.vari = vari;
	}
	
	
	public abstract void siirra(String ruutu);


	public char getVari() {
		return vari;
	}
	
	public abstract String toString();

}
