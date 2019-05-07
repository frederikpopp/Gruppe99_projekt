package Recipe;

import ProductBatch.IProductContentsDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static Utilities.ConnectionHandler.createConnection;

public class RecipeContentsDAO implements IRecipeContentsDAO {
    @Override
    public void addIngredient(IRecipeContentsDTO ingredient) throws DALException {
        try (Connection c = createConnection()) {
            PreparedStatement stmt = c.prepareStatement(
                    "INSERT INTO product_contents VALUES(?,?,?,?)");
            stmt.setInt(1, ingredient.getRecipeID());
            stmt.setInt(2, ingredient.getIngredientID());
            stmt.setDouble(3, ingredient.getAmount());
            stmt.setString(4, ingredient.getUseCase());
            stmt.executeQuery();
        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }
    }

    @Override
    public void updateIngredient(IRecipeContentsDTO ingredient) throws DALException {
      try (Connection c = createConnection()) {
          PreparedStatement stmt = c.prepareStatement(
                  "UPDATE product_contents SET amount = ?, usecase = ? WHERE recipe_ID = ? AND ingredient_ID = ?");
          stmt.setInt(3, ingredient.getRecipeID());
          stmt.setInt(4, ingredient.getIngredientID());
          stmt.setDouble(1, ingredient.getAmount());
          stmt.setString(2, ingredient.getUseCase());
          stmt.executeQuery();
      } catch (SQLException e) {
          throw new DALException(e.getMessage());
      }
    }

    @Override
    public void deleteIngredient(int ingredientID, int recipe_ID) throws DALException {
      try (Connection c = createConnection()) {
          PreparedStatement stmt = c.prepareStatement(
                  "DELETE product_contents WHERE ingredientID = ? AND recipe_ID = ?");
          stmt.setInt(1, ingredientID);
          stmt.setInt(2, recipe_ID);
          stmt.executeQuery();
      } catch (SQLException e) {
          throw new DALException(e.getMessage());
      }
    }

    @Override
    public List<IRecipeContentsDTO> getIngredients(int recipeID) throws DALException {
      List<IRecipeContentsDTO> ingList = new ArrayList<>();
      try(Connection c = createConnection()) {
          PreparedStatement stmt = c.prepareStatement("SELECT * FROM recipe_contents WHERE recipe_ID = ?");
          stmt.setInt(1, recipeID);
          ResultSet results = stmt.executeQuery();
          while (results.next()) {
              IRecipeContentsDTO rc = new RecipeContentsDTO();
              rc.setRecipeID(results.getInt("recipe_ID"));
              rc.setIngredientID(results.getInt("ingredient_ID"));
              rc.setAmount(results.getDouble("amount"));
              rc.setUseCase(results.getString("usecase"));
              ingList.add(rc);
          }
      }catch (SQLException e) {
          throw new DALException(e.getMessage());
      }
    return ingList;
    }

    @Override
    public void addRecipe(List<IRecipeContentsDTO> recipe) throws DALException {
      try{
        for (IRecipeContentsDTO i : recipe) {
            addIngredient(i);
        }
      }catch (DALException e) {
        throw new DALException(e.getMessage());
      }
    }

    @Override
    public void updateRecipe(List<IRecipeContentsDTO> recipe) throws DALException {
      try{
        deleteRecipe(recipe.get(0).getRecipeID());
        addRecipe(recipe);
      }catch (DALException e) {
        throw new DALException(e.getMessage());
      }
    }

    @Override
    public void deleteRecipe(int recipeID) throws DALException {
      try (Connection c = createConnection()) {
          PreparedStatement stmt = c.prepareStatement(
                  "DELETE product_contents WHERE  recipe_ID = ?");
          stmt.setInt(1, recipeID);
          stmt.executeQuery();
      } catch (SQLException e) {
          throw new DALException(e.getMessage());
      }
    }
}
