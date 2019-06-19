package com.java.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

@Component
public class UploadUtil {
	@Autowired
	AmazonS3 s3Client;
	
	public String uploadFile(MultipartFile file) {
		String bucketName = "revature1903";
		String key = UUID.randomUUID() + "";
		s3Client.putObject(new PutObjectRequest(bucketName, key, convert(file))
				.withCannedAcl(CannedAccessControlList.PublicRead));

		S3Object object = s3Client.getObject(new GetObjectRequest(bucketName, key));
		
		return object.getObjectContent().getHttpRequest().getURI().toString();		
	}
	
	public File convert(MultipartFile file) 
	{    
	    File convFile = new File(file.getOriginalFilename());
	    try {
			convFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(convFile); 
		    fos.write(file.getBytes());
		    fos.close(); 
		    return convFile;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    return null;
	}
}
