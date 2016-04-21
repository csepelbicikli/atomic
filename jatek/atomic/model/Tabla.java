package jatek.atomic.model;

/**
 * Játéktábla modellje, mely lehet 
 * <ul>
 * <li>- téglalap alakú (2 paraméter),</li>
 * <li>- vagy négyzetes(1 paraméter).</li>
 * </ul>
 * A tábla elemei <b>Mezo</b> objektumok.
 * @author Mészáros Balázs
 * @version 1
 */
public class Tabla<FieldType extends Mezo> {
	/**
         * Mezőket tartalmazó 2 dimenziós konténer
         */
	private FieldType[][] mezok;
	
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
	public Tabla(FieldType[][] fieldSet)throws java.lang.IllegalArgumentException {
            if (fieldSet.length > 1){
                if (fieldSet[0].length < 2)
                    throw new java.lang.IllegalArgumentException("A táblának túl kevés oszlopa van (minimum 2)");
            } else throw new java.lang.IllegalArgumentException("A táblának túl kevés sora van (minimum 2)");
            
            mezok = fieldSet;
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
	public int getX(){
            return mezok.length;
	}
	
	/**
	 * Getter függvény a tábla szélességének lekérdezéséhez
         * @return Tábla oszlopainak száma
	 */
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
	public FieldType getMezo(int x,int y) throws java.lang.IllegalArgumentException{
            if (x < 0 || x > mezok.length)
                throw new java.lang.IllegalArgumentException("Hibás sorindex");
            else if (y < 0 || y > mezok[0].length)
                throw new java.lang.IllegalArgumentException("Hibás oszlopindex");
            return mezok[x][y];
	}
}
