package recipe.com.example.food.controller;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import recipe.com.example.food.Service.UserService;
import recipe.com.example.food.entity.user;
import recipe.com.example.food.utility.GlobalResources;

@RestController
@RequestMapping("/user")
@Api(value = "/user" ,tags = "recipe user management")
public class UserController {
	private Logger logger = GlobalResources.getLogger(UserController.class);
	@Autowired
	private UserService userService;
	////////////////////////////////////////////////////////////////////////////////////
	//creating user
	@PostMapping("/add")
	@ApiOperation(value = "create user", notes = "Create new user" ,tags = {"user management"})
		@ApiResponses( value = {@ApiResponse(code = 200 ,message = "user created sucessfully"),
		@ApiResponse(code = 404 ,message = "Invalid data"),
		@ApiResponse(code = 200 ,message = "Internal server error")
	})
	public user createUser(@RequestBody user user) throws Exception {
		String methodName = "createUser()";
		logger.info(methodName + "called");
		
		return this.userService.createUser(user);
	}
	
	///////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/get/{username}")
	@ApiOperation(value = "get user", notes = "get user from database" ,tags = {"user management"})
	@ApiResponses( value = {@ApiResponse(code = 200 ,message = "get operation sucessful sucessfully"),
	@ApiResponse(code = 404 ,message = "Invalid data"),
	@ApiResponse(code = 200 ,message = "Internal server error")
})
	public user getUser(@PathVariable("userName") String userName) throws Exception {
		String methodName = "getUser()";
		logger.info(methodName + "called");
		return this.userService.getUser(userName);
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////
	@DeleteMapping("/delete/{userId}")
	@ApiOperation(value = "delete user", notes = "Delete user from database" ,tags = {"user management"})
	@ApiResponses( value = {@ApiResponse(code = 200 ,message = "user deleted sucessfully"),
	@ApiResponse(code = 404 ,message = "Invalid data"),
	@ApiResponse(code = 200 ,message = "Internal server error")
})
	public user deleteUser(@PathVariable("userId") Integer userId) {
		String methodName = "deleteUser()";
		logger.info(methodName + "called");
		return this.userService.deleteUser(userId);
	}
	 
}
