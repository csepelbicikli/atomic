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
public class Tabla {
	/**
         * Mezőket tartalmazó 2 dimenziós konténer
         */
	private Mezo[][] mezok;
	
	/**
	 * Négyzetes tábla konstruálása
         * @param x A tábla sorainak és oszlopainak száma
	 */
	public Tabla(int x){
		this(x,x);
	}
	
	/**
	 * Téglalap alakú tábla konstruálása
         * <p>
         * A konstruktor a következő műveleteket végzi el:
         * <ul>
         * <li>Létrehozza a mezőket tartalmazó konténert</li>
         * <li>Létrehozza a mező objektumokat, a megfelelő telítettségi limitekkel (2 a sarkokon, 3 a széleken, 4 a többi mezőn) és feltölti velük a konténert</li>
         * </ul>
         * @param x Tábla sorainak száma
         * @param y Tábla oszlopainak száma
	 */
	public Tabla(int x, int y){
		mezok = new Mezo[x][y];
		mezok[0][0]=new Mezo(2,null);  		//É.Ny.
		mezok[0][y-1]=new Mezo(2,null); 	//D.Ny
		mezok[x-1][0]=new Mezo(2,null); 	//É.K.
		mezok[x-1][y-1]=new Mezo(2,null);	//D.K.
		for(int i=1;i<x-1;i++){
			mezok[i][0]  =new Mezo(3,null);	//Észak
			mezok[i][y-1]=new Mezo(3,null); //Dél
		}
		for(int j=1;j<y-1;j++){
			mezok[0][j]  =new Mezo(3,null);	//Nyugat
			mezok[x-1][j]=new Mezo(3,null); //Kelet
		}
		for(int i=1;i<x-1;i++){
			for(int j=1;j<y-1;j++){
				mezok[i][j]=new Mezo(4,null);//Többi
			}
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
         * A sorok és oszlopok indexelése is 0-tól kezdődik
         * @param x Sor index
         * @param y Oszlop index
         * @return Az x. sor y. oszlopán elhelyezkedő Mezo objektum
	 */ 
	public Mezo getMezo(int x,int y){
		return mezok[x][y];
	}
}
