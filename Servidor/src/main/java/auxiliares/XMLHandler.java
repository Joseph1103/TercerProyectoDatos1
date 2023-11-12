package auxiliares;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XMLHandler {

    public static void marshalToXML(Empleado empleado, String filePath) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Empleado.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(empleado, new File(filePath));
    }

    public static Empleado unmarshalFromXML(String filePath) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Empleado.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        return (Empleado) unmarshaller.unmarshal(new File(filePath));
    }
}