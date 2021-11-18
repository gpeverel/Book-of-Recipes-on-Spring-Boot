package recipes.businesslayer;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import recipes.persistence.RecipeRepository;
import recipes.securityUser.User;
import recipes.securityUser.UserDetailsImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeService
{
	public final RecipeRepository recipeRepository;

	public RecipeService(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;}

	public void deleteByIdAndCheckDetails(Integer id, UserDetailsImpl details)
	{
		Recipe recipe = recipeRepository.findById(id).orElseThrow(() ->
				new ResponseStatusException(HttpStatus.NOT_FOUND));

		if (details == null)
		{
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}
		if (recipe.getUserId() != details.getId())
		{
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		recipeRepository.deleteById(id);
	}

	public Recipe  save(Recipe recipe)
	{
		return recipeRepository.save(recipe);
	}

	public Recipe existsByIdAndUpdateByNewRecipe(Integer id, Recipe newRecipe)
	{
		Optional<Recipe> optRecipe = recipeRepository.findById(id);
		if (optRecipe.isPresent())
		{
			optRecipe.get().setCategory(newRecipe.getCategory());
			optRecipe.get().setDescription(newRecipe.getDescription());
			optRecipe.get().setDirections(newRecipe.getDirections());
			optRecipe.get().setIngredients(newRecipe.getIngredients());
			optRecipe.get().setName(newRecipe.getName());
			optRecipe.get().setDate(LocalDateTime.now());
			return optRecipe.get();
		}
		else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

	public Recipe existsById(Integer id)
	{
		Optional<Recipe> optRecipe = recipeRepository.findById(id);
		if (optRecipe.isPresent())
			return optRecipe.get();
		else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

	public List<Recipe> findAllByName(String name)
	{
		return recipeRepository.findDistinctByNameContainingIgnoreCaseOrderByDateDesc(name);
	}

	public List<Recipe> findAllByCategory(String name)
	{
		return recipeRepository.findDistinctByCategoryIgnoreCaseOrderByDateDesc(name);
	}
}
