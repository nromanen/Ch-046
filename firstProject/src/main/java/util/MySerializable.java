package util;

import java.util.List;

/**
 * Created by rmochetc on 17.11.2016.
 */
public interface MySerializable<T> {

    List<T> read(String fileName);

    boolean write(List<T> list, String fileName);

}
