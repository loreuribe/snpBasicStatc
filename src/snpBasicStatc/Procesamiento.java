package snpBasicStatc;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import snpBasicStatcPedFiles.Particiones;

public class Procesamiento extends Thread {
    
    
   protected Thread modelohilo;
   protected Thread equilibriohilo;
   protected Thread frecuenciasAlelicashilo;
   protected Thread frecuenciasGenotipicashilo;
   protected Thread hombresmujereshilo;
   protected Thread validacioncruzadahombresmujereshilo;
   
           
    
    ProcesarEstructura procesar;
     ArrayList<SNP> snp;
    int numhilo=0;
    String nombreArchivo;
    
    public Procesamiento()
    {
        procesar = new ProcesarEstructura();
        snp = new ArrayList<SNP>();
        //procesar.Procesar_Archivo();
    }

    public Procesamiento( Particiones p ) {
        
        procesar = new ProcesarEstructura();
        snp = new ArrayList<SNP>();
        //procesar.Procesar_Archivo();
        snp= procesar.arregloSNP;
        this.numhilo= numhilo;
        
    }

    
    public void setNumhilo(int numhilo) {
        this.numhilo = numhilo;
    }

    public void setSnp(ArrayList<SNP> snp) {
        this.snp = snp;
    }

    
    public int procesarSNP()
    {
        
        snp = new ArrayList<SNP>();
        procesar.organizarSNP();
        snp=procesar.arregloSNP;
        return snp.size();
    }
    
    @Override
    public void run(){
        
    }
    
    
    public synchronized void frecuenciasAlelicas()
    {
      System.out.println("----------------------------------------SNP_"+(numhilo+1)+" Frecuencias Alelicas---------------------------------------------------------------------");
      System.out.println("Alelo     Total         Frecuencias");
      System.out.println( snp.get(numhilo).homocigotoMayorFr.getTipo().substring(0,1)+"         "+snp.get(numhilo).ConteoAlelosMayorFr()+"          "+snp.get(numhilo).frecuenciasAlelicasTodosAleloMayorFr());
      System.out.println( snp.get(numhilo).homocigotoMenorFr.getTipo().substring(0,1)+ "         "+snp.get(numhilo).ConteoAlelosMenorFr() +"          "+snp.get(numhilo).frecuenciasAlelicasTodosAleloMenorFr());
    }
    
    public synchronized void frecuenciasAlelicasCasos()
    {
      System.out.println("----------------------------------------SNP_"+(numhilo+1)+" Frecuencias Alelicas Casos---------------------------------------------------------------------");
      System.out.println("Alelo     Total         Frecuencias");
      System.out.println( snp.get(numhilo).homocigotoMayorFr.getTipo().substring(0,1)+"         "+snp.get(numhilo).ConteoAlelosMayorFrCasos()+"          "+snp.get(numhilo).frecuenciasAlelicasCasosAleloMayorFr());
      System.out.println( snp.get(numhilo).homocigotoMenorFr.getTipo().substring(0,1)+ "         "+snp.get(numhilo).ConteoAlelosMenorFrCasos()+"          "+snp.get(numhilo).frecuenciasAlelicasCasosAleloMenorFr());
    }
    
    public synchronized  void frecuenciasAlelicasControles()
    {
      System.out.println("----------------------------------------SNP_"+(numhilo+1)+" Frecuencias Alelicas Controles---------------------------------------------------------------------");
      System.out.println("Alelo     Total         Frecuencias");
      System.out.println( snp.get(numhilo).homocigotoMayorFr.getTipo().substring(0,1)+"         "+snp.get(numhilo).ConteoAlelosMayorFrControles()+"          "+snp.get(numhilo).frecuenciasAlelicasControlesAleloMayorFr());
      System.out.println( snp.get(numhilo).homocigotoMenorFr.getTipo().substring(0,1)+ "         "+snp.get(numhilo).ConteoAlelosMenorFrControles()+"          "+snp.get(numhilo).frecuenciasAlelicasControlesAleloMenorFr());
      
    }
    
    
    
    
    
    
    public synchronized void frecuenciasGenotipicas()
    {
      System.out.println("-------------Todos  los  Sujetos:SNP_"+(numhilo+1)+" Frecuencias Genotipicas----------------------------------------------------");
      System.out.println("Genotipo     Total         Frecuencias");
      System.out.println(snp.get(numhilo).homocigotoMayorFr.getTipo()+"          "+snp.get(numhilo).homocigotoMayorFr.getCantidad()+"          "+snp.get(numhilo).frecuenciasGenotipicasTodosHomMayorFr());
      System.out.println(snp.get(numhilo).heterocigoto.getTipo()+ "          "+snp.get(numhilo).heterocigoto.getCantidad()+ "          "+snp.get(numhilo).frecuenciasGenotipicasTodosHeterocigoto());
      System.out.println(snp.get(numhilo).homocigotoMenorFr.getTipo()+ "           "+snp.get(numhilo).homocigotoMenorFr.getCantidad()+ "          "+snp.get(numhilo).frecuenciasGenotipicasTodosHomMenorFr());
      System.out.println(snp.get(numhilo).NA.getTipo()+           "            "+ snp.get(numhilo).NA.getCantidad());
    }  
    
    public synchronized void frecuenciasGenotipicasCasos()
    {
      System.out.println("-------------Todos  los  Sujetos:SNP_"+(numhilo+1)+" Frecuencias Genotipicas Casos----------------------------------------------------");
      System.out.println("Genotipo     Total         Frecuencias");
      System.out.println(snp.get(numhilo).homocigotoMayorFr.getTipo()+"          "+snp.get(numhilo).homocigotoMayorFr.totalCasos()+"          "+snp.get(numhilo).frecuenciasGenotipicasHomMayorFrCasos());
      System.out.println(snp.get(numhilo).heterocigoto.getTipo()+ "          "+snp.get(numhilo).heterocigoto.totalCasos()+ "          "+snp.get(numhilo).frecuenciasGenotipicasHeterocigotoCasos());
      System.out.println(snp.get(numhilo).homocigotoMenorFr.getTipo()+ "           "+snp.get(numhilo).homocigotoMenorFr.totalCasos()+ "          "+snp.get(numhilo).frecuenciasGenotipicasHomMenorFrCasos());
      System.out.println(snp.get(numhilo).NA.getTipo()+           "            "+ (snp.get(numhilo).NA.getCantidadCasoHombre()+snp.get(numhilo).NA.getCantidadCasoMujer()));
    
    }
     public synchronized void frecuenciasGenotipicasControles()
    {
      System.out.println("-------------Todos  los  Sujetos:SNP_"+(numhilo+1)+" Frecuencias Genotipicas Controles----------------------------------------------------");
      System.out.println("Genotipo     Total         Frecuencias");
      System.out.println(snp.get(numhilo).homocigotoMayorFr.getTipo()+"          "+snp.get(numhilo).homocigotoMayorFr.totalControles()+"          "+snp.get(numhilo).frecuenciasGenotipicasHomMayorFrControles());
      System.out.println(snp.get(numhilo).heterocigoto.getTipo()+ "          "+snp.get(numhilo).heterocigoto.totalControles()+ "          "+snp.get(numhilo).frecuenciasGenotipicasHeterocigotoControles());
      System.out.println(snp.get(numhilo).homocigotoMenorFr.getTipo()+ "           "+snp.get(numhilo).homocigotoMenorFr.totalControles()+ "          "+snp.get(numhilo).frecuenciasGenotipicasHomMenorFrControles());
      System.out.println(snp.get(numhilo).NA.getTipo()+           "            "+ (snp.get(numhilo).NA.getCantidadControlHombre()+snp.get(numhilo).NA.getCantidadControlMujer()));
    
    
    }
     
     
     
     
    
    public synchronized void equilibrioHW()
    {
      System.out.println("-------------  Equilibrio HardyWeinberg Todos SNP_"+(numhilo+1)+" --------------------------------------------------------------");
      System.out.println("                                "+
              snp.get(numhilo).homocigotoMayorFr.getTipo()+"    "+ 
              snp.get(numhilo).heterocigoto.getTipo()+ "    "+ 
              snp.get(numhilo).homocigotoMenorFr.getTipo()+ "    "+ 
              snp.get(numhilo).homocigotoMayorFr.getTipo().substring(0,1)+"     "+ 
              snp.get(numhilo).homocigotoMenorFr.getTipo().substring(0,1)    );
      
      System.out.println("Todos los Sujetos Observados:   "+
              snp.get(numhilo).homocigotoMayorFr.getCantidad()+"    "+ 
              snp.get(numhilo).heterocigoto.getCantidad()+ "    "+ 
              snp.get(numhilo).homocigotoMenorFr.getCantidad()+ "    "+ 
              snp.get(numhilo).ConteoAlelosMayorFr()+"    "+ 
              snp.get(numhilo).ConteoAlelosMenorFr());
      
      System.out.println("Todos los Sujetos Esperados :   "+
              snp.get(numhilo).esperadosTodosAlleloMayorFr()+"    "+ 
              snp.get(numhilo).esperadosTodosHetero()+ "    "+ 
              snp.get(numhilo).esperadosTodosAleloMenorFr());
      System.out.println(""); 
      System.out.println("Chi Cuadrado                :   "+
              snp.get(numhilo).chiCuadradoMayorFrecuenciaTodos()+"    "+ 
              snp.get(numhilo).chiCuadradoHeterocigotoTodos()+ "    "+ 
              snp.get(numhilo).chiCuadradoMenorFrecuenciaTodos()+ " ="+
              snp.get(numhilo).sumachiCuadradoTodosSignificancia()+"  P-VALUE: "+  snp.get(numhilo).valorSignificanciaTodos());
    } 
    
    public synchronized void equilibrioHWCasos()
    {
      System.out.println("-------------  Equilibrio HardyWeinberg Casos SNP_"+(numhilo+1)+" --------------------------------------------------------------");
      System.out.println("                   "+
              snp.get(numhilo).homocigotoMayorFr.getTipo()+"    "+ 
              snp.get(numhilo).heterocigoto.getTipo()+ "    "+ 
              snp.get(numhilo).homocigotoMenorFr.getTipo()+ "    "+ 
              snp.get(numhilo).homocigotoMayorFr.getTipo().substring(0,1)+"     "+ 
              snp.get(numhilo).homocigotoMenorFr.getTipo().substring(0,1)    );
      
      System.out.println("Casos Observados   "+
              (snp.get(numhilo).homocigotoMayorFr.getCantidadCasoHombre() + snp.get(numhilo).homocigotoMayorFr.getCantidadCasoMujer())+"    "+
              (snp.get(numhilo).heterocigoto.getCantidadCasoHombre() + snp.get(numhilo).heterocigoto.getCantidadCasoMujer())+ "    "+ 
              (snp.get(numhilo).homocigotoMenorFr.getCantidadCasoHombre() + snp.get(numhilo).homocigotoMenorFr.getCantidadCasoMujer())+ "    "+ 
              snp.get(numhilo).ConteoAlelosMayorFrCasos()+"    " + 
              snp.get(numhilo).ConteoAlelosMenorFrCasos());
      
      System.out.println("Casos Esperados    "+
              snp.get(numhilo).esperadosCasosAlleloMayorFr()+"    "+ 
              snp.get(numhilo).esperadosCasosHetero()+ "    "+ 
              snp.get(numhilo).esperadosCasosAleloMenorFr());
      System.out.println(""); 
      System.out.println("Chi Cuadrado  Casos: "+
              snp.get(numhilo).chiCuadradoMayorFrecuenciaCasos()+"    "+ 
              snp.get(numhilo).chiCuadradoHeterocigotoCasos()+ "    "+ 
              snp.get(numhilo).chiCuadradoMenorFrecuenciaCasos()+ " ="+
              snp.get(numhilo).sumachiCuadradoCasosSignificancia()+"  P-VALUE: "+ snp.get(numhilo).valorSignificanciaCasos());
    } 
    
    public synchronized void equilibrioHWControles()
    {
      System.out.println("-------------  Equilibrio HardyWeinberg Controles SNP_"+(numhilo+1)+" --------------------------------------------------------------");
      System.out.println("                   "+
              snp.get(numhilo).homocigotoMayorFr.getTipo()+"    "+ 
              snp.get(numhilo).heterocigoto.getTipo()+ "    "+ 
              snp.get(numhilo).homocigotoMenorFr.getTipo()+ "    "+ 
              snp.get(numhilo).homocigotoMayorFr.getTipo().substring(0,1)+"     "+ 
              snp.get(numhilo).homocigotoMenorFr.getTipo().substring(0,1)    );
      
      System.out.println("Controles Observados   "+
              (snp.get(numhilo).homocigotoMayorFr.getCantidadControlHombre()+snp.get(numhilo).homocigotoMayorFr.getCantidadControlMujer())+"    "+
              (snp.get(numhilo).heterocigoto.getCantidadControlHombre()+snp.get(numhilo).heterocigoto.getCantidadControlMujer())+ "    "+ 
              (snp.get(numhilo).homocigotoMenorFr.getCantidadControlHombre()+snp.get(numhilo).homocigotoMenorFr.getCantidadControlMujer())+ "    "+ 
              snp.get(numhilo).ConteoAlelosMayorFrControles()+"    "+ 
              snp.get(numhilo).ConteoAlelosMenorFrControles());
      
      System.out.println("Controles Esperados    "+
              snp.get(numhilo).esperadosControlesAlleloMayorFr()+"    "+ 
              snp.get(numhilo).esperadosControlesHetero()+ "    "+ 
              snp.get(numhilo).esperadosControlesAleloMenorFr());
      System.out.println(""); 
      System.out.println("Chi Cuadrado  Controles: "+
              snp.get(numhilo).chiCuadradoMayorFrecuenciaControles()+"    "+ 
              snp.get(numhilo).chiCuadradoHeterocigotoControles()+ "    "+ 
              snp.get(numhilo).chiCuadradoMenorFrecuenciaControles()+ " ="+
              snp.get(numhilo).sumachiCuadradoControlesSignificancia()+"  P-VALUE: "+ snp.get(numhilo).valorSignificanciaControles());
      
    }
    
    
    
    
    
    public synchronized void modeloCodominante()
    {
        System.out.println("-------------Asociacion Caso Control  SNP_"+(numhilo+1)+" Estado+ Sexo + Edad + BMI (Indice de Masa Corporal--------------------");
        System.out.println("Modelo Codominante       Genotipo   Control   Caso     OddRatio              Error             Confidence Interval[IC]");
        System.out.println("                         "+snp.get(numhilo).homocigotoMayorFr.getTipo()+"        "+snp.get(numhilo).totalControlesHomMayor()+    "     "+snp.get(numhilo).totalCasosHomoMayor());
        System.out.println("                         "+snp.get(numhilo).heterocigoto.getTipo()+ "        "+snp.get(numhilo).totalControlesHeterocigoto()+"     "+snp.get(numhilo).totalCasosHeterocigoto()+"     "+snp.get(numhilo).modeloCodominanteOddRatioHomoMayoHet()+"  "+snp.get(numhilo).errorCodominateAllelosHomoMayorHetFr()+"  ["+snp.get(numhilo).intervaloConfianzaCondominanteHomoMayorHetFrInf()+"  "+snp.get(numhilo).intervaloConfianzaCondominanteHomoMayorHetFrSup()+"]");
        System.out.println("                         "+snp.get(numhilo).homocigotoMenorFr.getTipo()+ "          "+snp.get(numhilo).totalControlesHomMenor()+   "      "+snp.get(numhilo).totalCasosHomoMenor()+   "    "+snp.get(numhilo).modeloCodominanteOddRatioHomoMayoHomoMenor()+"  "+snp.get(numhilo).errorCodominateAllelosHomoMayoMenorFr()+"  ["+snp.get(numhilo).intervaloConfianzaCodominateHomMayorHomMenorInf()+"  "+snp.get(numhilo).intervaloConfianzaCodominateHomMayorHomMenorSup()+"]");
        
    }
        
    public synchronized void modeloDominante()
    {
        System.out.println("Modelo Dominate          Genotipo   Control   Caso     OddRatio              Error                 IC");
        System.out.println("                         "+snp.get(numhilo).homocigotoMayorFr.getTipo()+"        "+snp.get(numhilo).totalControlesHomMayor()+    "     "+snp.get(numhilo).totalCasosHomoMayor());
        System.out.println("                         "+snp.get(numhilo).heterocigoto.getTipo()+"+"+snp.get(numhilo).homocigotoMenorFr.getTipo()+"     "+(snp.get(numhilo).totalControlesHomMenor()+snp.get(numhilo).totalControlesHeterocigoto())+"     "+(snp.get(numhilo).totalCasosHomoMenor()+snp.get(numhilo).totalCasosHeterocigoto())+"        "+snp.get(numhilo).modeloDominanteOddRatio()+"  "+ snp.get(numhilo).errorMayorFrecuencia()+"  ["+snp.get(numhilo).modeloDominanteICInf()+"  "+snp.get(numhilo).modeloDominanteICSup()+"]");
    }
    public synchronized void modeloRecesivo()
    {
     
    System.out.println("Modelo Recesivo          Genotipo   Control   Caso     OddRatio              Error                 IC");
        System.out.println("                         "+snp.get(numhilo).homocigotoMayorFr.getTipo()+"+"+snp.get(numhilo).heterocigoto.getTipo()+"     "+(snp.get(numhilo).totalControlesHomMayor()+snp.get(numhilo).totalControlesHeterocigoto())+"     "+(snp.get(numhilo).totalCasosHomoMayor()+snp.get(numhilo).totalCasosHeterocigoto()));
        System.out.println("                         "+snp.get(numhilo).homocigotoMenorFr.getTipo()+"         "+snp.get(numhilo).totalControlesHomMenor()+    "     "+snp.get(numhilo).totalCasosHomoMenor()+"        "+snp.get(numhilo).modeloRecesivoOddsRatio()+"   "+snp.get(numhilo).errorRecesivo()+"        "+"  ["+snp.get(numhilo).modeloRecesivoICInf()+"  "+snp.get(numhilo).modeloRecesivoICSup()+"]");
    }
    public synchronized void modeloSobreDominante()
    {
        System.out.println("Modelo Sobredominante    Genotipo   Control   Caso     OddRatio              Error                 IC");
        System.out.println("                         "+snp.get(numhilo).homocigotoMayorFr.getTipo()+"+"+snp.get(numhilo).homocigotoMenorFr.getTipo()+"     "+(snp.get(numhilo).totalControlesHomMayor()+snp.get(numhilo).totalControlesHomMenor())+"     "+(snp.get(numhilo).totalCasosHomoMayor()+snp.get(numhilo).totalCasosHomoMenor()));
        System.out.println("                         "+snp.get(numhilo).heterocigoto.getTipo()+"         "+snp.get(numhilo).totalControlesHeterocigoto()+    "     "+snp.get(numhilo).totalCasosHeterocigoto()+"        "+snp.get(numhilo).modeloSobreDominanteOddRatio()+"   "+snp.get(numhilo).errorSobreDominante()+"  ["+snp.get(numhilo).modeloSobredominanteICInf()+"  "+snp.get(numhilo).modeloSobredominanteICSup()+"]");            
    }       
    
    public synchronized void modeloAditivo()
    {
        System.out.println("Modelo Aditivo           Genotipo   Control   Caso     OddRatio              Error                 IC");
        System.out.println("                         "+snp.get(numhilo).homocigotoMayorFr.getTipo()+"+"+snp.get(numhilo).heterocigoto.getTipo()+"     "+(2*snp.get(numhilo).totalControlesHomMayor()+snp.get(numhilo).totalControlesHeterocigoto())+"     "+(2*snp.get(numhilo).totalCasosHomoMayor()+snp.get(numhilo).totalCasosHeterocigoto()));
        System.out.println("                         "+snp.get(numhilo).homocigotoMenorFr.getTipo()+"         " +snp.get(numhilo).totalControlesHomMenor()+"     "+(snp.get(numhilo).totalCasosHomoMenor())+"        "+snp.get(numhilo).modeloAditivoOddsRatio()+"   "+snp.get(numhilo).errorAditivo()+"  ["+snp.get(numhilo).modeloAditivoICInf()+"  "+snp.get(numhilo).modeloAditivoICSup()+"]");        
    }
    
    public synchronized void analisisMujeres()
    {
        System.out.println("SNP"+(numhilo+1)+" Mujeres");
        System.out.println("Mujeres");
        System.out.println("Genotipo  Control  Caso  OddRatio      IntervaloConfianzaCI");
        System.out.println(snp.get(numhilo).homocigotoMayorFr.getTipo()+"        " + snp.get(numhilo).homocigotoMayorFr.getCantidadControlMujer()+"   "+snp.get(numhilo).homocigotoMayorFr.getCantidadCasoMujer());
        System.out.println(snp.get(numhilo).heterocigoto.getTipo()+     "        " + snp.get(numhilo).heterocigoto.getCantidadControlMujer()+     "   "+snp.get(numhilo).heterocigoto.getCantidadCasoMujer()+     "    "+snp.get(numhilo).oddRatioMujeresHomoMayorHet()+        "["+ snp.get(numhilo).intervaloConfianzaMujeresHomoMayorHetInf()+    "  "+ snp.get(numhilo).intervaloConfianzaMujeresHomoMayorHetSup()+"]");
        System.out.println(snp.get(numhilo).homocigotoMenorFr.getTipo()+"        " + snp.get(numhilo).homocigotoMenorFr.getCantidadControlMujer()+"    "+snp.get(numhilo).homocigotoMenorFr.getCantidadCasoMujer()+"     "+snp.get(numhilo).oddRatioMujeresHomoMayorHomoMenor()+"["+ snp.get(numhilo).intervaloConfianzaMujeresHomMayorHomoMenorInf()+"  "+ snp.get(numhilo).intervaloConfianzaMujeresHomoMayorHomoMenorFrSup()+"]");
    }
    public synchronized void analisisHombres()
    {
        System.out.println("SNP"+(numhilo+1)+"   Hombres" );
        System.out.println("Hombres");
        System.out.println("Genotipo  Control  Caso  OddRatio      IntervaloConfianzaCI");
        System.out.println(snp.get(numhilo).homocigotoMayorFr.getTipo()+ "       " + snp.get(numhilo).homocigotoMayorFr.getCantidadControlHombre()+"       "+snp.get(numhilo).homocigotoMayorFr.getCantidadCasoHombre());
        System.out.println(snp.get(numhilo).heterocigoto.getTipo()+      "       " + snp.get(numhilo).heterocigoto.getCantidadControlHombre()+     "       "+snp.get(numhilo).heterocigoto.getCantidadCasoHombre()+     "     "+snp.get(numhilo).oddRatioHombresHomoMayorHet()+" ["+snp.get(numhilo).intervaloConfianzaHombresHomoMayorHetInf()+" "+snp.get(numhilo).intervaloConfianzaHombresHomoMayorHetSup()+"]");
        System.out.println(snp.get(numhilo).homocigotoMenorFr.getTipo()+ "       " + snp.get(numhilo).homocigotoMenorFr.getCantidadControlHombre()+"        "+snp.get(numhilo).homocigotoMenorFr.getCantidadCasoHombre()+"    "+snp.get(numhilo).oddRatioHombresHomoMayorHomoMenor()+" ["+snp.get(numhilo).intervaloConfianzaHombresHomMayorHomoMenorInf()+" "+snp.get(numhilo).intervaloConfianzaHombresHomoMayorHomoMenorFrSup()+"]");
    }
    
    public synchronized void analisisHombreMujeresHomoMayor()
    {
        System.out.println("SNP"+(numhilo+1)+"   Clasificación Cruzada Hombres/Mujers" );
        System.out.println("Genotipo  Control  Caso  OddRatio      IntervaloConfianzaCI");
        System.out.println(snp.get(numhilo).homocigotoMayorFr.getTipo()+" Mujer  "+snp.get(numhilo).homocigotoMayorFr.getCantidadControlMujer()+ "        "+snp.get(numhilo).homocigotoMayorFr.getCantidadCasoMujer());
        System.out.println(snp.get(numhilo).homocigotoMayorFr.getTipo()+" Hombre "+snp.get(numhilo).homocigotoMayorFr.getCantidadControlHombre()+"        "+snp.get(numhilo).homocigotoMayorFr.getCantidadCasoHombre()+" "+snp.get(numhilo).oddRatioHombreMujeresHomoMayorFR()+" ["+ snp.get(numhilo).intervaloConfianzaHombreMujeresHomoMayorFRInf()+" "+snp.get(numhilo).intervaloConfianzaHombreMujeresHomoMayorFRSup());      
        
    }
    
    public synchronized void analisisHombreMujeresHeterocigoto()
    {
        System.out.println("SNP"+(numhilo+1)+"   Clasificación Cruzada Hombres/Mujers" );
        System.out.println("Genotipo  Control  Caso  OddRatio      IntervaloConfianzaCI");
        System.out.println(snp.get(numhilo).heterocigoto.getTipo()+" Mujer  "+snp.get(numhilo).heterocigoto.getCantidadControlMujer()+  "        "+ snp.get(numhilo).heterocigoto.getCantidadCasoMujer());
        System.out.println(snp.get(numhilo).heterocigoto.getTipo()+" Hombre " +snp.get(numhilo).heterocigoto.getCantidadControlHombre()+"        "+snp.get(numhilo).heterocigoto.getCantidadCasoHombre()+" "+snp.get(numhilo).oddRatioHombreMujeresHetero()+" ["+ snp.get(numhilo).intervaloConfianzaHombreMujeresHeteroInf()+" "+snp.get(numhilo).intervaloConfianzaHombreMujeresHeteroSup());      
        
    }
    public synchronized void analisisHombreMujeresHomoMenor()
    {
        System.out.println("SNP"+(numhilo+1)+"   Clasificación Cruzada Hombres/Mujers" );
        System.out.println("Genotipo  Control  Caso  OddRatio      IntervaloConfianzaCI");
        System.out.println(snp.get(numhilo).homocigotoMenorFr.getTipo()+ " Mujer  "+snp.get(numhilo).homocigotoMenorFr.getCantidadControlMujer()+"       "+ snp.get(numhilo).homocigotoMenorFr.getCantidadCasoMujer());
        System.out.println(snp.get(numhilo).homocigotoMenorFr.getTipo()+ " Hombre "+snp.get(numhilo).homocigotoMenorFr.getCantidadControlHombre()+"      "+snp.get(numhilo).homocigotoMenorFr.getCantidadCasoHombre()+" "+snp.get(numhilo).oddRatioHombreMujerHomoMenorFr()+" ["+ snp.get(numhilo).intervaloConfianzaHombreMujeresMenorFrInf()+" "+snp.get(numhilo).intervaloConfianzaHombreMujeresMenorFrSup());      
        
    }
    
    
    
    public synchronized void analisisHombresMujeres()
    {
        System.out.println("SNP"+(numhilo+1));
        analisisHombreMujeresHomoMayor();
        analisisHombreMujeresHeterocigoto();
        analisisHombreMujeresHomoMenor();
            
        
    }
    
    
    
     public  synchronized void equilibrioHWHilo() { 
          
        equilibriohilo = new Thread(()->{            
            
                  equilibrioHW();
                  equilibrioHWCasos();
                  equilibrioHWControles();
        });
       equilibriohilo.start();
    }    
    
     public synchronized void frecuenciasHilo(){
       frecuenciasAlelicashilo = new Thread(()->{   
      frecuenciasAlelicas();
      frecuenciasAlelicasCasos();
      frecuenciasAlelicasControles();
      });
       frecuenciasAlelicashilo.start();
    } 
     public synchronized Thread frecuenciasGenoHilo(){
       frecuenciasGenotipicashilo = new Thread(()->{   
      frecuenciasGenotipicas();
      frecuenciasGenotipicasCasos();
      frecuenciasGenotipicasControles();
      });
       return frecuenciasGenotipicashilo;
    } 
     
     public synchronized Thread analisisHombresMujeresHilo(){
       hombresmujereshilo = new Thread(()->{   
        analisisMujeres();
        analisisHombres();
      });
       return frecuenciasGenotipicashilo;
    } 
     
     public synchronized Thread validacionAnalisisHombresMujeresHilo(){
       validacioncruzadahombresmujereshilo = new Thread(()->{   
       analisisHombreMujeresHomoMayor();
       analisisHombreMujeresHeterocigoto();
       analisisHombreMujeresHomoMenor();
       });
       return frecuenciasGenotipicashilo;
    } 
             
     public synchronized void modelosHilo(){ 
        
        modelohilo = new Thread(()->
        {            
            modeloCodominante();
            modeloDominante();
            modeloRecesivo();
            modeloSobreDominante();
            modeloAditivo();
                
        });
        modelohilo.start();
    } 
    
    
    
    public void equilibrioHWSecuencial(){ 
          
                  equilibrioHW();
                  equilibrioHWCasos();
                  equilibrioHWControles();
    }    
    
    public void frecuenciasSecuencial(){
       
      frecuenciasAlelicas();
      frecuenciasAlelicasCasos();
      frecuenciasAlelicasControles();
     
    } 
    public void frecuenciasGenoSecuencial(){
      
      frecuenciasGenotipicas();
      frecuenciasGenotipicasCasos();
      frecuenciasGenotipicasControles();
      
    } 
     
    public void modelosSecuencial(){ 
        
                
            modeloCodominante();
            modeloDominante();
            modeloRecesivo();
            modeloSobreDominante();
            modeloAditivo();
                
      }    
     
        
    public void analisisConjuntodSNPTodos()
    {   System.out.println("----------------Todos los individuos------------------");
        for(int i=0;i<snp.size()-1;i++)
        {
           for(int j=i+1;j<snp.size();j++)
           {    
            
            System.out.print("SNP"+(i+1)+","+(j+1)+ "  ");
            AlelosConjuntosSNP aleconj= new AlelosConjuntosSNP(snp.get(i).frecuenciasAlelicasTodosAleloMayorFr(),snp.get(j).frecuenciasAlelicasTodosAleloMayorFr(),snp.get(i).frecuenciasAlelicasTodosAleloMayorFr(),snp.get(j).frecuenciasAlelicasTodosAleloMenorFr(),i,j);
            aleconj.setArregloSNP(snp);
            aleconj.linkageDisequilibrium();
            System.out.println("LD          ="   +   aleconj.LD);
            System.out.println("DMax        =" + aleconj.DMax);
            System.out.println("Person Coef ="+aleconj.Pearsoncoefficientofcorrelation());
            System.out.println(aleconj.AlelosConjuntosChiCuadrado(snp.get(i).ConteoAlelosMayorFr(), snp.get(i).ConteoAlelosMenorFr() , snp.get(j).ConteoAlelosMayorFr(), snp.get(j).ConteoAlelosMenorFr()));
           }
        }
    }
    
    public void analisisConjuntodSNPCasos()
    {
        System.out.println("----------------Casos-----------------------------");
        for(int i=0;i<snp.size()-1;i++)
        {
           for(int j=i+1;j<snp.size();j++)
           {    
            
            System.out.print("SNP"+(i+1)+","+(j+1)+ "  ");
            AlelosConjuntosSNP aleconj= new AlelosConjuntosSNP(snp.get(i).frecuenciasAlelicasCasosAleloMayorFr(),snp.get(j).frecuenciasAlelicasCasosAleloMayorFr(),snp.get(i).frecuenciasAlelicasCasosAleloMayorFr(),snp.get(j).frecuenciasAlelicasCasosAleloMenorFr(),i,j);
            aleconj.setArregloSNP(snp);
            double LD=aleconj.linkageDisequilibrium();
            System.out.println("LD          ="   +  LD);
            System.out.println("DMax        =" + aleconj.DMax);
            System.out.println("Person Coef ="+aleconj.Pearsoncoefficientofcorrelation());
            System.out.println(aleconj.AlelosConjuntosChiCuadrado(snp.get(i).ConteoAlelosMayorFrCasos(), snp.get(i).ConteoAlelosMenorFrCasos(), snp.get(j).ConteoAlelosMayorFr(), snp.get(j).ConteoAlelosMenorFrCasos()));
           }
        }
    }
    
    public void analisisConjuntodSNPControles()
    {
        System.out.println("--------------------Controles------------------- ");
        for(int i=0;i<snp.size()-1;i++)
        {
           for(int j=i+1;j<snp.size();j++)
           {    
            
            System.out.print("SNP"+(i+1)+","+(j+1)+ "  ");
            AlelosConjuntosSNP aleconj= new AlelosConjuntosSNP(snp.get(i).frecuenciasAlelicasControlesAleloMayorFr(),snp.get(j).frecuenciasAlelicasControlesAleloMayorFr(),snp.get(i).frecuenciasAlelicasControlesAleloMayorFr(),snp.get(j).frecuenciasAlelicasControlesAleloMenorFr(),i,j);
            aleconj.setArregloSNP(snp);
            aleconj.linkageDisequilibrium();
            System.out.println("LD          ="   +   aleconj.LD);
            System.out.println("DMax        =" + aleconj.DMax);
            System.out.println("Person Coef ="+aleconj.Pearsoncoefficientofcorrelation());
            System.out.println(aleconj.AlelosConjuntosChiCuadrado(snp.get(i).ConteoAlelosMayorFrCasos(), snp.get(i).ConteoAlelosMenorFrCasos(), snp.get(j).ConteoAlelosMayorFr(), snp.get(j).ConteoAlelosMenorFrCasos()));
           }
        }
    }
}
