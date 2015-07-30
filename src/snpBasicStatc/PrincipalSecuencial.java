/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snpBasicStatc;

import static snpBasicStatc.CalculoTiempoProcesamiento.fotmatoTiempo;
import java.util.ArrayList;

/**
 *
 * @author Loena
 */
public class PrincipalSecuencial {

    /**
     * @param args the command line arguments
     */
      public static void main(String[] args)  {
          
          long time_start, time_end;
          Procesamiento p = new Procesamiento();

          ArrayList<SNP> snp = new ArrayList<SNP>();
          snp=p.procesar.arregloSNP;
          
          int totalSNP= p.procesarSNP();
          
          time_start = System.currentTimeMillis();
         
          
          System.out.println("Inicio Contador Tiempo Secuencial   "+time_start);
         
          for(int i=0;i<totalSNP;i++)
          { 
              p.setNumhilo(i);
              p.frecuenciasSecuencial();
              p.frecuenciasGenoSecuencial();
              p.equilibrioHWSecuencial();
              p.modelosSecuencial();
              p.analisisMujeres();
              p.analisisHombres();
              p.analisisHombreMujeresHomoMayor();
              p.analisisHombreMujeresHeterocigoto();
              p.analisisHombreMujeresHomoMenor();
              
          }
           p.analisisConjuntodSNPTodos();
           p.analisisConjuntodSNPCasos();
           p.analisisConjuntodSNPControles();
        time_end = System.currentTimeMillis();
        
        System.out.println("Final Contador Tiempo Secuencial   "+time_end);
        long fin = time_end - time_start;
        
         System.out.println(fotmatoTiempo(fin));
        
        System.out.println("Tiempo de Procesamiento Secuencial "+ fin +" msseconds");
        
    }
    
}
