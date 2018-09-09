package clientebuscaminas;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author aguirre
 */
public class ClienteBuscaminas {

    public static void main(String[] args) {
        try {

            BufferedReader bfSistema = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Escriba la direccion del servidor");
            String host = bfSistema.readLine();
            System.out.println("\nEscriba el puerto:");
            int pto = Integer.parseInt(bfSistema.readLine());
            System.out.println("Escriba su nombre:");
            String nombre = bfSistema.readLine();
            System.out.println("\nEscriba el nivel:\n(1)Basico, (2)Intermedio, (3) Avanzado");
            int nivel = Integer.parseInt(bfSistema.readLine());
            byte[] b;
            if (nivel == 1) {
                b = new byte[81];
            } else if (nivel == 2) {
                b = new byte[256];
            } else {
                b = new byte[480];
            }

            Socket cl = new Socket(host, pto);
            DataOutputStream dos = new DataOutputStream(cl.getOutputStream());
            DataInputStream dis = new DataInputStream(cl.getInputStream());
            //envia nombre, nivel
            dos.writeUTF(nombre);
            dos.flush();
            dos.writeInt(nivel);
            dos.flush();
            
            //leer matriz en un arreglo de tamaño total = nxm segun el tipo de nivel
            dis.read(b);
            
            //TODO: logica juego de Buscaminas donde se obtiene la puntuaicon
            
            
            
            //se envia nombre, puntuacion
            int puntuacion = 0;
            dos.writeUTF(nombre);
            dos.flush();
            dos.writeInt(puntuacion);
            dos.flush();
            
            bfSistema.close();
            dos.close();
            dis.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}