package com.example.BlogApp.services.serviceImpli;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.BlogApp.entities.Category;
import com.example.BlogApp.entities.Posts;
import com.example.BlogApp.entities.User;
import com.example.BlogApp.payload.CategoryDTO;
import com.example.BlogApp.payload.PostDto;
import com.example.BlogApp.payload.UserDTO;
import com.example.BlogApp.repository.CategoryRepo;
import com.example.BlogApp.repository.PostRepo;
import com.example.BlogApp.repository.UserRepo;
import com.example.BlogApp.services.PostsService;

@Service
public class PostServiceImpli implements PostsService {
	
	@Autowired
	private PostRepo postrepo;
	
	@Autowired
	private ModelMapper modelmapper;
	
	//as we are going to filter the posts using category and users we need their data too. and to get their data we use their repository
	@Autowired
	private UserRepo userrepo;
	@Autowired
	private CategoryRepo catrepo;

	
	@Override
	public PostDto createPost(PostDto postdto, int userid, int catid) {
		// during post request(i.e. creating post) client will enter only title and content. thats why in postdto we define only title and content
		//remaining instances we have to assign here
		Posts post = modelmapper.map(postdto, Posts.class);
		
		post.setImagename("default.png");
		
		//to add the current date while creating user 
		post.setAddeddate(new Date());
		
		//we will get the cateogory and user object which belongs to this post using their respective ids
		User user = userrepo.findById(userid).orElse(new User());
		Category cat = catrepo.findById(catid).orElse(new Category());
		
		post.setUser(user);
		post.setCategory(cat);
		
		//title and content comes by client during post request
		//thus we have all the column values and we will save it in database
		
		Posts added_post = postrepo.save(post);
		
		PostDto postdtoadded = modelmapper.map(added_post, PostDto.class);
		
		
		return postdtoadded;
	}

	@Override
	public PostDto updatePost(PostDto postdto, int postid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostDto> getAllPosts(int pagesize, int pagenum, String sortBy) {
	
		
		//we want to to implement pagination as we dont want to view all the posts in a single web page.
		//we use page size and page number to access the items. (Normally we keep page size as constant and page number as dynamic which  user will use)
		
		
		//using pageable we implement Pagination
		//Sort inbult class is used to sort the list of posts using the argument passed in by() 
		Pageable p = (Pageable) PageRequest.of(pagenum, pagesize, Sort.by(sortBy));
		
		//we get all the posts for the required page number and page size
		Page<Posts> pagepost = postrepo.findAll(p);
		
		List<Posts> posts = pagepost.getContent();
		
		List<PostDto> postdtos = posts.stream().map((post)->modelmapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postdtos;
		
		
		/*
		//we cannot perform JPA operation on PostDTO as it is not an entity/Table, thus we perform operation on  Posts then convert it to postdto using modelmapper and then return postdto
		List<Posts> posts = postrepo.findAll();
		
		List<PostDto> postdtos = posts.stream().map((post)->modelmapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postdtos;
		*/
	}

	@Override
	public PostDto getPostById(int postid) {
		// TODO Auto-generated method stub
		
		Posts post = postrepo.findById(postid).orElse(new Posts());
		
		PostDto postdto = modelmapper.map(post, PostDto.class);
		
		return postdto;
	}

	@Override
	public void deletePost(int postid) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<PostDto> getPostByCategory(int categoryid) {
		// TODO Auto-generated method stub
		
		//we need to fetch all the posts for a particular category passed in url.(If we have a website then user will click they will see all the post belonging to that category)
		
		//first we fetch the category using the category id  
		Category cat = catrepo.findById(categoryid).orElse(new Category());
		
		//using that category we fetch all the Posts belonging to that category 
		List<Posts> posts = postrepo.findByCategory(cat);
		
		//we do not need to implement findbycategory method spring will automatically. It will get all the posts in which the category column is same as category which is passed as argument here
		
		//we convert this Post to PostDto and then return
		List<PostDto> postdtos = posts.stream().map((post)->modelmapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		
		
		return postdtos;
	}

	@Override
	public List<PostDto> getPostByUser(int userid) {
		// TODO Auto-generated method stub
		
		//fetch the user object using userid
		User user = userrepo.findById(userid).orElse(new User());
		
		//fetch the Posts for the particular user object
		List<Posts> posts = postrepo.findByUser(user);
		
		List<PostDto> postdtos = posts.stream().map(post->modelmapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		
		return postdtos;
	}

	@Override
	public List<PostDto> searchposts(String keyword) {
		// TODO Auto-generated method stub
		
		List<Posts> posts = postrepo.findByTitleContaining(keyword);
		
		List<PostDto> postdtos = posts.stream().map(post->modelmapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postdtos;
	}

}
