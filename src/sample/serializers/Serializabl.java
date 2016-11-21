package sample.serializers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by oleg on 17.11.16.
 */
 interface Serializabl <T>{
    void  write(String path,T t);
     T read(String path);
}
