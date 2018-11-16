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

    public DisplayComIndcadorAndar(int x, int y, int width, int height, ArrayList<Botoeira> botoes) {
        super(x, y, width, height, botoes, new IndicadorSentido((int)(x + width/10),
                (int)(y + height/10),
                (int)(y + height*8/10),
                (int)(y + height*4/10)));
        this.indicador_andar = new IndicadorAndarAtual(x, y, width, height);
    }
    
    
    @Override
    public void paint(Graphics2D g2){
        
    }
    
}
