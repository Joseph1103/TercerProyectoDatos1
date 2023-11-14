package archivos.main.uber.auxiliares;

import android.os.AsyncTask;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class LoginTask extends AsyncTask<String, Void, String> {

    private static final String SERVER_IP = "192.168.1.51"; // Ip a conectar
    private static final int SERVER_PORT = 7777; // Puerto a conectarse en la IP asignada

    @Override
    protected String doInBackground(String... params) {
        String action = params[0];
        String user = params[1];
        String password = params[2];

        String finalMessage = "";


        String serverResponse = null;

        try {
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);

            // serializar información
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

            // crear clase para guardar la información
            Empleado empleado = new Empleado();
            empleado.setAccion(action);
            empleado.setCarnetUsuario(user);
            empleado.setPasswordUsuario(password);

            String jsonString = new ObjectMapper().writeValueAsString(empleado);

            out.writeObject(jsonString);

            // Recibir respuesta del servidor
            // Aquí deberías implementar la lógica para manejar la respuesta del servidor
            // En este ejemplo, simplemente imprimimos la respuesta
            serverResponse = "Respuesta del servidor: " + socket.getInputStream().toString();

            // Cerrar la conexión
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
            serverResponse = "Error: " + e.getMessage();
        }

        return serverResponse;
    }

    // Este método se llama después de que doInBackground ha terminado
    @Override
    protected void onPostExecute(String result) {
        // Manejar la respuesta del servidor (puedes actualizar la interfaz de usuario aquí)
        // Por ejemplo, puedes mostrar un Toast con el resultado
        // Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
    }
}