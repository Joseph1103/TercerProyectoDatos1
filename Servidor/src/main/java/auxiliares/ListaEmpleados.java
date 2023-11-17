package auxiliares;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class ListaEmpleados {

    private List<Empleado> empleados;

    public ListaEmpleados() {
        this.empleados = new ArrayList<>();
    }

    public ListaEmpleados(Empleado empleado) {
        this.empleados = new ArrayList<>();
        this.empleados.add(empleado);
    }

    @XmlElement(name = "empleado")
    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public void addEmpleado(Empleado empleado) {
        this.empleados.add(empleado);
    }
}
