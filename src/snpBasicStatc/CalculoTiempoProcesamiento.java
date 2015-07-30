/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snpBasicStatc;

/**
 *
 * @author Loena
 */
public class CalculoTiempoProcesamiento {
    public static String fotmatoTiempo(long ms){//Devuelve el formato h, m, s, ms para un número de ms dado. Para 3600000 devolvería "1 h.".
        final int msHora = 3600000;
        final int msMinuto = 60000;
        final int msSegundo = 1000;
        
        long horas = 0;
        int minutos = 0;
        int segundos = 0;
        int milisegundos;
        
        if (ms >= msHora){
            horas = ms / msHora;
            ms -= msHora * horas;
        }
        if (ms >= msMinuto){
            minutos = (int) ms / msMinuto;
            ms -= msMinuto * minutos;
        }
        if (ms >= msSegundo){
            segundos = (int) ms / msSegundo;
            ms -= msSegundo * segundos;
        }
        milisegundos = (int) ms;
        return  (horas + minutos + segundos + milisegundos == 0 ? "De inmediato" : "") + (
                (horas > 0 ? horas + " h" : "") + 
                (minutos > 0 ? (horas > 0 ? ", " : "") + minutos + " min" : "") + 
                (segundos > 0 ? (horas + minutos > 0 ? ", " : "") + segundos + " s" : "") + 
                (milisegundos > 0 ? (horas + minutos + segundos > 0 ? ", " : "") + milisegundos + " ms" : "")
                ) + '.'
                ;
    }
    public static void main(String[] args) {
        long actual=System.currentTimeMillis();;
        System.out.println(fotmatoTiempo(actual));
    }
    
}
