package auxiliares;

import auxiliares.Empleado;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
/**
 * La clase EmpleadoListWrapper se utiliza como un contenedor para una lista de empleados
 * y se utiliza para la serialización XML.
 */
@XmlRootElement(name = "empleados")
public class EmpleadoListWrapper {

    private List<Empleado> employees;
    /**
     * Obtiene la lista de empleados.
     *
     * @return La lista de empleados.
     */
    @XmlElement(name = "empleado")
    public List<Empleado> getEmployees() {
        return employees;
    }
    /**
     * Establece la lista de empleados.
     *
     * @param employees La nueva lista de empleados.
     */
    public void setEmployees(List<Empleado> employees) {
        this.employees = employees;
    }
    /**
     * Constructor predeterminado de EmpleadoListWrapper.
     */
    public EmpleadoListWrapper() {
    }
    /**
     * Constructor de EmpleadoListWrapper con una lista de empleados.
     *
     * @param employees La lista de empleados a encapsular.
     */
    public EmpleadoListWrapper(List<Empleado> employees) {
        this.employees = employees;
    }
}
