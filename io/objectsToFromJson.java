package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by vnovohatskyy on 22.11.16.
 */

public class objectsToFromJson<T> implements InputOutput<T> {
	TypeReference<T> typeReference;

	public objectsToFromJson(TypeReference<T> typeReference) {
		this.typeReference = typeReference;
	}

	@Override
	public void writeToFile(String path, T t) {
		File file = new File(path);
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(new FileOutputStream(file), t);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public T readFromFile(String path) {
		File file = new File(path);
		T t = null;
		if (file.exists())
			System.out.println("exists");
		else
			System.out.println("not");
		ObjectMapper mapper = new ObjectMapper();
		try {
			t = mapper.readValue(new FileInputStream(file), typeReference);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return t;
	}

}
