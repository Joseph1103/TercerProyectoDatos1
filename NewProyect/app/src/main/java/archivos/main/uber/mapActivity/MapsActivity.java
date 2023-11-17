package archivos.main.uber.mapActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

import archivos.main.uber.R;
import archivos.main.uber.auxiliares.AsyncTaskCompleteListener;
import archivos.main.uber.auxiliares.LoginTask;
import archivos.main.uber.auxiliares.Mensaje;
import archivos.main.uber.auxiliares.NodoMapa;
import archivos.main.uber.crearUsuarioActivity.CrearUsuarioActivity;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener ,AsyncTaskCompleteListener{

    private EditText txtLatitud, txtLonguitud;
    private GoogleMap mMap;

    private final MapsActivity referenciaPropiaClase = this;

    private Random random = new Random();

    private ArrayList<NodoMapa> listaNodos;

    @Override
    protected void onCreate(Bundle savedInstanteState){

        super.onCreate(savedInstanteState);
        setContentView(R.layout.activity_maps);

        txtLatitud = findViewById(R.id.txtLatitud);
        txtLonguitud = findViewById(R.id.txtLonguitud);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapa);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        //objeto google maps
        mMap = googleMap;

        //establecer una zona fija donde se generarán los nodos
        LatLngBounds zonaFija = new LatLngBounds(
                new LatLng(9.818813335100824, -84.31611066808877),
                new LatLng(10.03998298080924, -83.86910447044677)

        );


        //centrar la cámara en la zona designada
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(zonaFija,0));

        //tener cierto zoom en la zona designada
        mMap.setMinZoomPreference(10.0f);

        //acciones para interactuar con el mapa
        this.mMap.setOnMapClickListener(this);
        this.mMap.setOnMapLongClickListener(this);

        //llamar y obtener los marcadores del servidor
        crearMarcadoresMapa();

        //marcador central auxiliar
        LatLng costaRica = new LatLng(9.866519875807688, -83.91721256313163);
        mMap.addMarker(new MarkerOptions().position(costaRica).title("Tiquicia"));


        //minimo arriba izquierda 10.034185901361647, -84.26286620316812
        //minimo abajo derecha 9.817071909307883, -83.88177796487435


        /*for (int i = 0; i < 30;i++){

            double muestra1 = generarNumRandom(10.034185901361647,9.817071909307883);
            double muestra2 = generarNumRandom(-84.26286620316812,-83.88177796487435);

            LatLng prueba = new LatLng(muestra1,muestra2);
            mMap.addMarker(new MarkerOptions().position(prueba).title("Persona"));

        }*/

        //LatLng prueba = new LatLng(muestra1,muestra2);
        //mMap.addMarker(new MarkerOptions().position(prueba).title("Persona"));

        //accion al presionar un marcador de google maps
        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {

                //marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                //tratar de enviar un mensaje a otro activity
                Intent intent = new Intent(MapsActivity.this, CrearUsuarioActivity.class);

                //agregar string como extra al intent
                /*intent.putExtra("latitud", String.valueOf(txtLatitud));

                startActivity(intent);*/

                String latitud = String.valueOf(marker.getPosition().latitude);
                String longuitud = String.valueOf(marker.getPosition().longitude);
                intent.putExtra("latitud",latitud);
                intent.putExtra("longitud",longuitud);

                setResult(RESULT_OK,intent);
                finish();

                return true;
            }
        });


        /*double muestra3 = generarNumRandom(9.893567798775242,9.820504808894489);
        double muestra4 = generarNumRandom(-83.98034107148003,-83.85296833597823);

        LatLng prueba2 = new LatLng(muestra3,muestra4);
        mMap.addMarker(new MarkerOptions().position(prueba2).title("Chirig"));*/

        //Log.d("Coordenadas","latitud: " + muestra1 + "  " + "longuitud: " + muestra2);
        //Log.d("Coordenadas","latitud: " + muestra3 + "  " + "longuitud: " + muestra4);

        /*LatLng start = new LatLng(9.846736526082923, -83.91678990503631);
        LatLng end = new LatLng(9.85527761099618, -83.88271512378292);

        mMap.addMarker(new MarkerOptions().position(start).title("start"));
        mMap.addMarker(new MarkerOptions().position(end).title("end"));*/


    }

    private void crearMarcadoresMapa(){

        LoginTask loginTask = new LoginTask(referenciaPropiaClase);
        loginTask.setAccion("solicitarNodosMapa");
        loginTask.execute();

    }

    private void generarMarcadores(){

        for (int i = 0; i < listaNodos.size();i++){

            LatLng latLng = new LatLng(listaNodos.get(i).getLatitud(),listaNodos.get(i).getLongitud());
            mMap.addMarker(new MarkerOptions().position(latLng).title(listaNodos.get(i).getNombreMarcador()));

        }

    }


    @Override
    public void onMapClick(@NonNull LatLng latLng) {

        txtLatitud.setText(""+latLng.latitude);
        txtLonguitud.setText(""+latLng.longitude);
    }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {

        txtLatitud.setText(""+latLng.latitude);
        txtLonguitud.setText(""+latLng.longitude);

    }

    @Override
    public void onTaskComplete(String result) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Mensaje respuestaServer = mapper.readValue(result,Mensaje.class);

            listaNodos = respuestaServer.getListaNodosMapa();

            generarMarcadores();

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        Log.d("Nodos servidor", result);

    }
}
