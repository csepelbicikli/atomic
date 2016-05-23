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
public interface ITabla<FieldType extends IMezo> {
    public int getX();
    public int getY();
    public FieldType getMezo(int x,int y)throws java.lang.IllegalArgumentException;
    public void setStateChangeCallBack(Runnable operation);
    public void setEndGameCallback(Runnable operation);
    public void rahelyez(int x, int y);
}
