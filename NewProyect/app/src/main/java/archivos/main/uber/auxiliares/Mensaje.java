package archivos.main.uber.auxiliares;

import java.io.Serializable;
import java.util.ArrayList;
public class Mensaje implements Serializable {

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
