/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snpBasicStatcPedFiles;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import snpBasicStatc.SNP;
import static snpBasicStatcPedFiles.PruebaLeerArchivo.snpArray;

/**
 *
 * @author Loena
 */
public class ProcesamientoPED extends Thread{
    
      private ArrayList<SNP> snp;
      private ArrayList <Particiones>particiones;
      private int inicioP;
      private int finalP;
      
      public ProcesamientoPED(ArrayList<SNP> snp, int inicioP, int finalP) 
      {
          this.snp = snp;
          this.particiones=particiones;
          this.inicioP=inicioP;
          this.finalP=finalP;
      }
      
     
          
    
    public synchronized String frecuenciasAlelicas(int numhilo)
    {
      String cadena="----------------------------------------SNP_"+(numhilo+1)+" Frecuencias Alelicas---------------------------------------------------------------------\n";
      cadena=cadena + "Alelo     Total         Frecuencias\n";
      cadena=snp.get(numhilo).homocigotoMayorFr.getTipo().substring(0,1)+"         "+snp.get(numhilo).ConteoAlelosMayorFr()+"          "+snp.get(numhilo).frecuenciasAlelicasTodosAleloMayorFr()+ "\n";
      cadena=snp.get(numhilo).homocigotoMenorFr.getTipo().substring(0,1)+ "         "+snp.get(numhilo).ConteoAlelosMenorFr() +"          "+snp.get(numhilo).frecuenciasAlelicasTodosAleloMenorFr();
      return cadena;
    }
    
    @Override
    public void run()
    {
        for(int  i=inicioP; i<finalP;i++)
        frecuenciasAlelicas(i);
    }
    
}
