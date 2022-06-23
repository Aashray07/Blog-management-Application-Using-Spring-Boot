package com.example.BlogApp.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.BlogApp.entities.Posts;
import com.example.BlogApp.payload.AppConstants;
import com.example.BlogApp.payload.PostDto;
import com.example.BlogApp.repository.PostRepo;
import com.example.BlogApp.services.FileService;
import com.example.BlogApp.services.PostsService;

@RestController
@RequestMapping("api/")
public class PostController {

	@Autowired
	private PostsService pserv;
	
	@Autowired                  
	private FileService fileserv;
	
	@Value("${project.image}")
	private String path;
	
	
	//we get the user and category which has created the post using the url
	@PostMapping("/user/{uid}/category/{catid}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto pdto,@PathVariable int uid,@PathVariable int catid) {
	
		PostDto new_post =  pserv.createPost(pdto, uid, catid);
		
		return new ResponseEntity<PostDto>(new_post, HttpStatus.CREATED);
	}
	//when user sends post request by entering the title and content the URI will be like http://localhost:8080/api/user/3/category/3/posts
	//the user with user id 3 is creating a post with the some title and content. That post is saved in category with catid 3
	
	
	//By mentioning category id or user id in the uri, the below controllers return the list of posts belonging to that category and user
	@GetMapping("userposts/{userid}/users")
	public ResponseEntity<List<PostDto>> getPostsByUser( @PathVariable int userid){
		List<PostDto> postdtos = pserv.getPostByUser(userid);
		return new ResponseEntity<List<PostDto>>(postdtos, HttpStatus.OK);
	}
	
	
	@GetMapping("catposts/{categoryid}/cats")
	public ResponseEntity<List<PostDto>> getPostsByCategory( @PathVariable int categoryid){
		List<PostDto> postdtos = pserv.getPostByCategory(categoryid);
		return new ResponseEntity<List<PostDto>>(postdtos, HttpStatus.OK);
	}
	
	
	//here we are implementing pagination thus we use requestparam where we have set the default value
	//when the user sends the request using a form, we take that form data in our parameter using requestpram annotation
	//we also use sorting parameter, using the default string mentioned spring will sort the list.
	@GetMapping("/allposts") 
	//default value will be considered when we dont specify the value in URI while sending get request
	public List<PostDto> getAllPosts(@RequestParam(value = "pagesize", defaultValue = AppConstants.PAGE_SIZE, required = false) int pagesize, @RequestParam(value="pagenum", defaultValue= AppConstants.PAGE_NUM, required = false) int pagenum, @RequestParam(value = "sorBy", defaultValue = AppConstants.SORTbY, required = false) String sortBy){
		List<PostDto> postdtos = pserv.getAllPosts(pagesize, pagenum, sortBy);
		
		return (postdtos);
	}
	//In real time we can create number of urls and each url redirect to a specific page. The page number is selected by user dynamically
	

	//instead of ResponseEntity we can also use just list and return the list, it will work pefrctly fine.
	//response entity is just used to return the response code along with the list
	@GetMapping("/posts/{postid}")
	public PostDto getPostById(@PathVariable int postid) {
		PostDto postdto = pserv.getPostById(postid);
		
		return postdto;
	}
	
	@GetMapping("/posts/{keyword}/search")
	public ResponseEntity<List<PostDto>> searchposts(@PathVariable("keyword") String keyword) {
		
		List<PostDto> postdtos =  pserv.searchposts(keyword);
		
		return new ResponseEntity<List<PostDto>>(postdtos, HttpStatus.OK);
	}
	
	//we return updated bpost object which is passed using postid from uri.
	//the updated post object will have image name which is uploaded from postman
	@PostMapping("/postimages")
	public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image, @PathVariable int postid) throws IOException{
		
		PostDto postdto = pserv.getPostById(postid);
		
		String filename = fileserv.uploadImage(path, image);
		
		postdto.setImagename(filename);
		
		PostDto updatedpost = pserv.updatePost(postdto, postid);
		
		return new ResponseEntity<PostDto>(updatedpost, HttpStatus.OK);
		
		
	}
	
}
