package archivos.main.uber.auxiliares;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class NodoMapa implements Serializable {

    private double latitud;
    private double longitud;
    private int pesoRuta;
    private String nombreMarcador;

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public int getPesoRuta() {
        return pesoRuta;
    }

    public void setPesoRuta(int pesoRuta) {
        this.pesoRuta = pesoRuta;
    }

    public String  getNombreMarcador() {
        return nombreMarcador;
    }

    public void setNombreMarcador(String  nombreMarcador) {
        this.nombreMarcador = nombreMarcador;
    }

}
