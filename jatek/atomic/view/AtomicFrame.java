package jatek.atomic.view;
import javax.swing.*;
import jatek.atomic.model.*;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;

public class AtomicFrame extends JFrame{
	
    private Tabla tabla;
    private Jatekos aktual, jatekos1, jatekos2;
    private JButton[][] gomb;
    private JPanel panel, felsoPanel;
    private JLabel cimke;
	private JButton resetGomb, mentesGomb, betoltesGomb;

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
     * <p>
     * A játék újrakezdhető a tábla felett elhelyezett 'Játék újrakezdése' gombra való kattintással.
     */
    public AtomicFrame(int x, int y){
		jatekos1=new Jatekos("1. jatekos",Jatekos.Szin.PIROS);
        jatekos2=new Jatekos("2. jatekos",Jatekos.Szin.KEK);
		this.setVisible(true);
        this.setSize(600,600);
        this.setTitle("Atomic játék");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
                       
		panel = new JPanel();
		panel.setLayout(new GridLayout(x,y));
		cimke = new JLabel();
		felsoPanel = new JPanel();
		felsoPanel.setLayout(new BoxLayout(felsoPanel,BoxLayout.X_AXIS));
		betoltesGomb = new JButton("Betöltés");
		betoltesGomb.addActionListener( ae -> {betolt();});
		felsoPanel.add(betoltesGomb);
		mentesGomb = new JButton("Mentés");
		felsoPanel.add(mentesGomb);
		resetGomb = new JButton("Újrakezdés");
		resetGomb.addActionListener( ae -> {reset();});
		felsoPanel.add(resetGomb);
		
		this.add(felsoPanel, BorderLayout.NORTH);            
		this.add(panel,BorderLayout.CENTER);
		this.add(cimke,BorderLayout.SOUTH);
            
		start(ujTabla(x, y),1);
	}
	
	public void start(Tabla tabla, int kezdo){
            //tabla = this.ujTabla(x, y);
            this.tabla = tabla;
			if(kezdo==1){
				aktual = jatekos1;
				cimke.setText("1. játékos jön.");
			} else {
				aktual = jatekos2;
				cimke.setText("2. játékos jön.");
			}
			gomb=new JButton[tabla.getX()][tabla.getY()];
			for(int i=0;i<tabla.getX();i++){
				for(int j=0;j<tabla.getY();j++){
					gomb[i][j]=new JButton("");
				}
			}  
            for(int i=0;i<tabla.getX();i++){
                    for(int j=0;j<tabla.getY();j++){
                            final int i_tmp = i, j_tmp = j;//belső class csak final lokális változókat fogad el
                            gomb[i][j].addActionListener(ae -> {
                                    if (rahelyez(i_tmp,j_tmp,aktual)){ //ha sikerült rárakni
                                            frissit();
                                            valt();
                                            if (nyert()!=null){
													cimke.setText(nyert().getNev()+" nyert.");
													for( ActionListener al : mentesGomb.getActionListeners() ) {
														mentesGomb.removeActionListener( al );
													}
                                                    //JOptionPane.showMessageDialog(this,nyert().getNev()+" nyert.");
                                                    //this.dispose();
                                                    //return;
                                            }
                                    }
                            });
                            gomb[i][j].setOpaque(true);//egyéni háttérszin swing gomboknak
                            panel.add(gomb[i][j]);
                    }
            }
            mentesGomb.addActionListener(ae -> {ment(); });
            frissit();
    }
    
	public void stop(){
		for(int i=0;i<tabla.getX();i++){
			for(int j=0;j<tabla.getY();j++){
				for( ActionListener al : gomb[i][j].getActionListeners() ) {
					gomb[i][j].removeActionListener( al );
				}
				panel.remove(gomb[i][j]);
			}
		}
		for( ActionListener al : mentesGomb.getActionListeners() ) {
			mentesGomb.removeActionListener( al );
		}
	}
	
	public void reset(){
		stop();
		int x = tabla.getX();
		int y = tabla.getY();
		start(ujTabla(x, y),1);
	}
	
	public void betolt(){
		Tabla t=null;
		List<String> sorok=null;
		try{	
			Path dataPath = Paths.get("data.txt");
			sorok = Files.readAllLines(dataPath);
			int x=Integer.parseInt(sorok.get(1));
			int y=Integer.parseInt(sorok.get(2));
			t = this.ujTabla(x, y);
			for(int i=0;i<x;++i){
				String s = sorok.get(i+3);
				for(int j=0;j<x;++j){
					System.out.println(i+","+j);
					int szam = Integer.parseInt(s.substring(j*2,j*2+1));
					char betu = s.charAt(j*2+1);
					if(betu=='a'){
						t.getMezo(i,j).setJatekos(jatekos1);
					} else if(betu=='b'){
						t.getMezo(i,j).setJatekos(jatekos2);
					}else{
						t.getMezo(i,j).setJatekos(null);
					}
					for(int c=0;c<szam;++c){
						t.getMezo(i,j).incSzam();
					}
				}
			}
			
		}catch(IOException ex){
			ex.printStackTrace();
			cimke.setText("Hiba a data.txt betoltesekor.");
		}
		System.out.println("hopp");
		stop();
		if(sorok.get(0).equals("a")) {
			start(t,1);
		} else {
			start(t,2);
		}
	}
	
	public void ment(){
		/*if(!jatekos2.equals(aktual) && !jatekos2.equals(aktual)){
			System.err.println("Hiba: aktualis jatekos nem valid");
			return;
		}*/
		PrintWriter pw;
		try{
			File f = new File ("data.txt");
			pw = new PrintWriter (f);
			if(jatekos1.equals(aktual)){
				pw.println("a");
			}else if(jatekos2.equals(aktual)){
				pw.println("b");
			}
			pw.println(tabla.getX()+"");
			pw.println(tabla.getY()+"");
			for(int i=0;i<tabla.getX();i++){
				for(int j=0;j<tabla.getY();j++){
					pw.print(tabla.getMezo(i,j).getSzam()+"");
					Jatekos jt = tabla.getMezo(i,j).getJatekos();
					if(jatekos1.equals(jt)){
						pw.print("a");
					}else if(jatekos2.equals(jt)){
						pw.print("b");
					} else{
						pw.print("z");
					}
				}
				pw.println();
            }
			pw.close ();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}    
	}

    /**
     * A Szin enum színeit fordítja át AWT-s színekre
     */	
    public static Color getColor(Jatekos.Szin sz){
            if(sz==Jatekos.Szin.KEK)return Color.BLUE;
            if(sz==Jatekos.Szin.SARGA)return Color.YELLOW;
            if(sz==Jatekos.Szin.ZOLD)return Color.GREEN;
            if(sz==Jatekos.Szin.PIROS)return Color.RED;
            if(sz==Jatekos.Szin.FEHER)return Color.WHITE;
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
                            mezo.setJatekos(null); // A mező felszabadul
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
    
    /**
     * Segédfüggvény új tábla létrehozásához
     * @param x Sorok száma
     * @param y Oszlopok száma
     * @return Tabla objektum
     */
    private Tabla ujTabla(int x, int y) {
        Mezo[][] mezok = new Mezo[x][y];
        for (int i = 0; i < x; i++)
            for (int j = 0; j < y; j++)
                mezok[i][j] = new Mezo();
        return new Tabla<>(mezok);
    }
}

