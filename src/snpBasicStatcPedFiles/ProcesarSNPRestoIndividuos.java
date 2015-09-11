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
    String []fileDetail;
    static ArrayList<SNP> snpArray;
    int inicioP;
    int finalP;
    
    public  ProcesarSNPRestoIndividuos(String []fileDetail, ArrayList<SNP> snpArray,int inicioP,int finalP) {
        this.fileDetail = fileDetail;
        //snpArray = new ArrayList<SNP>();
        this.snpArray=snpArray;
        System.out.println("Instancie los datos");
        this.inicioP=inicioP;
        this.finalP=finalP;
    }
    
    
    
    
    
    synchronized public void procesargrupoSNP()
    {
        SNP temposnp = null;
        int casohombre=0, casomujer=0, controlhombre=0, controlmujer=0, total=0;
        int j=inicioP;
        System.out.println("Valor de J   "+ j);
         System.out.println("Valo Final"+ finalP);
        int i=4;
                 
                    // hombre - control
                   if(fileDetail[i].equals("1") && fileDetail[i+1].equals("0"))
                    {
                        System.out.println("Hombre Control");
                        controlhombre+=1;
                        
                    }
                    //hombre -caso
                    if(fileDetail[i].equals("1") && fileDetail[i+1].equals("1"))
                    {
                        System.out.println("Hombre Caso");
                        casohombre+=1;
                        
                    }
                    //mujer control
                   if(fileDetail[i].equals("2") && fileDetail[i+1].equals("0"))
                    {
                        System.out.println("Mujer control");
                        controlmujer+=1;
                        
                    }
                    //mujer - caso
                    if(fileDetail[i].equals("2") && fileDetail[i+1].equals("1"))
                    {
                        System.out.println("Mujer Caso");
                        casomujer+=1;
                        
                    }
                    total+=1;
                
                String tipo=fileDetail[j];
                System.out.println("Tipo "+ tipo);
                
                while(j<finalP) 
                {               
                   
                   tipo=fileDetail[j];
                   System.out.println("Tipo "+ tipo);
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
                        j++;
                    
                    
                }      
                 casohombre=0; casomujer=0; controlhombre=0; controlmujer=0; total=0;
                 System.out.println("Valor de J   "+ j+ " Final "+ finalP);
    }

    @Override
    public void run() {
        procesargrupoSNP();
        
    }
                  
 }//Fin de la Clase
    
