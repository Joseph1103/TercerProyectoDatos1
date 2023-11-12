package auxiliares;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XMLHandler {

    public static void marshalToXML(Empleado empleado, String filePath) throws JAXBException {
        try {
            List<Empleado> empleados = getAllEmpleados(filePath);

            empleados.add(empleado);

            JAXBContext context = JAXBContext.newInstance(EmpleadoListWrapper.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            EmpleadoListWrapper wrapper = new EmpleadoListWrapper();
            wrapper.setEmpleados(empleados);

            marshaller.marshal(wrapper, new File(filePath));
        } catch (JAXBException e) {
            e.printStackTrace();
            throw e; // Propagate the exception to the caller
        }
    }

    public static List<Empleado> getAllEmpleados(String filePath) throws JAXBException {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return new ArrayList<>();
            }

            JAXBContext context = JAXBContext.newInstance(EmpleadoListWrapper.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            EmpleadoListWrapper wrapper = (EmpleadoListWrapper) unmarshaller.unmarshal(file);

            return wrapper.getEmpleados();
        } catch (JAXBException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
