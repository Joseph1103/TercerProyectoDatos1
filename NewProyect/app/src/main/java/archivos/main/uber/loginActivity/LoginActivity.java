package archivos.main.uber.loginActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import archivos.main.uber.R;
import archivos.main.uber.crearUsuarioActivity.CrearUsuarioActivity;

public class LoginActivity extends AppCompatActivity {

    /**
     * Metodo que se llama al crear la activity
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        elementosApp();

    }


    //informacion del usuario
    private EditText userInfo;
    private EditText passwordInfo;

    private TextView textPrueba;

    //botones
    private Button botonIniciarSesion;
    private Button botonCrearUsuario;


    /**
     * carga la información del archivo xml de diseño y asigna funcionamiento a los botones
     */
    private void elementosApp(){

        userInfo = findViewById(R.id.userTextField);
        passwordInfo = findViewById(R.id.passwordTextField);

        textPrueba = findViewById(R.id.pruebaText);

        //comportamiento del boton
        botonIniciarSesion = findViewById(R.id.loginButton);
        botonIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //comportamiento del boton crear cuenta
        botonCrearUsuario = findViewById(R.id.crearUsuarioButton);
        botonCrearUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //intent para intentar abrir el activity
                Intent intent = new Intent(LoginActivity.this, CrearUsuarioActivity.class);

                //iniciar el activity
                startActivity(intent);

            }
        });

    }

    /**
     * minimizar la app al presionar el boton de retroceder
     */
    @Override
    public void onBackPressed() {

        super.onBackPressed();
        moveTaskToBack(true);

    }


}