package auxiliares;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
/**
 * La clase ListaEmpleados se utiliza como un contenedor para una lista de empleados
 * y se utiliza para la serialización XML.
 */
@XmlRootElement
public class ListaEmpleados {

    private List<Empleado> empleados;
    /**
     * Constructor predeterminado de ListaEmpleados.
     * Inicializa la lista de empleados como una nueva instancia de ArrayList.
     */
    public ListaEmpleados() {
        this.empleados = new ArrayList<>();
    }
    /**
     * Constructor de ListaEmpleados con un empleado inicial.
     * Inicializa la lista de empleados como una nueva instancia de ArrayList y agrega el empleado proporcionado.
     *
     * @param empleado El empleado inicial a agregar a la lista.
     */
    public ListaEmpleados(Empleado empleado) {
        this.empleados = new ArrayList<>();
        this.empleados.add(empleado);
    }
    /**
     * Obtiene la lista de empleados.
     *
     * @return La lista de empleados.
     */
    @XmlElement(name = "empleado")
    public List<Empleado> getEmpleados() {
        return empleados;
    }
    /**
     * Establece la lista de empleados.
     *
     * @param empleados La nueva lista de empleados.
     */
    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }
    /**
     * Agrega un empleado a la lista.
     *
     * @param empleado El empleado a agregar a la lista.
     */
    public void addEmpleado(Empleado empleado) {
        this.empleados.add(empleado);
    }
}
