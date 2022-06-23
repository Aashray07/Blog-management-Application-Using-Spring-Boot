package com.example.BlogApp.services.serviceImpli;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BlogApp.entities.User;
import com.example.BlogApp.payload.UserDTO;
import com.example.BlogApp.repository.UserRepo;
import com.example.BlogApp.services.UserService;

@Service
public class UserServiceImpli implements UserService {

	@Autowired
	UserRepo userrepo;
	
	@Autowired
	private ModelMapper modelmapper; 
	
	@Override
	public UserDTO createUser(UserDTO user) {
		// TODO Auto-generated method stub
		
		//we first convert UserDTO to USer as we must have User as Entity 
		User Uuser = this.dtoToUser(user);
		
		User savedUuser = userrepo.save(Uuser);
		
		//we want to return UserSTO thus convert again
		UserDTO userdto = this.UserToUserDTO(savedUuser);
		
		return userdto;
	}

	@Override
	public UserDTO updateUser(UserDTO user, int userid) {
		// TODO Auto-generated method stub
		//updated values are passed as parameter of UserDTO and the userId tells which row/object should get updated
		
		//We retrieve the User object using the passed Id
		User old_user_obj  = userrepo.findById(userid).orElse(new User());
		
		//next we update the fetched old user object with the userDTO object passed in parameter.
		old_user_obj.setAbout(user.getAbout());
		old_user_obj.setEmail(user.getEmail());
		old_user_obj.setId(user.getId());
		old_user_obj.setName(user.getName());
		old_user_obj.setPassword(user.getPassword());
		
		//finally we save the updated user in the database, it will automatically update the same row using Id
		User updated_user = userrepo.save(old_user_obj);
		
		//convert User to UserDTO
		UserDTO userdto = this.UserToUserDTO(updated_user);
		
		return userdto;
	}

	
	@Override
	public UserDTO getUserbyID(int userid) {
		// TODO Auto-generated method stub
		
		User user = userrepo.findById(userid).orElse(new User());
		
		
		return this.UserToUserDTO(user);
	}

	
	@Override
	public List<UserDTO> getAllUser() {
		// TODO Auto-generated method stub
		
		List<User> users = userrepo.findAll();
		
		List<UserDTO> userdtos = users.stream().map(user->this.UserToUserDTO(user)).collect(Collectors.toList()); 
		
		return userdtos;
	}

	@Override
	public void deleteUser(int userid) {
		// TODO Auto-generated method stub
		
		User user = userrepo.findById(userid).orElse(new User());
		
		userrepo.delete(user);

	}
	
	//this method is used to convert the dto into User, we do this because we can save on User only. 
	public User dtoToUser(UserDTO userdto) {
		
		User u1 = modelmapper.map(userdto, User.class);
		/*
		u1.setId(userdto.getId());
		u1.setAbout(userdto.getAbout());
		u1.setEmail(userdto.getEmail());
		u1.setName(userdto.getName());
		u1.setPassword(userdto.getPassword());
		*/
		return u1;
		
	}
	
	//convert User back to UserDTO
public UserDTO UserToUserDTO(User user) {
		
		UserDTO u1 = modelmapper.map(user, UserDTO.class);
		
		/*
		u1.setId(user.getId());
		u1.setAbout(user.getAbout());
		u1.setEmail(user.getEmail());
		u1.setName(user.getName());
		u1.setPassword(user.getPassword());
		*/
		return u1;
		
	}

}
