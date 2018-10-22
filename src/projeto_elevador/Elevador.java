/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto_elevador;

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
    
    public Elevador(int andar_min, int andar_max){
        this.andar_min = andar_min;
        this.andar_max = andar_max;
        this.andar_atual = 0;
        this.ciclos = 0;
        this.destinos = new ArrayList<Integer>();
        this.estadoMotor = 0;
        this.ciclo_atual = 0;
    }
    
    public void addDestino(int andar) {
        
        if(andar <= this.andar_max && andar >= this.andar_min){
            this.destinos.add(andar);
        }else{
            System.out.println("Andar de destino errado");
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
        if(this.ciclo_atual == this.ciclos -1){
            this.ciclo_atual = -1;
            if(this.estadoMotor == 1){
                this.andar_atual++;
            }
            if(this.estadoMotor == -1){
                this.andar_atual--;
            }
            if(this.estadoMotor == 0){
                this.destinos.remove(0);
            }
            if(this.ciclo_atual == -1){
                try{
                    System.out.println("Movendo para: " + this.destinos.get(0));
                }catch(Exception e){System.exit(0);}
            }
        }
        
        try{        
            if(this.destinos.get(0) > this.andar_atual){
                this.setSubir();
            }
            if(this.destinos.get(0) < this.andar_atual){
                this.setDescer();
            }
            if(this.destinos.get(0) == this.andar_atual){
                this.setParar();
            }
        }catch(Exception e){}
        
        this.ciclo_atual++;
    }
    
    public String getEstadoMotor(){
        if(this.estadoMotor == 1){
            return "Subindo";
        }
        if(this.estadoMotor == -1){
            return "Descendo";
        }
        if(this.estadoMotor == 0){
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
            

            
            
            }
