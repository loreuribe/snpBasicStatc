/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snpBasicStatcPedFiles;

import java.util.ArrayList;
import snpBasicStatc.Gen;
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
        this.inicioP=inicioP;
        this.finalP=finalP;
    }
    
    
    
    
    
    synchronized public void procesargrupoSNP()
    {
        SNP temposnp = null;
        int casohombre=0, casomujer=0, controlhombre=0, controlmujer=0, total=0;
        int j=inicioP;
        //System.out.println("Valor de J   "+ j);
        //System.out.println("Valo Final"+ finalP);
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
                       // System.out.println("Mujer control");
                        controlmujer+=1;
                        
                    }
                    //mujer - caso
                    if(fileDetail[i].equals("2") && fileDetail[i+1].equals("1"))
                    {
                       // System.out.println("Mujer Caso");
                        casomujer+=1;
                        
                    }
                    total+=1;
                
                String tipo;
              
                
                while(j<finalP) 
                {               
                   
                   tipo=fileDetail[j+6];
                  // System.out.println("Tipo "+ tipo);
                        if( tipo.equals(snpArray.get(j).heterocigoto.getTipo())   || (!fileDetail[j+6].substring(0,1).equals(fileDetail[j+6].substring(2))  ))
                        {
                            //System.out.println("heterocigoto  "+tipo);
                            snpArray.get(j).heterocigoto.setTipo(tipo);
                            snpArray.get(j).heterocigoto.setCantidad          (   snpArray.get(j).heterocigoto.getCantidad()+total);
                            snpArray.get(j).heterocigoto.setCantidadCasoHombre(   snpArray.get(j).heterocigoto.getCantidadCasoHombre()+casohombre);
                            snpArray.get(j).heterocigoto.setCantidadCasoMujer    (snpArray.get(j).heterocigoto.getCantidadCasoMujer()+casomujer);
                            snpArray.get(j).heterocigoto.setCantidadControlHombre(snpArray.get(j).heterocigoto.getCantidadControlHombre()+controlhombre);
                            snpArray.get(j).heterocigoto.setCantidadControlMujer (snpArray.get(j).heterocigoto.getCantidadControlMujer()+controlmujer);
                        }
                       if(fileDetail[j+6].substring(0,1).equals("0"))
                        {   //System.out.println("NA  "+tipo);
                            snpArray.get(j).NA.setTipo("NA");
                            snpArray.get(j).NA.setCantidad          (   snpArray.get(j).NA.getCantidad()+total);
                            snpArray.get(j).NA.setCantidadCasoHombre(snpArray.get(j).NA.getCantidadCasoHombre()+casohombre);
                            snpArray.get(j).NA.setCantidadCasoMujer(snpArray.get(j).NA.getCantidadCasoMujer()+casomujer);
                            snpArray.get(j).NA.setCantidadControlHombre(snpArray.get(j).NA.getCantidadControlHombre()+controlhombre);
                            snpArray.get(j).NA.setCantidadControlMujer(snpArray.get(j).NA.getCantidadControlMujer()+controlmujer);
                        }
                        
                        if( fileDetail[j+6].substring(0,1).equals(fileDetail[j+6].substring(2)) &&  !(fileDetail[j+6].substring(0,1).equals("0")))
                        {   
                           
                            if(tipo.equals(snpArray.get(j).homocigotoMayorFr.getTipo()) )
                            {
                               snpArray.get(j).homocigotoMayorFr.setTipo(tipo);
                               snpArray.get(j).homocigotoMayorFr.setCantidad          (   snpArray.get(j).homocigotoMayorFr.getCantidad()+total);
                               snpArray.get(j).homocigotoMayorFr.setCantidadCasoHombre(snpArray.get(j).homocigotoMayorFr.getCantidadCasoHombre()+casohombre);
                               snpArray.get(j).homocigotoMayorFr.setCantidadCasoMujer(snpArray.get(j).homocigotoMayorFr.getCantidadCasoMujer()+casomujer);
                               snpArray.get(j).homocigotoMayorFr.setCantidadControlHombre(snpArray.get(j).homocigotoMayorFr.getCantidadControlHombre()+controlhombre);
                               snpArray.get(j).homocigotoMayorFr.setCantidadControlMujer(snpArray.get(j).homocigotoMayorFr.getCantidadControlMujer()+controlmujer);
                            }
                            else
                            {
                               snpArray.get(j).homocigotoMenorFr.setTipo(tipo);
                               snpArray.get(j).homocigotoMenorFr.setCantidad          (   snpArray.get(j).homocigotoMenorFr.getCantidad()+total);
                               snpArray.get(j).homocigotoMenorFr.setCantidadCasoHombre(snpArray.get(j).homocigotoMenorFr.getCantidadCasoHombre()+casohombre);
                               snpArray.get(j).homocigotoMenorFr.setCantidadCasoMujer(snpArray.get(j).homocigotoMenorFr.getCantidadCasoMujer()+casomujer);
                               snpArray.get(j).homocigotoMenorFr.setCantidadControlHombre(snpArray.get(j).homocigotoMenorFr.getCantidadControlHombre()+controlhombre);
                               snpArray.get(j).homocigotoMenorFr.setCantidadControlMujer(snpArray.get(j).homocigotoMenorFr.getCantidadControlMujer()+controlmujer);
                                
                            }
                            
                            if(snpArray.get(j).homocigotoMayorFr.getCantidad()<snpArray.get(j).homocigotoMenorFr.getCantidad())
                            {
                                Gen tempsnp=snpArray.get(j).homocigotoMayorFr;
                                snpArray.get(j).homocigotoMayorFr= snpArray.get(j).homocigotoMenorFr;
                                snpArray.get(j).homocigotoMenorFr=tempsnp;       
                             
                            }    
                        }
                        j++;
                    
                    
                }      
                 casohombre=0; casomujer=0; controlhombre=0; controlmujer=0; total=0;
                // System.out.println("Valor de J   "+ j+ " Final "+ finalP);
    }

    @Override
    public void run() {
        procesargrupoSNP();
        
    }
                  
 }//Fin de la Clase
    
