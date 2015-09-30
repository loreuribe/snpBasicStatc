/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snpBasicStatcPedFiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import snpBasicStatc.Gen;
import snpBasicStatc.SNP;

/**
 *
 * @author Loena
 */
public class PruebaLeerArchivo  {
     
    
    ArrayList <Particiones>arregloParticiones;
    
    //Guarda la categorizacion de lso SNPS
     public static ArrayList<SNP> snpArray;
     
     public SNP temposnp=null;
     String[] fileDetail;
     int contlineas=0;
     //Procesor number in java
     int cores =Runtime.getRuntime().availableProcessors();
     //Load procesor in java
     double man = ManagementFactory.getOperatingSystemMXBean().getSystemLoadAverage();
             
     int totalSNP;
     String  linea;
     ArrayList<String> textoArchivo;
     ArrayList <ProcesarSNPBasico>arregloProcesosSNPBasico;
     ArrayList <ProcesarSNPRestoIndividuos>arregloProcesosSNPResto;
     
     long time_start, time_end;
  
     
     //
     public PruebaLeerArchivo() {
        textoArchivo=new ArrayList<>();
        snpArray = new ArrayList<SNP>();
        arregloParticiones= new ArrayList<>();
       
        
    }

    public static ArrayList<SNP> getSnpArray() {
        return snpArray;
    }

    
     
     
    //Método que lee de datos de un archivo 
     
    public ArrayList<String> leer_Archivo(String direccion){
            try 
            {
                BufferedReader archivo = new BufferedReader(new FileReader(new File(direccion)));
                int control=0;
                while((linea=archivo.readLine())!=null)
                { 
                    //textoArchivo.add(linea+"\n");
                    if(control==0)
                    {
                    
                    fileDetail = linea.split("\t");
                    System.out.println("Tamaño de la cadena a procesar  "+fileDetail.length);
                    totalSNP=fileDetail.length-6;
                    ProcesarPrimeraLinea();
                    
                    control++;
                   }
                   else
                   {
                      
                      fileDetail = linea.split("\t");
                      ProcesarSiguientesLineas();
                      time_end = System.currentTimeMillis();
                      
                   }
                }
            }
            catch(Exception ex)
            {
                System.out.println("Error: F1 "+ex.getLocalizedMessage());
                ex.printStackTrace();
                System.exit(0);
            }             
        return textoArchivo;
    }

    public void partirSNPS()
    {

        int particion = totalSNP / cores;
        System.out.println( "CORES: " + cores + ", Particion: " + particion );
        int inicioP;
        int finalP;
        Particiones p;
        for ( int i=0; i<cores; i++ ){
        inicioP = i * particion;

        if ( i == cores - 1 ){
            finalP = totalSNP;
        }
        else{
            finalP = (i + 1) * particion;
        }
           p = new Particiones(inicioP, finalP);
           arregloParticiones.add(p); 
        }


        for(Particiones pp:arregloParticiones)
        {
         System.out.println(pp.toString());
        }

}

    
    
    public void ProcesarPrimeraLinea() 
    {
        partirSNPS();
        //
         ProcesarSNPBasico basico;
         arregloProcesosSNPBasico = new ArrayList<ProcesarSNPBasico>();
        //for(int i=0;i<arregloParticiones.size();i++)
        for(Particiones p:arregloParticiones)
        {
         
            basico= new ProcesarSNPBasico(fileDetail, snpArray, p.inicioP+6, p.finalP+6);
            arregloProcesosSNPBasico.add(basico);
        }
        
        for(int i=0;i <arregloProcesosSNPBasico.size();i++)
        
        {
            //System.out.println("inicio= "+arregloProcesosSNPBasico.get(i).inicioP+ "  Final= "+arregloProcesosSNPBasico.get(i).finalP);
            arregloProcesosSNPBasico.get(i).start();
            try {
             arregloProcesosSNPBasico.get(i).join();
                
            } catch (InterruptedException ex) {
                Logger.getLogger(PruebaLeerArchivo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//Fin del método   
        
        
    public void ProcesarSiguientesLineas() 
    //Inicio
    {
        //System.out.println("Entre a procesar el resto de lineas");
   
        //for(Particiones p:arregloParticiones)
         ProcesarSNPRestoIndividuos resto=null;
          arregloProcesosSNPResto = new ArrayList<ProcesarSNPRestoIndividuos>();
        for(Particiones p:arregloParticiones)
        {
           
            
            resto= new ProcesarSNPRestoIndividuos(fileDetail, snpArray, p.inicioP, p.finalP);
            arregloProcesosSNPResto.add(resto);
        }
        
        for(int i=0;i <arregloProcesosSNPResto.size();i++)
        
        {
            arregloProcesosSNPResto.get(i).start();
            try {
             arregloProcesosSNPResto.get(i).join();
                
            } catch (InterruptedException ex) {
                Logger.getLogger(PruebaLeerArchivo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//Find del Método
        
     
    
    
    
    public void procesar()
    {
        long time_start, time_end;
        time_start = System.currentTimeMillis();
        String filePed="X:\\doctorado\\plink-1.07-x86_64\\datosPLINK\\hapmap1\\hastaSNP8.ped";
        leer_Archivo(filePed);
        time_end = System.currentTimeMillis();
        System.out.println("Tiempo de Procesar ARchivo "+ ( time_end - time_start ) +" milliseconds");
        ProcesamientoPED ped;
        ArrayList <ProcesamientoPED>arregloProcesamientoPED = new ArrayList<ProcesamientoPED>();
        System.out.println("tamano arreglo particiones  "+arregloParticiones.size());
        
        System.out.println("Tamaño del arreglo "+snpArray.size());
        for(int i=0;i<9;i++)
        {    
                 System.out.println(i+"-esimo " +  snpArray.get(i).toString());
                 
                 //i=pp.snpArray.size();
        }
         System.out.println((snpArray.size()/2)+"-esimo " +  snpArray.get((snpArray.size()/2)).toString());
         System.out.println((snpArray.size()-2)+"-esimo " +  snpArray.get((snpArray.size()-2)).toString());
         System.out.println((snpArray.size()-1)+"-esimo " +  snpArray.get((snpArray.size()-1)).toString());
        
        for(Particiones p:arregloParticiones)
        {
           
            
            try {
                ped= new ProcesamientoPED(snpArray, p.inicioP, p.finalP);
                ped.start();
                ped.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(PruebaLeerArchivo.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
       
    
    public static void main(String[] args) 
    {
        // TODO code application logic here
        //pp.leer_Archivo("X:\\doctorado\\plink-1.07-x86_64\\datosPLINK\\hapmap1\\prueba.ped");
        //pp.leer_Archivo("/home/auribe/doctorado/plink-1.07-x86_64/datosPLINK/hapmap1/hapmap3_r1_b36_fwd.ASW.qc.poly.recode.ped");
        //

        PruebaLeerArchivo pp= new PruebaLeerArchivo();
       
        pp.procesar();
 
        System.out.println("Tamaño del arreglo "+pp.snpArray.size());
        for(int i=0;i<9;i++)
        {    
                 System.out.println(i+"-esimo " +  pp.snpArray.get(i).toString());
                 
                 //i=pp.snpArray.size();
        }
         System.out.println((pp.snpArray.size()/2)+"-esimo " +  pp.snpArray.get((pp.snpArray.size()/2)).toString());
         System.out.println((pp.snpArray.size()-2)+"-esimo " +  pp.snpArray.get((pp.snpArray.size()-2)).toString());
         System.out.println((pp.snpArray.size()-1)+"-esimo " +  pp.snpArray.get((pp.snpArray.size()-1)).toString());
        
         
       
    }
    
}
