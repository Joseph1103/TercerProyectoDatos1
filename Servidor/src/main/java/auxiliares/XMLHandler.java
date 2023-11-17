package auxiliares;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
/**
 * La clase XMLHandler proporciona métodos para manipular datos en formato XML
 * utilizando la tecnología JAXB (Java Architecture for XML Binding).
 */
public class XMLHandler {

    /**
     * Agrega un nuevo empleado al archivo XML especificado.
     *
     * @param newEmpleado El nuevo empleado a agregar.
     * @param filePath    La ruta del archivo XML.
     * @throws JAXBException Si hay un error durante la operación JAXB.
     */
    public static void appendToXML(Empleado newEmpleado, String filePath) throws JAXBException {
        File file = new File(filePath);
        List<Empleado> employees;

        // If the file exists, unmarshal its content into a list
        if (file.exists()) {
            employees = unmarshalListFromXML(filePath);
        } else {
            // If the file doesn't exist, create a new list
            employees = new ArrayList<>();
        }

        // Add the new employee to the list
        employees.add(newEmpleado);

        // Marshal the updated list to the XML file
        marshalListToXML(employees, filePath);
    }
    /**
     * Deserializa una lista de empleados desde un archivo XML.
     *
     * @param filePath La ruta del archivo XML.
     * @return La lista de empleados deserializada.
     * @throws JAXBException Si hay un error durante la operación JAXB.
     */
    public static List<Empleado> unmarshalListFromXML(String filePath) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(EmpleadoListWrapper.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        EmpleadoListWrapper wrapper = (EmpleadoListWrapper) unmarshaller.unmarshal(new File(filePath));
        return wrapper.getEmployees();
    }
    /**
     * Serializa una lista de empleados a un archivo XML.
     *
     * @param employees La lista de empleados a serializar.
     * @param filePath  La ruta del archivo XML.
     * @throws JAXBException Si hay un error durante la operación JAXB.
     */
    public static void marshalListToXML(List<Empleado> employees, String filePath) throws JAXBException {
        File file = new File(filePath);
        JAXBContext context = JAXBContext.newInstance(EmpleadoListWrapper.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(new EmpleadoListWrapper(employees), file);
    }
}
