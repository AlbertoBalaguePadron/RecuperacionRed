import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.io.ByteArrayOutputStream;
import java.util.Scanner;

public class main {

    public static void main(String[]args) {

        Session sesion = null;
        ChannelExec canal = null;
        Scanner leer = new Scanner(System.in);

        try {

            System.out.println("Introduce la IP");
            String ip = leer.next();

            System.out.println("Introduce el Puerto");
            int puerto = leer.nextInt();

            System.out.println("Introduce el Usuario");
            String usuario = leer.next();

            System.out.println("Introduce la Contraseña");
            String contra = leer.next();

            sesion = new JSch().getSession(usuario, ip ,puerto);
            sesion.setPassword(contra);
            sesion.setConfig("StrictHostKeyChecking", "no");
            sesion.connect();
            System.out.println("Conectado correctamente");

            int bucle = 0;

            while (bucle == 0) {

                canal = (ChannelExec) sesion.openChannel("exec");

                System.out.println("Introduce el nombre del archivo");
                String nombreArchivo = leer.next();

                System.out.println("Introduce la fecha y hora");
                String fechaHora = leer.next();

                canal.setCommand("touch /home/" + nombreArchivo);
                canal.setCommand("echo " + fechaHora + " > " + nombreArchivo);

                ByteArrayOutputStream respuesta = new ByteArrayOutputStream();
                canal.setOutputStream(respuesta);

                canal.connect();

                while (canal.isConnected()) {
                    Thread.sleep(100);
                }


                String respuestaString = new String(respuesta.toByteArray());
                System.out.println(respuestaString);

                System.out.println("Para introducir otro fichero pulse 0, para salir pulse 1 ");
                bucle = leer.nextInt();
            }

        } catch (JSchException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(sesion != null) sesion.disconnect();
            if(canal != null) canal.disconnect();
        }
    }
}
