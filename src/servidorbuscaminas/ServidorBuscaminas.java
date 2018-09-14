/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorbuscaminas;

import usuario.Usuario;

import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 *
 * @author alejandroms
 */
public class ServidorBuscaminas {

    public static void main(String[] args) {
        try {
            ServerSocket s = new ServerSocket(1234);
            System.out.println("Esperando cliente...");
            //El servidor espera la solicitud de conexión de un cliente
            for (;;) {
                Socket cl = s.accept();
                System.out.println("Conexión establecida desde: "
                        + cl.getInetAddress() + ":" + cl.getPort());
                //Se define un flujo de entrada ligado al socket
                DataInputStream dis = new DataInputStream(cl.getInputStream());
                //se reciben los datos para generar el juego
                String usuario = dis.readUTF();
                int nivel = dis.readInt();
                System.out.println("Usuario: " + usuario);
                //Se define un flujo de salida para enviar una matriz de acuerdo al nivel
                ObjectOutputStream oos = new ObjectOutputStream(cl.getOutputStream());
                int matriz[][];
                Random rand = new Random(System.currentTimeMillis());
                switch (nivel) {
                    case 1:
                        matriz = new int[9][9];
                        for (int i = 0; i < matriz.length; i++) {
                            for (int j = 0; j < matriz[0].length; j++) {
                                matriz[i][j] = 0;
                            }
                        }
                        for (int i = 0; i <= 10; i++) {
                            int y = rand.nextInt(matriz.length);
                            int x = rand.nextInt(matriz[0].length);
                            System.out.println(">>" + y + " " + x);
                            matriz[y][x] = 1;
                            rand.setSeed(System.currentTimeMillis());
                            Thread.sleep(5L);
                        }
                        oos.writeObject(matriz);
                        oos.flush();
                        break;
                    case 2:
                        matriz = new int[16][16];
                        for (int i = 0; i < matriz.length; i++) {
                            for (int j = 0; j < matriz[0].length; j++) {
                                matriz[i][j] = 0;
                            }
                        }
                        
                        for (int i = 0; i <= 40; i++) {
                            rand.nextInt();
                            int y = rand.nextInt(matriz.length);
                            int x = rand.nextInt(matriz[0].length);
                            System.out.println(">>" + y + " " + x);
                            matriz[y][x] = 1;
                            Thread.sleep(17L);
                            rand.setSeed(System.currentTimeMillis());
                        }
                        oos.writeObject(matriz);
                        oos.flush();
                        break;
                    case 3:
                        matriz = new int[16][30];
                        for (int i = 0; i < matriz.length; i++) {
                            for (int j = 0; j < matriz[0].length; j++) {
                                matriz[i][j] = 0;
                            }
                        }
                        for (int i = 0; i <= 99; i++) {
                            rand.nextInt();
                            int y = rand.nextInt(matriz.length);
                            int x = rand.nextInt(matriz[0].length);
                            System.out.println(">>" + y + " " + x);
                            matriz[y][x] = 1;
                            Thread.sleep(22L);
                            rand.setSeed(System.currentTimeMillis());
                        }
                        oos.writeObject(matriz);
                        oos.flush();
                        break;
                }

                //Leer los resultados
                int puntuacion = dis.readInt();
                //Se preparan los datos para almacenar resultados en un archivo
                //se crea un flujo para la salida al archivo
                int partidas = 1;
                Usuario u = new Usuario(usuario, puntuacion, partidas);
                //Almacenar información en un archivo

                //Enviar mejores puntuaciones
                oos.close();
                dis.close();
                cl.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void aleatorio() {

    }

}
