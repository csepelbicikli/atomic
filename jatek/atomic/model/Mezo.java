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
	private int limit;
        
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
         */
	public Mezo(int limit){
		this.limit = limit;
		this.jt  = null;
		this.szam = 0;
	}
        
        /**
         * Alapértelmezett konstruktor
         */
        public Mezo(){
           this.limit = 1;
           this.jt = null;
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
        
        /**
         * Setter függvény a mezőre helyezhető kövek maximális számának megadásához
         * <p>
         * A mezőre helyezhető kövek limitjét a paranméterként megadott értékre állítja,
         * ha az nagyobb vagy egyenlő mint a mezőn lévő kövek száma, egyébként nem csinál semmit
         * <p>
         * Ha az új érték 0 vagy negatív, az eljárás java.lang.IllegalArgumentException kivételt dob
         * @param pLimit új limit, csak pozitív egész szám lehet
         * @exception java.lang.IllegalArgumentException
         */
        public void setLimit(int pLimit) throws java.lang.IllegalArgumentException{
            if (pLimit <= 0)
                throw new java.lang.IllegalArgumentException("A mező limitje csak pozitív egész lehet");
            if (this.szam <= pLimit){
                this.limit = pLimit;
            }
        }
}
