/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto_elevador;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

/**
 *
 * @author Franciele Kuwahara, Gabriel Bressanini
 */
public class IndicadorAndarAtual {
    private int x;
    private int y;
    private int widht;
    private int height;
    
    private int andar_atual;
    
    public IndicadorAndarAtual(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.widht = width;
        this.height = height;
        
        this.andar_atual = 0;
    }

    public void paint(Graphics2D g2){
        g2.setColor(Color.gray);
        g2.fillRect(x, y, widht, height);        
        g2.setFont(new Font("Arial", Font.BOLD, 100));
        g2.setColor(Color.GREEN);
        if(this.andar_atual < 10){
            g2.drawString(""+this.andar_atual, (int) (x + this.widht/3.8), y + this.height*4/5);
        }else{
            g2.drawString(""+this.andar_atual, (int) (x + this.widht/20), y + this.height*4/5);
        }
    }
    
    public int getAndar_atual() {
        return andar_atual;
    }

    public void setAndar_atual(int andar_atual) {
        this.andar_atual = andar_atual;
    }
}
