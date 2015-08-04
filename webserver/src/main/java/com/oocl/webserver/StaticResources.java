package com.oocl.webserver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class StaticResources {
	public static byte[] get(Path path) {
		byte[] fileContents = null;
		if (Files.exists(path)) {
			try {
				fileContents = Files.readAllBytes(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fileContents;
	}
}
