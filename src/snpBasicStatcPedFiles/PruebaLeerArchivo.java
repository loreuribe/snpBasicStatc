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
public class PruebaLeerArchivo {

    /**
     * @param args the command line arguments
     * 
     * 
     */
     public static ArrayList<SNP> snpArray;
     public SNP temposnp=null;
     String[] fileDetail;
     int contlineas=0;

    public PruebaLeerArchivo() {
        snpArray = new ArrayList<SNP>();
        
    }
     
    
    public ArrayList<String> leer_Archivo(String direccion){
          
        ArrayList<String> textoArchivo=new ArrayList<>();
        
       
            try 
            {
                BufferedReader archivo = new BufferedReader(new FileReader(new File(direccion)));
                String linea;
                int control=0;
                
                if( (linea=archivo.readLine()) != null)
                {
                    textoArchivo.add(linea+"\n");
                    procesarLinea(linea,control);
                    control++;
                
                }
                    
                
               while((linea=archivo.readLine())!=null)
               { 
                    textoArchivo.add(linea+"\n");  
                    procesarLinea(linea,control);       
                    control ++;
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
    
    
    
    public synchronized void procesarLinea (String linea, int control)
    {
        //variable compartida por los hilos
        fileDetail = linea.split("\t");
        
       /* int totalSNP=fileDetail.length-6;
        System.out.println("Longitud del arreglo split   "+ totalSNP);*/
        int cores = Runtime.getRuntime().availableProcessors();
        //int hilosrestantes=fileDetail.length-(fileDetail.length/cores);
        
        System.out.println("numero de nucleos "+cores);
        
                
        int casohombre=0, casomujer=0, controlhombre=0, controlmujer=0, total=0;
        int j=6;
        int i=4;
                    // hombre - control
                   if(fileDetail[i].equals("1") && fileDetail[i+1].equals("0"))
                    {
                        controlhombre+=1;
                        
                    }
                    //hombre -caso
                    if(fileDetail[i].equals("1") && fileDetail[i+1].equals("1"))
                    {
                        casohombre+=1;
                        
                    }
                    //mujer control
                   if(fileDetail[i].equals("2") && fileDetail[i+1].equals("0"))
                    {
                        controlmujer+=1;
                        
                    }
                    //mujer - caso
                    if(fileDetail[i].equals("2") && fileDetail[i+1].equals("1"))
                    {
                        casomujer+=1;
                        
                    }
                    total+=1;
                    //System.out.println("lineas"+control);
                   
                 if(control==0)
                 {
                    System.out.println("Entre a procesar Primera Linea SNP");  
                    while(j<fileDetail.length)
                    {
                        
                        ProcesarSNPBasico proceBa = new ProcesarSNPBasico(casohombre, casomujer, controlhombre, controlmujer, total, j, fileDetail,snpArray);
                        proceBa.start();
                        j++;
                        ProcesarSNPBasico proceBa1 = new ProcesarSNPBasico(casohombre, casomujer, controlhombre, controlmujer, total, j, fileDetail,snpArray);
                        proceBa1.start();
                        j++;
                        ProcesarSNPBasico proceBa2 = new ProcesarSNPBasico(casohombre, casomujer, controlhombre, controlmujer, total, j, fileDetail,snpArray);
                        proceBa2.start();
                        j++;
                        ProcesarSNPBasico proceBa3 = new ProcesarSNPBasico(casohombre, casomujer, controlhombre, controlmujer, total, j, fileDetail,snpArray);
                        proceBa3.start();
                        j++;
                        ProcesarSNPBasico proceBa4 = new ProcesarSNPBasico(casohombre, casomujer, controlhombre, controlmujer, total, j, fileDetail,snpArray);
                        proceBa4.start();
                        j++;
                        ProcesarSNPBasico proceBa5 = new ProcesarSNPBasico(casohombre, casomujer, controlhombre, controlmujer, total, j, fileDetail,snpArray);
                        proceBa5.start();
                        j++;
                        ProcesarSNPBasico proceBa6 = new ProcesarSNPBasico(casohombre, casomujer, controlhombre, controlmujer, total, j, fileDetail,snpArray);
                        proceBa6.start();
                        j++;
                        
                        try {
                            proceBa.join();
                            proceBa1.join();
                            proceBa2.join();
                            proceBa3.join();
                            proceBa4.join();
                            proceBa5.join();
                            proceBa6.join();
                            
                        } catch (InterruptedException ex) {
                            Logger.getLogger(PruebaLeerArchivo.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        
                        if(fileDetail.length-j <8)
                        {
                            System.out.println("j-fileDetail.length"+(fileDetail.length-j));   
                         break;
                        }
                    }         
                }
                else
                {
                    while(j<fileDetail.length)
                    {
                        System.out.println("Procesando el resto");  
                      
                      ProcesarSNPRestoIndividuos  resto =new ProcesarSNPRestoIndividuos(casohombre,casomujer,controlhombre,controlmujer,total,j,fileDetail,snpArray);
                    }  
                    
                }
                 
                 
        }
       /* System.out.println("");
        System.out.println(fileDetail.length);
        System.out.print(fileDetail[6] + "  ");
        System.out.print(fileDetail[7] + "  ");*/
        
    
    
    
    
    public static void main(String[] args) 
{
        // TODO code application logic here
        PruebaLeerArchivo pp= new PruebaLeerArchivo();
        pp.leer_Archivo("X:\\doctorado\\plink-1.07-x86_64\\datosPLINK\\hapmap1\\hapmap3_r1_b36_fwd.ASW.qc.poly.recode.ped");
        //pp.leer_Archivo("../doctorado/plink-1.07-x86_64/datosPLINK/hapmap1/hapmap3_r1_b36_fwd.ASW.qc.poly.recode.ped");
        
        System.out.println("LSNP=  "+PruebaLeerArchivo.snpArray.size());
        int cont=0;
        /*for(int i=0;i<PruebaLeerArchivo.snpArray.size();i++)
        {*/
            System.out.println("Primero  SNP"+PruebaLeerArchivo.snpArray.get(0).toString()+" Pos= "+0 );
            System.out.println("Ultimo   SNP"+PruebaLeerArchivo.snpArray.get(PruebaLeerArchivo.snpArray.size()-1).toString()+" Pos= " +PruebaLeerArchivo.snpArray.size() );
            cont++;
        //}
        
                
    }
    
}
