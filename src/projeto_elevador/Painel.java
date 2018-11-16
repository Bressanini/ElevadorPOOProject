/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto_elevador;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Franciele Kuwahara, Gabriel Bressanini
 */
public class Painel extends JPanel implements KeyListener, MouseListener{

    private Elevador elevador;
    private Display display;
    private Display display_s;
    private DisplayComIndcadorAndar display_ind;
    
    public Painel(int min, int max){
        this.elevador = new Elevador(min, max, 2500, 100, 200, 250);
        elevador.setNumeroCiclosPorAndar(5);
        //elevador.addDestino(0);
        //elevador.addDestino(2);
        //elevador.addDestino(1);
        //elevador.addDestino(3);
        //elevador.addDestino(2);
        
        
        this.display_s = new Display(50, 50, new Botoeira("", 4));
        ArrayList<Botoeira> btns1 = new ArrayList<>();
        btns1.add(new Botoeira("", 2));
        this.display = new DisplayComIndcadorAndar(400, 50, btns1);
        ArrayList<Botoeira> btns2 = new ArrayList<>();
        
        for(int i = this.elevador.getAndarMin(); i <= this.elevador.getAndarMax(); i++){
            if(i == 0){
                btns2.add(new Botoeira("T", 0));
            }else{
                btns2.add(new Botoeira(""+i, i));
            }
        }
        this.display_ind = new DisplayComIndcadorAndar(750, 50, btns2);
        
    }
    
    @Override
    public void paint(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.lightGray);
        g2.fillRect(0, 0, Projeto_Elevador.WIDTH, Projeto_Elevador.HEIGHT);
        
        this.elevador.paint(g2);
        
        this.display_s.paint(g2);
        
        this.display.paint(g2);
        this.display_ind.paint(g2);
        
    }
    private int cont = 0;
    public void update() throws InterruptedException{
        //if(cont > 500){
            this.elevador.atualizaStatus();
            this.display.update(elevador);
            this.display_ind.update(elevador);
            this.display_s.update(elevador);
          //  cont = 0;
        //Thread.sleep(1000);
        //}
        //cont += 33;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {
        for(int i = 0; i < this.display.getBotoes().size(); i++){
            if(this.display.getBotoes().get(i).intersects(e.getX(), e.getY())){
                this.display.getBotoes().get(i).setState(true);
                this.elevador.addDestino(this.display.getBotoes().get(i).getAndar());
            }
        }
        
        for(int i = 0; i < this.display_s.getBotoes().size(); i++){
            if(this.display_s.getBotoes().get(i).intersects(e.getX(), e.getY())){
                this.display_s.getBotoes().get(i).setState(true);
                this.elevador.addDestino(this.display_s.getBotoes().get(i).getAndar());
            }
        }
        
        for(int i = 0; i < this.display_ind.getBotoes().size(); i++){
            if(this.display_ind.getBotoes().get(i).intersects(e.getX(), e.getY())){
                this.display_ind.getBotoes().get(i).setState(true);
                this.elevador.addDestino(this.display_ind.getBotoes().get(i).getAndar());
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    
    
    
}
