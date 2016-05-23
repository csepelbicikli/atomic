/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jatek.atomic.view;

import jatek.atomic.model.IMezo;
import jatek.atomic.model.ITabla;
import jatek.atomic.model.Jatekos;
import jatek.atomic.model.Tabla;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author Kece
 */
public class JTabla extends JPanel implements ITabla<JMezo> {
    class MezoAction implements MouseListener{
        private final int rowIndex;
        private final int columnIndex;
        private final JComponent owner;
        
        public MezoAction(int x, int y, JComponent owner){
            this.rowIndex = x;
            this.columnIndex = y;
            this.owner = owner;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            JTabla.this.rahelyez(this.rowIndex, this.columnIndex);
            this.owner.repaint();
            System.out.println(this.rowIndex + " - " + this.columnIndex);
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
    }
    
    private ITabla<JMezo> tablaCore = null;
    private final MainWindow.SessionConfig config;
    
    public JTabla(MainWindow.SessionConfig config){
        this.config = config;
        this.buildJTabla(this.config.ROWS, this.config.COLUMNS, this.config.PLAYER_COUNT);
    }
    
    @Override
    public JMezo getMezo(int x, int y) throws IllegalArgumentException {
        return tablaCore.getMezo(x, y);
    }

    @Override
    public void setStateChangeCallBack(Runnable operation) {
        this.tablaCore.setStateChangeCallBack(operation);
    }

    @Override
    public void setEndGameCallback(Runnable operation) {
        this.tablaCore.setEndGameCallback(operation);
    }

    private void buildJTabla(int x, int y, int playerCount) {
        JMezo[][] arr = new JMezo[x][y];
        this.setLayout(new GridLayout(x, y));
        for (int i = 0; i < x; i++)
            for (int j = 0; j < y; j++){
                arr[i][j] = new JMezo();
                arr[i][j].addMouseListener(new JTabla.MezoAction(i, j, arr[i][j]));
                this.add(arr[i][j]);
            }
        Jatekos[] jatekosok = new Jatekos[playerCount];
        for (int i = 0; i < jatekosok.length; i++)
            jatekosok[i] = new Jatekos(Integer.toString(i + 1) + ". játékos", Jatekos.Szin.values()[i]);
        this.tablaCore = new Tabla<>(arr, jatekosok);
    }
    
    @Override
    public void rahelyez(int x, int y){
        this.tablaCore.rahelyez(x, y);
    }
}
