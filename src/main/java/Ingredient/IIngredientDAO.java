package Ingredient;

import Utilities.DAO;

import java.util.List;

public interface IIngredientDAO extends DAO {

  public void createIngredient(IIngredientDTO ingredient) throws DALException;

  public IIngredientDTO getIngredient(int ingredientID) throws DALException;

  public List<IIngredientDTO> getIngredientList() throws DALException;

  public void updateIngredient(IIngredientDTO ingredient) throws DALException;

  public void deleteIngredient(int ingredientID) throws DALException;

  public List<IIngredientDTO> getReorderList() throws DALException;

}
