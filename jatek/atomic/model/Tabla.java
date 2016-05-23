package jatek.atomic.model;

/**
 * Játéktábla modellje, mely lehet 
 * <ul>
 * <li>- téglalap alakú (2 paraméter),</li>
 * <li>- vagy négyzetes(1 paraméter).</li>
 * </ul>
 * A tábla elemei <b>Mezo</b> objektumok.
 * @author Mészáros Balázs
 * @author Németh Péter
 * @version 1
 */
public class Tabla<FieldType extends IMezo> implements ITabla<FieldType> {
    private int stepCounter = 0;
    private boolean gameOver = false;
    
    /**
     * Mezőket tartalmazó 2 dimenziós konténer
     */
    private FieldType[][] mezok;

    /**
     * Runnbale objektum, a run() eljárásának meg kell hívódnia, ha a tábla állapota megváltozik
     */
    private Runnable onStateChange = null;

    /**
     * Runnable objektum, a run() eljárásának meg kell hívódnia, ha valamelyik játékos nyer
     */
    private Runnable onEndGame = null;
    
    private Jatekos[] jatekosok;
    private int currJatekos = 0;

    /**
     * Téglalap alakú tábla konstruálása
     * <p>
     * A konstruktor a mezőket tartalmazó tömb elemein végigiterál, és minden elemére
     * beállítja a megfelelő limitet
     * <p>
     * Ha a tömb valamely dimenziója túl kicsi, vagy bármely eleme null, kivételt dob
     * @param fieldSet Kétdimenziós, legalább 2x2 méretű tömb, ami a mezőket tartalmazza
     * @exception java.lang.IllegalArgumentException
     */
    public Tabla(FieldType[][] fieldSet, Jatekos[] jatekosok)throws java.lang.IllegalArgumentException {
        if (fieldSet.length > 1){
            if (fieldSet[0].length < 2)
                throw new java.lang.IllegalArgumentException("A táblának túl kevés oszlopa van (minimum 2)");
        } else throw new java.lang.IllegalArgumentException("A táblának túl kevés sora van (minimum 2)");

        this.jatekosok = jatekosok;
        if (jatekosok.length < 2)
            throw new java.lang.IllegalArgumentException("Legalább két játékos kell");
        else
            for (Jatekos jatekos : this.jatekosok) {
            if (jatekos == null) {
                throw new java.lang.NullPointerException("Null érték a játékosok között");
            }
        }
        
        this.mezok = fieldSet;
        int i = 0, j = 0;
        try{
            mezok[0][0].setLimit(2);
            mezok[0][fieldSet[0].length - 1].setLimit(2);
            mezok[fieldSet.length - 1][0].setLimit(2);
            mezok[fieldSet.length - 1][fieldSet[0].length - 1].setLimit(2);
            for(i=1;i<fieldSet.length - 1;i++){
                    mezok[i][0].setLimit(3);
                    mezok[i][fieldSet[0].length - 1].setLimit(3);
            }
            for(j=1;j<fieldSet[0].length - 1;j++){
                    mezok[0][j].setLimit(3);
                    mezok[fieldSet.length - 1][j].setLimit(3);
            }
            for(i=1;i<fieldSet.length - 1;i++){
                    for(j=1;j<fieldSet[0].length - 1;j++){
                            mezok[i][j].setLimit(4);
                    }
            }
        } catch (java.lang.NullPointerException e){
            throw new java.lang.IllegalArgumentException("Null érték a(z) (" + i + "; " + j + ") helyen");
        }
    }

    /**
     * Getter függvény a tábla magasságának lekérdezéséhez
     * @return Tábla sorainak száma
     */
    @Override
    public int getX(){
        return mezok.length;
    }

    /**
     * Getter függvény a tábla szélességének lekérdezéséhez
     * @return Tábla oszlopainak száma
     */
    @Override
    public int getY(){
        return mezok[0].length;
    }

    /**
     * Getter függvény, a tábla mezőinek eléréséhez
     * <p>
     * A sorok és oszlopok indexelése is 0-tól kezdődik, érvénytelen index esetén kivételt dob
     * @param x Sor index
     * @param y Oszlop index
     * @return Az x. sor y. oszlopán elhelyezkedő Mezo objektum
     * @exception java.lang.IllegalArgumentException
     */ 
    @Override
    public FieldType getMezo(int x,int y) throws java.lang.IllegalArgumentException{
            if (x < 0 || x > mezok.length)
                throw new java.lang.IllegalArgumentException("Hibás sorindex");
            else if (y < 0 || y > mezok[0].length)
                throw new java.lang.IllegalArgumentException("Hibás oszlopindex");
            return mezok[x][y];
	}

    @Override
    public void setStateChangeCallBack(Runnable operation) {
        this.onStateChange = operation;
    }

    @Override
    public void setEndGameCallback(Runnable operation) {
        this.onEndGame = operation;
    }

    @Override
    public void rahelyez(int x, int y) {
        if (this.gameOver) return;
        
        IMezo mezo = this.getMezo(x, y);
        Jatekos j = this.getCurrentPlayer();
        if (j == null || mezo.getJatekos() == j){
            mezo.incSzam();
            mezo.setJatekos(j);
            if (this.onStateChange != null)
                this.onStateChange.run();
            int szam = mezo.getSzam();
            if (szam == mezo.getLimit()){
                int[][] szomszedMezok = this.getAdjacentFields(x, y);
                for (int[] szomszed : szomszedMezok){
                    this.rahelyez(szomszed[0], szomszed[1]);
                }
            }
            this.checkEndgame();
            if (!this.gameOver)
                this.currJatekos = (this.currJatekos + 1) % this.jatekosok.length;
            this.stepCounter++;
        }
    }
    
    public Jatekos getCurrentPlayer(){
        return this.jatekosok[this.currJatekos];
    }

    private int[][] getAdjacentFields(int x, int y) {
        int retSize = x > 0 || x < this.getX() - 1 ? 2 : 1;
        retSize += y > 0 || y < this.getY() - 1 ? 2 : 1;
        int[][] ret = new int[retSize][2];
        int i = 0;
        if (x > 0){
            ret[i][0] = x - 1;
            ret[i][1] = y;
            i++;
        }
        
        if (x < this.getX() - 1){
            ret[i][0] = x + 1;
            ret[i][1] = y;
            i++;
        }
        
        if (y > 0){
            ret[i][0] = x;
            ret[i][1] = y - 1;
            i++;
        }
        
        if (x < this.getX() - 1){
            ret[i][0] = x;
            ret[i][1] = y + 1;
            i++;
        }
        return ret;
    }

    private void checkEndgame() {
        Jatekos jatekos = null;
        for (int i = 0; i < this.getX(); i++)
            for (int j = 0; j < this.getY(); j++){
                if (jatekos != null && this.getMezo(i, j).getJatekos() != jatekos)
                    return;
                else if (jatekos == null)
                    jatekos = this.getMezo(i, j).getJatekos();
            }
        if (jatekos != null){
            this.gameOver = true;
            if (this.onEndGame != null)
                this.onEndGame.run();
        }
    }
}
