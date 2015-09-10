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
public class ProcesarSNPRestoIndividuos extends Thread
{
    int casohombre;
    int casomujer;
    int controlhombre;
    int controlmujer;
    int total;
    int j;
    String[] fileDetail;
    static ArrayList<SNP> snpArray;

    public  ProcesarSNPRestoIndividuos(int casohombre, int casomujer, int controlhombre, int controlmujer, int total, int j, String[] fileDetail, ArrayList<SNP> snpArray) {
        this.casohombre = casohombre;
        this.casomujer = casomujer;
        this.controlhombre = controlhombre;
        this.controlmujer = controlmujer;
        this.total = total;
        this.j = j;
        this.fileDetail = fileDetail;
        this.snpArray=snpArray;
    }
    
    
    
    
    
    public synchronized void procesarSiguientes()
    {
                        SNP temposnp = new SNP();
                       
                        String tipo=fileDetail[j];
                        System.out.println("El J " +j+ " J-7 "+ (j-7));
                        
                        if( tipo.equals(snpArray.get(j-7).heterocigoto.getTipo())   || (!fileDetail[j].substring(0,1).equals(fileDetail[j].substring(2))  ))
                        {
                            //System.out.println("heterocigoto  "+tipo);
                            snpArray.get(j-7).heterocigoto.setTipo(tipo);
                            snpArray.get(j-7).heterocigoto.setCantidad          (   snpArray.get(j-7).heterocigoto.getCantidad()+total);
                            snpArray.get(j-7).heterocigoto.setCantidadCasoHombre(   snpArray.get(j-7).heterocigoto.getCantidadCasoHombre()+casohombre);
                            snpArray.get(j-7).heterocigoto.setCantidadCasoMujer    (snpArray.get(j-7).heterocigoto.getCantidadCasoMujer()+casomujer);
                            snpArray.get(j-7).heterocigoto.setCantidadControlHombre(snpArray.get(j-7).heterocigoto.getCantidadControlHombre()+controlhombre);
                            snpArray.get(j-7).heterocigoto.setCantidadControlMujer (snpArray.get(j-7).heterocigoto.getCantidadControlMujer()+controlmujer);
                        }
                       if(fileDetail[j].substring(0,1).equals("0"))
                        {   //System.out.println("NA  "+tipo);
                            snpArray.get(j-7).NA.setTipo("NA");
                            snpArray.get(j-7).NA.setCantidadCasoHombre(snpArray.get(j-7).NA.getCantidadCasoHombre()+casohombre);
                            snpArray.get(j-7).NA.setCantidadCasoMujer(snpArray.get(j-7).NA.getCantidadCasoMujer()+casomujer);
                            snpArray.get(j-7).NA.setCantidadControlHombre(snpArray.get(j-7).NA.getCantidadControlHombre()+controlhombre);
                            snpArray.get(j-7).NA.setCantidadControlMujer(snpArray.get(j-7).NA.getCantidadControlMujer()+controlmujer);
                        }
                        
                        if( fileDetail[j].substring(0,1).equals(fileDetail[j].substring(2)) )
                        {   //System.out.println("homocigoto  "+tipo);
                            
                            
                            if(tipo.equals(snpArray.get(j-7).homocigotoMayorFr.getTipo()))
                            {
                               snpArray.get(j-7).homocigotoMayorFr.setTipo(tipo);
                               snpArray.get(j-7).homocigotoMayorFr.setCantidadCasoHombre(snpArray.get(j-7).homocigotoMayorFr.getCantidadCasoHombre()+casohombre);
                               snpArray.get(j-7).homocigotoMayorFr.setCantidadCasoMujer(snpArray.get(j-7).homocigotoMayorFr.getCantidadCasoMujer()+casomujer);
                               snpArray.get(j-7).homocigotoMayorFr.setCantidadControlHombre(snpArray.get(j-7).homocigotoMayorFr.getCantidadControlHombre()+controlhombre);
                               snpArray.get(j-7).homocigotoMayorFr.setCantidadControlMujer(snpArray.get(j-7).homocigotoMayorFr.getCantidadControlMujer()+controlmujer);
                            }
                            else
                            {
                               snpArray.get(j-7).homocigotoMenorFr.setTipo(tipo);
                               snpArray.get(j-7).homocigotoMenorFr.setCantidadCasoHombre(snpArray.get(j-7).homocigotoMenorFr.getCantidadCasoHombre()+casohombre);
                               snpArray.get(j-7).homocigotoMenorFr.setCantidadCasoMujer(snpArray.get(j-7).homocigotoMenorFr.getCantidadCasoMujer()+casomujer);
                               snpArray.get(j-7).homocigotoMenorFr.setCantidadControlHombre(snpArray.get(j-7).homocigotoMenorFr.getCantidadControlHombre()+controlhombre);
                               snpArray.get(j-7).homocigotoMenorFr.setCantidadControlMujer(snpArray.get(j-7).homocigotoMenorFr.getCantidadControlMujer()+controlmujer);
                                
                            }
                        }
                        
                    
                    casohombre=0; casomujer=0; controlhombre=0; controlmujer=0; total=0;
                    
}

    @Override
    public void run() {
        procesarSiguientes();
        
    }
                  
 }//Fin de la Clase
    
