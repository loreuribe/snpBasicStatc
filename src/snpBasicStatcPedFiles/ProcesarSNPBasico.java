/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snpBasicStatcPedFiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import snpBasicStatc.SNP;
import static snpBasicStatcPedFiles.PruebaLeerArchivo.snpArray;

/**
 *
 * @author Loena
 */
public class ProcesarSNPBasico extends Thread
{
    int casohombre;
    int casomujer;
    int controlhombre;
    int controlmujer;
    int total;
    String []fileDetail;
    static ArrayList<SNP> snpArray;
    int inicioP=0;
    int finalP=0;

    public  ProcesarSNPBasico(String []fileDetail, ArrayList<SNP> snpArray,int inicioP,int finalP) {
        this.fileDetail = fileDetail;
        //snpArray = new ArrayList<SNP>();
        this.snpArray=snpArray;
        
        this.inicioP=inicioP;
        this.finalP=finalP;
    }
   
    synchronized public void procesargrupoSNP()
    {
        SNP temposnp = null;
        int casohombre=0, casomujer=0, controlhombre=0, controlmujer=0, total=0;
        int j=inicioP;
        //System.out.println("Valor de J   "+ j);
        // System.out.println("Valo Final"+ finalP);
        int i=4;
                 
                    // hombre - control
                   if(fileDetail[i].equals("1") && fileDetail[i+1].equals("0"))
                    {
                       // System.out.println("Hombre Control");
                        controlhombre+=1;
                        
                    }
                    //hombre -caso
                    if(fileDetail[i].equals("1") && fileDetail[i+1].equals("1"))
                    {
                        //System.out.println("Hombre Caso");
                        casohombre+=1;
                        
                    }
                    //mujer control
                   if(fileDetail[i].equals("2") && fileDetail[i+1].equals("0"))
                    {
                        //System.out.println("Mujer control");
                        controlmujer+=1;
                        
                    }
                    //mujer - caso
                    if(fileDetail[i].equals("2") && fileDetail[i+1].equals("1"))
                    {
                        //System.out.println("Mujer Caso");
                        casomujer+=1;
                        
                    }
                    total+=1;
                
                String tipo;
                
                //System.out.println("Valor de J   "+ j+ " Final "+ finalP);
                while(j<finalP) 
                {
                   temposnp = new SNP();
                   tipo=fileDetail[j];
                   //System.out.print("Tipo "+ tipo+ "  ");
                   if(!tipo.substring(0,1).equals(tipo.substring(2)))
                        {
                            //System.out.println("heterocigoto  "+tipo);
                            temposnp.heterocigoto.setTipo(tipo);
                            temposnp.heterocigoto.setCantidadCasoHombre(temposnp.heterocigoto.getCantidadCasoHombre()+casohombre);
                            temposnp.heterocigoto.setCantidadCasoMujer(temposnp.heterocigoto.getCantidadCasoMujer()+casomujer);
                            temposnp.heterocigoto.setCantidadControlHombre(temposnp.heterocigoto.getCantidadControlHombre()+controlhombre);
                            temposnp.heterocigoto.setCantidadControlMujer(temposnp.heterocigoto.getCantidadControlMujer()+controlmujer);
                        }
                       if(tipo.substring(0,1).equals("0"))
                        {   //System.out.println("NA  "+tipo);
                            temposnp.NA.setTipo("NA");
                            temposnp.NA.setCantidadCasoHombre(temposnp.NA.getCantidadCasoHombre()+casohombre);
                            temposnp.NA.setCantidadCasoMujer(temposnp.NA.getCantidadCasoMujer()+casomujer);
                            temposnp.NA.setCantidadControlHombre(temposnp.NA.getCantidadControlHombre()+controlhombre);
                            temposnp.NA.setCantidadControlMujer(temposnp.NA.getCantidadControlMujer()+controlmujer);
                        }
                        
                        if( tipo.substring(0,1).equals(tipo.substring(2)) && !tipo.substring(0,1).equals("0")  )
                        {  
                           // System.out.println("Tipo   "+ tipo);
                            temposnp.homocigotoMayorFr.setTipo(tipo);
                            temposnp.homocigotoMayorFr.setCantidadCasoHombre(temposnp.homocigotoMayorFr.getCantidadCasoHombre()+casohombre);
                            temposnp.homocigotoMayorFr.setCantidadCasoMujer(temposnp.homocigotoMayorFr.getCantidadCasoMujer()+casomujer);
                            temposnp.homocigotoMayorFr.setCantidadControlHombre(temposnp.homocigotoMayorFr.getCantidadControlHombre()+controlhombre);
                            temposnp.homocigotoMayorFr.setCantidadControlMujer(temposnp.homocigotoMayorFr.getCantidadControlMujer()+controlmujer);
                            
                        }
                        
                    //System.out.println("Hilo "+ j);
                    snpArray.add(j-6,temposnp);
                  j++;
                }   
                    casohombre=0; casomujer=0; controlhombre=0; controlmujer=0; total=0;
                    //System.out.println("Valor de J   "+ j+ " Final "+ finalP);
         
}

    @Override
    public void run() {
        procesargrupoSNP();
    }
           
    
    public static void main(String[] args) {
        
        BufferedReader archivo = null;
        String linea;
        String []fileDetail;
        int totalSNP;
        try {
        
            archivo = new BufferedReader(new FileReader(new File("X:\\doctorado\\plink-1.07-x86_64\\datosPLINK\\hapmap1\\prueba.ped")));
            int control=0;
            while((linea=archivo.readLine())!=null)
            {
                //textoArchivo.add(linea+"\n");
                if(control==0)
                {
                    fileDetail = linea.split("\t");
                    totalSNP=fileDetail.length-6;
                    ProcesarSNPBasico basico = new ProcesarSNPBasico(fileDetail, snpArray, 0+6, totalSNP+6);
                    basico.procesargrupoSNP();
                    control++;
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProcesarSNPBasico.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProcesarSNPBasico.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                archivo.close();
            } catch (IOException ex) {
                Logger.getLogger(ProcesarSNPBasico.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        
        System.out.println("Primero"+snpArray.get(0).toString());
        System.out.println("Primero"+snpArray.get(snpArray.size()-1));
        System.out.println("Tamaño del arreglo "+snpArray.size());
        
    }
    
}//Fin de la Clase
    
