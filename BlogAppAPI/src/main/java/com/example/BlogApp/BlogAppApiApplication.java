package com.example.BlogApp;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

//we have added dependency of databse while creating App, thus before running we have to congigure database with this app otherwise we will get error

@SpringBootApplication
public class BlogAppApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApiApplication.class, args);
	}
//here as we have added spring boot dev tools we just need to save the code and server will automatically get restarted, no need to restart again

	//bean annotation makes spring add the below object to spring container
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
}

/*
 * first add the postgres database connectivity configurations in applications.prioperties file
 * next, we want User table, thus in the entities package, we create User class which will automatically create entity/table in database. 
 * next, we need a java file using which we will perform operation on database. thus, create repository file inside repository package
 * create UserDTO in payload package. normally we perform any user related operations on UserDTO class, not directly on User centity. User is just a entity/ table in database, so we dont perform operations directly on it.
 * create userservice intercace in services package, there we mention all the operations to be performed on user table
 * next, create a sub package called serviceImpli, and create userserviceImpli class which will implement the UserServices. Once that is done create a method for conversion of User to USerDTo and vice versa using modelmapping.
 * conversion for one model to another is done by modelmapper. for that create a bean in main class and autowire and use it in Impli class
 * define all the methods in UserServiceImpli class for CRUD operations of User
 * next, we have to create the controller for user to handle get, post, put and delete request using service method
 * once that is done go to postman and perform the operations, we can also check the postgres database it will also get updated
 * 
 *   next, we perform validation of user's data using hibernate validator. for that first add dependency of spring starter project validastion by removing version
 *   next go to userDTO class and add all the validations.
 *   go to createUser and UpdateUser beans in Usercontoller and add @valid annotation and thats it. Now when we try to add bad user we will get exception
 *   
 *   next,we create ctagory entity, Dto and Repo
 *   then create categoryservice and implement it in ServiceImpli package and implement all CRUD operations methods in it
 *   crete category controller and use validations
 *   
 *   next is our main Entity which is posts which are uploaded by users and will be saved in the respective category
 *   we have to establish RelationShip between Posts, and users and Categries. for that create category and user instance in Posts class with many to one relationship
 *   then create one to many relationship in User and category class
 *   then once the entity is created, create the repo and mention custom method for accesing using user and category
 *   then create Dto, service and serviceImpli for Post methods
 *   then create controller for all the methods of post
 *   
 *   Use Paginaation in GetAllPosts Method, change the getAllpost method and add parameters in postervice interface also and implement controller. Also create AppConstants class to define constants
 *   next we need to do  sorting and searching. for that use Sort.by() in getAllPosts method in servicxeimpoli class 
 *   And for searching create FindByTitleContaining method in repo file, which will automatically get implemented and will sercch all the posts with title mentionwed in it as argument. That's the bueaty of Spring
 *   
 *   We also need to perform CRUD operatons for image for Posts database.
 *   For that create an service called fileservice and fileserviceimpli
 *   open application.properties and add configurationsin it
 *   once this is done we have to add the controller for file in PostController.java file. 
 *   We have implemented file controller yet but it is giving error so our image will not work. Watch COde with durgesh REST API backend video 28 7 min onwards
 *   
 *     next we wqnt users to add comment for a post. so create comment entity with many to one relationship with posts.
 *     then create comment dto, comment commentrepo, commentservice and it's impli
 *     
 *     now we want all the API to get secured and want to use JWT for authentication, for that add spring security dependency first and then edit application.properties to add debug(user username as 'user')
 *     SS uses FOrm based Authentication, instead we want to use basic Auth, but in basic auth also each time we have to login inside postman, thus we use JWT auth.
 *     for basic Auth watch code with durgesh video 31
 *     
 *     once it is done follow the notes and next durgesh video for JWT authentication
 *     
 *      deployed using aws beanstalk and rds 
 * */
 
//posts with image and JWT authenticATIONis not implemented for that follow notes and durgesh backend videos when we need to implement thst for realtime projects
