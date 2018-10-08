package com.example.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
import com.example.demo.Model.Notification;
import com.example.demo.Model.Post;
import com.example.demo.Model.User;
import com.example.demo.Repository.FriendRepository;
import com.example.demo.Repository.NotificationRepository;
import com.example.demo.Repository.PostRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Services.FriendService;
import com.example.demo.Services.PostService;
import com.example.demo.Services.UserService;

import ch.qos.logback.core.net.SyslogOutputStream;
@SessionAttributes("user")
@Controller
public class LoginController {

	@Autowired
private UserService userService;
	
@Autowired
private UserRepository ur;

@Autowired
private FriendRepository fr;
	
	@Autowired
	private NotificationRepository nr;

	@Autowired
	private PostService postservice;
	
@Autowired	
private FriendService friendServie;	
//@Value("#{environment.key}")
String key="AKIAJT5HBYZFTS2XJYLA";
//@Value("#{environment.password}")
String password="FN2TRq7ay2O+nFM5niWX2gq6fK3zwBpyLH//F8z1";

@Autowired
private PostRepository pr;
	
	@GetMapping(value="/")
	public ModelAndView renderPage()
	{
		ModelAndView index = new ModelAndView();
		index.setViewName("index");
	System.out.print("#########heyyyyyyyyyyyy");
	return index;
	}
	
	@GetMapping(value="/home")
	public ModelAndView renderPage2()
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
			@RequestParam(name="myId") String myid,
			@RequestParam(name="myName") String myName,
			@RequestParam(name="myFriends") String myFriends,
			@RequestParam(name="myEmail") String myEmail,
			HttpServletRequest req,
			@ModelAttribute User user
			
			) {
		User u=new User();
		
	
	//	String id=String.valueOf(myid);
		String id=myid;
		System.out.println("i am here");
		System.out.println("id"+myid);
		System.out.println("my email"+myEmail);
		
		if(myid.equalsIgnoreCase("2040582155956479"))
		{
			User u3 = new User();
			u3.setUserid(myid);
			u3.setEmail(myEmail);
u3.setDetails("Admin");
u3.setFriend("");
user.setDetails(u3.getDetails());
user.setUserid(u3.getUserid());
user.setEmail(u3.getEmail());
//userService.saveMyUser(u3);
			ModelAndView index = new ModelAndView();
			List<Post> prt= new ArrayList<Post>();
			
			prt=postservice.showAllPosts();
			for(int i=0;i< prt.size();i++)
			{
				Post p=prt.get(i);
				
			
			System.out.println("id"+p.getId());
			}
			index.addObject("post",prt);
			System.out.println("come to papa");
			index.setViewName("admin");
			return index;
			
		}
		
		else 	if(userService.findByUserid(id)!=null)
		{
			User use=userService.findByUserid(id);
			System.out.print("kjkldjsakldjasljd"+use.getProfileLink());
			
			user.setUserid(use.getUserid());
			user.setEmail(use.getEmail());
			user.setUsername(use.getUsername());
			System.out.println("idd"+user.getUserid());
			
			
			List<Notification> nfR=new ArrayList<>();
		//nfR=nr.findByUsername(use.getUserid());
			
			nfR=nr.findByUsername(use.getUserid());
			System.out.println("me ethe ahe");
			ModelAndView index = new ModelAndView();
			List<Post> prrott = new ArrayList<>();
			
			prrott=pr.findByUser(use);
			System.out.println("prottt chi value"+prrott.size());
			List<Friend> frtlist= new ArrayList<>();
			frtlist=fr.findByUser(use);
			System.out.println("frasdas"+frtlist.size());
		index.addObject("friends",frtlist);
			index.addObject("posts",prrott);
			index.addObject("notification",nfR);
			index.addObject("use",use);
			index.setViewName("profile");	
			
			return index;
			
		}
		
		
		
		else {
	//	long my=Integer.parseInt(myid);
			System.out.println("My id"+id);
			System.out.println("Friends"+myFriends);
	//		int r=(int) my;
		u.setUserid(id);
		u.setEmail(myEmail);
		u.setUsername(myName);
		user.setUserid(id);
		String[] splitted=myFriends.split("./");
		
		ModelAndView index2 = new ModelAndView();
		index2.addObject("splitted",splitted);
		
	List<Friend> fr2 = new ArrayList<Friend>();
	List<String> friend=new ArrayList<>();
	
		for(int i=0;i<splitted.length;i++) {
			System.out.println("firends"+splitted[i]);
			
			//System.out.println("firends2"+split[i]);
			Friend fr= new Friend(); 
			String kep=splitted[i].substring(0,splitted[i].indexOf(".") );
			System.out.println("My id 2 is"+kep);
			String ret=splitted[i].substring(splitted[i].indexOf("."),splitted[i].length());
			System.out.println("my name is"+ret);
			friend.add(ret);
			System.out.println("friends list"+friend.get(i));
			fr.setFriend(ret);
			fr.setFriend_id(kep);
			fr.setUser(u);
			fr2.add(fr);
			System.out.println("sdas"+i);
			}
		index2.addObject("friends",fr2);
System.out.println("kasfjklsdjfklds"+u.getUserid());
//fr.setUser_id(id);
//index2.addObject("friends",friend);
	//u.setFriend(myFriends);
	//u.setFriendslist(fr2);
u.setFrind(fr2);


		
		//System.out.println("#############################3"+u.getFriends());
		userService.saveMyUser(u);
		
	
	return new ModelAndView("register");
	}
	}
	
	@GetMapping("/show-users")
	public ModelAndView showuser() {
		ModelAndView index2 = new ModelAndView();
		List<User> urt=new ArrayList<>();
		urt=(List<User>) ur.findAll();
		System.out.println("size of list"+urt.size());
		
		index2.addObject("users",urt);
		index2.setViewName("showuser");
		System.out.println("shsajkhsa"+urt.size());
		//request.setAttribute("mode","ALL_USERS");
		return index2;
	}
	
	@RequestMapping("/show-profile")
	public ModelAndView endMyevent2(@RequestParam String id,HttpServletRequest request ) {
		//eventService.deleteMyevent(eventid);
	//	int id2=Integer.parseInt(id);
		User p=ur.findByUserid(id);
		List<Post> prtlist = new ArrayList<>();
		prtlist=pr.findByUser(p);
	

	List<Post> prt= new ArrayList<Post>();
	
	prt=postservice.showAllPosts();
	ModelAndView index7 = new ModelAndView();
	index7.addObject("use",p);
	index7.addObject("posts",prt);
	index7.setViewName("frndprof");

	
	return index7;
		
	}
	
	@RequestMapping("/delete2-post")
	public ModelAndView endMyevent(@RequestParam String id,HttpServletRequest request ) {
		//eventService.deleteMyevent(eventid);
		int id2=Integer.parseInt(id);
		Post p=pr.findById(id2);
	pr.delete(p);
	ModelAndView index = new ModelAndView();
	List<Post> prt= new ArrayList<Post>();
	
	prt=postservice.showAllPosts();
//	for(int i=0;i< prt.size();i++)
//	{
//		Post pu=prt.get(i);
//		
//	
//	System.out.println("id"+p.getId());
//	}
	index.addObject("post",prt);
	System.out.println("come to papa");
	index.setViewName("admin");
	return index;
		
	}
	
	@GetMapping("/showfrnd")
	public ModelAndView showuser2(@ModelAttribute User user) {
		ModelAndView index2 = new ModelAndView();
		User use=ur.findByUserid(user.getUserid());
		System.out.println("user name"+use.getUserid());
		List<Friend> frtlist= new ArrayList<>();
		frtlist=fr.findByUser(use);
		System.out.println("frasdas"+frtlist.size());
		List<User> usert = new ArrayList<>();
	index2.addObject("friends",frtlist);
	for(int i=0;i<frtlist.size();i++)
	{
		Friend fry=new Friend();
		fry=frtlist.get(i);
		System.out.println("asdsa"+fry.getFriend_id());
	User to= new User();
	if(ur.findByUserid(fry.getFriend_id())==null)
			{
		
			}
	else {
	to=ur.findByUserid(fry.getFriend_id());
	usert.add(to);
	System.out.println("user iddddddddddddd"+to.getUserid());
	}	}
	index2.addObject("users",usert);
	index2.setViewName("showfrnd");
//	System.out.println("asdasd suer siez"+usert.size());
		//System.out.println("shsajkhsa"+urt.size());
		//request.setAttribute("mode","ALL_USERS");
		return index2;
	}
	
	@RequestMapping("/EditProfile")
	public ModelAndView editUser(@ModelAttribute User user,HttpServletRequest request)  {
		
		ModelAndView index8 = new ModelAndView();
		User u6= new User();
		u6=ur.findByUserid(user.getUserid());
		index8.setViewName("register");
		
		return index8;
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

