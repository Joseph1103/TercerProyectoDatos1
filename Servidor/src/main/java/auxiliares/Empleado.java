package auxiliares;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Empleado {

    private String carnetUsuario;
    private String passwordUsuario;

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
}
