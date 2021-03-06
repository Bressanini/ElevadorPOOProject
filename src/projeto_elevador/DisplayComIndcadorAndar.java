/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto_elevador;

import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author gabri
 */
public class DisplayComIndcadorAndar extends Display{
    
    private IndicadorAndarAtual indicador_andar;
    private int width;

    public DisplayComIndcadorAndar(int x, int y, ArrayList<Botoeira> botoes, boolean isVisible) {
        super(x, y, isVisible, botoes, new IndicadorSentido((int)(x + 135/10),
                (int)(y + 150),(135*8/10), (int)(135*8/10)));
        this.width = 135;
        this.indicador_andar = new IndicadorAndarAtual((int)(x + this.width/10),
                (int)(y + this.width/10),
                (int)(this.width*8/10),
                (int)(this.width*8/10));
        
    }
        
    @Override
    public void paint(Graphics2D g2){
        super.paint(g2);
        this.indicador_andar.paint(g2);
    }
    
    @Override
    public void update(Elevador elevador){
        this.indicador_andar.setAndar_atual(elevador.getAndarAtual());
        super.indicador_sentido.setSentido(elevador.getEstadoMotor_int());
        
        if(elevador.getEstadoMotor_int() == 0){
            for(int i = 0; i < super.botoes.size(); i++){
                if(super.botoes.get(i).getAndar() == elevador.getAndarAtual()){
                    super.botoes.get(i).setState(false);
                }
            }
        }
    }
}
