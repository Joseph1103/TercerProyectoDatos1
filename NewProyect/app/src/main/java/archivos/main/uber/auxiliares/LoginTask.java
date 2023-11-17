package archivos.main.uber.auxiliares;

import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ref.WeakReference;
import java.net.Socket;

import archivos.main.uber.crearUsuarioActivity.CrearUsuarioActivity;
import archivos.main.uber.loginActivity.LoginActivity;

public class LoginTask extends AsyncTask<String, Void, String> {

    private static final String SERVER_IP = "192.168.1.51"; // Ip a conectar
    private static final int SERVER_PORT = 7777; // Puerto a conectarse en la IP asignada

    private String accion;

    private WeakReference<AsyncTaskCompleteListener> callBackReference;

    //private CrearUsuarioActivity.AsyncTaskListener listener;


    public LoginTask(AsyncTaskCompleteListener callback) {

        callBackReference = new WeakReference<>(callback);

    }

    public void setAccion(String accion){
        this.accion = accion;
    }

    @Override
    protected String doInBackground(String... params) {

        String user = null;
        String password = null;
        String latitud = null;
        String longuitud = null;

        if (params.length != 0) {
            user = params[0];
            password = params[1];
            latitud = params[2];
            longuitud = params[3];
        }

        String finalMessage = "";

        String serverResponse = null;

        try {
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);

            // serializar información
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

            //objeto mensaje que llevará el objeto empleado
            Mensaje mensaje = new Mensaje();
            mensaje.setAccion(accion);

            // crear clase para guardar la información
            Empleado empleado = new Empleado();
            empleado.setCarnetUsuario(user);
            empleado.setPasswordUsuario(password);
            empleado.setLatitud(latitud);
            empleado.setLongitud(longuitud);

            mensaje.setEmpleado(empleado);

            //convertir a un objeto json el objeto empleado
            String jsonString = new ObjectMapper().writeValueAsString(mensaje);

            //enviar el mensaje por el socket alservidor
            out.writeObject(jsonString);

            // Recibir respuesta del servidor
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            serverResponse = (String)  in.readObject();


        } catch (IOException e) {
            e.printStackTrace();
            serverResponse = "Error: " + e.getMessage();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }



        return serverResponse;
    }



    // Este método se llama después de que doInBackground ha terminado
    @Override
    protected void onPostExecute(String result) {
        // Manejar la respuesta del servidor (puedes actualizar la interfaz de usuario aquí)
        // Por ejemplo, puedes mostrar un Toast con el resultado
        // Toast.makeText(context, result, Toast.LENGTH_SHORT).show();

        AsyncTaskCompleteListener callback = callBackReference.get();
        callback.onTaskComplete(result);

    }
}