/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorbuscaminas;

import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author alejandroms
 */
public class ServidorBuscaminas {
    public static void main(String[]args){
        try{
            ServerSocket s = new ServerSocket(1234);
            System.out.println("Esperando cliente...");
            //El servidor espera la solicitud de conexión de un cliente
            for(;;){
                Socket cl = s.accept();
                System.out.println("Conexión establecida desde: " + 
                        cl.getInetAddress() + ":" + cl.getPort());
                //Se define un flujo de entrada ligado al socket
                DataInputStream dis = new DataInputStream(cl.getInputStream());
                //se reciben los datos para generar el juego
                int nivel = dis.readInt();
                String usuario = dis.readUTF();                
                System.out.println("Usuario: " + usuario);
                //Se define un flujo de salida para enviar una matriz de acuerdo al nivel
                ObjectOutputStream oos = new ObjectOutputStream(cl.getOutputStream());
                int matriz[][];
                switch(nivel){
                    case 1:                       
                        matriz = new int[9][9];
                        for(int i = 0; i < 9; i++){
                            for(int j = 0; j < 9; i++){
                                matriz[i][j] = 0;
                            }
                        }
                        oos.writeObject(matriz);
                        oos.flush();
                        break;
                    case 2:
                        matriz = new int[16][16];
                        break;
                    case 3:
                        matriz = new int[16][30];
                        break;
                }
                oos.close();
                dis.close();
                cl.close();
            }            
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
}
