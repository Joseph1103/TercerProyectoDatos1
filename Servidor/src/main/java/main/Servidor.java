package main;

import archivos.main.uber.auxiliares.Empleado;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor implements Runnable {

    private ServerSocket serverSocket;

    public Servidor(){

        //inicializar el puerto del servidor
        try {
            serverSocket = new ServerSocket(7777);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //mensaje de servidor iniciado
        System.out.println("Server Noises Turning On");

        //hilo para mantener el servidor activo
        Thread hiloServer = new Thread(this::run);
        hiloServer.start();


    }

    public static void main(String [] args){

        Servidor servidor = new Servidor();

    }



    @Override
    /**
     * Metodo encargado de mantenerse a la escucha de cualquier información entrante
     */
    public void run(){
        try {

            //mantener servidor activo
            while (true) {

                //socket que recibe el mensaje
                Socket socket = serverSocket.accept();

                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

                //recibir json del cliente
                String jsonString = (String) in.readObject();
                System.out.println(jsonString);

                //convertir json a java
                ObjectMapper mapper = new ObjectMapper();
                Empleado empleado = mapper.readValue(jsonString,archivos.main.uber.auxiliares.Empleado.class);

                System.out.println(empleado.getCarnetUsuario() + "   " + empleado.getPasswordUsuario());

            }
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }

    }

}
