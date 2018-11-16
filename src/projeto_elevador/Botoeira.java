package projeto_elevador;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Franciele Kuwahara, Gabriel Bresssanini
 */
public class Botoeira {
    private String identificador;
    private boolean state;
    
    private int x;
    private int y;
    private int width;
    private int height;
    
    public Botoeira(String identificador){
        this.state = false;
        this.identificador = identificador;
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
            g2.setColor(Color.pink);
        }else{
            g2.setColor(Color.white);
        }
        g2.fillOval(this.x, this.y, this.width, this.height);
        
        g2.setColor(Color.gray);
        g2.fillOval((int) (this.x*1.1),(int) (this.y*1.1),
                (int)(width - this.x*0.1),
                (int)(height - this.y*0.1));
        
        g2.setColor(Color.red);
        g2.drawString("" + this.identificador, x + this.width/3, y + this.height*2/3);
    }
}
