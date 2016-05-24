package jatek.atomic.model;
/**
 * A játéktér egy mezőjét reprezentáló osztály
 * @author Mészáros Balázs
 * @author Németh Péter
 * @version 1
 */
public class Mezo implements IMezo{
	/**
         * A mezőt telítő kövek száma
         */
	protected int limit;
        
        /**
         * A mezőn lévő kövek száma
         */
	protected int szam;
        
        /**
         * A mezőt éppen birtokló játékos
         */
	protected Jatekos jt;
	
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
	 * Ha telített egy mező akkor nullázza a vezérlés és szétosztja 
	 * a köveket a szomszédok között
	 * @return Igaz ha a mező jelenleg telített, egyébként hamis
	 */
        @Override
	public boolean telitett(){
		return(szam==limit);
	}
	
        /**
         * Getter függvény, a mezőn lévő kövek számát adja vissza
         * @return Az objektum "szam" attribútuma
         */
        @Override
	public int getSzam(){
		return szam;
	}
	
        /**
         * A mezőn lévő kövek számát inkrementálja lépéskor
         */
        @Override
	public void incSzam(){
		this.szam++;
	}
	
        /**
         * Telítődéskor a mezőn lévő kövek számát alaphelyzetbe állítja
         */
        @Override
	public void nullazSzam(){
		this.szam=0;
	}
	
        /**
         * Getter függvény a mezőt birtokló játékos lekérdezéséhez
         * @return Az objektum "jt" attribútuma
         */
        @Override
	public Jatekos getJatekos(){
		return jt;
	}
	
        /**
         * A Setter függvény, a mezőt birtokló játékos megadáa a mező elfoglalásakor
         * @param jt Új játékos
         */
        @Override
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
        @Override
        public void setLimit(int pLimit) throws java.lang.IllegalArgumentException{
            if (pLimit <= 0)
                throw new java.lang.IllegalArgumentException("A mező limitje csak pozitív egész lehet");
            if (this.szam <= pLimit){
                this.limit = pLimit;
            }
        }

    @Override
    public int getLimit() {
        return this.limit;
    }
}
