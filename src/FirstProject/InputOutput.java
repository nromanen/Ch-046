package FirstProject;

import java.util.List;

public interface InputOutput <T> {
	public  List<T> read(String filePath);
	public boolean write(List<T> list, String filePath);
}
