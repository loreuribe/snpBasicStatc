/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snpBasicStatcPedFiles;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.jfr.events.FileWriteEvent;
import org.json.JSONArray;
import snpBasicStatc.Gen;
import snpBasicStatc.SNP;

/**
 *
 * @author Loena
 */
public class PruebaLeerArchivo  {
     
    //JSONArray que almacena los SNP con sus respectivos cálculos
    private JSONArray snpJsonArray;
    private String ruta;
    
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
        
        this.snpJsonArray = new JSONArray();
        
              
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
                archivo.close();
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
       
        /*
        for(Particiones pp:arregloParticiones)
        {
            System.out.println(pp.toString());
        }
        */
            
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
            
            arregloProcesosSNPBasico.get(i).start();
            try {
             arregloProcesosSNPBasico.get(i).join();
                
            } catch (InterruptedException ex) {
                Logger.getLogger(PruebaLeerArchivo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        arregloProcesosSNPBasico.clear();
        arregloProcesosSNPBasico = null;
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
        //System.gc();
        arregloProcesosSNPResto.clear();
        arregloProcesosSNPResto = null;
    }//Find del Método
        
     
    
    
    
    public String procesar( String rutaArchivoPed )
    {         
        long time_Start_escritura = System.currentTimeMillis();
        String nombreArchivo = new File( rutaArchivoPed ).getName();
        // Ruta para cuando se ejecuta en Linux:
        //String ruta = String.format( "/home/santiago/snpJsonArray_%s.json", nombreArchivo.replace(".ped", "") );
        // Ruta para cuando se ejecuta en Windows:
        String ruta = String.format( "D:\\snpJsonArray_%s.json", nombreArchivo.replace(".tmp", "") );  
        // En Windows, los archivos temporales que gestiona Apache tienen extension .tmp, en Linux no quedan con extensión
        
        FileWriter writer;
        try {
            writer = new FileWriter( ruta );
            writer.write( "[" );
        writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        
        
        long time_start, time_end;
        time_start = System.currentTimeMillis();   
        leer_Archivo( rutaArchivoPed );
        time_end = System.currentTimeMillis();
        System.out.println("Tiempo de Procesar ARchivo "+ ( time_end - time_start ) +" milliseconds");
        ProcesamientoPED ped; 
        ProcesamientoPED[] arregloProcesamientoPed = new ProcesamientoPED[ arregloParticiones.size() ];
        Particiones p;
        System.out.println("tamano arreglo particiones  "+arregloParticiones.size());
        for(int i=0; i<arregloParticiones.size(); i++)
        {    
            p = arregloParticiones.get(i);
            ped = new ProcesamientoPED( snpArray, p.inicioP, p.finalP, ruta, snpJsonArray, (fileDetail.length-6)-1 );
            arregloProcesamientoPed[i] = ped;
            
            /*
            try {
                ped= new ProcesamientoPED(snpArray, p.inicioP, p.finalP, cadena);                
                ped.start();
                ped.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(PruebaLeerArchivo.class.getName()).log(Level.SEVERE, null, ex);
            } 
            */
        }   
        
        for ( ProcesamientoPED procesamientoPed : arregloProcesamientoPed ){
            procesamientoPed.start();
            try {
                procesamientoPed.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(PruebaLeerArchivo.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
        
        try {
            writer = new FileWriter( ruta, true );
            writer.append( "]" );
        writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        long time_End_escritura = System.currentTimeMillis();
        System.out.println( "---------------------------------IMPORTANTEEEE: TTiempo Finalizacion Escritura: " + (time_End_escritura - time_Start_escritura) + " ms ---------------------------------" );
        arregloProcesamientoPed = null;
        return ruta;
        //return snpJsonArray;        
    }
    
    /*
    private String escribirResultado( String rutaSnpJsonArray, JSONArray snpJsonArray ){
        File temp = new File( rutaSnpJsonArray );
        System.out.println( "Ruta snpJsonArray.json: " + temp.getAbsolutePath() );
        try {
            FileWriter writer = new FileWriter( rutaSnpJsonArray );
            writer.write(  snpJsonArray.toString()  );
            writer.close();
            return temp.getAbsolutePath();
        } 
        catch (IOException ex) {
            ex.printStackTrace();
            return "";
        }
    }
    */
       
    
    public static void main(String[] args) 
    {        
        //pp.leer_Archivo("X:\\doctorado\\plink-1.07-x86_64\\datosPLINK\\hapmap1\\prueba.ped");
        //pp.leer_Archivo("/home/auribe/doctorado/plink-1.07-x86_64/datosPLINK/hapmap1/hapmap3_r1_b36_fwd.ASW.qc.poly.recode.ped");
        //

        PruebaLeerArchivo pp= new PruebaLeerArchivo();
        String archivo = "G:\\SANTI\\hastaSNP150000.ped";
        //String archivo = "D:\\Google Drive\\Semestre X - FINAL\\Trabajo de Grado - GWAS\\archivos de entrada\\hastaSNP8.ped";  //hastaSNP8.ped
        //String archivo = "/home/santiago/TG/hastaSNP8.ped";  //hastaSNP8.ped
        String rutaSnpJsonArray = pp.procesar( archivo );
        System.out.println( rutaSnpJsonArray );
        
        /*
        IMPORTANTEEE:
            Cambios Necesarios para ejecutar en las plataformas:
            LINUX Server:
                - Cambiar la ruta del archivo de entrada por: 
                      /home/santiago/hastaSNP8.ped, en PruebaLeerArchivo.main()
        
                - Cambiar la Ruta de almacenamiento del archivo .json con los cálculos:  
                      "/home/santiago/snpJsonArray_%s.json" en PruebaLeerArchivo.procesar()
        
                - Cambiar el tamaño del Java Heap Size:
                       Click derecho en el proyecto, Properties, Apartado Run, Campo VM Options y colocar -Xms20g -Xmx20g
        
            Windows:
                - Cambiar la ruta del archivo de entrada por: 
                      /home/santiago/hastaSNP8.ped, en PruebaLeerArchivo.main()
        
                - Cambiar la Ruta de almacenamiento del archivo .json con los cálculos:  
                      "/home/santiago/snpJsonArray_%s.json" en PruebaLeerArchivo.procesar()
                
                - Cambiar el tamaño del Java Heap Size:
                       Click derecho en el proyecto, Properties, Apartado Run, Campo VM Options y colocar -Xms1g -Xmx1g
                
        */
        
       
        
        
        
        
        
        
         
       
    }
    
}
