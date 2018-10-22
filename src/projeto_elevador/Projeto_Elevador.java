/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto_elevador;

/**
 *
 * @author Franciele Kuwahara, Gabriel Bressanini
 */
public class Projeto_Elevador {

    public static void main(String[] args) throws InterruptedException {
        
        Elevador a1 = new Elevador(0,5);
        
        a1.addDestino(1);
        a1.addDestino(3);
        a1.addDestino(5);
        a1.addDestino(1);
        a1.setNumeroCiclosPorAndar(4);
        while(true){
            a1.atualizaStatus();
            System.out.println(a1.toString());
            Thread.sleep(500);
        }
        
    }
    
}
