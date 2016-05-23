package jatek.atomic.model;

/**
 * Játékos reprezentációját megvalósító osztály
 * @author Mészáros Balázs
 * @version 1
 */
public class Jatekos{

    /**
     * Színek amelyeket a játékosokhoz rendelünk.
     * <p>
     * A GUI-ban (jatek.atomic.view.AtomicFrame) a getColor() függvény
     * fordítja át AWT-s színekre
     * @see jatek.atomic.view.AtomicFrame
     */
    public static enum Szin {
        KEK, PIROS, SARGA, ZOLD, FEHER
    }

    /**
     * Statikus számláló, új játékosok azonosítójának előállításához
     */
    private static int c=0;
    
    /**
     * Játékos azonosítója, egy futtatáson belül egyedi
     */
    private final int id;
    
    /**
     * Játékos neve
     */
    private final String nev;
    
    /**
     * Játékos színe
     */
    private final Szin szin;

    /**
     * A játékosnak van neve, színe és egy id-je,
     * amelyet a c nevű statikus egész növelése alapján kap.
     * 
     * @param nev Játékos neve
     * @param szin Játékos színe
     */
    public Jatekos(String nev,Szin szin){
            this.id=c++;
            this.nev=nev;
            this.szin=szin;
    }

    /**
     * Getter függvény, a játékos nevének lekérdezéséhez
     * @return Az objektum "nev" attribútuma
     */
    public String getNev() {return nev;}

    /**
     * Getter függvény, a játékos azonosítójának lekérdezéséhez
     * @return Az objektum "id" attribútuma
     */
    public int getID() {return id;}

    /**
     * Getter függvény, a játékos színének lekérdezésére
     * @return Az objektum "szin" azonosítója
     * @see jatek.atomic.model.Jatekos.Szin
     */
    public Szin getSzin() {return szin;}
	
    /**
     * Két játékos összevetésére equals,
     * a Jatekos osztály ˝c˝ nevű statikus tagja által
     * adott ID alapján hasonlít össze.
     * 
     * @param other Másik játékos
     * @return Igaz, ha a két játékos azonosítója megegyezik, egyébként hamis
     */
    public boolean equals(Jatekos other) {
			if(other==null){return false;}
            return (this.getID() == other.getID());
    }
}
