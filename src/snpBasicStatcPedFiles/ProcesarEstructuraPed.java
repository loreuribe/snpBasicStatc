/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snpBasicStatcPedFiles;

import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import snpBasicStatc.Funciones;
import snpBasicStatc.Gen;
import snpBasicStatc.SNP;

/**
 *
 * @author Loena
 */
public class ProcesarEstructuraPed {
    
       
    /** 
     * Arreglo de genes guardado en un array list
     */
    ArrayList<Gen>[] snp;
    ArrayList <SNP>arregloSNP;
    
    JFileChooser choser;
    int aceptar;
    
   //Constructor
    public ProcesarEstructuraPed() {
        //Funciones funciones=new Funciones();
        FuncionesPed funciones=new FuncionesPed();
        
        
        
        arregloSNP=new ArrayList<SNP>();
        choser = new JFileChooser();
        choser.setMultiSelectionEnabled(false);
        choser.addChoosableFileFilter(new FileNameExtensionFilter("Documento de texto (txt)", "txt"));
        aceptar = choser.showOpenDialog(null);
        int acumuladorCasos;
        int acumuladorControles;
        if(aceptar==JFileChooser.APPROVE_OPTION){    
            ArrayList <String> textoArchivo = funciones.leer_Archivo(choser.getSelectedFile().getAbsolutePath());
            snp=funciones.llenar_Estructura(textoArchivo);        
            Gen tempGen = new Gen();

        }
    }
     
     public void setArregloSNP(ArrayList<SNP> arregloSNP) {
        this.arregloSNP = arregloSNP;
    }
 
    public void Procesar_Archivo(){
        Funciones funciones=new Funciones();
        JFileChooser choser = new JFileChooser();
        choser.setMultiSelectionEnabled(false);
        choser.addChoosableFileFilter(new FileNameExtensionFilter("Documento de texto (txt)", "txt"));
        aceptar = choser.showOpenDialog(null);
        int acumuladorCasos;
        int acumuladorControles;
        if(aceptar==JFileChooser.APPROVE_OPTION){    
            ArrayList <String> textoArchivo = funciones.leer_Archivo(choser.getSelectedFile().getAbsolutePath());
            snp=funciones.llenar_Estructura(textoArchivo);        
            Gen tempGen = new Gen();

        }
    }
    
    public void Procesar_ArchivoTUI(String nombreArchivo){
        Funciones funciones=new Funciones();
         JFileChooser choser = new JFileChooser();
         choser.setMultiSelectionEnabled(false);
         choser.addChoosableFileFilter(new FileNameExtensionFilter("Documento de texto (txt)", "txt"));
         aceptar = choser.showOpenDialog(null);
        int acumuladorCasos;
        int acumuladorControles;
        if(nombreArchivo.length()!=0)
        {    
            ArrayList <String> textoArchivo = funciones.leer_Archivo(nombreArchivo);
            snp=funciones.llenar_Estructura(textoArchivo);        
            Gen tempGen = new Gen();

        }
    }
    

    private void llenarEstructuraSNP()
    {
        //Gen tempGen; 
        SNP tempsnp; 
        int j=0;
       
        for(int i=0; i<snp.length; i++)
        { 
            tempsnp= new SNP();
                if(snp[i].size()==4)
                {    
                    j=0;
                    tempsnp.homocigotoMayorFr=(Gen)snp[i].get(j);
                    j++;
                   
                    tempsnp.heterocigoto=(Gen)snp[i].get(j);
                    j++;
                    
                    tempsnp.homocigotoMenorFr=snp[i].get(j);
                    j++;
                    
                    tempsnp.NA=(Gen)snp[i].get(j);
                    j++;
                }
             
                if(snp[i].size()==3)
                {
                    j=0;
                    tempsnp.homocigotoMayorFr=(Gen)snp[i].get(j);
                    j++;
                   
                    tempsnp.heterocigoto=(Gen)snp[i].get(j);
                    j++;
                    
                    tempsnp.homocigotoMenorFr=snp[i].get(j);
                    j++;
                    
                }
 
                arregloSNP.add(tempsnp);
        }

    } 
    
    
    public void idetificarHeterocigoto()
    {
        
        for(int i =1 ; i <arregloSNP.size();i++ )
        {
            
            
             if(!arregloSNP.get(i).homocigotoMayorFr.getTipo().substring(0,1).equals(arregloSNP.get(i).homocigotoMayorFr.getTipo().substring(2)))
             {
                Gen tempSNP = arregloSNP.get(i).homocigotoMayorFr;
                arregloSNP.get(i).homocigotoMayorFr=arregloSNP.get(i).heterocigoto;
                arregloSNP.get(i).heterocigoto=tempSNP;
             }
             if(!arregloSNP.get(i).homocigotoMenorFr.getTipo().substring(0,1).equals(arregloSNP.get(i).homocigotoMenorFr.getTipo().substring(2)))
             {
                Gen tempSNP = arregloSNP.get(i).homocigotoMenorFr;
                arregloSNP.get(i).homocigotoMenorFr=arregloSNP.get(i).heterocigoto;
                arregloSNP.get(i).heterocigoto=tempSNP;
             }
            
            
        }
        
    }
    
    private void identificarDatosFaltantes()
    {
        for(int i=0;i<arregloSNP.size();i++)
        {
            
            //Ordena en su posición los NA
            //if(arregloSNP.get(i).homocigotoMayorFr.getTipo().contains("NA"))
            if(arregloSNP.get(i).homocigotoMayorFr.getTipo().contains("0/0"))
            {
                Gen tempSNP = arregloSNP.get(i).homocigotoMayorFr;
                arregloSNP.get(i).homocigotoMayorFr=arregloSNP.get(i).NA;
                arregloSNP.get(i).NA= tempSNP;
                
            }
            //if(arregloSNP.get(i).homocigotoMenorFr.getTipo().contains("NA"))
            if(arregloSNP.get(i).homocigotoMenorFr.getTipo().contains("0/0"))
            {
                Gen tempSNP = arregloSNP.get(i).homocigotoMenorFr;
                arregloSNP.get(i).homocigotoMenorFr=arregloSNP.get(i).NA;
                arregloSNP.get(i).NA= tempSNP;
                
            }
            //if(arregloSNP.get(i).heterocigoto.getTipo().contains("NA"))
            if(arregloSNP.get(i).heterocigoto.getTipo().contains("0/0"))
            {
                Gen tempSNP = arregloSNP.get(i).heterocigoto;
                arregloSNP.get(i).heterocigoto=arregloSNP.get(i).NA;
                arregloSNP.get(i).NA= tempSNP;
                
            }
        }
    }
    
    private void buscarDominates()
    {
        for(int i=0;i<arregloSNP.size();i++)
        {
            if(arregloSNP.get(i).homocigotoMayorFr.getCantidad() < arregloSNP.get(i).homocigotoMenorFr.getCantidad())
            {
                 Gen tempSNP = arregloSNP.get(i).homocigotoMayorFr;
                arregloSNP.get(i).homocigotoMayorFr=arregloSNP.get(i).homocigotoMenorFr;
                arregloSNP.get(i).homocigotoMenorFr=tempSNP;
            }    
        }
    }
    
    
    
    public  void  organizarSNP()
    {
        llenarEstructuraSNP();
        identificarDatosFaltantes();
        idetificarHeterocigoto();          

        
    }
    
    
    public static void main(String[] args) {
        ProcesarEstructuraPed ped = new ProcesarEstructuraPed();
        //ped.organizarSNP();
        
    }
}
