package com.example.demo;

import java.io.IOException;

import org.apache.hadoop.hdfs.server.namenode.status_jsp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.Region;

@Controller
public class ProfileController {

	
	@Value("#{environment.key}")
	String key;
	@Value("#{environment.password}")
	String password;
	
	private static final CannedAccessControlList CannedAccesContr = null;
//	@Value("#{systemProperties['key']}")
//	private String key;
	
	 

	
	@GetMapping(value="/")
	public ModelAndView renderPage()
	{
		ModelAndView indexPage = new ModelAndView();
		indexPage.setViewName("index");
	
	return indexPage;
	}
	
	@PostMapping(value ="/upload")
	public ModelAndView uploadTos3 (@RequestParam("file") MultipartFile image )
	{  //"AKIAJEGSZVVD725XQGJA"
		
	//key=System.getenv("key");
	
	//password=System.getenv("password");
	

		ModelAndView profilePage = new ModelAndView();
		profilePage.setViewName("index");
		 System.out.println("##################33"+key);
		BasicAWSCredentials cred = new BasicAWSCredentials(key,password);
	//	AmazonS3Client s3client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(cred)).withRegion(Regions.US_EAST_2).build();
			
			
		AmazonS3 s3client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(cred)).withRegion(Regions.US_EAST_2).build();

		
		try {
			PutObjectRequest putReq = new PutObjectRequest("ameypalav",image.getOriginalFilename(), image.getInputStream(),new ObjectMetadata()).withCannedAcl(CannedAccessControlList.PublicRead);
	
		s3client.putObject(putReq);
		
		String imgSrc = "http://"+"ameypalav"+".s3.amazonaws.com/"+image.getOriginalFilename();
		profilePage.addObject("imgSrc", imgSrc);
		profilePage.setViewName("profile");
		
		return profilePage;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			profilePage.setViewName("/error");
			return profilePage;	
		}
		
	}
	//private static final String PATH = "/error";
//	@RequestMapping(value = "/error")
//	   public String ErrorPage() {
//	      return "error";
//	   }
//	@Override
//	public String getErrorPath() {
//        return PATH;
//    }
//	
}
