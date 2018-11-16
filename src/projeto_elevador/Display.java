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
    protected ArrayList<Botoeira> botoes;
    protected IndicadorSentido indicador_sentido;
    
    private int x;
    private int y;
    private int width;
    private int height;
    
    public Display(int x, int y, Botoeira botao){
        this.x = x;
        this.y = y;
        this.width = 135;
        this.botoes = new ArrayList<>();
        
        botao.setX(this.x + this.width/2 - 30);
        botao.setY(this.y + 165);
        botao.setWidth(60);
        botao.setHeight(60);
        botoes.add(botao);
        this.height = this.width*8/10 + 155;
        
        
        this.indicador_sentido = new IndicadorSentido((int)(this.x + this.width/10),
                (int)(this.y + this.height/10),
                (int)(this.width*8/10),
                (int)(this.width*8/10));
        
    }
    
    public Display(int x, int y, ArrayList<Botoeira> botoes, IndicadorSentido indicador_sentido){
        this.x = x;
        this.y = y;
        this.width = 135;
        this.height = 135 * 2 + botoes.size() * 100;        
        this.botoes = botoes;
        this.indicador_sentido = indicador_sentido;
        
        for(int i = 0; i < this.botoes.size(); i++){
            botoes.get(i).setX(this.x + this.width/2 - 30);
            botoes.get(i).setY(this.y + 285 + 100*i);
            botoes.get(i).setWidth(60);
            botoes.get(i).setHeight(60);
        }
    }
    
    public void paint(Graphics2D g2){
        g2.setColor(Color.black);
        g2.fillRoundRect(x, y, width, height, width/10, width/10);
        g2.setColor(Color.gray);
        g2.drawRoundRect(x, y, width, height, width/10, width/10);
        this.indicador_sentido.paint(g2);
        for(int i = 0; i < this.botoes.size(); i++){
            this.botoes.get(i).paint(g2);
        }
    }
    
    public void update(Elevador elevador){
        this.indicador_sentido.setSentido(elevador.getEstadoMotor_int());
        
        if(elevador.getEstadoMotor_int() == 0){
            for(int i = 0; i < this.botoes.size(); i++){
                if(this.botoes.get(i).getAndar() == elevador.getAndarAtual()){
                    this.botoes.get(i).setState(false);
                }
            }
        }
    }
    
    public ArrayList<Botoeira> getBotoes(){
            return this.botoes;
    }
}
