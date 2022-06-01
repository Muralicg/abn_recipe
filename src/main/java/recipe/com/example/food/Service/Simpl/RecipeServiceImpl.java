package recipe.com.example.food.Service.Simpl;

import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import recipe.com.example.food.Exceptions.RecipeAlreadyExistsException;
import recipe.com.example.food.Exceptions.RecipeIdNotFoundException;
import recipe.com.example.food.Exceptions.RecipeNameNotFoundException;
import recipe.com.example.food.Service.RecipeService;
import recipe.com.example.food.controller.UserController;
import recipe.com.example.food.entity.recipes;
import recipe.com.example.food.repository.RecipeRepository;
import recipe.com.example.food.utility.GlobalResources;

@Service
public class RecipeServiceImpl implements RecipeService{
	
	private Logger logger = GlobalResources.getLogger(UserController.class);
	@Autowired
	private RecipeRepository recipeRepository;

	/**
	 *Given method accepts recipe entity as input
	 *checks whether given recipe present in data base or not
	 *if recipe not present recipe gets added
	 *if recipe already presents it will throw an exception
	 */
	@Override
	public recipes createRecipe(recipes recipe) throws RecipeAlreadyExistsException {
		String methodName = "createRecipe()";
		logger.info(methodName + "called");
		
		Optional<recipes> optional = recipeRepository.findByRecipeName(recipe.getRecipeName());
		 
			if(optional.isPresent()) {
				throw new RecipeAlreadyExistsException("recipe already exists");
			}
			
		 recipeRepository.save(recipe);
		 return recipe;
		
	}
	/**
	 *Given method accepts recipe as input
	 *checks whether the given recipe is present in the database or not
	 *if recipe is present the recipe will be displayed
	 *if recipe is not present it will throw an exception
	 *
	 */
	@Override
	public recipes getRecipe(String recipeName) throws RecipeNameNotFoundException {
		String methodName = "getRecipe()";
		logger.info(methodName + "called");
		Optional<recipes> optional = recipeRepository.findByRecipeName(recipeName);
		if(optional.isEmpty()) {
			throw new RecipeNameNotFoundException("no recipe present with this name ");
		}
		recipes recipe = optional.get();
		return recipe;
		
	}
	/**
	 * Given method accepts recipeId as input
	 *checks whether the given recipeId is present in the database or not
	 *if recipe id is present it will get deleted
	 *if the recipe id not present it will throw exception
	 */
	@Override
	public recipes deleteRecipe(Integer recipeId) throws RecipeIdNotFoundException {
		String methodName = "deleteRecipe()";
		logger.info(methodName + "called");
		Optional<recipes> optional = recipeRepository.findById(recipeId);
		if(optional.isEmpty()) {
			throw new RecipeIdNotFoundException("recipe with id"+recipeId+"not present");
		} 
		else{
			recipeRepository.deleteById(recipeId);
		}
		recipes recipe = optional.get();
		return recipe;
	
	}
	/**
	 *Given method accepts recipeId as input
	 *checks whether the given recipeId is present in the database or not
	 *if the recipe id is present it will update 
	 *if the recipe id is not present it will throw exception
	 */
	@Override
	public recipes updateRecipe(Integer recipeId, recipes recipe) throws RecipeIdNotFoundException {
		String methodName = "updateRecipe()";
		logger.info(methodName + "called");
		Optional<recipes> optional = recipeRepository.findById(recipeId);
		if(optional.isPresent()) {
			recipes temp = optional.get();
			temp.setRecipeName(recipe.getRecipeName());
			temp.setServing(recipe.getServing());
			temp.setCategory(recipe.getCategory());
			temp.setRecipeId(recipe.getRecipeId());
			//temp.setInstructions(recipe.getInstructions());
			//temp.setIngredients(recipe.getIngredients());
			temp.setDate(recipe.getDate());
		}
		else throw new RecipeIdNotFoundException("recipe with id"+recipeId+"not present");
		return recipe;
	}

	
	
	

}
