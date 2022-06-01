package recipe.com.example.food.Service.Simpl;

import java.util.Optional;
import java.util.Set;
import java.util.function.IntPredicate;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import recipe.com.example.food.Service.UserService;
import recipe.com.example.food.controller.UserController;
import recipe.com.example.food.entity.user;
import recipe.com.example.food.repository.UserRepository;
import recipe.com.example.food.utility.GlobalResources;

@Service
public class UserServiceImpl implements UserService {
	private Logger logger = GlobalResources.getLogger(UserController.class);
	@Autowired
	private UserRepository userRepository;
	/**
     * given method accepts user entity as input
     * checks whether the given user name is present in the database or not 
     * if the user name is  present already it will display user already exists in database
     *if the user name is not present then the user will get added
     */
	@Override
	public user createUser(user user) throws Exception {
		String methodName = "createUser()";
		logger.info(methodName + "called");
		Optional<user> optional = userRepository.findByUserName(user.getUserName());
		if(optional.isPresent()) {
			throw new Exception("User is Alredy present in database");
		}
		else {
		  //  user user1 = optional.get();
		    userRepository.save(user);}
		return user;
		
		
	}
	/**
     * given method accepts user entity as input
     * checks whether the given user name is present in the database or not 
     * if the user name is  present already it will return the user details
     *if the user name is not present then it will throw user not exists
     */
	@Override
	public user getUser(String userName) throws Exception {
		String methodName = "getUser()";
		logger.info(methodName + "called");
		Optional<user> optional = userRepository.findByUserName(userName);
		if(optional.isEmpty()) {
			throw new Exception("User not exists");
		}
		
		user user2 = optional.get();
		
		return user2;
	}
	/**
     * given method accepts user entity as input
     * checks whether the given userId is present in the database or not 
     * if the userId is  present it will delete the user 
     *if the userId is not present then it will throw exception
     */
	@Override
	public user deleteUser(Integer userId) {
		String methodName = "deleteUser()";
		logger.info(methodName + "called");
		Optional<user> optional = userRepository.findById(userId);
		user user = optional.get();
		userRepository.delete(optional.get());
		return user;
	}
	/**
     * given method accepts user entity as input
     * checks whether the given userId is present in the database or not 
     * if the userId is  present already it will update the user details 
     *if the user is not present then it will get updated
     */
	@Override
	public user updateUser(Integer userId, user user ) throws Exception{
		String methodName = "updateUser()";
		logger.info(methodName + "called");

	Optional<user> optional = userRepository.findById(userId);
	if(optional.isPresent()) {
	user temp = optional.get();
	temp.setFirstName(user.getFirstName());
	temp.setLastName(user.getLastName());
	temp.setUserName(user.getUserName());
	temp.setRole(user.getRole());
	temp.setPassword(user.getPassword());

	userRepository.save(temp);
	}
	return user;
	}


}
