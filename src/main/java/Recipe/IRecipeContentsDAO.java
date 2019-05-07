package Recipe;

import ProductBatch.IProductContentsDTO;
import Utilities.DAO;

import java.util.List;

public interface IRecipeContentsDAO extends DAO {

    public void addIngredient(IProductContentsDTO ingredient) throws DALException;

    public void updateIngredient(IProductContentsDTO ingredient) throws DALException;

    public void deleteIngredient(int ingredientID) throws DALException;

    public List<IProductContentsDTO> getIngredients(int recipeID) throws DALException;

    public void addRecipe(List<IProductContentsDTO> recipe) throws DALException;

    public void updateRecipe(List<IProductContentsDTO> recipe) throws DALException;

    public void deleteRecipe(int recipeID) throws DALException;
}
