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
public class ProcesarSNPBasico extends Thread
{
    int casohombre;
    int casomujer;
    int controlhombre;
    int controlmujer;
    int total;
    int j;
    String []fileDetail;
    static ArrayList<SNP> snpArray;
    int inicioP;
    int finalP;

    public  ProcesarSNPBasico(String []fileDetail, ArrayList<SNP> snpArray,int inicioP,int finalP) {
        this.fileDetail = fileDetail;
        this.snpArray=snpArray;
    }
   
    synchronized public void procesargrupoSNP(int inicioP, int finalP)
    {
        SNP temposnp = new SNP();
        int casohombre=0, casomujer=0, controlhombre=0, controlmujer=0, total=0;
        int j=inicioP+6;
        int i=4;
                  System.out.println("Primer SNP="+fileDetail[6]);
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
                    System.out.println("Entre a procesar Primera Linea SNP");  
                String tipo=fileDetail[j];
                while(j<finalP) 
                {
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
                        
                        if( tipo.substring(0,1).equals(tipo.substring(2)) )
                        {   //System.out.println("homocigoto  "+tipo);
                            temposnp.homocigotoMayorFr.setTipo(tipo);
                            temposnp.homocigotoMayorFr.setCantidadCasoHombre(temposnp.homocigotoMayorFr.getCantidadCasoHombre()+casohombre);
                            temposnp.homocigotoMayorFr.setCantidadCasoMujer(temposnp.homocigotoMayorFr.getCantidadCasoMujer()+casomujer);
                            temposnp.homocigotoMayorFr.setCantidadControlHombre(temposnp.homocigotoMayorFr.getCantidadControlHombre()+controlhombre);
                            temposnp.homocigotoMayorFr.setCantidadControlMujer(temposnp.homocigotoMayorFr.getCantidadControlMujer()+controlmujer);
                            
                        }
                        
                    //System.out.println("Hilo "+ j);
                    snpArray.add(temposnp);
                  
                }   
                    casohombre=0; casomujer=0; controlhombre=0; controlmujer=0; total=0;
                    
}
               
}//Fin de la Clase
    
