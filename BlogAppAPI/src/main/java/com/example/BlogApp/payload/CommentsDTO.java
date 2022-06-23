package com.example.BlogApp.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CommentsDTO {
	
    private int commentid;
	
	
	private String content;
	
}
