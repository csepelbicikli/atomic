/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jatek.atomic.view;

import jatek.atomic.model.Jatekos;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

/**
 *
 * @author Kece
 */
public class MainWindow extends JFrame{
    public static class SessionConfig {
        public static final SessionConfig DEFAULT = new SessionConfig(6, 6, 2);
        
        public final int ROWS;
        public final int COLUMNS;
        public final int PLAYER_COUNT;
        
        public SessionConfig(int rows, int columns, int playerCount){
            this.ROWS = rows;
            this.COLUMNS = columns;
            this.PLAYER_COUNT = playerCount;
        }
    }
    
    private JTabla tabla = null;
    private JToolBar toolBar = null;
    private JSplitPane contentPane = null;
    private JButton btnReset = null;
    private SessionConfig config = SessionConfig.DEFAULT;
    
    protected MainWindow(){
        this.buildMainWindow();
    }
    
    protected MainWindow(SessionConfig config){
        this.config = config;
        this.buildMainWindow();
    }
    
    private void buildMainWindow(){
        this.setTitle("Atomic játék");
        this.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        this.buildContentPane();
        this.setContentPane(this.contentPane);
        this.setResizable(false);
        this.pack();
    }
    
    private void buildResetButton(){
        this.btnReset = new JButton();
        this.btnReset.setAction(new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindow.this.dispose();
                MainWindow.openMainWindow();
            }
        });
        this.btnReset.setText("Új játék");
    }
    
    private void buildToolbar(){
        this.toolBar = new JToolBar();
        this.buildResetButton();
        this.toolBar.setFloatable(false);
        this.toolBar.add(this.btnReset);
    }
    
    private void buildTabla(){
        this.tabla = new JTabla(this.config);
    }
    
    private void buildContentPane(){
        this.buildToolbar();
        this.buildTabla();
        this.contentPane = new JSplitPane();
        this.contentPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        this.contentPane.setDividerSize(0);
        this.contentPane.setTopComponent(this.toolBar);
        this.contentPane.setBottomComponent(this.tabla);
    }
    
    public static void openMainWindow(){
        MainWindow window = new MainWindow();
        window.setVisible(true);
    }
    
    public static void main(String[] args){
        MainWindow.openMainWindow();
    }
}
