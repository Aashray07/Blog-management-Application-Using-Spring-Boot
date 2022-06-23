package com.example.BlogApp.services;

import com.example.BlogApp.payload.CommentsDTO;

public interface CommentService {

	 CommentsDTO createComment(CommentsDTO comment, int postid);
	 
	 void deleteComment(int commentid);
}
