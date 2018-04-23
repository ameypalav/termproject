package com.example.demo.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.demo.Model.Friend;
import com.example.demo.Model.User;
import com.example.demo.Services.FriendService;
import com.example.demo.Services.UserService;
@SessionAttributes("user")
@Controller
public class LoginController {

	@Autowired
private UserService userService;
	
private FriendService friendServie;	
@Value("#{environment.key}")
String key;
@Value("#{environment.password}")
String password;
	
	@GetMapping(value="/")
	public ModelAndView renderPage()
	{
		ModelAndView index = new ModelAndView();
		index.setViewName("index");
	System.out.print("#########heyyyyyyyyyyyy");
	return index;
	}
	
	@ModelAttribute("user")
	public User setUpUserForm() {
	   return new User();
	}
	
	
	@PostMapping(value="/facebookRedirect")
	public ModelAndView handleRedirectPage(
			@RequestParam(name="myId") long myid,
			@RequestParam(name="myName") String myName,
			@RequestParam(name="myFriends") String myFriends,
			@RequestParam(name="myEmail") String myEmail,
			HttpServletRequest req,
			@ModelAttribute User user
			
			) {
		User u=new User();
		
	
		String id=String.valueOf(myid);
		System.out.println("i am here");
		
		if(userService.findByUserid(id)!=null)
		{
			User use=userService.findByUserid(id);
			System.out.print("kjkldjsakldjasljd"+use.getProfileLink());
			
			ModelAndView index = new ModelAndView();
			index.addObject("use",use);
			index.setViewName("profile");	
			
			return index;
			
		}
		
		
		else {
		//long my=Integer.parseInt(myid);
		u.setUserid(id);
		u.setEmail(myEmail);
		u.setUsername(myName);
		user.setUserid(id);
		String[] splitted=myFriends.split("/");
		
		ModelAndView index2 = new ModelAndView();
		index2.addObject("splitted",splitted);
		
		
		for(int i=0;i<splitted.length;i++) {
			System.out.println("firends"+splitted[i]);
			//System.out.println("firends2"+split[i]);
			
			System.out.println("sdas"+i);
			}

		//u.getFriends().add(arg0)
		//String k="";
		
	u.setFriend(myFriends);
		
		



//u.setFriend(k);

		
		//System.out.println("#############################3"+u.getFriends());
		userService.saveMyUser(u);
		
	
	return new ModelAndView("register");
	}
	}
	
	@PostMapping(value ="/upload")
	public ModelAndView uploadTos3 (@RequestParam("file") MultipartFile image,@ModelAttribute User user,@RequestParam(name="details") String detail )
	{  //"AKIAJEGSZVVD725XQGJA"
		
	//key=System.getenv("key");
	
	//password=System.getenv("password");
	String k=user.getUserid();
	User r=userService.findByUserid(k);
	System.out.println("%%%%%5Friends"+r.getFriend());
	User use= userService.findByUserid(k);
	

		ModelAndView profilePage = new ModelAndView();
		profilePage.setViewName("index");
		 System.out.println("##################33"+key);
		BasicAWSCredentials cred = new BasicAWSCredentials(key,password);
	//	AmazonS3Client s3client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(cred)).withRegion(Regions.US_EAST_2).build();
			
			
		AmazonS3 s3client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(cred)).withRegion(Regions.US_EAST_2).build();

		
		try {
			PutObjectRequest putReq = new PutObjectRequest("termproject69",image.getOriginalFilename(), image.getInputStream(),new ObjectMetadata()).withCannedAcl(CannedAccessControlList.PublicRead);
	
		s3client.putObject(putReq);
		
		String imgSrc = "http://"+"termproject69"+".s3.amazonaws.com/"+image.getOriginalFilename();
		//System.out.println("Friends!!!!!!!!!!!! String"+user.getFriend());
		use.setProfileLink(imgSrc);
		String k1=r.getFriend();
		use.setFriend(k1);
		use.setDetails(detail);
	//	use.setDesc(desc);
		userService.saveMyUser(use);
		profilePage.addObject("use", use);
		profilePage.setViewName("profile");
		
		return profilePage;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			profilePage.setViewName("/error");
			return profilePage;	
		}
		
	}
	
	


}

