package projeto_elevador;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Franciele Kuwahara, Gabriel Bresssanini
 */
public class Botoeira{
    private String identificador;
    private boolean state;
    
    private int x;
    private int y;
    private int width;
    private int height;
    private int andar;
    
    public Botoeira(String identificador, int andar){
        this.state = false;
        this.identificador = identificador;
        this.andar = andar;
    }
    
    public boolean getState(){
        return this.state;
    }
    
    public void setState(boolean state){
        this.state = state;
    }
    
    public String getIdentificador(){
        return this.identificador;
    }
    
    public void setIdentificador(String identificador){
        this.identificador = identificador;
    }
    
    public void paint(Graphics2D g2){
        if(this.state){
            g2.setColor(Color.green);
        }else{
            g2.setColor(Color.white);
        }
        g2.fillOval(this.x, this.y, this.width, this.height);
        
        g2.setColor(Color.gray);
        g2.fillOval((int) (this.x + this.width*0.1),(int) (this.y + this.height*0.1),
                (int)(width*0.8),
                (int)(height*0.8));
        
        g2.setFont(new Font("Arial", Font.BOLD, 40));
        g2.setColor(Color.yellow);
        if(this.getIdentificador().length()>1){
            g2.drawString("" + this.identificador, x + this.width*1/12, (int) (y + this.height*7.5/10));
        }else{
            g2.drawString("" + this.identificador, x + this.width*3/10, (int) (y + this.height*7.5/10));
        }
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    public boolean intersects(int x, int y){
        if(x > this.x && x < (this.x + this.width) 
                && y > this.y && y < (this.y + this.height)){
            return true;
        }else{
            return false;
        }
        
    }
    
    public int getAndar(){
        return this.andar;
    }
}
