package jatek.atomic.view;
import javax.swing.*;
import jatek.atomic.model.*;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;

public class AtomicFrame extends JFrame{
	
	private Tabla tabla;
	private Jatekos aktual, jatekos1, jatekos2;
	private JButton[][] gomb;
	private JPanel panel;
	private JLabel cimke;
	
	/**
	 * Négyzetes táblájú játék konstruktorja
	 */
	public AtomicFrame(int x){
		this (x,x);
	}
	
	/**
	 * Téglalap táblájú játék konstruktorja
	 * <p> 
	 * A fejlesztés jelenlegi fázisában 2 előre definiált (kék-piros) játékosunk van.
	 * A gomobok megfelelően méretezett GridLayout-ban helyezkednek el,
	 * alatta állapotsor található.
	 * A gombokra kattintáskor aktiválódik azoknak ActionListener-je,
	 * amely megkísérli elvégezni a kő elhelyezését. 
	 * A művelet sikere esetén a tábla újrarajzolódik és játékosváltás történik.
	 * Nyerés esetén üzenetablak tájékoztatja a nyertest majd a program leáll.
	 * 
	 */
	public AtomicFrame(int x, int y){
		tabla=new Tabla(x,y);
		jatekos1=new Jatekos("1. jatekos",Szin.PIROS);
		jatekos2=new Jatekos("2. jatekos",Szin.KEK);
		aktual = jatekos1;
		
		this.setVisible(true);
		this.setSize(600,600);
		this.setTitle("Atomic játék");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		gomb=new JButton[x][y];
		panel = new JPanel();
		cimke = new JLabel();
		this.add(panel,BorderLayout.CENTER);
		this.add(cimke,BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(x,y));
		
		for(int i=0;i<x;i++){
			for(int j=0;j<y;j++){
				gomb[i][j]=new JButton("");
				final int i_tmp = i, j_tmp = j;//belső class csak final lokális változókat fogad el
				gomb[i][j].addActionListener(ae -> {
					if (rahelyez(i_tmp,j_tmp,aktual)){ //ha sikerült rárakni
						frissit();
						valt();
						if (nyert()!=null){
							JOptionPane.showMessageDialog(this,nyert().getNev()+" nyert.");
							this.dispose();
							return;
						}
					}
				});
				gomb[i][j].setOpaque(true);//egyéni háttérszin swing gomboknak
				panel.add(gomb[i][j]);
			}
		}
		frissit();
	}
	
	/**
	 * A Szin enum színeit fordítja át AWT-s színekre
	 */	
	public static Color getColor(Szin sz){
		if(sz==Szin.KEK)return Color.BLUE;
		if(sz==Szin.SARGA)return Color.YELLOW;
		if(sz==Szin.ZOLD)return Color.GREEN;
		if(sz==Szin.PIROS)return Color.RED;
		if(sz==Szin.FEHER)return Color.WHITE;
		return Color.BLACK;//ha bovited a szineket ird bele
							//kulonben ezt kapod
	}
	
	/**
	 * Olyasmi mint az AWT-ben a repaint().
	 * <p>
	 * Újraszínezi a mezőket a tulajdonos szerint,
	 * illetve felírva a rajtuk levő kövek számát.
	 */
	public void frissit(){
		for(int i=0;i<tabla.getX();i++){
			for(int j=0;j<tabla.getY();j++){
				gomb[i][j].setText(tabla.getMezo(i,j).getSzam()+"");
				Jatekos jt = tabla.getMezo(i,j).getJatekos();
				if(jt!=null){
					gomb[i][j].setBackground(getColor(jt.getSzin()));
				} else {
					gomb[i][j].setBackground(Color.WHITE);
				}
			}
		}
	}
	
	/**
	 * Lebonyolítja a váltást a játékosok között.
	 * <p>
	 * Megj.: Egyelőre két előre definiált játékosra van beállítva.
	 */
	public void valt(){
		if (aktual.equals(jatekos1)){ 
			aktual=jatekos2;
			cimke.setText("2. játékos jön.");
			return;
		}
		if (aktual.equals(jatekos2)){
			aktual=jatekos1;
			cimke.setText("1. játékos jön.");
			return;
		}
	}
	/**
	 * Elvégzi a kő ráhelyezését a mezőre, amennyiben a játékos 
	 * ezt megteheti (semleges v. saját mező)
	 * <p>
	 * Amennyiben a mező telítetté válik, áthelyezi a kavicsokat
	 * a szomszédos mezőre, elfoglalván azokat.
	 * <p>
	 * Az alapelv teljesülése biztosított:
	 * sarok esetén 2
	 * <br>
	 * nem-sarok táblaszél esetén 3
	 * <br>
	 * más helyeken 4
	 * <br>
	 * kavics szóródik szét, 
	 * mivel az algoritmus a szomszédos mezők meglétét megfigyelve,
	 * továbbá rekurzívan rak le. 
	 */
	public boolean rahelyez(int x, int y, Jatekos j){
		if (nyert()!=null){return true;}
		Mezo mezo = tabla.getMezo(x,y);
		Jatekos mj = mezo.getJatekos();
		if (mj==null){
			mezo.setJatekos(j);
			mezo.incSzam();
			return true;
		}
		if(j.equals(mj)){
			mezo.incSzam();
			if (mezo.telitett()){
				mezo.nullazSzam();
				if(x!=0){				//NYUGAT
					tabla.getMezo(x-1,y).setJatekos(j);
					rahelyez(x-1,y,j);	
				}
				if(y!=0){				//ÉSZAK
					tabla.getMezo(x,y-1).setJatekos(j);
					rahelyez(x,y-1,j);
				}
				if(x!=tabla.getX()-1){	//KELET
					tabla.getMezo(x+1,y).setJatekos(j);
					rahelyez(x+1,y,j);
				}	
				if(y!=tabla.getY()-1){	//DÉL
					tabla.getMezo(x,y+1).setJatekos(j);
					rahelyez(x,y+1,j);
				}
			}
			return true;
		}
		return false;
	}
		
	/**
	 * E metódus 2 játékos esetén mutatja meg, van-e nyertes
	 * <p>
	 * Akkor van nyertes, ha a másiknak nem marad mezője 
	 * (tehát csak semleges mezők és a nyertes mezői léteznek a táblán)
	*/
	public Jatekos nyert(){
		boolean nemElsoLepes=false;
		Jatekos jt=null;
		for (int i=0;i<tabla.getX();i++){
			for (int j=0;j<tabla.getY();j++){
				Jatekos jt_akt=tabla.getMezo(i,j).getJatekos();
				if(jt!=null&&jt_akt!=null&&(jt_akt.equals(jt))) 
					{nemElsoLepes=true;}
				if(jt==null&&jt_akt!=null) 
					{jt=jt_akt;}
				if(jt!=null&&jt_akt!=null&&(!jt_akt.equals(jt))) 
					{return null;}		
			}
		}
		if(jt==null) {return null;} 
			//játék kezdete: nincs egy játékos se a pályán
		else{
			if(nemElsoLepes){
				return jt;
			}
		} 
			//csak egy játékost leltünk és nem első lépés
		return null;
	}
	
	/**
	 * Tesztelési célokra készített ideiglenes main függvény.
	 * <p>
	 * A Swing JFrame-t külön szálon futtatja, ennek az EventListener-ek
	 * miatt van jelentőssége.
	 * <p>
	 *  @param args Parancssori paraméterek
	 * 
	 * 
	 */	
	public static void main(String[] args){
		SwingUtilities.invokeLater(() -> {
			new AtomicFrame(4);
		});
	}
}

