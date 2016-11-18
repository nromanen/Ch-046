package FirstProject;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.core.type.TypeReference;

public class ObjectToXML implements InputOutput<Subject>{
	@XmlRootElement(name = "subjects")
	@XmlAccessorType(XmlAccessType.FIELD)
    private static class Subjects {
		@XmlElement(name = "subject") 
        private List<Subject> subjects;
        public Subjects() {
        }

        public List<Subject> getSubjects() {
            return subjects;
        }

        public void setSubjects(List<Subject> subjects) {
            this.subjects = subjects;
        }
    }
	@Override
	public List<Subject> read(String filePath) {
		Teacher teacher= new Teacher();
		try {
        JAXBContext context = JAXBContext.newInstance(Teacher.class);
        Unmarshaller un = context.createUnmarshaller();
        teacher = (Teacher) un.unmarshal(new File(filePath));
        
    } 
catch (JAXBException e) {
        e.printStackTrace();
    }
	return teacher.getList();
}


	@Override
	public boolean write(List<Subject> list, String filePath) {
		try {
			Subjects sub = new Subjects();
			sub.setSubjects(list);
            JAXBContext context = JAXBContext.newInstance(Subjects.class);
            		
            Marshaller m = context.createMarshaller();
            //for pretty-print XML in JAXB
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(sub, new File(filePath));
        } catch (JAXBException e) {
            e.printStackTrace();
        }

		return true;
	}

}
