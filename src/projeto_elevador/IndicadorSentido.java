/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto_elevador;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

/**
 *
 * @author gabri
 */
public class IndicadorSentido {
    private int x;
    private int y;
    private int width;
    private int hegiht;
    
    private int sentido;
    private Polygon triang_baixo;
    private Polygon triang_cima;
    
    public IndicadorSentido(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.hegiht = height;
        
        int xpoints[] = {x + width/5, x+width/2, x + width*4/5};
        int ypoints[] = {(int)(y + height/2 + height*0.1), y + height*9/10, (int)(y + height/2 + height*0.1)};
        int npoints = 3;
        triang_baixo = new Polygon(xpoints, ypoints, npoints);
        
        int ypointsA[] = {(int)(y + height/2 - height*0.1), y + height/10,(int)(y + height/2 - height*0.1)};
        triang_cima = new Polygon(xpoints, ypointsA, npoints);
    }
    
    public void paint(Graphics2D g2){
        g2.setColor(Color.red);
        g2.drawRoundRect(x, y, width, hegiht, 20, 20);
        g2.draw(triang_cima);
        g2.draw(triang_baixo);
        
        if(this.sentido == 1){
            g2.fill(triang_cima);
        }else if(this.sentido == -1){
            g2.fill(triang_baixo);
        }
    }

    public int getSentido() {
        return sentido;
    }

    public void setSentido(int sentido) {
        this.sentido = sentido;
    }
    
    
}
