/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snpBasicStatc;

import static snpBasicStatc.ChiSquareUtils.pochisq;
import java.util.ArrayList;

/**
 *
 * @author Loena
 */
public class AlelosConjuntosSNP {
    double mayorP1; double mayorQ1; double menorP1; double menorQ1;
    int indice1;
    int indice2;
    double LD,DMax,personCoefCor;
    ArrayList<SNP> arregloSNP;

    public AlelosConjuntosSNP(double mayorP1, double mayorQ1, double menorP1, double menorQ1, int indice1, int indice2) {
        this.mayorP1 = mayorP1;
        this.mayorQ1 = mayorQ1;
        this.menorP1 = menorP1;
        this.menorQ1 = menorQ1;
        this.indice1 = indice1;
        this.indice2 = indice2;
    }

    public ArrayList<SNP> getArregloSNP() {
        return arregloSNP;
    }

    public void setArregloSNP(ArrayList<SNP> arregloSNP) {
        this.arregloSNP = arregloSNP;
    }
    
    
    
    
    
    public double linkageDisequilibrium()
    {
        /*Calculo de frecuencias haplotipicas*/
        double p11= mayorP1*mayorQ1;
        double p22= menorP1*menorQ1;
        
        double p12=mayorP1*menorQ1;
        double p21=menorP1*mayorQ1;
        
        this.LD= p11*p22-(p12*p21);
        
        if(LD>0)
          this.DMax=   Math.min(p12, p21);
        if (LD<0)
          this.DMax= Math.min(p22, p11);
        
        return LD;
        
    }
    
    
     public double StandardizationD() 
     {
         return this.LD/this.DMax;
     }
    
      
     public double Pearsoncoefficientofcorrelation()
     {
          personCoefCor=this.LD/Math.sqrt(mayorP1*mayorQ1*menorP1*menorQ1);
          
          return Math.pow(personCoefCor, 2);
         
     }
    
     
     public double AlelosConjuntosChiCuadrado(int MayorA1, int MenorA1, int MayorA2, int MenorA2)
     {
          int total=(MayorA1+MayorA2)+(MayorA1+MenorA2)+(MenorA1+MayorA2)+(MenorA1+MenorA2);
          double chisq=Pearsoncoefficientofcorrelation()*total;
          chisq = pochisq(chisq, 1);
          return chisq;
     }
    
     
   
         
        
         
        
   
     
}
