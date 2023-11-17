package auxiliares;

import java.io.Serializable;
/**
 * La clase NodoMapa representa un nodo en un mapa y implementa la interfaz Serializable
 * para permitir la serialización de objetos.
 */
public class NodoMapa implements Serializable {

    private double latitud;
    private double longitud;
    private int pesoRuta;
    private String nombreMarcador;
    /**
     * Obtiene la latitud del nodo en el mapa.
     *
     * @return La latitud del nodo.
     */
    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }
    /**
     * Establece la latitud del nodo en el mapa.
     *
     * @param latitud La nueva latitud del nodo.
     */
    public double getLongitud() {
        return longitud;
    }
    /**
     * Establece la longitud del nodo en el mapa.
     *
     * @param longitud La nueva longitud del nodo.
     */
    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
    /**
     * Obtiene el peso de la ruta asociada al nodo.
     *
     * @return El peso de la ruta del nodo.
     */
    public int getPesoRuta() {
        return pesoRuta;
    }
    /**
     * Establece el peso de la ruta asociada al nodo.
     *
     * @param pesoRuta El nuevo peso de la ruta del nodo.
     */
    public void setPesoRuta(int pesoRuta) {
        this.pesoRuta = pesoRuta;
    }
    /**
     * Obtiene el nombre del marcador asociado al nodo.
     *
     * @return El nombre del marcador del nodo.
     */
    public String  getNombreMarcador() {
        return nombreMarcador;
    }
    /**
     * Establece el nombre del marcador asociado al nodo.
     *
     * @param nombreMarcador El nuevo nombre del marcador del nodo.
     */
    public void setNombreMarcador(String  nombreMarcador) {
        this.nombreMarcador = nombreMarcador;
    }
}
