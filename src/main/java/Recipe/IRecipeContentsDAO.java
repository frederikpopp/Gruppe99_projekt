package Recipe;

import ProductBatch.IProductContentsDTO;
import Utilities.DAO;

import java.util.List;

public interface IRecipeContentsDAO extends DAO {

    public void addIngredient(IRecipeContentsDTO ingredient) throws DALException;

    public void updateIngredient(IRecipeContentsDTO ingredient) throws DALException;

    public void deleteIngredient(int ingredientID, int recipe_ID) throws DALException;

    public List<IRecipeContentsDTO> getIngredients(int recipeID) throws DALException;

    public void addRecipe(List<IRecipeContentsDTO> recipe) throws DALException;

    public void updateRecipe(List<IRecipeContentsDTO> recipe) throws DALException;

    public void deleteRecipe(int recipeID) throws DALException;
}
