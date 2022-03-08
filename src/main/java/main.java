import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.util.Scanner;

public class main {

    public static void main(String[]args) {

        Session sesion = null;
        ChannelExec canal = null;
        Scanner leer = new Scanner(System.in);

        try {

            System.out.println("Introduce la ip");
            String ip = leer.next();

            System.out.println("Introduce el puerto");
            int puerto = leer.nextInt();

            System.out.println("Introduce el usuario");
            String usuario = leer.next();

            System.out.println("Introduce la contraseña");
            String contra = leer.next();

            sesion = new JSch().getSession(usuario, ip ,puerto);
            sesion.setPassword(contra);
            sesion.setConfig("StrictHostKeyChecking", "no");
            sesion.connect();
            System.out.println("Conectado correctamente");






        } catch (JSchException e) {
            e.printStackTrace();
        } finally {
            if(sesion != null) sesion.disconnect();
            if(canal != null) canal.disconnect();
        }
    }
}
