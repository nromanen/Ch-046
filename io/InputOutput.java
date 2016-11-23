package com.ss.schedule.io;

import java.io.File;

public interface InputOutput<E> {
	E readFromFile(File file);
	void writeToFile(File file, E objects);
}
