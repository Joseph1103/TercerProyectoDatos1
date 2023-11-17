package auxiliares;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * La clase Empleado representa a un empleado y se utiliza para la serialización XML.
 */
@XmlRootElement
public class Empleado{

    private String carnetUsuario;
    private String passwordUsuario;
    private String latitud;
    private String longitud;

    /**
     * Obtiene el carné del usuario.
     *
     * @return El carné del usuario.
     */
    @XmlElement
    public String getCarnetUsuario() {
        return carnetUsuario;
    }
    /**
     * Establece el carné del usuario.
     *
     * @param carnetUsuario El nuevo carné del usuario.
     */
    public void setCarnetUsuario(String carnetUsuario) {
        this.carnetUsuario = carnetUsuario;
    }
    /**
     * Obtiene la contraseña del usuario.
     *
     * @return La contraseña del usuario.
     */
    @XmlElement
    public String getPasswordUsuario() {
        return passwordUsuario;
    }
    /**
     * Establece la contraseña del usuario.
     *
     * @param passwordUsuario La nueva contraseña del usuario.
     */
    public void setPasswordUsuario(String passwordUsuario) {
        this.passwordUsuario = passwordUsuario;
    }
    /**
     * Obtiene la latitud del empleado.
     *
     * @return La latitud del empleado.
     */
    public String getLatitud() {
        return latitud;
    }
    /**
     * Establece la latitud del empleado.
     *
     * @param latitud La nueva latitud del empleado.
     */
    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }
    /**
     * Obtiene la longitud del empleado.
     *
     * @return La longitud del empleado.
     */
    public String getLongitud() {
        return longitud;
    }
    /**
     * Establece la longitud del empleado.
     *
     * @param longitud La nueva longitud del empleado.
     */
    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
}
