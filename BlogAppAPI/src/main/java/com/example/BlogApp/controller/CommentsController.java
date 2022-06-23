package com.example.BlogApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BlogApp.payload.CommentsDTO;
import com.example.BlogApp.services.CommentService;

@RestController
@RequestMapping("comments/")
public class CommentsController {

	@Autowired
	private CommentService cserev;
	
	@PostMapping("/create/{postid}/comm")
	public CommentsDTO createComment(@RequestBody CommentsDTO comdto,@PathVariable int postid) {
		
		CommentsDTO comm = cserev.createComment(comdto, postid);
		
		return comm;
	}
	
	@DeleteMapping("/deletecomment/{commid}/done")
	public void deleteComment(@PathVariable int commid) {
		
		cserev.deleteComment(commid);
	}

}

