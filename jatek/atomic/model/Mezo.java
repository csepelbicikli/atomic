package jatek.atomic.model;
/**
 * A játéktér egy mezőjét reprezentáló osztály
 * @author Mészáros Balázs
 * @version 1
 */
public class Mezo{
	/**
         * A mezőt telítő kövek száma
         */
	private final int limit;
        
        /**
         * A mezőn lévő kövek száma
         */
	private int szam;
        
        /**
         * A mezőt éppen birtokló játékos
         */
	private Jatekos jt;
	
        /**
         * A mezőt inicializáló konstruktor
         * @param limit A mezőt telítő kövek száma, egyenlő a vízszintesen, illetve függőlegesen szomszédos mezők számával
         * @param j A mezőt birtokló játékos
         */
	public Mezo(int limit, Jatekos j){
		this.limit = limit;
		this.jt  = jt ;
		this.szam = 0;
	}
	
	/**
	 * Ha telített egy mező akkor nullázza a vezérlés ész szétosztja 
	 * a köveket a szomszédok között
	 * @return Igaz ha a mező jelenleg telített, egyébként hamis
	 */
	public boolean telitett(){
		return(szam==limit);
	}
	
        /**
         * Getter függvény, a mezőn lévő kövek számát adja vissza
         * @return Az objektum "szam" attribútuma
         */
	public int getSzam(){
		return szam;
	}
	
        /**
         * A mezőn lévő kövek számát inkrementálja lépéskor
         */
	public void incSzam(){
		this.szam++;
	}
	
        /**
         * Telítődéskor a mezőn lévő kövek számát alaphelyzetbe állítja
         */
	public void nullazSzam(){
		this.szam=0;
	}
	
        /**
         * Getter függvény a mezőt birtokló játékos lekérdezéséhez
         * @return Az objektum "jt" attribútuma
         */
	public Jatekos getJatekos(){
		return jt;
	}
	
        /**
         * A Setter függvény, a mezőt birtokló játékos megadáa a mező elfoglalásakor
         * @param jt Új játékos
         */
	public void setJatekos(Jatekos jt){
		this.jt=jt;
	}
}
