package com.ss.schedule.io;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;

public class InputOutputXml<E> implements InputOutput<E> {

	private TypeReference<E> typeReference;

	public InputOutputXml(TypeReference<E> typeReference) {
		this.typeReference = typeReference;
	}

	@Override
	public E readFromFile(String filePath) {
		E objects = null;

		try {
			ObjectMapper xmlMapper = new XmlMapper();
			objects = xmlMapper.readValue(new File(filePath), typeReference);
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return objects;
	}

	@Override
	public void writeToFile(String filePath, E objects) {
		try {
			ObjectMapper mapper = new XmlMapper();
			mapper.writeValue(new File(filePath), objects);
		} catch (IOException ex) {
			// TODO
			ex.printStackTrace();
		}
	}
}
