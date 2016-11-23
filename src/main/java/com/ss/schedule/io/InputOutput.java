package com.ss.schedule.io;

public interface InputOutput<E> {
	E readFromFile(String filePath);
	void writeToFile(String filePath, E objects);
}
