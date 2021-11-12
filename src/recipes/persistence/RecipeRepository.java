package recipes.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import recipes.businesslayer.Recipe;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Integer>
{

	List<Recipe> findDistinctByNameContainingIgnoreCaseOrderByDateDesc(String name);
	List<Recipe> findDistinctByCategoryIgnoreCaseOrderByDateDesc(String category);
}
