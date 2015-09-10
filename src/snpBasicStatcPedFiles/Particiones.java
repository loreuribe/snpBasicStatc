/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snpBasicStatcPedFiles;

/**
 *
 * @author Loena
 */
public class Particiones {
    
    public int inicioP;
    public int finalP;

    public Particiones(int inicioP, int finalP) {
        this.inicioP = inicioP;
        this.finalP = finalP;
    }

    @Override
    public String toString() {
        return "Particiones{" + "inicioP=" + inicioP + ", finalP=" + finalP + '}';
    }
    
    
    
}
