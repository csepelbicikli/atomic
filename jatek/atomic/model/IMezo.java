/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jatek.atomic.model;

/**
 *
 * @author Kece
 */
public interface IMezo {
    public boolean telitett();
    public int getSzam();
    public void incSzam();
    public void nullazSzam();
    public Jatekos getJatekos();
    public void setJatekos(Jatekos jt);
    public void setLimit(int pLimit);
    public int getLimit();
}
