package jatek.atomic.model;

/**
 * Játéktábla modellje, mely lehet 
 * <p>
 * -téglalap alakú (2 paraméter), 
 * <p>
 * -vagy négyzetes(1 paraméter).
 * <p>
 * A tábla elemei <b>Mezo</b> objektumok.
 * 
 */
public class Tabla {
	
	private Mezo[][] mezok;
	
	/**
	 * Négyzetes tábla konstruálása
	 */
	public Tabla(int x){
		this(x,x);
		
	}
	
	/**
	 * Téglalap alakú tábla konstruálása
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
	 * Tábla oszlopainak száma
	 */
	public int getX(){
		return mezok.length;
	}
	
	/**
	 * Tábla sorainak száma 
	 */
	public int getY(){
		return mezok[0].length;
	}
	
	/**
	 * Tábla mezőjének lekérdezése
	 */ 
	public Mezo getMezo(int x,int y){
		return mezok[x][y];
	}
	
	
}
