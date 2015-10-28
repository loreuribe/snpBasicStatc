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
        
        casohombre=0; casomujer=0; controlhombre=0; controlmujer=0; total=0;
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
                   SNP snpRef = snpArray.get(j);
                        if( tipo.equals(snpRef.heterocigoto.getTipo())   || (!fileDetail[j+6].substring(0,1).equals(fileDetail[j+6].substring(2))  ))
                        {
                            //System.out.println("heterocigoto  "+tipo);
                            snpRef.heterocigoto.setTipo(tipo);
                            snpRef.heterocigoto.setCantidad          (   snpRef.heterocigoto.getCantidad()+total);
                            snpRef.heterocigoto.setCantidadCasoHombre(   snpRef.heterocigoto.getCantidadCasoHombre()+casohombre);
                            snpRef.heterocigoto.setCantidadCasoMujer    (snpRef.heterocigoto.getCantidadCasoMujer()+casomujer);
                            snpRef.heterocigoto.setCantidadControlHombre(snpRef.heterocigoto.getCantidadControlHombre()+controlhombre);
                            snpRef.heterocigoto.setCantidadControlMujer (snpRef.heterocigoto.getCantidadControlMujer()+controlmujer);
                        }
                       if(fileDetail[j+6].substring(0,1).equals("0"))
                        {   //System.out.println("NA  "+tipo);
                            snpRef.NA.setTipo("NA");
                            snpRef.NA.setCantidad          (   snpRef.NA.getCantidad()+total);
                            snpRef.NA.setCantidadCasoHombre(snpRef.NA.getCantidadCasoHombre()+casohombre);
                            snpRef.NA.setCantidadCasoMujer(snpRef.NA.getCantidadCasoMujer()+casomujer);
                            snpRef.NA.setCantidadControlHombre(snpRef.NA.getCantidadControlHombre()+controlhombre);
                            snpRef.NA.setCantidadControlMujer(snpRef.NA.getCantidadControlMujer()+controlmujer);
                        }
                        
                        if( fileDetail[j+6].substring(0,1).equals(fileDetail[j+6].substring(2)) &&  !(fileDetail[j+6].substring(0,1).equals("0")))
                        {   
                           
                            if(tipo.equals(snpRef.homocigotoMayorFr.getTipo()) )
                            {
                               snpRef.homocigotoMayorFr.setTipo(tipo);
                               snpRef.homocigotoMayorFr.setCantidad          (   snpRef.homocigotoMayorFr.getCantidad()+total);
                               snpRef.homocigotoMayorFr.setCantidadCasoHombre(snpRef.homocigotoMayorFr.getCantidadCasoHombre()+casohombre);
                               snpRef.homocigotoMayorFr.setCantidadCasoMujer(snpRef.homocigotoMayorFr.getCantidadCasoMujer()+casomujer);
                               snpRef.homocigotoMayorFr.setCantidadControlHombre(snpRef.homocigotoMayorFr.getCantidadControlHombre()+controlhombre);
                               snpRef.homocigotoMayorFr.setCantidadControlMujer(snpRef.homocigotoMayorFr.getCantidadControlMujer()+controlmujer);
                            }
                            else
                            {
                               snpRef.homocigotoMenorFr.setTipo(tipo);
                               snpRef.homocigotoMenorFr.setCantidad          (   snpRef.homocigotoMenorFr.getCantidad()+total);
                               snpRef.homocigotoMenorFr.setCantidadCasoHombre(snpRef.homocigotoMenorFr.getCantidadCasoHombre()+casohombre);
                               snpRef.homocigotoMenorFr.setCantidadCasoMujer(snpRef.homocigotoMenorFr.getCantidadCasoMujer()+casomujer);
                               snpRef.homocigotoMenorFr.setCantidadControlHombre(snpRef.homocigotoMenorFr.getCantidadControlHombre()+controlhombre);
                               snpRef.homocigotoMenorFr.setCantidadControlMujer(snpRef.homocigotoMenorFr.getCantidadControlMujer()+controlmujer);
                                
                            }
                            
                            if(snpRef.homocigotoMayorFr.getCantidad()<snpRef.homocigotoMenorFr.getCantidad())
                            {
                                Gen tempsnp=snpRef.homocigotoMayorFr;
                                snpRef.homocigotoMayorFr= snpRef.homocigotoMenorFr;
                                snpRef.homocigotoMenorFr=tempsnp;                                       
                            }    
                        }
                        j++;
                    
                    
                }      
                 //casohombre=0; casomujer=0; controlhombre=0; controlmujer=0; total=0;
                // System.out.println("Valor de J   "+ j+ " Final "+ finalP);
                //System.gc();
    }

    @Override
    public void run() {
        procesargrupoSNP();               
    }
                  
 }//Fin de la Clase
    
