package io;

/**
 * Created by vnovohatskyy on 22.11.16.
 */

public interface InputOutput <T>{
    void  writeToFile(String path,T t);
    T readFromFile(String path);
}
