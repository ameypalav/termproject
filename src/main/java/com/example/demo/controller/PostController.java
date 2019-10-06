package com.example.demo.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
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
import com.amazonaws.services.simpleworkflow.flow.worker.SynchronousRetrier;
import com.example.demo.Model.Comments;
import com.example.demo.Model.Friend;
import com.example.demo.Model.Notification;
import com.example.demo.Model.Post;
import com.example.demo.Model.User;
import com.example.demo.Repository.CommentRepository;
import com.example.demo.Repository.FriendRepository;
import com.example.demo.Repository.NotificationRepository;
import com.example.demo.Repository.PostRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Services.PostService;
import com.example.demo.Services.UploadS3;
import com.example.demo.Services.UserService;



@SessionAttributes("user")
@Controller
public class PostController {

@Autowired
UploadS3 up;
@Autowired
private UserService userService;
@Autowired
private PostRepository pr;

@Autowired
private CommentRepository cr;

@Autowired
private UserRepository ur;
@Autowired
private PostService postservice;

String key="AKIAJT5HBYZFTS2XJYLA";

String password="FN2TRq7ay2O+nFM5niWX2gq6fK3zwBpyLH//F8z1";

@Autowired
private FriendRepository fr;

@Autowired
private NotificationRepository nr;

	
@ModelAttribute("user")
public User setUpUserForm() {
   return new User();
}


//	@GetMapping(value="/Record")
//	public ModelAndView renderPage()
//	{
//		ModelAndView index = new ModelAndView();
//		index.setViewName("Record");
//	System.out.print("#########heyyyyyyyyyyyy");
//	return index;
//	}
	@GetMapping(value="/Post")
	public ModelAndView renderPage2()
	{
		ModelAndView index = new ModelAndView();
		index.setViewName("Record");
	System.out.print("#########heyyyyyyyyyyyy");
	return index;
	}	
	
	@GetMapping(value="/Profile")
	public ModelAndView renderPage3()
	{
		ModelAndView index = new ModelAndView();
		index.setViewName("profile");
	System.out.print("#########heyyyyyyyyyyyy");
	return index;
	}	
	

	
//	@GetMapping(value="/Post2")
//	public ModelAndView showAllPosts(HttpServletRequest request,@ModelAttribute User user) {
//	
//	//	List<Post> prt= new ArrayList<Post>();
//		
//
//		prt=pr.findAllById(user.getUserid());
//		
//		for(int i=0;i<prt.size();i++)
//		{Post py= new Post();
//			py=prt.get(i);
//			System.out.println("hjkhashda"+py.getAudiourl());
//			
//			
//		}
			
//		System.out.println("iddd"+user.getUserid());
//		ModelAndView post = new ModelAndView("AllPost");
//	//	post.addObject("posts", prt);
//
//		return post;
//	}
//	
	@GetMapping(value="/Post2")
	public ModelAndView show2(HttpServletRequest request,@ModelAttribute User user)
	
	{	ModelAndView post = new ModelAndView("AllPost");
		System.out.println("iddd"+user.getUserid());
		List<Post> prt= new ArrayList<Post>();
		List<Post> prtx= new ArrayList<Post>();
		
		prt=postservice.showAllPosts();
		User y=new User();
		y=ur.findByUserid(user.getUserid());
		List<Friend> fog=new ArrayList<>();
		fog=fr.findByUser(y);
		for(int i=0;i<fog.size();i++)
		{Friend gq=new Friend();
		gq=fog.get(i);
		User yx=new User();
		yx=ur.findByUserid(gq.getFriend_id());
		Post r=new Post();
		prtx=pr.findByUser(yx);
		}
		for(int i=0;i< prt.size();i++)
		{
			Post p=prt.get(i);
		System.out.println("id"+p.getId());
				
		}
		
//for(Post post:pr.findAllById(user)) {
//	prt.add(pr);
//}
post.addObject("posts", prtx);
		return post;
		
	}
	
	@RequestMapping("/select-post")
	public ModelAndView endMyevent(@RequestParam int id,HttpServletRequest request ) {
		
		ModelAndView post = new ModelAndView("post");
		Post p = new Post();
	p=pr.findById(id);
	post.addObject("post",p);
	
	List<Comments> crt= new ArrayList<>();
	crt=cr.findByPost(p);
	
	post.addObject("comment",crt);
	
	//	p=postservice.
		//eventService.deleteMyevent(eventid);
		//request.setAttribute("events",eventService.showAllEvents());
		//request.setAttribute("mode","ALL_EVENTS");
		return post;
	}

	@RequestMapping("/select2-post")
	public ModelAndView endMyevent2(@RequestParam int id,HttpServletRequest request ) {
		
		ModelAndView post = new ModelAndView("deletePost");
		Post p = new Post();
	p=pr.findById(id);
	post.addObject("post",p);
	
	List<Comments> crt= new ArrayList<>();
	crt=cr.findByPost(p);
	
	post.addObject("comment",crt);
	
	//	p=postservice.
		//eventService.deleteMyevent(eventid);
		//request.setAttribute("events",eventService.showAllEvents());
		//request.setAttribute("mode","ALL_EVENTS");
		return post;
	}
	
	@RequestMapping("/Profile2")
	public ModelAndView endMyevent2(@ModelAttribute User user,HttpServletRequest request ) {
		
		User use=userService.findByUserid(user.getUserid());
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
	
	@GetMapping(value="/comment")
public ModelAndView endMyevent2(@RequestParam int postid,HttpServletRequest request,@RequestParam String comment ,@ModelAttribute User user) {
		
		ModelAndView post = new ModelAndView("post");
		Post p = new Post();
		System.out.println("post id"+postid);
		System.out.println("coment"+comment);
		
	p=pr.findById(postid);
	post.addObject("post",p);
	Comments cm= new Comments();
	cm.setCommentuser(user.getUsername());
	cm.setComment(comment);
	cm.setPost(p);
	cm.setCommentuserid(user.getUserid());
	List<Comments> cmL= new ArrayList<>();
	cmL.add(cm);
	p.setComment(cmL);
	Notification nw= new Notification();
	User it= new User();
	it=p.getUser();
	List<Comments> lc=new ArrayList<>();
	
	lc=cr.findByPost(p);
	System.out.println("lc size"+lc.size());
	for(int i=0;i<lc.size();i++)
	{
		Comments pfrd = new Comments();
		pfrd=lc.get(i);
		Post r=pfrd.getPost();
		
		if(p.getId()==r.getId())
		{	Notification ns= new Notification();
			ns.setUsername(pfrd.getCommentuserid());
			System.out.println("Frnd id"+pfrd.getCommentuserid());
			User k = new User();
			k=ur.findByUserid(pfrd.getCommentuserid());
			System.out.println("my user"+k.getUserid());
			ns.setNotifica("Commented on your post"+k.getUsername());
			System.out.println("coment id r"+r.getId());
		//	nw.setUsername(r.getId());
			System.out.println("Cooment on foreds id"+nw.getUsername());
			
			nr.save(ns);
		}
		
	}
	
	nw.setUsername(it.getUserid());
	nw.setPostid(p.getId());
	nw.setNotifica(user.getUsername()+"Replied on your post");
	nr.save(nw);
	pr.save(p);
	
	List<Comments> crt= new ArrayList<>();
	crt=cr.findByPost(p);
	
	
	
	
//	Comments t=crt.get(1);
//	Comments t2=crt.get(2);
//	System.out.println("New Comment"+t.getComment());
//	System.out.println("New Comment2"+t2.getComment());
	post.addObject("comment",crt);
	
	
	
	//	p=postservice.
		//eventService.deleteMyevent(eventid);
		//request.setAttribute("events",eventService.showAllEvents());
		//request.setAttribute("mode","ALL_EVENTS");
		return post;
	}
	
	
	@PostMapping(value="/base64audio")
	public ModelAndView saveAudioRenderSomePage(HttpServletRequest request,@RequestParam("1234") String recording,@ModelAttribute User user,@RequestParam("file") String image,@RequestParam("descpti") String descpt) throws Exception
	{
		ModelAndView sucess = new ModelAndView("success");
		System.out.println("amey is here");
		if(recording.isEmpty()) throw new Exception("recording--data is null");
		Decoder decod=Base64.getDecoder();
		Decoder decoder=Base64.getDecoder();
		System.out.println(recording);
		System.out.println("Image"+image);
		byte[] decodedByte = decoder.decode(recording.split(",")[1]);
		byte[] decodedByter = decod.decode(image.split(",")[1]);
		FileOutputStream fos;
		FileOutputStream fos2;
		try {
			fos=new FileOutputStream("MyAudio.webm");
			fos2=new FileOutputStream("MyImage.png");
			fos.write(decodedByte);
			fos2.write(decodedByter);
			fos.close();
			fos2.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		String ur=up.upload("amey"+".webm", new FileInputStream("MyAudio.webm"));
		String ur2=up.upload("amey2"+".png", new FileInputStream("MyImage.png"));
		//String urt=up.upload("ameyy", image);
		
		System.out.println("URLLLL"+ur);
		System.out.println("iddd"+user.getUserid());
		User u=userService.findByUserid(user.getUserid());
//		BasicAWSCredentials cred = new BasicAWSCredentials(key,password);
//		//	AmazonS3Client s3client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(cred)).withRegion(Regions.US_EAST_2).build();
//				
//				
//			AmazonS3 s3client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(cred)).withRegion(Regions.US_EAST_2).build();
//
//			
//		
//				PutObjectRequest putReq = new PutObjectRequest("termproject69",image.getOriginalFilename(), image.getInputStream(),new ObjectMetadata()).withCannedAcl(CannedAccessControlList.PublicRead);
//		
//			s3client.putObject(putReq);
//			
//			String imgSrc = "http://"+"termproject69"+".s3.amazonaws.com/"+image.getOriginalFilename();
		Post p = new Post();
		List<Post> ap= new ArrayList<Post>();
		p.setAudiourl(ur);
	p.setImageurl(ur2);
		p.setUser(u);
		p.setDescpt(descpt);
		ap.add(p);
		u.setPost(ap);
		userService.saveMyUser(u);
		List<Friend> frnd= new ArrayList<>();
		frnd=fr.findByUser(u);
		
		for(int i=0;i<frnd.size();i++)
		{
			Friend fr= new Friend();
			fr=frnd.get(i);
			Notification nt=new Notification();
			nt.setUsername(fr.getFriend_id());
			nt.setNotifica("Post has been Created by"+u.getUsername());
			nt.setPostid(p.getId());
			nr.save(nt);
			
		}
	
		//postservice.saveMyPost(p);
		sucess.addObject("audiourl2",descpt);
		sucess.addObject("audiourl",ur);
		sucess.addObject("imgsrc",ur2);
		
		return sucess;
		
		
		
	}
	
	
}
