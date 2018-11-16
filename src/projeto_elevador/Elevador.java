/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto_elevador;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Franciele Kuwahara, Gabriel Bressanini
 */
public class Elevador {
    private int andar_min;
    private int andar_max;
    private int ciclos;
    private int ciclo_atual;
    private List<Integer> destinos;
    private int estadoMotor; // 0 = parado; 1 = subindo; -1 = descendo;
    private int andar_atual;
    private int andar_destino;
    private boolean aguardar_usuario;
    private int cont_aux_escolha_usuario;
    
    private int x;
    private int y;
    private int width;
    private int height;
    
    public Elevador(int andar_min, int andar_max, int x, int y, int width, int height){
        this.andar_min = andar_min;
        this.andar_max = andar_max;
        this.andar_atual = this.andar_min;
        this.ciclos = 0;
        this.destinos = new ArrayList<Integer>();
        this.estadoMotor = 0;
        this.ciclo_atual = 3;
        this.andar_destino = -1;
        this.aguardar_usuario = false;
        this.cont_aux_escolha_usuario = 0;
        
        this.x = Projeto_Elevador.WIDTH - width;
       // this.y = y;
        this.height = (int)(Projeto_Elevador.HEIGHT / (this.andar_max - this.andar_min +1));
        this.y = Projeto_Elevador.HEIGHT - this.height;
        this.width = width;
    }
    
    public void addDestino(int andar) {
        
        if(andar <= this.andar_max && andar >= this.andar_min){
            this.destinos.add(andar);
        }else{
            System.out.println("Andar informado eh incorreto, por favor certifique-se que o numero informado esteja correto.");
        }      
    }
    
    public void setSubir(){
        this.estadoMotor = 1;
    }
    
    public int getAndarAtual(){
        return this.andar_atual;
    }
    
    public void setDescer(){
        this.estadoMotor = -1;
    }
    
    public void setParar(){
        this.estadoMotor = 0;
    }
    
    public void atualizaStatus(){
        if(this.andar_destino != -1){
            if(this.estadoMotor == 1){
                this.y -= (Projeto_Elevador.HEIGHT/(this.andar_max - this.andar_min + 1)) / (this.ciclos);
            }
            if(this.estadoMotor == -1){
                this.y += (Projeto_Elevador.HEIGHT/(this.andar_max - this.andar_min + 1) / (this.ciclos));
            }
            if(this.ciclo_atual == this.ciclos -1){
                this.ciclo_atual = -1;
                if(this.estadoMotor == 1){
                    this.andar_atual++;
                }
                if(this.estadoMotor == -1){
                    this.andar_atual--;
                }   
                if(this.estadoMotor == 0){
                    for(int i = 0; i < this.destinos.size(); i++){
                        if(this.destinos.get(i) == this.andar_destino){
                            this.destinos.remove(i);
                        }
                    }
                    if(!this.destinos.isEmpty()){
                        this.andar_destino = this.destinos.remove(0);
                        System.out.println("Movendo para: " + this.andar_destino);
                    }else{
                        this.andar_destino = -1;
                        return;
                    }
                }
            }

            if(this.andar_destino > this.andar_atual){
                this.setSubir();
            }
            if(this.andar_destino < this.andar_atual){
                this.setDescer();
            }
            if(this.andar_destino == this.andar_atual){
                this.setParar();
               // System.out.println("Contador: " + this.cont_aux_escolha_usuario);
                if(this.cont_aux_escolha_usuario == 2){
                    this.cont_aux_escolha_usuario = 0;
                    this.ciclo_atual = this.ciclos - 2;
                }else{
                    this.cont_aux_escolha_usuario ++;
                }
            }        
            this.ciclo_atual++;
            
        }else{
            //System.out.println("Lista de Destinos Vazia!");
            if(!this.destinos.isEmpty()){
                this.andar_destino = this.destinos.remove(0);
                System.out.println("Movendo para: " + this.andar_destino);
                this.ciclo_atual = 1;    
                if(this.andar_destino > this.andar_atual){
                    this.setSubir();
                    this.y -= (Projeto_Elevador.HEIGHT/(this.andar_max - this.andar_min + 1)) / (this.ciclos);
                }
                if(this.andar_destino < this.andar_atual){
                    this.setDescer();
                    this.y += (Projeto_Elevador.HEIGHT/(this.andar_max - this.andar_min + 1)) / (this.ciclos);
                }
                if(this.andar_destino == this.andar_atual){
                    this.setParar();
                } 
            }
        }
    }
    
    public String getEstadoMotor(){
        if(this.estadoMotor == 1){
            return "Subindo";
        }
        if(this.estadoMotor == -1){
            return "Descendo";
        }
        if(this.estadoMotor == 0 || this.andar_destino == -1){
            return "Parado";
        }
        return null;
    }
    
    public int getEstadoMotor_int(){
        return this.estadoMotor;
    }

    @Override
    public String toString() {
        return "Elevador{Andar = " + this.andar_atual + ", Sentido = " + this.estadoMotor + " " + this.getEstadoMotor()
                + ", Ciclos = " + this.ciclo_atual + ", Fila = " + this.destinos + '}';
    }
    
    public void setNumeroCiclosPorAndar(int numerociclos){
        this.ciclos = numerociclos;        
    }      
    
    public void paint(Graphics2D g2){
        //Andares
        g2.setFont(new Font("Arial", Font.BOLD, 55));
        g2.setColor(Color.darkGray);
        for(int i = andar_min; i <= this.andar_max; i++){
            g2.fillRect(Projeto_Elevador.WIDTH - 2*this.width,
                    Projeto_Elevador.HEIGHT - Projeto_Elevador.HEIGHT/(this.andar_max-this.andar_min + 1)*(i-andar_min),
                    2*this.width,
                    10);
            g2.drawString(""+i, Projeto_Elevador.WIDTH - 2*this.width,
                    Projeto_Elevador.HEIGHT - Projeto_Elevador.HEIGHT/(this.andar_max-this.andar_min + 1)*(i-andar_min));
        }
        
        g2.setColor(Color.white);
        g2.setFont(new Font("Arial", Font.BOLD, 28));
        g2.drawString("Elevador", Projeto_Elevador.WIDTH - this.width*4/5,this.y);
         
        g2.setColor(Color.black);
        g2.fillRect(Projeto_Elevador.WIDTH - this.width, (int) (this.y + this.height*0.1), this.width, (int) (this.height*0.9));
        g2.setColor(Color.DARK_GRAY);
        g2.fillRect((int) (Projeto_Elevador.WIDTH - this.width*0.95), (int) (this.y + this.height*0.15), (int) (this.width), (int) (this.height*0.85));
        if(this.estadoMotor == 0){
            g2.setColor(Color.orange);
            g2.fillRect((int) (Projeto_Elevador.WIDTH - this.width*0.7), (int) (this.y + this.height*0.2),
                    (int) (this.width*0.4), (int) (this.height*0.8));
            g2.setColor(Color.black);
            g2.fillRect((int) (Projeto_Elevador.WIDTH - this.width*0.9), (int) (this.y + this.height*0.2), (int) (this.width*0.1), (int) (this.height*0.8));
            g2.fillRect((int) (Projeto_Elevador.WIDTH - this.width*0.2), (int) (this.y + this.height*0.2),
                    (int) (this.width*0.1), (int) (this.height*0.8));
        
        }else{
            g2.setColor(Color.black);
            g2.fillRect((int) (Projeto_Elevador.WIDTH - this.width*0.9), (int) (this.y + this.height*0.2), (int) (this.width*0.2), (int) (this.height*0.8));
            g2.fillRect((int) (Projeto_Elevador.WIDTH - this.width*0.3), (int) (this.y + this.height*0.2), (int) (this.width*0.2), (int) (this.height*0.8));
        
        }
        g2.setFont(new Font("Arial", Font.BOLD, 15));
        g2.drawString("Destinos: ", Projeto_Elevador.WIDTH/2, 50);
        g2.drawString(this.toString(), Projeto_Elevador.WIDTH/2, 80);
        for(int i = 0; i < this.destinos.size(); i++){ 
            g2.drawString(this.destinos.get(i)+" ", Projeto_Elevador.WIDTH/2 + 200 + i*30, 100);
        }
        
    }
    
    public int getAndarMin(){
        return this.andar_min;
    }
    public int getAndarMax(){
        return this.andar_max;
    }
    
    public List<Integer> getDestinos(){
        return this.destinos;
    }
}
