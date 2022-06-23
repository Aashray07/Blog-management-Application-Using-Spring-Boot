package com.example.BlogApp.services.serviceImpli;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.BlogApp.entities.Comments;
import com.example.BlogApp.entities.Posts;
import com.example.BlogApp.payload.CommentsDTO;
import com.example.BlogApp.repository.CommentsRepo;
import com.example.BlogApp.repository.PostRepo;
import com.example.BlogApp.services.CommentService;

@Service
public class commentserviceimpli implements CommentService {

	@Autowired
	ModelMapper modelmapper;
	
	@Autowired
	PostRepo postrepo;
	
	@Autowired
	CommentsRepo crepo;
	
	@Override

	public CommentsDTO createComment(CommentsDTO comment, int postid) {
		
		Posts posts = postrepo.findById(postid).orElse(new Posts());
		
		
		Comments comments = modelmapper.map(comment, Comments.class);
		
		comments.setPost(posts);
		
		Comments new_comment = crepo.save(comments);
		
		
		return modelmapper.map(new_comment, CommentsDTO.class);
	}

	@Override
	public void deleteComment(int commentid) {
		

		Comments comm = crepo.findById(commentid).orElse(new Comments());
		
		crepo.delete(comm);
		
	}

}
