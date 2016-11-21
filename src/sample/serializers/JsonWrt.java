package sample.serializers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import sample.entities.TimeTable;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by oleg on 17.11.16.
 */
public class JsonWrt<T> implements Serializabl<T> {
    TypeReference<T> typeReference;



    public JsonWrt(TypeReference<T> typeReference) {
        this.typeReference=typeReference;
    }

    @Override
    public void write(String path, T t) {
        File file = new File(path);

        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new FileOutputStream(
                    file), t);
        } catch (IOException e) {
            e.printStackTrace();
        }





    }

    @Override
    public T read(String path) {
        File file = new File(path);
        T t=null;
        if (file.exists()) System.out.println("exists");
        else System.out.println("not");
        ObjectMapper mapper = new ObjectMapper();
        try {
           t=mapper.readValue(new FileInputStream(file),
                   typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return t;
    }

}
