package Recipe;

import Utilities.DAO;

import java.util.List;

public interface IRecipeDAO extends DAO {

    void createRecipe(IRecipeDTO recipe) throws DAO.DALException;

    IRecipeDTO getRecipe(int recipeID) throws DAO.DALException;

    List<IRecipeDTO> getRecipeList() throws DAO.DALException;

    void removeRecipe(int recipeID) throws DAO.DALException;
}
