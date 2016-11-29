package com.ss.schedule.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class InputOutputXml<T> implements InputOutput<T> {
	TypeReference<T> typeReference;

	public InputOutputXml(TypeReference<T> typeReference) {
		this.typeReference = typeReference;
	}

	@Override
	public void writeToFile(String path, T t) {
		try {
			XmlMapper xmlMapper = new XmlMapper();
			xmlMapper.writerWithDefaultPrettyPrinter().writeValue(new File(path), t);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public T readFromFile(String path) {
		XmlMapper xmlMapper = new XmlMapper();
	        File file = new File(path);
	        T t=null;
	        try {
	            t = xmlMapper.readValue(new FileInputStream(file), typeReference);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        return t;
	}

}
