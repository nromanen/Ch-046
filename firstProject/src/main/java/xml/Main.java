package xml;

/**
 * Created by rmochetc on 17.11.2016.
 */
public class Main {

    public static void main(String[] args) {
        Employee employee = new Employee();

        employee.setName("TestXml");
        employee.setAge(25);
        employee.setGender("Male");
        employee.setId(666);
        employee.setRole("Java Developer");
        employee.setPassword("test");

        XmlUtils.jaxbObjectToXML(employee, "test.xml");

        System.out.println(employee);
        System.out.println();

        Employee eemployee1 = XmlUtils.jaxbXMLToObject("test.xml");

        System.out.println(eemployee1);
    }

}
