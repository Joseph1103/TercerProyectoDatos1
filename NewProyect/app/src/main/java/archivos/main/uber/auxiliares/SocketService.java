package archivos.main.uber.auxiliares;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;



public class SocketService extends Service {

    private static final String TAG = "SocketService";
    private static final String SERVER_IP = "192.168.1.51"; // Reemplaza con la dirección IP de tu servidor
    private static final int SERVER_PORT = 7777; // Reemplaza con el puerto que estás utilizando
    private boolean isRunning = false;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "Servicio creado");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "Servicio iniciado");
        isRunning = true;

        // Iniciar el hilo de escucha
        new ListenTask().execute();

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Servicio destruido");
        isRunning = false;
    }

    private class ListenTask extends AsyncTask<Void, String, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Socket socket = new Socket(SERVER_IP, SERVER_PORT);
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                // Mantener la escucha del servidor
                while (isRunning) {
                    String message = reader.readLine();
                    if (message == null) {
                        // Si el servidor cierra la conexión, salimos del bucle
                        break;
                    }
                    publishProgress(message);  // Actualizar la interfaz de usuario con el mensaje recibido
                }

                // Cerrar la conexión cuando dejamos de escuchar
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            // Actualizar la interfaz de usuario con el mensaje recibido
            Log.d(TAG, "Mensaje del servidor: " + values[0]);
            // Puedes enviar un Broadcast a tus componentes para que actualicen la interfaz de usuario
            // o utilizar un EventBus u otras técnicas para comunicar el mensaje a las partes interesadas.
        }
    }
}





