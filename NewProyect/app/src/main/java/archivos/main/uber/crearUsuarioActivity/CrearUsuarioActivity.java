package archivos.main.uber.crearUsuarioActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import archivos.main.uber.auxiliares.LoginTask;
import archivos.main.uber.auxiliares.SocketService;
import archivos.main.uber.loginActivity.LoginActivity;
import archivos.main.uber.R;

public class CrearUsuarioActivity extends AppCompatActivity{

    //info del usuario
    private EditText userInfo;
    private EditText passwordInfo;
    private Button botonCrearUsuario;

    private TextView textFieldAlert;

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

        //habilitar la escucha de una respuesta del servidor con respecto a la creación de usuario
        iniciarEscuchaServer();

        //funcionamiento de la creacion de usuario
        administrarRegistro();

    }

    /**
     * Metodo que inicia la escucha del dispositivo a una respuesta del server
     */
    public void iniciarEscuchaServer(){


        startService(new Intent(this, SocketService.class));

    }

    /**
     * Método que asigna un comportamiento al boton de crear usuario y obtiene la información
     * almacenada en los textField enviando la información al servidor
     */
    private void administrarRegistro(){

        userInfo = findViewById(R.id.crearUsuarioField);
        passwordInfo = findViewById(R.id.crearContraField);
        botonCrearUsuario = findViewById(R.id.buttonRegistrarUsuario);
        textFieldAlert = findViewById(R.id.textView4);

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

                //limpiar el area de texto y enviar el mensaje al servidor
                textFieldAlert.setText("");
                LoginTask loginTask = new LoginTask();
                loginTask.execute("crearUsuario",infoUser,infoContra);


            }
        });

    }



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

}
