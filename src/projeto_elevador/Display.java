/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto_elevador;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author gabri
 */
public class Display {
    private ArrayList<Botoeira> botoes;
    private IndicadorSentido indicador_sentido;
    
    private int x;
    private int y;
    private int width;
    private int height;
    
    public Display(int x, int y, int width, int height, ArrayList<Botoeira> botoes){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        
        this.botoes = botoes;
        
        this.indicador_sentido = new IndicadorSentido((int)(this.x + this.width/10),
                (int)(this.y + this.height/10),
                (int)(this.y + this.height*8/10),
                (int)(this.y + this.height*4/10));
    }
    
    public Display(int x, int y, int width, int height, ArrayList<Botoeira> botoes, IndicadorSentido indicador_sentido){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;        
        this.botoes = botoes;
        this.indicador_sentido = indicador_sentido;
    }
    
    public void paint(Graphics2D g2){
        g2.setColor(Color.gray);
        g2.drawRoundRect(x, y, width, height, width/10, width/10);
        this.indicador_sentido.paint(g2);
        for(int i = 0; i < this.botoes.size(); i++){
            this.botoes.get(i).paint(g2);
        }
    }
}
