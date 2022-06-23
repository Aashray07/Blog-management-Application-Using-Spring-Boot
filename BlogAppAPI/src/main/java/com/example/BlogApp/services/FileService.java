package com.example.BlogApp.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
//we just need this 2 methods for performing operations ona file
	String uploadImage(String path, MultipartFile file) throws IOException;
	InputStream getResource(String path, String filename) throws FileNotFoundException;
}
