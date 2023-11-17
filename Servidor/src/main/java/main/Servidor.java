package main;

import auxiliares.Empleado;
import auxiliares.Mensaje;
import auxiliares.NodoMapa;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.xml.bind.JAXBException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * La clase Servidor implementa un servidor que maneja conexiones entrantes de dispositivos Android.
 */
public class Servidor implements Runnable {

    //instancia de server socket
    private ServerSocket serverSocket;

    private Random random = new Random();

    //lista de nodos
    ArrayList<NodoMapa> listaNodos = new ArrayList<>();
    private static final Logger logger = Logger.getLogger(Servidor.class.getName());

    /**
     * Constructor del objeto Servidor
     * Se inicializa el ServerSocket en el puerto 7777
     * Se inicia un hilo en el propio servidor
     */
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



    /**
     * Convierte al servidor en un objeto ejecutable
     * @param args
     */
    public static void main(String [] args){
        //instancia del servidor
        Servidor servidor = new Servidor();
        servidor.generarNodosMapa();

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        logger.addHandler(consoleHandler);

        logger.setLevel(Level.ALL);


    }

    private void enviarMensajeAndroid(Socket socket, String accion){

        try {
            //respuesta al android
            Mensaje mensaje = new Mensaje();

            switch (accion){

                //en caso de solicitar la configuración de nodos del server
                case "solicitarNodosMapa" -> {

                    mensaje.setAccion("nodosMapa");
                    mensaje.setListaNodosMapa(listaNodos);

                }

                case "wewe" -> {

                    Empleado empleado = new Empleado();
                    empleado.setCarnetUsuario("20214646362");
                    empleado.setPasswordUsuario("contraseñia");

                }


            }

            //serialización
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

            //convertir objeto a un json
            String jsonString = new ObjectMapper().writeValueAsString(mensaje);

            System.out.println(jsonString);

            //envío del mensaje
            out.writeObject(jsonString);

            System.out.println("Se ha respondido al socket: " + socket.getInetAddress());

            //cerrar canales
            out.close();
            socket.close();

        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    //generar numeros aleatorios en el área del mapa
    private double generarNumRandom(double min, double max){

        return min + (Math.random() * (max - min));

    }

    //generar objetos nodos
    private void generarNodosMapa(){

        //for para guardar varios nodos
        for (int i = 0; i < 30; i++) {

            //generar objeto nodo
            NodoMapa nodoMapa = new NodoMapa();

            double min1 = 9.818813335100824;
            double max1 = 10.03998298080924;
            double min2 = -84.31611066808877;
            double max2 = -83.86910447044677;


            double latitud = generarNumRandom(max1, min1);
            double longitud = generarNumRandom(max2, min2);

            //dar latitud y longitud al nodo
            nodoMapa.setLatitud(latitud);
            nodoMapa.setLongitud(longitud);
            nodoMapa.setNombreMarcador("User" + (i + 1));
            nodoMapa.setPesoRuta(random.nextInt(100) + 1);

            listaNodos.add(nodoMapa);

            //System.out.println(latitud + "  " + longitud);
        }

        //pintar los elementos de la lista
        /*for (int i = 0; i < listaNodos.size();i++){

            NodoMapa nodoMapa = listaNodos.get(i);
            System.out.println(nodoMapa.getNombreMarcador());
            System.out.println(nodoMapa.getPesoRuta());
            System.out.println(nodoMapa.getLatitud());
            System.out.println(nodoMapa.getLongitud());
            System.out.println("--------------------------------------------------------------");

        }*/

    }

    private void procesarSolicitudesServer(Socket socket,Mensaje mensaje) {

        switch (mensaje.getAccion()){

            case "crearUsuario" -> {

                //ruta del archivo
                String xmlFilePath = "empleado.xml";

                try {

                    //obtiene y guarda el empleado almacenado en el objeto mensaje
                    auxiliares.XMLHandler.appendToXML(mensaje.getEmpleado(), xmlFilePath);
                    System.out.println("Empleado stored in XML file: " + xmlFilePath);
                } catch (JAXBException e) {
                    e.printStackTrace();
                }

            }

            case "solicitarNodosMapa" -> {

                //se envia respuesta al android con los nodos a utilizar
                enviarMensajeAndroid(socket,"solicitarNodosMapa");

            }

        }

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
                logger.info("Cliente conectado: " + socket.getInetAddress());

                //deserializar información del socket
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

                //recibir json del cliente
                String jsonString = (String) in.readObject();
                logger.info("Datos recibidos: " + jsonString);

                //convertir json a java
                ObjectMapper mapper = new ObjectMapper();
                Mensaje mensaje = mapper.readValue(jsonString, Mensaje.class);
                //Empleado empleado = mapper.readValue(jsonString,Empleado.class);

                //se procesa los mensajes entrantes desde los dispositovos android
                procesarSolicitudesServer(socket,mensaje);

                //prueba
                //enviarMensajeAndroid(socket,);

                socket.close();
                in.close();

            }
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }

    }

}
