/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientebuscaminas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author aguirre
 */
public final class JuegoBuscaminas {

    private int x;
    private int y;
    private int[][] juego;
    private int ancho;
    private int largo;

    public JuegoBuscaminas() {
    }

    public int iniciarJuego(int nivel, int[][] juego) {
        int puntuacion = 0;
        boolean canJugar = true;
        this.juego = juego;
        largo = juego.length;
        ancho = juego[0].length;
        String escenario[][] = new String[largo][ancho];
        for (int i = 0; i < largo; i++) {
            for (int j = 0; j < ancho; j++) {
                escenario[i][j] = " - ";
            }
            System.out.println("");
        }
        imprimirEscenario(escenario);
        leerCoordenada();
        jugar(escenario, canJugar);

        return puntuacion;
    }

    private void jugar(String escenario[][], boolean canSeguirJugando) {
        while (canSeguirJugando) {
            int minasVecinas = 0;
            if (juego[y][x] == 1) {
                canSeguirJugando = false;
                System.out.println("FIN DEL JUEGO!!!!");
                break;
            } else {
                if (y == 0) {
                    if (x == 0) {//esq. sup. izq
                        minasVecinas = juego[0][1] + juego[1][0] + juego[1][1];
                    } else if (x == (ancho - 1)) {//esq. sup. der
                        minasVecinas = juego[0][ancho - 2] + juego[1][ancho - 1] + juego[1][ancho - 2];
                    } else {
                        minasVecinas = juego[0][x - 1] + juego[0][x + 1] + juego[1][x - 1] + juego[1][x] + juego[1][x + 1];
                    }

                } else if (y == (largo - 1)) {
                    if (x == 0) {//esq. inf. izq
                        minasVecinas = juego[largo - 1][1] + juego[largo - 2][0] + juego[largo - 2][1];
                    } else if (x == (ancho - 1)) {//esq. inf. der
                        minasVecinas = juego[largo - 1][ancho - 2] + juego[largo - 2][ancho - 1] + juego[largo - 2][ancho - 2];
                    } else {
                        minasVecinas = juego[largo - 1][x - 1] + juego[largo - 1][x + 1] + juego[largo - 2][x - 1] + juego[largo - 2][x] + juego[largo - 2][x + 1];
                    }
                } else {
                    if (x == 0) {
                        minasVecinas = juego[y-1][0] + juego[y + 1][0] + juego[y-1][1] + juego[y][1] + juego[y+1][1];
                    }else if(x == (ancho - 1)){
                        minasVecinas = juego[y-1][ancho - 1] + juego[y + 1][ancho - 1] + juego[y-1][ancho - 2] + juego[y][ancho - 2] + juego[y+1][ancho - 2];
                    }else{
                        minasVecinas = juego[y-1][x-1] + juego[y][x-1] + juego[y+1][x-1] + juego[y-1][x] + juego[y+1][x] + juego[y-1][x+1] + juego[y][x+1] + juego[y+1][x+1];
                    }

                }

            }
            actualizarEscenario(minasVecinas, escenario);
        }

    }

    private void actualizarEscenario(int minasVecinas, String escenario[][]) {
        escenario[y][x] = " " + Integer.toString(minasVecinas) + " ";
        imprimirEscenario(escenario);
        leerCoordenada();
    }

    private void leerCoordenada() {
        BufferedReader bfSistema = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("introduzca coordenada de tiro de la forma: y,x ");
            String coordenada = bfSistema.readLine();
            x = Integer.parseInt(coordenada.split(",")[1]);
            y = Integer.parseInt(coordenada.split(",")[0]);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void imprimirEscenario(String escenario[][]) {
        for (int i = 0; i < largo; i++) {
            for (int j = 0; j < ancho; j++) {
                System.out.print(escenario[i][j]);
            }
            System.out.println("");
        }
    }

}
