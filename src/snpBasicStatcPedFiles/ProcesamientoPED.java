/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snpBasicStatcPedFiles;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import snpBasicStatc.SNP;

/**
 *
 * @author Loena
 */
public class ProcesamientoPED extends Thread{
    
    
    //Constantes para definir las opciones de conteoFrecuenciasAlelicas
    private static final int FRECUENCIAS_ALELICAS_TODOS = 0;
    private static final int FRECUENCIAS_ALELICAS_CASOS = 1;
    private static final int FRECUENCIAS_ALELICAS_CONTROLES = 2;
    
    
    //Constantes para definir las opciones de conteoFrecuenciasGenotipicas
    private static final int FRECUENCIAS_GENOTIPICAS_TODOS = 0;
    private static final int FRECUENCIAS_GENOTIPICAS_CASOS = 1;
    private static final int FRECUENCIAS_GENOTIPICAS_CONTROLES = 2;
    
    
        
    
    
      public static  ArrayList<SNP> snp;
      private int inicioP;
      private int finalP;
      
      private File archivoSalida;
      private FileWriter writer;
      
      private JSONArray snpJsonArray;
      
      
      
      public ProcesamientoPED(ArrayList<SNP> snp, int inicioP, int finalP, File archivoSalida, JSONArray snpJsonArray) 
      {
          this.snp = snp;
          this.inicioP=inicioP;
          this.finalP=finalP;
          this.archivoSalida = archivoSalida;
          
          this.snpJsonArray = snpJsonArray;
      }
      
      
      private synchronized void escribirResultado( String cadena ){
          FileWriter writer = null;
          try {
              writer = new FileWriter( this.archivoSalida, true );
              writer.append( cadena );
          } 
          catch (IOException ex) {
              ex.printStackTrace();
          } 
          finally {
              try {
                  writer.close();
              } 
              catch (IOException ex) {
                 ex.printStackTrace();
              }
          }
      }
      
      
      
      public synchronized void conteoFrecuenciasGenotipicas( int numhilo, int opcion, JSONObject snpJsonObject, 
            SNP snpRef, int[][] opcionesTotalesFrecGen, double[][] opcionesFrecuenciasFrecGen ){
          
        boolean flagHomoMay = snpRef.homocigotoMayorFr.getTipo() != null;
        boolean flagHomoMen = snpRef.homocigotoMenorFr.getTipo() != null;
        boolean flagHetero  = snpRef.heterocigoto.getTipo()      != null;
        boolean flagNA  =     snpRef.NA.getTipo()                != null;
        
        
        String opcionCalculoFrecuencias = "";
        switch (opcion){
            case FRECUENCIAS_GENOTIPICAS_TODOS:
                opcionCalculoFrecuencias = "Todos";
                break;
            case FRECUENCIAS_GENOTIPICAS_CASOS:
                opcionCalculoFrecuencias = "Casos";
                break;
            case FRECUENCIAS_GENOTIPICAS_CONTROLES:
                opcionCalculoFrecuencias = "Controles";
                break;
            default:
                break;
        }
        
        String[] genotipos = new String[4];
        int[] totales = new int[4];
        double[] frecuencias = new double[4];
        
        if ( flagHomoMay  &&  flagHomoMen  &&  flagHetero  &&  flagNA ){
            genotipos[0] = snpRef.homocigotoMayorFr.getTipo();  genotipos[1] = snpRef.heterocigoto.getTipo();  genotipos[2] = snpRef.homocigotoMenorFr.getTipo();  genotipos[3] = snpRef.NA.getTipo();
            totales[0] = opcionesTotalesFrecGen[0][opcion];  totales[1] = opcionesTotalesFrecGen[1][opcion];  totales[2] = opcionesTotalesFrecGen[2][opcion];  totales[3] = opcionesTotalesFrecGen[3][opcion];  
            frecuencias[0] = opcionesFrecuenciasFrecGen[0][opcion];  frecuencias[1] = opcionesFrecuenciasFrecGen[1][opcion];  frecuencias[2] = opcionesFrecuenciasFrecGen[2][opcion];  frecuencias[3] = opcionesFrecuenciasFrecGen[3][opcion];
        }
        else if ( flagHomoMay  &&  flagHomoMen ){
        }
        
        
    
    }
      
      
      
      public synchronized void conteoFrecuenciasAlelicas( int numhilo, int opcion, JSONObject snpJsonObject, 
            SNP snpRef, int[][] opcionesTotales, double[][] opcionesFrecuencias ){      
        
        boolean flagHomoMay = snpRef.homocigotoMayorFr.getTipo() != null;
        boolean flagHomoMen = snpRef.homocigotoMenorFr.getTipo() != null;
        boolean flagHetero  = snpRef.heterocigoto.getTipo()      != null;
        
        String alelo1 = "", alelo2 = "";        
        int total1 = 0, total2 = 0;
        double frecuencias1 = 0.0, frecuencias2 = 0.0;
        
        String opcionCalculoFrecuencias = "";
        switch (opcion){
            case FRECUENCIAS_ALELICAS_TODOS:
                opcionCalculoFrecuencias = "Todos";
                break;
            case FRECUENCIAS_ALELICAS_CASOS:
                opcionCalculoFrecuencias = "Casos";
                break;
            case FRECUENCIAS_ALELICAS_CONTROLES:
                opcionCalculoFrecuencias = "Controles";
                break;
            default:
                break;
        }       
        /*
        String cadena = "--------------------SNP "+(numhilo+1)+" Frecuencias Alelicas " + opcionCalculoFrecuencias + "--------------------\n";
        cadena += "Alelo \tTotal \tFrecuencias\n";
        */    
        
        if ( flagHomoMay  &&  flagHomoMen  &&  flagHetero ){
            alelo1 = snpRef.homocigotoMayorFr.getTipo().substring(0,1);
            alelo2 = snpRef.heterocigoto.getTipo().substring(0,1);
            total1 = opcionesTotales[0][opcion];            //snpRef.ConteoAlelosMayorFr();
            total2 = opcionesTotales[1][opcion];            //snpRef.ConteoAlelosMenorFr();
            frecuencias1 = opcionesFrecuencias[0][opcion];  //snpRef.frecuenciasAlelicasTodosAleloMayorFr();
            frecuencias2 = opcionesFrecuencias[1][opcion];  // snpRef.frecuenciasAlelicasTodosAleloMenorFr();
        }
        else if ( flagHomoMay  &&  flagHomoMen ){
            alelo1 = snpRef.homocigotoMayorFr.getTipo().substring(0,1);
            alelo2 = snpRef.homocigotoMenorFr.getTipo().substring(0,1);
            total1 = opcionesTotales[0][opcion];            //snpRef.ConteoAlelosMayorFr();
            total2 = opcionesTotales[1][opcion];            //snpRef.ConteoAlelosMenorFr();
            frecuencias1 = opcionesFrecuencias[0][opcion];  //snpRef.frecuenciasAlelicasTodosAleloMayorFr();
            frecuencias2 = opcionesFrecuencias[1][opcion];  // snpRef.frecuenciasAlelicasTodosAleloMenorFr();
        }
        else if ( flagHomoMay  &&  flagHetero ){
            alelo1 = snpRef.homocigotoMayorFr.getTipo().substring(0,1);                    
            total1 = opcionesTotales[0][opcion];            //snpRef.ConteoAlelosMayorFr();                    
            frecuencias1 = opcionesFrecuencias[0][opcion];  //snpRef.frecuenciasAlelicasTodosAleloMayorFr();
            alelo2 = snpRef.heterocigoto.getTipo().substring(0,1);
            if ( alelo1.equals( alelo2 ) ){
                alelo2 = snpRef.heterocigoto.getTipo().substring(2);
                total2 = opcionesTotales[1][opcion];            //snpRef.ConteoAlelosMenorFr();
                frecuencias2 = opcionesFrecuencias[1][opcion]; //snpRef.frecuenciasAlelicasTodosAleloMenorFr();
            }
            else{
                //alelo2 el mismo substring(0, 1)
                total2 = opcionesTotales[1][opcion];       //snpRef.ConteoAlelosMenorFr();
                frecuencias2 = opcionesFrecuencias[1][opcion]; //snpRef.frecuenciasAlelicasTodosAleloMenorFr();
            }        
        }
        else if ( flagHomoMen  &&  flagHetero ){
            alelo1 = snpRef.heterocigoto.getTipo().substring(0,1);
            total1 = opcionesTotales[1][opcion];           //snpRef.ConteoAlelosMenorFr();
            frecuencias1 = opcionesFrecuencias[1][opcion]; //snpRef.frecuenciasAlelicasTodosAleloMenorFr();
            alelo2 = snpRef.homocigotoMenorFr.getTipo().substring(0, 1);
            if ( alelo2.equals(alelo1) ){
                alelo2 = snpRef.heterocigoto.getTipo().substring(2);
                total2 = opcionesTotales[1][opcion];           //snpRef.ConteoAlelosMenorFr();
                frecuencias2 = opcionesFrecuencias[1][opcion]; //snpRef.frecuenciasAlelicasTodosAleloMenorFr();
            }
            else{
                alelo2 = snpRef.heterocigoto.getTipo().substring(0, 1);
                total2 = opcionesTotales[1][opcion];            //snpRef.ConteoAlelosMenorFr();
                frecuencias2 = opcionesFrecuencias[1][opcion]; //snpRef.frecuenciasAlelicasTodosAleloMenorFr();                                
            }              
        }
        /*
        cadena += alelo1 + " \t" + total1 + " \t" + frecuencias1 + "\n";
        cadena += alelo2 + " \t" + total2 + " \t" + frecuencias2 + "\n";            
        */
        
        
        
        String[] arregloAlelos = new String[]{ alelo1, alelo2 };
        int[] arregloTotales = new int[]{ total1, total2 };
        double[] arregloFrecuencias = new double[]{ frecuencias1, frecuencias2 };        
        //Se crea el JSONObject con el resultado del Cálculo del método
        //Este JSONObject se agrega al JSONArray con llave "Prop" dentro del snpJSONArray
        // {
        //     Tipo: FrecuenciasAlelicasCasos
        //     
        JSONObject frecAlelicasJsonObject = new JSONObject();
        try{
            frecAlelicasJsonObject.put( "Tipo", "FrecuenciasAlelicas" + opcionCalculoFrecuencias );
            frecAlelicasJsonObject.put( "Alelo", new JSONArray(arregloAlelos) );
            frecAlelicasJsonObject.put( "Total", new JSONArray(arregloTotales) );
            frecAlelicasJsonObject.put( "Frec", new JSONArray(arregloFrecuencias) );
            
            //Se agregan los cálculos de Frecuencias Alélicas al JSONArray que tiene el snpJsonObject
            snpJsonObject.getJSONArray("SNP" + numhilo).put( frecAlelicasJsonObject );
        }
        catch (org.json.JSONException error){
            error.printStackTrace();
        }
        arregloAlelos = null;
        arregloTotales =null;
        arregloFrecuencias = null;
        

        //System.out.println( cadena );
        //escribirResultado( cadena );
    }
    
    
    
    
    
    
    
    public synchronized void frecuenciasGenotipicas( int numhilo, JSONObject snpJsonObject )
    {
        SNP snpRef = snp.get(numhilo);
        
        //Como mínimo deben haber 2 que sean != a nulos para ejecutar las frecuencias alélicas
        boolean flagHomoMay = snpRef.homocigotoMayorFr.getTipo() != null;
        boolean flagHomoMen = snpRef.homocigotoMenorFr.getTipo() != null;
        boolean flagHetero  = snpRef.heterocigoto.getTipo() != null;
        
        String cadena = "----------------------------------------Todos  los  Sujetos: SNP_"+(numhilo+1)+" Frecuencias Genotipicas---------------------------------------- \n";
        cadena += "Genotipo    Total    Frecuencias \n";
        
        if ( flagHomoMay  &&  flagHomoMen  &&  flagHetero ){
            cadena += snpRef.homocigotoMayorFr.getTipo().substring(0,1) + "    " + snpRef.homocigotoMayorFr.getCantidad() + "    " + snpRef.frecuenciasGenotipicasTodosHomMayorFr() + "\n";
            cadena += snpRef.heterocigoto.getTipo().substring(0,1)+ "    " + snpRef.heterocigoto.getCantidad() + "    " + snpRef.frecuenciasGenotipicasTodosHeterocigoto() + "\n";
        }
        else if ( flagHomoMay  &&  flagHomoMen ){
            cadena += snpRef.homocigotoMayorFr.getTipo().substring(0,1) + "    " + snpRef.homocigotoMayorFr.getCantidad() + "    " + snpRef.frecuenciasGenotipicasTodosHomMayorFr() + "\n";
            cadena += snpRef.homocigotoMenorFr.getTipo().substring(0,1) + "    " + snpRef.homocigotoMenorFr.getCantidad()+ "    " + snpRef.frecuenciasGenotipicasTodosHomMenorFr() + "\n";
        }
        else if ( flagHomoMay  && flagHetero ){
            cadena += snpRef.homocigotoMayorFr.getTipo().substring(0,1) + "    " + snpRef.homocigotoMayorFr.getCantidad() + "    " + snpRef.frecuenciasGenotipicasTodosHomMayorFr() + "\n";
            if ( snpRef.homocigotoMayorFr.getTipo().substring(0,1).equals( snpRef.heterocigoto.getTipo().substring(0,1)  ) ){
                cadena += snpRef.heterocigoto.getTipo().substring(2) +  "    " + snpRef.heterocigoto.getCantidad() + "    " + snpRef.frecuenciasGenotipicasTodosHeterocigoto() + "\n";
            }
            else{
                cadena += snpRef.heterocigoto.getTipo().substring(0, 1)+ "         "+snpRef.ConteoAlelosMenorFr() +"    "+snpRef.frecuenciasGenotipicasTodosHeterocigoto() + "\n";
            }
        }
        else if ( flagHomoMen  &&  flagHetero ){
            if ( snpRef.homocigotoMenorFr.getTipo().substring(0,1).equals( snpRef.heterocigoto.getTipo().substring(0,1)  ) ){
                cadena += snpRef.heterocigoto.getTipo().substring(2) +  "    " + snpRef.heterocigoto.getCantidad() + "    " + snpRef.frecuenciasGenotipicasTodosHeterocigoto() + "\n";
            }
            else{
                cadena += snpRef.heterocigoto.getTipo().substring(0, 1)+"    "+snpRef.ConteoAlelosMenorFr() +"    "+snpRef.frecuenciasGenotipicasTodosHeterocigoto() + "\n";
            }             
        }     
        //System.out.println( cadena );
        escribirResultado( cadena );
    }  
     
          

    

    /*
    synchronized public void frecuenciasAlelicas(int numhilo, String opcion)
    {        
        SNP snpRef = snp.get(numhilo);
        
        //Como mínimo deben haber 2 que sean != a nulos para ejecutar las frecuencias alélicas
        boolean flagHomoMay = snpRef.homocigotoMayorFr.getTipo() != null;
        boolean flagHomoMen = snpRef.homocigotoMenorFr.getTipo() != null;
        boolean flagHetero  = snpRef.heterocigoto.getTipo() != null;
        
        //String cadena = null;
        String cadena = "----------------------------------------SNP_"+(numhilo+1)+" Frecuencias Alelicas---------------------------------------------------------------------\n";
        cadena += "Alelo \tTotal \tFrecuencias\n";
        
        if ( flagHomoMay  &&  flagHomoMen  &&  flagHetero ){
            cadena += snpRef.homocigotoMayorFr.getTipo().substring(0,1)+" \t"+snpRef.ConteoAlelosMayorFr()+" \t"+snpRef.frecuenciasAlelicasTodosAleloMayorFr()+ "\n";
            cadena += snpRef.heterocigoto.getTipo().substring(0,1)+ " \t"+snpRef.ConteoAlelosMenorFr() +" \t"+snpRef.frecuenciasAlelicasTodosAleloMenorFr() + "\n";             
        }
        else if ( flagHomoMay  &&  flagHomoMen ){
            cadena += snpRef.homocigotoMayorFr.getTipo().substring(0,1)+" \t"+snpRef.ConteoAlelosMayorFr()+" \t"+snpRef.frecuenciasAlelicasTodosAleloMayorFr()+ "\n";
            cadena += snpRef.homocigotoMenorFr.getTipo().substring(0,1)+ "         "+snpRef.ConteoAlelosMenorFr() +"          "+snpRef.frecuenciasAlelicasTodosAleloMenorFr() + "\n";
            
        }
        else if ( flagHomoMay  &&  flagHetero ){
            cadena += snpRef.homocigotoMayorFr.getTipo().substring(0,1)+"         "+snpRef.ConteoAlelosMayorFr()+"          "+snpRef.frecuenciasAlelicasTodosAleloMayorFr()+ "\n";          
            if ( snpRef.homocigotoMayorFr.getTipo().substring(0,1).equals( snpRef.heterocigoto.getTipo().substring(0,1)  ) )                
                cadena += snpRef.heterocigoto.getTipo().substring(2)+ "         "+snpRef.ConteoAlelosMenorFr() +"          "+snpRef.frecuenciasAlelicasTodosAleloMenorFr() + "\n";
            else
                cadena += snpRef.heterocigoto.getTipo().substring(0, 1)+ "         "+snpRef.ConteoAlelosMenorFr() +"          "+snpRef.frecuenciasAlelicasTodosAleloMenorFr() + "\n";           
        }
        else if ( flagHomoMen  &&  flagHetero ){
            cadena += snpRef.heterocigoto.getTipo().substring(0,1)+ "         "+snpRef.ConteoAlelosMenorFr() +"          "+snpRef.frecuenciasAlelicasTodosAleloMenorFr() + "\n";
            if (  snpRef.homocigotoMenorFr.getTipo().substring(0, 1).equals( snpRef.heterocigoto.getTipo().substring(0, 1) )  )
                cadena += snpRef.heterocigoto.getTipo().substring(2)+ "         "+snpRef.ConteoAlelosMenorFr() +"          "+snpRef.frecuenciasAlelicasTodosAleloMenorFr() + "\n";
            else
                cadena += snpRef.heterocigoto.getTipo().substring(0, 1)+ "         "+snpRef.ConteoAlelosMenorFr() +"          "+snpRef.frecuenciasAlelicasTodosAleloMenorFr() + "\n";
            
        }
        //System.out.println(cadena);
        escribirResultado( cadena );
>>>>>>> master
    }
    */
    
    
    
    
    
    
    
    
    @Override
    public void run()
    {          
        for( int i=inicioP; i<finalP; i++ ){
            
            /*
            Cada SNP está en un JSONObject, el cual tiene solamente 1 entrada {  "SNP i": [ JSONArray de JSONObjects ]  }
                {
                    "SNP1": [
                        { frecAlelicasJsonObject },
                        { frecGenotipicasJsonObject },
                        ...
                    ]
                }            
            */            
            JSONObject snpJsonObject = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            try {
                snpJsonObject.put( "SNP" + i, jsonArray );               
            } 
            catch (JSONException ex) {
                Logger.getLogger(ProcesamientoPED.class.getName()).log(Level.SEVERE, null, ex);
            }  
            
            //Se captura el SNP y por cada uno de los SNP se realizan los respectivos cálculos
            SNP snpRef = snp.get(i);      
            
            //Matriz que almacena los seis calculos de conteo de alelos de > y < frecuencia
            //para los diferentes llamados (FRECUENCIAS_ALELICAS TODOS, FRECUENCIAS_ALELICAS_CASOS,
            //FRECUENCIAS_ALELICAS_CONTROLES)
            //En la fila 0 estan los conteos de > Frecuencia
            //En la fila 1 estan los conteos de < Frecuencia
            //En cada columna estan las opciones para los diferentes llamados
            int[][] opcionesTotalesFrecAlelicas = new int[2][3];
            opcionesTotalesFrecAlelicas[0][0] = snpRef.ConteoAlelosMayorFr();
            opcionesTotalesFrecAlelicas[0][1] = snpRef.ConteoAlelosMayorFrCasos();
            opcionesTotalesFrecAlelicas[0][2] = snpRef.ConteoAlelosMayorFrControles();
            opcionesTotalesFrecAlelicas[1][0] = snpRef.ConteoAlelosMenorFr();
            opcionesTotalesFrecAlelicas[1][1] = snpRef.ConteoAlelosMenorFrCasos();
            opcionesTotalesFrecAlelicas[1][2] = snpRef.ConteoAlelosMenorFrControles();

            double[][] opcionesFrecuenciasFrecAlelicas = new double[2][3];
            opcionesFrecuenciasFrecAlelicas[0][0] = snpRef.frecuenciasAlelicasTodosAleloMayorFr();
            opcionesFrecuenciasFrecAlelicas[0][1] = snpRef.frecuenciasAlelicasCasosAleloMayorFr();
            opcionesFrecuenciasFrecAlelicas[0][2] = snpRef.frecuenciasAlelicasControlesAleloMayorFr();
            opcionesFrecuenciasFrecAlelicas[1][0] = snpRef.frecuenciasAlelicasTodosAleloMenorFr();
            opcionesFrecuenciasFrecAlelicas[1][1] = snpRef.frecuenciasAlelicasCasosAleloMenorFr();
            opcionesFrecuenciasFrecAlelicas[1][2] = snpRef.frecuenciasAlelicasControlesAleloMenorFr(); 
            
            /*
            int[][] opcionesTotalesFrecGen = new int[4][3];
            opcionesTotalesFrecGen[0][0] = snpRef.homocigotoMayorFr.getCantidad();
            opcionesTotalesFrecGen[0][1] = snpRef.homocigotoMayorFr.totalCasos();
            opcionesTotalesFrecGen[0][2] = snpRef.homocigotoMayorFr.totalControles();
            opcionesTotalesFrecGen[1][0] = snpRef.heterocigoto.getCantidad();
            opcionesTotalesFrecGen[1][1] = snpRef.heterocigoto.totalCasos();
            opcionesTotalesFrecGen[1][2] = snpRef.heterocigoto.totalControles();
            opcionesTotalesFrecGen[2][0] = snpRef.homocigotoMenorFr.getCantidad();
            opcionesTotalesFrecGen[2][1] = snpRef.homocigotoMenorFr.totalCasos();
            opcionesTotalesFrecGen[2][2] = snpRef.homocigotoMenorFr.totalControles();
            opcionesTotalesFrecGen[3][0] = snpRef.NA.getCantidad();
            opcionesTotalesFrecGen[3][1] = snpRef.NA.getCantidadCasoHombre() + snpRef.NA.getCantidadCasoMujer();
            opcionesTotalesFrecGen[3][2] = snpRef.NA.getCantidadControlHombre() + snpRef.NA.getCantidadControlMujer();
            
            double[][] opcionesFrecuenciasFrecGen = new double[4][3];
            opcionesFrecuenciasFrecGen[0][0] = snpRef.frecuenciasGenotipicasTodosHomMayorFr();
            opcionesFrecuenciasFrecGen[0][1] = snpRef.frecuenciasGenotipicasHomMayorFrCasos();
            opcionesFrecuenciasFrecGen[0][2] = snpRef.frecuenciasGenotipicasHomMayorFrControles();
            opcionesFrecuenciasFrecGen[1][0] = snpRef.frecuenciasGenotipicasTodosHeterocigoto();
            opcionesFrecuenciasFrecGen[1][1] = snpRef.frecuenciasGenotipicasHeterocigotoCasos();
            opcionesFrecuenciasFrecGen[1][2] = snpRef.frecuenciasGenotipicasHeterocigotoControles();
            opcionesFrecuenciasFrecGen[2][0] = snpRef.frecuenciasGenotipicasTodosHomMenorFr();
            opcionesFrecuenciasFrecGen[2][1] = snpRef.frecuenciasGenotipicasHomMenorFrCasos();
            opcionesFrecuenciasFrecGen[2][2] = snpRef.frecuenciasGenotipicasHomMenorFrControles();
            opcionesFrecuenciasFrecGen[3][0] = -1.0;
            opcionesFrecuenciasFrecGen[3][1] = -1.0;
            opcionesFrecuenciasFrecGen[3][2] = -1.0;
            */
            
            conteoFrecuenciasAlelicas( i, FRECUENCIAS_ALELICAS_TODOS, snpJsonObject, snpRef, opcionesTotalesFrecAlelicas, opcionesFrecuenciasFrecAlelicas );
            conteoFrecuenciasAlelicas( i, FRECUENCIAS_ALELICAS_CASOS, snpJsonObject, snpRef, opcionesTotalesFrecAlelicas, opcionesFrecuenciasFrecAlelicas );
            conteoFrecuenciasAlelicas( i, FRECUENCIAS_ALELICAS_CONTROLES, snpJsonObject, snpRef, opcionesTotalesFrecAlelicas, opcionesFrecuenciasFrecAlelicas );
            //frecuenciasGenotipicas( i, snpJsonObject );
            
            //Se termina de agregar el snpJsonObject al snpJsonArray luego de haber completado todos los calculos
            this.snpJsonArray.put( snpJsonObject );
            
        }
    }
    
}
