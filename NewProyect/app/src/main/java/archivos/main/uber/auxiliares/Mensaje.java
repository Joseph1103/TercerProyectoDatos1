package archivos.main.uber.auxiliares;

import java.io.Serializable;
import java.util.ArrayList;
public class Mensaje implements Serializable {

    private String accion;
    private ArrayList<NodoMapa> listaNodosMapa;
    private Empleado empleado;

    /**
     * obtener acción
     * @return acción
     */
    public String getAccion() {
        return accion;
    }

    /**
     * asignar accion
     * @param accion nueva accion
     */
    public void setAccion(String accion) {
        this.accion = accion;
    }

    /**
     * obtener lista de nodos
     * @return lista nodos
     */
    public ArrayList<NodoMapa> getListaNodosMapa() {
        return listaNodosMapa;
    }

    /**
     * asignar lista nodos
     * @param listaNodosMapa nueva lista
     */
    public void setListaNodosMapa(ArrayList<NodoMapa> listaNodosMapa) {
        this.listaNodosMapa = listaNodosMapa;
    }

    /**
     * obtener el empleado
     * @return empleado
     */
    public Empleado getEmpleado() {
        return empleado;
    }

    /**
     * asignar elmpleado
     * @param empleado nuevo empleado
     */
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
}
