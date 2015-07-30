/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snpBasicStatc;

import static snpBasicStatc.CalculoTiempoProcesamiento.fotmatoTiempo;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Loena
 */
public class PrincipalHilos extends Thread{


   public static void main(String[] args)  {
          
          long time_start, time_end=0;
          Procesamiento p = new Procesamiento();
          ArrayList<SNP> snp = new ArrayList<SNP>();
          snp=p.procesar.arregloSNP;
          
          int totalSNP= p.procesarSNP();
          
          time_start = System.currentTimeMillis();
          
         
          for(int i=0;i<totalSNP;i++)
          {     p.setNumhilo(i);
              try {
                  
                  
                  p.frecuenciasHilo();
                  p.frecuenciasAlelicashilo.join();
               
                  p.frecuenciasGenoHilo().start();
                  p.frecuenciasGenotipicashilo.join();
                  
                  p.equilibrioHWHilo();
                  p.equilibriohilo.join();
                  
                  p.modelosHilo();
                  p.modelohilo.join();
                  
                  p.analisisHombresMujeresHilo();
                  p.hombresmujereshilo.join();
             
                  p.validacionAnalisisHombresMujeresHilo();
                  p.validacioncruzadahombresmujereshilo.join();
                  
              } catch (InterruptedException ex) {
                  Logger.getLogger(PrincipalHilos.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
          
            
         
         /*for(int i=0;i<totalSNP;i++)
          { 
              p.setNumhilo(i);
              try {
                 p.frecuenciasAlelicashilo.join();
                 p.frecuenciasGenotipicashilo.join();
                 p.equilibriohilo.join();
                 p.modelohilo.join();
              } catch (InterruptedException ex) {
                  Logger.getLogger(PrincipalHilos.class.getName()).log(Level.SEVERE, null, ex);
              }
             
              
          }*/
        
        System.out.println("Final Contador Tiempo Secuencial   "+time_end);
        long fin = time_end - time_start;
        
         System.out.println();
        
        System.out.println("Tiempo de Procesamiento Concurrente "+fotmatoTiempo(fin));
        
    }
    
    
    
}
