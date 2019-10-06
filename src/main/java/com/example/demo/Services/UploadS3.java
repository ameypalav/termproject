package com.example.demo.Services;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class UploadS3 {
	@Value("#{environment.key}")
	String key;
	@Value("#{environment.password}")
	String password;
	
	public String upload(String filename,InputStream fs) {
	BasicAWSCredentials cred = new BasicAWSCredentials(key,password);
	//	AmazonS3Client s3client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(cred)).withRegion(Regions.US_EAST_2).build();
			
			
		AmazonS3 s3client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(cred)).withRegion(Regions.US_EAST_2).build();

		
		
			PutObjectRequest putReq = new PutObjectRequest("ameypalav",filename, fs,new ObjectMetadata()).withCannedAcl(CannedAccessControlList.PublicRead);
	
		s3client.putObject(putReq);
		
		String fileUrl = "http://"+"ameypalav"+".s3.amazonaws.com/"+filename;
		
		return fileUrl;
	
}
}