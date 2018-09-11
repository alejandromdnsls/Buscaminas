package clientebuscaminas;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aguirre
 */
public class ClienteBuscaminas {

    public static void main(String[] args) {
        try {

            BufferedReader bfSistema = new BufferedReader(new InputStreamReader(System.in));
//            System.out.println("Escriba la direccion del servidor");
//            String host = bfSistema.readLine();
//            System.out.println("\nEscriba el puerto:");
//            int pto = Integer.parseInt(bfSistema.readLine());
            String host = "127.0.0.1";
            int pto = 1234;
            System.out.println("Escriba su nombre:");
            String nombre = bfSistema.readLine();
            System.out.println("\nEscriba el nivel:\n(1)Basico, (2)Intermedio, (3) Avanzado");
            int nivel = Integer.parseInt(bfSistema.readLine());
            int[][] b;
            if (nivel == 1) {
                b = new int[9][9];
            } else if (nivel == 2) {
                b = new int[16][16];
            } else {
                b = new int[30][16];
            }

            Socket cl = new Socket(host, pto);
            DataOutputStream dos = new DataOutputStream(cl.getOutputStream());
            
            //envia nombre, nivel
            dos.writeUTF(nombre);
            dos.flush();

            dos.writeInt(nivel);
            dos.flush();
            ObjectInputStream ois = new ObjectInputStream(cl.getInputStream());
            //leer matriz en un arreglo de tamaño total = nxm segun el tipo de nivel
            b = (int[][]) ois.readObject();
            
            
            //se envia puntuacion
            JuegoBuscaminas juego = new JuegoBuscaminas();
            int puntuacion = juego.iniciarJuego(nivel, b);;
            dos.writeInt(puntuacion);
            dos.flush();

            bfSistema.close();
            dos.close();
            ois.close();

        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteBuscaminas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
