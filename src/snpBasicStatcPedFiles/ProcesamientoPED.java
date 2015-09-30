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
    
      public static  ArrayList<SNP> snp;
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
      
     
          
    
    synchronized public String frecuenciasAlelicas(int numhilo)
    {
       boolean flagHomoMay= snp.get(numhilo).homocigotoMayorFr.getTipo()!=null;
       boolean flagHomoMen= snp.get(numhilo).homocigotoMenorFr.getTipo()!=null;
       boolean flagHeter= snp.get(numhilo).heterocigoto.getTipo()!=null;
       
        String cadena=null;
        cadena="----------------------------------------SNP_"+(numhilo+1)+" Frecuencias Alelicas---------------------------------------------------------------------\n";
        cadena+="Alelo     Total         Frecuencias\n";
      if(flagHomoMay && flagHomoMen && flagHeter) 
      {  
        cadena+= snp.get(numhilo).homocigotoMayorFr.getTipo().substring(0,1)+"         "+snp.get(numhilo).ConteoAlelosMayorFr()+"          "+snp.get(numhilo).frecuenciasAlelicasTodosAleloMayorFr()+"\n";
        cadena+=snp.get(numhilo).homocigotoMenorFr.getTipo().substring(0,1)+ "         "+snp.get(numhilo).ConteoAlelosMenorFr() +"          "+snp.get(numhilo).frecuenciasAlelicasTodosAleloMenorFr()+"\n";
        return cadena;
      }
      else if(flagHomoMay && flagHomoMen)
      {
        cadena+= snp.get(numhilo).homocigotoMayorFr.getTipo().substring(0,1)+"         "+snp.get(numhilo).ConteoAlelosMayorFr()+"          "+snp.get(numhilo).frecuenciasAlelicasTodosAleloMayorFr()+"\n";
        cadena+=snp.get(numhilo).homocigotoMenorFr.getTipo().substring(0,1)+ "         "+snp.get(numhilo).ConteoAlelosMenorFr() +"          "+snp.get(numhilo).frecuenciasAlelicasTodosAleloMenorFr()+"\n";
        return cadena;
      }
      else if(flagHomoMay && flagHeter)
      {
        cadena+= snp.get(numhilo).homocigotoMayorFr.getTipo().substring(0,1)+"         "+snp.get(numhilo).ConteoAlelosMayorFr()+"          "+snp.get(numhilo).frecuenciasAlelicasTodosAleloMayorFr()+"\n";
        if(snp.get(numhilo).homocigotoMayorFr.getTipo().substring(0,1).equals(snp.get(numhilo).heterocigoto.getTipo().substring(0,1)))
             cadena+=snp.get(numhilo).heterocigoto.getTipo().substring(2)+ "         "+snp.get(numhilo).ConteoAlelosMenorFr() +"          "+snp.get(numhilo).frecuenciasAlelicasTodosAleloMenorFr()+"\n";
        else    
             cadena+=snp.get(numhilo).heterocigoto.getTipo().substring(0,1)+ "         "+snp.get(numhilo).ConteoAlelosMenorFr() +"          "+snp.get(numhilo).frecuenciasAlelicasTodosAleloMenorFr()+"\n";
        
        
        return cadena;
      }
      else if(flagHomoMen && flagHeter)
      {
        
        cadena+= snp.get(numhilo).homocigotoMenorFr.getTipo().substring(0,1)+"         "+snp.get(numhilo).ConteoAlelosMayorFr()+"          "+snp.get(numhilo).frecuenciasAlelicasTodosAleloMayorFr()+"\n";
        if(snp.get(numhilo).homocigotoMenorFr.getTipo().substring(0,1).equals(snp.get(numhilo).heterocigoto.getTipo().substring(0,1)))
         cadena+=snp.get(numhilo).heterocigoto.getTipo().substring(2)+ "         "+snp.get(numhilo).ConteoAlelosMenorFr() +"          "+snp.get(numhilo).frecuenciasAlelicasTodosAleloMenorFr()+"\n";
        else    
           cadena+=snp.get(numhilo).heterocigoto.getTipo().substring(0,1)+ "         "+snp.get(numhilo).ConteoAlelosMenorFr() +"          "+snp.get(numhilo).frecuenciasAlelicasTodosAleloMenorFr()+"\n";
        return cadena;
          
      }
      
      
      
      return cadena;
    }
    
    @Override
    public void run()
    {
        for(int  i=inicioP; i<finalP;i++)
            System.out.println(frecuenciasAlelicas(i));
    }
    
}
