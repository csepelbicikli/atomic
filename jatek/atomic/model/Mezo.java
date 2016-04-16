package jatek.atomic.model;
public class Mezo{
	
	private final int limit;
	private int szam;
	private Jatekos jt;
	
	public Mezo(int limit, Jatekos j){
		this.limit = limit;
		this.jt  = jt ;
		this.szam = 0;
	}
	
	public boolean telitett(){
		return(szam==limit);
	}
	
	public int getSzam(){
		return szam;
	}
	
	public void incSzam(){
		this.szam++;
	}
	
	public void nullazSzam(){
		this.szam=0;
	}
	
	public Jatekos getJatekos(){
		return jt;
	}
	
	public void setJatekos(Jatekos jt){
		if (this.jt!=null) jt.decMezok();
		if (jt     !=null) jt.incMezok();
		this.jt=jt;
	}
}
