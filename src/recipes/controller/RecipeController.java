package recipes.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import recipes.businesslayer.Recipe;
import recipes.businesslayer.RecipeService;
import recipes.securityUser.UserDetailsImpl;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@Validated
public class RecipeController
{
	@Autowired
	RecipeService recipeService;

	@PostMapping("/api/recipe/new")
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Integer> post(@AuthenticationPrincipal UserDetailsImpl details,
			@Validated @RequestBody Recipe recipe)
	{
		System.out.println("Go recipe");
		if (details == null)
		{
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}
		recipe.setUserId(details.getId());
		recipe.setDate(LocalDateTime.now());
		Recipe savedRecipe = recipeService.save(recipe);
		return Map.of("id", savedRecipe.getId());
	}

	@PutMapping("/api/recipe/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Map<String, Integer> updateRecipeById(@AuthenticationPrincipal UserDetailsImpl details,
			@Validated @RequestBody Recipe newRecipe, @PathVariable int id)
	{
		Recipe updatedRecipe = recipeService.existsByIdAndUpdateByNewRecipe(id, newRecipe);
		if ( details.getId() != updatedRecipe.getUserId())
		{
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		Recipe savedRecipe = recipeService.save(updatedRecipe);
		return Map.of("id", savedRecipe.getId());
	}

	@GetMapping("/api/recipe/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Recipe get(@PathVariable int id)
	{
		return recipeService.existsById(id);
	}

	@GetMapping(value = "/api/recipe/search", params = "name")
	@ResponseStatus(HttpStatus.OK)
	public List<Recipe> getRecipesByName(
			@RequestParam @NotEmpty @NotBlank String name)
	{
		return recipeService.findAllByName(name);
	}

	@GetMapping(value = "/api/recipe/search", params = "category")
	@ResponseStatus(HttpStatus.OK)
	public List<Recipe> getRecipesByCategory(
			@RequestParam @NotEmpty @NotBlank String category)
	{
		return recipeService.findAllByCategory(category);
	}


	@DeleteMapping("/api/recipe/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@AuthenticationPrincipal UserDetailsImpl details,
	                   @PathVariable int id)
	{
		recipeService.deleteByIdAndCheckDetails(id, details);
	}



}
