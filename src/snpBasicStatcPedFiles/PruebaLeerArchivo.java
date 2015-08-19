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

    public PruebaLeerArchivo() {
        snpArray = new ArrayList<SNP>();
        
    }
     
    
    public ArrayList<String> leer_Archivo(String direccion){
          
        ArrayList<String> textoArchivo=new ArrayList<>();
       
            try {
                BufferedReader archivo = new BufferedReader(new FileReader(new File(direccion)));
                String linea;
               while((linea=archivo.readLine())!=null)
               {
                    textoArchivo.add(linea+"\n");
                    procesarLinea(linea);
                    
                }
            }
            catch(Exception ex)
            {
                System.out.println("Error: F1 "+ex.getLocalizedMessage());
                ex.printStackTrace();
            }             
      
      
        return textoArchivo;
    }
    
    
    
    public void procesarLinea (String linea)
    {
        //variable compartida por los hilos
        fileDetail = linea.split("\t");
        
        int totalSNP=fileDetail.length-6;
        //System.out.println("Longitud del arreglo split   "+ totalSNP);

        int casohombre=0, casomujer=0, controlhombre=0, controlmujer=0, total=0;
      
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
                   
                 if(snpArray.isEmpty())
                 {
                    
                    for(int j=6;j<j+8;j+=8)
                    {
                        ProcesarSNPBasico proceBa = new ProcesarSNPBasico(casohombre, casomujer, controlhombre, controlmujer, total, j, fileDetail,snpArray);
                        proceBa.run();
                        
                        
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
        
        System.out.println("LSNP=  "+PruebaLeerArchivo.snpArray.size());
        int cont=0;
        for(SNP snp : pp.snpArray){
            
            System.out.println(snp.toString()+" Contador= " +cont);
            cont++;
            
        }
        
                
    }
    
}
