package archivos.main.uber.auxiliares;

import java.io.Serializable;

public class Empleado implements Serializable {

    private String carnetUsuario;
    private String passwordUsuario;

    private String latitud;

    private String longitud;

    /**
     * obtener carnet
     * @return carnet
     */
    public String getCarnetUsuario() {
        return carnetUsuario;
    }

    /**
     * asignar carnet
     * @param carnetUsuario nuevo carnet
     */
    public void setCarnetUsuario(String carnetUsuario) {
        this.carnetUsuario = carnetUsuario;
    }

    /**
     * obtener contra
     * @return contraseña
     */
    public String getPasswordUsuario() {
        return passwordUsuario;
    }

    /**
     * asignar contraseña
     * @param passwordUsuario nueva contra
     */
    public void setPasswordUsuario(String passwordUsuario) {
        this.passwordUsuario = passwordUsuario;
    }

    /**
     * obtener latitud
     * @return latitud
     */
    public String getLatitud() {
        return latitud;
    }

    /**
     * asignar latitud
     * @param latitud nueva latitud
     */
    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    /**
     * obtener longitud
     * @return longitud
     */
    public String getLongitud() {
        return longitud;
    }

    /**
     * asignar longitud
     * @param longitud nueva longitud
     */
    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
}
