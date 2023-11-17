package auxiliares;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XMLHandler {

    public static void appendToXML(Empleado newEmpleado, String filePath) throws JAXBException {
        File file = new File(filePath);
        List<Empleado> employees = new ArrayList<>();

        // If the file exists, unmarshal its content into a list
        if (file.exists()) {
            employees = unmarshalListFromXML(filePath);
        }

        // Add the new employee to the list
        employees.add(newEmpleado);

        // Marshal the updated list to the XML file
        JAXBContext context = JAXBContext.newInstance(EmpleadoListWrapper.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(new EmpleadoListWrapper(employees), file);
    }

    public static List<Empleado> unmarshalListFromXML(String filePath) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(EmpleadoListWrapper.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        EmpleadoListWrapper wrapper = (EmpleadoListWrapper) unmarshaller.unmarshal(new File(filePath));
        return wrapper.getEmployees();
    }
}
