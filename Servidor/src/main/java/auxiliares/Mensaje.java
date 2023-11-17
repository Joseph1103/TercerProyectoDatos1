package auxiliares;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
/**
 * La clase Mensaje se utiliza para representar un mensaje que puede contener una acción,
 * una lista de nodos de mapa y un objeto Empleado.
 */
@XmlRootElement
public class Mensaje {

    private String accion;
    private ArrayList<NodoMapa> listaNodosMapa;
    private Empleado empleado;
    /**
     * Obtiene la acción asociada al mensaje.
     *
     * @return La acción del mensaje.
     */
    public String getAccion() {
        return accion;
    }
    /**
     * Establece la acción asociada al mensaje.
     *
     * @param accion La nueva acción del mensaje.
     */
    public void setAccion(String accion) {
        this.accion = accion;
    }
    /**
     * Obtiene la lista de nodos de mapa asociada al mensaje.
     *
     * @return La lista de nodos de mapa del mensaje.
     */
    public ArrayList<NodoMapa> getListaNodosMapa() {
        return listaNodosMapa;
    }
    /**
     * Establece la lista de nodos de mapa asociada al mensaje.
     *
     * @param listaNodosMapa La nueva lista de nodos de mapa del mensaje.
     */
    public void setListaNodosMapa(ArrayList<NodoMapa> listaNodosMapa) {
        this.listaNodosMapa = listaNodosMapa;
    }
    /**
     * Obtiene el objeto Empleado asociado al mensaje.
     *
     * @return El objeto Empleado del mensaje.
     */

    public Empleado getEmpleado() {
        return empleado;
    }
    /**
     * Establece el objeto Empleado asociado al mensaje.
     *
     * @param empleado El nuevo objeto Empleado del mensaje.
     */
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }



}
