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
public class Gen{    
    private String tipo;
    private int cantidad;
    private int cantidadControlHombre;
    private int cantidadCasoHombre;
    private int cantidadControlMujer;
    private int cantidadCasoMujer;

    public Gen() {
    }

    public Gen(String tipo, int cantidad, int cantidadControlHombre, int cantidadCasoHombre, int cantidadControlMujer, int cantidadCasoMujer) {
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.cantidadControlHombre = cantidadControlHombre;
        this.cantidadCasoHombre = cantidadCasoHombre;
        this.cantidadControlMujer = cantidadControlMujer;
        this.cantidadCasoMujer = cantidadCasoMujer;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCantidadControlHombre() {
        return cantidadControlHombre;
    }

    public void setCantidadControlHombre(int cantidadControlHombre) {
        this.cantidadControlHombre = cantidadControlHombre;
    }

    public int getCantidadCasoHombre() {
        return cantidadCasoHombre;
    }

    public void setCantidadCasoHombre(int cantidadCasoHombre) {
        this.cantidadCasoHombre = cantidadCasoHombre;
    }

    public int getCantidadControlMujer() {
        return cantidadControlMujer;
    }

    public void setCantidadControlMujer(int cantidadControlMujer) {
        this.cantidadControlMujer = cantidadControlMujer;
    }

    public int getCantidadCasoMujer() {
        return cantidadCasoMujer;
    }

    public void setCantidadCasoMujer(int cantidadCasoMujer) {
        this.cantidadCasoMujer = cantidadCasoMujer;
    }
    
    public int totalCasos()
    {
        return cantidadCasoHombre+cantidadCasoMujer;
    }
    public int totalControles()
    {
        return cantidadControlHombre+cantidadControlMujer;
    }
    
    
    
}
