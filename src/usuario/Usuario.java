/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario;

import java.io.Serializable;

/**
 *
 * @author alejandroms
 */
public class Usuario implements Serializable{
    String nombre;
    int puntuacion;
    int partidas;
    
    public Usuario(String nombre, int puntuacion, int partidas){
        this.nombre = nombre;
        this.puntuacion = puntuacion;
        this.partidas = partidas;                
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public int getPartidas() {
        return partidas;
    }

    public void setPartidas(int partidas) {
        this.partidas = partidas;
    }           
    
    /*
    @Override
    public int compareTo(Usuario o) {
        if (puntuacion < o.puntuacion) {
            return puntuacion;
        }
        if (puntuacion > o.puntuacion) {
            return puntuacion;
        }
        return 0;
    }*/

    /*
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (this.puntuacion != other.puntuacion) {
            return false;
        }
        return true;
    }*/

    
       
}
