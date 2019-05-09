package Ingredient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static Utilities.ConnectionHandler.createConnection;

public class IngredientDAO implements IIngredientDAO{

  @Override
  public void createIngredient(IIngredientDTO ingredient) throws DALException {
    try (Connection c = createConnection()) {
      PreparedStatement stmt = c.prepareStatement(
        "INSERT INTO ingredient VALUES(?,?,?)");
      stmt.setInt(1, ingredient.getIngredientID());
      stmt.setString(2, ingredient.getName());
      stmt.setInt(3, ingredient.getOrderStatus(1));
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new DALException(e.getMessage());
  }
  }

  @Override
  public IIngredientDTO getIngredient(int ingredientID) throws DALException {
    IIngredientDTO ingredient = new IngredientDTO();
    try (Connection c = createConnection()) {
      PreparedStatement stmt = c.prepareStatement(
        "SELECT * FROM ingredient WHERE ingredient_ID = ?");
      stmt.setInt(1, ingredientID);
      ResultSet result = stmt.executeQuery();
      if (result.next()) {
        ingredient.setIngredientID(ingredientID);
        ingredient.setName(result.getString("i_name"));
        boolean status = (result.getInt("reorder") == 1);
        ingredient.setOrderStatus(status);
      }
      return ingredient;
    } catch (SQLException e) {
      throw new DALException(e.getMessage());
    }
  }

  @Override
  public List<IIngredientDTO> getIngredientList() throws DALException {
    List<IIngredientDTO> ingredientList = new ArrayList<>();
    try (Connection c = createConnection()) {
      PreparedStatement stmt = c.prepareStatement(
        "SELECT * FROM ingredient");
      ResultSet results = stmt.executeQuery();
      while(results.next()) {
        IIngredientDTO ingredient = new IngredientDTO();
        ingredient.setIngredientID(results.getInt("ingredient_ID"));
        ingredient.setName(results.getString("i_name"));
        boolean status = (results.getInt("reorder") == 1);
        ingredient.setOrderStatus(status);
        ingredientList.add(ingredient);
      }
      return ingredientList;
    } catch (SQLException e) {
      throw new DALException(e.getMessage());
    }
  }

  @Override
  public void updateIngredient(IIngredientDTO ingredient) throws DALException {
    try (Connection c = createConnection()) {
      PreparedStatement stmt = c.prepareStatement(
        "UPDATE ingredient SET i_name = ?, reorder = ? WHERE ingredient_ID = ?");
      stmt.setString(1, ingredient.getName());
      stmt.setInt(2, ingredient.getOrderStatus(1));
      stmt.setInt(3, ingredient.getIngredientID());
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new DALException(e.getMessage());
    }
  }

  @Override
  public void deleteIngredient(int ingredientID) throws DALException {
    try (Connection c = createConnection()) {
      PreparedStatement stmt = c.prepareStatement(
        "DELETE FROM ingredient WHERE ingredient_ID = ?");
      stmt.setInt(1, ingredientID);
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new DALException(e.getMessage());
    }
  }

  @Override
  public List<IIngredientDTO> getReorderList() throws DALException {
    List<IIngredientDTO> reorderList = new ArrayList<>();
    try (Connection c = createConnection()) {
      PreparedStatement stmt = c.prepareStatement(
        "SELECT * FROM ingredient WHERE reorder = 1");
      ResultSet results = stmt.executeQuery();
      while(results.next()) {
        IIngredientDTO ingredient = new IngredientDTO();
        ingredient.setIngredientID(results.getInt("ingredient_ID"));
        ingredient.setName(results.getString("i_name"));
        boolean status = (results.getInt("reorder") == 1);
        ingredient.setOrderStatus(status);
        reorderList.add(ingredient);
      }
      return reorderList;
    } catch (SQLException e) {
      throw new DALException(e.getMessage());
    }
  }
}
