package auxiliares;

import auxiliares.Empleado;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "empleados")
public class EmpleadoListWrapper {

    private List<Empleado> employees;

    @XmlElement(name = "empleado")
    public List<Empleado> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Empleado> employees) {
        this.employees = employees;
    }

    public EmpleadoListWrapper() {
    }

    public EmpleadoListWrapper(List<Empleado> employees) {
        this.employees = employees;
    }
}
