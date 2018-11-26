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
public class Painel extends JPanel implements MouseListener{

    private Elevador elevador;
    private ArrayList<Display> display;
    private ArrayList<Display> displays;
    private ArrayList<Display> display_s;
    private DisplayComIndcadorAndar display_ind;
    private ArrayList<Botoeira> botoes_aux;
    
    
    public Painel(int min, int max, int numCiclos){
        this.elevador = new Elevador(min, max, 2500, 100, 200, 250);
        elevador.setNumeroCiclosPorAndar(numCiclos);
        
        this.botoes_aux = new ArrayList<>();
        
        this.display = new ArrayList<>();
        ArrayList<Botoeira> btns2 = new ArrayList<>();
        
        this.display_s = new ArrayList<>();
        
        for(int i = this.elevador.getAndarMin(); i <= this.elevador.getAndarMax(); i++){
            
            Botoeira b_ax = new Botoeira(i+"", 1000+i);
            b_ax.setX((int) (Projeto_Elevador.WIDTH - 2.5*this.elevador.getWidth()));
            b_ax.setY((int) (Projeto_Elevador.HEIGHT - this.elevador.getHeight()/1.5  - Projeto_Elevador.HEIGHT/(this.elevador.getAndarMax()-this.elevador.getAndarMin() + 1)*(i-this.elevador.getAndarMin())));
            b_ax.setWidth(60);
            b_ax.setHeight(60);
            this.botoes_aux.add(b_ax);
            
            ArrayList<Botoeira> btns1 = new ArrayList<>();
            btns1.add(new Botoeira("", i));
            this.display.add(new DisplayComIndcadorAndar(155, 100, btns1, false));
            this.display_s.add(new Display(10, 100, new Botoeira("", i), false));
        
            if(i == 0){
                btns2.add(new Botoeira("T", 0));
            }else{
                btns2.add(new Botoeira(""+i, i));
            }
        }
        this.display_ind = new DisplayComIndcadorAndar(300, 10, btns2, true);
        
        this.displays = new ArrayList<>();
        
        for(int i = this.elevador.getAndarMin(); i <= this.elevador.getAndarMax(); i++){
            Display d = new Display(10, 10, new Botoeira(i+"", i), true);
        }
        
    }
    
    @Override
    public void paint(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.lightGray);
        g2.fillRect(0, 0, Projeto_Elevador.WIDTH, Projeto_Elevador.HEIGHT);
        
        this.elevador.paint(g2);
        
        for(int i = 0; i < this.botoes_aux.size(); i++){
            this.botoes_aux.get(i).paint(g2);
            if(this.botoes_aux.get(i).getState()){
                if(this.botoes_aux.get(i).getAndar() - 1000 != 0){
                    g2.drawString((this.botoes_aux.get(i).getAndar()-1000)+"º Andar", 50, 60);
                }else{
                    g2.drawString("Terreo", 50, 60);
                }
            }
        }
        
        for(int i = 0; i < this.display_s.size(); i++){
            if(this.display_s.get(i).isVisible()){
                this.display_s.get(i).paint(g2);
            }
        }
        
        this.display_ind.paint(g2);
        
        for(int i = 0; i < this.display.size(); i++){
            if(this.display.get(i).isVisible()){
                this.display.get(i).paint(g2);
            }
        }
        
        g2.setColor(Color.black);
        g2.setFont(new Font("Arial", Font.ITALIC, 14));
        g2.drawString("Trabalho do Curso de P.O.O.", 20, Projeto_Elevador.HEIGHT - 80);
        g2.drawString("Franciele Kuwahara", 20, Projeto_Elevador.HEIGHT - 60);
        g2.drawString("Gabriel Bressanini", 20, Projeto_Elevador.HEIGHT - 40);
        g2.drawString("UFSC - Blumenau - 2018/2", 20, Projeto_Elevador.HEIGHT - 20);
        
        for(int i = 0; i < this.displays.size(); i++){
            displays.get(i).paint(g2);
        }
    }
    private int cont = 0;
    public void update() throws InterruptedException{
        //if(cont > 500){
            this.elevador.atualizaStatus();
            
            for(int i = 0; i < this.display.size(); i++){
                if(this.display.get(i).isVisible()){
                    this.display.get(i).update(elevador);
                }
            }
            
            for(int i = 0; i < this.display_s.size(); i++){
                if(this.display_s.get(i).isVisible()){
                    this.display_s.get(i).update(elevador);
                }
            }   
        
            this.display_ind.update(elevador);
            
            System.out.println(this.elevador.toString());
          //  cont = 0;
        //Thread.sleep(1000);
        //}
        //cont += 33;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for(int i = 0; i < this.botoes_aux.size(); i++){
            if(this.botoes_aux.get(i).intersects(e.getX(), e.getY())){
                for(int j = 0; j < this.display.size(); j++){
                    for(int k = 0; k < this.display.get(j).getBotoes().size(); k++){
                        if(this.botoes_aux.get(i).getAndar() - 1000 == this.display.get(j).getBotoes().get(k).getAndar()){
                            this.display.get(j).setVisible(!this.display.get(j).isVisible());
                        }else{
                            this.display.get(j).setVisible(false);
                        }
                    }
                }
                
                for(int j = 0; j < this.display_s.size(); j++){
                    for(int k = 0; k < this.display_s.get(j).getBotoes().size(); k++){
                        if(this.botoes_aux.get(i).getAndar() - 1000 == this.display_s.get(j).getBotoes().get(k).getAndar()){
                            this.display_s.get(j).setVisible(!this.display_s.get(j).isVisible());
                        }else{
                            this.display_s.get(j).setVisible(false);
                        }
                    }
                }
                
                //this.display_s.get(this.botoes_aux.get(i).getAndar() - 1000).setVisible(true);
                this.botoes_aux.get(i).setState(!this.botoes_aux.get(i).getState());
                for(int aux = 0; aux < this.botoes_aux.size(); aux++){
                    if(aux != i){
                        this.botoes_aux.get(aux).setState(false);
                    }
                }
            }
        }
        
        for(int j = 0; j < this.display.size(); j++){
            for(int i = 0; i < this.display.get(j).getBotoes().size(); i++){
                if(this.display.get(j).getBotoes().get(i).intersects(e.getX(), e.getY())){
                    if(this.display.get(j).isVisible()){
                        this.display.get(j).getBotoes().get(i).setState(true);
                        this.elevador.addDestino(this.display.get(j).getBotoes().get(i).getAndar());
                    }
                }
            }
        }
        for(int j = 0; j < this.display_s.size(); j ++){
            for(int i = 0; i < this.display_s.get(j).getBotoes().size(); i++){
                if(this.display_s.get(j).getBotoes().get(i).intersects(e.getX(), e.getY())){
                    if(this.display_s.get(j).isVisible()){
                        this.display_s.get(j).getBotoes().get(i).setState(true);
                        this.elevador.addDestino(this.display_s.get(j).getBotoes().get(i).getAndar());
                    }
                }
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
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    
    
    
}
