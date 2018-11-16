/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto_elevador;

import java.awt.Dimension;
import java.awt.event.MouseListener;
import javax.swing.JFrame;

/**
 *
 * @author Franciele Kuwahara, Gabriel Bressanini
 */
public class Projeto_Elevador {

    public static final int WIDTH = 2880;
    public static final int HEIGHT = 1700;

    public static void main(String[] args) throws InterruptedException {
        Elevador a1 = new Elevador(0,5, 100, 100, 100, 250);
        int aux = 0;
        a1.addDestino(5);
        a1.addDestino(3);
        a1.addDestino(1);
        a1.addDestino(2);
        a1.setNumeroCiclosPorAndar(4);
        /*while(true){
            a1.atualizaStatus();
            System.out.println(a1.toString());
            Thread.sleep(500);
            aux += 500;
            if(aux == 40 * 500){
                a1.addDestino(3);
                a1.addDestino(5);
                a1.addDestino(3);
            }
        }*/
        
        JFrame window = new JFrame("Elevador - Curso de Programacao Orientada a Objetos");
        
        window.setResizable(false);
        window.setUndecorated(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(new Dimension(Projeto_Elevador.WIDTH, Projeto_Elevador.HEIGHT));
        window.setLocationRelativeTo(null);
        Painel winPanel = new Painel(0, 4);
        
        window.add(winPanel);
        window.setVisible(true);
        
        window.addKeyListener(winPanel);
        window.addMouseListener(winPanel);
        
        while(true){
            winPanel.repaint();
            winPanel.update();
            Thread.sleep(100);
        }
    }
    
}
