/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snpBasicStatcPedFiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
     public static ArrayList<SNP> snpArray;
     public SNP temposnp=null;
     String[] fileDetail;
     int contlineas=0;
     int cores =Runtime.getRuntime().availableProcessors();
     int totalSNP;
     String  linea;
     ArrayList<String> textoArchivo;
     ArrayList <ProcesarSNPBasico>arregloProcesosSNPBasico;
     ArrayList <ProcesarSNPRestoIndividuos>arregloProcesosSNPResto;
  
     
     //
     public PruebaLeerArchivo() {
        textoArchivo=new ArrayList<>();
        snpArray = new ArrayList<SNP>();
        arregloParticiones= new ArrayList<>();
       
        
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
                   }
                   else
                   {
                      fileDetail = linea.split("\t");
                      System.out.println("Tamaño de la cadena a procesar  "+fileDetail.length);
                      totalSNP=fileDetail.length-6;
                      ProcesarSiguientesLineas();
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
       int i=0;
       int  particion=(int) Math.ceil(totalSNP/cores);
       //int  particion=totalSNP/cores;
       int inicioP=0;
       int finalP=particion;
       
       
       do
       {
           Particiones p = new Particiones(inicioP, finalP);
           arregloParticiones.add(p);
           inicioP=finalP;
           finalP=finalP+particion;
        
       }while( (totalSNP-finalP)>particion);
       if(totalSNP%particion!=0)
       {       
               finalP=totalSNP;
               Particiones p = new Particiones(inicioP, finalP);
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
        //for(Particiones p:arregloParticiones)
         ProcesarSNPBasico basico;
        for(int i=0;i<arregloParticiones.size();i++)
        {
            arregloProcesosSNPBasico = new ArrayList<ProcesarSNPBasico>();
            
            basico= new ProcesarSNPBasico(fileDetail, snpArray, arregloParticiones.get(i).inicioP+6, arregloParticiones.get(i).finalP+6);
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
    }//Fin del método   
        
        
    public void ProcesarSiguientesLineas() 
    //Inicio
    {
        System.out.println("Entre a procesar el resto de lineas");
        partirSNPS();
        //for(Particiones p:arregloParticiones)
         ProcesarSNPRestoIndividuos resto;
        for(int i=0;i<arregloParticiones.size();i++)
        {
            arregloProcesosSNPBasico = new ArrayList<ProcesarSNPBasico>();
            
            resto= new ProcesarSNPRestoIndividuos(fileDetail, snpArray, arregloParticiones.get(i).inicioP+6, arregloParticiones.get(i).finalP+6);
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
        
        
       
    
    public static void main(String[] args) 
    {
        // TODO code application logic here
        PruebaLeerArchivo pp= new PruebaLeerArchivo();
        pp.leer_Archivo("X:\\doctorado\\plink-1.07-x86_64\\datosPLINK\\hapmap1\\prueba.ped");
        System.out.println("Tamaño del arreglo "+pp.snpArray.size());
       
        
        
        System.out.println("Primero"+pp.snpArray.get(0).toString());
        System.out.println("Primero"+pp.snpArray.get(pp.snpArray.size()-1));
        System.out.println("Tamaño del arreglo "+pp.snpArray.size());
       
    }
    
}
