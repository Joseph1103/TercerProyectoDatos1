package auxiliares;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XMLHandler {

    public static void appendToXML(Empleado newEmpleado, String filePath) throws JAXBException {
        File file = new File(filePath);
        Empleado existingEmpleado = null;

        // If the file exists, unmarshal its content into an object
        if (file.exists()) {
            existingEmpleado = unmarshalFromXML(filePath);
        } else {
            // If the file doesn't exist, create a new Empleado object
            existingEmpleado = new Empleado();
        }

        // Add the new data to the existing object
        existingEmpleado.setCarnetUsuario(newEmpleado.getCarnetUsuario());
        existingEmpleado.setPasswordUsuario(newEmpleado.getPasswordUsuario());

        // Marshal the updated object to the XML file
        JAXBContext context = JAXBContext.newInstance(Empleado.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(existingEmpleado, file);
    }

    public static Empleado unmarshalFromXML(String filePath) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Empleado.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (Empleado) unmarshaller.unmarshal(new File(filePath));
    }
}
