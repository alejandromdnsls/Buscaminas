/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorbuscaminas;

import usuario.Usuario;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
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
                                
                //Leer puntuación
                int puntuacion = dis.readInt();
                System.out.println("Puntos: " + puntuacion);
                
                //Comprueba si exite el Usuario en el archivo                               
                
                if(searchUsuario(usuario) == null){
                    Usuario u = new Usuario(usuario, puntuacion, 1);
                    ArrayList <Usuario> usuarios = getUsuarios();
                    ObjectOutputStream oosf = new ObjectOutputStream(new FileOutputStream("Resultados.dat"));                    
                    for(int i = 0; i < usuarios.size(); i++){
                        oosf.writeObject(usuarios.get(i));
                    }
                    oosf.writeObject(u);
                    oosf.close();
                    ArrayList <Usuario> otro= getUsuarios();
                    for(int i = 0; i < otro.size(); i++){
                        System.out.println(otro.get(i).getNombre());
                    }
                    
                }
                else{
                    actualizarUsuario(usuario, puntuacion);
                }
   
                oos.writeObject(getUsuarios());
                
                oos.close();
                dis.close();
                cl.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public static Usuario searchUsuario(String u) throws IOException, ClassNotFoundException{
        ObjectInputStream ois = null;
        try{
            ois = new ObjectInputStream(new FileInputStream("Resultados.dat"));             
            while(true){
                Usuario usuario = (Usuario)ois.readObject();                
                if(usuario.getNombre().equals(u))
                    return usuario;                
            }
        }catch(EOFException e){
            return null;
        }finally{
            ois.close();
        }
    }
    
    public static ArrayList<Usuario> getUsuarios() throws IOException, ClassNotFoundException{
        ObjectInputStream ois = null;
        ArrayList <Usuario> usuarios = new ArrayList<>(); 
        try{
            ois = new ObjectInputStream(new FileInputStream("Resultados.dat"));
            while(true){
                Usuario usuario = (Usuario)ois.readObject();
                usuarios.add(usuario);
                //System.out.println(usuario.getNombre());
            }
        }catch(EOFException e){
            return usuarios;
        }finally{
            ois.close();
        }
    }    
    
    public static void actualizarUsuario(String u, int p) throws IOException, ClassNotFoundException{
        ArrayList<Usuario> usuarios = getUsuarios();      
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Resultados.dat"));
        for(int i = 0; i < usuarios.size(); i++){
            if(usuarios.get(i).getNombre().equals(u)){
                int partidas = usuarios.get(i).getPartidas() + 1;
                usuarios.get(i).setPartidas(partidas);
                if(usuarios.get(i).getPuntuacion() < p)
                    usuarios.get(i).setPuntuacion(p);
                oos.writeObject(usuarios.get(i));
            }
            oos.writeObject(usuarios.get(i));
        }              
        oos.close();
    }
    
    /*public static ArrayList<Usuario> getTop() throws IOException, ClassNotFoundException{
        ArrayList<Usuario> top_usuarios = getUsuarios();
        Collections.sort(top_usuarios);
        return top_usuarios;        
    } */
}
