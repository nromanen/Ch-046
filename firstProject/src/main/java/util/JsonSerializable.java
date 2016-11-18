package util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Classroom;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Admin on 17.11.16.
 */
public class JsonSerializable implements MySerializable<Classroom> {


    // write list of rooms to JSON file
    @Override
    public boolean write (List<Classroom> classrooms, String fileName) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            try {

                mapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileName),classrooms);

               //
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch (Exception  e) {
            e.printStackTrace();
        }

        return true;
    }

    // read list of rooms from JSON file
    @Override
    public List<Classroom> read(String fileName){
        
        ObjectMapper mapper = new ObjectMapper();
        try {
            return  (List) mapper.readValue(new File(fileName), new TypeReference<List<Classroom>>(){});

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }



}
