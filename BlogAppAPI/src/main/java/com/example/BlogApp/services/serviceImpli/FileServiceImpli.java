package com.example.BlogApp.services.serviceImpli;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.BlogApp.services.FileService;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Service
public class FileServiceImpli implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		// TODO Auto-generated method stub
		
		//get the file name
		String name = file.getOriginalFilename();
		
		String filePath=path+ File.separator+name;
		
		File f = new File(path);
		
		if(!f.exists()) {
			f.mkdir();
		}
		
		
		 //file copy
		
		Files.copy(file.getInputStream(), Paths.get(filePath));
		
		return name;
	}

	@Override
	public InputStream getResource(String path, String filename) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		String fullpath = path + File.separator + filename;
		InputStream is = new FileInputStream(fullpath); 
		return is;
	}

}
