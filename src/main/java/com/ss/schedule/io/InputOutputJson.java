package com.ss.schedule.io;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class InputOutputJson<E> implements InputOutput<E> {

	private TypeReference<E> typeReference;

	public InputOutputJson(TypeReference<E> typeReference) {
		this.typeReference = typeReference;
	}

	@Override
	public E readFromFile(String filePath) {
		E objects = null;

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objects = objectMapper.readValue(new File(filePath),  typeReference);
		} catch (IOException ex) {
			// TODO
			ex.printStackTrace();
		}

		return objects;
	}

	@Override
	public void writeToFile(String filePath, E objects) {
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), objects);
		} catch (IOException ex) {
			//TODO
			ex.printStackTrace();
		}
	}
}
