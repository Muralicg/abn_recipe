package recipe.com.example.food.advice;
import java.util.NoSuchElementException;

import javax.persistence.EntityExistsException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import recipe.com.example.food.Exceptions.ElementNotFound;
import recipe.com.example.food.Exceptions.EntityAlreadyExistsExceptionindb;
import recipe.com.example.food.Exceptions.IngredientAlreadyExistsException;

@RestControllerAdvice
public class Controlleradvice {

	@ExceptionHandler(EntityAlreadyExistsExceptionindb.class)
	public ResponseEntity<String>
					handleEmptyResponse(EntityAlreadyExistsExceptionindb userNotFoundException){
						return new ResponseEntity<String>("no element is present in database with given name or Id", HttpStatus.NOT_FOUND);
						
		
	}
	
	@ExceptionHandler(ElementNotFound.class)
	public ResponseEntity<String>
	handleEmptyResponse(ElementNotFound ElementAlreadyExists){
		return new ResponseEntity<String>("Element with given id / name already exists in db", HttpStatus.BAD_REQUEST);
		
	}
}
