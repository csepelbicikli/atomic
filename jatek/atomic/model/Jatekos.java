package jatek.atomic.model;
public class Jatekos{

    /**
     * Színek amelyeket a játékosokhoz rendelünk.
     * <p>
     * A GUI-ban (jatek.atomic.view.AtomicFrame) a getColor() függvény
     * fordítja át AWT-s színekre
     */
    public static enum Szin {
        KEK, PIROS, SARGA, ZOLD, FEHER
    }

    public static int c=0;
    private int id;
    private String nev;
    private Szin szin;

    /**
     * A játékosnak van neve, színe és egy id-je,
     * melyet a c nevű statikus egész növelése alapján kap.
     *  
     */
    public Jatekos(String nev,Szin szin){
            this.id=c++;
            this.nev=nev;
            this.szin=szin;
    }

    public String getNev() {return nev;}

    public int getID() {return id;}

    public Szin getSzin() {return szin;}
	
     /**
     * Két játékos összevetésére equals,
     * a Jatekos osztály ˝c˝ nevű statikus tagja által
     * adott ID alapján hasonlít össze.
     */
    @Override
    public boolean equals(Object o) {
            boolean result = false;
            if (o instanceof Jatekos) {
                    Jatekos that = (Jatekos) o;
                    result = (this.getID() == that.getID());
            }
            return result;
    }
}
