package xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by rmochetc on 17.11.2016.
 */
public class XmlUtils {

    public static void jaxbObjectToXML(Employee emp, String fileName) {
        try {
            JAXBContext context = JAXBContext.newInstance(Employee.class);
            Marshaller m = context.createMarshaller();
            //for pretty-print XML in JAXB
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // Write to System.out for debugging
            // m.marshal(emp, System.out);

            // Write to File
            m.marshal(emp, new File(fileName));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static Employee jaxbXMLToObject(String fileName) {
        try {
            JAXBContext context = JAXBContext.newInstance(Employee.class);
            Unmarshaller un = context.createUnmarshaller();
            Employee emp = (Employee) un.unmarshal(new File(fileName));
            return emp;
        }
        catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }


}
