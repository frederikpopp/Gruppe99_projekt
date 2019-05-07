package Recipe;

import ProductBatch.IProductContentsDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static Utilities.ConnectionHandler.createConnection;

public class RecipeContentsDAO implements IRecipeContentsDAO {
    @Override
    public void addIngredient(IProductContentsDTO ingredient) throws DALException {
        try (Connection c = createConnection()) {
            PreparedStatement stmt = c.prepareStatement(
                    "INSERT INTO product_contents VALUES(?,?,?,?");
        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }
    }

    @Override
    public void updateIngredient(IProductContentsDTO ingredient) throws DALException {

    }

    @Override
    public void deleteIngredient(int ingredientID) throws DALException {

    }

    @Override
    public List<IProductContentsDTO> getIngredients(int recipeID) throws DALException {
        return null;
    }

    @Override
    public void addRecipe(List<IProductContentsDTO> recipe) throws DALException {

    }

    @Override
    public void updateRecipe(List<IProductContentsDTO> recipe) throws DALException {

    }

    @Override
    public void deleteRecipe(int recipeID) throws DALException {

    }
}
