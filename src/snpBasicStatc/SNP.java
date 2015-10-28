/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snpBasicStatc;

import static snpBasicStatc.ChiSquareUtils.pochisq;
import java.util.ArrayList;
import java.util.jar.Attributes;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Ana Lorena Uribe Hurtado
 * Calcular Chi cuadrado para los genotipos en el caso de los modelos
 * http://www.gwaspi.org/?page_id=332
 * 
 */
public class SNP {
    public Gen NA;
    public Gen homocigotoMayorFr;
    public Gen heterocigoto;
    public Gen homocigotoMenorFr;
    public int numIndiv;
 
    private Funciones fun;
    private ArrayList<Gen>[] snp;

    public SNP(Gen dominanteHomo, Gen heterocigoto, Gen recesivoHomo, int numIndiv) {
        this.homocigotoMayorFr = dominanteHomo;
        this.heterocigoto = heterocigoto;
        this.homocigotoMenorFr = recesivoHomo;
        this.numIndiv = numIndiv;
    }
     public SNP()
     {
         
          NA= new Gen();
          heterocigoto = new Gen();
          homocigotoMenorFr = new Gen();
          homocigotoMayorFr = new Gen();
          
     }
     
     
     public int getCantidadPorGenes(){
         return homocigotoMayorFr.getCantidad() + homocigotoMenorFr.getCantidad() + heterocigoto.getCantidad() + NA.getCantidad();
     }

     
    /******************************************************************Conteos Alelos - Menory Mayor Frecuencia Todos/casos/controles*******************************/ 
    
    public int ConteoAlelosMayorFr()
    {
        
       return homocigotoMayorFr.getCantidad()*2+heterocigoto.getCantidad();
    }
    
    public int ConteoAlelosMenorFr()
    {
       
       return homocigotoMenorFr.getCantidad()*2+heterocigoto.getCantidad();
    }
    
    public int ConteoAlelosMayorFrCasos()
    {
        return (homocigotoMayorFr.getCantidadCasoHombre()+homocigotoMayorFr.getCantidadCasoMujer())*2+heterocigoto.getCantidadCasoHombre()+heterocigoto.getCantidadCasoMujer();
    }
    public int ConteoAlelosMenorFrCasos()
    {
        return (homocigotoMenorFr.getCantidadCasoHombre()+homocigotoMenorFr.getCantidadCasoMujer())*2+heterocigoto.getCantidadCasoHombre()+heterocigoto.getCantidadCasoMujer();
    }
    
     public int ConteoAlelosMayorFrControles()
    {
        return ( homocigotoMayorFr.getCantidadControlHombre()+homocigotoMayorFr.getCantidadControlMujer() )*2+heterocigoto.getCantidadControlHombre()+heterocigoto.getCantidadControlMujer();
    }
    public int ConteoAlelosMenorFrControles()
    {
        return (homocigotoMenorFr.getCantidadControlHombre()+homocigotoMenorFr.getCantidadControlMujer())*2+heterocigoto.getCantidadControlHombre()+heterocigoto.getCantidadControlMujer();
    }
    
    /************************************************************************************Totales por Genotipos  Todos / Casos  / Controles ******************************************************/
    
    public int totalCasosporSNP()
    {
        return homocigotoMayorFr.getCantidadCasoHombre()+homocigotoMayorFr.getCantidadCasoMujer()+
                heterocigoto.getCantidadCasoHombre()+heterocigoto.getCantidadCasoMujer()+
                homocigotoMenorFr.getCantidadCasoHombre()+homocigotoMenorFr.getCantidadCasoMujer();
    }
    
    public int totalControlesporSNP()
    {
        return homocigotoMayorFr.getCantidadControlHombre()+homocigotoMayorFr.getCantidadControlMujer()+
                heterocigoto.getCantidadControlHombre()+heterocigoto.getCantidadControlMujer()+
                homocigotoMenorFr.getCantidadControlHombre()+homocigotoMenorFr.getCantidadControlMujer();
    }
    
    public int todosIndividuos()
    {
        return totalCasosporSNP()+totalControlesporSNP();
    }
    
    public double frecuenciasGenotipicasTodosHomMayorFr(){
    
        double resultado=homocigotoMayorFr.getCantidad()/(double)todosIndividuos();
        return resultado;

    }
    public double frecuenciasGenotipicasTodosHeterocigoto(){
        double resultado=heterocigoto.getCantidad()/(double)todosIndividuos();
        return resultado;
        
    }
    public double frecuenciasGenotipicasTodosHomMenorFr(){
        double resultado=homocigotoMenorFr.getCantidad()/(double)todosIndividuos();
        return resultado;
        
    }
    public double frecuenciasGenotipicasHomMayorFrCasos(){
    
        double resultado=(homocigotoMayorFr.getCantidadCasoHombre()+homocigotoMayorFr.getCantidadCasoMujer())/(double)totalCasosporSNP();
        return resultado;

    }
    public double frecuenciasGenotipicasHeterocigotoCasos(){
        double resultado=(heterocigoto.getCantidadCasoHombre()+heterocigoto.getCantidadCasoMujer())/(double)totalCasosporSNP();
        return resultado;

    }
    public double frecuenciasGenotipicasHomMenorFrCasos(){
        double resultado=(homocigotoMenorFr.getCantidadCasoHombre()+homocigotoMenorFr.getCantidadCasoMujer())/(double)totalCasosporSNP();
        return resultado;

    }
    public double frecuenciasGenotipicasHomMayorFrControles(){
    
        double resultado=(homocigotoMayorFr.getCantidadControlHombre()+homocigotoMayorFr.getCantidadControlMujer())/(double)totalControlesporSNP();
        return resultado;

    }
    public double frecuenciasGenotipicasHeterocigotoControles(){
        double resultado=(heterocigoto.getCantidadControlHombre()+heterocigoto.getCantidadControlMujer())/(double)totalControlesporSNP();
        return resultado;

    }
    public double frecuenciasGenotipicasHomMenorFrControles(){
        double resultado=(homocigotoMenorFr.getCantidadControlHombre()+homocigotoMenorFr.getCantidadControlMujer())/(double)totalControlesporSNP();
        return resultado;

    }
    
    
    
    
    /**Frecuancias Alelelicas Todos los Individuos*/
    public double frecuenciasAlelicasTodosAleloMayorFr()
    {
        
        double frecalelicaD= (double)(2*homocigotoMayorFr.getCantidad() + heterocigoto.getCantidad())/(double) (2*todosIndividuos());
        return frecalelicaD;
    }
    
    public double frecuenciasAlelicasTodosAleloMenorFr()
    {
        
        double frecalelicaD= (double)(2* homocigotoMenorFr.getCantidad() + heterocigoto.getCantidad() )  /(double) (2*todosIndividuos());
        return frecalelicaD;
    }
     
    /**Frecuancias Alelicas Casos*/
    public double frecuenciasAlelicasCasosAleloMayorFr()
    {
        
        double frecalelicaD= (double)(2*(homocigotoMayorFr.getCantidadCasoHombre()+homocigotoMayorFr.getCantidadCasoMujer()) + (heterocigoto.getCantidadCasoHombre()+heterocigoto.getCantidadCasoMujer()))/(double) (2*totalCasosporSNP());
        return frecalelicaD;
    
    }
    
    public double frecuenciasAlelicasCasosAleloMenorFr()
    {
       
        double frecalelicaD= (double)(2*(homocigotoMenorFr.getCantidadCasoHombre()+homocigotoMenorFr.getCantidadCasoMujer()) + (heterocigoto.getCantidadCasoHombre()+heterocigoto.getCantidadCasoMujer()))/(double) (2*totalCasosporSNP());
        return frecalelicaD;
    }
    /**Frecuancias Alelicas Controles*/
    public double frecuenciasAlelicasControlesAleloMayorFr()
    {
       
        double frecalelicaD= (double) (2 *     (homocigotoMayorFr.getCantidadControlHombre()+homocigotoMayorFr.getCantidadControlMujer() )   +  (heterocigoto.getCantidadControlHombre()+heterocigoto.getCantidadControlMujer()))/(double) (2*totalControlesporSNP());
        //System.out.println( totalControlesporSNP() );
        return frecalelicaD;
    
    }
    
    public double frecuenciasAlelicasControlesAleloMenorFr()
    {
        
        double frecalelicaD= (double)(2*       (homocigotoMenorFr.getCantidadControlHombre()+homocigotoMenorFr.getCantidadControlMujer()) +    (heterocigoto.getCantidadControlHombre()+heterocigoto.getCantidadControlMujer()))/(double) (2*totalControlesporSNP());
        return frecalelicaD;
    }
    /****************************************************************************** Individuos Esperados **********************************************************************/
   
    public double esperadosTodosAlleloMayorFr()
    {
       double frecalelicaD = frecuenciasAlelicasTodosAleloMayorFr()*frecuenciasAlelicasTodosAleloMayorFr()*todosIndividuos();
       return frecalelicaD;
    }
    public double esperadosTodosAleloMenorFr()
    {
       double frecalelicaD = frecuenciasAlelicasTodosAleloMenorFr()*frecuenciasAlelicasTodosAleloMenorFr()*todosIndividuos();
       return frecalelicaD;
       
    }
    public double esperadosTodosHetero()
    {
       double frecalelicaD = 2*frecuenciasAlelicasTodosAleloMayorFr()*frecuenciasAlelicasTodosAleloMenorFr()*todosIndividuos();
       return frecalelicaD;
    }
   
    public double esperadosCasosAlleloMayorFr()
    {
       double frecalelicaD = frecuenciasAlelicasCasosAleloMayorFr()*frecuenciasAlelicasCasosAleloMayorFr()*totalCasosporSNP();
       return frecalelicaD;
    }
    public double esperadosCasosAleloMenorFr()
    {
       double frecalelicaD = frecuenciasAlelicasCasosAleloMenorFr()*frecuenciasAlelicasCasosAleloMenorFr()*totalCasosporSNP();
       return frecalelicaD;
       
    }
    public double esperadosCasosHetero()
    {
       double frecalelicaD = 2*frecuenciasAlelicasCasosAleloMayorFr()*frecuenciasAlelicasCasosAleloMenorFr()*totalCasosporSNP();
       return frecalelicaD;
    }
    
    
    
    
    public double esperadosControlesAlleloMayorFr()
    {
       double frecalelicaD = frecuenciasAlelicasControlesAleloMayorFr()*frecuenciasAlelicasControlesAleloMayorFr()*totalControlesporSNP();
       return frecalelicaD;
    }
    public double esperadosControlesAleloMenorFr()
    {
       double frecalelicaD = frecuenciasAlelicasControlesAleloMenorFr()*frecuenciasAlelicasControlesAleloMenorFr()*totalControlesporSNP();
       return frecalelicaD;
       
    }
    public double esperadosControlesHetero()
    {
        double frecalelicaD = 2*frecuenciasAlelicasControlesAleloMayorFr()*frecuenciasAlelicasControlesAleloMenorFr()*totalControlesporSNP();
       return frecalelicaD;
    }
    
    
    
    /**********************************************************************Ji Cuadrado******************************************************************************/
    public double chiCuadradoMayorFrecuenciaTodos()
    {
        double resultado=Math.pow((homocigotoMayorFr.getCantidad()-esperadosTodosAlleloMayorFr()),2);
        resultado= resultado/esperadosTodosAlleloMayorFr();
        return resultado;
    }
        
    public double chiCuadradoHeterocigotoTodos()
    {
         return Math.pow( (heterocigoto.getCantidad()-esperadosTodosHetero()),2)/esperadosTodosHetero();
    }
    
    public double chiCuadradoMenorFrecuenciaTodos()
    {
         return Math.pow((homocigotoMenorFr.getCantidad()-esperadosTodosAleloMenorFr()),2)/esperadosTodosAleloMenorFr();
    }
    
    public double chiCuadradoMayorFrecuenciaCasos()
    {
        double resultado=Math.pow((homocigotoMayorFr.totalCasos()-esperadosCasosAlleloMayorFr()),2);
        resultado= resultado/esperadosCasosAlleloMayorFr();
        return resultado;
    }
        
    public double chiCuadradoHeterocigotoCasos()
    {
         return Math.pow( (heterocigoto.totalCasos()-esperadosCasosHetero()),2)/esperadosCasosHetero();
    }
    
    public double chiCuadradoMenorFrecuenciaCasos()
    {
         return Math.pow((homocigotoMenorFr.totalCasos()-esperadosCasosAleloMenorFr()),2)/esperadosCasosAleloMenorFr();
    }
  
    
    
    
    
    public double chiCuadradoMayorFrecuenciaControles()
    {
        double resultado=Math.pow((homocigotoMayorFr.totalControles()-esperadosControlesAlleloMayorFr()),2);
        resultado= resultado/esperadosControlesAlleloMayorFr();
        return resultado;
    }
        
    public double chiCuadradoHeterocigotoControles()
    {
         return Math.pow( (heterocigoto.totalControles()-esperadosControlesHetero()),2)/esperadosControlesHetero();
    }
    
    public double chiCuadradoMenorFrecuenciaControles()
    {
         return Math.pow((homocigotoMenorFr.totalControles()-esperadosControlesAleloMenorFr()),2)/esperadosControlesAleloMenorFr();
    }
    
    
      
    public double sumachiCuadradoTodosSignificancia()
    {
        return chiCuadradoMayorFrecuenciaTodos()+chiCuadradoHeterocigotoTodos()+chiCuadradoMenorFrecuenciaTodos();
    }
    
    public double sumachiCuadradoCasosSignificancia()
    {
        return chiCuadradoMayorFrecuenciaCasos()+chiCuadradoHeterocigotoCasos()+chiCuadradoMenorFrecuenciaCasos();
    }
    
    public double sumachiCuadradoControlesSignificancia()
    {
        return chiCuadradoMayorFrecuenciaControles()+chiCuadradoHeterocigotoControles()+chiCuadradoMenorFrecuenciaControles();
    }

    
    
    public double valorSignificanciaTodos()
    {
        double respuesta=sumachiCuadradoTodosSignificancia();
        respuesta= pochisq(respuesta, 1);
        return respuesta;
    }
    
    public double valorSignificanciaCasos()
    {
        return pochisq(sumachiCuadradoCasosSignificancia(), 1);
    }
    public double valorSignificanciaControles()
    {
        
        return  pochisq(sumachiCuadradoControlesSignificancia(), 1);
        
    }
    
    
    
    /**
     * variables de entrada
     * x,y valores enteros = controles 
     * z,w valores enteros =  casos
     */
    public double oddRatio(int x, int y, int z, int w)
    {
        double respuesta =  (double)(x*w)/(double)(y*z);
        return respuesta;
        
    }
    
    public int totalCasosHomoMayor()
    {
        return homocigotoMayorFr.getCantidadCasoHombre()+homocigotoMayorFr.getCantidadCasoMujer();
    }
    public int totalControlesHomMayor()
    {
        return homocigotoMayorFr.getCantidadControlHombre()+homocigotoMayorFr.getCantidadControlMujer();
    }
    
    
    public int totalCasosHeterocigoto()
    {
        return heterocigoto.getCantidadCasoHombre()+heterocigoto.getCantidadCasoMujer();
    }
    
    public int totalControlesHeterocigoto()
    {
        return heterocigoto.getCantidadControlHombre()+heterocigoto.getCantidadControlMujer();
    }
    
    public int totalCasosHomoMenor()
    {
        return  homocigotoMenorFr.getCantidadCasoHombre()+homocigotoMenorFr.getCantidadCasoMujer();
    }
    
    public int totalControlesHomMenor()
    {
        return homocigotoMenorFr.getCantidadControlHombre()+homocigotoMenorFr.getCantidadControlMujer();
    }
    
    public double calculoError(double x, double y, double z, double w)
    {
        double respuesta= (double)(1/x + 1/y + 1/z + 1/w);
        
        return Math.sqrt(respuesta);
    }
     /**
     * Parametro de entrada valorSignificancia para calcular el intervalo de consianza:
     * 90 calcula con  1.645 
     * 91 calcula con  1.69
     * 93 calcula con  1.81
     * 95 calcula con  1.96 
     * 98 calcula con  2.236 
     * 99 calcula con  2.576	
     * retorna el valor Inferior del intervalo de confianza
     * http://www.monografias.com/trabajos60/tamano-muestra-archivistica/tamano-muestra-archivistica2.shtml
     */
    
    public double intervaloConfianzaInf(double ratio, double err, int valorSignificancia)
    {
        double respuesta=0;
        float constante=0;
        switch(valorSignificancia)
        {
            case 90:
            {
                constante=(float)1.645;
                
                break;
            }
            case 91:
            {
                constante=(float)(1.69);
                break;
            }
            case 93:
            {
                constante=(float)(1.81);
                break;
            }
            case 95:
            {
                constante=(float)1.96;
                break;
            }
            
            case 98:
            {
                constante=(float)2.236;
                break;
            }
            case 99:
            {
                constante=(float)2.576;
                break;
            }
            default:
            {
                JOptionPane.showMessageDialog(null, "Opcion No Válida");
                break;
            }
            
        }
        double loga= Math.log(ratio);
        respuesta= loga-(constante*err);
        return Math.exp(respuesta);
    }  
    
     /**
     * Parametro de entrada valorSignificancia para calcular el intervalo de consianza:
     * 90 calcula con  1.645 
     * 91 calcula con  1.69
     * 93 calcula con  1.81
     * 95 calcula con  1.96 
     * 98 calcula con  2.236 
     * 99 calcula con  2.576	
     * retorna el valor Superior del intervalo de confianza
     */
    public double intervaloConfianzaSup(double ratio, double err, int valorSignificancia)
    {
         double respuesta=0;
        float constante=0;
        switch(valorSignificancia)
        {
            case 90:
            {
                constante=(float)1.645;
                
                break;
            }
            case 91:
            {
                constante=(float)(1.69);
                break;
            }
            case 93:
            {
                constante=(float)(1.81);
                break;
            }
            case 95:
            {
                constante=(float)1.96;
                break;
            }
            
            case 98:
            {
                constante=(float)2.236;
                break;
            }
            case 99:
            {
                constante=(float)2.576;
                break;
            }
            default:
            {
                JOptionPane.showMessageDialog(null, "Opcion No Válida");
                break;
            }
            
        }
        double loga= Math.log(ratio);
        respuesta= loga+(constante*err);
        return Math.exp(respuesta);
    }
    /***********************************************  MODELOS ******************************************************************************/
    /***********************************************  CODOMINANTE Alelo Mayor Frecuencia Heterocigoto ******************************************************************************/
    
    public double modeloCodominanteOddRatioHomoMayoHet()
    {
        
        double respuesta = (double)oddRatio(totalControlesHomMayor(),totalControlesHeterocigoto(),totalCasosHomoMayor(),totalCasosHeterocigoto());
        return respuesta;
    }
    
        
    public double errorCodominateAllelosHomoMayorHetFr()
    {
        return calculoError(totalControlesHomMayor(),totalControlesHeterocigoto(),totalCasosHomoMayor(),totalCasosHeterocigoto());
    }
    
    public double intervaloConfianzaCondominanteHomoMayorHetFrSup()
    {
        double respuesta= intervaloConfianzaSup(modeloCodominanteOddRatioHomoMayoHet(),errorCodominateAllelosHomoMayorHetFr(),95);
        return respuesta;
    }
    
     public double intervaloConfianzaCondominanteHomoMayorHetFrInf()
    {
        double respuesta= intervaloConfianzaInf(modeloCodominanteOddRatioHomoMayoHet(),errorCodominateAllelosHomoMayorHetFr(),95);
        return respuesta;
    }
    
     
     /***********************************************  CODOMINANTE Alelo Mayor Frecuencia Homocigoto, Alelo Menor Frecuencia Homocigoto ******************************************************************************/
    public double modeloCodominanteOddRatioHomoMayoHomoMenor()
    {
        
        double respuesta = (double)oddRatio(totalControlesHomMayor(), totalControlesHomMenor(),totalCasosHomoMayor(),totalCasosHomoMenor());
        return respuesta;
    }  
     
    public double errorCodominateAllelosHomoMayoMenorFr()
    {
        return calculoError(totalControlesHomMayor(), totalControlesHomMenor(),totalCasosHomoMayor(),totalCasosHomoMenor());
    }
    
    public double intervaloConfianzaCodominateHomMayorHomMenorInf()
    {
        double respuesta= intervaloConfianzaInf( modeloCodominanteOddRatioHomoMayoHomoMenor(), errorCodominateAllelosHomoMayoMenorFr(), 95);
        return respuesta;
    }
    
  public double intervaloConfianzaCodominateHomMayorHomMenorSup()
  {
        double respuesta= intervaloConfianzaSup( modeloCodominanteOddRatioHomoMayoHomoMenor(), errorCodominateAllelosHomoMayoMenorFr(), 95);
        return respuesta;
  }
    
    
    /********************************************************************************************************************************/
    public double modeloDominanteOddRatio()
    {
        int sumarCasosHetRec= totalCasosHeterocigoto()+totalCasosHomoMenor();
        int sumarControHetRec=totalControlesHeterocigoto()+totalControlesHomMenor();
        double respuesta = (double)oddRatio(totalControlesHomMayor(), sumarControHetRec,totalCasosHomoMayor(),sumarCasosHetRec);
        return respuesta;
    }  
    
    public double errorMayorFrecuencia()
    {
        int sumarCasosHetRec= totalCasosHeterocigoto()+totalCasosHomoMenor();
        int sumarControHetRec=totalControlesHeterocigoto()+totalControlesHomMenor();
        double respuesta = calculoError(totalControlesHomMayor(), sumarControHetRec,totalCasosHomoMayor(),sumarCasosHetRec);
        return respuesta;
    }
    
    
  
    public double modeloDominanteICInf()
    {
       double respuesta= intervaloConfianzaInf(modeloDominanteOddRatio(), errorMayorFrecuencia(), 95);
        
        return (respuesta);
    }
     public double modeloDominanteICSup()
    {
       double respuesta= intervaloConfianzaSup(modeloDominanteOddRatio(), errorMayorFrecuencia(), 95);
        
        return (respuesta);
    }
    
    
    
    /******************************************************RECESIVO*****************************************************************/
                
    public double modeloRecesivoOddsRatio()
    {
        int sumarCasosHetDom=totalCasosHeterocigoto()+totalCasosHomoMayor();
        int sumarControlesHetDom=totalControlesHeterocigoto()+totalControlesHomMayor();
        double respuesta = (double) oddRatio(sumarControlesHetDom,totalControlesHomMenor(),sumarCasosHetDom,totalCasosHomoMenor());
        return respuesta;
    }
    
    public double errorRecesivo()
    {
        int sumarCasosHetDom=totalCasosHeterocigoto()+totalCasosHomoMayor();
        int sumarControlesHetDom=totalControlesHeterocigoto()+totalControlesHomMayor();
        double respuesta = calculoError(sumarControlesHetDom,totalControlesHomMenor(),sumarCasosHetDom,totalCasosHomoMenor());
        return respuesta;
    }
    
    public double modeloRecesivoICInf()
    {
       double respuesta= intervaloConfianzaInf(modeloRecesivoOddsRatio(), errorRecesivo(), 95);
        
        return (respuesta);
    }
     public double modeloRecesivoICSup()
    {
       double respuesta= intervaloConfianzaSup(modeloRecesivoOddsRatio(), errorRecesivo(), 95);
        
        return (respuesta);
    }
    
    
    /******************************************************SobreDominante*****************************************************************/
    
    public double modeloSobreDominanteOddRatio()
    {
        int sumarCasosDomRec=totalCasosHomoMenor()+totalCasosHomoMayor();
        int sumarControlesDomRec=totalControlesHomMenor()+totalControlesHomMayor();
        double respuesta = (double) oddRatio(sumarControlesDomRec,totalControlesHeterocigoto(),sumarCasosDomRec,totalCasosHeterocigoto());
        return respuesta;
       
    }  
    public double errorSobreDominante()
    {
         int sumarCasosDomRec=totalCasosHomoMenor()+totalCasosHomoMayor();
        int sumarControlesDomRec=totalControlesHomMenor()+totalControlesHomMayor();
        double respuesta = calculoError(sumarControlesDomRec,totalControlesHeterocigoto(),sumarCasosDomRec,totalCasosHeterocigoto());
        return respuesta;
    }
     public double modeloSobredominanteICInf()
    {
       double respuesta= intervaloConfianzaInf(modeloSobreDominanteOddRatio(), errorSobreDominante(), 95);
        
        return (respuesta);
    }
     public double modeloSobredominanteICSup()
    {
       double respuesta= intervaloConfianzaSup(modeloSobreDominanteOddRatio(), errorSobreDominante(), 95);
        
        return (respuesta);
    }
    
    /******************************************************ADITIVO*****************************************************************/
    public double modeloAditivoOddsRatio()
    {
        int sumarCasosHetDom=totalCasosHeterocigoto()+2*totalCasosHomoMayor();
        int sumarControlesHetDom=totalControlesHeterocigoto()+2*totalControlesHomMayor();
        
        
        
        double respuesta = (double) oddRatio(sumarControlesHetDom, totalControlesHomMenor(),sumarCasosHetDom, totalCasosHomoMenor());
        return respuesta;
    }
    
    public double errorAditivo()
    {
       int sumarCasosHetDom=totalCasosHeterocigoto()+2*totalCasosHomoMayor();
        int sumarControlesHetDom=totalControlesHeterocigoto()+2*totalControlesHomMayor();
        
        double respuesta = calculoError(sumarControlesHetDom, totalControlesHomMenor(),sumarCasosHetDom, totalCasosHomoMenor());
        return respuesta;
    }
     public double modeloAditivoICInf()
    {
       double respuesta= intervaloConfianzaInf(modeloAditivoOddsRatio(), errorAditivo(), 95);
        
        return (respuesta);
    }
     public double modeloAditivoICSup()
    {
       double respuesta= intervaloConfianzaSup(modeloAditivoOddsRatio(), errorAditivo(), 95);
        
        return (respuesta);
    }
   
    /************************************************Calculos Ligados al Sexo - Mujeres***********************************************************************/
    public double oddRatioMujeresHomoMayorHet()
    {
      
        return oddRatio(homocigotoMayorFr.getCantidadControlMujer(), heterocigoto.getCantidadControlMujer(), homocigotoMayorFr.getCantidadCasoMujer(), heterocigoto.getCantidadCasoMujer());
    
    }
    
    public double errorMujeresAlelosMayorFrHet()
    {
        return calculoError(homocigotoMayorFr.getCantidadControlMujer(), heterocigoto.getCantidadControlMujer(), homocigotoMayorFr.getCantidadCasoMujer(), heterocigoto.getCantidadCasoMujer());
    }
    
    public double intervaloConfianzaMujeresHomoMayorHetInf()
    {
        return intervaloConfianzaInf(oddRatioMujeresHomoMayorHet(),errorMujeresAlelosMayorFrHet(),95);
    }
   
    public double intervaloConfianzaMujeresHomoMayorHetSup()
    {
        return intervaloConfianzaSup(oddRatioMujeresHomoMayorHet(),errorMujeresAlelosMayorFrHet(),95);
    }
    
     
    public double oddRatioMujeresHomoMayorHomoMenor()
    {
        return oddRatio(homocigotoMayorFr.getCantidadControlMujer(), homocigotoMenorFr.getCantidadControlMujer(), homocigotoMayorFr.getCantidadCasoMujer(), homocigotoMenorFr.getCantidadCasoMujer());
    
    }
    
    public double errorMujeresAlelosHomMayorMenorFr()
    {
        return calculoError(homocigotoMayorFr.getCantidadControlMujer(), homocigotoMenorFr.getCantidadControlMujer(), homocigotoMayorFr.getCantidadCasoMujer(), homocigotoMenorFr.getCantidadCasoMujer());
    }
    
     public double intervaloConfianzaMujeresHomMayorHomoMenorInf()
    {
        return intervaloConfianzaInf(oddRatioMujeresHomoMayorHomoMenor(),errorMujeresAlelosHomMayorMenorFr(),95);
    }
   
    public double intervaloConfianzaMujeresHomoMayorHomoMenorFrSup()
    {
        return intervaloConfianzaSup(oddRatioMujeresHomoMayorHomoMenor(),errorMujeresAlelosHomMayorMenorFr(),95);
    }
    
    
    /************************************************Calculos Ligados al Sexo - Hombres***********************************************************************/
    public double oddRatioHombresHomoMayorHet()
    {
        return oddRatio(homocigotoMayorFr.getCantidadControlHombre(), heterocigoto.getCantidadControlHombre(), homocigotoMayorFr.getCantidadCasoHombre(), heterocigoto.getCantidadCasoHombre());
    }
    
     public double errorHombresAlelosMayorFrHet()
    {
        return calculoError(homocigotoMayorFr.getCantidadControlHombre(), heterocigoto.getCantidadControlHombre(), homocigotoMayorFr.getCantidadCasoHombre(), heterocigoto.getCantidadCasoHombre());
    }
    
    public double intervaloConfianzaHombresHomoMayorHetInf()
    {
        return intervaloConfianzaInf(oddRatioHombresHomoMayorHet(),errorHombresAlelosMayorFrHet(),95);
    }
   
    public double intervaloConfianzaHombresHomoMayorHetSup()
    {
        return intervaloConfianzaSup(oddRatioHombresHomoMayorHet(),errorHombresAlelosMayorFrHet(),95);
    }
    
     
    public double oddRatioHombresHomoMayorHomoMenor()
    {
        return oddRatio(homocigotoMayorFr.getCantidadControlHombre(), homocigotoMenorFr.getCantidadControlHombre(), homocigotoMayorFr.getCantidadCasoHombre(), homocigotoMenorFr.getCantidadCasoHombre());
    
    }
    
    public double errorHombresAlelosHomMayorMenorFr()
    {
        return calculoError(homocigotoMayorFr.getCantidadControlHombre(), homocigotoMenorFr.getCantidadControlHombre(), homocigotoMayorFr.getCantidadCasoHombre(), homocigotoMenorFr.getCantidadCasoHombre());
    }
    
     public double intervaloConfianzaHombresHomMayorHomoMenorInf()
    {
        return intervaloConfianzaInf(oddRatioHombresHomoMayorHomoMenor(),errorHombresAlelosHomMayorMenorFr(),95);
    }
   
    public double intervaloConfianzaHombresHomoMayorHomoMenorFrSup()
    {
        return intervaloConfianzaSup(oddRatioHombresHomoMayorHomoMenor(),errorHombresAlelosHomMayorMenorFr(),95);
    }
    
    
    /*************************************************Realcionados Hombres Vs Mujeres *******************************************************************/
    public double oddRatioHombreMujeresHomoMayorFR()
    {
        return oddRatio(homocigotoMayorFr.getCantidadControlMujer(), homocigotoMayorFr.getCantidadControlHombre(), homocigotoMayorFr.getCantidadCasoMujer(), homocigotoMayorFr.getCantidadCasoHombre());
    }
    
    public double errorHombreMujeresHomoMayorFR()
    {
        return calculoError(homocigotoMayorFr.getCantidadControlMujer(), homocigotoMayorFr.getCantidadControlHombre(), homocigotoMayorFr.getCantidadCasoMujer(), homocigotoMayorFr.getCantidadCasoHombre());
    }
    public double intervaloConfianzaHombreMujeresHomoMayorFRInf()
    {
        return intervaloConfianzaInf(oddRatioHombreMujeresHomoMayorFR(), errorHombreMujeresHomoMayorFR(), 93);
        
    }     
    public double intervaloConfianzaHombreMujeresHomoMayorFRSup()
    {
        return intervaloConfianzaSup(oddRatioHombreMujeresHomoMayorFR(), errorHombreMujeresHomoMayorFR(), 93);
        
    }
    /*****************************************************************************************************************************************************/
    public double oddRatioHombreMujeresHetero()
    {
        return oddRatio(heterocigoto.getCantidadControlMujer(), heterocigoto.getCantidadControlHombre(), heterocigoto.getCantidadCasoMujer(), heterocigoto.getCantidadCasoHombre());
    }      
    public double errorHombreMujeresHetero()
    {
        return calculoError(heterocigoto.getCantidadControlMujer(), heterocigoto.getCantidadControlHombre(), heterocigoto.getCantidadCasoMujer(), heterocigoto.getCantidadCasoHombre());
        
    }
     
    public double intervaloConfianzaHombreMujeresHeteroInf()
    {
        return intervaloConfianzaInf(oddRatioHombreMujeresHetero(), errorHombreMujeresHetero(), 93);
    }
    
    public double intervaloConfianzaHombreMujeresHeteroSup()
    {
        return intervaloConfianzaSup(oddRatioHombreMujeresHetero(), errorHombreMujeresHetero(), 93);
    }
    
    /*****************************************************************************************************************************************************/
    public double oddRatioHombreMujerHomoMenorFr()
    {
        return oddRatio(homocigotoMenorFr.getCantidadControlMujer(), homocigotoMenorFr.getCantidadControlHombre(), homocigotoMenorFr.getCantidadCasoMujer(), homocigotoMenorFr.getCantidadCasoHombre());
    }
     public double errorHombreMujeresHomoMenorFr()
     {
         return calculoError(homocigotoMenorFr.getCantidadControlMujer(), homocigotoMenorFr.getCantidadControlHombre(), homocigotoMenorFr.getCantidadCasoMujer(), homocigotoMenorFr.getCantidadCasoHombre());
     }
     public double intervaloConfianzaHombreMujeresMenorFrInf()
     {
          return intervaloConfianzaInf(oddRatioHombreMujerHomoMenorFr(), errorHombreMujeresHomoMenorFr(), 93);
     }
     public double intervaloConfianzaHombreMujeresMenorFrSup()
     {
         return intervaloConfianzaSup(oddRatioHombreMujerHomoMenorFr(), errorHombreMujeresHomoMenorFr(), 93);
     }

    @Override
    public String toString() {
        return " SNP{" + "NA=" + NA.getTipo() + ", homocigotoMayorFr=" + homocigotoMayorFr.getTipo() + ", heterocigoto=" + heterocigoto.getTipo() + ", homocigotoMenorFr=" + homocigotoMenorFr.getTipo() + '\n'+ 
              "NA= CAH  "+ NA.getCantidadCasoHombre()+" CAM  "+NA.getCantidadCasoMujer()+" COH "+NA.getCantidadControlHombre()+" COM "+NA.getCantidadControlMujer() + "----TOTAL: " + NA.getCantidad() + '\n'+
              "homocigotoMayorFr= CAH "+ homocigotoMayorFr.getCantidadCasoHombre()+" CAM "+homocigotoMayorFr.getCantidadCasoMujer()+" COH "+homocigotoMayorFr.getCantidadControlHombre()+" COM "+homocigotoMayorFr.getCantidadControlMujer()+ "----TOTAL: " + homocigotoMayorFr.getCantidad() + '\n'+ 
              "homocigotoMenorFr= CAH "+ homocigotoMenorFr.getCantidadCasoHombre()+" CAM "+homocigotoMenorFr.getCantidadCasoMujer()+" COH "+homocigotoMenorFr.getCantidadControlHombre()+" COM "+homocigotoMenorFr.getCantidadControlMujer()+ "----TOTAL: " + homocigotoMenorFr.getCantidad() + '\n'+ 
              "Heterocigoto= CAH "+heterocigoto.getCantidadCasoHombre()+" CAM "+heterocigoto.getCantidadCasoMujer()+" COH "+heterocigoto.getCantidadControlHombre()+" COM "+heterocigoto.getCantidadControlMujer()+ "----TOTAL: " + heterocigoto.getCantidad() + '\n' + 
              "TOTAL GENES: " + getCantidadPorGenes() + '}' ;        
    }
     
     
     
}
