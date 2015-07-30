/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snpBasicStatc;

import java.util.Random;

/**
 *
 * @author Loena
 */
public class Aleatorio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        String []redes=  {"radar", "ruta", "ruav", "ruana", "unired", "riescar", "rup"};
        String []proponentes=  {"une", "azteca", "claro", "internexa", "Tejas Network-Verytel", "leve3", "telefonica", "ufinet","UNE"};
        Random red= new Random();
        Random prop= new Random();
        
        for(int i = 0; i<20;i++)
        {
            System.out.print(""+redes[red.nextInt(7)]);
            System.out.print(" - "+proponentes[prop.nextInt(8)]);
            System.out.println("");
        }
    }
    
}
