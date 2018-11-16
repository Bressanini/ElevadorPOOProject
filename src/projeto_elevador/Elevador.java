/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto_elevador;

import java.awt.Color;
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
        this.andar_atual = 0;
        this.ciclos = 0;
        this.destinos = new ArrayList<Integer>();
        this.estadoMotor = 0;
        this.ciclo_atual = 3;
        this.andar_destino = -1;
        this.aguardar_usuario = false;
        this.cont_aux_escolha_usuario = 0;
        
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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
    
    public void setDescer(){
        this.estadoMotor = -1;
    }
    
    public void setParar(){
        this.estadoMotor = 0;
    }
    
    public void atualizaStatus(){
        if(this.andar_destino != -1){
            if(this.ciclo_atual == this.ciclos -1){
                this.ciclo_atual = -1;
                if(this.estadoMotor == 1){
                    this.andar_atual++;
                }
                if(this.estadoMotor == -1){
                    this.andar_atual--;
                }   
                if(this.estadoMotor == 0){
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
                }
                if(this.andar_destino < this.andar_atual){
                    this.setDescer();
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

    @Override
    public String toString() {
        return "Elevador{Andar = " + this.andar_atual + ", Sentido = " + this.estadoMotor + " " + this.getEstadoMotor()
                + ", Ciclos = " + this.ciclo_atual + ", Fila = " + this.destinos + '}';
    }
    
    public void setNumeroCiclosPorAndar(int numerociclos){
        this.ciclos = numerociclos;
        
    }      
    
    public void paint(Graphics2D g2){
        g2.setColor(Color.darkGray);
        g2.fillRect(this.x, this.y, this.width, this.height);
        g2.drawString("State: " + this.estadoMotor, this.x + this.width/2 - 10,
                this.y - 25);
    }
}
