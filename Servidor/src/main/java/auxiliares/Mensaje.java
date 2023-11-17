package auxiliares;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement
public class Mensaje {

    private String accion;
    private ArrayList<NodoMapa> listaNodosMapa;
    private Empleado empleado;

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public ArrayList<NodoMapa> getListaNodosMapa() {
        return listaNodosMapa;
    }

    public void setListaNodosMapa(ArrayList<NodoMapa> listaNodosMapa) {
        this.listaNodosMapa = listaNodosMapa;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }



}
