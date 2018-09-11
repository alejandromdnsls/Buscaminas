/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientebuscaminas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aguirre
 */
public final class JuegoBuscaminas {

    private int x;
    private int y;

    public JuegoBuscaminas() {
    }

    public int iniciarJuego(int nivel, int[][] juego) {
        int puntuacion = 0;
        String escenario[][] = new String[juego.length][juego[0].length];
        for (int i = 0; i < escenario.length; i++) {
            for (int j = 0; j < escenario[0].length; j++) {
                escenario[i][j] = " - ";
            }
            System.out.println("");
        }

        for (int i = 0; i < escenario.length; i++) {
            for (int j = 0; j < escenario[0].length; j++) {
                System.out.print(escenario[i][j]);
            }
            System.out.println("");
        }
        leerCoordenada();
        jugar(juego, escenario);
        return puntuacion;
    }

    private void jugar(int[][] juego, String escenario[][]) {
        if(juego[x][y] == 1){
            
        }else{
            
        }
        

    }

    private void leerCoordenada() {
        BufferedReader bfSistema = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("introduzca coordenada de tiro de la forma: x,y ");
            String coordenada = bfSistema.readLine();
            x = Integer.parseInt(coordenada.split(",")[0]);
            y = Integer.parseInt(coordenada.split(",")[1]);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
