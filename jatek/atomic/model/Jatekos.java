package jatek.atomic.model;
public class Jatekos{
	public static int c=0;
	private int id;
	private String nev;
	private int mezok;
	private Szin szin;
	
	public Jatekos(String nev,Szin szin){
		this.id=c++;
		this.nev=nev;
		mezok= -1;
		this.szin=szin;
	}
	
	public void incMezok(){
		if (mezok==-1){
			mezok=1;
		} else {
			mezok++;
		}
	}
	
	public void decMezok() {mezok--;}
	
	public boolean vesztett() {return mezok == 0;}
	
	public String getNev() {return nev;}
	
	public int getID() {return id;}
	
	public Szin getSzin() {return szin;}
	
	@Override public boolean equals(Object o) {
		boolean result = false;
		if (o instanceof Jatekos) {
			Jatekos that = (Jatekos) o;
			result = (this.getID() == that.getID());
		}
		return result;
	}
}
