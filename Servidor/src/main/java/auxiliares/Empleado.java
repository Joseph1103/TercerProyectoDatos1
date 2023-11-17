package auxiliares;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Empleado {

    private String carnetUsuario;
    private String passwordUsuario;
    private String latitud;
    private String longitud;

    @XmlElement
    public String getCarnetUsuario() {
        return carnetUsuario;
    }

    public void setCarnetUsuario(String carnetUsuario) {
        this.carnetUsuario = carnetUsuario;
    }

    @XmlElement
    public String getPasswordUsuario() {
        return passwordUsuario;
    }

    public void setPasswordUsuario(String passwordUsuario) {
        this.passwordUsuario = passwordUsuario;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
}
