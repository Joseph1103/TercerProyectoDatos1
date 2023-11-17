package archivos.main.uber.crearUsuarioActivity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import archivos.main.uber.auxiliares.AsyncTaskCompleteListener;
import archivos.main.uber.auxiliares.LoginTask;
import archivos.main.uber.auxiliares.Mensaje;
import archivos.main.uber.loginActivity.LoginActivity;
import archivos.main.uber.R;
import archivos.main.uber.mapActivity.MapsActivity;

public class CrearUsuarioActivity extends AppCompatActivity implements AsyncTaskCompleteListener {

    //info del usuario
    private EditText userInfo;
    private EditText passwordInfo;
    private Button botonCrearUsuario;
    private Button botonSelectMapa;

    private String prueba;

    private static final String SERVER_IP = "192.168.1.51"; // Ip a conectar
    private static final int SERVER_PORT = 7777; // Puerto a conectarse en la IP asignada

    private String latitudMapa,longitudMapa;

    private TextView textFieldAlert;

    static final int REQUEST_CODE_ACTIVIDAD_B = 1;

    //referencia a la clase
    private final CrearUsuarioActivity referenciaPropiaClase = this;

    private boolean isBound = false;
    /*public Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            if (msg.what == 1){

                String mesage = (String) msg.obj;
                Log.d("MensajeServer",mesage);

            }

            return false;
        }
    });*/

    /**
     * Método encargado de cargar el diseño de la Activity y diferentes comportamientos
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario);

        /*Intent intetSocketServices = new Intent(this,SocketServices.class);
        bindService(intetSocketServices,connection,Context.BIND_AUTO_CREATE);*/

        //prueba
        Intent intent = getIntent();

        if (intent.hasExtra("latitud")){

            String mensaje = intent.getStringExtra("latitud");

            Log.d("Prueba",mensaje);

        }

        //funcionamiento de la creacion de usuario
        administrarRegistro();

    }

    /**
     * Método que asigna un comportamiento al boton de crear usuario y obtiene la información
     * almacenada en los textField enviando la información al servidor
     */
    private void administrarRegistro() {

        userInfo = findViewById(R.id.crearUsuarioField);
        passwordInfo = findViewById(R.id.crearContraField);
        botonCrearUsuario = findViewById(R.id.buttonRegistrarUsuario);
        textFieldAlert = findViewById(R.id.textView4);
        botonSelectMapa = findViewById(R.id.selectMapUbication);



        //boton al ser presionado
        botonCrearUsuario.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //ontener informacion de los textfield
                String infoUser = userInfo.getText().toString();
                String infoContra = passwordInfo.getText().toString();

                //si el usuario no tiene caracteres
                if (infoUser.isEmpty()) {
                    textFieldAlert.setText("Usuario Incorrecto");
                    return;
                }
                //si la contraseña no tiene caracteres
                else if (infoContra.isEmpty()) {
                    textFieldAlert.setText("Contraseña Incorrecta");
                    return;
                }

                //si no ha elegido ubicación la persona
                else if (latitudMapa == null) {
                    textFieldAlert.setText("Debe elegir ubicación");
                    return;
                }

                //limpiar el area de texto y enviar el mensaje al servidor
                textFieldAlert.setText("");

                LoginTask loginTask = new LoginTask(referenciaPropiaClase);
                loginTask.setAccion("crearUsuario");
                loginTask.execute(infoUser,infoContra,latitudMapa,longitudMapa);



                Mensaje mensaje = new Mensaje();
                mensaje.setAccion("Hola");

                Log.d("BotonCrearUsuario", "Acción realizada");
                /*//conexion servidor
                socketServices.connectToServer(SERVER_IP,SERVER_PORT);
                //enviar mensaje
                socketServices.sendMessageToServer(mensaje);*/

            }
        });

        //al presionar el boton abre una activity maps para seleccionar una ubicación en el mapa
        botonSelectMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                //intent para abrir el activity
                Intent intent = new Intent(CrearUsuarioActivity.this, MapsActivity.class);


                //iniicar el ativity
                startActivityForResult(intent,REQUEST_CODE_ACTIVIDAD_B);



            }
        });

    }

    /**
     * método que almacena la respuesta del server del LoginTask
     * @param newValue respuesta del servidor
     */
    public void onTaskComplete(String newValue) {

        prueba = newValue;
        Log.d("Prueba","New Text:" + newValue);

    }

    /**
     * Metodo que almacena la información requerida del activity maps para enviar la ubicación
     * elegida al servidor
     * @param requestCode The integer request code originally supplied to
     *                    startActivityForResult(), allowing you to identify who this
     *                    result came from.
     * @param resultCode The integer result code returned by the child activity
     *                   through its setResult().
     * @param data An Intent, which can return result data to the caller
     *               (various data can be attached to Intent "extras").
     *
     */
    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data){

        super.onActivityResult(requestCode,resultCode,data);

        if (requestCode == REQUEST_CODE_ACTIVIDAD_B){

            if (resultCode == RESULT_OK) {

                //obtener el string de retorno
                String latitud = data.getStringExtra("latitud");
                String longitud = data.getStringExtra("longitud");

                ///asignar la latitud y longitud a variables locales
                latitudMapa = latitud;
                longitudMapa = longitud;

                Log.d("Result Prueba: ", latitud + "   " + longitud);

            }

        }

    }

    /**
     * Permite recibir y responder mensajes desde otras aplicaciones
     */
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Mensaje mensaje = (Mensaje) intent.getSerializableExtra("mensaje");

            if (mensaje != null) {

                Log.d("MensajeServer", "Mensaje server: " + mensaje);

            }

        }
    };

    /**
     * Si se esta ejecutando el activity
     */
    @Override
    protected void onResume() {

        super.onResume();
        IntentFilter filter = new IntentFilter("mensajePrueba");
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);

    }

    /**
     * Si el activity esta en pausa
     */
    @Override
    protected void onPause() {

        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);

    }

    /**
     * Si se presiona el boton para retroceder
     */
    //al presionar el boton para volver, abre el menu principal
    @Override
    public void onBackPressed() {
        //intent para abrir la activity
        super.onBackPressed();
        Intent intent = new Intent(this, LoginActivity.class);

        //iniciar la activity
        startActivity(intent);

        finish();

    }

    /**
     * Si se destruye el activity
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isBound) {

            //unbindService(connection);
            isBound = false;

        }
    }

}
