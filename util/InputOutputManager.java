package util;

import java.util.List;

public interface InputOutputManager<T> {

	void writeToFile(List<T> list, String filePath);

	List<T> readFromFile(String filePath);

}
