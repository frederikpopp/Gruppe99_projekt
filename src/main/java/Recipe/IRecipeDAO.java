package Recipe;

import Utilities.DAO;

import java.sql.Timestamp;
import java.util.List;

public interface IRecipeDAO extends DAO {

    void createRecipe(IRecipeDTO recipe) throws DALException;

    IRecipeDTO getRecipe(int recipeID) throws DALException;

    List<IRecipeDTO> getRecipeList() throws DALException;

    void removeRecipe(int recipeID) throws DALException;

    List<IRecipeDTO> getOldVersions(int recipeID) throws DALException;

    List<IRecipeDTO> getVersionsBefore(int recipeID, Timestamp date) throws DALException;

    List<IRecipeDTO> getVersionsAfter(int recipeID, Timestamp date) throws DALException;
}
