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

    /**
     * obtener latitud
     * @return latitud
     */
    public double getLatitud() {
        return latitud;
    }

    /**
     * asignar latitud
     * @param latitud nueva latitud
     */
    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    /**
     * obtener longitud
     * @return longitud
     */
    public double getLongitud() {
        return longitud;
    }

    /**
     * asignar longitud
     * @param longitud
     */
    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    /**
     * obtener peso ruta
     * @return peso
     */
    public int getPesoRuta() {
        return pesoRuta;
    }

    /**
     * asignar peso
     * @param pesoRuta nuevo peso
     */
    public void setPesoRuta(int pesoRuta) {
        this.pesoRuta = pesoRuta;
    }

    /**
     * obtener nombre marcador
     * @return marcador
     */
    public String  getNombreMarcador() {
        return nombreMarcador;
    }

    /**
     * asignar marcador
     * @param nombreMarcador nuevo marcador
     */
    public void setNombreMarcador(String  nombreMarcador) {
        this.nombreMarcador = nombreMarcador;
    }

}
