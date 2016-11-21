package sample.serializers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import sample.entities.TimeTable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by oleg on 21.11.16.
 */
public class XmlWrt<T> implements Serializabl<T> {
    TypeReference<T> typeReference;

    public XmlWrt(TypeReference<T> typeReference) {
        this.typeReference = typeReference;
    }

    @Override
    public void write(String path, T o) {

        try {
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.writerWithDefaultPrettyPrinter().writeValue(new File(path), o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public T read(String path) {
        XmlMapper xmlMapper = new XmlMapper();
       // String s="<TimeTable><subject><name>English</name><type>LECTURE</type><courseN>3</courseN></subject><pair>FIRST</pair><oddnessOfWeek>EVEN</oddnessOfWeek><day>MONDAY</day><teacher><firstName/><lastName/></teacher><group><name/><amount>0</amount></group></TimeTable>";
        File file = new File(path);
        System.out.println(file.exists());
        //String xml = inputStreamToString(new FileInputStream(file));
        T t=null;
        try {
            t = xmlMapper.readValue(new FileInputStream("src/sample/tt.xml"), typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return t;
    }

}
