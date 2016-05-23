/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jatek.atomic.view;

import jatek.atomic.model.IMezo;
import jatek.atomic.model.Jatekos;
import jatek.atomic.model.Mezo;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Shape;
import javax.swing.JComponent;

/**
 *
 * @author Kece
 */
public class JMezo extends JComponent implements IMezo {
    private final IMezo mezo;
    
    public JMezo(){
        this.mezo = new Mezo();
        this.setPreferredSize(new Dimension(40, 40));
    }
    
    @Override
    public boolean telitett() {
        return this.mezo.telitett();
    }

    @Override
    public int getSzam() {
        return this.mezo.getSzam();
    }

    @Override
    public void incSzam() {
        this.mezo.incSzam();
    }

    @Override
    public void nullazSzam() {
        this.mezo.nullazSzam();
    }

    @Override
    public Jatekos getJatekos() {
        return this.mezo.getJatekos();
    }

    @Override
    public void setJatekos(Jatekos jt) {
        this.mezo.setJatekos(jt);
    }

    @Override
    public void setLimit(int pLimit) {
        this.mezo.setLimit(pLimit);
    }

    @Override
    public int getLimit() {
        return this.mezo.getLimit();
    }
    
    @Override
    public void paint(Graphics g){
        this.drawFrame(g);
        this.drawTokens(g);
    }

    private void drawFrame(Graphics g) {
        g.drawLine(0, 0, 0, this.getPreferredSize().height);
        g.drawLine(0, this.getPreferredSize().height, this.getPreferredSize().width, this.getPreferredSize().height);
        g.drawLine(this.getPreferredSize().width, this.getPreferredSize().height, this.getPreferredSize().width, 0);
        g.drawLine(this.getPreferredSize().width, 0, 0, 0);
    }

    private void drawTokens(Graphics g) {
        switch (this.getSzam()){
            case 4:
                g.drawOval(
                        this.getPreferredSize().width / 3,
                        this.getPreferredSize().height / 3,
                        this.getPreferredSize().width / 4,
                        this.getPreferredSize().height / 4);
                g.drawOval(
                        2 * this.getPreferredSize().width / 3,
                        this.getPreferredSize().height / 3,
                        this.getPreferredSize().width / 4,
                        this.getPreferredSize().height / 4);
                g.drawOval(
                        2 * this.getPreferredSize().width / 3,
                        2 * this.getPreferredSize().height / 3,
                        this.getPreferredSize().width / 4,
                        this.getPreferredSize().height / 4);
                g.drawOval(
                        this.getPreferredSize().width / 3,
                        2 * this.getPreferredSize().height / 3,
                        this.getPreferredSize().width / 4,
                        this.getPreferredSize().height / 4);
                break;
            case 3:
                g.drawOval(
                        this.getPreferredSize().width / 3,
                        this.getPreferredSize().height / 2,
                        this.getPreferredSize().width / 4,
                        this.getPreferredSize().height / 4);
                g.drawOval(
                        2 * this.getPreferredSize().width / 3,
                        this.getPreferredSize().height / 3,
                        this.getPreferredSize().width / 4,
                        this.getPreferredSize().height / 4);
                g.drawOval(
                        2 * this.getPreferredSize().width / 3,
                        2 * this.getPreferredSize().height / 3,
                        this.getPreferredSize().width / 4,
                        this.getPreferredSize().height / 4);
                break;
            case 2:
                g.drawOval(
                        this.getPreferredSize().width / 3,
                        2 * this.getPreferredSize().height / 3,
                        this.getPreferredSize().width / 4,
                        this.getPreferredSize().height / 4);
                g.drawOval(
                        2 * this.getPreferredSize().width / 3,
                        this.getPreferredSize().height / 3,
                        this.getPreferredSize().width / 4,
                        this.getPreferredSize().height / 4);
                break;
            case 1:
                g.drawOval(
                        this.getPreferredSize().width / 2,
                        this.getPreferredSize().height / 2,
                        this.getPreferredSize().width / 4,
                        this.getPreferredSize().height / 4);
                break;
            default:
                g.fillOval(
                    this.getPreferredSize().width / 2,
                    this.getPreferredSize().height / 2,
                    this.getPreferredSize().width / 4,
                    this.getPreferredSize().height / 4);
        }
    }
}
