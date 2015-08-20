/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snpBasicStatcPedFiles;

import java.util.ArrayList;
import snpBasicStatc.SNP;

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
    String[] fileDetail;
    static ArrayList<SNP> snpArray;

    public  ProcesarSNPBasico(int casohombre, int casomujer, int controlhombre, int controlmujer, int total, int j, String[] fileDetail, ArrayList<SNP> snpArray) {
        this.casohombre = casohombre;
        this.casomujer = casomujer;
        this.controlhombre = controlhombre;
        this.controlmujer = controlmujer;
        this.total = total;
        this.j = j;
        this.fileDetail = fileDetail;
        this.snpArray=snpArray;
    }
    
    
    
    
    
    public synchronized void procesarPrimeroSNP()
    {
                        SNP temposnp = new SNP();
                       
                        String tipo=fileDetail[j];
                        System.out.println("tipo="+tipo);
                        
                        if(!fileDetail[j].substring(0,1).equals(fileDetail[j].substring(2)))
                        {
                            //System.out.println("heterocigoto  "+tipo);
                            temposnp.heterocigoto.setTipo(tipo);
                            temposnp.heterocigoto.setCantidadCasoHombre(temposnp.heterocigoto.getCantidadCasoHombre()+casohombre);
                            temposnp.heterocigoto.setCantidadCasoMujer(temposnp.heterocigoto.getCantidadCasoMujer()+casomujer);
                            temposnp.heterocigoto.setCantidadControlHombre(temposnp.heterocigoto.getCantidadControlHombre()+controlhombre);
                            temposnp.heterocigoto.setCantidadControlMujer(temposnp.heterocigoto.getCantidadControlMujer()+controlmujer);
                        }
                       if(fileDetail[j].substring(0,1).equals("0"))
                        {   //System.out.println("NA  "+tipo);
                            temposnp.NA.setTipo("NA");
                            temposnp.NA.setCantidadCasoHombre(temposnp.NA.getCantidadCasoHombre()+casohombre);
                            temposnp.NA.setCantidadCasoMujer(temposnp.NA.getCantidadCasoMujer()+casomujer);
                            temposnp.NA.setCantidadControlHombre(temposnp.NA.getCantidadControlHombre()+controlhombre);
                            temposnp.NA.setCantidadControlMujer(temposnp.NA.getCantidadControlMujer()+controlmujer);
                        }
                        
                        if( fileDetail[j].substring(0,1).equals(fileDetail[j].substring(2)) )
                        {   //System.out.println("homocigoto  "+tipo);
                            temposnp.homocigotoMayorFr.setTipo(tipo);
                            temposnp.homocigotoMayorFr.setCantidadCasoHombre(temposnp.homocigotoMayorFr.getCantidadCasoHombre()+casohombre);
                            temposnp.homocigotoMayorFr.setCantidadCasoMujer(temposnp.homocigotoMayorFr.getCantidadCasoMujer()+casomujer);
                            temposnp.homocigotoMayorFr.setCantidadControlHombre(temposnp.homocigotoMayorFr.getCantidadControlHombre()+controlhombre);
                            temposnp.homocigotoMayorFr.setCantidadControlMujer(temposnp.homocigotoMayorFr.getCantidadControlMujer()+controlmujer);
                            
                        }
                        
                    //System.out.println("Hilo "+ j);
                    snpArray.add(temposnp);
              
                    
                    casohombre=0; casomujer=0; controlhombre=0; controlmujer=0; total=0;
                    
}

    @Override
    public void run() {
        procesarPrimeroSNP();
        
    }
                  
 }//Fin de la Clase
    
