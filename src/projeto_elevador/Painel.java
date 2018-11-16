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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

/**
 *
 * @author Franciele Kuwahara, Gabriel Bressanini
 */
public class Painel extends JPanel implements KeyListener, MouseListener{

    private Elevador elevador;
    
    public Painel(int min, int max){
        this.elevador = new Elevador(min, max, 100, 100, 200, 250);
    }
    
    @Override
    public void paint(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.black);
        g2.fillRect(0, 0, Projeto_Elevador.WIDTH, Projeto_Elevador.HEIGHT);
        
        g2.setColor(Color.red);
        g2.fillOval(Projeto_Elevador.WIDTH/2 - 50,
                Projeto_Elevador.HEIGHT/2 - 50,
                100,
                100);
        
        this.elevador.paint(g2);
        
        g2.setFont(new Font("Arial", Font.ITALIC, 32));
        g2.drawString("Ref: ", Projeto_Elevador.WIDTH-150, 50);
        
        
        IndicadorSentido is = new IndicadorSentido(1100,700,300,300);
        is.setSentido(0);
        is.paint(g2);
        
        IndicadorAndarAtual iaa = new IndicadorAndarAtual(600,600,300,300);
        iaa.paint(g2);
    }
    
    public void update(){
        this.elevador.atualizaStatus();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    
    
    
}
